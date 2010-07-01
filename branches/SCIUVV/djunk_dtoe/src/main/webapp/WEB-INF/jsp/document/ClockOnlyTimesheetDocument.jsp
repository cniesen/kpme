<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/tlds.jsp"%>
<%@ include file="../noCache.jsp"%>
<%@page import="java.util.Date"%>

<c:if test="${param.method == 'unlockDocument'}">
	<script language="javascript">
	 	window.close();
	</script>
</c:if>
<c:if test="${!StrutsActionForm.erroneous && (param.method == 'approve')}">
	<script language="javascript">
	 	parent.window.close();
	</script>
</c:if>

<script language="javascript">

finishedLoadingPage = true;
startClock = true; 

function setScrollCoordinates() {
 if(document.forms[0].scrollx){
  document.forms[0].scrollx.value = (document.all)?document.documentElement.scrollLeft:window.pageXOffset;
  document.forms[0].scrolly.value = (document.all)?document.documentElement.scrollTop:window.pageYOffset;
 }
}

function postToOperation(operation){
        document.forms[0].action = operation;
        document.forms[0].onSubmit = setScrollCoordinates();
        document.forms[0].submit();
}


        <jsp:useBean id="serverTime" class="java.util.Date"/>
        var serverTime = new Date();
        serverTime.setTime(<%= serverTime.getTime() %>);
        var clientTime = new Date();
        var offsetMilliseconds = serverTime.getTime() - clientTime.getTime();
        
        var dayNames=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday');
        var monthNames=new Array('January','February','March','April','May','June','July','August','September','October','November','December');
       
        function displayServerTime() {
           var clientNow = new Date();    
           var serverNow = new Date(clientNow.getTime() + offsetMilliseconds);
           document.getElementById('clock').innerHTML=
                           dayNames[serverNow.getDay()]+' '
                          +monthNames[serverNow.getMonth()]+' '
                          +leadingZero(serverNow.getDate())+', '
                          +fixYear4(serverNow.getYear())+' '
                          +twelveHour(serverNow.getHours())+':'
                          +leadingZero(serverNow.getMinutes())+':'
                          +leadingZero(serverNow.getSeconds())+' '
                          +amPMsymbol(serverNow.getHours());  
           setTimeout('displayServerTime()',1000); 
        }
        function leadingZero(x){
           return (x>9)?x:'0'+x;
        }
        function twelveHour(x){
           if(x==0){
              x=12;
           }
           return (x>12)?x-=12:x;  
        }
        function amPMsymbol(x){
           return (x>11)?'PM':'AM';  
        }
        function fixYear4(x){
           return (x<500)?x+1900:x;        
		}
</script>

<BODY onLoad="displayServerTime()">
<hr:page nestingPath="timesheetDocument" headerColumns="documentHeader.documentId,documentHeader.payEndDate,documentHeader.universityId,name" hiddenFields="documentId,documentStatus,documentHeader.documentLock.documentId,documentHeader.documentLock.userUniversityId,documentHeader.documentLock.timestamp,documentHeader.documentStatus,assignmentTimeOffsets,serverLocationTimeOffset,serverTimeMillisAtLoad,userLocationPreferenceOffset" startClock="true" waitToFinishLoadingPage="false" isLiteMode="true">
           <div id="policy" align="center">
              <br> Each employee is responsible for accurately recording their time and attendance hours using the TIME system. <br>
              Use of this timesheet is governed by the Timekeeping Policy available for review <a href="http://www.indiana.edu/~vpcfo/policies/payroll/iv-270.html" target="_new">here</a>. <br><br>
           </div>

            <!--  condition to display/hide Hour Distribution checkbox -->
            <c:set var="hourDistribution" value=""/>
             <c:if test="${StrutsActionForm.timesheetDocument.clock.hourDistributionAllowed}">
                 <c:set var="hourDistribution" value=",userDoingHourDistribution"/>
			 </c:if>

			<!-- TODO:  need to be able to hide the translates on the timesheetDocument too -->
            <c:choose>
			<c:when test="${StrutsActionForm.timesheetDocument.clock.mode.editable}">
				<c:choose>
					<c:when test="${StrutsActionForm.editedByAdministrator}">
			    		<hr:entityContainer nestingPath="timesheetDocument.clock" columns="workStatus,clockAssignment,clockTime${hourDistribution}" hiddenFields="clockLog.universityId,clockLog.employeeRecord,clockLog.workArea,clockLog.task,clockLog.clockTime,clockLog.action,clockLog.userUniversityId,clockLog.timestamp,clockLog.timeZone" startClock="true" columnCount="1" />
					</c:when>
					<c:otherwise>

			    		  <hr:entityContainer nestingPath="timesheetDocument.clock" columns="workStatus,clockAssignment${hourDistribution}" hiddenFields="clockLog.universityId,clockLog.employeeRecord,clockLog.workArea,clockLog.task,clockLog.clockTime,clockLog.action,clockLog.userUniversityId,clockLog.timestamp,clockLog.timeZone" startClock="true" columnCount="1" />

					</c:otherwise>
		    	</c:choose>
            </c:when>
            <c:otherwise>
                <c:if test="${StrutsActionForm.timesheetDocument.clock.hasClockAssignment}">
                    <p class="warning">Clock actions are not available while editing timesheet.  Please save or close to exit edit mode.</p>
                    <hr:entityContainer nestingPath="timesheetDocument.clock" columns="workStatus,clockAssignment${hourDistribution}" hiddenFields="clockLog.universityId,clockLog.employeeRecord,clockLog.workArea,clockLog.task,clockLog.clockTime,clockLog.action,clockLog.userUniversityId,clockLog.timestamp,clockLog.timeZone" startClock="true" />
                </c:if>
            </c:otherwise>
            </c:choose>
				<br>

		<center><html:link href="TimesheetDocument.do?method=open&ignoreClockOnlyStatus=true&clearLiteMode=true">Detailed View</html:link></center>
		<hr:hidden nestingPath="timesheetDocument.hours.payCalendar" hiddenFields="company,paygroup,payEndDate,payBeginDate,offCycle,period,periodWeeks"/>
		</hr:page>
</BODY>	