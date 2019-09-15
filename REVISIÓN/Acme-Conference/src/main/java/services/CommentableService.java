
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentableRepository;
import domain.Commentable;

@Service
@Transactional
public class CommentableService {

	@Autowired
	CommentableRepository	commentableRepository;


	public Commentable findOne(final int id) {
		return this.commentableRepository.findOne(id);
	}

}
