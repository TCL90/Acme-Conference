
package controllers;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.CommentService;
import services.ReviewerService;
import domain.Comment;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService	commentService;

	@Autowired
	private ReviewerService	reviewerService;

	@Autowired
	private AuthorService	authorService;


	@RequestMapping(value = "/createC", method = RequestMethod.GET)
	public ModelAndView createByConferenceId(@RequestParam final int conferenceId) {
		ModelAndView result;

		Comment comment;

		try {
			comment = this.commentService.createCommentByCommentableId(conferenceId, "conference");
			result = this.createEditModelAndView(comment);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		return result;
	}

	@RequestMapping(value = "/createA", method = RequestMethod.GET)
	public ModelAndView createByActivityId(@RequestParam final int activityId) {
		ModelAndView result;

		Comment comment;

		try {
			comment = this.commentService.createCommentByCommentableId(activityId, "activity");
			result = this.createEditModelAndView(comment);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		return result;
	}

	@RequestMapping(value = "/createR", method = RequestMethod.GET)
	public ModelAndView createByReportId(@RequestParam final int reportId) {
		ModelAndView result;

		Comment comment;

		try {
			comment = this.commentService.createCommentByCommentableId(reportId, "report");
			result = this.createEditModelAndView(comment);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		return result;
	}

	private ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView res;
		res = this.createEditModelAndView(comment, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Comment c, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("comment/edit");

		res.addObject("comment", c);
		res.addObject("message", messageCode);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Comment c, final BindingResult binding) {
		ModelAndView res;

		try {
			Assert.isTrue(c.getText() != "", "notBlank");
			Assert.isTrue(c.getTitle() != "", "notBlank");
			c = this.commentService.save(c);
			if (this.authorService.checkAuthor())
				res = new ModelAndView("redirect:../submission/author/list.do");
			else if (this.reviewerService.checkReviewer())
				res = new ModelAndView("redirect:../report/reviewer/list.do");
			else
				res = new ModelAndView("redirect:../conference/list.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(c);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "notBlank")
				res = this.createEditModelAndView(c, "comment.notBlank");
			else
				res = this.createEditModelAndView(c, "error.comment");
		}

		return res;
	}
}
