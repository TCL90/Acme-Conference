<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="section/administrator/edit.do"
	modelAttribute="section">
	<form:hidden path="id" />
	<jstl:if test="${section.id==0 }">
	<form:hidden path="tutorial"/>
	</jstl:if>

	<fieldset>
		<legend align="left">
			<spring:message code="edit.section" />
		</legend>

		<form:label path="title">
		<spring:message code="section.title" />* :
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br /> <br />
		
		<form:label path="summary">
		<spring:message code="section.summary" />* :
		</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br /> <br />

		<form:label path="pictures">
			<spring:message code="section.pictures" /> :
		</form:label>
		<form:textarea path="pictures" />
		<form:errors cssClass="error" path="pictures" />
		<br /> <br />
		
	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="section.save" />" />&nbsp;
	<jstl:if test="${section.id!=0 }">
	<jstl:if test="${section.tutorial.conference.finalMode==true}">
	<input type="submit" name="delete"
		value="<spring:message code="section.delete" />"
		onclick="return confirm('<spring:message code="section.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('conference/administrator/list.do')"
		value="<spring:message code="section.edit.cancel" />" />

</form:form>