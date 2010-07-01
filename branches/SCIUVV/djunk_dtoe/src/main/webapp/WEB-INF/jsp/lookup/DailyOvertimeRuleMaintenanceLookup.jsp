<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="department,workArea,task"
		resultColumns="department,workArea,task,maxGap,shiftHours,overtimePreference,effectiveDate,effectiveSequence,active"/>
</hr:page>