
package repositories;

import java.util.Collection;
import java.util.Date;

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

	@Query("select count(r) from Registration r where r.conference.id=?1")
	int numberOfRegistrations(int conferenceId);

	@Query("select c from Conference c where c.startDate > NOW() and c.finalMode='1' and c not in (select r.conference from Registration r where r.author.id=?1)")
	Collection<Conference> findAllForthCommingNotRegistered(int authorId);

	@Query("select c from Conference c where c.submissionDeadline > NOW() and c.finalMode='1' and c not in (select s.conference from Submission s where s.author.id=?1)")
	Collection<Conference> findAllForthCommingNotSubmitted(int authorId);

	@Query("select c from Conference c where c.startDate > NOW() and c.finalMode='1' and c in (select s.conference from Submission s where s.author.id=?1)")
	Collection<Conference> findAllForthCommingSubmitted(int authorId);

	//	@Query("select c from Conference c where c.administrator.id=?1")
	//	Collection<Conference> findAllByAdmin(int adminId);

	@Query("select c from Conference c where c.submissionDeadline <= CURRENT_DATE and c.submissionDeadline in (select c.submissionDeadline from Conference c where c.submissionDeadline >= NOW()-5000000)")
	Collection<Conference> findAllByAdminSDElapsed(Date fiveDaysAgo);
	//TODO: TOMAS
	//	@Query("select c from Conference c where c.administrator.id=?1 and c.notificationDeadline<=?2 and c.notificationDeadline>=NOW()")
	//	Collection<Conference> findAllByAdminNDElapses(int id, Date nextFiveDays);
	//
	//	@Query("select c from Conference c where c.administrator.id=?1 and c.cameraReadyDeadline<=?2 and c.cameraReadyDeadline>=NOW()")
	//	Collection<Conference> findAllByAdminCRDElapses(int id, Date nextFiveDays);
	//
	//	@Query("select c from Conference c where c.administrator.id=?1 and c.startDate<=?2 and c.startDate>=NOW()")
	//	Collection<Conference> findAllByAdminOrganisedSoon(int id, Date nextFiveDays);
}
