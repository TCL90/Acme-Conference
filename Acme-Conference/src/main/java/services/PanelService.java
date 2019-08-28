
package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Panel;
import repositories.PanelRepository;

@Service
@Transactional
public class PanelService {

	@Autowired
	private PanelRepository			panelRepository;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;


	public Panel findPanelByActivityId(final int id) {
		final Panel p = this.panelRepository.findPanelByActivityId(id);
		return p;
	}

	public Panel createPanelByConferenceId(final int conferenceId) {
		Assert.isTrue(this.administratorService.checkAdmin());
		final Panel res = new Panel();

		res.setId(0);
		res.setConference(this.conferenceService.findOne(conferenceId));

		return res;
	}

	public Panel findOne(final int activityId) {
		final Panel res = this.panelRepository.findOne(activityId);
		return res;
	}

	public Panel reconstruct(final Panel p, final BindingResult binding) {
		Panel res;
		if (p.getId() == 0) {
			res = p;

			Date Schedule;
			final Calendar ca = Calendar.getInstance();
			ca.setTime(p.getStartMoment());
			ca.add(Calendar.MINUTE, p.getDuration());
			Schedule = ca.getTime();

			res.setSchedule(Schedule);

		} else {
			final Panel old = this.findOne(p.getId());

			Date Schedule;
			final Calendar ca = Calendar.getInstance();
			ca.setTime(p.getStartMoment());
			ca.add(Calendar.MINUTE, p.getDuration());
			Schedule = ca.getTime();

			p.setSchedule(Schedule);
			p.setId(old.getId());
			p.setConference(old.getConference());
			p.setVersion(old.getVersion());

			res = p;

		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Panel save(final Panel p) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(p.getConference().isFinalMode() == false);

		return this.panelRepository.save(p);
	}

	public void delete(final Panel p) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(p.getConference().isFinalMode() == false);

		this.panelRepository.delete(p);
	}
	
	public List<Panel> findAllFromConferenceNotFinal(){
		return this.panelRepository.findAllFromConferenceNotFinal();
	}
}
