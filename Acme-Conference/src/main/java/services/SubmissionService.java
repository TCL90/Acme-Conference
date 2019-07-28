
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SubmissionRepository;
import domain.Submission;

@Service
@Transactional
public class SubmissionService {

	@Autowired
	private SubmissionRepository	submissionRepository;


	public Collection<Submission> findByAuthor(final int authorId) {
		return this.submissionRepository.findByAuthor(authorId);
	}

}
