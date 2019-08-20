<%--
 * newMessage.jsp
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

	<form:form action="messages/newMessage.do" modelAttribute="mesInformation">
	
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="sender" />
		<form:hidden path="moment" />
		
		<form:label path="recipients">
			<spring:message code="message.recipients"/>*:
		</form:label>				
		<form:select path="recipients">
			<form:options items="${recipient}" itemLabel = "name" />
			</form:select>
		<form:errors cssClass="error" path="recipients" />
		<br />

		<form:label path="topic">
			<spring:message code="message.topic" />* :
		</form:label>
		<form:input path="topic" />
		<form:errors cssClass="error" path="topic" />
		<br /> <br />

		<form:label path="subject">
			<spring:message code="message.subject" />* :
		</form:label>
		<form:input path="subject" />
		<form:errors cssClass="error" path="subject" />
		<br /> <br />	
		
		<form:label path="body">
			<spring:message code="message.body" />* :
		</form:label>
		<form:textarea path="body" />
		<form:errors cssClass="error" path="body" />

		<br /> <br />	
		
		<input type="submit" name="send"
			value="<spring:message code="message.send.message" />" />&nbsp;
		<input type="button" name="cancel"
			onclick="javascript: window.location.replace('welcome/index.do')"
			value="<spring:message code="message.send.cancel" />" />
	</form:form>