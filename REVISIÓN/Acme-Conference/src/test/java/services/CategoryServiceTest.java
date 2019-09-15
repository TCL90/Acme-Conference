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
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class CategoryServiceTest  extends AbstractTest {

	@Autowired
	private CategoryService cs;
	

	@Test
	public void testSaveCategory() {
		
		super.authenticate("admin");
		
		List<Category> initialCs = cs.findAll();
		Category cat = cs.create();
		
		cat.setTitleEsp("Titulo en español");
		cat.setTitleIng("Inglish title");
		cat.setParent(initialCs.get(0));
		Category cat2 = cs.save(cat);
		
		List<Category> finalCs = cs.findAll();
		
		Assert.isTrue(finalCs.size()!= initialCs.size());
		Assert.isTrue(finalCs.contains(cat2));
		
		super.authenticate(null);
	}
	
	@Test
	public void testDeleteCategory() {
		
		super.authenticate("admin");
		
		List<Category> initialCs = cs.findAllEmpty();
		
		Category cat = initialCs.get(0);
		cs.delete(cat);
		
		List<Category> finalCs = cs.findAllEmpty();
		Assert.isTrue(initialCs.size() != finalCs.size());
		Assert.isTrue(!finalCs.contains(cat));
		
		super.authenticate(null);
	}
}
