
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private Date				moment;
	private String				topic;
	private String				body;
	private String				subject;
	private boolean				broadcast;

	private Actor				sender;
	private Actor				recipient;


	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotBlank
	@SafeHtml
	public String getBody() {
		return this.body;
	}
	public void setBody(final String body) {
		this.body = body;
	}

	@NotBlank
	@SafeHtml
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public boolean isBroadcast() {
		return this.broadcast;
	}

	public void setBroadcast(final boolean broadcast) {
		this.broadcast = broadcast;
	}

	@Valid
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}
	public void setSender(final Actor sender) {
		this.sender = sender;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Actor getRecipient() {
		return this.recipient;
	}
	public void setRecipient(final Actor recipient) {
		this.recipient = recipient;
	}

	@SafeHtml
	@NotBlank
	public String getTopic() {
		return this.topic;
	}

	public void setTopic(final String topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "messageerror";
	}

}
