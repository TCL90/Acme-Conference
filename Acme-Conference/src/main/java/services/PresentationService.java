
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PresentationRepository;
import domain.Presentation;

@Service
@Transactional
public class PresentationService {

	@Autowired
	private PresentationRepository	presentationRepository;


	public Presentation findPresentationByActivityId(final int id) {
		final Presentation p = this.presentationRepository.findPresentationByActivityId(id);
		return p;
	}
}
