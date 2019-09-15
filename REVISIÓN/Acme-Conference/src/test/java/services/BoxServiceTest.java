package services;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Author;
import domain.Box;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class BoxServiceTest  extends AbstractTest {

	@Autowired
	private AuthorService as;
	
	@Autowired
	private BoxService bs;
	
	@Test
	public void testSaveBox() {
		
		super.authenticate("admin");
		
		
		Author autor = as.create();
		
		autor.setAddress("Any address");
		autor.setEmail("email@es.es");
		autor.setPhoneNumber("666555444");
		autor.setName("Author new");
		autor.setMiddleName("Middle");
		autor.setSurname("Surname");
		autor.setPhoto("http://www.flicker.es/url");
		autor.setScore(0.0);
		
		//Dentro del metodo save del autor se crean las boxes
		Author finalAuthor = as.save(autor);
		as.save(finalAuthor);
		Collection<Box> finalBoxes = finalAuthor.getBoxes();
		
		Assert.isTrue(finalBoxes.size() == 3);
		super.authenticate(null);
	}
}
