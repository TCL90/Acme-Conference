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
<display:table name="cameras" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="title" titleKey="paper.title"/>
	<display:column property="authors" titleKey="paper.authors"/>
	<display:column property="summary" titleKey="paper.summary"/>
	<display:column><a href="camerareadypaper/administrator/show.do?paperId=${row.id}"><spring:message code="camera.show"/></a></display:column>
	<display:column><a href="activity/administrator/createP.do?conferenceId=${row.submission.conference.id }&type=presentation&cameraId=${row.id}"><spring:message code="conference.presentation.create"/></a></display:column>
</display:table>

</security:authorize>