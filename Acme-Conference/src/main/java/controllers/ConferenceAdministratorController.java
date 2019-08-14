
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.AdministratorService;
import services.CommentService;
import services.ConferenceService;
import services.SponsorshipService;
import domain.Activity;
import domain.Comment;
import domain.Conference;
import domain.Sponsorship;

@Controller
@RequestMapping("/conference/administrator")
public class ConferenceAdministratorController extends AbstractController {

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private ActivityService			activityService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private CommentService			commentService;

	@Autowired
	private SponsorshipService		sponsorshipService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllByAdmin();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/administrator/list");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/administrator/list.do");
		result.addObject("allA", true);

		return result;
	}

	@RequestMapping(value = "/pastList", method = RequestMethod.GET)
	public ModelAndView pastList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllPastAdministrator();

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

			conferences = this.conferenceService.findAllForthCommingAdministrator();

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

			conferences = this.conferenceService.findAllRunningAdministrator();

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

	@RequestMapping(value = "/sdElapsedList", method = RequestMethod.GET)
	public ModelAndView sdElapsedList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllByAdminSDElapsed();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/administrator/sdElapsedList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/sdElapsedList.do");
		result.addObject("sdElapsed", true);

		return result;
	}

	@RequestMapping(value = "/nDElapsesSoonList", method = RequestMethod.GET)
	public ModelAndView nDElapsesSoonList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllByAdminNDElapses();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/administrator/nDElapsesSoonList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/administrator/nDElapsesSoonList.do");
		result.addObject("nDElapses", true);

		return result;
	}

	@RequestMapping(value = "/cRElapsesSoonList", method = RequestMethod.GET)
	public ModelAndView cRElapsesSoonList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllByAdminCRDElapses();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/administrator/cRElapsesSoonList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/administrator/cRElapsesSoonList.do");
		result.addObject("cRElapses", true);

		return result;
	}

	@RequestMapping(value = "/organisedSoonList", method = RequestMethod.GET)
	public ModelAndView organisedSoon() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllByAdminOrganisedSoon();

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/administrator/organisedSoonList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/organisedSoonList.do");
		result.addObject("organisedS", true);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int conferenceId) {
		final ModelAndView result;
		Conference conference;
		int numberOfRegistrations;
		Collection<Activity> activities;
		final Collection<Comment> comments;
		final Collection<Sponsorship> sponsorships;

		try {

			conference = this.conferenceService.findOne(conferenceId);
			numberOfRegistrations = this.conferenceService.numberOfRegistrations(conference);
			activities = this.activityService.findAllByConference(conference);
			comments = this.commentService.findByConference(conference);
			sponsorships = this.sponsorshipService.findAllByConference(conference.getId());

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/show");
		result.addObject("conference", conference);
		result.addObject("numberOfR", numberOfRegistrations);
		result.addObject("activities", activities);
		result.addObject("comments", comments);
		result.addObject("sponsorships", sponsorships);
		result.addObject("requestURI", "/conference/administrator/show.do?conferenceId=" + conference.getId());

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam final String q) {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			conferences = this.conferenceService.findAllKeywordAdmin(q);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("conference/searchList");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "/conference/search.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Conference conference = this.conferenceService.create();

		result = this.createEditModelAndView(conference);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int conferenceId) {
		ModelAndView result;
		final Conference conference = this.conferenceService.findOne(conferenceId);

		if (conference.isFinalMode())
			return new ModelAndView("redirect:/welcome/index.do");

		result = this.createEditModelAndView(conference);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Conference conference, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(conference);
		else
			try {
				final Conference c = this.conferenceService.save(conference);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage() == "submissionBeforeNotification")
					result = this.createEditModelAndView(conference, "conference.submissionBeforeNotification");
				else if (oops.getMessage() == "notificationBeforeCamera")
					result = this.createEditModelAndView(conference, "conference.notificationBeforeCamera");
				else if (oops.getMessage() == "cameraBeforeStart")
					result = this.createEditModelAndView(conference, "conference.cameraBeforeStart");
				else if (oops.getMessage() == "startBeforeEnd")
					result = this.createEditModelAndView(conference, "conference.startBeforeEnd");
				else
					result = this.createEditModelAndView(conference, "conference.commit.error");
			}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Conference conference) {

		ModelAndView result;
		result = this.createEditModelAndView(conference, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Conference conference, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("conference/administrator/edit");
		result.addObject("conference", conference);

		result.addObject("message", messageCode);

		return result;
	}

}
