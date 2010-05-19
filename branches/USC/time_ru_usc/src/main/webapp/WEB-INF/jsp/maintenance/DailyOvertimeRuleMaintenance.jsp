<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:listMaintenance nestingPath="rule" 
	columns="department,workArea,task,maxGap,shiftHours,overtimePreference" style="horizontal" tabFooter="true"/>
</hr:page>