
package forms;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Author;
import domain.CameraReadyPaper;
import domain.Conference;
import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class SubmissionForm extends DomainEntity {

	private String				ticker;
	private Date				moment;
	private String				status;

	private String				title;
	private Collection<String>	authors;
	private String				summary;
	private String				document;


	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-z]{3}-[A-Z0-9_]{4}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "^" + "ACCEPTED" + "|" + "UNDER-REVIEW" + "|" + "REJECTED" + "$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	//Relationships
	private Author				author;
	private domain.Paper		paper;
	private CameraReadyPaper	cameraReadyPaper;
	private Conference			conference;


	@Valid
	@ManyToOne(optional = false)
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@Valid
	@OneToOne(optional = false)
	public domain.Paper getPaper() {
		return this.paper;
	}

	public void setPaper(final domain.Paper paper) {
		this.paper = paper;
	}

	@Valid
	@OneToOne(optional = true)
	public CameraReadyPaper getCameraReadyPaper() {
		return this.cameraReadyPaper;
	}

	public void setCameraReadyPaper(final CameraReadyPaper cameraReadyPaper) {
		this.cameraReadyPaper = cameraReadyPaper;
	}

	@Valid
	@ManyToOne(optional = false)
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotEmpty
	@ElementCollection
	public Collection<String> getAuthors() {
		return this.authors;
	}

	public void setAuthors(final Collection<String> authors) {
		this.authors = authors;
	}

	@NotBlank
	@SafeHtml
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	@SafeHtml
	@URL
	public String getDocument() {
		return this.document;
	}

	public void setDocument(final String document) {
		this.document = document;
	}

}
