<%@ include file="/jsp/tlds.jsp" %>
<c:set var="searchCategory" value="&searchCategory="/>
<c:set var="maintenanceSearchCategory" value="${searchCategory}maintenance"/>

<html>
<head>
	<title>TK Index</title>
	<script language="Javascript">
		function openNewWindow(url) {
		    var activeWindow = window.open(url, "_blank", "toolbar=yes,status=yes,location=yes,menubar=yes,scrollbars=yes,resizable=yes,directories=yes,left=0,top=0,height=" + screen.availHeight + ",width=" + screen.availWidth);
		    activeWindow.focus();
		}
	</script>
	<link href="https://docs.onestart.iu.edu/dav/MY/channels/style-channel.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
	    	<th colspan="2">Rules</th>
	  	</tr>
	  	<tr>
    		<td colspan="2" height="5">
    			<fmt:message key="clearpixel.image.html" bundle="${springMessages}"/>
    		</td>
		</tr>
		<tr>
			<td>
				<ul>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.ClockLocationRuleMaintenanceLookupForm']}')">Clock Location</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.DailyOvertimeRuleMaintenanceLookupForm']}')">Daily Overtime</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.DepartmentLunchRuleMaintenanceLookupForm']}')">Department Lunch</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.GracePeriodRuleMaintenanceForm']}')">Grace Period</a></li>
				    <li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.HourlyHoursRuleMaintenanceLookupForm']}')">Hourly Hours</a></li>
				</ul>
		   </td>
		   <td>
				<ul>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.ShiftDifferentialRuleMaintenanceLookupForm']}')">Shift Differential</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.SystemLunchRuleMaintenanceForm']}')">System Lunch</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.TimeCollectionRuleMaintenanceLookupForm']}')">Time Collection</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.WeeklyOvertimeRuleMaintenanceForm']}')">Weekly Overtime</a></li>
				</ul>
		   </td>
		</tr>


		<tr>
	    	<th colspan="2">Maintenance</th>
	  	</tr>
	  	<tr>
    		<td colspan="2" height="5">
    			<fmt:message key="clearpixel.image.html" bundle="${springMessages}"/>
    		</td>
		</tr>
		<tr>
			<td>
				<ul>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.department.client.DepartmentMaintenanceLookupForm']}${maintenanceSearchCategory}&clientFieldsToReturn=department&clientNestingPath=department&clientReturnUrl=${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.department.client.DepartmentMaintenanceForm']}')">Department - Work Area</a></li>
                    <li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.DocumentLockMaintenanceLookupForm']}')">Document Lock</a></li>	
                    <li><a href="javascript:openNewWindow('${StrutsActionForm.lookupBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.person.client.UniversityIdLookupForm']}${maintenanceSearchCategory}&clientFieldsToReturn=universityId&clientNestingPath=userRoles&clientReturnUrl=${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.directory.client.RoleMaintenanceForm']}')">User Role</a></li>
				</ul>
			</td>
			<td>
				<ul>	
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.ClockLogMaintenanceLookupForm']}')">Clock Log</a></li>
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.DocumentHeaderMaintenanceLookupForm']}')">Document Header</a></li>
                    <li><a href="javascript:openNewWindow('https://${pageContext.request.serverName}:${pageContext.request.serverPort}/en-${StrutsActionForm.environment}/RuleQuickLinks.do?rootDocTypeName=TKDocument')">Timesheet Approval Delegation</a></li>					
				</ul>
			</td>
		</tr>
		
		<tr>
	    	<th colspan="2">Inquiry</th>
	  	</tr>
	  	<tr>
    		<td colspan="2" height="5">
    			<fmt:message key="clearpixel.image.html" bundle="${springMessages}"/>
    		</td>
		</tr>
		<tr>
			<td>
				<ul>   
   				    <li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.ActualTimeInquiryForm']}')">Actual Time</a></li>	
				    <li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.TimeBlockHistoryInquiryForm']}')">Time Block History</a></li>	
				    <li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.directory.client.RoleAuditInquiryForm']}')">User Role Audit</a></li>	
					<li><a href="javascript:openNewWindow('${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.client.WorkAreaStatusInquiryForm']}')">Work Area Status</a></li>
				</ul>
			</td>
			<td>
				<ul>
                    <li><a href="javascript:openNewWindow('https://${pageContext.request.serverName}:${pageContext.request.serverPort}/en-${StrutsActionForm.environment}/DocumentSearch.do?docTypeFullName=Timesheet')">Find Timesheets</a></li>
					<li><a href="javascript:openNewWindow('https://${pageContext.request.serverName}:${pageContext.request.serverPort}/en-${StrutsActionForm.environment}/ActionList.do?docType=Timesheet')">Approve Timesheets</a></li>                    					
				    <li><a href="javascript:openNewWindow('http://www.fms.indiana.edu/time/')">TIME Website</a></li>	
				</ul>
			</td>
		</tr>
	</table>
</body>
</html>