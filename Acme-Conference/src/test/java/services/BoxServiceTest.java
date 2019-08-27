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
		Collection<Box> boxes = bs.createBoxesForNewActor();
		
		Iterator<Author> autors = as.findAll().iterator();
		Author autor = autors.next();
		
		Collection<Box> initialBoxes = autor.getBoxes();
		
		autor.getBoxes().addAll(boxes);
		for (Box box : autor.getBoxes()) {
			bs.save(box);
		}
		
		Author a2 = as.save(autor);
		Collection<Box> finalBoxes = a2.getBoxes();
		
		Assert.isTrue(finalBoxes.size() != initialBoxes.size());
		super.authenticate(null);
	}
}
