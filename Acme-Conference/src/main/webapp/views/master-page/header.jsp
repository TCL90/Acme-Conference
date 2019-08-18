<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<!-- <a href="#"><img src="images/logo.png" alt="Acme Conference Co., Inc." /></a> -->
	
	<a href="#"><img src="${customisation.bannerUrl}" alt="${customisation.systemName}" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/administrator/list.do"><spring:message code="master.page.administrator.conferences.list" /></a></li>
					<li><a href="administrator/administrator/create.do"><spring:message code="master.page.administrator.create" /></a></li>
					<li><a href="administrator/administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
					<li><a href="activity/administrator/list.do"><spring:message code="master.page.administrator.activities.list" /></a></li>				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('AUTHOR')">
			<li><a class="fNiv"><spring:message	code="master.page.author" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="author/author/edit.do"><spring:message code="master.page.edit.author" /></a></li>
					<li><a href="registration/author/list.do"><spring:message code="master.page.author.registration" /></a></li>
					<li><a href="submission/author/list.do"><spring:message code="master.page.author.submission" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.author" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsor/sponsor/edit.do"><spring:message code="master.page.edit.sponsor" /></a></li>
					<li><a href="sponsorship/sponsor/list.do"><spring:message code="master.page.sponsor.sponsorship" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('REVIEWER')">
			<li><a class="fNiv"><spring:message	code="master.page.reviewer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="reviewer/reviewer/edit.do"><spring:message code="master.page.edit.reviewer" /></a></li>
					<li><a href="submission/reviewer/list.do"><spring:message code="master.page.reviewer.submission" /></a></li>
					<li><a href="report/reviewer/list.do"><spring:message code="master.page.reviewer.report" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="author/register.do"><spring:message code="master.page.register.author" /></a></li>
			<li><a class="fNiv" href="reviewer/register.do"><spring:message code="master.page.register.reviewer" /></a></li>
			<li><a class="fNiv" href="sponsor/register.do"><spring:message code="master.page.register.sponsor" /></a></li>
			<li><a class="fNiv" href="conference/list.do"><spring:message code="master.page.conference.list" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<spring:message code="master.page.search.placeholder" var="placeholder"/>
			<li><form action="conference/search.do"><div>
    				<input type="search" id="search" name="q"
    				placeholder="${placeholder }" size="20" required>
    				<button><spring:message code="master.page.search"/></button>
  					  </div>
				</form>
			</li>
		
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<security:authorize access="!hasRole('ADMIN')">
				<li><a class="fNiv" href="conference/list.do"><spring:message code="master.page.conference.list" /></a></li>
			</security:authorize>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			
			<spring:message code="master.page.search.placeholder" var="placeholder"/>
			<security:authorize access="!hasRole('ADMIN')">
			<li><form action="conference/search.do"><div>
    				<input type="search" id="search" name="q"
    				placeholder="${placeholder }" size="20" required>
    				<button><spring:message code="master.page.search"/></button>
  					  </div>
				</form>
			</security:authorize>
			
			<security:authorize access="hasRole('ADMIN')">
			<li><form action="conference/administrator/search.do"><div>
    				<input type="search" id="search" name="q"
    				placeholder="${placeholder }" size="20" required>
    				<button><spring:message code="master.page.search"/></button>
  					  </div>
				</form>
			</li>
			</security:authorize>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

