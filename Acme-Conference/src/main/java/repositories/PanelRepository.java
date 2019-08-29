
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Panel;

@Repository
public interface PanelRepository extends JpaRepository<Panel, Integer> {

	@Query("select p from Panel p where p.id=?1")
	Panel findPanelByActivityId(int id);

	@Query("select p from Panel p "
			+ "where p.conference in (select c from Conference c where c.finalMode='0')")
	List<Panel> findAllFromConferenceNotFinal();
}
