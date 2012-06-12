<%--
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="kpme.username" 	var="username"/>
<spring:message code="kpme.password" 	var="password"/>
<spring:message code="kpme.login" 	var="login"/>

<kme:page title="Login" id="kpme-webapp" backButton="true" homeButton="true" cssFilename="kpme" jsFilename="kpme">

	<kme:content>
	<center>
	<img src="${pageContext.request.contextPath}/images/kpme_image.png" />
	</center>
	<form:form action="${pageContext.request.contextPath}/kpme/login" commandName="kpme" data-ajax="false" method="post">
	${message}
            <div class="ui-grid-a"  style="margin-top: 10px;">
                    <label for="username">${username}</label>
                    <form:input id="username" path="name" type="text" value="" class="required"  />
                    </div>
            <div class="ui-grid-a"  style="margin-top: 10px;">
                    <label for="password">${password}</label>
                    <form:input id="password" path="value" type="password" value="" class="required"  />
                        </div>
                        <div class="ui-grid-a"  style="margin-top: 10px;">
                       <input data-theme="c" class="submit" type="submit" value="${login}" />
                       </div>
         
        </form:form>
	</kme:content>
</kme:page>