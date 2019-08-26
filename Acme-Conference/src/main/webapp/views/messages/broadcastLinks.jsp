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


<a href="messages/createBroadcast.do?type=subConf"><spring:message code="messages.broadcast.subConf" /></a>
<br/>
<a href="messages/createBroadcast.do?type=regConf"><spring:message code="messages.broadcast.regConf" /></a>
<br/>
<a href="messages/createBroadcast.do?type=authors"><spring:message code="messages.broadcast.authors" /></a>
<br/>
<a href="messages/createBroadcast.do?type=actors"><spring:message code="messages.broadcast.actors" /></a>