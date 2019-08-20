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

<b><spring:message code="submission.ticker"/>: </b>
	<jstl:out value="${submission.ticker}"/>
<br/>

<b><spring:message code="submission.moment"/>: </b>
	<jstl:out value="${submission.moment}"/>
<br/>

<b><spring:message code="submission.status"/>: </b>
	<jstl:if test="${submission.status == 'UNDER-REVIEW'}">
			<spring:message code="submission.under.review"/>
	</jstl:if>
	<jstl:if test="${submission.status == 'ACCEPTED'}">
		<spring:message code="submission.accepted"/>
	</jstl:if>
	<jstl:if test="${submission.status == 'REJECTED'}">
		<spring:message code="submission.rejected"/>
	</jstl:if>
<br/>

<b><spring:message code="submission.author"/>: </b>
	<jstl:out value="${submission.author.name}"/> <jstl:out value="${submission.author.surname}"/>
<br/>

<b><spring:message code="submission.conference"/>: </b>
	<jstl:out value="${submission.conference.title}"/>
<br/>

<b><spring:message code="submission.reviewers"/>: </b>
	<c:forEach items="${submission.reviewers}" var="reviewer">
	<jstl:out value="${reviewer.name}"/> <jstl:out value="${reviewer.surname}"/>
	<br/>
</c:forEach>
<br/>