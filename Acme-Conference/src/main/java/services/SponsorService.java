
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;


	public Sponsor findByPrincipal() {
		Sponsor res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Sponsor findByUserAccount(final UserAccount userAccount) {
		Sponsor res;
		Assert.notNull(userAccount);

		res = this.sponsorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Sponsor create() {

		Sponsor result;
		result = new Sponsor();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.SPONSOR);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		return result;
	}

	public Sponsor save(final Sponsor sponsor) {
		Assert.notNull(sponsor);

		final String pnumber = sponsor.getPhoneNumber();
		//TODO: DESCOMENTAR
		//final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		//final String cc = cus.getPhoneNumberCode();
		//		if (pnumber.matches("^[0-9]{4,}$"))
		//			sponsor.setPhoneNumber(cc.concat(pnumber));

		//		if (sponsor.getId() != 0) {
		//			Assert.isTrue(this.actorService.checkSponsor());
		//
		//			// Modified Sponsor must be logged Sponsor
		//			final Sponsor logSponsor;
		//			logSponsor = this.findByPrincipal();
		//			Assert.notNull(logSponsor);
		//			Assert.notNull(logSponsor.getId());
		//
		//		} else {
		//TODO: DESCOMENTAR
		//			final Collection<Box> boxes = this.actorService1.createPredefinedBoxes();
		//			sponsor.setBoxes(boxes);
		if (sponsor.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = sponsor.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = sponsor.getUserAccount();
			cuenta.setPassword(hash);
			sponsor.setUserAccount(cuenta);
		}
		//TODO: DESCOMENTAR
		//			final Finder find = new Finder();
		//
		//			find.setMoment(new Date());
		//			final Finder find2 = this.finderRepository.save(find);

		//			sponsor.setFinder(find2);
		//		}
		// Restrictions
		Sponsor res;

		res = this.sponsorRepository.save(sponsor);

		this.sponsorRepository.flush();
		return res;
	}

	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}
}
