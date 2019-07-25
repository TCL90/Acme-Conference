
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	private String		keyword;
	private Date		startDate;
	private Date		endDate;
	private int			maximumFee;
	private Category	category;


	@SafeHtml
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@Min(0)
	public int getMaximumFee() {
		return this.maximumFee;
	}

	public void setMaximumFee(final int maximumFee) {
		this.maximumFee = maximumFee;
	}

	@Valid
	@ManyToOne(optional = true)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}


	//Relationships
	private Collection<Conference>	conferences;


	@ManyToMany
	public Collection<Conference> getConferences() {
		return this.conferences;
	}

	public void setConferences(final Collection<Conference> conferences) {
		this.conferences = conferences;
	}

}
