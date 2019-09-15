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
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
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
		sship.setBanner("http://www.some.com");
		sship.setTargetUrl("http://www.other.com");
		sship.setHolderName("Holdername");
		sship.setMakeName("VISA");
		sship.setNumber("1111222233334444");
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
	
	@Test
	public void testDeleteSponsorship() {
		
		super.authenticate("sponsor1");
		Sponsor s =  sponsorS.findByPrincipal();
		
		Collection<Sponsorship> lista = ss.findBySponsor(s.getId());
		Sponsorship delete = lista.iterator().next();
		ss.delete(delete);
		
		Collection<Sponsorship> listaFin = ss.findBySponsor(s.getId());
		
		Assert.isTrue(lista.size() != listaFin.size());
		
		super.authenticate(null);
	}
}
