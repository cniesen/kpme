<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search searchCriteria="location,salaryPlan,grade"
		resultColumns="location,salaryPlan,grade,effectiveDate,effectiveSequence,active,earnCode,beginTime,endTime,minHours,applicableToSunday,applicableToMonday,applicableToTuesday,applicableToWednesday,applicableToThursday,applicableToFriday,applicableToSaturday,maxGap"/>
</hr:page>