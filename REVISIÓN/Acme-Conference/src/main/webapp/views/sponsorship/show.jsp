<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<fieldset>

	<legend align="left">
		<spring:message code="sponsorship.creditcard" />
	</legend>

	<b><spring:message code="sponsorship.holderName"/>: </b>
		<jstl:out value="${sponsorship.holderName}"/>
	<br/>
	
	<b><spring:message code="sponsorship.makeName"/>: </b>
		<jstl:out value="${sponsorship.makeName}"/>
	<br/>
	
	<b><spring:message code="sponsorship.number"/>: </b>
		<jstl:out value="${sponsorship.number}"/>
	<br/>
	
	<b><spring:message code="sponsorship.expirationYear"/>: </b>
		<jstl:out value="${sponsorship.expirationYear}"/>
	<br/>
	
	<b><spring:message code="sponsorship.expirationMonth"/>: </b>
		<jstl:out value="${sponsorship.expirationMonth}"/>
	<br/>
	
	
	<security:authorize access="hasRole('AUTHOR')">
		<b><spring:message code="sponsorship.cvv"/>: </b>
			<jstl:out value="${sponsorship.cvv}"/>
		<br/>
	</security:authorize>
</fieldset>
<br/>
<fieldset>

	<legend align="left">
		<spring:message code="sponsorship.info" />
	</legend>
	
	<b><spring:message code="sponsorship.banner"/>: </b>
		<jstl:out value="${sponsorship.banner}"/>
		<img src="${sponsorship.banner}"> 
	<br/>
	
	<b><spring:message code="sponsorship.targetUrl"/>: </b>
		<jstl:out value="${sponsorship.targetUrl}"/>
	<br/>
	
	
	
	<b><spring:message code="sponsorship.sponsor"/>: </b>
		<jstl:out value="${sponsorship.sponsor.name}"/>
		<jstl:out value="${sponsorship.sponsor.surname}"/>
	<br/>

</fieldset>

<br/><br/>
<input type="button" name="cancel" value="<spring:message code="sponsorship.cancel" />" onclick="javascript:relativeRedir('welcome/index.do');" />


<br/>