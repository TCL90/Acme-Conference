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

<h3 style="color:blue;">
		<spring:message code="report.originalityScore" />:
	</h3>
	<jstl:out value="${report.originalityScore}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="report.qualityScore" />:
	</h3>
	<jstl:out value="${report.qualityScore}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="report.readabilityScore" />:
	</h3>
	<jstl:out value="${report.readabilityScore}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="report.decision" />:
	</h3>
	<jstl:out value="${report.decision}"></jstl:out>
	
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
	<display:column value="Anónimo" titleKey="comment.authors"/>
	</jstl:if>
	
	</jstl:if>
	
	<jstl:if test="${row2.author.userAccount.username!='' && row2.author.userAccount.username!=null}">
	<display:column property="author.userAccount.username" titleKey="comment.authors"/>
	</jstl:if>
	<display:column property="text" titleKey="comment.text"/>
	</display:table>
	
	<br/>
	<security:authorize access="hasRole('REVIEWER')">
	<a href="comment/createR.do?reportId=${report.id }"><spring:message code="comment.create"/></a>
	</security:authorize>
	<br/>
	<br/>
<input type="button" name="cancel" value="<spring:message code="report.back" />" onclick="javascript:relativeRedir('report/author/list.do?submissionId=${report.submission.id}');" />


<br/>