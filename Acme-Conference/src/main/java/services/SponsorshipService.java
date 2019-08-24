
package services;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Customisation;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SponsorService			sponsorService;

	@Autowired
	private CustomisationService	customisationService;


	//	public Collection<Sponsorship> findAllByConference(final int conferenceId) {
	//		Assert.isTrue(this.administratorService.checkAdmin());
	//
	//		final Collection<Sponsorship> res = this.sponsorshipRepository.findSponsorshipByConferenceId(conferenceId);
	//		return res;
	//	}

	public Sponsorship create() {

		Sponsorship result;
		result = new Sponsorship();
		result.setSponsor(this.sponsorService.findByPrincipal());

		// Sponsorship

		return result;
	}

	public Sponsorship save(final Sponsorship sponsorship) {
		Assert.notNull(sponsorship);

		if (sponsorship.getId() == 0) {
			final Sponsor sponsor = this.sponsorService.findByPrincipal();
			sponsorship.setSponsor(sponsor);
		}

		final List<Customisation> cuss = (List<Customisation>) this.customisationService.findAll();
		final Customisation cus = cuss.get(0);
		final Collection<String> makes = cus.getCreditCardMakes();
		Assert.isTrue(makes.contains(sponsorship.getMakeName()));

		Sponsorship res;

		res = this.sponsorshipRepository.save(sponsorship);

		return res;
	}

	public Collection<Sponsorship> findBySponsor(final int sponsorId) {
		return this.sponsorshipRepository.findBySponsor(sponsorId);
	}

	public Sponsorship findOne(final int sponsorshipId) {
		return this.sponsorshipRepository.findOne(sponsorshipId);
	}

	public void delete(final Sponsorship spo) {
		this.sponsorshipRepository.delete(spo);
	}

	public Sponsorship random() {
		final List<Sponsorship> sponsorships = this.sponsorshipRepository.findAll();
		final int size = sponsorships.size();
		final int randomNum = ThreadLocalRandom.current().nextInt(0, size);

		return sponsorships.get(randomNum);
	}
}
