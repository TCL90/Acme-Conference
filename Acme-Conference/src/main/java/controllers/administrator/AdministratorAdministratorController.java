
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import repositories.ActorRepository;
import security.Authority;
import security.UserAccount;
import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Box;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorRepository			actorRepository;


	////////////////////////////
	//////////CREATE////////////
	////////////////////////////
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final Administrator administrator = new Administrator();
		administrator.setUserAccount(userAccount);
		res = this.createCreateModelAndView(administrator);

		return res;

	}

	protected ModelAndView createCreateModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createCreateModelAndView(administrator, null);

		return result;

	}

	protected ModelAndView createCreateModelAndView(final Administrator administrator, final String message) {
		ModelAndView result;
		result = new ModelAndView("administrator/administrator/create");
		result.addObject("administrator", administrator);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCreateModelAndView(administrator);
		else
			try {
				final String vacia = "";
				if (!administrator.getEmail().isEmpty() || administrator.getEmail() != vacia)
					Assert.isTrue(administrator.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || administrator.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$") || administrator.getEmail().matches("^[A-z0-9]+@$")
						|| administrator.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@>$"), "Wrong email");

				Assert.isTrue(this.actorRepository.findByUsername(administrator.getUserAccount().getUsername()) == null, "username taken");

				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:../../");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createCreateModelAndView(administrator, "administrator.email.error");
				else if (error.getMessage() == "username taken")
					result = this.createCreateModelAndView(administrator, "administrator.username.error");
				else
					result = this.createCreateModelAndView(administrator, "administrator.comit.error");

			}
		return result;

	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final Administrator administrator;

		administrator = this.administratorService.findByPrincipal();
		//customer = this.customerService.findOne(customerId);
		res = this.createEditModelAndView(administrator);
		return res;

	}

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		UserAccount userAccount;

		boxes = administrator.getBoxes();
		userAccount = administrator.getUserAccount();

		result = new ModelAndView("administrator/administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("boxes", boxes);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCreateModelAndView(administrator);
		else
			try {
				final String vacia = "";
				if (!administrator.getEmail().isEmpty() || administrator.getEmail() != vacia)
					Assert.isTrue(administrator.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || administrator.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$") || administrator.getEmail().matches("^[A-z0-9]+@$")
						|| administrator.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@>$"), "Wrong email");

				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:../../");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditModelAndView(administrator, "administrator.email.error");
				else
					result = this.createEditModelAndView(administrator, "administrator.comit.error");

			}

		return result;

	}
}
