
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import domain.Activity;
import domain.Conference;

@Service
@Transactional
public class ActivityService {

	@Autowired
	private ActivityRepository	activityRepository;

	@Autowired
	private PresentationService	presentationService;

	@Autowired
	private TutorialService		tutorialService;


	public Collection<Activity> findAllByConference(final Conference c) {
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

	public String selectType(final Activity activity) {
		String type = null;
		Assert.isTrue(activity.getConference().isFinalMode());
		Assert.notNull(activity);

		//		if (this.findPanelByActivityId(activity.getId()) != null)
		//			type = "panel";
		if (this.presentationService.findPresentationByActivityId(activity.getId()) != null)
			type = "presentation";
		else if (this.tutorialService.findTutorialByActivityId(activity.getId()) != null)
			type = "tutorial";
		else
			type = "activity";

		return type;
	}

}
