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

import domain.Customisation;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomisationServiceTest  extends AbstractTest {

	@Autowired
	private CustomisationService cs;
	
	@Test
	public void testSaveCustomisation() {
		
		Customisation custom = cs.create();
		
		Collection<Customisation> initCust = cs.findAll();
		
		Collection<String> stringList = new ArrayList<String>();
		stringList.add("word1");
		stringList.add("word2");
		stringList.add("word3");
		stringList.add("word4");
		
		custom.setBannerUrl("BannerUrl");
		custom.setBuzzWordsEsp(stringList);
		custom.setBuzzWordsIng(stringList);
		custom.setCreditCardMakes(stringList);
		custom.setPhoneNumberCode("34");
		custom.setSystemName("SystemName");
		custom.setTopicsEsp(stringList);
		custom.setTopicsIng(stringList);
		custom.setWelcomeMessageEng("WelcomMessEng");
		custom.setWelcomeMessageEsp("welcomMEssEsp");
		
		Customisation cFinal = cs.save(custom);
		Collection<Customisation> finalCs = cs.findAll();
		
		Assert.isTrue(finalCs.contains(cFinal));
		Assert.isTrue(initCust.size()!= finalCs.size());
		
	}
}
