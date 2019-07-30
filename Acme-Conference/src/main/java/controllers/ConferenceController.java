
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
import services.ConferenceService;
import domain.Activity;
import domain.Comment;
import domain.Conference;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private ActivityService		activityService;

	@Autowired
	private CommentService		commentService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllFinalMode();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/list.do");
		result.addObject("all", true);

		return result;
	}

	@RequestMapping(value = "/pastList", method = RequestMethod.GET)
	public ModelAndView pastList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllPast();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/pastList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/pastList.do");
		result.addObject("past", true);

		return result;
	}

	@RequestMapping(value = "/forthCommingList", method = RequestMethod.GET)
	public ModelAndView forthCommingList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllForthComming();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/forthCommingList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/forthCommingList.do");
		result.addObject("forth", true);

		return result;
	}

	@RequestMapping(value = "/runningList", method = RequestMethod.GET)
	public ModelAndView runningList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllRunning();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/runningList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/runningList.do");
		result.addObject("running", true);

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam final String q) {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllKeyword(q);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/searchList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/search.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int conferenceId) {
		final ModelAndView result;
		Conference conference;
		int numberOfRegistrations;
		Collection<Activity> activities;
		final Collection<Comment> comments;

		try {

			conference = this.conferenceService.findOne(conferenceId);
			numberOfRegistrations = this.conferenceService.numberOfRegistrations(conference);
			activities = this.activityService.findAllByConference(conference);
			comments = this.commentService.findByConference(conference);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/show");
		result.addObject("conference", conference);
		result.addObject("numberOfR", numberOfRegistrations);
		result.addObject("activities", activities);
		result.addObject("comments", comments);
		result.addObject("requestURI", "/conference/show.do?conferenceId=" + conference.getId());

		return result;
	}

}
