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
<jstl:if test="${allA==true||sdElapsed==true||nDElapses==true||cRElapses==true||organisedS==true }">
<p>
<jstl:if test="${allA==true }">
<b><spring:message code="conference.allA"/></b>|
</jstl:if>

<jstl:if test="${allA!=true }">
<a href="conference/administrator/list.do"><spring:message code="conference.allA"/></a>|
</jstl:if>

<jstl:if test="${sdElapsed==true }">
<b><spring:message code="conference.sdElapsed"/></b>|
</jstl:if>

<jstl:if test="${sdElapsed!=true }">
<a href="conference/administrator/sdElapsedList.do"><spring:message code="conference.sdElapsed"/></a>|
</jstl:if>

<jstl:if test="${nDElapses==true }">
<b><spring:message code="conference.nDElapses"/></b>|
</jstl:if>

<jstl:if test="${nDElapses!=true }">
<a href="conference/administrator/nDElapsesSoonList.do"><spring:message code="conference.nDElapses"/></a>|
</jstl:if>

<jstl:if test="${cRElapses==true }">
<b><spring:message code="conference.cRElapses"/></b>|
</jstl:if>

<jstl:if test="${cRElapses!=true }">
<a href="conference/administrator/cRElapsesSoonList.do"><spring:message code="conference.cRElapses"/></a>|
</jstl:if>

<jstl:if test="${organisedS==true }">
<b><spring:message code="conference.organisedS"/></b>
</jstl:if>

<jstl:if test="${organisedS!=true }">
<a href="conference/administrator/organisedSoonList.do"><spring:message code="conference.organisedS"/></a>
</jstl:if>
</p>
</jstl:if>
</security:authorize>

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
<b><spring:message code="conference.forthComming"/></b>|
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
	
	<display:column property="startDate" titleKey="conference.startDate"/>
	<display:column property="endDate" titleKey="conference.endDate"/>

	<display:column property="fee" titleKey="conference.fee"/>
	
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<display:column property="category.titleEsp" titleKey="conference.category"/>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column property="category.titleIng" titleKey="conference.category"/>
	</jstl:if>
	
	<security:authorize access="!hasRole('ADMIN')">
	<display:column><a href="conference/show.do?conferenceId=${row.id }"><spring:message code="conference.show"/></a></display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${row.finalMode == false}">
		<display:column> <a href="conference/administrator/edit.do?conferenceId=${row.id}"> <spring:message code="conference.edit"/></a></display:column>
	</jstl:if>
	
	
		<display:column><a href="conference/administrator/show.do?conferenceId=${row.id }"><spring:message code="conference.show"/></a></display:column>
	</security:authorize>
	
	
</display:table>

<br/>
<a href="conference/administrator/create.do"><spring:message code="administrator.conference.create"/></a>