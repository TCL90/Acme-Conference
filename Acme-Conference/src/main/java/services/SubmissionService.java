
package services;

import java.util.Collection;
import java.util.Date;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ConferenceRepository;
import repositories.ReportRepository;
import repositories.SubmissionRepository;
import utilities.TickerGenerator;
import domain.Author;
import domain.Conference;
import domain.Paper;
import domain.Report;
import domain.Reviewer;
import domain.Submission;
import forms.SubmissionForm;

@Service
@Transactional
public class SubmissionService {

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private PaperService			paperService;

	@Autowired
	private ReportRepository		reportRepository;

	@Autowired
	private ConferenceRepository	conferenceRepository;


	public Collection<Submission> findByAuthor(final int authorId) {
		return this.submissionRepository.findByAuthor(authorId);
	}

	public Collection<Submission> findByReviewer2(final Reviewer revi) {
		return this.submissionRepository.findByReviewer2(revi);
	}

	public SubmissionForm create() {

		final SubmissionForm res = new SubmissionForm();

		final Author logAuthor = this.authorService.findByPrincipal();

		res.setAuthor(logAuthor);
		res.setMoment(new Date());
		res.setTicker(TickerGenerator.tickerPosition(logAuthor));
		res.setStatus("UNDER-REVIEW");

		return res;

	}

	public Submission save(final Submission sub) {
		return this.submissionRepository.save(sub);
	}


	@Autowired
	private Validator	validator;


	public Submission reconstructSubmissionPaper(final SubmissionForm submissionForm, final BindingResult binding) {
		final Submission res = new Submission();
		//			res = this.submissionRepository.findOne(submissionForm.getId());
		res.setAuthor(this.authorService.findByPrincipal());
		res.setStatus(submissionForm.getStatus());
		res.setTicker(submissionForm.getTicker());
		res.setMoment(submissionForm.getMoment());
		res.setConference(submissionForm.getConference());

		final Paper pap = new Paper();

		pap.setAuthors(submissionForm.getAuthors());
		pap.setDocument(submissionForm.getDocument());
		pap.setSummary(submissionForm.getSummary());
		pap.setTitle(submissionForm.getTitle());

		final Paper pap2 = this.paperService.save(pap);
		res.setPaper(pap2);

		this.validator.validate(res, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return res;

	}

	public Submission findOne(final int submissionId) {
		return this.submissionRepository.findOne(submissionId);
	}

	public Collection<Submission> findAccepted(final int authorId) {
		return this.submissionRepository.findAccepted(authorId);
	}

	public Collection<Submission> findUnderReview() {
		return this.submissionRepository.findUnderReview();
	}

	public Collection<Submission> decisionProcedure(final int conferenceId) {
<<<<<<< HEAD
=======
		final Conference conference = this.conferenceRepository.findOne(conferenceId);
		//TODO: COMPROBAR SUBMISSION 
		//Assert.isTrue(conference.getSubmissionDeadline().before());
>>>>>>> CU-ManageConferences
		final Collection<Submission> submissions = this.submissionRepository.findUnderReviewReported(conferenceId);
		Collection<Report> reports = null;
		int accept = 0;
		int reject = 0;
		int borderLine = 0;

		for (final Submission s : submissions) {
			accept = 0;
			reject = 0;
			borderLine = 0;
			reports = this.reportRepository.findBySubmission(s.getId());
			for (final Report r : reports)
				if (r.getDecision().contains("ACCEPT"))
					accept++;
				else if (r.getDecision().contains("REJECT"))
					reject++;
				else if (r.getDecision().contains("BORDER-LINE"))
					borderLine++;
			if (reject > accept)
				s.setStatus("REJECTED");
			else
				s.setStatus("ACCEPTED");
			this.submissionRepository.save(s);
			this.submissionRepository.flush();
		}

		return submissions;
	}

	public Collection<Submission> findAll() {
		return this.submissionRepository.findAll();
	}
}
