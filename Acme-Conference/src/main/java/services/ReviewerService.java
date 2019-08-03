
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReviewerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Reviewer;

@Service
@Transactional
public class ReviewerService {

	@Autowired
	private ReviewerRepository	reviewerRepository;


	//Constructor
	public ReviewerService() {
		super();
	}

	public Reviewer create() {

		Reviewer result;
		result = new Reviewer();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.REVIEWER);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Reviewer

		return result;
	}

	public Reviewer findByPrincipal() {
		Reviewer res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Reviewer save(final Reviewer reviewer) {
		Assert.notNull(reviewer);

		final String pnumber = reviewer.getPhoneNumber();
		//TODO: DESCOMENTAR
		//final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		//final String cc = cus.getPhoneNumberCode();
		//		if (pnumber.matches("^[0-9]{4,}$"))
		//			reviewer.setPhoneNumber(cc.concat(pnumber));

		//		if (reviewer.getId() != 0) {
		//			Assert.isTrue(this.actorService.checkReviewer());
		//
		//			// Modified Reviewer must be logged Reviewer
		//			final Reviewer logReviewer;
		//			logReviewer = this.findByPrincipal();
		//			Assert.notNull(logReviewer);
		//			Assert.notNull(logReviewer.getId());
		//
		//		} else {
		//TODO: DESCOMENTAR
		//			final Collection<Box> boxes = this.actorService1.createPredefinedBoxes();
		//			reviewer.setBoxes(boxes);

		if (reviewer.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = reviewer.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = reviewer.getUserAccount();
			cuenta.setPassword(hash);
			reviewer.setUserAccount(cuenta);
		}

		Reviewer res;

		res = this.reviewerRepository.save(reviewer);

		this.reviewerRepository.flush();
		return res;
	}

	public Reviewer findByUserAccount(final UserAccount userAccount) {
		Reviewer res;
		Assert.notNull(userAccount);

		res = this.reviewerRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Reviewer> findAll() {
		return this.reviewerRepository.findAll();
	}

}
