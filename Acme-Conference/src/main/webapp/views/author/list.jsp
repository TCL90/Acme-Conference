<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<display:table name="authors" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="name" titleKey="author.name"/>
	<display:column property="surname" titleKey="author.surname"/>
	<display:column property="middleName" titleKey="author.middleName"/>
	<display:column property="score" titleKey="author.score"/>
	
	<display:column>
	
	<a href ="author/administrator/show.do?authorId=${row.id}" ><spring:message code="author.show" /></a>
	
	</display:column>
	
</display:table>

<a href ="author/administrator/procedure.do" ><spring:message code="author.execute" /></a>

<br/> <br/>

<jstl:if test="${buzzwords != null }">
	<spring:message code="author.buzzwords" />
	
	<jstl:forEach items="${buzzwords }" var="buzzword">
		<jstl:out value="${buzzword }"></jstl:out>
		,
	</jstl:forEach>
	
</jstl:if>

</security:authorize>