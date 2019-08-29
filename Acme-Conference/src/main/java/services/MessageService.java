
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Message;
import repositories.MessageRepository;
import domain.Box;
import domain.Submission;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository		messageRepository;

	@Autowired
	private ActorService			as;

	@Autowired
	private AdministratorService	ad;

	@Autowired
	private BoxService				mbs;


	
		public Message create() {
			final Date date = new Date();
			final Message m = new Message();
			final Actor a = this.as.findByPrincipal();
			m.setMoment(date);
			m.setSender(a);
			m.setRecipients(new ArrayList<Actor>());
			System.out.println("create servicio:");
			System.out.println(m.getSender().getName());
			return m;
		}
	
		private Box findBoxToGo(Message m) {
			final Actor me = this.as.findByPrincipal();
			Box res = null;
			Iterator<Box> x = me.getBoxes().iterator();
			boolean encontrado = false;
			while(x.hasNext() && !encontrado) {
				Box b = x.next();
				
				if(b.getName().equals("Out") && m.getSender().getId() == me.getId()) {
					if(b.getMessages().contains(m)) {
						res = b;
						encontrado = true;
					}
				} else if(b.getName().equals("Notification") && m.getSender().getId() != me.getId()) {
					if(b.getMessages().contains(m)) {
						res = b;
						encontrado = true;
					}
				}else if(b.getName().equals("In") && m.getSender().getId() != me.getId()) {
					if(b.getMessages().contains(m)) {
						res = b;
						encontrado = true;
					}
				}
			}
				
			return res;
		}
		public String deleteMessage(final Message m) {
			Assert.notNull(m);
			Assert.isTrue(!(m.getId() == 0));
	
			Message borrar = this.findOne(m.getId());
			
			Box boxToDelete = findBoxToGo(borrar);
			boxToDelete.getMessages().remove(borrar);
			mbs.save(boxToDelete);
			
			String boxn = boxToDelete.getName().equals("In") ? "" : boxToDelete.getName();
			return boxn;
		}

	
		public Collection<Message> findAll() {
			return this.messageRepository.findAll();
		}
		
		public Collection<Message> findAllMines(Actor actor, String boxName){
			Collection<Box> boxes = actor.getBoxes();
			Box caja = null;
			for (Box box : boxes) {
				if(box.getName().equals(boxName)) {
					caja = box;
					break;
				}
			}
			
			return caja.getMessages();
		}
		
		public Collection<Message> findBySender(Actor actor){
			return this.messageRepository.findByActor(actor);
		}
	
		public Message findOne(final int messageId) {
			return this.messageRepository.findOne(messageId);
		}
	
		public Message save(final Message message) {
			return this.messageRepository.save(message);
		}
	
	public void NotificateMessage(final Submission s) {
		final Message res = new Message();
		String StatusEsp = null;
		if (s.getStatus().contains("REJECTED"))
			StatusEsp = "RECHAZADA";
		else if (s.getStatus().contains("ACCEPTED"))
			StatusEsp = "ACEPTADA";
		res.setBody("Your submission " + s.getTicker() + ", has been " + s.getStatus() + "./n" + "Tu solicitud " + s.getTicker() + ", ha sido" + StatusEsp + ".");
		res.setId(0);
		res.setMoment(Calendar.getInstance().getTime());
		final ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(s.getAuthor());
		res.setRecipients(actors);
		res.setSender(this.ad.findByPrincipal());
		res.setSubject("Notification message, mensaje de notificación");
		res.setTopic("SYSTEM");

		final Message enviado = this.messageRepository.save(res);
		final Collection<Box> boxes = s.getAuthor().getBoxes();
		for (final Box b : boxes)
			if (b.getName().toLowerCase().contains("notification")) {
				final List<Message> messages = new ArrayList<>(b.getMessages());
				messages.add(enviado);
				b.setMessages(messages);
				this.mbs.saveNotification(b);
			}
		final Collection<Box> boxes2 = this.ad.findByPrincipal().getBoxes();
		for (final Box b2 : boxes2)
			if (b2.getName().toLowerCase().contains("out")) {
				final List<Message> messages2 = new ArrayList<>(b2.getMessages());
				messages2.add(enviado);
				b2.setMessages(messages2);
				this.mbs.saveNotification(b2);
			}

	}
}
