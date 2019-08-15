
package controllers.reviewer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import services.ReviewerService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Reviewer;
import domain.Submission;

@Controller
@RequestMapping("/submission/reviewer")
public class SubmissionReviewerController extends AbstractController {


	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private ReviewerService		reviewerService;

	@Autowired
	private ReportService		reportService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Submission> submissions = null;

		try {
			final Reviewer reviewer = this.reviewerService.findByPrincipal();
			submissions = this.submissionService.findByReviewer2(reviewer);
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/reviewer/list");
		result.addObject("submissions", submissions);
		result.addObject("requestURI", "/submission/reviewer/list.do");

		return result;
	}

}
