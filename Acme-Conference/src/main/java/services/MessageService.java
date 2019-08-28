
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
import repositories.ActorRepository;
import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.Submission;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository		messageRepository;

	@Autowired
	private ActorRepository			ar;

	@Autowired
	private UserAccountRepository	ur;

	@Autowired
	private ActorService			as;

	@Autowired
	private AdministratorService	ad;

	@Autowired
	private BoxService				mbs;


	
		public Message create() {
			final Date date = new Date();
			final Message m = new Message();
			final UserAccount actual = LoginService.getPrincipal();
			final Actor a = this.ar.getActor(actual);
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

	//
	//	public Message broadcastMessage(final Message msg) {
	//		final UserAccount actual = LoginService.getPrincipal();
	//		final Actor admin = this.ar.getActor(actual);
	//		Assert.notNull(msg);
	//		Assert.isTrue(!admin.getBan());
	//
	//		final Message result = this.save(msg);
	//
	//		final Collection<Box> aboxes = admin.getBoxes();
	//		for (final Box abox : aboxes)
	//			if (abox.getName().endsWith("out box") && abox.getPredefined() == true) {
	//				final Collection<Message> ames = abox.getMessages();
	//				ames.add(result);
	//				abox.setMessages(ames);
	//			}
	//
	//		final Collection<Actor> listaActores = this.as.findAll();
	//		listaActores.remove(admin);
	//		for (final Actor actors : listaActores)
	//			for (final Box msb : actors.getBoxes())
	//
	//				if (msb.getName().endsWith("notification box") && msb.getPredefined() == true) {
	//					final Collection<Message> rmes = msb.getMessages();
	//					rmes.add(result);
	//					msb.setMessages(rmes);
	//				}
	//		return result;
	//
	//	}
	//
	//	public String getTemplateSecurityBreachNotificationMessage() {
	//		return "Lamentamos informar de que hemos encontrado una posible brecha de seguridad" + " que podr�a afectar a los datos e informaci�n que usted como usuario ha ingresado"
	//			+ " en nuestra web. Como consecuencia, sus datos, usuario y contrase�a pueden haber sido" + " filtrados a personas ajenas a Acme. Por favor, le pedimos que cambie su contrase�a lo antes posible, "
	//			+ "y compruebe que su informaci�n est� inalterada. \n \n Si necesita informaci�n sobre este asunto, por favor, "
	//			+ "no dude en contactar con nosotros usando la direcci�n de correo support.madruga@acme.com o utilizando nuestro tel�fono de asistencia al cliente."
	//			+ " La brecha de seguridad ha sido identificada y estamos trabajando para poder solucionar este problema lo antes posible. \n De nuevo, desde Acme, lamentamos lo sucedido."
	//			+ " \n \n We are sorry to admit that we found a security breach that can affect the data and information you have introduced in our domain as an user."
	//			+ "Due to this breach, your data, user and password may be filtered to people alien to Acme. Please, we ask you to change your password as soon as possible, and to check that your information and data are still intact."
	//			+ "\n\n If you need further information about this issue, please be sure to contact us using the email support.madruga@acme.com or our customer service phone."
	//			+ "The security breach has been identified and we are working hard to fix it. \n Once again, we are very sorry for this error. ";
	//	}
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
	
		public void delete(final Message message) {
			this.messageRepository.delete(message);
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
		res.setSubject("Notification message, mensaje de notificaci�n");
		res.setTopic("SYSTEM");

		final Message enviado = this.messageRepository.save(res);
		final Collection<Box> boxes = s.getAuthor().getBoxes();
		for (final Box b : boxes)
			if (b.getName().contains("notification")) {
				b.getMessages().add(enviado);
				this.mbs.save(b);
			}
		final Collection<Box> boxes2 = this.ad.findByPrincipal().getBoxes();
		for (final Box b2 : boxes2) {
			if (b2.getName().contains("out"))
				b2.getMessages().add(enviado);
			this.mbs.save(b2);
		}

	}
}
