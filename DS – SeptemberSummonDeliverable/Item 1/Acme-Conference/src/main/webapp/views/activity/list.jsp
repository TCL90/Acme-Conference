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

<display:table name="activities" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="title" titleKey="activity.title"/>
	<display:column property="speakers" titleKey="activity.speakers"/>
	<display:column property="startMoment" titleKey="activity.startMoment"/>
	<display:column property="duration" titleKey="activity.duration"/>
	<display:column property="schedule" titleKey="activity.schedule"/>
	<display:column property="room" titleKey="activity.room"/>
	
	<display:column>
	<a href="activity/administrator/show.do?activityId=${row.id }">
	<spring:message code="activity.show"/>
	</a>
	<security:authorize access="!hasRole('ADMIN')">
	<a href="activity/show.do?activityId=${row.id }">
	<spring:message code="activity.show"/>
	</a>
	</security:authorize>
	</display:column>
	
	<display:column>
	<jstl:if test="${row.conference.finalMode==true }">
	<a href="activity/administrator/edit.do?activityId=${row.id }">
	<spring:message code="activity.edit"/>
	</a>
	</jstl:if>
	</display:column>
	
	</display:table>

</security:authorize>
<br/>