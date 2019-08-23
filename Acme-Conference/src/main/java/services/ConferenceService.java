
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import security.Authority;
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

	@Autowired
	private CustomisationService	customisationService;


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

	public List<String> findBuzzwords() {
		//TODO: 12 MESES DE CONFERENCES
		//TODO: ELIMINACIÓN DE BUZZWORDS
		final List<String> findWords = this.conferenceRepository.findTitles();
		findWords.addAll(this.conferenceRepository.findSummaries());
		final List<String> wordsSplitted = new ArrayList<String>();

		//Se separa en palabras
		for (final String w : findWords)
			wordsSplitted.addAll(Arrays.asList(w.split(" ")));

		//Se eliminan las void words
		final List<String> buzzs = this.customisationService.findBuzzWords();
		final boolean funsiona = wordsSplitted.removeAll(buzzs);

		//Las claves serán las palabras y los valores su frecuencia
		final Map<String, Integer> stringCountMap = new TreeMap<String, Integer>();

		//Para cada palabra, si no existía se introduce en el map 
		//Si ya existía se aumenta su frecuencia en 1
		for (final String s : wordsSplitted)
			if (stringCountMap.containsKey(s))
				stringCountMap.put(s, stringCountMap.get(s) + 1);
			else
				stringCountMap.put(s, 1);

		//Se busca cuál es la palabra con mayor frecuencia
		Integer ind = 0;
		String buzztop = "";
		for (final String st : stringCountMap.keySet())
			if (stringCountMap.get(st) > ind) {
				ind = stringCountMap.get(st);
				buzztop = st;
			}

		//A partir de esta palabra se obtienen todas las buzzwords (20% más frecuentes)
		final double buzzlimit = ind - 0.2 * ind;
		final List<String> buzzwords = new ArrayList<String>();
		for (final String st : stringCountMap.keySet())
			if (stringCountMap.get(st) > buzzlimit)
				buzzwords.add(st);

		return buzzwords;
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
		//		if (finder.getMaximumFee() != null)
		//			res.retainAll(this.finderFee(finder.getMaximumFee()));
		return res;
	}
}
