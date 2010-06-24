<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="documentId"
		resultColumns="documentId,employeeRecord,workArea,task,earnCode,beginTime,endTime,timestamp,beginTsTz,endTsTz,timestampTz,overtimeEarnCode,overtimeHours,premiumEarnCode,premiumHours,shiftEarnCode,shiftHours,createdByClock"
		/>
</hr:page> 