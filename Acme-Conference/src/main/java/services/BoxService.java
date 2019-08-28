
package services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import repositories.BoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {


	@Autowired
	private BoxRepository	messageBoxRepository;

	@Autowired
	private ActorRepository	actorRepository;

	@Autowired
	private MessageService	ms;

	public Collection<Box> createBoxesForNewActor() {
		
		Box bIn = new Box();
		bIn.setId(0);
		bIn.setName("In");
		
		Box bNotif = new Box();
		bNotif.setId(0);
		bNotif.setName("Notification");
		
		Box bOut = new Box();
		bOut.setId(0);
		bOut.setName("Out");
		
		
		return Arrays.asList(bIn, bNotif, bOut);
	}

	public Box save(final Box messageBox) {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);
		final Box mb = this.messageBoxRepository.save(messageBox);
		if (!a.getBoxes().contains(messageBox)) {
			final Collection<Box> mboxes = a.getBoxes();
			mboxes.add(mb);
			a.setBoxes(mboxes);
			Assert.isTrue(a.getBoxes().contains(mb));

		}
		return mb;

	}
	
	public Message sendMessage(final Message msg) {
		final UserAccount actual = LoginService.getPrincipal();
		final Actor a = this.actorRepository.getActor(actual);
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
