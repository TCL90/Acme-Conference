
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Section;
import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("select t from Tutorial t where t.id=?1")
	Tutorial findTutorialByActivityId(int id);

	@Query("select s from Section s where s.tutorial.id=?1")
	Collection<Section> findSectionsByTutorialId(int id);

	@Query("select t from Tutorial t where t.conference.id = ?1")
	List<Tutorial> findAllTutorialsByConferenceId(int id);

}
