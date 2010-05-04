<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="payEndDate,status"
		resultColumns="payEndDate,effectiveDate,effectiveSequence,active,status,scheduledBeginTime,actualBeginTime"/>
</hr:page>