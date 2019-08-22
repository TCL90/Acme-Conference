
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private AdministratorService	administratorService;


	public Collection<Sponsorship> findAllByConference(final int conferenceId) {
		Assert.isTrue(this.administratorService.checkAdmin());

		final Collection<Sponsorship> res = this.sponsorshipRepository.findSponsorshipByConferenceId(conferenceId);
		return res;
	}
}
