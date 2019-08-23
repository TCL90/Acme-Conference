
package controllers.administrator;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
