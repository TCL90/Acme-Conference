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


<form:form modelAttribute="registration" action="registration/author/edit.do">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="author" />
	<form:hidden path="conference" />

<fieldset>

	<legend align="left">
		<spring:message code="registration.creditcard" />
	</legend>

	<form:label path="holderName">
	<spring:message code="registration.holderName"/>*:
	</form:label>
	<form:input path="holderName"/>
	<form:errors cssClass="error" path="holderName" />
	<br />
	
	<form:label path="makeName">
	<spring:message code="registration.makeName"/>*:
	</form:label>
	<form:input path="makeName"/>
	<form:errors cssClass="error" path="makeName" />
	<br />
	
	<form:label path="number">
	<spring:message code="registration.number"/>*:
	</form:label>
	<form:input path="number"/>
	<form:errors cssClass="error" path="number" />
	<br />
	
	<form:label path="expirationYear">
	<spring:message code="registration.expirationYear"/>*:
	</form:label>
	<form:input path="expirationYear"/>
	<form:errors cssClass="error" path="expirationYear" />
	<br />
	
	<form:label path="expirationMonth">
	<spring:message code="registration.expirationMonth"/>*:
	</form:label>
	<form:input path="expirationMonth"/>
	<form:errors cssClass="error" path="expirationMonth" />
	<br />
	
	<form:label path="cvv">
	<spring:message code="registration.cvv"/>*:
	</form:label>
	<form:input path="cvv"/>
	<form:errors cssClass="error" path="cvv" />
	<br />
	
</fieldset>
	<br />
<fieldset>
	<legend align="left">
		<spring:message code="registration.info" />
	</legend>
	
	<form:label path="conference">
		<spring:message code="registration.conference"/>*:
	</form:label>				
	<form:select path="conference">
		<form:option label="----" value="0" />
		<form:options items="${conferences}"  itemLabel = "title"/>
		</form:select>
	<form:errors cssClass="error" path="conference" />
	<br />				
</fieldset>
	<spring:message code="asterisco"/>
		<br />	<br />
	<input type ="submit" name="save" value="<spring:message code="registration.save"/>" />
	
	<input type="button" name="cancel" value="<spring:message code="registration.cancel" />" onclick="javascript:relativeRedir('registration/author/list.do');" />

</form:form>










