
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Submission;

@Controller
@RequestMapping("/submission/administrator")
public class SubmissionAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SubmissionService		submissionService;


	@RequestMapping(value = "/decision", method = RequestMethod.GET)
	public ModelAndView decision() {
		ModelAndView result;

		try {
			Assert.notNull(this.administratorService.findByPrincipal());
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/administrator/decision");
		result.addObject("requestURI", "/submission/administrator/decision.do");

		return result;
	}

	@RequestMapping(value = "/procedure", method = RequestMethod.GET)
	public ModelAndView procedure(@RequestParam final int conferenceId) {
		ModelAndView result;

		Collection<Submission> submissions = null;

		try {
			Assert.notNull(this.administratorService.findByPrincipal());
			submissions = this.submissionService.decisionProcedure(conferenceId);
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/administrator/decision");
		result.addObject("requestURI", "/submission/administrator/decision.do");
		result.addObject("submissions", submissions);
		return result;
	}

}
