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

import domain.Conference;
import domain.Panel;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class PanelServiceTest  extends AbstractTest {

	@Autowired
	private PanelService ps;
	
	@Autowired
	private ConferenceService cs;
	
	
	@Test
	public void testSavePanel() {
		
		super.authenticate("admin");
		Panel p ;
		Collection<Conference> confs = cs.findAllNotFinalMode();
		Conference conf = confs.iterator().next();
		
		Collection<String> stringList = new ArrayList<String>();
		stringList.add("word1");
		stringList.add("word2");
		stringList.add("word3");
		stringList.add("word4");
		
		p = ps.createPanelByConferenceId(conf.getId());
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
		
		Panel finalP = ps.save(p);
	
		Assert.isTrue(finalP != null && finalP.getId() != 0 );
		
		super.authenticate(null);
		
		
	}
}
