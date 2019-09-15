
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Report;
import domain.Reviewer;
import domain.Submission;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private ReviewerService		reviewerService;

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private SubmissionService	submissionService;


	public Collection<Report> findByReviewer(final int reviewerId) {
		return this.reportRepository.findByReviewer(reviewerId);
	}

	public Report save(final Report report) {

		final Reviewer logReviewer = this.reviewerService.findByPrincipal();

		//Se comprueba que el reviewer es el loggeado
		Assert.isTrue(logReviewer.getId() == report.getReviewer().getId());

		//Se comprueba que la submission la tiene asignada el reviewer loggeado
		final Collection<Submission> submissions = this.submissionService.findByReviewer2(logReviewer);

		Assert.isTrue(submissions.contains(report.getSubmission()));

		return this.reportRepository.save(report);
	}

	public Report findOne(final int id) {
		Report res;
		res = this.reportRepository.findOne(id);
		if (!this.reviewerService.checkReviewer()) {
			Assert.isTrue(!res.getSubmission().getStatus().contains("UNDER-REVIEW"));
			Assert.isTrue(this.authorService.findByPrincipal().getId() == res.getSubmission().getAuthor().getId());
		}
		return res;
	}

	public Collection<Report> findAllBySubmissionId(final int submissionId) {
		final Submission s = this.submissionService.findOne(submissionId);
		Assert.isTrue(s.getAuthor().getId() == this.authorService.findByPrincipal().getId());

		return this.reportRepository.findAllBySubmissionId(submissionId);
	}
}
