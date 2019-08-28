
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Author;
import domain.CameraReadyPaper;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository		authorRepository;

	@Autowired
	private CameraReadyPaperService	cameraReadyPaperService;


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
		if (author.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = author.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = author.getUserAccount();
			cuenta.setPassword(hash);
			author.setUserAccount(cuenta);
		}
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

	public Collection<Actor> findAll2() {
		return this.authorRepository.findAll2();
	}

	public Collection<Author> score(final List<String> buzzwords) {
		final Collection<Author> authors = this.authorRepository.findAll();
		Collection<CameraReadyPaper> cams = null;
		int score = 0;
		double scoreRedondeado = 0;
		for (final Author a : authors) {
			score = 0;
			//Se comienza con el score a 0
			a.setScore(0);
			cams = this.cameraReadyPaperService.findByAuthorId(a.getId());
			//Para cada camera ready paper se comprueban las palabras
			for (final CameraReadyPaper cam : cams) {
				final String[] titAr = cam.getTitle().split(" ");
				final List<String> titLi = new ArrayList<String>(Arrays.asList(titAr));
				//				final List<String> title = new ArrayList<String>(Arrays.asList(cam.getTitle().split(" ")));
				//El número de palabras que coinciden se obtiene con retain
				titLi.retainAll(buzzwords);
				//Por cada palabra se incrementa en un punto el score
				score = titLi.size() + score;
			}
			scoreRedondeado = AuthorService.redondearDecimales(score, 2);
			a.setScore(score);
			this.save(a);
		}
		return authors;
	}

	public static double redondearDecimales(final double valorInicial, final int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
		resultado = Math.round(resultado);
		resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
		return resultado;
	}

	public Author findOne(final int authorId) {
		return this.authorRepository.findOne(authorId);
	}

}
