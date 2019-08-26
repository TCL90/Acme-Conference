package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Author;
import domain.Conference;
import domain.Paper;
import domain.Submission;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SubmissionServiceTest  extends AbstractTest {

	@Autowired
	private SubmissionService ss;
	
	@Autowired
	private AuthorService as; 
	
	@Autowired
	private ConferenceService cs;
	
	@Autowired
	private PaperService paperService;
	
	@Test
	public void testSaveSubmission() {
		
		super.authenticate("author1");
		Author autor = as.findByPrincipal();
		
		Collection<Conference> confs = cs.findAllForthComming();
		
		Collection<Submission> initialSubs = ss.findByAuthor(autor.getId());
		
		final Submission res = new Submission();
		res.setAuthor(autor);
		res.setStatus("Status");
		res.setTicker("Ticker");
		res.setMoment(new Date());
		res.setConference(confs.iterator().next());

		final Paper pap = new Paper();

		pap.setAuthors(Arrays.asList("author1", "Author2"));
		pap.setDocument("Some document");
		pap.setSummary("Summary");
		pap.setTitle("Title");

		final Paper pap2 = this.paperService.save(pap);
		res.setPaper(pap2);

		Submission finalSub = ss.save(res);
	
		Collection<Submission> finalSubs = ss.findByAuthor(autor.getId());
		
		Assert.isTrue(initialSubs.size()!= finalSubs.size());
		Assert.isTrue(finalSubs.contains(finalSub));
		
		super.authenticate(null);
	}
}
