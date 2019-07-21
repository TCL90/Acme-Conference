
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private String	titleIng;
	private String	titleEsp;


	@NotBlank
	@SafeHtml
	public String getTitleIng() {
		return this.titleIng;
	}

	public void setTitleIng(final String titleIng) {
		this.titleIng = titleIng;
	}

	@NotBlank
	@SafeHtml
	public String getTitleEsp() {
		return this.titleEsp;
	}

	public void setTitleEsp(final String titleEsp) {
		this.titleEsp = titleEsp;
	}


	//Relationships
	private Category	parent;


	@Valid
	@ManyToOne(optional = false)
	public Category getParent() {
		return this.parent;
	}

	public void setParent(final Category parent) {
		this.parent = parent;
	}

}
