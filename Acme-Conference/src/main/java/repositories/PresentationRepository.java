
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Presentation;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Integer> {

	@Query("select p from Presentation p where p.id=?1")
	Presentation findPresentationByActivityId(int id);
	
	@Query("select p from Presentation p where p.conference in (select c from Conference c where c.finalMode = '0')")
	List<Presentation> findAllFromConferenceNFM();
}
