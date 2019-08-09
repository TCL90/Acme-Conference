
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
import repositories.SubmissionRepository;
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

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private ConferenceRepository	conferenceRepository;


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
		int subEvaluated = 0;
		int subAccepted = 0;
		int subRejected = 0;

		try {
			Assert.notNull(this.administratorService.findByPrincipal());
<<<<<<< HEAD
			submissions = this.submissionService.decisionProcedure(conferenceId);
=======
			final Collection<Submission> submissionsEvaluated = this.submissionRepository.findUnderReviewReported(conferenceId);
			subEvaluated = submissionsEvaluated.size();

			submissions = this.submissionService.decisionProcedure(conferenceId);
			for (final Submission s : submissions)
				if (s.getStatus().contains("ACCEPTED"))
					subAccepted++;
				else
					subRejected++;
>>>>>>> CU-ManageConferences
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/administrator/list");
		result.addObject("requestURI", "/conference/administrator/list.do");
		result.addObject("conferences", this.conferenceRepository.findAll());
		result.addObject("submissions", submissions);
		result.addObject("subEvaluated", subEvaluated);
		result.addObject("subAccepted", subAccepted);
		result.addObject("subRejected", subRejected);

		return result;
	}
}
