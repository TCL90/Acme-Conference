
package controllers.administrator;

import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
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
import domain.Conference;
import domain.Reviewer;
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

		final Conference conf = this.conferenceRepository.findOne(conferenceId);

		Collection<Submission> submissions = null;
		int subEvaluated = 0;
		int subAccepted = 0;
		int subRejected = 0;

		try {
			final DateTime fechaAhora = DateTime.now();
			final Date fechaAhoraDate = fechaAhora.toDate();

			//Se comprueba que la fecha de submission es anterior a hoy
			Assert.isTrue(conf.getSubmissionDeadline().before(fechaAhoraDate));

			Assert.notNull(this.administratorService.findByPrincipal());
			final Collection<Submission> submissionsEvaluated = this.submissionRepository.findUnderReviewReported(conferenceId);
			subEvaluated = submissionsEvaluated.size();

			submissions = this.submissionService.decisionProcedure(conferenceId);
			for (final Submission s : submissions)
				if (s.getStatus().contains("ACCEPTED"))
					subAccepted++;
				else
					subRejected++;
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		final DateTime fechaAhora = DateTime.now();
		final Date fechaAhoraDate = fechaAhora.toDate();

		result = new ModelAndView("conference/administrator/list");
		result.addObject("fechaAhoraDate", fechaAhoraDate);
		result.addObject("requestURI", "/conference/administrator/list.do");
		result.addObject("conferences", this.conferenceRepository.findAll());
		result.addObject("submissions", submissions);
		result.addObject("subEvaluated", subEvaluated);
		result.addObject("subAccepted", subAccepted);
		result.addObject("subRejected", subRejected);

		result.addObject("showAllLinks", true);

		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.GET)
	public ModelAndView assignReviewers(@RequestParam final int submissionId) {
		ModelAndView result;
		Collection<Reviewer> reviewers = null;

		final Submission submission = this.submissionService.findOne(submissionId);

		try {
			Assert.notNull(this.administratorService.findByPrincipal());

			reviewers = this.submissionService.assignReviewers(submission);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/administrator/list");
		result.addObject("requestURI", "/submission/administrator/list.do");
		result.addObject("submissions", this.submissionService.findAll());
		result.addObject("reviewers", reviewers);
		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Submission> submissions = null;

		try {
			submissions = this.submissionService.findAll();
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/administrator/list");
		result.addObject("submissions", submissions);
		result.addObject("requestURI", "/submission/administrator/list.do");

		return result;
	}
}
