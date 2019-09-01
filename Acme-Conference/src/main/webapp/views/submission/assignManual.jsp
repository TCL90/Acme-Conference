<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form modelAttribute="submission" action="submission/administrator/edit.do">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="ticker" />
	<form:hidden path="moment" />
	<form:hidden path="status" />
	
	<form:hidden path="author" />
	<form:hidden path="paper" />
	<form:hidden path="conference" />
	
	


		<form:label path="reviewers">
			<spring:message code="submission.reviewers"/>*:
		</form:label>				
		<form:select path="reviewers" var="revi">
			<form:options items="${reviewers}"  itemLabel = "name"/>
			</form:select>
		<form:errors cssClass="error" path="conference" />
		<br />	<br />	



	
	<input type ="submit" name="save" value="<spring:message code="submission.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="submission.cancel" />" onclick="javascript:relativeRedir('submission/administrator/list.do');" />

</form:form>