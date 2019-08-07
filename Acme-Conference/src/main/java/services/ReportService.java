
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
}
