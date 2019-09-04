
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends Commentable {

	private int		originalityScore;
	private int		qualityScore;
	private int		readabilityScore;
	private String	decision;


	@NotNull
	@Range(min = 0, max = 10)
	public int getOriginalityScore() {
		return this.originalityScore;
	}

	public void setOriginalityScore(final int originalityScore) {
		this.originalityScore = originalityScore;
	}

	@NotNull
	@Range(min = 0, max = 10)
	public int getQualityScore() {
		return this.qualityScore;
	}

	public void setQualityScore(final int qualityScore) {
		this.qualityScore = qualityScore;
	}

	@NotNull
	@Range(min = 0, max = 10)
	public int getReadabilityScore() {
		return this.readabilityScore;
	}

	public void setReadabilityScore(final int readabilityScore) {
		this.readabilityScore = readabilityScore;
	}

	@NotBlank
	@SafeHtml
	@Pattern(regexp = "^" + "BORDER-LINE" + "|" + "ACCEPT" + "|" + "REJECT" + "$")
	public String getDecision() {
		return this.decision;
	}

	public void setDecision(final String decision) {
		this.decision = decision;
	}


	//Relationships
	private Reviewer	reviewer;
	private Submission	submission;


	@Valid
	@ManyToOne(optional = false)
	public Reviewer getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(final Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	@Valid
	@ManyToOne(optional = false)
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}

}
