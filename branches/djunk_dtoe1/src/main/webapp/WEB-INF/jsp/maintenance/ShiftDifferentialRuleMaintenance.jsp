<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:listMaintenance nestingPath="rule" 
	columns="location,salaryPlan,grade,earnCode,beginTime,endTime,minHours,applicableToSunday,applicableToMonday,applicableToTuesday,applicableToWednesday,applicableToThursday,applicableToFriday,applicableToSaturday,maxGap" tabFooter="true"/>
</hr:page>