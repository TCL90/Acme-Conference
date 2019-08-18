
package controllers;

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
import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	@Autowired
	private SponsorService	sponsorService;

	@Autowired
	private ActorRepository	actorRepository;


	// Constructors -----------------------------------------------------------

	public SponsorController() {
		super();
	}

	// Register ---------------------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		//		Sponsority sponsority;
		//		Collection<Sponsority> sponsorities;
		//		UserAccount userAccount;
		//		userAccount = new UserAccount();
		//		sponsorities = userAccount.getSponsorities();
		//		sponsority = new Sponsority();
		//		sponsority.setSponsority(Sponsority.AUTHOR);
		//		sponsorities.add(sponsority);
		//		userAccount.setSponsorities(sponsorities);

		final Sponsor sponsor = this.sponsorService.create();
		//sponsor.setUserAccount(userAccount);
		res = this.createEditModelAndView(sponsor);

		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult binding) {
		ModelAndView result;

		try {

			final String vacia = "";
			if (!sponsor.getEmail().isEmpty() || sponsor.getEmail() != vacia)
				Assert.isTrue(sponsor.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || sponsor.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			Assert.isTrue(this.actorRepository.findByUsername(sponsor.getUserAccount().getUsername()) == null, "username taken");

			this.sponsorService.save(sponsor);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(sponsor);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(sponsor, "sponsor.email.error");
			else if (oops.getMessage() == "username taken")
				result = this.createEditModelAndView(sponsor, "sponsor.username.error");
			else
				result = this.createEditModelAndView(sponsor, "sponsor.comit.error");
		}
		return result;

	}
	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {
		ModelAndView result;

		result = this.createEditModelAndView(sponsor, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String message) {
		ModelAndView result;

		result = new ModelAndView("sponsor/register");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);

		return result;
	}

}
