
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {

	@Autowired
	private BoxRepository	boxRepository;

	@Autowired
	private ActorService	as;

	@Autowired
	private MessageService	ms;
	
	@Autowired
	private AdministratorService administratorService;

	

	public Collection<Box> createBoxesForNewActor() {

		final Box bIn = new Box();
		bIn.setName("In");

		final Box bNotif = new Box();
		bNotif.setName("Notification");

		final Box bOut = new Box();
		bOut.setName("Out");

		final List<Box> boxes = new ArrayList<Box>();
		boxes.add(bIn);
		boxes.add(bNotif);
		boxes.add(bOut);

		final List<Box> res = new ArrayList<Box>();

		for (final Box b : boxes)
			res.add(this.boxRepository.save(b));

		return res;
	}

	public Box save(final Box messageBox) {
		final Actor a = this.as.findByPrincipal();
		final Box mb = this.boxRepository.save(messageBox);
		if (!a.getBoxes().contains(messageBox)) {
			final Collection<Box> mboxes = a.getBoxes();
			mboxes.add(mb);
			a.setBoxes(mboxes);
			Assert.isTrue(a.getBoxes().contains(mb));

		}
		return mb;

	}
	
	public void saveNotification(final Box messageBox) {
		final Actor a = this.as.findByPrincipal();
		Assert.isTrue(a.getBoxes().contains(messageBox) || this.administratorService.checkAdmin());
		this.boxRepository.save(messageBox);

	}
	
	public Message sendMessage(final Message msg) {
		final Actor a = this.as.findByPrincipal();
		Assert.notNull(msg);

		final Message result = this.ms.save(msg);

		final Collection<Box> aboxes = a.getBoxes();
		for (final Box abox : aboxes)
			if (abox.getName().endsWith("Out")) {
				final Collection<Message> ames = abox.getMessages();
				ames.add(result);
				abox.setMessages(ames);
			}

		final Collection<Actor> recipients = result.getRecipients();
		recipients.remove(a);
		for (final Actor r : recipients) {
			final Collection<Box> rboxes = r.getBoxes();
			for (final Box rbox : rboxes)

				if (rbox.getName().endsWith("In")) {
					final Collection<Message> rmes = rbox.getMessages();
					rmes.add(result);
					rbox.setMessages(rmes);
				}
		}
		return result;
	}
	
	

}
