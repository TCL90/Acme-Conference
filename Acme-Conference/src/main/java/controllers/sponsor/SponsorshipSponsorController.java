
package controllers.sponsor;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceService;
import services.CustomisationService;
import services.SponsorService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Conference;
import domain.Customisation;
import domain.Sponsor;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship/sponsor")
public class SponsorshipSponsorController extends AbstractController {

	@Autowired
	private SponsorshipService		sponsorshipService;

	@Autowired
	private SponsorService			sponsorService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private CustomisationService	customisationService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Sponsorship> sponsorships = null;

		try {
			final Sponsor sponsor = this.sponsorService.findByPrincipal();
			sponsorships = this.sponsorshipService.findBySponsor(sponsor.getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("sponsorship/sponsor/list");
		result.addObject("sponsorships", sponsorships);
		result.addObject("requestURI", "/sponsorship/sponsor/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final Sponsorship sponsorship = this.sponsorshipService.create();
		result = this.createModelAndView(sponsorship);
		return result;
	}

	protected ModelAndView createModelAndView(final Sponsorship sponsorship) {
		ModelAndView result;
		result = this.createModelAndView(sponsorship, null);

		return result;
	}

	protected ModelAndView createModelAndView(final Sponsorship sponsorship, final String messageCode) {
		ModelAndView result;

		final Sponsor sponsor = this.sponsorService.findByPrincipal();
		final Collection<Conference> conferences = this.conferenceService.findAllForthCommingNotRegistered(sponsor.getId());
		final List<Customisation> cuss = (List<Customisation>) this.customisationService.findAll();
		final Customisation cus = cuss.get(0);
		final Collection<String> makes = cus.getCreditCardMakes();

		result = new ModelAndView("sponsorship/sponsor/create");
		result.addObject("sponsorship", sponsorship);
		result.addObject("conferences", conferences);
		result.addObject("makes", makes);

		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsorship sponsorship, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(sponsorship);
		else
			try {

				final int month = Calendar.getInstance().getTime().getMonth();
				if (sponsorship.getExpirationYear() == 2019)
					Assert.isTrue(sponsorship.getExpirationMonth() >= month, "registration month");

				this.sponsorshipService.save(sponsorship);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage() == "registration month")
					result = this.createModelAndView(sponsorship, "sponsorship.month");
				else
					result = this.createModelAndView(sponsorship, "sponsorship.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int sponsorshipId) {

		ModelAndView result;

		final Sponsorship sponsorship = this.sponsorshipService.findOne(sponsorshipId);

		try {
			//Se comprueba que es suyo
			final Sponsor sponsor = this.sponsorService.findByPrincipal();
			final Collection<Sponsorship> sponsorships = this.sponsorshipService.findBySponsor(sponsor.getId());
			Assert.isTrue(sponsorships.contains(sponsorship));

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("sponsorship/sponsor/show");

		result.addObject("sponsorship", sponsorship);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Sponsorship spo, final BindingResult binding) {
		ModelAndView res;

		try {
			//Se comprueba que es suyo
			final Sponsor sponsor = this.sponsorService.findByPrincipal();
			final Collection<Sponsorship> sponsorships = this.sponsorshipService.findBySponsor(sponsor.getId());
			Assert.isTrue(sponsorships.contains(spo));

			this.sponsorshipService.delete(spo);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createModelAndView(spo, "error.section");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sponsorshipId) {
		ModelAndView result;

		final Sponsorship sponsorship = this.sponsorshipService.findOne(sponsorshipId);

		//Se comprueba que es suyo
		final Sponsor sponsor = this.sponsorService.findByPrincipal();
		final Collection<Sponsorship> sponsorships = this.sponsorshipService.findBySponsor(sponsor.getId());
		Assert.isTrue(sponsorships.contains(sponsorship));

		result = this.createModelAndView(sponsorship);

		return result;
	}
}
