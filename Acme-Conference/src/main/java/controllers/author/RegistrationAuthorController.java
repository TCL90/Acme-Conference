
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.RegistrationService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
import domain.Registration;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController extends AbstractController {

	@Autowired
	private RegistrationService	registrationService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Registration> registrations = null;

		try {
			final Author author = this.authorService.findByPrincipal();
			registrations = this.registrationService.findByAuthor(author.getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("registration/author/list");
		result.addObject("registrations", registrations);
		result.addObject("requestURI", "/registration/author/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final Registration registration = this.registrationService.create();
		result = this.createModelAndView(registration);
		return result;
	}

	protected ModelAndView createModelAndView(final Registration registration) {
		ModelAndView result;
		result = this.createModelAndView(registration, null);

		return result;
	}

	protected ModelAndView createModelAndView(final Registration registration, final String messageCode) {
		ModelAndView result;

		final Author author = this.authorService.findByPrincipal();
		final Collection<Conference> conferences = this.conferenceService.findAllForthComming();

		result = new ModelAndView("registration/author/create");
		result.addObject("registration", registration);
		result.addObject("conferences", conferences);

		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Registration registration, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(registration);
		else
			try {
				//				final Collection<Registration> registrations = this.registrationService.findByAuthorAndConference(registration.getAuthor().getId(), registration.getConference().getId());
				//				Assert.isTrue(this.registrationService.findByAuthorAndConference(registration.getAuthor().getId(), registration.getConference().getId()) != null, "registration sent");

				this.registrationService.save(registration);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage() == "registration sent")
					result = this.createModelAndView(registration, "registration.already.sent");
				else
					result = this.createModelAndView(registration, "registration.commit.error");
			}

		return result;
	}
}
