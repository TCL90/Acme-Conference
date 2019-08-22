<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('AUTHOR')">

	<form:form action="finder/author/edit.do" modelAttribute="finder">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="conferences" />

		<form:label path="keyword">
			<spring:message code="finder.keyword" />
		</form:label>
		<form:input path="keyword" />
		<form:errors cssClass="error" path="keyword" />
		<br />

		<form:label path="startDate">
			<spring:message code="finder.startDate" />
		</form:label>
		<form:input path="startDate" />
		<form:errors cssClass="error" path="startDate" />
		<br />

		<form:label path="endDate">
			<spring:message code="finder.endDate" />
		</form:label>
		<form:input path="endDate" />
		<form:errors cssClass="error" path="endDate" />
		<br />

		<form:label path="maximumFee">
			<spring:message code="finder.maximumFee" />
		</form:label>
		<form:input path="maximumFee" />
		<form:errors cssClass="error" path="maximumFee" />
		<br />

		<jstl:if test="${language=='en'}">
			<form:label path="category">
				<spring:message code="finder.category" />
			</form:label>
			<form:select path="category" multiple="false">
				<form:option value="0" label="-----" />
				<form:options items="${categories}" itemLabel="titleIng" />
			</form:select>
		</jstl:if>
		<jstl:if test="${language=='es'}">
			<form:label path="category">
				<spring:message code="finder.category" />
			</form:label>
			<form:select path="category" multiple="false">
				<form:option value="0" label="-----" />
				<form:options items="${categories}" itemLabel="titleEsp" />
			</form:select>
		</jstl:if>

		<input type="button" name="cancel"
			value="<spring:message code="finder.cancel"/>"
			onclick="javascript: relativeRedir('finder/author/show.do');" />
		<input type="submit" name="save"
			value="<spring:message code="finder.save"/>" />
	</form:form>


</security:authorize>