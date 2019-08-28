<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
	<h3 style="color:blue;">
		<spring:message code="paper.title" />:
	</h3>
	<jstl:out value="${camera.title}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="paper.authors" />:
	</h3>
	<jstl:out value="${camera.authors}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="paper.summary" />:
	</h3>
	<jstl:out value="${camera.summary}"></jstl:out>
	
	<h3 style="color:blue;">
		<spring:message code="paper.document" />:
	</h3>
	<jstl:out value="${camera.document}"></jstl:out>
</security:authorize>