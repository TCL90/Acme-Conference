
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Activity;
import domain.Comment;
import domain.Conference;
import domain.Report;

@Service
@Transactional
public class CommentService {

	@Autowired
	CommentRepository	commentRepository;


	public Collection<Comment> findByConference(final Conference c) {
		Assert.isTrue(c.isFinalMode());
		Assert.notNull(c);

		return this.commentRepository.findByConference(c.getId());
	}

	public Collection<Comment> findByActivity(final Activity a) {
		Assert.isTrue(a.getConference().isFinalMode());
		Assert.notNull(a);

		return this.commentRepository.findByActivity(a.getId());
	}

	public Collection<Comment> findByReport(final Report r) {
		Assert.notNull(r);

		return this.commentRepository.findByReport(r.getId());
	}

}
