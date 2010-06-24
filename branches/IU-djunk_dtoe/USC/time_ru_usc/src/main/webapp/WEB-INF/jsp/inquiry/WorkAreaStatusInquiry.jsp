<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search
		searchCriteria="workArea,endTimestamp"
		resultColumns="clockLog.workArea,clockLog.universityId,name,clockLog.clockTime,clockLog.translate.description"
	/>
</hr:page>