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


<form:form modelAttribute="report" action="report/reviewer/edit.do">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="reviewer" />

<fieldset>



	<form:label path="originalityScore">
	<spring:message code="report.originalityScore"/>*:
	</form:label>
	<form:input path="originalityScore" type="number"/>
	<form:errors cssClass="error" path="originalityScore" />
	<br />
	
	<form:label path="qualityScore">
	<spring:message code="report.qualityScore"/>*:
	</form:label>
	<form:input path="qualityScore" type="number"/>
	<form:errors cssClass="error" path="qualityScore" />
	<br />
	
	<form:label path="readabilityScore">
	<spring:message code="report.readabilityScore"/>*:
	</form:label>
	<form:input path="readabilityScore" type="number"/>
	<form:errors cssClass="error" path="readabilityScore" />
	<br />
	
	<form:label path="decision">
	<spring:message code="report.decision"/>*:
	</form:label>
	<form:select path="decision">
		<jstl:if test="${pageContext.response.locale.language=='en' }">
			<form:option label="Border-line" value="BORDER-LINE" />
			<form:option label="Accept" value="ACCEPT" />
			<form:option label="Reject" value="REJECT" />
		</jstl:if>
		<jstl:if test="${pageContext.response.locale.language=='es' }">
			<form:option label="Al límite" value="BORDER-LINE" />
			<form:option label="Aceptar" value="ACCEPT" />
			<form:option label="Rechazar" value="REJECT" />
		</jstl:if>
		
	</form:select>
	<form:errors cssClass="error" path="decision" />
	<br />
	
	
		<form:label path="submission">
			<spring:message code="report.submission"/>*:
		</form:label>				
		<form:select path="submission">
			<form:options items="${submissions}"  itemLabel = "ticker"/>
		</form:select>
		<form:errors cssClass="error" path="submission" />
		<br />	
	
	</fieldset>
	<spring:message code="asterisco"/>
		<br />	<br />
	<input type ="submit" name="save" value="<spring:message code="report.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="report.cancel" />" onclick="javascript:relativeRedir('report/reviewer/list.do');" />

</form:form>










