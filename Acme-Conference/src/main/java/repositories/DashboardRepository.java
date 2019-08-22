
package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Administrator;

public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	@Query("select avg(1.0*(select count(s) from Submission s where s.conference.id =c.id group by s.conference)) from Conference c")
	Double avgSubmissions();

	@Query("select min(1*(select count(s) from Submission s where s.conference.id =c.id group by s.conference)) from Conference c")
	Integer minSubmissions();

	@Query("select max(1*(select count(s) from Submission s where s.conference.id =c.id group by s.conference)) from Conference c")
	Integer maxSubmissions();

	@Query("select stddev(1.0*(select count(s) from Submission s where s.conference.id =c.id group by s.conference)) from Conference c")
	Double stddevSubmissions();

	@Query("select avg(1.0*(select count(r) from Registration r where r.conference.id =c.id group by r.conference)) from Conference c")
	Double avgRegistrations();

	@Query("select min(1*(select count(r) from Registration r where r.conference.id =c.id group by r.conference)) from Conference c")
	Integer minRegistrations();

	@Query("select max(1*(select count(r) from Registration r where r.conference.id =c.id group by r.conference)) from Conference c")
	Integer maxRegistrations();

	@Query("select stddev(1.0*(select count(r) from Registration r where r.conference.id =c.id group by r.conference)) from Conference c")
	Double stddevRegistrations();

	@Query("select avg(c.fee) from Conference c")
	Double avgFee();

	@Query("select min(c.fee) from Conference c")
	Integer minFee();

	@Query("select max(c.fee) from Conference c")
	Integer maxFee();

	@Query("select stddev(c.fee) from Conference c")
	Double stddevFee();

	//Días en servicio
	@Query("select c.startDate from Conference c")
	List<Date> startDates();

	@Query("select c.endDate from Conference c")
	List<Date> endDates();

	@Query("select avg(1.0*(select count(c) from Conference c where c.category.id = cat.id group by c.category)) from Category cat")
	Double avgCategory();

	@Query("select min(1*(select count(c) from Conference c where c.category.id = cat.id group by c.category)) from Category cat")
	Integer minCategory();

	@Query("select max(1*(select count(c) from Conference c where c.category.id = cat.id group by c.category)) from Category cat")
	Integer maxCategory();

	@Query("select stddev(1.0*(select count(c) from Conference c where c.category.id = cat.id group by c.category)) from Category cat")
	Double stddevCategory();

	@Query("select avg(1.0*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Conference a")
	Double avgCommentsConf();

	@Query("select min(1*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Conference a")
	Integer minCommentsConf();

	@Query("select max(1*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Conference a")
	Integer maxCommentsConf();

	@Query("select stddev(1.0*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Conference a")
	Double stddevCommentsConf();

	@Query("select avg(1.0*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Activity a")
	Double avgCommentsAct();

	@Query("select min(1*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Activity a")
	Integer minCommentsAct();

	@Query("select max(1*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Activity a")
	Integer maxCommentsAct();

	@Query("select stddev(1.0*(select count(c) from Comment c where c.commentable.id = a.id group by c.commentable)) from Activity a")
	Double stddevCommentsAct();
}
