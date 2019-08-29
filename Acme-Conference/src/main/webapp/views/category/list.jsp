<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
<display:table name="categories" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="titleIng" titleKey="category.titleIng"/>
	<display:column property="titleEsp" titleKey="category.titleEsp"/>
	<jstl:if test="${language=='en'}">
	<display:column property="parent.titleIng" titleKey="category.parent"/>
	</jstl:if>
	<jstl:if test="${language=='es'}">
	<display:column property="parent.titleEsp" titleKey="category.parent"/>
	</jstl:if>
	<display:column>
	<jstl:if test="${row.titleEsp != 'CONFERENCIA'}">
		<a href="category/administrator/edit.do?categoryId=${row.id}"><spring:message code="category.edit"/></a>
	</jstl:if>
	</display:column>
</display:table>
<a href="category/administrator/create.do"><spring:message code="category.create"/></a>
</security:authorize>