package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Category;
import domain.Finder;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class FinderServiceTest  extends AbstractTest {

	@Autowired
	private FinderService fs;
	
	@Autowired 
	private CategoryService cs;
	
	@Test
	public void testSaveFinder() {
		
		super.authenticate("author1");
		
		Finder f = fs.getFinder();
		final int confs = f.getConferences().size(); 
		
		List<Category> cats = cs.findAll();
		
		Category cat = cats.get(0);
		if(cat.equals(f.getCategory())) {
			cat = cats.get(1);
		}
		f.setCategory(cat);
		Finder fin = fs.save(f);
		final int confsFin = fin.getConferences().size();
		Assert.isTrue(confs != confsFin);
		
		super.authenticate(null);
	}
}
