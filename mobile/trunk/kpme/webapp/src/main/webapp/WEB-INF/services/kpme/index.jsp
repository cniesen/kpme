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

<spring:message code="kpme.currenttime" 	var="currenttime"/>
<spring:message code="kpme.workstatus" 	var="workstatus"/>
<spring:message code="kpme.assignments" 	var="assignments"/>
<spring:message code="kpme.since" 	var="since"/>

<style type="text/css">
.min-width-480px .ui-select {
           width: 100%;//Before it was 60%.
          display: inline-block;
          }
          .ui-li .ui-btn-text {
text-overflow: ellipsis;
overflow: hidden;
white-space: pre-wrap;
font-size: 10px;
}
span.ui-btn-inner {
white-space: pre-wrap;
}
</style>

<kme:page title="Clock Entry" id="kpme-webapp" backButton="true" homeButton="true" cssFilename="kpme" jsFilename="kpme">
	<kme:content><center>
	<img src="${pageContext.request.contextPath}/images/kpme_image.png" />
	<form:form action="${pageContext.request.contextPath}/kpme/index" commandName="kpme" data-ajax="false" method="post">
	
	
	 <div class="ui-grid-a"  style="margin-bottom: 10px; margin-top: 10px;">

          <div class="ui-block-c" style="color: #770000;width: 40%;text-align: right">${currenttime}&nbsp;&nbsp;</div>

          <div  class="ui-block-d" id="currentTime"></div>
          </div>
          <div class="ui-grid-a" style="margin-bottom: 10px;">
          <div class="ui-block-c" style="color: #770000;width: 40%;text-align: right;">${workstatus}&nbsp;&nbsp;</div>
          <div  class="ui-block-d" id="currentTime" >${clockEntry.workStatus}</div>
     </div>
     <div class="ui-grid-a" style="margin-bottom: 10px;">
          <div class="ui-block-c" style="color: #770000;width: 40%;text-align: right;">${since}&nbsp;&nbsp;</div>
          <div  class="ui-block-d" id="currentTime">${clockEntry.lastClockEntry}</div>
     </div>
            <fieldset>
                
                <form:select id="assignments" data-theme="c" path="value" data-native-menu="false"  multiple="false"  items="${clockEntry.assignKeyToAssignmentDescriptions}" class="required" style="white-space: pre-wrap;"/>
                 
            </fieldset>
                        
            <input data-theme="c" class="submit" type="submit" value="${clockEntry.clockAction}" />
                       
        </form:form>
        <script type="text/javascript">
		function startTime()
		{
		            var curtime = new Date();
		            var curhour = curtime.getHours();
		            var curmin = curtime.getMinutes();
		            var cursec = curtime.getSeconds();
		            var month = curtime.getMonth() + 1;
		            var day = curtime.getDate();
		            var year = curtime.getFullYear();
		            var time = month + "/" + day + "/" + year + " ";
		            var time = " ";
		            if(curhour == 0) curhour = 12;
		            time += (curhour > 12 ? curhour - 12 : curhour) + ":" +
		                   (curmin < 10 ? "0" : "") + curmin + ":" +
		                   (cursec < 10 ? "0" : "") + cursec + " " +
		                   (curhour > 12 ? "PM" : "AM");
		document.getElementById("currentTime").innerHTML=time.toString();
		t=setTimeout('startTime()',500);
		}
		
		startTime();		
		</script>
		</center>
		${message}
	</kme:content>
</kme:page>