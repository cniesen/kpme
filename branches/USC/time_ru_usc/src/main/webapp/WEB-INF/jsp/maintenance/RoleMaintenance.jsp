<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:entityContainer nestingPath="userRoles" columns="universityId,name,networkId,interfaceManager" />
	<br>
	<hr:listMaintenance nestingPath="userRoles.locationRole" columns="roleName,location" style="horizontal-multiple-row" subheadDisplayField="Location Roles" />
	<hr:listMaintenance nestingPath="userRoles.departmentRole" columns="roleName,department" tabHeader="middle" style="horizontal-multiple-row" subheadDisplayField="Department Roles" displayOnlyNotRestrictedByList="department"/>
	<hr:listMaintenance nestingPath="userRoles.viewOnlyRole" columns="roleName,department" tabHeader="middle" style="horizontal-multiple-row" subheadDisplayField="View Only Roles" displayOnlyNotRestrictedByList="department"/>
	<hr:listMaintenance nestingPath="userRoles.workAreaRole" columns="roleName,workArea,description" tabHeader="middle" tabFooter="true" style="horizontal-multiple-row" subheadDisplayField="Work Area Roles"/>
</hr:page> 