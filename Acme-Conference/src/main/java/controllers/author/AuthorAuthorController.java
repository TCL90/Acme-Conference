
package controllers.author;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import repositories.ActorRepository;
import services.AuthorService;
import controllers.AbstractController;
import domain.Author;

@Controller
@RequestMapping("/author/author")
public class AuthorAuthorController extends AbstractController {

	@Autowired
	private AuthorService	authorService;

	@Autowired
	private ActorRepository	actorRepository;


	// Register ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		//		Authority authority;
		//		Collection<Authority> authorities;
		//		UserAccount userAccount;
		//		userAccount = new UserAccount();
		//		authorities = userAccount.getAuthorities();
		//		authority = new Authority();
		//		authority.setAuthority(Authority.AUTHOR);
		//		authorities.add(authority);
		//		userAccount.setAuthorities(authorities);

		final Author author = this.authorService.findByPrincipal();
		//author.setUserAccount(userAccount);
		res = this.createEditModelAndView(author);

		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Author author, final BindingResult binding) {
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
			else if (oops.getMessage() == "username taken")
				result = this.createEditModelAndView(author, "author.username.error");
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

		result = new ModelAndView("author/author/edit");
		result.addObject("author", author);
		result.addObject("message", message);
		result.addObject("boxes", author.getBoxes());

		return result;
	}

}
