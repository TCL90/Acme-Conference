package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Sponsor;
import domain.Sponsorship;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorshipServiceTest  extends AbstractTest {

	@Autowired
	private SponsorshipService ss;
	
	@Autowired
	private SponsorService sponsorS;

	@Test
	public void testSaveSponsorship() {
		
		super.authenticate("sponsor1");
		
		Sponsor spon = sponsorS.findByPrincipal();
		Collection<Sponsorship> ssini = ss.findBySponsor(spon.getId());
		
		Sponsorship sship = ss.create();
		sship.setBanner("banner");
		sship.setTargetUrl("url");
		sship.setHolderName("Holdername");
		sship.setMakeName("Makename");
		sship.setNumber("ES8200000000000000");
		sship.setExpirationYear(2020);
		sship.setExpirationMonth(4);
		sship.setCvv(123);
		sship.setSponsor(spon);
		
		Sponsorship sshipfin = ss.save(sship);
		Collection<Sponsorship> ssfin = ss.findBySponsor(spon.getId());
		
		Assert.isTrue(ssfin.contains(sshipfin));
		Assert.isTrue(ssfin.size() != ssini.size());
		
		super.authenticate(null);
	
	
	}
}
