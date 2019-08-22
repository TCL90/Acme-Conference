
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import domain.Comment;
import domain.Conference;
import domain.Panel;
import domain.Presentation;
import domain.Report;
import domain.Tutorial;

@Service
@Transactional
public class CommentService {

	@Autowired
	CommentRepository				commentRepository;

	@Autowired
	private AdministratorService	administratorService;


	public Collection<Comment> findByConference(final Conference c) {
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au))
			Assert.isTrue(c.isFinalMode());

		Assert.notNull(c);

		return this.commentRepository.findByConference(c.getId());
	}

	public Collection<Comment> findByReport(final Report r) {
		Assert.notNull(r);

		return this.commentRepository.findByReport(r.getId());
	}

	public Collection<Comment> findByPresentation(final Presentation presentation) {
		Assert.isTrue(presentation.getConference().isFinalMode());
		Assert.notNull(presentation);

		return this.commentRepository.findByActivity(presentation.getId());
	}

	public Collection<Comment> findByPanel(final Panel p) {
		Assert.isTrue(p.getConference().isFinalMode());
		Assert.notNull(p);

		return this.commentRepository.findByActivity(p.getId());
	}

	public Collection<Comment> findByTutorial(final Tutorial t) {
		Assert.isTrue(t.getConference().isFinalMode());
		Assert.notNull(t);

		return this.commentRepository.findByActivity(t.getId());
	}

}
