
package controllers;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import domain.Author;

@Controller
@RequestMapping("/author")
public class AuthorController extends AbstractController {

	@Autowired
	private AuthorService	authorService;


	// Constructors -----------------------------------------------------------

	public AuthorController() {
		super();
	}

	// Register ---------------------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final Author author, final BindingResult binding) {
		ModelAndView result;

		try {

			final String vacia = "";
			if (!author.getEmail().isEmpty() || author.getEmail() != vacia)
				Assert.isTrue(author.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || author.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.authorService.save(author);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(author);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(author, "author.email.error");
			else
				result = this.createEditModelAndView(author, "author.comit.error");
		}
		return result;

	}

	protected ModelAndView createEditModelAndView(final Author author) {
		ModelAndView result;

		result = this.createEditModelAndView(author, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Author author, final String message) {
		ModelAndView result;

		result = new ModelAndView("author/register");
		result.addObject("author", author);
		result.addObject("message", message);

		return result;
	}

}
