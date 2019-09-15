package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Reviewer;
import domain.Sponsor;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class SponsorServiceTest  extends AbstractTest {

	@Autowired
	private SponsorService ss;
	
	@Test
	public void testSaveSponsor() {
		
		super.authenticate("admin");
		int initialSize = ss.findAll().size();
		
		Sponsor sponsor = ss.create();
		
		//Create userAccount
		
		sponsor.setAddress("Any address");
		sponsor.setEmail("email@es.es");
		sponsor.setPhoneNumber("666555444");
		sponsor.setName("Author new");
		sponsor.setMiddleName("Middle");
		sponsor.setSurname("Surname");
		
		Sponsor finalSpon = ss.save(sponsor);

		Collection<Sponsor> sponsors = ss.findAll();
		
		Assert.isTrue(initialSize != sponsors.size());
		Assert.isTrue(sponsors.contains(finalSpon));
		
		super.authenticate(null);
		
	}
}
