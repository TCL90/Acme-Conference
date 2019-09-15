
package controllers.administrator;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.AuthorService;
import services.ConferenceService;
import controllers.AbstractController;
import domain.Author;

@Controller
@RequestMapping("/author/administrator")
public class AuthorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ConferenceService		conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView decision() {
		ModelAndView result;
		Collection<Author> authors = null;
		try {
			authors = this.authorService.findAll();
			Assert.notNull(this.administratorService.findByPrincipal());
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("author/administrator/list");
		result.addObject("requestURI", "/author/administrator/list.do");
		result.addObject("authors", authors);
		return result;
	}

	@RequestMapping(value = "/procedure", method = RequestMethod.GET)
	public ModelAndView procedure() {
		ModelAndView result;

		Collection<Author> authors = null;
		List<String> buzzwords = null;

		try {
			authors = this.authorService.findAll();
			Assert.notNull(this.administratorService.findByPrincipal());

			buzzwords = this.conferenceService.findBuzzwords();

			this.authorService.score(buzzwords);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("author/administrator/list");
		result.addObject("requestURI", "/author/administrator/list.do");
		result.addObject("authors", authors);
		result.addObject("buzzwords", buzzwords);
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int authorId) {
		final ModelAndView result;
		Author author;
		try {
			author = this.authorService.findOne(authorId);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("author/administrator/show");
		result.addObject("name", author.getName());
		result.addObject("surname", author.getSurname());
		result.addObject("middleName", author.getMiddleName());
		result.addObject("address", author.getAddress());
		result.addObject("email", author.getEmail());
		result.addObject("photo", author.getPhoto());
		result.addObject("phoneNumber", author.getPhoneNumber());
		result.addObject("score", author.getScore());

		return result;
	}
}
