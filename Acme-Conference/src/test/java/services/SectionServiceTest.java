package services;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Conference;
import domain.Section;
import domain.Tutorial;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SectionServiceTest  extends AbstractTest {

	@Autowired
	private SectionService ss;
	
	@Autowired
	private TutorialService ts;
	
	@Autowired
	private ConferenceService cs;
	
	@Autowired
	private ActivityService as;
	
	
	@Test
	public void testSaveSection() {
		
		super.authenticate("admin");
	
		Collection<Conference> confs = cs.findAllByAdmin();
		Conference conf = confs.iterator().next();
		Collection<Activity> acts = as.findAllByConference(conf);
		Activity act = acts.iterator().next();
		Tutorial tutorial = ts.findTutorialByActivityId(act.getId());
		
		Collection<Section> inicialSects = ss.findSectionsByTutorial(tutorial);
		Section section = ss.createSectionByTutorialId(tutorial.getId());
		section.setTitle("title");
		section.setSummary("Summary");
		section.setPictures(Arrays.asList("http://flicker.com/1","http://flicker.com/2"));
		
		Section finalSection = ss.save(section);
		Collection<Section> finalSects = ss.findSectionsByTutorial(tutorial);
		
		Assert.isTrue(inicialSects.size()!= finalSects.size());
		Assert.isTrue(finalSects.contains(finalSection));
		
		super.authenticate(null);
	}
}