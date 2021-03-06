
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository			actorRepository;

	@Autowired
	private BoxService				mbs;

	@Autowired
	private CustomisationService	cs;


	public Collection<Actor> findAll() {
		return this.actorRepository.findAll();
	}
	public Actor findOne(final int actorId) {
		return this.actorRepository.findOne(actorId);
	}
	public Actor save(final Actor actor) {
		return this.actorRepository.save(actor);
	}

	public Actor findByPrincipal() {
		Actor res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Actor res;
		Assert.notNull(userAccount);
		Assert.notNull(userAccount.getId());

		res = this.actorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}
	//	public Collection<Box> getMyBoxes() {
	//		final UserAccount actual = LoginService.getPrincipal();
	//		final Actor a = this.actorRepository.getActor(actual);
	//		return a.getBoxes();
	//	}

	//	public Box editBox(final Box m) {
	//		final UserAccount actual = LoginService.getPrincipal();
	//		final Actor a = this.actorRepository.getActor(actual);
	//
	//		Assert.isTrue(!a.getBan());
	//
	//		//Compruebo que no se produce ninguna redundancia Padre-Hijo:
	//		boolean fine = true;
	//		final Collection<Box> desc = m.getDescendants();
	//		if (desc != null)
	//			for (final Box b : desc) {
	//				if (b.getId() == m.getId()) {
	//					fine = false;
	//					break;
	//				}
	//				for (final Box b2 : b.getDescendants())
	//					if (b2.getId() == m.getId()) {
	//						fine = false;
	//						break;
	//					}
	//
	//			}
	//		Assert.isTrue(fine);
	//		//Fin de la comprobación
	//
	//		final Box result = this.mbs.save(m);
	//
	//		return result;
	//
	//	}
	//
	//	public void deleteMessageBox(final Box m) {
	//		final UserAccount actual = LoginService.getPrincipal();
	//		final Actor a = this.actorRepository.getActor(actual);
	//
	//		Assert.isTrue(a.getBoxes().contains(m));
	//		Assert.isTrue(!a.getBan());
	//
	//		Assert.isTrue(!m.getPredefined());
	//		final Collection<Box> actorBoxes = a.getBoxes();
	//		for (final Box b : actorBoxes)
	//			if (b.getDescendants().contains(m))
	//				b.getDescendants().remove(m);
	//		a.setBoxes(actorBoxes);
	//		actorBoxes.remove(m);
	//		a.setBoxes(actorBoxes);
	//		this.mbs.delete(m);
	//
	//	}
	//


}
