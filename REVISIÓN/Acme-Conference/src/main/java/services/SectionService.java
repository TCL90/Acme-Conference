
package services;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SectionRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {

	@Autowired
	private SectionRepository		sectionRepository;

	@Autowired
	private TutorialService			tutorialService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;


	public Collection<Section> findSectionsByTutorial(final Tutorial tutorial) {
		Assert.isTrue(this.administratorService.checkAdmin());
		return this.sectionRepository.findSectionByTutorialId(tutorial.getId());
	}
	
	public Collection<Section> findSectionsByTutorialAdmin(final Tutorial tutorial) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		return this.sectionRepository.findSectionByTutorialId(tutorial.getId());
	}

	public Section createSectionByTutorialId(final int tutorialId) {
		Assert.isTrue(this.administratorService.checkAdmin());
		final Section res = new Section();

		res.setId(0);
		res.setTutorial(this.tutorialService.findOne(tutorialId));

		return res;
	}
	
	public Section createSectionByTutorialIdAdmin(final int tutorialId) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		final Section res = new Section();

		res.setId(0);
		res.setTutorial(this.tutorialService.findOne(tutorialId));

		return res;
	}

	public Section findOne(final int sectionId) {
		final Section res = this.sectionRepository.findOne(sectionId);
		return res;
	}

	public Section reconstruct(final Section s, final BindingResult binding) {
		Section res = new Section();
		if (s.getId() == 0)
			res = s;
		else {
			final Section old = this.findOne(s.getId());
			res.setId(old.getId());
			res.setTutorial(old.getTutorial());
			res.setVersion(old.getVersion());
			res.setPictures(s.getPictures());
			res.setSummary(s.getSummary());
			res.setTitle(s.getTitle());

		}

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;
	}
	public Section save(final Section s) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(s.getTutorial().getConference().isFinalMode() == true);

		return this.sectionRepository.save(s);
	}
	
	public Section saveAdmin(final Section s) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		Assert.isTrue(s.getTutorial().getConference().isFinalMode() == true);

		return this.sectionRepository.save(s);
	}

	public void delete(final Section s) {
		Assert.isTrue(this.administratorService.checkAdmin());
		Assert.isTrue(s.getTutorial().getConference().isFinalMode() == true);

		this.sectionRepository.delete(s);
	}
	
	public void deleteAdmin(final Section s) {
		Assert.isTrue(this.administratorService.checkAdminActor());
		Assert.isTrue(s.getTutorial().getConference().isFinalMode() == true);

		this.sectionRepository.delete(s);
	}

	public Section create(final int tutorialId) {
		Assert.isTrue(this.administratorService.checkAdmin());
		final Section res = new Section();
		res.setId(0);
		res.setTutorial(this.tutorialService.findOne(tutorialId));
		return res;
	}
}
