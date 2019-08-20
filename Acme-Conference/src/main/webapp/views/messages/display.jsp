<%--
 * display.jsp
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


	<h3 style="color:blue;">
		<spring:message code="message.subject" />:
	</h3>
	<jstl:out value="${messageInfo.subject}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="message.topic" />:
	</h3>
	<jstl:out value="${messageInfo.topic}"></jstl:out>
	
	
	<form:form action="messages/delete.do" modelAttribute="messageInfo">
		
		<form:hidden path="id"/>
		<input type="submit" name="delete"
			value="<spring:message code="activity.delete" />"
			onclick="return confirm('<spring:message code="activity.confirm.delete" />')" />&nbsp;
	</form:form>
	<!-- <jstl:if test="${isSender}">
		<input type="submit" name="deleteMessage"
		value="<spring:message code="message.delete" />"
		onclick="return confirm('<spring:message code="message.confirm.delete" />')" />
	 </jstl:if> -->