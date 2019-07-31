
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

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


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int activityId) {
		final ModelAndView result;
		String activityType;
		Activity activity;
		//		final Panel panel;
		Presentation presentation = null;
		Tutorial tutorial = null;
		Panel panel = null;
		final Collection<Comment> comments;

		Collection<Section> sections = null;

		try {

			activity = this.activityService.findOne(activityId);
			activityType = this.activityService.selectType(activity);

			if (activityType == "presentation")
				presentation = this.presentationService.findPresentationByActivityId(activityId);
			else if (activityType == "tutorial") {
				tutorial = this.tutorialService.findTutorialByActivityId(activityId);
				sections = this.tutorialService.findSectionsByTutorial(tutorial);
			} else
				panel = this.panelService.findPanelByActivityId(activityId);

			comments = this.commentService.findByActivity(activity);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		if (activityType == "presentation") {
			result = new ModelAndView("activity/presentationShow");
			result.addObject("presentation", presentation);
		} else if (activityType == "tutorial") {
			result = new ModelAndView("activity/tutorialShow");
			result.addObject("tutorial", tutorial);
			result.addObject("sections", sections);
		} else {
			result = new ModelAndView("activity/panelShow");
			result.addObject("activity", activity);
		}
		result.addObject("type", activityType);
		result.addObject("comments", comments);
		result.addObject("requestURI", "/activity/show.do?activityId=" + activity.getId());

		return result;
	}
}
