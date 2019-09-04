package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Author;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml", "classpath:spring/junit.xml"
})
@Transactional
public class AdministratorServiceTest  extends AbstractTest {

	@Autowired
	private AdministratorService as;
	
	@Test
	public void testSaveAdministrator() {
		
		super.authenticate("admin");
		List<Administrator> initia = as.findAll();
		
		Administrator admin = as.create();
		
		admin.setAddress("Any address");
		admin.setEmail("email@es.es");
		admin.setPhoneNumber("666555444");
		admin.setName("Admin new");
		admin.setMiddleName("Middle");
		admin.setSurname("Surname");
		admin.setPhoto("http://wwww.whereiam.com/photo");
		
		Administrator finalAdmin = as.save(admin);

		List<Administrator> admins = as.findAll();
		
		Assert.isTrue(initia.size() != admins.size());
		Assert.isTrue(admins.contains(finalAdmin));
		
		super.authenticate(null);
		
	}
}
