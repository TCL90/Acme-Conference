
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RegistrationRepository;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private RegistrationRepository	registrationRepository;

	@Autowired
	private AuthorService			authorService;


	//Constructor
	public RegistrationService() {
		super();
	}

	public Registration create() {

		Registration result;
		result = new Registration();
		result.setAuthor(this.authorService.findByPrincipal());

		// Registration

		return result;
	}

	public Registration save(final Registration registration) {
		Assert.notNull(registration);

		//Se comprueba que el author no ha enviado ya una registration a la conference

		Registration res;

		res = this.registrationRepository.save(registration);

		return res;
	}

	public Collection<Registration> findByAuthor(final int authorId) {
		return this.registrationRepository.findByAuthor(authorId);
	}

	public Collection<Registration> findByAuthorAndConference(final int authorId, final int conferenceId) {
		return this.registrationRepository.findByAuthorAndConference(authorId, conferenceId);
	}

	public Registration findOne(final int registrationId) {
		return this.registrationRepository.findOne(registrationId);
	}

}
