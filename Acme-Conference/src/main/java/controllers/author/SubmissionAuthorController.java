
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Author;
import domain.Conference;
import domain.Submission;
import forms.SubmissionForm;

@Controller
@RequestMapping("/submission")
public class SubmissionAuthorController extends AbstractController {

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ConferenceService	conferenceService;


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

	@RequestMapping(value = "/author/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		final SubmissionForm submissionForm = this.submissionService.create();
		result = this.createModelAndView(submissionForm);
		return result;
	}

	protected ModelAndView createModelAndView(final SubmissionForm submissionForm) {
		ModelAndView result;
		result = this.createModelAndView(submissionForm, null);

		return result;
	}

	protected ModelAndView createModelAndView(final SubmissionForm submissionForm, final String messageCode) {
		ModelAndView result;

		final Collection<Author> authors = this.authorService.findAll();
		//		List<String> authors = new ArrayList<>();
		//		for (Author au : authors2)
		//			authors.add(au.getName()+)

		final Collection<Conference> conferences = this.conferenceService.findAllForthCommingNotSubmitted(this.authorService.findByPrincipal().getId());
		result = new ModelAndView("submission/author/create");
		result.addObject("submissionForm", submissionForm);
		result.addObject("authors", authors);
		result.addObject("message", messageCode);
		result.addObject("conferences", conferences);

		return result;
	}

	@RequestMapping(value = "/author/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SubmissionForm submissionForm, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(submissionForm);
		else
			try {
				Assert.notNull(submissionForm.getConference(), "submissionForm conference");

				final Submission sub = this.submissionService.reconstructSubmissionPaper(submissionForm, binding);

				this.submissionService.save(sub);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage() == "submissionForm conference")
					result = this.createModelAndView(submissionForm, "submission.conference.mandatory");
				else
					result = this.createModelAndView(submissionForm, "submission.commit.error");
			}

		return result;
	}
}
