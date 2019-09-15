<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="comment/edit.do"
	modelAttribute="comment">

	<form:hidden path="id" />
	
	<form:hidden path="moment"/>
	<form:hidden path="commentable" value="${comment.commentable.id }"/>
	<form:hidden path="author"/>

	<fieldset>
		<legend align="left">
			<spring:message code="edit.comment" />
		</legend>

		<form:label path="title">
		<spring:message code="comment.title" />* :
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br /> <br />
		
		<form:label path="text">
		<spring:message code="comment.text" />* :
		</form:label>
		<form:textarea path="text" />
		<form:errors cssClass="error" path="text" />
		<br /> <br />
		
	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="comment.save" />" />&nbsp;
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('conference/list.do')"
		value="<spring:message code="comment.edit.cancel" />" />
</form:form>