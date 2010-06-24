<%@ include file="/jsp/tlds.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
	<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />

<SCRIPT type="text/javascript">
      function jobAction(jobName, jobGroup, jobAction) {
            document.forms[0].elements['existingJobName'].value = jobName;
            document.forms[0].elements['existingJobGroup'].value = jobGroup;
            document.forms[0].elements['method'].value = jobAction;
            document.forms[0].submit();
        }
      function scheduleJob() {
            document.forms[0].elements['method'].value = 'scheduleJob';
            document.forms[0].submit();
        }        
</SCRIPT>

 </head>
 <body>
 <div id="headerarea" class="headerarea">
<span class="left">
<img border="0" alt="Logo" src="jsp/images/time_logo3.gif"/>
</span>
<br/>
<span class="right" style="margin-right: 10px;">
<a href="TemporaryIndex.do">Admin Index</a> - 
<a href="BatchAdmin.do">Refresh Page</a>
</span>
</div>

<html:form action="/BatchAdmin">
<input type="hidden" name="method" value="" />

<html:hidden property="existingJobName" />
<html:hidden property="existingJobGroup" />
 <div align="center" id="main">
 <br/><br/>
 <table width="95%" cellpadding="0" cellspacing="0" style="border-style:ridge; border-color:#7d1100; border-width:thin">
   <tr>
    <td style="background-color:#F0F0F0">
		   <div class="portlet">
	          <div class="header">
	             <h2>Current Jobs</h2>
	          </div>
			  <div class="portlet-content">
			  
       <c:forEach var="job" items="${TKBatchAdminForm.jobs}">
       <!-- TKBatchAdminForm.methodToCall.value='runJob';  -->
			<div><span class="title">${job.jobDetail.name}</span> (<a href="#RUN_${job.jobDetail.name}" onclick="jobAction('${job.jobDetail.name}', '${job.jobDetail.group}', 'runJob');">RUN</a>) (<a href="#DELETE_${job.jobDetail.name}" onclick="jobAction('${job.jobDetail.name}', '${job.jobDetail.group}', 'removeJob');">DELETE</a>)<br/>
			<c:forEach var="trigger" items="${job.triggers}">
				Next Run : ${trigger.nextFireTime}<br/>
			</c:forEach>	
			<c:if test="${empty job.triggers}">
			-- Running Now!<br/>
			</c:if>	
			</div><br/>   
        </c:forEach>
			   </div>    
           </div>
  		   <div class="portlet">
	          <div class="header">
	             <h2>Edit/New Job</h2>
	          </div>
			  <div class="portlet-content">
			           
					<table width="50%">
					<tr><td style="text-align: right;">Job Name:</td><td><input type="text" name="jobName" size="55" /></td><td>Any Unique Job Name<br/>(An existing name will edit the existing job)</td></tr>
					<tr><td style="text-align: right;">Class Name:</td><td><input type="text" name="jobClass" size="55" /></td><td>Fully qualified class name Ex/<br/>edu.iu.uis.hr.tk.batch.job.DumbJob</td></tr>
					<tr><td style="text-align: right;">Cron Value:</td><td><input type="text" name="triggerCronValue" size="55" /></td><td>Cron expression</td></tr>
					</table>
					<br/>
					<input style="margin-left: 175px;" type="image" src="en/images/tinybutton-save.gif"  id="SubmitJob" onclick="scheduleJob();" /> <br/><br/>
					Cron Examples:
					<table width=100%>
					<tr><td>0 0 12 * * ?</td><td>Fire at 12pm (noon) every day</td></tr>
					<tr><td>0 15 10 ? * *</td><td>Fire at 10:15am every day</td></tr>
					<tr><td>0 15 10 * * ?</td><td>Fire at 10:15am every day</td></tr>
					<tr><td>0 15 10 * * ? *</td><td>Fire at 10:15am every day</td></tr>
					<tr><td>0 15 10 * * ? 2005</td><td>Fire at 10:15am every day during the year 2005</td></tr>
					<tr><td>0 * 14 * * ?</td><td>Fire every minute starting at 2pm and ending at 2:59pm, every day</td></tr>
					<tr><td>0 0/5 14 * * ?</td><td>Fire every 5 minutes starting at 2pm and ending at 2:55pm, every day</td></tr>
					<tr><td>0 0/5 14,18 * * ?</td><td>Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day</td></tr>
					<tr><td>0 0-5 14 * * ?</td><td>Fire every minute starting at 2pm and ending at 2:05pm, every day</td></tr>
					<tr><td>0 10,44 14 ? 3 WED</td><td>Fire at 2:10pm and at 2:44pm every Wednesday in the month of March.</td></tr>
					<tr><td>0 15 10 ? * MON-FRI</td><td>Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday</td></tr>
					<tr><td>0 15 10 15 * ?</td><td>Fire at 10:15am on the 15th day of every month</td></tr>
					<tr><td>0 15 10 L * ?</td><td>Fire at 10:15am on the last day of every month</td></tr>
					<tr><td>0 15 10 ? * 6L</td><td>Fire at 10:15am on the last Friday of every month</td></tr>
					<tr><td>0 15 10 ? * 6L</td><td>Fire at 10:15am on the last Friday of every month</td></tr>
					<tr><td>0 15 10 ? * 6L 2002-2005</td><td>Fire at 10:15am on every last friday of every month during the years 2002, 2003, 2004 and 2005</td></tr>
					<tr><td>0 15 10 ? * 6#3</td><td>Fire at 10:15am on the third Friday of every month</td></tr>
					<tr><td>0 0 12 1/5 * ?</td><td>Fire at 12pm (noon) every 5 days every month, starting on the first day of the month.</td></tr>
					<tr><td>0 11 11 11 11 ?</td><td>Fire every November 11th at 11:11am. </td></tr>
					</table>
			   </div>    
           </div>
  		   <div class="portlet">
	          <div class="header">
	             <h2>Quartz Summary</h2>
	          </div>
			  <div class="portlet-content">
				${TKBatchAdminForm.summary}
			   </div>    
           </div>
    <br/><br/>
    </td>
   </tr>
 </table>           
</html:form>
<br/><br/>
</body>

</html>