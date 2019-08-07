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


<display:table name="reports" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="originalityScore" titleKey="report.originalityScore"/>
	<display:column property="qualityScore" titleKey="report.qualityScore"/>
	<display:column property="readabilityScore" titleKey="report.readabilityScore"/>
	<display:column property="decision" titleKey="report.decision"/>


	
</display:table>

<a href="report/reviewer/create2.do" ><spring:message code="report.create" /></a>
	







