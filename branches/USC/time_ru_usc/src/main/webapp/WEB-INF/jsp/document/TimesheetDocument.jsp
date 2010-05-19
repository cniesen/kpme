<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/tlds.jsp"%>
<%@ page import="java.util.Date"%>

<c:if test="${StrutsActionForm.kioskUser}">
 <%@ include file="../noCache.jsp"%>
</c:if>
 
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


<c:choose>
	<c:when test="${StrutsActionForm.command != 'displayActionListInlineView'}">
		<hr:page nestingPath="timesheetDocument" headerColumns="documentHeader.documentId,documentHeader.payEndDate,documentHeader.universityId,name" hiddenFields="documentId,documentStatus,documentHeader.documentLock.documentId,documentHeader.documentLock.userUniversityId,documentHeader.documentLock.timestamp,documentHeader.documentStatus,assignmentTimeOffsets,serverLocationTimeOffset,serverTimeMillisAtLoad,userLocationPreferenceOffset" startClock="true" waitToFinishLoadingPage="true">
           <html:hidden property="ignoreClockOnlyStatus" value="true" />
           <div id="policy" align="center">
              <br> Each employee is responsible for accurately recording their time and attendance hours using the TIME system. <br>
              Use of this timesheet is governed by the Timekeeping Policy available for review <a href="http://www.indiana.edu/~vpcfo/policies/payroll/iv-270.html" target="_new">here</a>. <br><br>
           </div>
		
			<!-- TODO:  Need to figure out how to display this link.  This is supposed to be a javascript focusInOn url, but we need to post.  Can we combine the javascript functions? -->
            <c:choose>
				<c:when test="${!StrutsActionForm.mode.editable && StrutsActionForm.editAllowed}">
					<a href="${StrutsActionForm.editUrl}"><fmt:message key="edit.image.html" bundle="${springMessages}" /></a><br><br>
				</c:when>
	            <c:otherwise>
		            <div class="globalbuttons">
		                <c:if test="${StrutsActionForm.modeApplicable}">
		                    <html:hidden name="StrutsActionForm" property="mode.editable" styleId="mode.editable"/>
		                    <c:forTokens items="${StrutsActionForm.mode.eventNames}" delims="," var="event" varStatus="current">
		                        <c:choose>
		                            <c:when test="${event eq 'exitCancel'}">
		                                <a href="${StrutsActionForm.exitCancelUrl}"><fmt:message key="${StrutsActionForm.exitCancelImageKey}" bundle="${springMessages}" /></a>
		                            </c:when>
		                            <c:otherwise>
		                                <c:set var="fullUrl" value="${event}Url" />
		                                <a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
		                                <fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
		
		                            </c:otherwise>
		                        </c:choose>
		                    </c:forTokens>
		                </c:if>
		                <br>
		                <img src="jsp/images/pixel_clear.gif" alt="" width="20" height="20">
		            </div>
	            </c:otherwise>
            </c:choose>
            
            <div id="waitForPageToLoad" align="center" style="color:red">
                 Please wait for the page to complete loading...
            </div> 
			
            <!--  condition to display/hide Hour Distribution checkbox -->
            <c:set var="hourDistribution" value=""/>
             <c:if test="${StrutsActionForm.timesheetDocument.clock.hourDistributionAllowed}">
                 <c:set var="hourDistribution" value=",userDoingHourDistribution"/>
			 </c:if>
			 
			<!-- TODO:  need to be able to hide the translates on the timesheetDocument too -->
			<c:if test="${StrutsActionForm.timesheetDocument.documentHeader.documentStatus ne 'F' and StrutsActionForm.timesheetDocument.documentHeader.documentStatus ne 'R'}">
            	<c:choose>
				<c:when test="${StrutsActionForm.timesheetDocument.clock.mode.editable}">
					<c:choose>
						<c:when test="${StrutsActionForm.editedByAdministrator}">
			    			<hr:entityContainer nestingPath="timesheetDocument.clock" columns="workStatus,clockAssignment,clockTime${hourDistribution}" hiddenFields="clockLog.universityId,clockLog.employeeRecord,clockLog.workArea,clockLog.task,clockLog.clockTime,clockLog.action,clockLog.userUniversityId,clockLog.timestamp,clockLog.timeZone" startClock="true" />
						</c:when>
						<c:otherwise>
			    		  	<hr:entityContainer nestingPath="timesheetDocument.clock" columns="workStatus,clockAssignment${hourDistribution}" hiddenFields="clockLog.universityId,clockLog.employeeRecord,clockLog.workArea,clockLog.task,clockLog.clockTime,clockLog.action,clockLog.userUniversityId,clockLog.timestamp,clockLog.timeZone" startClock="true" />
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
            </c:if>
				<br>
				
				<%--
			<c:if test="${StrutsActionForm.clockOnlyTimesheet && StrutsActionForm.allowTimesheetLite}">
				<center><html:link href="TimesheetDocument.do?method=open&ignoreClockOnlyStatus=false">Simple View</html:link>
				    <br><br>
			  		<font size="-2">
					Recent Clock Out actions may still be processing. If your Work Status is correct but <br>
					a corresponding Hours Detail record is not yet created below, please try opening your timesheet again in a few minutes.
					</font> 
				</center>
			</c:if>
				--%>
				
		   <div align="right">
		      <a href="javascript:expandAllTabs()"><img src="jsp/images/tinybutton-expandall.gif" alt="Expand all tabs" width="73" height="15" border="0" align="absmiddle"></a>
		      <a href="javascript:collapseAllTabs()"><img src="jsp/images/tinybutton-collapseall.gif" alt="Collapse all tabs" width="73" height="15" border="0" align="absmiddle"></a>
		   </div>
		
			<hr:listMaintenance nestingPath="timesheetDocument.hours.hoursDetail" columns="" hiddenFields="date,tabStatus" style="horizontal" subheadDisplayField="displayDate" actions="suppress">
				        <hr:listMaintenance nestingPath="hourDetail" columns="assignment,earnCode,hours,beginTime,endTime,overtimeEarnCode,overtimeHours" hiddenFields="userUniversityId,timestamp,shiftEarnCode,shiftHours,premiumEarnCode,premiumHours,beginTsTz,endTsTz" childList="true" style="horizontal-multiple-row" subheadDisplayField="suppress"/>			
			</hr:listMaintenance>
		
		    <hr:tabHeader maintainableListName="Hours Summary" id="0" erroneous="false" tabHeader="false"/>
			<hr:hoursSummary nestingPath="timesheetDocument.hoursSummary" />
			
			<hr:hidden nestingPath="timesheetDocument.hours.payCalendar" hiddenFields="company,paygroup,payEndDate,payBeginDate,offCycle,period,periodWeeks"/>
			<html:hidden name="StrutsActionForm" property="scrollx" styleId="scrollx"/>
			<html:hidden name="StrutsActionForm" property="scrolly" styleId="scrolly"/>

			<hr:hiddenMaintainableList nestingPath="timesheetDocument.documentHeader.timeBlock" hiddenFields="documentId,employeeRecord,workArea,task,earnCode,beginTime,endTime,beginTsTz,endTsTz,createdByClock,hours,overtimeEarnCode,shiftEarnCode,shiftHours,premiumEarnCode,premiumHours,userUniversityId,timestamp"/>
		    <hr:listMaintenance nestingPath="timesheetDocument.job" columns="universityId,employeeRecord,effectiveDate,effectiveSequence,action,businessUnit,department,location,salaryPlan,grade,standardHours,paygroup,compensationRate,fte,employeeStatus,jobIndicator,employeeType" hiddenFields="actionReason,position,company,jobcode,employeeClass,earnsDistributionType" subheadDisplayField="displayRecord" actions="supress" tabHeader="false" tabStatus="collapsed">
		   	    <hr:listMaintenance nestingPath="assignment" columns="endDate,earnCode,chartOfAccounts,account,subAccount,project,object,workArea,task,organizationReferenceId,percent"  hiddenFields="subAccount,object,subObject,project,additionalSequence,order" childList="true" style="horizontal" subheadDisplayField="assignmentDescription" actions="supress"/>
			</hr:listMaintenance>

			<c:set var="displayLeaveBal" value="0" />
			<c:forEach items="${StrutsActionForm.timesheetDocument.jobs}" var="job">
				<c:if test="${job.employeeType != 'H'}">
					<c:set var="displayLeaveBal" value="1" />
				</c:if>
			</c:forEach>
			<c:if test="${displayLeaveBal == 1}">
				<hr:listMaintenance nestingPath="timesheetDocument.leaveBalance" columns="planDescription,leaveBalance,accrualProcessDate" style="horizontal-multiple-row" actions="suppress" tabHeader="false" tabStatus="collapsed" subheadDisplayField="Leave Balances"/>
			</c:if>
		
			<hr:hidden nestingPath="timesheetDocument" hiddenFields="assignmentEarnCodes,hoursEntryEarnCodes"/>
		
			<c:if test="${!StrutsActionForm.user.cardAuthenticated}">
			<hr:tabHeader maintainableListName="Notes" id="0" erroneous="false" tabHeader="false"/>
				<div class="tab-container" align="center" id="G${tableBodyId}">
					<table width="100%" cellpadding="0" cellspacing="0" class="null">
						<tr>
							<td width="100%">
		    					<iframe src="${StrutsActionForm.workflowBaseUrl}Note.do?docId=${StrutsActionForm.timesheetDocument.documentId}&attachmentTarget=_blank" height="200" width="100%" scrolling="auto"> </iframe>
		    				</td>
						</tr>
					</table>
		    	</div>
			</c:if>
		    <hr:tabFooter/>
		</hr:page>
	</c:when>
	<c:otherwise>
		<hr:pageLite>
			<hr:tabHeader maintainableListName="Hours Summary" id="0" erroneous="false"/>
				<hr:hoursSummary nestingPath="timesheetDocument.hoursSummary" />
			<hr:tabFooter/>
		</hr:pageLite>

	</c:otherwise>
</c:choose>

<script language="javascript">
	finishedLoadingPage = true;
	var waitForPageToLoad = document.getElementById("waitForPageToLoad");
	waitForPageToLoad.style.display="none";
</script>
