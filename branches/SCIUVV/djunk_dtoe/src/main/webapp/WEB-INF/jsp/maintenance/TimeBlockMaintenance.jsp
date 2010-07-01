<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
<center> <b>  Warning: The "delete" buttons will actually remove  records from the database! </b></center>
	<hr:listMaintenance nestingPath="timeBlock" 
	columns="documentId,employeeRecord,workArea,task,earnCode,beginTime,endTime,timestamp,beginTsTz,endTsTz,timestampTz,overtimeEarnCode,overtimeHours,premiumEarnCode,premiumHours,shiftEarnCode,shiftHours,createdByClock"  
	style="horizontal" tabFooter="true"/>
</hr:page>