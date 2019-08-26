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


<form:form action="messages/sendBroadcast.do" modelAttribute="bf">

		<form:hidden path="message.sender" />
		<form:hidden path="message.moment" />
		<form:hidden path="type" />
		
		<jstl:if test="${type=='subConf' or type == 'regConf'}">
			<form:select path="conference">
				<form:option label="----" value="0" />
				<form:options items="${conferences}" itemLabel = "title" />
			</form:select>
			<form:errors cssClass="error" path="conference" />
			<br />
			<br />
		</jstl:if>
		
		<form:label path="message.topic">
			<spring:message code="message.topic" />* :
		</form:label>
		<form:input path="message.topic" />
		<form:errors cssClass="error" path="message.topic" />
		<br /> <br />

		<form:label path="message.subject">
			<spring:message code="message.subject" />* :
		</form:label>
		<form:input path="message.subject" />
		<form:errors cssClass="error" path="message.subject" />
		<br /> <br />	
		
		<form:label path="message.body">
			<spring:message code="message.body" />* :
		</form:label>
		<form:textarea path="message.body" />
		<form:errors cssClass="error" path="message.body" />

		<br /> <br />	
		
		<input type="submit" name="send"
			value="<spring:message code="message.send.message" />" />&nbsp;
		<input type="button" name="cancel"
			onclick="javascript: window.location.replace('messages/broadcastMessage.do')"
			value="<spring:message code="message.send.cancel" />" />
	</form:form>