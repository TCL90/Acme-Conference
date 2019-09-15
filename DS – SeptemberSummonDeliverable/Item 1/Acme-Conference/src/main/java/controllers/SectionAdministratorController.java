
package controllers;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SectionService;
import domain.Section;

@Controller
@RequestMapping("/section/administrator")
public class SectionAdministratorController extends AbstractController {

	@Autowired
	private SectionService	sectionService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tutorialId) {
		ModelAndView result;

		Section section = null;

		try {

			section = this.sectionService.create(tutorialId);
			result = this.createEditModelAndView(section);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		return result;
	}

	private ModelAndView createEditModelAndView(final Section section) {
		ModelAndView res;
		res = this.createEditModelAndView(section, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Section s, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("section/edit");

		res.addObject("section", s);
		res.addObject("message", messageCode);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sectionId) {
		ModelAndView res = new ModelAndView();
		Section s;

		try {
			s = this.sectionService.findOne(sectionId);
			res = this.createEditModelAndView(s);
		} catch (final Throwable oops) {
			res = new ModelAndView("welcome/index");
			return res;
		}
		Assert.notNull(res);

		return res;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Section s, final BindingResult binding) {
		ModelAndView res;

		try {
			final Collection<String> pictures = s.getPictures();
			for (final String trozo : pictures) {
				Assert.isTrue(trozo.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"), "errorAttachments");
				Assert.isTrue(!trozo.contains(";"), "errorAttachments2");
			}

			s = this.sectionService.reconstruct(s, binding);
			Assert.isTrue(s.getTutorial().getConference().isFinalMode(), "tutorialFinalMode");
			this.sectionService.save(s);

			res = new ModelAndView("redirect:../../activity/administrator/show.do?activityId=" + s.getTutorial().getId());
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(s);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "tutorialFinalMode")
				res = this.createEditModelAndView(s, "tutorial.finalMode.error");
			else if (oops.getMessage() == "errorAttachments")
				res = this.createEditModelAndView(s, "conference.attachments.bad");
			else if (oops.getMessage() == "errorAttachments2")
				res = this.createEditModelAndView(s, "conference.attachments.bad");
			else
				res = this.createEditModelAndView(s, "error.section");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Section s, final BindingResult binding) {
		ModelAndView res;

		try {
			final Section s1 = this.sectionService.findOne(s.getId());
			Assert.isTrue(s1.getTutorial().getConference().isFinalMode(), "conferenceFinalMode");
			this.sectionService.delete(s1);
			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + s1.getTutorial().getConference().getId());
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(s, "conference.finalMode.error");
			else
				res = this.createEditModelAndView(s, "error.section");
		}

		return res;
	}
}
