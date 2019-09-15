<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<security:authorize access="hasRole('ADMIN')">
<form:form action="category/administrator/edit.do"
	modelAttribute="category">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="titleIng">
		<spring:message code="category.titleIng" />*:
		</form:label>
	<form:input path="titleIng" />
	<form:errors cssClass="error" path="titleIng" />
	<br />
	<form:label path="titleEsp">
		<spring:message code="category.titleEsp" />*:
		</form:label>
	<form:input path="titleEsp" />
	<form:errors cssClass="error" path="titleEsp" />
	<br />
	<form:label path="parent">
		<spring:message code="category.parent" />
	</form:label>
	<form:select path="parent" multiple="false">
		<jstl:if test="${language=='es'}">
		<form:options items="${parents}" itemLabel="titleEsp" />
		</jstl:if>
		<jstl:if test="${language=='en'}">
		<form:options items="${parents}" itemLabel="titleIng" />
		</jstl:if>
	</form:select>

	<input type="submit" name="save"
		value="<spring:message code="category.save" />" />&nbsp;
		<jstl:if test="${category.id!=0 }">
		<input type="submit" name="delete"
			value="<spring:message code="category.delete" />"
			onclick="return confirm('<spring:message code="category.confirm.delete" />')" />&nbsp;
		</jstl:if>
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('category/administrator/list.do')"
		value="<spring:message code="category.cancel" />" />
</form:form>
</security:authorize>