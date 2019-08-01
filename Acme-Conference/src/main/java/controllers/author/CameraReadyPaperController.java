
package controllers.author;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.CameraReadyPaperService;
import services.ConferenceService;
import services.SubmissionService;
import controllers.AbstractController;
import domain.Author;
import domain.CameraReadyPaper;
import domain.Submission;

@Controller
@RequestMapping("/cameraReadyPaper")
public class CameraReadyPaperController extends AbstractController {

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private CameraReadyPaperService	cameraReadyPaperService;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ConferenceService		conferenceService;


	@RequestMapping(value = "/author/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int submissionId) {

		ModelAndView result;
		final CameraReadyPaper cameraReadyPaper = new CameraReadyPaper();
		final Submission submission = this.submissionService.findOne(submissionId);
		cameraReadyPaper.setSubmission(submission);
		result = this.createModelAndView(cameraReadyPaper);
		return result;
	}

	protected ModelAndView createModelAndView(final CameraReadyPaper cameraReadyPaper) {
		ModelAndView result;
		result = this.createModelAndView(cameraReadyPaper, null);

		return result;
	}

	protected ModelAndView createModelAndView(final CameraReadyPaper cameraReadyPaper, final String messageCode) {
		ModelAndView result;

		final Collection<Author> authors = this.authorService.findAll();
		//		List<String> authors = new ArrayList<>();
		//		for (Author au : authors2)
		//			authors.add(au.getName()+)

		result = new ModelAndView("cameraReadyPaper/author/create");
		result.addObject("cameraReadyPaper", cameraReadyPaper);
		result.addObject("authors", authors);
		result.addObject("message", messageCode);

		return result;
	}

	@RequestMapping(value = "/author/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CameraReadyPaper camera, @Valid final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(camera);
		else
			try {

				this.cameraReadyPaperService.save(camera);

				result = new ModelAndView("redirect:submission/author/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(camera, "submission.commit.error");
			}

		return result;
	}
}
