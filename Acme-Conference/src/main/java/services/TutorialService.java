
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	tutorialRepository;


	public Tutorial findTutorialByActivityId(final int id) {
		return this.tutorialRepository.findTutorialByActivityId(id);
	}

	public Collection<Section> findSectionsByTutorial(final Tutorial tutorial) {
		Assert.isTrue(tutorial.getConference().isFinalMode());
		Assert.notNull(tutorial);
		return this.tutorialRepository.findSectionsByTutorialId(tutorial.getId());
	}
}
