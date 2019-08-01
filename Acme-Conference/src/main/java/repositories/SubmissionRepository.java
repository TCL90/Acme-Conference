
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

	@Query("select s from Submission s where s.author.id = ?1")
	Collection<Submission> findByAuthor(int authorId);

	@Query("select s from Submission s where s.author.id=?1 and s.status = 'ACCEPTED' and s not in (select c.submission from CameraReadyPaper c)")
	Collection<Submission> findAccepted(int authorId);

}
