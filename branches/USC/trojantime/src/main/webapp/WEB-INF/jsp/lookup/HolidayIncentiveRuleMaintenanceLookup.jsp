<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="location,salaryPlan"
		resultColumns="location,salaryPlan,effectiveDate,effectiveSequence,active"/>
</hr:page>