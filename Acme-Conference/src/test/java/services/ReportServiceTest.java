package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Report;
import domain.Reviewer;
import domain.Sponsor;
import domain.Submission;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ReportServiceTest  extends AbstractTest {

	@Autowired
	private ReportService	repService;

	@Autowired
	private SubmissionService		submissionService;
	
	@Autowired
	private ReviewerService reviewerS;


	@Test
	public void testSaveConference() {

		super.authenticate("reviewer1");
		Reviewer revi = reviewerS.findByPrincipal();

		final int size1 = this.repService.findByReviewer(revi.getId()).size();

		final Report report = new Report();
		
		final Collection<Submission> subs = this.submissionService.findByReviewer2(revi);
		report.setSubmission(subs.iterator().next());
	
		report.setReviewer(revi);
		report.setDecision("Decision");
		report.setOriginalityScore(0);
		report.setQualityScore(0);
		report.setReadabilityScore(0);

		Report finReport = this.repService.save(report);

		final Collection<Report> repsFin = this.repService.findByReviewer(revi.getId());

		Assert.isTrue(size1 != repsFin.size());
		Assert.isTrue(repsFin.contains(finReport));

		super.authenticate(null);
	}
}
