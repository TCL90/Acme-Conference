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
		<spring:message code="registration.creditcard" />
	</legend>

	<b><spring:message code="registration.holderName"/>: </b>
		<jstl:out value="${registration.holderName}"/>
	<br/>
	
	<b><spring:message code="registration.makeName"/>: </b>
		<jstl:out value="${registration.makeName}"/>
	<br/>
	
	<b><spring:message code="registration.number"/>: </b>
		<jstl:out value="${registration.number}"/>
	<br/>
	
	<b><spring:message code="registration.expirationYear"/>: </b>
		<jstl:out value="${registration.expirationYear}"/>
	<br/>
	
	<b><spring:message code="registration.expirationMonth"/>: </b>
		<jstl:out value="${registration.expirationMonth}"/>
	<br/>
	
	
	<security:authorize access="hasRole('AUTHOR')">
		<b><spring:message code="registration.cvv"/>: </b>
			<jstl:out value="${registration.cvv}"/>
		<br/>
	</security:authorize>
</fieldset>
<br/>
<fieldset>

	<legend align="left">
		<spring:message code="registration.info" />
	</legend>
	
	<b><spring:message code="registration.conference"/>: </b>
		<jstl:out value="${registration.conference.title}"/>
	<br/>
	
	<b><spring:message code="registration.author"/>: </b>
		<jstl:out value="${registration.author.name}"/>
		<jstl:out value="${registration.author.surname}"/>
	<br/>

</fieldset>

<br/><br/>
<input type="button" name="cancel" value="<spring:message code="registration.cancel" />" onclick="javascript:relativeRedir('welcome/index.do');" />


<br/>