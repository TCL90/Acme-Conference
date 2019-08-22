
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import security.Authority;
import domain.Activity;
import domain.Conference;

@Service
@Transactional
public class ActivityService {

	@Autowired
	private ActivityRepository		activityRepository;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private PresentationService		presentationService;

	@Autowired
	private TutorialService			tutorialService;

	@Autowired
	private PanelService			panelService;


	public Collection<Activity> findAllByConference(final Conference c) {
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au))
			Assert.isTrue(c.isFinalMode());

		Assert.notNull(c);

		return this.activityRepository.findAllByConference(c.getId());
	}

	public Activity findOne(final int activityId) {
		final Activity activity = this.activityRepository.findOne(activityId);
		Assert.isTrue(activity.getConference().isFinalMode());
		Assert.notNull(activity);
		return activity;
	}

	public String selectType(final int activityId) {
		String type = null;

		if (this.panelService.findPanelByActivityId(activityId) != null)
			type = "panel";
		if (this.presentationService.findPresentationByActivityId(activityId) != null)
			type = "presentation";
		else if (this.tutorialService.findTutorialByActivityId(activityId) != null)
			type = "tutorial";

		return type;
	}

}
