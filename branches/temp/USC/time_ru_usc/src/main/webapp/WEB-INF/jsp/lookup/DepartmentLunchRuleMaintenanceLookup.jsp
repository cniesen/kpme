<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="department,workArea,universityId,employeeRecord"
		resultColumns="department,workArea,universityId,employeeRecord,effectiveDate,effectiveSequence,active,clockUseRequired,maxMinutes"/>
</hr:page>