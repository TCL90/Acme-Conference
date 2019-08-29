<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="submissions" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">
	<display:column property="ticker" titleKey="submission.ticker"/>
	<display:column property="moment" titleKey="submission.moment"/>
	
	<display:column titleKey="submission.status" sortable="true">
		<jstl:if test="${row.status == 'UNDER-REVIEW'}">
			<spring:message code="submission.under.review"/>
		</jstl:if>
		<jstl:if test="${row.status == 'ACCEPTED'}">
			<spring:message code="submission.accepted"/>
		</jstl:if>
		<jstl:if test="${row.status == 'REJECTED'}">
			<spring:message code="submission.rejected"/>
		</jstl:if>
	</display:column>
	
	<display:column>
	<a href="submission/show.do?submissionId=${row.id}" ><spring:message code="submission.show" /></a>
			<br/><br/>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<jstl:if test="${empty row.reviewers }">
				<a href="submission/administrator/assign.do?submissionId=${row.id}" ><spring:message code="submission.assign" /></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	

</display:table>

<security:authorize access="hasRole('AUTHOR')" >
<a href="submission/author/create.do" ><spring:message code="submission.create" /></a>
<br/><br/>
<a href="cameraReadyPaper/author/create.do" >
<spring:message code="camera.create" /></a>	
</security:authorize>

<jstl:if test="${reviewers != null }">

	<spring:message code="submission.reviewers" />
	<jstl:forEach items="${reviewers }" var="reviewer">
		<jstl:out value="${reviewer.name}"></jstl:out>
		<jstl:out value="${reviewer.surname}"></jstl:out>, 
	</jstl:forEach>
	<spring:message code="submission.assigned" />
	
</jstl:if>








