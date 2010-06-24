<%@ include file="/jsp/tlds.jsp" %>
<c:set var="searchCategory" value="&searchCategory="/>
<c:set var="maintenanceSearchCategory" value="${searchCategory}maintenance"/>

<html>
<head>
	<title>TK Index</title>
	<script language="Javascript">
		function openNewWindow(url) {
		    //parent.location.href=url;
		     window.open(url,"maintenance");
		    //var activeWindow = window.open(url, "_blank", "toolbar=yes,status=yes,location=yes,menubar=yes,scrollbars=yes,resizable=yes,directories=yes,left=0,top=0,height=" + screen.availHeight + ",width=" + screen.availWidth);
		    //activeWindow.focus();
		}

		function backdoor(){
		  window.open("${StrutsActionForm.applicationBaseUrl}TimesheetDocument.do?method=open&backdoorId="+document.backdoorForm.backdoorId.value);
		}
	</script>
	<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="../jsp/css/hr_styles.css" rel="stylesheet">
</head>
<body>
<div id="headerarea" class="headerarea">
<span class="left">
<img border="0" alt="Logo" src="jsp/images/time_logo3.gif"/>
</span>

<span class="right" style="margin-right: 10px;">

</span>
</div>

<br/><br/>

 <div align="center" id="main">
 <table width="95%" cellpadding="0" cellspacing="0" style="border-style:ridge; border-color:#7d1100; border-width:thin">
   <tr>
    <td style="background-color:#F0F0F0">
    
      <table width="100%">
        <tr>
        <td width="33%" valign="top">
		   <div class="portlet">
	          <div class="header">
	    		<h2>Maintenance</h2>
	          </div>
			  <div class="portlet-content">
			   
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.BreakRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.BreakRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.ClockLogMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.timesheet.client.ClockLogMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.ClockLocationRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.ClockLocationRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.DailyOvertimeRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.DailyOvertimeRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.DepartmentLunchRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.DepartmentLunchRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.department.client.DepartmentMaintenanceLookupForm']}${maintenanceSearchCategory}&clientFieldsToReturn=department&clientNestingPath=department&clientReturnUrl=${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.department.client.DepartmentMaintenanceForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.department.client.DepartmentMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.DocumentHeaderMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.timesheet.client.DocumentHeaderMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.DocumentLockMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.timesheet.client.DocumentLockMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.GracePeriodRuleMaintenanceForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.GracePeriodRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.HourlyHoursRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.HourlyHoursRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.extract.client.PayrollExtractScheduleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.extract.client.PayrollExtractScheduleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.lookupBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.person.client.UniversityIdLookupForm']}${maintenanceSearchCategory}&clientFieldsToReturn=universityId&clientNestingPath=userRoles&clientReturnUrl=${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.directory.client.RoleMaintenanceForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.directory.client.RoleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.ShiftDifferentialRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.ShiftDifferentialRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.SystemLunchRuleMaintenanceForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.SystemLunchRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.TimeCollectionRuleMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.TimeCollectionRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.client.TranslateMaintenanceLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.client.TranslateMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.rule.client.WeeklyOvertimeRuleMaintenanceForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.rule.client.WeeklyOvertimeRuleMaintenanceForm']}</a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}ksb/ServiceRegistry.do?methodToCall=start" target="_new">KSB Service Registry</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}ksb/MessageQueue.do?methodToCall=start" target="_new">KSB Message Queue</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}ksb/ThreadPool.do?methodToCall=start" target="_new">KSB Thread Pool</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}ksb/Quartz.do?methodToCall=start" target="_new">KSB Quartz Queue</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}CacheAdministration.do?methodToCall=start" target="_new">Cache Administration</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}DeptEarncdMaintenance.do">Department Earn Code Maintenance</a><br/>
				
			   </div>    
           </div>
           <div class="portlet">
              <div class="header">
                  <h2>Inquiry</h2>
              </div>
              <div class="portlet-content">
				
                    <a href="https://${pageContext.request.serverName}:${pageContext.request.serverPort}/en-${StrutsActionForm.environment}/RuleQuickLinks.do?rootDocTypeName=TKDocument">Delegate Maintenance Doc</a><br/>
                    <a href="https://${pageContext.request.serverName}:${pageContext.request.serverPort}/en-${StrutsActionForm.environment}/DocumentSearch.do">Document Search</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.directory.client.RoleAuditInquiryForm']}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.directory.client.RoleAuditInquiryForm']}</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.TimeBlockHistoryInquiryForm']}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.timesheet.client.TimeBlockHistoryInquiryForm']} </a><br/>
   				    <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.timesheet.client.ActualTimeInquiryForm']}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.timesheet.client.ActualTimeInquiryForm']} </a><br/>
					<a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.client.WorkAreaStatusInquiryForm']}&backdoorId=${StrutsActionForm.backdoorId}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.client.WorkAreaStatusInquiryForm']}</a><br/>
				    <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.report.client.BatchMessageInquiryForm']}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.report.client.BatchMessageInquiryForm']}</a><br/>
 				
              </div>
           </div>
        </td>
        <td width="33%" valign="top">
           <div class="portlet">
			  <div class="header">
				  <h2>Support</h2>
			  </div>
			  <div class="portlet-content">
				
					<a href="${StrutsActionForm.feedbackUrl}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.client.IncidentReportForm']}</a><br/>
				
			  </div>
		   </div>        
		   <div class="portlet">
	          <div class="header">
	              <h2>OneStart Portal</h2>
	          </div>
		 	  <div class="portlet-content">
                  <a href="${StrutsActionForm.applicationBaseUrl}AdministrationChannel.do?method=load">Administration Channel</a><br/>
                  <a href="${StrutsActionForm.applicationBaseUrl}LinksChannel.do?method=load">Links Channel</a><br/>
                  <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.client.NotifySupervisorLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">Notify Supervisor Channel</a><br/>
				  <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.client.OtherTimesheetsLookupForm']}&backdoorId=${StrutsActionForm.backdoorId}">Other Timesheets Channel</a><br/>
                  <a href="${StrutsActionForm.applicationBaseUrl}ProcessesChannel.do?method=load">Processes Channel</a><br/>
                  <a href="${StrutsActionForm.applicationBaseUrl}TimesheetDocument.do?method=open">Timesheet Channel</a><br/>
                  <a href="${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.client.UserPreferenceForm']}&backdoorId=${StrutsActionForm.backdoorId}">User Preferences Channel</a><br/>
			  </div>
           </div>
           <div class="portlet">
	          <div class="header">
	              <h2>Kiosk</h2> 
	          </div>
			  <div class="portlet-content">
			  	<a href="${StrutsActionForm.applicationBaseUrl}kiosk/">Kiosk Login (New)</a><br/>
			  	<a href="${StrutsActionForm.applicationBaseUrl}jsp/kiosk/KioskLogin.jsp">Kiosk Login (Old)</a><br/>
			  </div>
           </div>
	       <div class="portlet">
	          <div class="header">
	              <h2>Administrative</h2> 
	          </div>
			  <div class="portlet-content">
			  	
			  	<a href="${StrutsActionForm.applicationBaseUrl}BatchAdmin.do">Batch Administration</a><br/>
			  	<a href="${StrutsActionForm.applicationBaseUrl}TKDocumentAdministration.do">Batch Job List</a><br/>
			    <a href="${StrutsActionForm.applicationBaseUrl}BatchJob.do">Batch Jobs</a><br/>
			    <a href="${StrutsActionForm.applicationBaseUrl}RunReport.do">Run Reports</a><br/>
			    <br/>
			    <%--
				<a href="${StrutsActionForm.applicationBaseUrl}en/Portal.do" target="blank" style="text-decoration:underline">Workflow</a><br/>
				<a href="https://es-nd.ucs.indiana.edu:9000/tk-unt/TemporaryIndex.do?backdoorId=milara&method=load" target="blank" style="text-decoration:underline">TK-UNT</a><br/>
				   Timesheet URL:  <a href="${StrutsActionForm.applicationBaseUrl}TimesheetDocument.do?method=open&backdoorId=eak" target="blank" style="text-decoration:underline">eak timesheet</a>
				   (Hourly)<br/>
				   Timesheet URL:  <a href="${StrutsActionForm.applicationBaseUrl}TimesheetDocument.do?method=open&backdoorId=dbbarret" target="blank" style="text-decoration:underline">dbbarret timesheet</a>
			       (Hourly)<br/>
			       Timesheet URL:  <a href="${StrutsActionForm.applicationBaseUrl}TimesheetDocument.do?method=open&backdoorId=saejacks" target="blank" style="text-decoration:underline">saejacks timesheet</a>
			       (Biweekly)<br/>
			       <br/>
			       --%>
				<form name="backdoorForm">
				   BackdoorId <input type="text" name="backdoorId">
				   <input type="button" value="Go" onclick="javascript:backdoor()">
				</form>
			   </div>
	       </div>
        </td>
        </tr>
      </table> 
    <br/><br/>
    </td>
   </tr>
 </table>
<br/><br/>
</body>
</html>