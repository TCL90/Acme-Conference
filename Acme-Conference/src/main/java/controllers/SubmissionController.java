
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SubmissionService;
import domain.Submission;

@Controller
@RequestMapping("/submission")
public class SubmissionController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int submissionId) {

		ModelAndView result;

		final Submission submission = this.submissionService.findOne(submissionId);

		try {

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/show");

		result.addObject("submission", submission);
		return result;

	}

}
