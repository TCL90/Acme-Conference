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
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest  extends AbstractTest {

	@Autowired
	private AdministratorService as;
	
	@Test
	public void testSaveAdministrator() {
		
//		super.authenticate("admin");
//		int initialSize = as.findAll().size();
//		
//		Author autor = as.create();
//		
//		autor.setAddress("Any address");
//		autor.setEmail("email@es.es");
//		autor.setPhoneNumber("666555444");
//		autor.setName("Author new");
//		autor.setMiddleName("Middle");
//		autor.setSurname("Surname");
//		autor.setPhoto("photo/url");
//		autor.setScore(0.0);
////		autor.set
//		
//		Author finalAuthor = as.save(autor);
//
//		Collection<Author> authors = as.findAll();
//		
//		Assert.isTrue(initialSize != authors.size());
//		Assert.isTrue(authors.contains(finalAuthor));
//		
//		super.authenticate(null);
		
	}
}
