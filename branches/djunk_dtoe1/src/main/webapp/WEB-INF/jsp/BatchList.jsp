<%@ include file="/jsp/tlds.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />

</head>
<body>


<div id="headerarea" class="headerarea"><span class="left">
<img border="0" alt="Logo" src="jsp/images/time_logo3.gif" /> </span> <br />
<span class="right" style="margin-right: 10px;"> <a
	href="TemporaryIndex.do">Admin Index</a> - <a href="BatchAdmin.do">Refresh
Page</a> </span></div>

<html:form action="/TKDocumentAdministration">

	<div align="center" id="main"><br />
	<br />
	<table width="95%" cellpadding="0" cellspacing="0"
		style="border-style: ridge; border-color: #7d1100; border-width: thin">
				<hr:batchListInput title="Initiate Document" label="Username" methodName="initiateDocument" inputName="userId" />
				<hr:batchListInput title="Populate Hours" label="Doc Id" methodName="populateHours" inputName="populateHoursDocId" />
				<hr:batchListInput title="End Pay Period" label="Doc Id" methodName="endPayPeriod" inputName="endPayPeriodDocId" />
				<hr:batchListInput title="Employee Approval" label="Doc Id" methodName="employeeApproval"  inputName="employeeApprovalDocId" />
				<hr:batchListInput title="Supervisor Approval" label="Doc Id" methodName="supervisorApproval" inputName="supervisorApprovalDocId" />
				<hr:batchListInput title="Payroll Extract" label="Doc Id" methodName="payrollExtract" inputName="payrollExtractDocId" />
				<hr:batchListInput title="Daily Reminder" label="Doc Id" methodName="dailyReminder" inputName="dailyReminderDocId" />
				<hr:batchListInput title="Biweekly Reminder" label="Username" methodName="biweeklyReminder" inputName="biweeklyReminderUserId" />
				<hr:batchListInput title="Weekly Reminder" label="Username" methodName="weeklyReminder" inputName="weeklyReminderUserId" />
		</tr>
	</table>
	<br />
	<br />
	</div>
</html:form>

</body>

</html>