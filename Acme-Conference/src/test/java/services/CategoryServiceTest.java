package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Category;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CategoryServiceTest  extends AbstractTest {

	@Autowired
	private CategoryService cs;
	

	@Test
	public void testSaveCategory() {
		
		super.authenticate("admin");
		Category cat = new Category();
		
		cat.setTitleEsp("Titulo en español");
		cat.setTitleIng("Inglish title");
		
//		Category catfinal = cs.
	}
}
