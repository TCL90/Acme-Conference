
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Section;
import domain.Tutorial;
import repositories.TutorialRepository;
import security.Authority;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository		tutorialRepository;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;


	public Tutorial findTutorialByActivityId(final int id) {
		final Tutorial t = this.tutorialRepository.findTutorialByActivityId(id);
		return t;
	}
	
	public List<Tutorial> findAllTutorialsByConferenceId(final int id){
		return this.tutorialRepository.findAllTutorialsByConferenceId(id);
	}

	public Collection<Section> findSectionsByTutorial(final Tutorial tutorial) {
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au))
			Assert.isTrue(tutorial.getConference().isFinalMode());
		Assert.notNull(tutorial);
		return this.tutorialRepository.findSectionsByTutorialId(tutorial.getId());
	}

	public Tutorial createTutorialByConferenceId(final int conferenceId) {
		final Tutorial res = new Tutorial();

		res.setId(0);
		res.setConference(this.conferenceService.findOne(conferenceId));

		return res;
	}

	public Tutorial findOne(final int activityId) {
		final Tutorial res = this.tutorialRepository.findOne(activityId);

		return res;
	}

	public Tutorial reconstruct(final Tutorial t, final BindingResult binding) {
		Tutorial res;
		if (t.getId() == 0) {
			res = t;

			Date Schedule;
			final Calendar ca = Calendar.getInstance();
			ca.setTime(t.getStartMoment());
			ca.add(Calendar.MINUTE, t.getDuration());
			Schedule = ca.getTime();

			res.setSchedule(Schedule);

		} else {
			final Tutorial old = this.findOne(t.getId());

			Date Schedule;
			final Calendar ca = Calendar.getInstance();
			ca.setTime(t.getStartMoment());
			ca.add(Calendar.MINUTE, t.getDuration());
			Schedule = ca.getTime();

			t.setConference(old.getConference());
			t.setId(old.getId());
			t.setVersion(old.getVersion());

			t.setSchedule(Schedule);
			res = t;

		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Tutorial save(final Tutorial t) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(t.getConference().isFinalMode() == true);

		return this.tutorialRepository.save(t);
	}

	public void delete(final Tutorial t) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(t.getConference().isFinalMode() == true);

		this.tutorialRepository.delete(t);
	}
}
