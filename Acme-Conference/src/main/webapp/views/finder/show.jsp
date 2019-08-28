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
	<strong><spring:message code="finder.keyword" />:</strong>
	<jstl:out value="${finder.keyword}" />
	<br />

	<strong><spring:message code="finder.startDate" />:</strong>
	<jstl:out value="${finder.startDate}" />
	<br />

	<strong><spring:message code="finder.endDate" />:</strong>
	<jstl:out value="${finder.endDate}" />
	<br />

	<strong><spring:message code="finder.category" />:</strong>
	<jstl:if test="${language=='es'}">
	<jstl:out value="${finder.category.titleEsp}" />
	</jstl:if>
	<jstl:if test="${language=='en'}">
	<jstl:out value="${finder.category.titleEng}" />
	</jstl:if>
	<br />

	<strong><spring:message code="finder.maximumFee" />:</strong>
	<jstl:out value="${finder.maximumFee}" />
	<br />

	<h3>
		<spring:message code="finder.results" />
		:
	</h3>
	<br />
	<display:table pagesize="5" requestURI="${requestURI}"
		class="displaytag" keepStatus="true" name="finder.conferences"
		id="conference">
		<display:column property="title" titleKey="finder.title" />
		<display:column property="acronym" titleKey="finder.acronym" />
		<jstl:if test="${language=='es'}">
			<display:column property="category.titleEsp" titleKey="finder.category"/>

		</jstl:if>
		<jstl:if test="${language=='en'}">
			<display:column property="category.titleIng" titleKey="finder.category" />
		</jstl:if>
		<display:column property="startDate" titleKey="finder.startDate" />
		<display:column property="endDate" titleKey="finder.endDate" />
		<display:column property="fee" titleKey="finder.fee" />
	</display:table>
	<br />

	<a href="finder/author/edit.do"><spring:message code="finder.edit" /></a>
</security:authorize>
