
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Reviewer;
import domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

	@Query("select s from Submission s where s.author.id = ?1")
	Collection<Submission> findByAuthor(int authorId);

	@Query("select s from Submission s where ?1 member of s.reviewers")
	Collection<Submission> findByReviewer2(Reviewer reviewer);

	@Query("select s from Submission s where s.author.id=?1 and s.conference.cameraReadyDeadline > NOW() and s.status = 'ACCEPTED' and s not in (select c.submission from CameraReadyPaper c)")
	Collection<Submission> findAccepted(int authorId);

	@Query("select s from Submission s where s.status = 'UNDER-REVIEW'")
	Collection<Submission> findUnderReview();

	@Query("select distinct r.submission from Report r where r.submission.status = 'UNDER-REVIEW' and r.submission.conference.id=?1")
	Collection<Submission> findUnderReviewReported(int conferenceId);

	@Query("select s.author from Submission s where s.conference.id = ?1")
	//Collection<Author> findAllAuthorsSubmissionConf(int conferenceId);
	Collection<Actor> findAllAuthorsSubmissionConf(int conferenceId);
}
