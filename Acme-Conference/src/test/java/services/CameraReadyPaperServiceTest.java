package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Activity;
import domain.CameraReadyPaper;
import domain.Conference;
import domain.Presentation;
import domain.Submission;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class CameraReadyPaperServiceTest  extends AbstractTest {

	@Autowired 
	private CameraReadyPaperService crps;
	
	@Autowired
	private SubmissionService ss;
	
	@Autowired
	private PresentationService ps;
	
	@Autowired 
	private ConferenceService cs;

	@Test
	public void testSaveCameraReadyPaper() {
		
		super.authenticate("admin");
		
		
		
		CameraReadyPaper pap = new CameraReadyPaper();
		
		Collection<Submission> x = ss.findAll();
		Submission sub = x.iterator().next();
		
		pap.setAuthors(Arrays.asList("author1", "Author2"));
		pap.setDocument("http://www.flicker.es/url");
		pap.setSummary("Summary");
		pap.setTitle("Title");
		
		Presentation p = createPresentation(pap);	
		
		pap.setSubmission(sub);
	
		CameraReadyPaper pap2 = crps.save(pap);
		
		Assert.isTrue(pap2.getId() != 0);
		
		super.authenticate(null);
		
	}

	private Presentation createPresentation(CameraReadyPaper cam) {
		Presentation p ;
		Collection<Conference> confs = cs.findAllForthComming();
		Conference conf = confs.iterator().next();
		
		Collection<String> stringList = new ArrayList<String>();
		stringList.add("word1");
		stringList.add("word2");
		stringList.add("word3");
		stringList.add("word4");
		
		p = ps.createPresentationByConferenceId(conf.getId(), cam.getId());
		p.setAttachments(stringList);
		p.setDuration(3);
		p.setTitle("title");
		p.setSpeakers(Arrays.asList("Author1", "Someone1", "Speaker1"));
		
		final Date startDate = new Date();
		startDate.setYear(2020);
		startDate.setMonth(8);
		startDate.setDate(16);
		p.setStartMoment(startDate);
		
		final Date someDate = new Date();
		someDate.setYear(2020);
		someDate.setMonth(8);
		someDate.setDate(16);
		p.setSchedule(someDate);
		
		p.setRoom("First class");
		p.setSummary("Panel summary");
		
		return ps.save(p);
	}
}
