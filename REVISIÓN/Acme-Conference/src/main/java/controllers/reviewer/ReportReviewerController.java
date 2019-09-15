
package controllers.reviewer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.ReportService;
import services.ReviewerService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Comment;
import domain.Report;
import domain.Reviewer;
import domain.Submission;

@Controller
@RequestMapping("/report/reviewer")
public class ReportReviewerController extends AbstractController {

	@Autowired
	private ReportService		reportService;

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private ReviewerService		reviewerService;

	@Autowired
	private CommentService		commentService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int submissionId) {

		ModelAndView result;
		final Report report = new Report();
		final Submission sub = this.submissionService.findOne(submissionId);
		report.setSubmission(sub);
		final Reviewer reviewer = this.reviewerService.findByPrincipal();
		report.setReviewer(reviewer);
		result = this.createModelAndView(report);
		return result;
	}

	protected ModelAndView createModelAndView(final Report report) {
		ModelAndView result;
		result = this.createModelAndView(report, null);

		return result;
	}

	protected ModelAndView createModelAndView(final Report report, final String messageCode) {
		ModelAndView result;

		final Reviewer reviewer = this.reviewerService.findByPrincipal();

		result = new ModelAndView("report/reviewer/create");
		result.addObject("report", report);
		result.addObject("reviewer", reviewer);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Report report, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView2(report);
		else
			try {

				this.reportService.save(report);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView2(report, "report.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Report> reports = null;

		try {
			final Reviewer reviewer = this.reviewerService.findByPrincipal();
			reports = this.reportService.findByReviewer(reviewer.getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("report/reviewer/list");
		result.addObject("reports", reports);
		result.addObject("requestURI", "/report/reviewer/list.do");

		return result;
	}

	@RequestMapping(value = "/create2", method = RequestMethod.GET)
	public ModelAndView create2() {

		ModelAndView result;
		final Report report = new Report();
		final Reviewer reviewer = this.reviewerService.findByPrincipal();
		report.setReviewer(reviewer);
		result = this.createModelAndView2(report);
		return result;
	}

	protected ModelAndView createModelAndView2(final Report report) {
		ModelAndView result;
		result = this.createModelAndView2(report, null);

		return result;
	}

	protected ModelAndView createModelAndView2(final Report report, final String messageCode) {
		ModelAndView result;

		final Reviewer reviewer = this.reviewerService.findByPrincipal();

		final Collection<Submission> submissions = this.submissionService.findByReviewerUnderReview(reviewer);

		final Collection<Report> reports = this.reportService.findByReviewer(reviewer.getId());

		for (final Report r : reports)
			submissions.remove(r.getSubmission());

		result = new ModelAndView("report/reviewer/create");
		result.addObject("report", report);
		result.addObject("reviewer", reviewer);
		result.addObject("message", messageCode);
		result.addObject("submissions", submissions);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int reportId) {
		final ModelAndView result;

		Report r = null;
		final Collection<Comment> comments;

		try {
			r = this.reportService.findOne(reportId);
			comments = this.commentService.findByReport(r);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}
		result = new ModelAndView("report/show");
		result.addObject("report", r);
		result.addObject("requestURI", "/report/author/show.do?reportId=" + r.getId());
		result.addObject("comments", comments);

		return result;
	}

}
