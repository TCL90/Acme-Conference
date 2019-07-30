
package controllers.author;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Author;
import domain.Submission;

@Controller
@RequestMapping("/submission")
public class SubmissionAuthorController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private AuthorService		authorService;


	@RequestMapping(value = "/author/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Submission> submissions = null;

		try {
			final Author author = this.authorService.findByPrincipal();
			submissions = this.submissionService.findByAuthor(author.getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("submission/author/list");
		result.addObject("submissions", submissions);
		result.addObject("requestURI", "/submission/author/list.do");

		return result;
	}
}
