
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RegistrationService;
import domain.Actor;
import domain.Registration;

@Controller
@RequestMapping("/registration")
public class RegistrationController extends AbstractController {

	@Autowired
	private RegistrationService	registrationService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int registrationId) {

		ModelAndView result;

		final Registration registration = this.registrationService.findOne(registrationId);

		//Se comprueba que el usuario es el author o el admin propietario de la conference
		try {
			final Collection<? extends GrantedAuthority> aus = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

			final Actor act = this.actorService.findByPrincipal();

			Assert.isTrue(act.getId() == registration.getAuthor().getId() || act.getId() == registration.getConference().getAdministrator().getId());

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("registration/show");

		result.addObject("registration", registration);
		return result;

	}
}
