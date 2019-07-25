
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Commentable extends DomainEntity {

	private List<Comment>	comments;


	@Valid
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final List<Comment> comments) {
		this.comments = comments;
	}

}
