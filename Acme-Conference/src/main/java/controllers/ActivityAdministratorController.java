
package controllers;

import java.util.Calendar;
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

import services.ActivityService;
import services.CommentService;
import services.PanelService;
import services.PresentationService;
import services.TutorialService;
import domain.Activity;
import domain.Comment;
import domain.Panel;
import domain.Presentation;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/activity/administrator")
public class ActivityAdministratorController extends AbstractController {

	@Autowired
	private ActivityService		activityService;

	@Autowired
	private CommentService		commentService;

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private PanelService		panelService;

	@Autowired
	private PresentationService	presentationService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Activity> activities = null;

		try {

			activities = this.activityService.findAll();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("requestURI", "/activity/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int activityId) {
		final ModelAndView result;
		String activityType;

		Presentation presentation = null;
		Tutorial tutorial = null;
		Panel panel = null;
		final Collection<Comment> comments;

		Collection<Section> sections = null;

		try {

			activityType = this.activityService.selectType(activityId);

			if (activityType == "presentation") {
				presentation = this.presentationService.findPresentationByActivityId(activityId);
				comments = this.commentService.findByPresentation(presentation);
			} else if (activityType == "tutorial") {
				tutorial = this.tutorialService.findTutorialByActivityId(activityId);
				sections = this.tutorialService.findSectionsByTutorial(tutorial);
				comments = this.commentService.findByTutorial(tutorial);
			} else {
				panel = this.panelService.findPanelByActivityId(activityId);
				comments = this.commentService.findByPanel(panel);
			}

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		if (activityType == "presentation") {
			result = new ModelAndView("activity/presentationShow");
			result.addObject("presentation", presentation);
			result.addObject("requestURI", "/activity/show.do?activityId=" + presentation.getId());
		} else if (activityType == "tutorial") {
			result = new ModelAndView("activity/tutorialShow");
			result.addObject("tutorial", tutorial);
			result.addObject("requestURI", "/activity/show.do?activityId=" + tutorial.getId());
			result.addObject("sections", sections);
		} else {
			result = new ModelAndView("activity/panelShow");
			result.addObject("requestURI", "/activity/show.do?activityId=" + panel.getId());
			result.addObject("activity", panel);
		}
		result.addObject("type", activityType);
		result.addObject("comments", comments);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId, @RequestParam final String type) {
		ModelAndView result;

		Tutorial tutorial = null;
		Panel panel = null;

		try {

			if (type.contains("tutorial")) {
				tutorial = this.tutorialService.createTutorialByConferenceId(conferenceId);
				result = this.createEditModelAndView(tutorial);
			} else {
				panel = this.panelService.createPanelByConferenceId(conferenceId);
				result = this.createEditModelAndView(panel);
			}

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		return result;
	}

	@RequestMapping(value = "/createP", method = RequestMethod.GET)
	public ModelAndView createP(@RequestParam final int conferenceId, @RequestParam final String type, @RequestParam final int cameraId) {
		ModelAndView result;

		Presentation presentation = null;

		try {

			presentation = this.presentationService.createPresentationByConferenceId(conferenceId, cameraId);
			result = this.createEditModelAndView(presentation);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		return result;
	}

	private ModelAndView createEditModelAndView(final Presentation presentation) {
		ModelAndView res;
		res = this.createEditModelAndView(presentation, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Panel panel) {
		ModelAndView res;
		res = this.createEditModelAndView(panel, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Tutorial tutorial) {
		ModelAndView res;
		res = this.createEditModelAndView(tutorial, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Presentation p, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("activity/editPresentation");

		res.addObject("presentation", p);
		res.addObject("type", "presentation");
		res.addObject("message", messageCode);

		return res;
	}

	private ModelAndView createEditModelAndView(final Tutorial t, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("activity/editTutorial");

		res.addObject("tutorial", t);
		res.addObject("type", "tutorial");
		res.addObject("message", messageCode);

		return res;
	}

	private ModelAndView createEditModelAndView(final Panel p, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("activity/editPanel");

		res.addObject("panel", p);
		res.addObject("type", "panel");
		res.addObject("message", messageCode);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int activityId) {
		ModelAndView res = new ModelAndView();
		Presentation p;
		Panel p2;
		Tutorial t;

		try {

			final String type = this.activityService.selectType(activityId);

			if (type == "presentation") {
				p = this.presentationService.findOne(activityId);
				res = this.createEditModelAndView(p);
			} else if (type == "panel") {
				p2 = this.panelService.findOne(activityId);
				res = this.createEditModelAndView(p2);
			} else if (type == "tutorial") {
				t = this.tutorialService.findOne(activityId);
				res = this.createEditModelAndView(t);
			}
		} catch (final Throwable oops) {
			res = new ModelAndView("welcome/index");
			return res;
		}
		Assert.notNull(res);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "savePresentation")
	public ModelAndView save(Presentation p, final BindingResult binding) {
		ModelAndView res;

		try {
			Assert.isTrue(p.getDuration() != 0, "durationEqualZero");
			final Collection<String> attachments = p.getAttachments();
			for (final String trozo : attachments) {
				Assert.isTrue(trozo.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"), "errorAttachments");
				Assert.isTrue(!trozo.contains(";"), "errorAttachments2");
			}

			p = this.presentationService.reconstruct(p, binding);
			Assert.isTrue(p.getConference().isFinalMode(), "conferenceFinalMode");
			Assert.isTrue(p.getStartMoment().equals(p.getConference().getStartDate()) || p.getStartMoment().after(p.getConference().getStartDate()), "activityStartBeforeConference");
			Assert.isTrue(p.getConference().getStartDate().after(Calendar.getInstance().getTime()), "startMomentBeforeToday");
			Assert.isTrue(p.getStartMoment().before(p.getConference().getEndDate()), "activityStartAfterConferenceEnd");
			Assert.isTrue(p.getSchedule().before(p.getConference().getEndDate()) && !p.getSchedule().equals(p.getConference().getEndDate()), "durationNoValid");
			Assert.isTrue(!p.getSchedule().after(p.getConference().getEndDate()) && !p.getSchedule().equals(p.getConference().getEndDate()), "durationNoValid");
			this.presentationService.save(p);

			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + p.getConference().getId());
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(p);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(p, "conference.finalMode.error");
			else if (oops.getMessage() == "activityStartBeforeConference")
				res = this.createEditModelAndView(p, "conference.activity.before.conference");
			else if (oops.getMessage() == "startMomentBeforeToday")
				res = this.createEditModelAndView(p, "conferenceHasStarted");
			else if (oops.getMessage() == "activityStartAfterConferenceEnd")
				res = this.createEditModelAndView(p, "conference.activity.after.conference");
			else if (oops.getMessage() == "durationEqualZero")
				res = this.createEditModelAndView(p, "conference.duration.equal.zero");
			else if (oops.getMessage() == "errorAttachments")
				res = this.createEditModelAndView(p, "conference.attachments.bad");
			else if (oops.getMessage() == "errorAttachments2")
				res = this.createEditModelAndView(p, "conference.attachments.bad");
			else if (oops.getMessage() == "durationNoValid")
				res = this.createEditModelAndView(p, "conference.duration.noValid");
			else
				res = this.createEditModelAndView(p, "error.presentation");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "savePanel")
	public ModelAndView save(Panel p, final BindingResult binding) {
		ModelAndView res;

		try {
			Assert.isTrue(p.getDuration() != 0, "durationEqualZero");
			final Collection<String> attachments = p.getAttachments();
			for (final String trozo : attachments) {
				Assert.isTrue(trozo.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"), "errorAttachments");
				Assert.isTrue(!trozo.contains(";"), "errorAttachments2");
			}

			p = this.panelService.reconstruct(p, binding);
			Assert.isTrue(p.getConference().isFinalMode(), "conferenceFinalMode");
			Assert.isTrue(p.getStartMoment().equals(p.getConference().getStartDate()) || p.getStartMoment().after(p.getConference().getStartDate()), "activityStartBeforeConference");
			Assert.isTrue(p.getConference().getStartDate().after(Calendar.getInstance().getTime()), "startMomentBeforeToday");
			Assert.isTrue(p.getStartMoment().before(p.getConference().getEndDate()), "activityStartAfterConferenceEnd");
			Assert.isTrue(p.getSchedule().before(p.getConference().getEndDate()) && !p.getSchedule().equals(p.getConference().getEndDate()), "durationNoValid");
			Assert.isTrue(!p.getSchedule().after(p.getConference().getEndDate()) && !p.getSchedule().equals(p.getConference().getEndDate()), "durationNoValid");
			this.panelService.save(p);

			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + p.getConference().getId());
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(p);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(p, "conference.finalMode.error");
			else if (oops.getMessage() == "activityStartBeforeConference")
				res = this.createEditModelAndView(p, "conference.activity.before.conference");
			else if (oops.getMessage() == "startMomentBeforeToday")
				res = this.createEditModelAndView(p, "conferenceHasStarted");
			else if (oops.getMessage() == "activityStartAfterConferenceEnd")
				res = this.createEditModelAndView(p, "conference.activity.after.conference");
			else if (oops.getMessage() == "durationEqualZero")
				res = this.createEditModelAndView(p, "conference.duration.equal.zero");
			else if (oops.getMessage() == "errorAttachments")
				res = this.createEditModelAndView(p, "conference.attachments.bad");
			else if (oops.getMessage() == "errorAttachments2")
				res = this.createEditModelAndView(p, "conference.attachments.bad");
			else if (oops.getMessage() == "durationNoValid")
				res = this.createEditModelAndView(p, "conference.duration.noValid");
			else
				res = this.createEditModelAndView(p, "error.presentation");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveTutorial")
	public ModelAndView save(Tutorial t, final BindingResult binding) {
		ModelAndView res;

		try {
			Assert.isTrue(t.getDuration() != 0, "durationEqualZero");
			final Collection<String> attachments = t.getAttachments();
			for (final String trozo : attachments) {
				Assert.isTrue(trozo.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"), "errorAttachments");
				Assert.isTrue(!trozo.contains(";"), "errorAttachments2");
			}

			t = this.tutorialService.reconstruct(t, binding);
			Assert.isTrue(t.getConference().isFinalMode(), "conferenceFinalMode");

			Assert.isTrue(t.getStartMoment().equals(t.getConference().getStartDate()) || t.getStartMoment().after(t.getConference().getStartDate()), "activityStartBeforeConference");
			Assert.isTrue(t.getStartMoment().before(t.getConference().getEndDate()), "activityStartAfterConferenceEnd");
			Assert.isTrue(t.getConference().getStartDate().after(Calendar.getInstance().getTime()), "startMomentBeforeToday");
			Assert.isTrue(t.getSchedule().before(t.getConference().getEndDate()) && !t.getSchedule().equals(t.getConference().getEndDate()), "durationNoValid");
			Assert.isTrue(!t.getSchedule().after(t.getConference().getEndDate()) && !t.getSchedule().equals(t.getConference().getEndDate()), "durationNoValid");
			this.tutorialService.save(t);

			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + t.getConference().getId());
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(t);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(t, "conference.finalMode.error");
			else if (oops.getMessage() == "activityStartBeforeConference")
				res = this.createEditModelAndView(t, "conference.activity.before.conference");
			else if (oops.getMessage() == "startMomentBeforeToday")
				res = this.createEditModelAndView(t, "conferenceHasStarted");
			else if (oops.getMessage() == "activityStartAfterConferenceEnd")
				res = this.createEditModelAndView(t, "conference.activity.after.conference");
			else if (oops.getMessage() == "durationEqualZero")
				res = this.createEditModelAndView(t, "conference.duration.equal.zero");
			else if (oops.getMessage() == "errorAttachments")
				res = this.createEditModelAndView(t, "conference.attachments.bad");
			else if (oops.getMessage() == "errorAttachments2")
				res = this.createEditModelAndView(t, "conference.attachments.bad");
			else if (oops.getMessage() == "durationNoValid")
				res = this.createEditModelAndView(t, "conference.duration.noValid");
			else if (oops.getMessage() == "startMomentAfterToday")
				res = this.createEditModelAndView(t, "conference.startMoment.noValid");
			else
				res = this.createEditModelAndView(t, "error.presentation");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deletePresentation")
	public ModelAndView delete(final Presentation p, final BindingResult binding) {
		ModelAndView res;

		try {
			final Presentation p1 = this.presentationService.findOne(p.getId());
			Assert.isTrue(p1.getConference().isFinalMode(), "conferenceFinalMode");
			this.presentationService.delete(p1);
			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + p1.getConference().getId());
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(p, "conference.finalMode.error");
			else
				res = this.createEditModelAndView(p, "error.presentation");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deletePanel")
	public ModelAndView delete(final Panel p, final BindingResult binding) {
		ModelAndView res;

		try {
			final Panel p1 = this.panelService.findOne(p.getId());
			Assert.isTrue(p1.getConference().isFinalMode(), "conferenceFinalMode");
			this.panelService.delete(p1);
			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + p1.getConference().getId());
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(p, "conference.finalMode.error");
			else
				res = this.createEditModelAndView(p, "error.presentation");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deleteTutorial")
	public ModelAndView delete(final Tutorial t, final BindingResult binding) {
		ModelAndView res;

		try {
			final Tutorial t1 = this.tutorialService.findOne(t.getId());
			Assert.isTrue(t1.getConference().isFinalMode(), "conferenceFinalMode");
			this.tutorialService.delete(t1);
			res = new ModelAndView("redirect:../../conference/administrator/show.do?conferenceId=" + t1.getConference().getId());
		} catch (final Throwable oops) {
			if (oops.getMessage() == "conferenceFinalMode")
				res = this.createEditModelAndView(t, "conference.finalMode.error");
			else
				res = this.createEditModelAndView(t, "error.presentation");
		}

		return res;
	}
}
