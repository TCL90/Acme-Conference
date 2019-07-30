
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	@Query("select c from Conference c where c.endDate < NOW() and c.finalMode='1'")
	Collection<Conference> findAllPast();

	@Query("select c from Conference c where c.startDate > NOW() and c.finalMode='1'")
	Collection<Conference> findAllForthComming();

	@Query("select c from Conference c where c.startDate <= NOW() and c.endDate >= NOW() and c.finalMode='1'")
	Collection<Conference> findAllRunning();

	@Query("select c from Conference c where c.finalMode='1'")
	Collection<Conference> findAllFinalMode();

	@Query("select c from Conference c where (c.title like %:keyword% or c.venue like %:keyword% or c.acronym like %:keyword% or c.summary like %:keyword%) and c.finalMode='1'")
	Collection<Conference> findAllKeyword(@Param("keyword") String keyword);

	@Query("select c from Conference c where c.startDate > NOW() and c.finalMode='1'")
	Collection<Conference> findAllForthCommingNotRegistered(int authorId);

}
