<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="department,workArea"
		resultColumns="department,workArea,effectiveDate,effectiveSequence,active,clockUseRequired,hoursDistributionAllowed"/>
</hr:page>