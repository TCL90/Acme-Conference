
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
import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Author;
import domain.Box;
import domain.CameraReadyPaper;
import domain.Customisation;
import domain.Finder;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository		authorRepository;

	@Autowired
	private CameraReadyPaperService	cameraReadyPaperService;

	@Autowired
	private CustomisationService	customisationService;

	@Autowired
	private BoxService				boxService;

	@Autowired
	private FinderRepository		finderRepository;


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
		final Finder finder = new Finder();
		result.setFinder(finder);
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

		final Customisation cus = ((List<Customisation>) this.customisationService.findAll()).get(0);
		final String cc = cus.getPhoneNumberCode();
		if (pnumber.matches("^[0-9]{4,}$"))
			author.setPhoneNumber(cc.concat(pnumber));

		if (author.getId() == 0) {

			final Collection<Box> boxes = this.boxService.createBoxesForNewActor();
			author.setBoxes(boxes);

			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = author.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = author.getUserAccount();
			cuenta.setPassword(hash);
			author.setUserAccount(cuenta);

			final Finder find = new Finder();

			final Finder find2 = this.finderRepository.save(find);

			author.setFinder(find2);

		}

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
		double highestScore = 0;
		double scoreRedondeado = 0;
		double ratio = 0;
		String cleanTitle = "";
		String cleanSummary = "";
		for (final Author a : authors) {
			score = 0;
			//Se comienza con el score a 0
			a.setScore(0);
			cams = this.cameraReadyPaperService.findByAuthorId(a.getId());
			//Para cada camera ready paper se comprueban las palabras
			for (final CameraReadyPaper cam : cams) {
				//Se limpian las palabras
				cleanTitle = cam.getTitle().replace(".", "");
				cleanSummary = cam.getSummary().replace(".", "");
				final String[] titAr = cleanTitle.split(" ");
				final String[] sumAr = cleanSummary.split(" ");
				final List<String> titLi = new ArrayList<String>(Arrays.asList(titAr));
				final List<String> sumLi = new ArrayList<String>(Arrays.asList(sumAr));
				//Se limpian las palabras
				//				for (final String word : titLi)
				//					word.replace(".", "");
				titLi.addAll(sumLi);
				//El número de palabras que coinciden se obtiene con retain
				titLi.retainAll(buzzwords);
				//Por cada palabra se incrementa en un punto el score
				score = titLi.size() + score;

			}

			if (score > highestScore)
				highestScore = score;
			a.setScore(score);
			//this.save(a);
		}
		for (final Author a : authors) {
			ratio = (a.getScore() / highestScore) * 10;
			scoreRedondeado = AuthorService.redondearDecimales(ratio, 2);
			a.setScore(scoreRedondeado);
		}
		return authors;
	}

	public boolean checkAuthor() {
		boolean res = false;

		final Authority a = new Authority();
		a.setAuthority(Authority.AUTHOR);

		if (LoginService.getPrincipal().getAuthorities().contains(a))
			res = true;
		return res;

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
