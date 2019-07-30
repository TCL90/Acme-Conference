
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
