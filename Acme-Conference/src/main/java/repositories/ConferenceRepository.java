
package repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Category;
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

	@Query("select c from Conference c where (c.title like %:keyword% or c.venue like %:keyword% or c.acronym like %:keyword% or c.summary like %:keyword%)")
	Collection<Conference> findAllKeywordAdmin(@Param("keyword") String keyword);

	@Query("select count(r) from Registration r where r.conference.id=?1")
	int numberOfRegistrations(int conferenceId);

	//FINDER
	@Query("select c from Conference c where (c.title like %?1% or c.acronym like %?1% or c.venue like %?1% or c.summary like %?1%) and c.finalMode=1")
	List<Conference> finderKeyword(String keyword);

	@Query("select c from Conference c where ?1 >= c.startDate and c.finalMode = 1")
	List<Conference> finderStartDate(Date startDate);

	@Query("select c from Conference c where ?1 <= c.endDate and c.finalMode =1")
	List<Conference> finderEndDate(Date endDate);

	@Query("select c from Category c where c.titleIng =?1 or c.titleEsp=?1")
	Category categoryByTitle(String cat);

	@Query("select c from Conference c where c = ?1 and c.finalMode=1")
	List<Conference> finderCategory(Category category);

	@Query("select c from Conference c where c.fee <= ?1")
	List<Conference> finderFee(Integer fee);

	@Query("select c from Conference c where c.startDate > NOW() and c.finalMode='1' and c not in (select r.conference from Registration r where r.author.id=?1)")
	Collection<Conference> findAllForthCommingNotRegistered(int authorId);

	@Query("select c from Conference c where c.submissionDeadline > NOW() and c.finalMode='1' and c not in (select s.conference from Submission s where s.author.id=?1)")
	Collection<Conference> findAllForthCommingNotSubmitted(int authorId);

	@Query("select c from Conference c where c.startDate > NOW() and c.finalMode='1' and c in (select s.conference from Submission s where s.author.id=?1)")
	Collection<Conference> findAllForthCommingSubmitted(int authorId);

	//	@Query("select c from Conference c where c.administrator.id=?1")
	//	Collection<Conference> findAllByAdmin(int adminId);

	@Query("select c from Conference c where c.submissionDeadline between :fiveDaysAgo and NOW()")
	Collection<Conference> findAllByAdminSDElapsed(@Param("fiveDaysAgo") Date fiveDaysAgo);

	@Query("select c from Conference c where c.notificationDeadline between NOW() and :nextFiveDays")
	Collection<Conference> findAllByAdminNDElapses(@Param("nextFiveDays") Date nextFiveDays);

	@Query("select c from Conference c where c.cameraReadyDeadline between NOW() and :nextFiveDays")
	Collection<Conference> findAllByAdminCRDElapses(@Param("nextFiveDays") Date nextFiveDays);

	@Query("select c from Conference c where c.startDate between NOW() and :nextFiveDays")
	Collection<Conference> findAllByAdminOrganisedSoon(@Param("nextFiveDays") Date nextFiveDays);

	@Query("select c from Conference c where c.endDate < NOW()")
	Collection<Conference> findAllPastAdministrator();

	@Query("select c from Conference c where c.startDate > NOW()")
	Collection<Conference> findAllForthCommingAdministrator();

	@Query("select c from Conference c where c.startDate <= NOW() and c.endDate >= NOW()")
	Collection<Conference> findAllRunningAdministrator();

	@Query("select c.title from Conference c")
	List<String> findTitles();

	@Query("select c.summary from Conference c")
	List<String> findSummaries();

}
