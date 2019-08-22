
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import security.Authority;
import domain.Administrator;
import domain.Category;
import domain.Conference;
import domain.Finder;

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

	public Conference findOne(final int conferenceId) {
		final Conference c = this.conferenceRepository.findOne(conferenceId);
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au))
			Assert.isTrue(c.isFinalMode());

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

	public Collection<Conference> findAllByAdmin(final Administrator a) {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Collection<Conference> conferences = this.conferenceRepository.findAll();

		//		for (final Conference c : conferences)
		//			Assert.isTrue(c.getAdministrator().getId() == a.getId());
		return conferences;
	}

	//List of the conferences created by the admin a whose submission deadline elapsed in the last 5 days
	public Collection<Conference> findAllByAdminSDElapsed(final Administrator a) {
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		Assert.isTrue(a.getUserAccount().getAuthorities().contains(au));

		Date fiveDaysAgo;
		final Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, -5);
		fiveDaysAgo = ca.getTime();

		final Date today = Calendar.getInstance().getTime();

		//		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//		final String fiveDaysAgoString = sdf.format(fiveDaysAgo);
		//		final String todayString = sdf.format(today);
		//				try {
		//					fiveDaysAgo = sdf.parse(fiveDaysAgoString);
		//				} catch (final ParseException e) {
		//					fiveDaysAgo = null;
		//				}

		final Collection<Conference> conferences = this.conferenceRepository.findAllByAdminSDElapsed(fiveDaysAgo);

		//		for (final Conference c : conferences)
		//			Assert.isTrue(c.getAdministrator().getId() == a.getId());
		return conferences;
	}

	//TODO: TOMAS
	//List of the conferences created by the admin a whose notification deadline elapses in the next 5 days
	//	public Collection<Conference> findAllByAdminNDElapses(final Administrator a) {
	//		final Authority au = new Authority();
	//		au.setAuthority(Authority.ADMIN);
	//
	//		Assert.isTrue(a.getUserAccount().getAuthorities().contains(au));
	//
	//		final Date nextFiveDays;
	//		final Calendar ca = Calendar.getInstance();
	//		ca.add(Calendar.DAY_OF_YEAR, 5);
	//		nextFiveDays = ca.getTime();
	//
	//		final Collection<Conference> conferences = this.conferenceRepository.findAllByAdminNDElapses(a.getId(), nextFiveDays);
	//
	//		//TODO: TOMAS
	//		//		for (final Conference c : conferences)
	//		//			Assert.isTrue(c.getAdministrator().getId() == a.getId());
	//		return conferences;
	//	}
	//
	//	//List of the conferences created by the admin a whose camera-ready deadline elapses in the next 5 days
	//	public Collection<Conference> findAllByAdminCRDElapses(final Administrator a) {
	//		final Authority au = new Authority();
	//		au.setAuthority(Authority.ADMIN);
	//
	//		Assert.isTrue(a.getUserAccount().getAuthorities().contains(au));
	//
	//		final Date nextFiveDays;
	//		final Calendar ca = Calendar.getInstance();
	//		ca.add(Calendar.DAY_OF_YEAR, 5);
	//		nextFiveDays = ca.getTime();
	//
	//		final Collection<Conference> conferences = this.conferenceRepository.findAllByAdminCRDElapses(a.getId(), nextFiveDays);
	//
	//		//TODO: TOMAS
	//		//		for (final Conference c : conferences)
	//		//			Assert.isTrue(c.getAdministrator().getId() == a.getId());
	//		return conferences;
	//	}
	//
	//	//List of the conferences created by the admin a and that will be organised in the next 5 days
	//	public Collection<Conference> findAllByAdminOrganisedSoon(final Administrator a) {
	//		final Authority au = new Authority();
	//		au.setAuthority(Authority.ADMIN);
	//
	//		Assert.isTrue(a.getUserAccount().getAuthorities().contains(au));
	//
	//		final Date nextFiveDays;
	//		final Calendar ca = Calendar.getInstance();
	//		ca.add(Calendar.DAY_OF_YEAR, 5);
	//		nextFiveDays = ca.getTime();
	//
	//		final Collection<Conference> conferences = this.conferenceRepository.findAllByAdminOrganisedSoon(a.getId(), nextFiveDays);
	//
	//		//TODO:TOMAS
	//		//		for (final Conference c : conferences)
	//		//			Assert.isTrue(c.getAdministrator().getId() == a.getId());
	//		return conferences;
	//	}
	//
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

	//FINDER
	public List<Conference> finderKeyword(final String keyword) {
		return this.conferenceRepository.finderKeyword(keyword);
	}

	public List<Conference> finderStartDate(final Date startDate) {
		return this.conferenceRepository.finderStartDate(startDate);
	}

	public List<Conference> finderEndDate(final Date endDate) {
		return this.conferenceRepository.finderStartDate(endDate);
	}

	public Category categoryByTitle(final String cat) {
		return this.conferenceRepository.categoryByTitle(cat);
	}

	public List<Conference> finderCategory(final String cat) {
		final Category c = this.categoryByTitle(cat);
		return this.conferenceRepository.finderCategory(c);
	}

	public List<Conference> finderFee(final Integer fee) {
		return this.conferenceRepository.finderFee(fee);
	}

	public List<Conference> finderResults(final Finder finder) {
		final List<Conference> res = new ArrayList<>(this.conferenceRepository.findAllFinalMode());
		if (finder.getKeyword() != null && finder.getKeyword() != "")
			res.retainAll(this.finderKeyword(finder.getKeyword()));
		if (finder.getStartDate() != null)
			res.retainAll(this.finderStartDate(finder.getStartDate()));
		if (finder.getEndDate() != null)
			res.retainAll(this.finderEndDate(finder.getEndDate()));
		if (finder.getCategory() != null)
			res.retainAll(this.finderCategory(finder.getCategory().getTitleIng()));
		if (finder.getMaximumFee() != null)
			res.retainAll(this.finderFee(finder.getMaximumFee()));
		return res;
	}
}
