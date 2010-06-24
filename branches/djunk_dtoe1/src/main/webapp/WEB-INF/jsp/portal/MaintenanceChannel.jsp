<%@ include file="/jsp/tlds.jsp" %>
<c:set var="searchCategory" value="&searchCategory="/>
<c:set var="maintenanceSearchCategory" value="${searchCategory}maintenance"/>

<html>
<head>
	<title>Maintenance</title>
	<script language="Javascript">
		function openNewWindow(url) {
		    var activeWindow = window.open(url, "_blank", "toolbar=yes,status=yes,location=yes,menubar=yes,scrollbars=yes,resizable=yes,directories=yes,left=0,top=0,height=" + screen.availHeight + ",width=" + screen.availWidth);
		    activeWindow.focus();
		}
	</script>
	<link href="https://docs.onestart.iu.edu/dav/MY/channels/style-channel.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<ul>
				  <li><a href="javascript:openNewWindow('${StrutsActionForm.lookupBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.person.client.UniversityIdLookupForm']}${maintenanceSearchCategory}&clientFieldsToReturn=universityId&clientNestingPath=userRoles&clientReturnUrl=${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.directory.client.RoleMaintenanceForm']}')">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.directory.client.RoleMaintenanceForm']}</a></li>
			      <li><a href="javascript:openNewWindow('${StrutsActionForm.lookupBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.position.client.DepartmentLookupForm']}${maintenanceSearchCategory}&clientFieldsToReturn=department&clientNestingPath=timeDepartment&clientReturnUrl=${StrutsActionForm.applicationBaseUrl}${StrutsActionForm.pageLoadUrls['edu.iu.uis.hr.tk.department.client.TimeDepartmentMaintenanceForm']}')">${StrutsActionForm.pageTitles['edu.iu.uis.hr.tk.department.client.TimeDepartmentMaintenanceForm']}</a></li>			
				</ul>
			</td>
		</tr>
	</table>
</body>
</html>