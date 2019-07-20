
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Author extends Actor {

	private double	score;


	@NotNull
	@Range(min = 0, max = 10)
	public double getScore() {
		return this.score;
	}

	public void setScore(final double score) {
		this.score = score;
	}


	//Relationships
	private Collection<Conference>	conference;
	private Finder					finder;


	@ManyToMany
	public Collection<Conference> getConference() {
		return this.conference;
	}

	public void setConference(final Collection<Conference> conference) {
		this.conference = conference;
	}
	@Valid
	@OneToOne(optional = true)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

}
