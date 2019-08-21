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

import domain.Author;
import domain.Reviewer;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ReviewerServiceTest  extends AbstractTest {

	@Autowired
	private ReviewerService rs;
	
	@Test
	public void testSaveReviewer() {
		
		super.authenticate("admin");
		int initialSize = rs.findAll().size();
		
		Reviewer rv = rs.create();
		
		//Create userAccount
		
		rv.setAddress("Any address");
		rv.setEmail("email@es.es");
		rv.setPhoneNumber("666555444");
		rv.setName("Author new");
		rv.setMiddleName("Middle");
		rv.setSurname("Surname");
		rv.setPhoto("photo/url");
		
		Collection<String> expertise = new ArrayList<String>();
		expertise.add("word1");
		expertise.add("word2");
		expertise.add("word3");
		expertise.add("word4");
		rv.setExpertise(expertise);
		
		Reviewer finalRv = rs.save(rv);

		Collection<Reviewer> revws = rs.findAll();
		
		Assert.isTrue(initialSize != revws.size());
		Assert.isTrue(revws.contains(finalRv));
		
		super.authenticate(null);
		
	}
}
