<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="conference/administrator/edit.do" modelAttribute="conference">
	
	<form:hidden path="id" />
	<form:hidden path="version" />


		<form:label path="title">
			<spring:message code="conference.title" />* :
		</form:label>
		<form:input path="title" placeholder="E.g.: Conference about ..."/>
		<form:errors cssClass="error" path="title" />

		<br /> <br />

		<form:label path="acronym">
			<spring:message code="conference.acronym" />* :
		</form:label>
		<form:input path="acronym"  placeholder="E.g.: CFD"/>
		<form:errors cssClass="error" path="acronym" />

		<br /> <br />

		<form:label path="venue">
			<spring:message code="conference.venue" />* :
		</form:label>
		<form:input path="venue" placeholder="E.g.: FIBES, Sevilla"/>
		<form:errors cssClass="error" path="venue" />

		<br /> <br />

		<form:label path="submissionDeadline">
			<spring:message code="conference.submissionDeadline" />* :
		</form:label>
		<form:input path="submissionDeadline" placeholder="yyyy-MM-dd HH:mm:ss"/>
		<form:errors cssClass="error" path="submissionDeadline" />

		<br /> <br />
		
		<form:label path="notificationDeadline">
			<spring:message code="conference.notificationDeadline" />* :
		</form:label>
		<form:input path="notificationDeadline" placeholder="yyyy-MM-dd HH:mm:ss"/>
		<form:errors cssClass="error" path="notificationDeadline" />

		<br /> <br />
		
		<form:label path="cameraReadyDeadline">
			<spring:message code="conference.cameraReadyDeadline" />* :
		</form:label>
		<form:input path="cameraReadyDeadline" placeholder="yyyy-MM-dd HH:mm:ss"/>
		<form:errors cssClass="error" path="cameraReadyDeadline" />

		<br /> <br />
		
		<form:label path="startDate">
			<spring:message code="conference.startDate" />* :
		</form:label>
		<form:input path="startDate" placeholder="yyyy-MM-dd HH:mm:ss"/>
		<form:errors cssClass="error" path="startDate" />

		<br /> <br />
		
		<form:label path="endDate">
			<spring:message code="conference.endDate" />* :
		</form:label>
		<form:input path="endDate" placeholder="yyyy-MM-dd HH:mm:ss"/>
		<form:errors cssClass="error" path="endDate" />

		<br /> <br />
		
		<form:label path="summary">
			<spring:message code="conference.summary" />* :
		</form:label>
		<form:input path="summary" placeholder="E.g.: 1.- Presentación..."/>
		<form:errors cssClass="error" path="summary" />

		<br /> <br />
		
		<form:label path="fee">
			<spring:message code="conference.fee" />* :
		</form:label>
		<form:input path="fee" type="number" />
		<form:errors cssClass="error" path="fee" />

		<br /> <br />
		
		<form:label path="finalMode">
			<spring:message code="conference.finalMode" />* :
		</form:label>
		
		<jstl:if test="${pageContext.response.locale.language=='en'}">
			<form:select path="finalMode" >
				<form:option label="Yes" value="true" />
				<form:option label="No" value="false" />	
			</form:select>
		</jstl:if>
		
		<jstl:if test="${pageContext.response.locale.language=='es'}">
			<form:select path="finalMode" >
				<form:option label="Si" value="true" />
				<form:option label="No" value="false" />	
			</form:select>
		</jstl:if>
		<form:errors cssClass="error" path="finalMode" />
			<br /> <br />
		<form:label path="category">
			<spring:message code="conference.category" />* :
		</form:label>
		
		<form:select path="category" >
			<jstl:if test="${pageContext.response.locale.language=='es'}">	
				<form:options items="${categories}"  itemLabel = "titleEsp"/>	
			</jstl:if>
			<jstl:if test="${pageContext.response.locale.language=='en'}">	
				<form:options items="${categories}"  itemLabel = "titleIng"/>	
			</jstl:if>
		</form:select>
		<form:errors cssClass="error" path="category" />

		
		<br /> <br />
		


	<br />

	<spring:message code="asterisco"/>
	<br />
	<br />
	<input type="submit" name="save"
		value="<spring:message code="conference.edit.save" />" />&nbsp;
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="conference.edit.cancel" />" />
	<jstl:if test="${conference.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="conference.delete" />"
			onclick="return confirm('<spring:message code="conference.confirm.delete" />')" />&nbsp;
	</jstl:if>
</form:form>