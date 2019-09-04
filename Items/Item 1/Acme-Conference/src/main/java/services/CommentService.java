
package services;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import domain.Activity;
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

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ReviewerService			reviewerService;

	@Autowired
	private SponsorService			sponsorService;

	@Autowired
	private ConferenceService		conferenceService;

	@Autowired
	private ActivityService			activityService;

	@Autowired
	private PresentationService		presentationService;

	@Autowired
	private PanelService			panelService;

	@Autowired
	private TutorialService			tutorialService;

	@Autowired
	private ReportService			reportService;


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
		Collection<Comment> res;
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au)) {
			if (r.getSubmission().getConference().isFinalMode())
				res = this.commentRepository.findByReport(r.getId());
			else
				res = null;
		} else
			res = this.commentRepository.findByReport(r.getId());

		return res;
	}

	public Collection<Comment> findByPresentation(final Presentation presentation) {
		Collection<Comment> res;
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au)) {
			if (!presentation.getConference().isFinalMode())
				res = null;
			else
				res = this.commentRepository.findByActivity(presentation.getId());
		} else
			res = this.commentRepository.findByActivity(presentation.getId());

		return res;
	}
	public Collection<Comment> findByPanel(final Panel p) {
		Collection<Comment> res;
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au)) {
			if (!p.getConference().isFinalMode())
				res = null;
			else
				res = this.commentRepository.findByActivity(p.getId());
		} else
			res = this.commentRepository.findByActivity(p.getId());

		return res;
	}

	public Collection<Comment> findByTutorial(final Tutorial t) {
		Collection<Comment> res;
		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority au = new Authority();
		au.setAuthority(Authority.ADMIN);

		if (!AuCollection.contains(au)) {
			if (!t.getConference().isFinalMode())
				res = null;
			else
				res = this.commentRepository.findByActivity(t.getId());
		} else
			res = this.commentRepository.findByActivity(t.getId());

		return res;
	}

	public Comment createCommentByCommentableId(final int id, String type) {
		final Comment res = new Comment();

		final Collection<Authority> AuCollection = (Collection<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		final Authority a = new Authority();
		final Authority a2 = new Authority();
		final Authority a3 = new Authority();
		final Authority a4 = new Authority();

		a.setAuthority(Authority.ADMIN);
		a2.setAuthority(Authority.AUTHOR);
		a3.setAuthority(Authority.REVIEWER);
		a4.setAuthority(Authority.SPONSOR);

		if (!AuCollection.isEmpty()) {
			if (AuCollection.contains(a2))
				res.setAuthor(this.authorService.findByPrincipal());
			if (AuCollection.contains(a))
				res.setAuthor(this.administratorService.findByPrincipal());
			if (AuCollection.contains(a3))
				res.setAuthor(this.reviewerService.findByPrincipal());
			if (AuCollection.contains(a4))
				res.setAuthor(this.sponsorService.findByPrincipal());
		}
		res.setMoment(Calendar.getInstance().getTime());
		res.setId(0);

		if (type.contains("conference")) {
			final Conference commentable = this.conferenceService.findOne(id);
			res.setCommentable(commentable);
			Assert.isTrue(commentable.isFinalMode());
		}

		if (type.contains("activity")) {
			Activity commentable = null;
			type = this.activityService.selectType(id);
			if (type.contains("presentation"))
				commentable = this.presentationService.findOne(id);
			else if (type.contains("tutorial"))
				commentable = this.tutorialService.findOne(id);
			else if (type.contains("panel"))
				commentable = this.panelService.findOne(id);

			res.setCommentable(commentable);
			Assert.isTrue(commentable.getConference().isFinalMode());
		}

		if (type.contains("report")) {
			final Report commentable = this.reportService.findOne(id);
			res.setCommentable(commentable);
			Assert.isTrue(commentable.getSubmission().getConference().isFinalMode());
			Assert.isTrue(this.reviewerService.checkReviewer());
			Assert.isTrue(commentable.getReviewer().getId() == this.reviewerService.findByPrincipal().getId());
		}

		return res;
	}

	public Comment save(final Comment c) {

		return this.commentRepository.save(c);
	}

}
