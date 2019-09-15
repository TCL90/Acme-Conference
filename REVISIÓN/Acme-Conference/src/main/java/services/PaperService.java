
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PaperRepository;
import domain.Paper;

@Service
@Transactional
public class PaperService {

	@Autowired
	private PaperRepository	paperRepository;


	public Paper save(final Paper paper) {

		return this.paperRepository.save(paper);
	}

}
