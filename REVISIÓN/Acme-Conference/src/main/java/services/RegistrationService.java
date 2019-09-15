
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RegistrationRepository;
import domain.Actor;
import domain.Customisation;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private RegistrationRepository	registrationRepository;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private CustomisationService	customisationService;


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

		final List<Customisation> cuss = (List<Customisation>) this.customisationService.findAll();
		final Customisation cus = cuss.get(0);
		final Collection<String> makes = cus.getCreditCardMakes();
		Assert.isTrue(makes.contains(registration.getMakeName()));

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

	public Collection<Registration> findByConference(final int conferenceId) {
		return this.registrationRepository.findByConference(conferenceId);
	}
	
//	public Collection<Author> findAuthorsRegisteredConf(final int conferenceId){
//		return this.registrationRepository.findAuthorsRegisteredConf(conferenceId);
//	}

	//	public Collection<Author> findAuthorsRegisteredConf(final int conferenceId){
	public Collection<Actor> findAuthorsRegisteredConf(final int conferenceId) {
		return this.registrationRepository.findAuthorsRegisteredConf(conferenceId);
	}

}
