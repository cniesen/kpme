<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:listMaintenance nestingPath="rule" 
	columns="department,workArea,universityId,employeeRecord,clockUseRequired,maxMinutes" tabFooter="true"/>
</hr:page>