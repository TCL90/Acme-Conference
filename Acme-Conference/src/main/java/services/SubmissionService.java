
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import domain.Actor;
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
	private MessageService			messageService;

	@Autowired
	private ReportRepository		reportRepository;

	@Autowired
	private ConferenceRepository	conferenceRepository;

	@Autowired
	private ReviewerService			reviewerService;


	public Collection<Submission> findByAuthor(final int authorId) {
		return this.submissionRepository.findByAuthor(authorId);
	}

	public Collection<Submission> findByReviewer2(final Reviewer revi) {
		return this.submissionRepository.findByReviewer2(revi);
	}

	public Collection<Submission> findByReviewerUnderReview(final Reviewer revi) {
		return this.submissionRepository.findByReviewerUnderReview(revi);
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
//	
//	public Collection<Author> findAllAuthorSubmissionToConference(int conferenceId){
//		return this.submissionRepository.findAllAuthorsSubmissionConf(conferenceId);
//	}

	//public Collection<Author> findAllAuthorSubmissionToConference(int conferenceId){
	public Collection<Actor> findAllAuthorSubmissionToConference(final int conferenceId) {
		return this.submissionRepository.findAllAuthorsSubmissionConf(conferenceId);
	}

	public Collection<Submission> decisionProcedure(final int conferenceId) {
		final Conference conference = this.conferenceRepository.findOne(conferenceId);
		//TODO: COMPROBAR SUBMISSION 
		//Assert.isTrue(conference.getSubmissionDeadline().before());
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
			if (reject > accept) {
				s.setStatus("REJECTED");
				this.messageService.NotificateMessage(s);
			} else {
				s.setStatus("ACCEPTED");
				this.messageService.NotificateMessage(s);
			}
			this.submissionRepository.save(s);
			this.submissionRepository.flush();
		}

		return submissions;
	}

	public Collection<Submission> findAll() {
		return this.submissionRepository.findAll();
	}

	public Collection<Reviewer> assignReviewers(final Submission submission) {
		//final Submission submission = this.submissionRepository.findOne(submissionId);
		final Conference conference = submission.getConference();
		final List<Reviewer> reviewers = (List<Reviewer>) this.reviewerService.findAll();
		final Set<Reviewer> res = new HashSet<Reviewer>();

		final String[] title = conference.getTitle().split(" ");
		final String[] summary = conference.getSummary().split(" ");

		Reviewer r = null;

		for (int i = 0; i < reviewers.size(); i++) {
			r = reviewers.get(i);

			//En title2 se guardan todas las palabras de la conferencia
			final List<String> title2 = new ArrayList<String>(Arrays.asList(title));
			final List<String> summary2 = new ArrayList<String>(Arrays.asList(summary));
			title2.addAll(summary2);

			//Se comprueba si hay palabras en com�n entre title2 y el expertise
			final Collection<String> expertise2 = r.getExpertise();
			title2.retainAll(expertise2);

			//Si hay palabras en com�n, se a�ade el reviewer
			if (title2.size() > 0)
				res.add(r);

		}

		//Si no hubiera suficientes reviewers, se completa hasta llegar a 3
		if (res.size() < 3)
			for (final Reviewer re : reviewers) {
				res.add(re);
				if (res.size() >= 3)
					break;
			}
		submission.setReviewers(res);

		this.save(submission);

		return res;
	}

}
