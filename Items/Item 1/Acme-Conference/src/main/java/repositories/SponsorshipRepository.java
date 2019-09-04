
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Sponsorship;

@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship, Integer> {

	//	@Query("select s from Sponsorship s join s.conference c where c.id=?1")
	//	Collection<Sponsorship> findSponsorshipByConferenceId(int conferenceId);

	@Query("select r from Sponsorship r where r.sponsor.id = ?1")
	Collection<Sponsorship> findBySponsor(int sponsorId);
}
