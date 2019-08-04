
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.ConferenceRepository;
import services.AdministratorService;
import services.RegistrationService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Conference;
import domain.Registration;

@Controller
@RequestMapping("/registration/administrator")
public class RegistrationAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private RegistrationService		registrationService;

	@Autowired
	private ConferenceRepository	conferenceRepository;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {
		ModelAndView result;

		Collection<Registration> registrations = null;

		try {
			//Se comprueba que el admin loggeado es el propietario de la conference
			final Administrator admin = this.administratorService.findByPrincipal();
			final Conference conf = this.conferenceRepository.findOne(conferenceId);
			Assert.isTrue(conf.getAdministrator().getId() == admin.getId());

			registrations = this.registrationService.findByConference(conferenceId);
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("registration/author/list");
		result.addObject("registrations", registrations);
		result.addObject("requestURI", "/registration/author/list.do");

		return result;
	}
}
