<%--
 * list.jsp
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
 
	<p>
			<b><spring:message code="messages.all" /></b>|
		
	</p>

<display:table name="messages" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	
	<display:column property="sender.name" titleKey="message.sender" sortable="true"/>
	<display:column property="topic" titleKey="message.topic" sortable="true"/>
	<display:column property="subject" titleKey="message.subject"/>
	<display:column property="moment" titleKey="message.moment"/>

	<display:column><a href="messages/display.do?messageId=${row.id}"><spring:message code="message.show"/></a></display:column>
	
</display:table>