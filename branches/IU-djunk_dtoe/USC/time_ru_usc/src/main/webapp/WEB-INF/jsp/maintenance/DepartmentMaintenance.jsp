<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ include file="/jsp/tlds.jsp"%>

<hr:page>

<script language=javascript src="jsp/scripts/ajax.js"></script>
<script language=javascript >
var image = "";
function retrieveNetworkId(action, focusField) {
  var fullPath = focusField.name.substring(0,focusField.name.lastIndexOf('.'));
  image = fullPath + "_lookup";
  document[image].src="<fmt:message key="processingIndicator.image.url"  bundle="${springMessages}" />";
  action = "/tk-${StrutsActionForm.environment}" + action;
  makeAjaxRequest(action, focusField, 'method=load&userRoles.universityId=','name,networkId')  
}
function resetImage(){
  document[image].src="<fmt:message key="lookup.image.url"  bundle="${springMessages}" />"; 
}
</script>

	<hr:entityContainer nestingPath="department" columns="department"/><br><br>
	<hr:listMaintenance nestingPath="department.roleUser" columns="name,networkId,universityId,roleName" style="horizontal-multiple-row" subheadDisplayField="Department Role Users"/>
	<hr:listMaintenance nestingPath="department.viewOnlyRoleUser" columns="name,networkId,universityId,roleName" style="horizontal-multiple-row" tabHeader="middle"/>
	<!--  hr:listMaintenance nestingPath="department.kioskManager" columns="name,networkId,universityId,roleName" style="horizontal" tabHeader="middle"/ -->
	<hr:listMaintenance nestingPath="department.workArea" columns="workArea,description,employeeType,overtimePreference,adminDescription"  hiddenFields="department" tabHeader="middle" tabFooter="true">
		<hr:listMaintenance nestingPath="roleUser" columns="name,networkId,universityId,roleName" childList="true"  style="horizontal-multiple-row" subheadDisplayField="Work Area Role Users"/>
		<hr:listMaintenance nestingPath="task" columns="task,description,adminDescription" childList="true" style="horizontal-multiple-row" subheadDisplayField="Tasks"/>
	</hr:listMaintenance>
	<!-- hr:listMaintenance nestingPath="department.kiosk" columns="kioskName,checked" style="horizontal" tabHeader="middle" tabFooter="true"/ -->

</hr:page>

