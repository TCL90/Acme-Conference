<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="reviewer/register.do"
	modelAttribute="reviewer">

	<form:hidden path="userAccount.authorities" />


	<fieldset>
		<legend align="left">
			<spring:message code="reviewer.edit.contact" />
		</legend>

		<form:label path="name">
			<spring:message code="reviewer.name" />* :
		</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" />

		<br /> <br />

		<form:label path="surname">
			<spring:message code="reviewer.surname" />* :
		</form:label>
		<form:input path="surname" />
		<form:errors cssClass="error" path="surname" />

		<br /> <br />

		<form:label path="middleName">
			<spring:message code="reviewer.middleName" />:
		</form:label>
		<form:input path="middleName" />
		<form:errors cssClass="error" path="middleName" />

		<br /> <br />

		<form:label path="address">
			<spring:message code="reviewer.address" />:
		</form:label>
		<form:input path="address" />
		<form:errors cssClass="error" path="address" />

		<br /> <br />

		<form:label path="email">
			<spring:message code="reviewer.email" />*:
		</form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="email" />
		<br /> <br />


		<form:label path="phoneNumber">
			<spring:message code="reviewer.phoneNumber" />:
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
					alert("<spring:message code="reviewer.confirm" />");
				}
			}
		</script>
		<br /> <br />

		<form:label path="photo">
			<spring:message code="reviewer.photo" />:
		</form:label>
		<form:input path="photo" />
		<form:errors cssClass="error" path="photo" />

		<br /> <br />
		
		<form:label path="expertise">
			<spring:message code="reviewer.expertise" />:
		</form:label>
		<form:input path="expertise" placeholder="artificial intelligence, big data"/>
		<form:errors cssClass="error" path="expertise" />

		<br /> <br />

	</fieldset>
	<br />


	<fieldset>
		<legend align="left">
			<spring:message code="reviewer.edit.userAccount" />
		</legend>
		<form:label path="userAccount.username">
			<spring:message code="reviewer.username" />* :
		</form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username" />

		<br /> <br />

		<form:label path="userAccount.password">
			<spring:message code="reviewer.password" />* :
		</form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password" />
	</fieldset>



	<spring:message code="asterisco"/>
	<br />
	<br />
	<input type="submit" name="save"
		value="<spring:message code="reviewer.edit.save" />" />&nbsp;
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="reviewer.edit.cancel" />" />

</form:form>