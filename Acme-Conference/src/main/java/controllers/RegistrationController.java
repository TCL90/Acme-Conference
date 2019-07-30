
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RegistrationService;
import domain.Registration;

@Controller
@RequestMapping("/registration")
public class RegistrationController extends AbstractController {

	@Autowired
	private RegistrationService	registrationService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int registrationId) {

		ModelAndView result;

		final Registration registration = this.registrationService.findOne(registrationId);

		result = new ModelAndView("registration/show");

		result.addObject("registration", registration);
		return result;

	}

}
