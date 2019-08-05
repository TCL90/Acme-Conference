
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
import domain.Administrator;
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

			final Administrator a = this.administratorService.findByPrincipal();
			conferences = this.conferenceService.findAllByAdmin(a);

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
	@RequestMapping(value = "/sdElapsedList", method = RequestMethod.GET)
	public ModelAndView sdElapsedList() {
		ModelAndView result;

		Collection<Conference> conferences = null;

		try {

			final Administrator a = this.administratorService.findByPrincipal();
			conferences = this.conferenceService.findAllByAdminSDElapsed(a);

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
	//TODO: TOMAS
	//	@RequestMapping(value = "/nDElapsesSoonList", method = RequestMethod.GET)
	//	public ModelAndView nDElapsesSoonList() {
	//		ModelAndView result;
	//
	//		Collection<Conference> conferences = null;
	//
	//		try {
	//
	//			final Administrator a = this.administratorService.findByPrincipal();
	//			conferences = this.conferenceService.findAllByAdminNDElapses(a);
	//
	//		} catch (final Throwable oops) {
	//			result = new ModelAndView("welcome/index");
	//			return result;
	//		}
	//
	//		result = new ModelAndView("conference/administrator/nDElapsesSoonList");
	//		result.addObject("conferences", conferences);
	//		result.addObject("requestURI", "/conference/administrator/nDElapsesSoonList.do");
	//		result.addObject("nDElapses", true);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/cRElapsesSoonList", method = RequestMethod.GET)
	//	public ModelAndView runningList() {
	//		ModelAndView result;
	//
	//		Collection<Conference> conferences = null;
	//
	//		try {
	//
	//			final Administrator a = this.administratorService.findByPrincipal();
	//			conferences = this.conferenceService.findAllByAdminCRDElapses(a);
	//
	//		} catch (final Throwable oops) {
	//			result = new ModelAndView("welcome/index");
	//			return result;
	//		}
	//
	//		result = new ModelAndView("conference/administrator/cRElapsesSoonList");
	//		result.addObject("conferences", conferences);
	//		result.addObject("requestURI", "/conference/administrator/cRElapsesSoonList.do");
	//		result.addObject("cRElapses", true);
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/organisedSoonList", method = RequestMethod.GET)
	//	public ModelAndView organisedSoon() {
	//		ModelAndView result;
	//
	//		Collection<Conference> conferences = null;
	//
	//		try {
	//
	//			final Administrator a = this.administratorService.findByPrincipal();
	//			conferences = this.conferenceService.findAllByAdminOrganisedSoon(a);
	//
	//		} catch (final Throwable oops) {
	//			result = new ModelAndView("welcome/index");
	//			return result;
	//		}
	//
	//		result = new ModelAndView("conference/administrator/organisedSoonList");
	//		result.addObject("conferences", conferences);
	//		result.addObject("requestURI", "/conference/organisedSoonList.do");
	//		result.addObject("organisedS", true);
	//
	//		return result;
	//	}

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
		result.addObject("requestURI", "/conference/show.do?conferenceId=" + conference.getId());

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
