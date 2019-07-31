<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	
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
		<spring:message code="activity.speakers" />:
	</h3>
	<jstl:out value="${presentation.speakers}"></jstl:out>
	
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
		<spring:message code="activity.attachments" />:
	</h3>
	<jstl:out value="${presentation.attachments}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.title" />:
	</h3>
	<jstl:out value="${presentation.cameraReadyPaper.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.authors" />:
	</h3>
	<jstl:out value="${presentation.cameraReadyPaper.authors}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="presentation.camera.summary" />:
	</h3>
	<jstl:out value="${presentation.cameraReadyPaper.summary}"></jstl:out>
	
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
		<spring:message code="activity.speakers" />:
	</h3>
	<jstl:out value="${tutorial.speakers}"></jstl:out>
	
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
		<spring:message code="activity.attachments" />:
	</h3>
	<jstl:out value="${tutorial.attachments}"></jstl:out>
	
	<jstl:forEach items="sections" var="section">
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.list" />:
	</h3>
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.title" />:
	</h3>
	<jstl:out value="${section.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.summary" />:
	</h3>
	<jstl:out value="${section.summary}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="tutorial.section.pictures" />:
	</h3>
	<jstl:out value="${section.pictures}"></jstl:out>
	
	</jstl:forEach>
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
	<jstl:out value="${activity.speakers}"></jstl:out>
	
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
		<spring:message code="activity.attachments" />:
	</h3>
	<jstl:out value="${activity.attachments}"></jstl:out>
	
	</jstl:if>
	
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
	
	<a href="comment/create.do?activityId=${activity.id }"><spring:message code="comment.create"/></a>
	
	<br/>
	<br/>
	
	<input type="button" name="back" onclick="javascript: window.location.replace('conference/list.do')"
		value="<spring:message code="activity.back" />" />
