
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.Conference;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	@Autowired
	FinderRepository	finderRepository;

	@Autowired
	AuthorService		authorService;

	@Autowired
	ConferenceService	conferenceService;


	private boolean checkAuthor() {
		final Authority a = new Authority();
		a.setAuthority(Authority.AUTHOR);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public Finder getFinder() {
		final Author a = this.authorService.findByPrincipal();
		return a.getFinder();
	}

	public Finder save(final Finder finder) {
		final Finder res = finder;
		Assert.isTrue(this.checkAuthor());
		if (finder.getId() != 0) {
			final Author principal = this.authorService.findByPrincipal();
			Assert.isTrue(principal.getFinder().getId() == finder.getId());

			final List<Conference> results = this.conferenceService.finderResults(finder);
			res.setConferences(results);

		}
		return this.finderRepository.save(res);
	}
}
