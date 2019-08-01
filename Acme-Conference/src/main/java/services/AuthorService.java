
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Author;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository	authorRepository;


	//Constructor
	public AuthorService() {
		super();
	}

	public Author create() {

		Author result;
		result = new Author();

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.AUTHOR);
		newUser.addAuthority(f);
		result.setUserAccount(newUser);

		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhoto("");

		// Author

		return result;
	}

	public Author findByPrincipal() {
		Author res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	public Author save(final Author author) {
		Assert.notNull(author);

		final String pnumber = author.getPhoneNumber();
		//TODO: DESCOMENTAR
		//final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		//final String cc = cus.getPhoneNumberCode();
		//		if (pnumber.matches("^[0-9]{4,}$"))
		//			author.setPhoneNumber(cc.concat(pnumber));

		//		if (author.getId() != 0) {
		//			Assert.isTrue(this.actorService.checkAuthor());
		//
		//			// Modified Author must be logged Author
		//			final Author logAuthor;
		//			logAuthor = this.findByPrincipal();
		//			Assert.notNull(logAuthor);
		//			Assert.notNull(logAuthor.getId());
		//
		//		} else {
		//TODO: DESCOMENTAR
		//			final Collection<Box> boxes = this.actorService1.createPredefinedBoxes();
		//			author.setBoxes(boxes);
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String oldpass = author.getUserAccount().getPassword();
		final String hash = encoder.encodePassword(oldpass, null);

		final UserAccount cuenta = author.getUserAccount();
		cuenta.setPassword(hash);
		author.setUserAccount(cuenta);

		//TODO: DESCOMENTAR
		//			final Finder find = new Finder();
		//
		//			find.setMoment(new Date());
		//			final Finder find2 = this.finderRepository.save(find);

		//			author.setFinder(find2);
		//		}
		// Restrictions
		Author res;

		res = this.authorRepository.save(author);

		this.authorRepository.flush();
		return res;
	}

	public Author findByUserAccount(final UserAccount userAccount) {
		Author res;
		Assert.notNull(userAccount);

		res = this.authorRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	public Collection<Author> findAll() {
		return this.authorRepository.findAll();
	}

}
