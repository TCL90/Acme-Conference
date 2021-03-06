
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Box;
import domain.Customisation;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CustomisationService	customisationService;

	@Autowired
	private BoxService				boxService;


	//Constructor
	public AdministratorService() {
		super();
	}

	public Administrator create() {

		Administrator result;
		result = new Administrator();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.ADMIN);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// admin

		return result;
	}

	public Administrator save(final Administrator administrator) {

		Assert.notNull(administrator);

		final String pnumber = administrator.getPhoneNumber();
		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			administrator.setPhoneNumber(cc.concat(pnumber));

		if (administrator.getId() != 0) {
			Assert.isTrue(this.checkAdmin());

			// Modified Author must be logged Author
			final Administrator logAdmin;
			logAdmin = this.findByPrincipal();
			Assert.notNull(logAdmin);
			Assert.notNull(logAdmin.getId());
		}

		if (administrator.getId() == 0) {

			final Collection<Box> boxes = this.boxService.createBoxesForNewActor();
			administrator.setBoxes(boxes);

			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = administrator.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = administrator.getUserAccount();
			cuenta.setPassword(hash);
			administrator.setUserAccount(cuenta);
		}

		Administrator res;

		res = this.administratorRepository.save(administrator);

		this.administratorRepository.flush();
		return res;
	}
	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Administrator findByUserAccount(final UserAccount userAccount) {
		Administrator res;
		Assert.notNull(userAccount);

		res = this.administratorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public boolean checkAdmin() {
		boolean res = false;

		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

		if (AuCollection.contains(a))
			res = true;
		return res;

	}
	
	public boolean checkAdminActor() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public List<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}
}
