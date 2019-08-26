<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


	<form:form modelAttribute="customisation" action="customisation/administrator/edit.do" >
		<security:authorize access="hasRole('ADMIN')">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:label path="systemName">
			<spring:message code="customisation.systemName" />:
		</form:label>
		<form:input path="systemName" />
		<form:errors cssClass="error" path="systemName" />
		<br />	
		
		<form:label path="bannerUrl">
			<spring:message code="customisation.bannerUrl" />:
		</form:label>
		<form:input path="bannerUrl" />
		<form:errors cssClass="error" path="bannerUrl" />
		<br />	
		
		<form:label path="welcomeMessageEng">
			<spring:message code="customisation.welcomeMessageEng" />:
		</form:label>
		<form:textarea path="welcomeMessageEng" />
		<form:errors cssClass="error" path="welcomeMessageEng" />
		<br />	
		<form:label path="welcomeMessageEsp">
			<spring:message code="customisation.welcomeMessageEsp" />:
		</form:label>
		<form:textarea path="welcomeMessageEsp" />
		<form:errors cssClass="error" path="welcomeMessageEsp" />
		<br />
		
		<form:label path="phoneNumberCode">
			<spring:message code="customisation.phoneNumberCode" />:
		</form:label>
		<form:input path="phoneNumberCode" />
		<form:errors cssClass="error" path="phoneNumberCode" />
		<br />
		
		<form:label path="buzzWordsIng">
			<spring:message code="customisation.buzzWordsIng" />:
		</form:label>
		<form:textarea path="buzzWordsIng" />
		<form:errors cssClass="error" path="buzzWordsIng" />
		<br />	
		
		<form:label path="buzzWordsEsp">
			<spring:message code="customisation.buzzWordsEsp" />:
		</form:label>
		<form:textarea path="buzzWordsEsp" />
		<form:errors cssClass="error" path="buzzWordsEsp" />
		<br />	
		
		<form:label path="topicsIng">
			<spring:message code="customisation.topicsIng" />:
		</form:label>
		<form:textarea path="topicsIng" />
		<form:errors cssClass="error" path="topicsIng" />
		<br />	
		
		<form:label path="topicsEsp">
			<spring:message code="customisation.topicsEsp" />:
		</form:label>
		<form:textarea path="topicsEsp" />
		<form:errors cssClass="error" path="topicsEsp" />
		<br />	
		
		<input type="submit" name="save" value = "<spring:message code ="customisation.save" /> " />
		
		<input type="button" name="cancel" value="<spring:message code ="customisation.cancel" />" onclick="javascript:relativeRedir('welcome/index.do');" />
	
	</security:authorize>
	
	</form:form>