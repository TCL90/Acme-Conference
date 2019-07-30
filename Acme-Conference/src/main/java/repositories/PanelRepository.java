
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Panel;

@Repository
public interface PanelRepository extends JpaRepository<Panel, Integer> {

	@Query("select p from Panel p where p.id=?1")
	Panel findPanelByActivityId(int id);
}
