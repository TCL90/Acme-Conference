
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import security.Authority;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	@Autowired
	private ConferenceRepository	conferenceRepository;

	@Autowired
	private AdministratorService	administratorService;


	public Collection<Conference> findAllFinalMode() {
		return this.conferenceRepository.findAllFinalMode();
	}

	public Collection<Conference> findAllPast() {
		return this.conferenceRepository.findAllPast();
	}

	public Collection<Conference> findAllForthComming() {
		return this.conferenceRepository.findAllForthComming();
	}

	public Collection<Conference> findAllRunning() {
		return this.conferenceRepository.findAllRunning();
	}

	public Collection<Conference> findAllKeyword(final String keyword) {
		return this.conferenceRepository.findAllKeyword(keyword);
	}

	public Collection<Conference> findAllKeywordAdmin(final String keyword) {
		Assert.isTrue(this.administratorService.checkAdmin());
		return this.conferenceRepository.findAllKeywordAdmin(keyword);
	}

	public Conference findOne(final int conferenceId) {
		final Conference c = this.conferenceRepository.findOne(conferenceId);
		Assert.notNull(c);
		return c;
	}

	public int numberOfRegistrations(final Conference c) {
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au))
			Assert.isTrue(c.isFinalMode());

		Assert.notNull(c);

		return this.conferenceRepository.numberOfRegistrations(c.getId());
	}

	public Collection<Conference> findAllForthCommingNotRegistered(final int authorId) {
		return this.conferenceRepository.findAllForthCommingNotRegistered(authorId);
	}

	public Collection<Conference> findAllForthCommingNotSubmitted(final int authorId) {
		return this.conferenceRepository.findAllForthCommingNotSubmitted(authorId);
	}

	public Collection<Conference> findAllForthCommingSubmitted(final int authorId) {
		return this.conferenceRepository.findAllForthCommingSubmitted(authorId);
	}

	public Collection<Conference> findAllByAdmin() {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Collection<Conference> conferences = this.conferenceRepository.findAll();

		//		for (final Conference c : conferences)
		//			Assert.isTrue(c.getAdministrator().getId() == a.getId());
		return conferences;
	}

	//List of the conferences created by the admin a whose submission deadline elapsed in the last 5 days
	public Collection<Conference> findAllByAdminSDElapsed() {
		Assert.isTrue(this.administratorService.checkAdmin());

		Date fiveDaysAgo;
		final Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, -5);
		fiveDaysAgo = ca.getTime();

		return this.conferenceRepository.findAllByAdminSDElapsed(fiveDaysAgo);
	}
	//List of the conferences created by the admin a whose notification deadline elapses in the next 5 days
	public Collection<Conference> findAllByAdminNDElapses() {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Date nextFiveDays;
		final Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, 5);
		nextFiveDays = ca.getTime();

		return this.conferenceRepository.findAllByAdminNDElapses(nextFiveDays);
	}

	//List of the conferences created by the admin a whose camera-ready deadline elapses in the next 5 days
	public Collection<Conference> findAllByAdminCRDElapses() {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Date nextFiveDays;
		final Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, 5);
		nextFiveDays = ca.getTime();

		return this.conferenceRepository.findAllByAdminCRDElapses(nextFiveDays);
	}
	//List of the conferences created by the admin a and that will be organised in the next 5 days
	public Collection<Conference> findAllByAdminOrganisedSoon() {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Date nextFiveDays;
		final Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, 5);
		nextFiveDays = ca.getTime();

		return this.conferenceRepository.findAllByAdminOrganisedSoon(nextFiveDays);
	}

	public Collection<Conference> findAllPastAdministrator() {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Collection<Conference> conferences = this.conferenceRepository.findAllPastAdministrator();
		return conferences;
	}

	public Collection<Conference> findAllForthCommingAdministrator() {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Collection<Conference> conferences = this.conferenceRepository.findAllForthCommingAdministrator();
		return conferences;
	}

	public Collection<Conference> findAllRunningAdministrator() {
		Assert.isTrue(this.administratorService.checkAdmin());
		//		final Collection<Conference> res = new ArrayList<>();
		//		final Date today = Calendar.getInstance().getTime();
		//		for (final Conference c : this.conferenceRepository.findAll())
		//			if (c.getStartDate().before(today))
		//				if (c.getEndDate().after(today))
		//					res.add(c);

		return this.conferenceRepository.findAllRunningAdministrator();
	}

	public Conference create() {
		final Conference res = new Conference();

		res.setFinalMode(false);

		return res;
	}

	public Conference save(final Conference conference) {

		Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotificationDeadline()), "submissionBeforeNotification");

		Assert.isTrue(conference.getNotificationDeadline().before(conference.getCameraReadyDeadline()), "notificationBeforeCamera");

		Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()), "cameraBeforeStart");

		Assert.isTrue(conference.getStartDate().before(conference.getEndDate()), "startBeforeEnd");

		return this.conferenceRepository.save(conference);

	}

	public void delete(final Conference conf) {
		Assert.isTrue(!conf.isFinalMode());

		this.conferenceRepository.delete(conf);

	}

}
