<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<h3 style="color:blue;">
		<spring:message code="conference.title" />:
	</h3>
	<jstl:out value="${conference.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.acronym" />:
	</h3>
	<jstl:out value="${conference.acronym}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.venue" />:
	</h3>
	<jstl:out value="${conference.venue}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.submissionDeadline" />:
	</h3>
	<jstl:out value="${conference.submissionDeadline}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.notificationDeadline" />:
	</h3>
	<jstl:out value="${conference.notificationDeadline}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.cameraReadyDeadline" />:
	</h3>
	<jstl:out value="${conference.cameraReadyDeadline}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.startDate" />:
	</h3>
	<jstl:out value="${conference.startDate}"></jstl:out>
	
	
	<h3 style="color:blue;">
		<spring:message code="conference.endDate" />:
	</h3>
	<jstl:out value="${conference.endDate}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.summary" />:
	</h3>
	<jstl:out value="${conference.summary}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="conference.fee" />:
	</h3>
	<jstl:out value="${conference.fee}"/>
	
	<h3 style="color:blue;">
		<spring:message code="conference.category" />:
	</h3>
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<jstl:out value="${conference.category.titleEsp}"/>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<jstl:out value="${conference.category.titleIng}"/>
	</jstl:if>
	
	<h3 style="color:blue;">
		<spring:message code="conference.numberOfR" />:
	</h3>
	<jstl:out value="${numberOfR}"/>
	
	<br/>
	
	<h3 style="color:blue;">
	<spring:message code="conference.listActivities"/>
	</h3>
	
	<display:table name="activities" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="title" titleKey="activity.title"/>
	<display:column property="speakers" titleKey="activity.speakers"/>
	<display:column property="startMoment" titleKey="activity.startMoment"/>
	<display:column property="duration" titleKey="activity.duration"/>
	<display:column property="schedule" titleKey="activity.schedule"/>
	<display:column property="room" titleKey="activity.room"/>
	
	<display:column>
	<a href="activity/show.do?activityId=${row.id }">
	<spring:message code="activity.show"/>
	</a>
	</display:column>
	
	</display:table>
	
	<h3 style="color:blue;">
	<spring:message code="conference.listComments"/>
	</h3>
	
	<display:table name="comments" id="row2" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="title" titleKey="comment.title"/>
	<display:column property="moment" titleKey="comment.moment"/>
	
	<jstl:if test="${row2.author.userAccount.username=='' || row2.author.userAccount.username==null}">
	
	<jstl:if test="${pageContext.response.locale.language=='en'}">
	<display:column value="Unknown" titleKey="comment.authors"/>
	</jstl:if>
	
	<jstl:if test="${pageContext.response.locale.language=='es'}">
	<display:column value="An�nimo" titleKey="comment.authors"/>
	</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${row2.author.userAccount.username!='' && row2.author.userAccount.username!=null}">
	<display:column property="author.userAccount.username" titleKey="comment.authors"/>
	</jstl:if>
	<display:column property="text" titleKey="comment.text"/>
	</display:table>
	
	<br/>
	<a href="comment/create.do?conferenceId=${conference.id }"><spring:message code="comment.create"/></a>
	<br/>
	<br/>
	
	<input type="button" name="back" onclick="javascript: window.location.replace('conference/list.do')"
		value="<spring:message code="conference.back" />" />