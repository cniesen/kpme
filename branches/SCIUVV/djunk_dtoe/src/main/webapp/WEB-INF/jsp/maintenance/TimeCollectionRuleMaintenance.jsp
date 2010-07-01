<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:listMaintenance nestingPath="rule" 
	columns="department,workArea,clockUseRequired,hoursDistributionAllowed"  tabFooter="true"/>
</hr:page>