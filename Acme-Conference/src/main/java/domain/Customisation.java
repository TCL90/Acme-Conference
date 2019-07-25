
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Customisation extends DomainEntity {

	//ATRIBUTOS DEL SISTEMA.
	private String				systemName;
	private String				bannerUrl;
	private String				welcomeMessageEng;
	private String				welcomeMessageEsp;
	private String				phoneNumberCode;
	private Collection<String>	buzzWordsIng;
	private Collection<String>	buzzWordsEsp;
	private Collection<String>	topicsIng;
	private Collection<String>	topicsEsp;

	private Collection<String>	creditCardMakes;


	@NotBlank
	@SafeHtml
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}

	@NotBlank
	@URL
	@SafeHtml
	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(final String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	@NotBlank
	@SafeHtml
	public String getWelcomeMessageEng() {
		return this.welcomeMessageEng;
	}

	public void setWelcomeMessageEng(final String welcomeMessageEng) {
		this.welcomeMessageEng = welcomeMessageEng;
	}
	@NotBlank
	@SafeHtml
	public String getWelcomeMessageEsp() {
		return this.welcomeMessageEsp;
	}

	public void setWelcomeMessageEsp(final String welcomeMessageEsp) {
		this.welcomeMessageEsp = welcomeMessageEsp;
	}
	@NotBlank
	@SafeHtml
	public String getPhoneNumberCode() {
		return this.phoneNumberCode;
	}

	public void setPhoneNumberCode(final String phoneNumberCode) {
		this.phoneNumberCode = phoneNumberCode;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getBuzzWordsIng() {
		return this.buzzWordsIng;
	}

	public void setBuzzWordsIng(final Collection<String> buzzWordsIng) {
		this.buzzWordsIng = buzzWordsIng;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getBuzzWordsEsp() {
		return this.buzzWordsEsp;
	}

	public void setBuzzWordsEsp(final Collection<String> buzzWordsEsp) {
		this.buzzWordsEsp = buzzWordsEsp;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getTopicsIng() {
		return this.topicsIng;
	}

	public void setTopicsIng(final Collection<String> topicsIng) {
		this.topicsIng = topicsIng;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getTopicsEsp() {
		return this.topicsEsp;
	}

	public void setTopicsEsp(final Collection<String> topicsEsp) {
		this.topicsEsp = topicsEsp;
	}

	@ElementCollection(targetClass = String.class)
	public Collection<String> getCreditCardMakes() {
		return this.creditCardMakes;
	}

	public void setCreditCardMakes(final Collection<String> creditCardMakes) {
		this.creditCardMakes = creditCardMakes;
	}

}
