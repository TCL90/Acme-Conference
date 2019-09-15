
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Author;
import domain.Category;
import domain.Conference;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class ConferenceServiceTest extends AbstractTest {

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private CategoryService		categoryService;


	@Test
	public void testSaveConference() {

		super.authenticate("admin");

		final int size1 = this.conferenceService.findAllFinalMode().size();

		final Conference conference = this.conferenceService.create();
		conference.setAcronym("ASD");
		final Date dat = new Date();
		final Calendar cal = Calendar.getInstance();
		cal.set(2020, 10, 10);
		final Date camera = new Date();

		camera.setYear(2020);
		camera.setMonth(8);
		camera.setDate(11);
		conference.setCameraReadyDeadline(camera);

		final List<Category> cats = (List<Category>) this.categoryService.findAll();
		conference.setCategory(cats.get(0));

		final Date end = new Date();
		end.setYear(2020);
		end.setMonth(8);
		end.setDate(16);
		conference.setEndDate(end);

		conference.setFee(12);
		conference.setFinalMode(true);

		final Date notif = new Date();
		notif.setYear(2020);
		notif.setMonth(8);
		notif.setDate(9);
		conference.setNotificationDeadline(notif);

		final Date start = new Date();
		start.setYear(2020);
		start.setMonth(8);
		start.setDate(13);
		conference.setStartDate(start);

		final Date submission = new Date();
		submission.setYear(2020);
		submission.setMonth(8);
		submission.setDate(8);
		conference.setSubmissionDeadline(submission);

		conference.setSummary("Summary test");
		conference.setTitle("Title test");
		conference.setVenue("Venue test");

		this.conferenceService.save(conference);

		final int size2 = this.conferenceService.findAllFinalMode().size();

		Assert.isTrue(size1 != size2);

		super.authenticate(null);
	}
	
	
	@Test
	public void testDeleteConference() {
		
		super.authenticate("admin");
		
		Collection<Conference> initial = this.conferenceService.findAllNotFinalMode();
		
		boolean encontrado = false;
		Iterator<Conference> it = initial.iterator();
		Conference delete = null;
		while(it.hasNext() && !encontrado) {
			delete = it.next();
			if(delete.getAcronym().equals("CTTD")) {
				break;
			}
		}
		
		this.conferenceService.delete(delete);
		Collection<Conference> finalCs = this.conferenceService.findAllNotFinalMode();
		
		Assert.isTrue(initial.size() != finalCs.size());
		
		super.authenticate(null);
	}
}
