package services;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Author;
import domain.Comment;
import domain.Conference;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CommentServiceTest  extends AbstractTest {

	@Autowired
	private ConferenceService confService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired 
	private AuthorService as;

	@Test
	public void testSaveComment() {
		
		super.authenticate("author1");
		
		int conferenceId = 0;
		Iterator<Conference> itConf = confService.findAllByAdmin().iterator();
		Conference c = itConf.next();
		
		Collection<Comment> initialComments = commentService.findByConference(c);
		
		Comment comment = commentService.createCommentByCommentableId(c.getId(), "conference");
		
		Author ator = as.findByPrincipal();
		comment.setAuthor(ator);
		
		comment.setText("Text of the comment");
		comment.setTitle("Comment");
		
		final Date moment = new Date();
		moment.setYear(2020);
		moment.setMonth(8);
		moment.setDate(16);
		comment.setMoment(moment);
		
		Comment finalC = commentService.save(comment);
		Collection<Comment> finalComments = commentService.findByConference(c);
		
		Assert.isTrue(finalComments.contains(finalC));
		Assert.isTrue(initialComments.size() != finalComments.size());
		
		super.authenticate(null);
	}
}
