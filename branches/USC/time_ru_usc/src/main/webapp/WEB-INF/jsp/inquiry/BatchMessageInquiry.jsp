<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search
		searchCriteria="processName,processDate,universityId,documentId"
		resultColumns="processName,processDate,documentId,universityId,message"
	/>
</hr:page>