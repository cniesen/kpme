<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search
		searchCriteria="documentId"
		resultColumns="documentId,employeeRecord,workArea,task,beginTime,earnCode,endTime,createdByClock,hours,overtimeEarnCode,overtimeHours,shiftEarnCode,shiftHours,premiumEarnCode,premiumHours,userUniversityId,timestampOriginal,timestampOriginalTz,modifiedByUniversityId,timestamp,timestampTz,actionHistory"
	/>
</hr:page>