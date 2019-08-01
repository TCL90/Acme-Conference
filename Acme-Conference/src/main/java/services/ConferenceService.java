
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	@Autowired
	private ConferenceRepository	conferenceRepository;


	public Collection<Conference> findAllFinalMode() {
		return this.conferenceRepository.findAllFinalMode();
	}

	public Collection<Conference> findAllPast() {
		return this.conferenceRepository.findAllPast();
	}

	public Collection<Conference> findAllForthComming() {
		return this.conferenceRepository.findAllForthComming();
	}

	public Collection<Conference> findAllRunning() {
		return this.conferenceRepository.findAllRunning();
	}

	public Collection<Conference> findAllKeyword(final String keyword) {
		return this.conferenceRepository.findAllKeyword(keyword);
	}

	public Conference findOne(final int conferenceId) {
		final Conference c = this.conferenceRepository.findOne(conferenceId);
		Assert.isTrue(c.isFinalMode());
		Assert.notNull(c);
		return c;
	}

	public int numberOfRegistrations(final Conference c) {
		Assert.isTrue(c.isFinalMode());
		Assert.notNull(c);

		return this.conferenceRepository.numberOfRegistrations(c.getId());
	}

	public Collection<Conference> findAllForthCommingNotRegistered(final int authorId) {
		return this.conferenceRepository.findAllForthCommingNotRegistered(authorId);
	}

	public Collection<Conference> findAllForthCommingNotSubmitted(final int authorId) {
		return this.conferenceRepository.findAllForthCommingNotSubmitted(authorId);
	}

	public Collection<Conference> findAllForthCommingSubmitted(final int authorId) {
		return this.conferenceRepository.findAllForthCommingSubmitted(authorId);
	}

}
