package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Author;
import domain.Conference;
import domain.Registration;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RegistrationServiceTest  extends AbstractTest {
	
	@Autowired
	private RegistrationService rs;
	
	@Autowired
	private ActorService as;
	
	@Autowired
	private ConferenceService cs;

	@Test
	public void testSaveRegistration() {
		
		super.authenticate("author1");
		
		Author autor = (Author) as.findByPrincipal();
		Collection<Conference> confs = cs.findAllForthCommingNotRegistered(autor.getId());
		
		Collection<Registration> resin = rs.findByAuthor(autor.getId());
		
		Registration r = rs.create();
		r.setHolderName("Holdername");
		r.setMakeName("Makename");
		r.setNumber("ES8200000000000000");
		r.setExpirationYear(2020);
		r.setExpirationMonth(4);
		r.setCvv(123);
		r.setConference(confs.iterator().next());
		
		Registration rr = rs.save(r);
		Collection<Registration> resfin = rs.findByAuthor(autor.getId());
		
		Assert.isTrue(resfin.contains(rr));
		Assert.isTrue(resin.size() != resfin.size());
		
		super.authenticate(null);
	}

}
