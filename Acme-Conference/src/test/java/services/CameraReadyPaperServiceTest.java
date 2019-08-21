package services;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.CameraReadyPaper;
import domain.Paper;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CameraReadyPaperServiceTest  extends AbstractTest {

	@Autowired 
	private CameraReadyPaperService crps;

	@Test
	public void testSaveCameraReadyPaper() {
		
		super.authenticate("admin");
		
		
		
		CameraReadyPaper pap = new CameraReadyPaper();
		
		pap.setAuthors(Arrays.asList("author1", "Author2"));
		pap.setDocument("Some document");
		pap.setSummary("Summary");
		pap.setTitle("Title");
	
		CameraReadyPaper pap2 = crps.save(pap);
		
		Assert.isTrue(pap2.getId() != 0);
		
		super.authenticate(null);
		
	}
}
