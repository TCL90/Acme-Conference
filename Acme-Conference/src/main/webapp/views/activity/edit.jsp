<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<jstl:if test="${type=='presentation' }">
<form:form action="activity/administrator/edit.do"
	modelAttribute="presentation">
	<form:hidden path="id" />
	<jstl:if test="${presentation.id==0 }">
	<form:hidden path="conference"/>
	<form:hidden path="cameraReadyPaper"/>
	</jstl:if>

	<fieldset>
		<legend align="left">
			<spring:message code="edit.activity" />
		</legend>

		<form:label path="title">
		<spring:message code="activity.title" />* :
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br /> <br />
		
		<form:label path="speakers">
		<spring:message code="activity.speakers" />* :
		</form:label>
		<form:textarea path="speakers" />
		<form:errors cssClass="error" path="speakers" />
		<br /> <br />

		<form:label path="startMoment">
			<spring:message code="activity.startMoment" />* (yyyy-MM-dd):
		</form:label>
		<form:input path="startMoment" />
		<form:errors cssClass="error" path="startMoment" placeHolder="2019-05-02"/>
		<br /> <br />
		
		<form:label path="duration">
		<spring:message code="activity.duration" />* :
		</form:label>
		<form:input type="number" path="duration" />
		<form:errors cssClass="error" path="duration" />
		<br /> <br />
		
		<form:label path="room">
		<spring:message code="activity.room" />* :
		</form:label>
		<form:input path="room" />
		<form:errors cssClass="error" path="room" />
		<br /> <br />
		
		<form:label path="summary">
		<spring:message code="activity.summary" />* :
		</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br /> <br />

		<form:label path="attachments">
		<spring:message code="activity.attachments" /> :
		</form:label>
		<form:textarea path="attachments" />
		<form:errors cssClass="error" path="attachments" />
		<br /> <br />
	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="savePresentation"
		value="<spring:message code="activity.save" />" />&nbsp;
	<jstl:if test="${presentation.id!=0 }">
	<jstl:if test="${presentation.conference.finalMode==false}">
	<input type="submit" name="deletePresentation"
		value="<spring:message code="activity.delete" />"
		onclick="return confirm('<spring:message code="activity.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('conference/administrator/list.do')"
		value="<spring:message code="activity.edit.cancel" />" />

</form:form>
</jstl:if>

<jstl:if test="${type=='panel' }">
<form:form action="activity/administrator/edit.do"
	modelAttribute="panel">
	<form:hidden path="id" />
	<jstl:if test="${panel.id==0 }">
	<form:hidden path="conference"/>
	</jstl:if>

	<fieldset>
		<legend align="left">
			<spring:message code="edit.activity" />
		</legend>

		<form:label path="title">
		<spring:message code="activity.title" />* :
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br /> <br />
		
		<form:label path="speakers">
		<spring:message code="activity.speakers" />* :
		</form:label>
		<form:textarea path="speakers" />
		<form:errors cssClass="error" path="speakers" />
		<br /> <br />

		<form:label path="startMoment">
			<spring:message code="activity.startMoment" />* (yyyy-MM-dd):
		</form:label>
		<form:input path="startMoment" />
		<form:errors cssClass="error" path="startMoment" placeHolder="2019-05-02"/>
		<br /> <br />
		
		<form:label path="duration">
		<spring:message code="activity.duration" />* :
		</form:label>
		<form:input type="number" path="duration" />
		<form:errors cssClass="error" path="duration" />
		<br /> <br />
		
		<form:label path="room">
		<spring:message code="activity.room" />* :
		</form:label>
		<form:input path="room" />
		<form:errors cssClass="error" path="room" />
		<br /> <br />
		
		<form:label path="summary">
		<spring:message code="activity.summary" />* :
		</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br /> <br />

		<form:label path="attachments">
		<spring:message code="activity.attachments" /> :
		</form:label>
		<form:textarea path="attachments" />
		<form:errors cssClass="error" path="attachments" />
		<br /> <br />
	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="savePanel"
		value="<spring:message code="activity.save" />" />&nbsp;
	<jstl:if test="${panel.id!=0 }">
	<jstl:if test="${panel.conference.finalMode==false}">
	<input type="submit" name="deletePanel"
		value="<spring:message code="activity.delete" />"
		onclick="return confirm('<spring:message code="activity.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('conference/administrator/list.do')"
		value="<spring:message code="activity.edit.cancel" />" />

</form:form>
</jstl:if>

<jstl:if test="${type=='tutorial' }">
<form:form action="activity/administrator/edit.do"
	modelAttribute="tutorial">
	<form:hidden path="id" />
	<jstl:if test="${tutorial.id==0 }">
	<form:hidden path="conference"/>
	</jstl:if>

	<fieldset>
		<legend align="left">
			<spring:message code="edit.activity" />
		</legend>

		<form:label path="title">
		<spring:message code="activity.title" />* :
		</form:label>
		<form:input path="title" />
		<form:errors cssClass="error" path="title" />
		<br /> <br />
		
		<form:label path="speakers">
		<spring:message code="activity.speakers" />* :
		</form:label>
		<form:textarea path="speakers" />
		<form:errors cssClass="error" path="speakers" />
		<br /> <br />

		<form:label path="startMoment">
			<spring:message code="activity.startMoment" />* (yyyy-MM-dd):
		</form:label>
		<form:input path="startMoment" />
		<form:errors cssClass="error" path="startMoment" placeHolder="2019-05-02"/>
		<br /> <br />
		
		<form:label path="duration">
		<spring:message code="activity.duration" />* :
		</form:label>
		<form:input type="number" path="duration" />
		<form:errors cssClass="error" path="duration" />
		<br /> <br />
		
		<form:label path="room">
		<spring:message code="activity.room" />* :
		</form:label>
		<form:input path="room" />
		<form:errors cssClass="error" path="room" />
		<br /> <br />
		
		<form:label path="summary">
		<spring:message code="activity.summary" />* :
		</form:label>
		<form:textarea path="summary" />
		<form:errors cssClass="error" path="summary" />
		<br /> <br />

		<form:label path="attachments">
		<spring:message code="activity.attachments" />* :
		</form:label>
		<form:textarea path="attachments" />
		<form:errors cssClass="error" path="attachments" />
		<br /> <br />
	</fieldset>
	<br />
	<br />
	
	<input type="submit" name="saveTutorial"
		value="<spring:message code="activity.save" />" />&nbsp;
	<jstl:if test="${tutorial.id!=0 }">
	<jstl:if test="${tutorial.conference.finalMode==false}">
	<input type="submit" name="deleteTutorial"
		value="<spring:message code="activity.delete" />"
		onclick="return confirm('<spring:message code="activity.confirm.delete" />')" />&nbsp;
	</jstl:if>
	</jstl:if>
	<input type="button" name="cancel"
		onclick="javascript: window.location.replace('conference/administrator/list.do')"
		value="<spring:message code="activity.edit.cancel" />" />

</form:form>
</jstl:if>