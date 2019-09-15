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
		
		Collection<Submission> x = ss.findAllWithoutCRP();
		Submission sub = x.iterator().next();
		
		pap.setAuthors(Arrays.asList("author1", "Author2"));
		pap.setDocument("http://www.flicker.es/url");
		pap.setSummary("Summary");
		pap.setTitle("Title");
		
		pap.setSubmission(sub);
	
		CameraReadyPaper pap2 = crps.save(pap);
		Collection<Submission> xfin = ss.findAllWithoutCRP();
		
		Assert.isTrue(x.size() != xfin.size());
		
		super.authenticate(null);
		
	}
	
}
