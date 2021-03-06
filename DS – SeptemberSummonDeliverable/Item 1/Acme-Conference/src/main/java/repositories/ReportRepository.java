
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.submission.id = ?1")
	Collection<Report> findBySubmission(int submissionId);

	@Query("select r from Report r where r.reviewer.id = ?1")
	Collection<Report> findByReviewer(int reviewerId);

	@Query("select r from Report r where r.submission.id=?1 and r.submission.status!='UNDER-REVIEW'")
	Collection<Report> findAllBySubmissionId(int id);

}
