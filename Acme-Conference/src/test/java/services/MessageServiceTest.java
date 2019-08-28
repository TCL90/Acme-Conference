package services;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Message;
import domain.Sponsor;
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
	private ActorService as;
	
	@Autowired
	private BoxService bs;
	
	@Test
	public void testSaveMessage() {
		
		super.authenticate("author1");
		
		Actor s = as.findAll().iterator().next();
		final Actor me = this.as.findByPrincipal();
		
		Message m = ms.create();
		m.setSubject("Subject message");
		m.setTopic("Topic");
		m.setBody("Message body");
		m.setBroadcast(false);
		m.setRecipients(Arrays.asList(s));
		
		Message m2 = bs.sendMessage(m);
		
		Assert.isTrue(m2.getId() != m.getId());
	}
}
