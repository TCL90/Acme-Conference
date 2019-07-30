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

<jstl:if test="${all==true||forth==true||running==true||past==true }">
<p>
<jstl:if test="${all==true }">
<b><spring:message code="conference.all"/></b>|
</jstl:if>

<jstl:if test="${all!=true }">
<a href="conference/list.do"><spring:message code="conference.all"/></a>|
</jstl:if>

<jstl:if test="${past==true }">
<b><spring:message code="conference.past"/></b>|
</jstl:if>

<jstl:if test="${past!=true }">
<a href="conference/pastList.do"><spring:message code="conference.past"/></a>|
</jstl:if>

<jstl:if test="${forth==true }">
<b><spring:message code="conference.all"/></b>|
</jstl:if>

<jstl:if test="${forth!=true }">
<a href="conference/forthCommingList.do"><spring:message code="conference.forthComming"/></a>|
</jstl:if>

<jstl:if test="${running==true }">
<b><spring:message code="conference.running"/></b>
</jstl:if>

<jstl:if test="${running!=true }">
<a href="conference/runningList.do"><spring:message code="conference.running"/></a>
</jstl:if>
</p>

</jstl:if>

<display:table name="conferences" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="title" titleKey="conference.title"/>
	<display:column property="acronym" titleKey="conference.acronym"/>
	<display:column property="venue" titleKey="conference.venue"/>
	<display:column property="submissionDeadline" titleKey="conference.submissionDeadline"/>
	<display:column property="notificationDeadline" titleKey="conference.notificationDeadline"/>
	<display:column property="cameraReadyDeadline" titleKey="conference.cameraReadyDeadline"/>
	<display:column property="startDate" titleKey="conference.startDate"/>
	<display:column property="endDate" titleKey="conference.endDate"/>
	<display:column property="summary" titleKey="conference.summary"/>
	<display:column property="fee" titleKey="conference.fee"/>
	
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<display:column property="category.titleEsp" titleKey="conference.category"/>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column property="category.titleIng" titleKey="conference.category"/>
	</jstl:if>
	
</display:table>









