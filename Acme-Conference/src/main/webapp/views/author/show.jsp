<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<h3>
		<spring:message code="author.name" />:
	</h3>
	<jstl:out value="${name}"></jstl:out>
	
	<h3>
		<spring:message code="author.middleName" />:
	</h3>
	<jstl:out value="${middleName}"></jstl:out>
	
	<h3>
		<spring:message code="author.surname" />:
	</h3>
	<jstl:out value="${surname}"></jstl:out>
	
	<h3>
		<spring:message code="author.email" />:
	</h3>
	<jstl:out value="${email}"></jstl:out>
	
	<h3>
		<spring:message code="author.phoneNumber" />:
	</h3>
	<jstl:out value="${phoneNumber}"></jstl:out>
	
	<h3>
		<spring:message code="author.address" />:
	</h3>
	<jstl:out value="${address}"></jstl:out>
	
	<h3>
		<spring:message code="author.photo" />:
	</h3>
	<jstl:out value="${photo}"></jstl:out>
	
	<jstl:if test="${score != 0.0}">
	<h3>
		<spring:message code="author.score" />:
	</h3>
		<jstl:out value="${score}"></jstl:out>
	</jstl:if>
	
	
	
	