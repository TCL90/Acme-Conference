
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customisation;

@Repository
public interface CustomisationRepository extends JpaRepository<Customisation, Integer> {

	@Query("select b from Customisation c join c.buzzWordsEsp b")
	List<String> findBuzzEsp();

	@Query("select b from Customisation c join c.buzzWordsIng b")
	List<String> findBuzzEng();

}
