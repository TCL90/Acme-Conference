<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	
	<jstl:if test="${type=='presentation' }">
	
	<h3 style="color:blue;">
		<spring:message code="activity.conference.title" />:
	</h3>
	<jstl:out value="${presentation.conference.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.title" />:
	</h3>
	<jstl:out value="${presentation.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.speakers2" />:
	</h3>
	
	<jstl:forEach var="speakers" items="${presentation.speakers }">
    <jstl:out value="${speakers}"/>
	</jstl:forEach>
	
	<h3 style="color:blue;">
		<spring:message code="activity.startMoment" />:
	</h3>
	<jstl:out value="${presentation.startMoment}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.duration" />:
	</h3>
	<jstl:out value="${presentation.duration}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.schedule" />:
	</h3>
	<jstl:out value="${presentation.schedule}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.room" />:
	</h3>
	<jstl:out value="${presentation.room}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.summary" />:
	</h3>
	<jstl:out value="${presentation.summary}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.attachments2" />:
	</h3>
	<jstl:forEach var="attachs" items="${presentation.attachments }">
	<jstl:forEach var="splt" items="${fn:split(attachs,',')}">
    <a href="${splt}"><jstl:out value="${splt}"/></a> 
	</jstl:forEach>
	</jstl:forEach>
	
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.title" />:
	</h3>
	<jstl:out value="${presentation.cameraReadyPaper.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.authors" />:
	</h3>
	<jstl:forEach var="authors" items="${presentation.cameraReadyPaper.authors }">
    <jstl:out value="${authors}"/>
	</jstl:forEach>
	
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.summary" />:
	</h3>
	<jstl:out value="${presentation.cameraReadyPaper.summary}"></jstl:out>
	
	<security:authorize access="hasRole('ADMIN')">
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.document" />:
	</h3>
	<a href="${presentation.cameraReadyPaper.document}"><jstl:out value="${presentation.cameraReadyPaper.document}"/></a>
	</security:authorize>
	
	</jstl:if>
	
	<jstl:if test="${type=='tutorial' }">
	<h3 style="color:blue;">
		<spring:message code="activity.conference.title" />:
	</h3>
	<jstl:out value="${tutorial.conference.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.title" />:
	</h3>
	<jstl:out value="${tutorial.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.speakers2" />:
	</h3>
	<jstl:forEach var="speakers" items="${tutorial.speakers }">
    <jstl:out value="${speakers}"/>
	</jstl:forEach>
	
	<h3 style="color:blue;">
		<spring:message code="activity.startMoment" />:
	</h3>
	<jstl:out value="${tutorial.startMoment}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.duration" />:
	</h3>
	<jstl:out value="${tutorial.duration}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.schedule" />:
	</h3>
	<jstl:out value="${tutorial.schedule}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.room" />:
	</h3>
	<jstl:out value="${tutorial.room}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.summary" />:
	</h3>
	<jstl:out value="${tutorial.summary}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.attachments2" />:
	</h3>
	<jstl:forEach var="attachs" items="${tutorial.attachments }">
	<jstl:forEach var="splt" items="${fn:split(attachs,',')}">
    <a href="${splt}"><jstl:out value="${splt}"/></a> 
	</jstl:forEach>
	</jstl:forEach>
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.list" />:
	</h3>
	
	<jstl:set var="tuto" value="1"/>
	<jstl:forEach items="${sections}" var="section">
	<h3 style="color:blue;">
	Tutorial <jstl:out value="${tuto }"/>:
	</h3>
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.title" />:
	</h3>
	<jstl:out value="${section.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.summary" />:
	</h3>
	<jstl:out value="${section.summary}"></jstl:out>
	<jstl:if test="${!empty section.pictures}">
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.pictures" />:
	</h3>
	<jstl:forEach var="pictures" items="${section.pictures }">
	<jstl:forEach var="pc" items="${fn:split(pictures,',')}">
    <a href="${pc}"><jstl:out value="${pc}"/></a> 
	</jstl:forEach>
	</jstl:forEach>
	</jstl:if>
	
	<br/>
	<br/>
	<jstl:if test="${empty section.pictures}">
	<h4 style="color:blue;"><spring:message code="section.noImages"/></h4>
	</jstl:if>
	
	<security:authorize access="hasRole('ADMIN')">
	<br/>
	<a href="section/administrator/edit.do?sectionId=${section.id }"><spring:message code="section.edit"/></a>
	</security:authorize>
	<jstl:set var="tuto" value="${tuto +1}"/>
	</jstl:forEach>
	<security:authorize access="hasRole('ADMIN')">
	<br/><br/>
	<a href="section/administrator/create.do?tutorialId=${tutorial.id}"><spring:message code="section.create"/></a>
	</security:authorize>
	<br/>
	</jstl:if>
	
	
	
	<jstl:if test="${type=='panel' }">
	
	<h3 style="color:blue;">
		<spring:message code="activity.conference.title" />:
	</h3>
	<jstl:out value="${activity.conference.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.title" />:
	</h3>
	<jstl:out value="${activity.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.speakers" />:
	</h3>
	<jstl:forEach var="speakers" items="${activity.speakers }">
    <jstl:out value="${speakers}"/>
	</jstl:forEach>
	
	<h3 style="color:blue;">
		<spring:message code="activity.startMoment" />:
	</h3>
	<jstl:out value="${activity.startMoment}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.duration" />:
	</h3>
	<jstl:out value="${activity.duration}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.schedule" />:
	</h3>
	<jstl:out value="${activity.schedule}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.room" />:
	</h3>
	<jstl:out value="${activity.room}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.summary" />:
	</h3>
	<jstl:out value="${activity.summary}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="activity.attachments2" />:
	</h3>
	<jstl:forEach var="attachs" items="${activity.attachments }">
	<jstl:forEach var="splt" items="${fn:split(attachs,',')}">
    <a href="${splt}"><jstl:out value="${splt}"/></a> 
	</jstl:forEach>
	</jstl:forEach>
	</jstl:if>
	<jstl:if test="${activity.conference.finalMode==true ||tutorial.conference.finalMode==true ||presentation.conference.finalMode==true }">
	<h3 style="color:blue;">
	<spring:message code="activity.listComments"/>
	</h3>
	
	<display:table name="comments" id="row2" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="title" titleKey="comment.title"/>
	<display:column property="moment" titleKey="comment.moment"/>
	
	<jstl:if test="${row2.author.userAccount.username=='' || row2.author.userAccount.username==null}">
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column value="Unknown" titleKey="comment.authors"/>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<display:column value="Anónimo" titleKey="comment.authors"/>
	</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${row2.author.userAccount.username!='' && row2.author.userAccount.username!=null}">
	<display:column property="author.userAccount.username" titleKey="comment.authors"/>
	</jstl:if>
	<display:column property="text" titleKey="comment.text"/>
	</display:table>
	
	<br/>
	<br/>
	<jstl:if test="${type=='presentation'}">
	<a href="comment/createA.do?activityId=${presentation.id }"><spring:message code="comment.create"/></a>
	</jstl:if>
	<jstl:if test="${type=='tutorial'}">
	<a href="comment/createA.do?activityId=${tutorial.id }"><spring:message code="comment.create"/></a>
	</jstl:if>
	<jstl:if test="${type=='panel'}">
	<a href="comment/createA.do?activityId=${activity.id }"><spring:message code="comment.create"/></a>
	</jstl:if>
	<br/>
	<br/>
	
	</jstl:if>
	
	<br/>
	<br/>
	
	<security:authorize access="!hasRole('ADMIN')">
	<input type="button" name="back" onclick="javascript: window.location.replace('conference/list.do')"
		value="<spring:message code="activity.back" />" />
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<input type="button" name="back" onclick="javascript: window.location.replace('conference/administrator/list.do')"
		value="<spring:message code="activity.back" />" />
	</security:authorize>