
package services;

import java.util.Collection;
import java.util.Date;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubmissionRepository;
import utilities.TickerGenerator;
import domain.Author;
import domain.Paper;
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


	public Collection<Submission> findByAuthor(final int authorId) {
		return this.submissionRepository.findByAuthor(authorId);
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

}
