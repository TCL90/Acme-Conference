
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Activity extends Commentable {

	private String			title;
	private List<String>	speakers;
	private Date			startMoment;
	private Date			duration;
	private String			room;
	private String			summary;
	private List<String>	attachments;
	private String			cameraReadyVersion;
	private String			activityType;


	@NotBlank
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@ElementCollection(targetClass = String.class)
	public List<String> getSpeakers() {
		return this.speakers;
	}

	public void setSpeakers(final List<String> speakers) {
		this.speakers = speakers;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartMoment() {
		return this.startMoment;
	}

	public void setStartMoment(final Date startMoment) {
		this.startMoment = startMoment;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "HH:mm:ss")
	public Date getDuration() {
		return this.duration;
	}

	public void setDuration(final Date duration) {
		this.duration = duration;
	}

	@NotBlank
	@SafeHtml
	public String getRoom() {
		return this.room;
	}

	public void setRoom(final String room) {
		this.room = room;
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
	@ElementCollection(targetClass = String.class)
	public List<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final List<String> attachments) {
		this.attachments = attachments;
	}

	@URL
	@SafeHtml
	public String getCameraReadyVersion() {
		return this.cameraReadyVersion;
	}

	public void setCameraReadyVersion(final String cameraReadyVersion) {
		this.cameraReadyVersion = cameraReadyVersion;
	}

	@NotBlank
	@SafeHtml
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(final String activityType) {
		this.activityType = activityType;
	}


	//Relationships
	private Conference	conference;


	@Valid
	@ManyToOne(optional = false)
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

}
