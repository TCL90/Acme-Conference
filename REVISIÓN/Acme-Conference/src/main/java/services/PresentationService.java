
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

import domain.Presentation;
import repositories.PresentationRepository;

@Service
@Transactional
public class PresentationService {

	@Autowired
	private PresentationRepository	presentationRepository;

	@Autowired
	private Validator				validator;

	@Autowired
	private CameraReadyPaperService	cameraReadyPaperService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private AdministratorService	administratorService;


	public Presentation findPresentationByActivityId(final int id) {
		final Presentation p = this.presentationRepository.findPresentationByActivityId(id);
		return p;
	}

	public Presentation createPresentationByConferenceId(final int conferenceId, final int cameraId) {
		Assert.isTrue(this.administratorService.checkAdmin());
		final Presentation res = new Presentation();

		res.setId(0);
		res.setConference(this.conferenceService.findOne(conferenceId));
		res.setCameraReadyPaper(this.cameraReadyPaperService.findOne(cameraId));

		return res;
	}
	
	public Presentation createPresentationByConferenceIdAdmin(final int conferenceId, final int cameraId) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		final Presentation res = new Presentation();

		res.setId(0);
		res.setConference(this.conferenceService.findOne(conferenceId));
		res.setCameraReadyPaper(this.cameraReadyPaperService.findOneAdmin(cameraId));

		return res;
	}
	
	public Presentation findOne(final int activityId) {

		final Presentation res = this.presentationRepository.findOne(activityId);
		return res;
	}

	public Presentation reconstruct(final Presentation p, final BindingResult binding) {
		Presentation res;
		if (p.getId() == 0) {
			res = p;

			Date Schedule;
			final Calendar ca = Calendar.getInstance();
			ca.setTime(p.getStartMoment());
			ca.add(Calendar.MINUTE, p.getDuration());
			Schedule = ca.getTime();

			res.setSchedule(Schedule);

		} else {
			final Presentation old = this.findOne(p.getId());

			Date Schedule;
			final Calendar ca = Calendar.getInstance();
			ca.setTime(p.getStartMoment());
			ca.add(Calendar.MINUTE, p.getDuration());
			Schedule = ca.getTime();

			p.setSchedule(Schedule);
			p.setId(old.getId());
			p.setConference(old.getConference());
			p.setVersion(old.getVersion());
			p.setCameraReadyPaper(old.getCameraReadyPaper());

			res = p;

		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}

	public Presentation save(final Presentation p) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(p.getConference().isFinalMode() == true);

		return this.presentationRepository.save(p);
	}
	
	public Presentation saveAdmin(final Presentation p) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		Assert.isTrue(p.getConference().isFinalMode() == true);

		return this.presentationRepository.save(p);
	}

	public void delete(final Presentation p) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(p.getConference().isFinalMode() == true);

		this.presentationRepository.delete(p);
	}
	
	public void deleteAdmin(final Presentation p) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		Assert.isTrue(p.getConference().isFinalMode() == true);

		this.presentationRepository.delete(p);
	}

	public List<Presentation> findAllFromConferenceFM() {
		return this.presentationRepository.findAllFromConferenceFM();
	}
}
