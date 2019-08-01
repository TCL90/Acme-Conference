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


<display:table name="submissions" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="ticker" titleKey="submission.ticker"/>
	<display:column property="moment" titleKey="submission.moment"/>
	
	<display:column titleKey="submission.status">
		<jstl:if test="${row.status == 'UNDER-REVIEW'}">
			<spring:message code="submission.under.review"/>
		</jstl:if>
		<jstl:if test="${row.status == 'ACCEPTED'}">
			<spring:message code="submission.accepted"/>
		</jstl:if>
		<jstl:if test="${row.status == 'REJECTED'}">
			<spring:message code="submission.rejected"/>
		</jstl:if>
	</display:column>
	
	<display:column >
		<jstl:if test="${row.status == 'ACCEPTED'}">
			<a href="cameraReadyPaper/author/create.do?submissionId=${row.id}" >
			<spring:message code="camera.create" /></a>
		</jstl:if>
	</display:column>
</display:table>

<a href="submission/author/create.do" ><spring:message code="submission.create" /></a>
	








