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

<security:authorize access="hasRole('ADMIN')">
	<spring:message code="dashboard.avgSubmissions"/>
	<jstl:out value="${avgSubmissions}"/>
	<br/>
	<spring:message code="dashboard.minSubmissions"/>
	<jstl:out value="${minSubmissions}"/>
	<br/>
	<spring:message code="dashboard.maxSubmissions"/>
	<jstl:out value="${maxSubmissions}"/>
	<br/>
	<spring:message code="dashboard.stddevSubmissions"/>
	<jstl:out value="${stddevSubmissions}"/>
	<br/>
	<spring:message code="dashboard.avgRegistrations"/>
	<jstl:out value="${avgRegistrations}"/>
	<br/>
	<spring:message code="dashboard.minRegistrations"/>
	<jstl:out value="${minRegistrations}"/>
	<br/>
	<spring:message code="dashboard.maxRegistrations"/>
	<jstl:out value="${maxRegistrations}"/>
	<br/>
	<spring:message code="dashboard.stddevRegistrations"/>
	<jstl:out value="${stddevRegistrations}"/>
	<br/>
	<spring:message code="dashboard.avgFee"/>
	<jstl:out value="${avgFee}"/>
	<br/>
	<spring:message code="dashboard.minFee"/>
	<jstl:out value="${minFee}"/>
	<br/>
	<spring:message code="dashboard.maxFee"/>
	<jstl:out value="${maxFee}"/>
	<br/>
	<spring:message code="dashboard.stddevFee"/>
	<jstl:out value="${stddevFee}"/>
	<br/>
	<spring:message code="dashboard.avgDays"/>
	<jstl:out value="${avgDays}"/>
	<br/>
	<spring:message code="dashboard.minDays"/>
	<jstl:out value="${minDays}"/>
	<br/>
	<spring:message code="dashboard.maxDays"/>
	<jstl:out value="${maxDays}"/>
	<br/>
	<spring:message code="dashboard.stddevDays"/>
	<jstl:out value="${stddevDays}"/>
	<br/>
	<spring:message code="dashboard.avgCategory"/>
	<jstl:out value="${avgCategory}"/>
	<br/>
	<spring:message code="dashboard.minCategory"/>
	<jstl:out value="${minCategory}"/>
	<br/>
	<spring:message code="dashboard.maxCategory"/>
	<jstl:out value="${maxCategory}"/>
	<br/>
	<spring:message code="dashboard.stddevCategory"/>
	<jstl:out value="${stddevCategory}"/>
	<br/>
	<spring:message code="dashboard.avgCommentsConf"/>
	<jstl:out value="${avgCommentsConf}"/>
	<br/>
	<spring:message code="dashboard.minCommentsConf"/>
	<jstl:out value="${minCommentsConf}"/>
	<br/>
	<spring:message code="dashboard.maxCommentsConf"/>
	<jstl:out value="${maxCommentsConf}"/>
	<br/>
	<spring:message code="dashboard.stddevCommentsConf"/>
	<jstl:out value="${stddevCommentsConf}"/>
	<br/>
	<spring:message code="dashboard.avgCommentsAct"/>
	<jstl:out value="${avgCommentsAct}"/>
	<br/>
	<spring:message code="dashboard.minCommentsAct"/>
	<jstl:out value="${minCommentsAct}"/>
	<br/>
	<spring:message code="dashboard.maxCommentsAct"/>
	<jstl:out value="${maxCommentsAct}"/>
	<br/>
	<spring:message code="dashboard.stddevCommentsAct"/>
	<jstl:out value="${stddevCommentsAct}"/>
	<br/>
</security:authorize>










