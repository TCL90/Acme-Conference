
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.parent = ?1")
	List<Category> findChilds(Category c);

	@Query("select c from Category c where c.parent is not null "
			+ "and c not in (select conf.category from Conference conf))")
	List<Category> findAllEmpty();
}
