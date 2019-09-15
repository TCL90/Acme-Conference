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


<display:table name="registrations" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="holderName" titleKey="registration.holderName"/>
	<display:column property="makeName" titleKey="registration.makeName"/>
	<display:column property="number" titleKey="registration.number"/>

	<display:column property="conference.title" titleKey="registration.conference"/>

	<display:column>
		<a href="registration/show.do?registrationId=${row.id}" ><spring:message code="registration.show" /></a>
	</display:column>
	
</display:table>

<security:authorize access="hasRole('AUTHOR')">
	<a href="registration/author/create.do" ><spring:message code="registration.create" /></a>
</security:authorize>







