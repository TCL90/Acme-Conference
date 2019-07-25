
package domain;

import java.util.List;

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
	private String			systemName;
	private String			bannerUrl;
	private String			welcomeMessageEng;
	private String			welcomeMessageEsp;
	private String			phoneNumberCode;
	private List<String>	buzzWordsIng;
	private List<String>	buzzWordsEsp;
	private List<String>	topicsIng;
	private List<String>	topicsEsp;

	private List<String>	creditCardMakes;


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
	public List<String> getBuzzWordsIng() {
		return this.buzzWordsIng;
	}

	public void setBuzzWordsIng(final List<String> buzzWordsIng) {
		this.buzzWordsIng = buzzWordsIng;
	}

	@ElementCollection(targetClass = String.class)
	public List<String> getBuzzWordsEsp() {
		return this.buzzWordsEsp;
	}

	public void setBuzzWordsEsp(final List<String> buzzWordsEsp) {
		this.buzzWordsEsp = buzzWordsEsp;
	}

	@ElementCollection(targetClass = String.class)
	public List<String> getTopicsIng() {
		return this.topicsIng;
	}

	public void setTopicsIng(final List<String> topicsIng) {
		this.topicsIng = topicsIng;
	}

	@ElementCollection(targetClass = String.class)
	public List<String> getTopicsEsp() {
		return this.topicsEsp;
	}

	public void setTopicsEsp(final List<String> topicsEsp) {
		this.topicsEsp = topicsEsp;
	}

	@ElementCollection(targetClass = String.class)
	public List<String> getCreditCardMakes() {
		return this.creditCardMakes;
	}

	public void setCreditCardMakes(final List<String> creditCardMakes) {
		this.creditCardMakes = creditCardMakes;
	}

}
