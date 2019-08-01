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


<form:form modelAttribute="cameraReadyPaper" action="cameraReadyPaper/author/edit.do">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="submission"/>

		<form:label path="title">
			<spring:message code="paper.title"/>*:
		</form:label>
		<form:input path="title"/>
		<form:errors cssClass="error" path="title" />
		<br/><br />
		
		<form:label path="summary">
			<spring:message code="paper.summary"/>*:
		</form:label>
		<form:input path="summary"/>
		<form:errors cssClass="error" path="summary" />
		<br/><br />
		
		<form:label path="document">
			<spring:message code="paper.document"/>*:
		</form:label>
		<form:input path="document"/>
		<form:errors cssClass="error" path="document" />
		<br/><br />
		
		<form:label path="authors">
			<spring:message code="paper.authors"/>*:
		</form:label>				
		<form:select path="authors">
			<form:options items="${authors}" itemLabel = "name" itemValue="name"/>
			</form:select>
		<form:errors cssClass="error" path="authors" />
		<br />
	
	
	<br />
	
	<spring:message code="asterisco"/>
	<br /><br />
	
	<input type ="submit" name="save" value="<spring:message code="submission.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="submission.cancel" />" onclick="javascript:relativeRedir('submission/author/list.do');" />

</form:form>










