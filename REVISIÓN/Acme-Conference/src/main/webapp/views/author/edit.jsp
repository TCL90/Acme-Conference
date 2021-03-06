<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="author/author/edit.do"
	modelAttribute="author">

	<form:hidden path="userAccount" />
	<form:hidden path="finder" />
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="boxes" />
	<form:hidden path="score" />


	<fieldset>
		<legend align="left">
			<spring:message code="author.edit.contact" />
		</legend>

		<form:label path="name">
			<spring:message code="author.name" />* :
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />

		<br /> <br />

		<form:label path="surname">
			<spring:message code="author.surname" />* :
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />

		<br /> <br />
		
		<form:label path="middleName">
			<spring:message code="author.middleName" />:
		</form:label>
		<form:input path="middleName" />
		<form:errors cssClass="error" path="middleName" />

		<br /> <br />

		<form:label path="address">
			<spring:message code="author.address" />:
		</form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />

		<br /> <br />

		<form:label path="email">
			<spring:message code="author.email" />*:
		</form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="email" />
		<br /> <br />


		<form:label path="phoneNumber">
			<spring:message code="author.phoneNumber" />:
		</form:label>
		<form:input path="phoneNumber" onchange="check(this)"
			pattern="^(\d|\(|\)| |\+)+$" />
		<form:errors cssClass="error" path="phoneNumber" />

		<script language='javascript' type='text/javascript'>
			var re = /^\+\d{1,3} \(\d{1,3}\) \d{4,}$/;
			var re2 = /^\+\d{1,3} \d{4,}$/;
			var re3 = /^\d{4,}$/;

			function check(input) {
				var OK = re.exec(input.value);
				var OK2 = re2.exec(input.value);
				var OK3 = re3.exec(input.value);
				if (!(OK || OK2 || OK3)) {
					alert("<spring:message code="author.confirm" />");
				}
			}
		</script>
		<br /> <br />

		<form:label path="photo">
			<spring:message code="author.photo" />:
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />

		<br /> <br />

	</fieldset>
	<br />

	<spring:message code="asterisco"/>
	<br />
	<br />
	<input type="submit" name="save"
		value="<spring:message code="author.edit.save" />" />&nbsp;
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="author.edit.cancel" />" />

</form:form>