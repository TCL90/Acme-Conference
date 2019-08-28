package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Message;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class MessageServiceTest  extends AbstractTest {

	@Autowired
	private MessageService ms;
	
	@Autowired
	private SponsorService ss;
	
	@Test
	public void testSaveMessage() {
		
		super.authenticate("author1");
		
		ss.findAll();
		Message m = ms.create();
	}
}
