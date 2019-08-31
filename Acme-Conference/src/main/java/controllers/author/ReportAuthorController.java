
package controllers.author;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.ReportService;
import controllers.AbstractController;
import domain.Comment;
import domain.Report;

@Controller
@RequestMapping("/report/author")
public class ReportAuthorController extends AbstractController {

	@Autowired
	ReportService	reportService;

	@Autowired
	CommentService	commentService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int submissionId) {
		ModelAndView result;

		Collection<Report> reports = null;

		try {

			reports = this.reportService.findAllBySubmissionId(submissionId);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("report/list");
		result.addObject("reports", reports);
		result.addObject("requestURI", "/report/author/list.do");

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
