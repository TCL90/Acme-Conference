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


<form:form modelAttribute="sponsorship" action="sponsorship/sponsor/edit.do">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="sponsor"/>

<fieldset>

	<legend align="left">
		<spring:message code="sponsorship.creditcard" />
	</legend>

	<form:label path="holderName">
	<spring:message code="sponsorship.holderName"/>*:
	</form:label>
	<form:input path="holderName"/>
	<form:errors cssClass="error" path="holderName" />
	<br />
	
	<form:label path="makeName">
		<spring:message code="sponsorship.makeName"/>*:
	</form:label>				
	<form:select path="makeName">
		<form:options items="${makes}" />
		</form:select>
	<form:errors cssClass="error" path="makeName" />
	<br />		
	
	<form:label path="number">
	<spring:message code="sponsorship.number"/>*:
	</form:label>
	<form:input path="number"/>
	<form:errors cssClass="error" path="number" />
	<br />
	
	<form:label path="expirationYear">
	<spring:message code="sponsorship.expirationYear"/>*:
	</form:label>
	<form:input path="expirationYear"/>
	<form:errors cssClass="error" path="expirationYear" />
	<br />
	
	<form:label path="expirationMonth">
	<spring:message code="sponsorship.expirationMonth"/>*:
	</form:label>
	<form:input path="expirationMonth"/>
	<form:errors cssClass="error" path="expirationMonth" />
	<br />
	
	<form:label path="cvv">
	<spring:message code="sponsorship.cvv"/>*:
	</form:label>
	<form:input path="cvv"/>
	<form:errors cssClass="error" path="cvv" />
	<br />
	
</fieldset>
	<br />
<fieldset>
	<legend align="left">
		<spring:message code="sponsorship.info" />
	</legend>
	
	<form:label path="banner">
		<spring:message code="sponsorship.banner"/>*:
	</form:label>
	<form:input path="banner"/>
	<form:errors cssClass="error" path="banner" />
	<br />
				
	<form:label path="targetUrl">
		<spring:message code="sponsorship.targetUrl"/>*:
	</form:label>
	<form:input path="targetUrl"/>
	<form:errors cssClass="error" path="targetUrl" />
	<br />
				

</fieldset>

	<spring:message code="asterisco"/>
		<br />	<br />

		
	<input type ="submit" name="save" value="<spring:message code="sponsorship.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="sponsorship.cancel" />" onclick="javascript:relativeRedir('sponsorship/sponsor/list.do');" />
	
	<jstl:if test="${sponsorship.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="sponsorship.delete" />"
			onclick="return confirm('<spring:message code="sponsorship.confirm.delete" />')" />&nbsp;
	</jstl:if>
</form:form>










