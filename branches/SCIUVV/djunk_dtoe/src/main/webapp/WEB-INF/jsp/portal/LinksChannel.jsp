<%@ include file="/jsp/tlds.jsp" %>
<c:set var="searchCategory" value="&searchCategory="/>
<c:set var="maintenanceSearchCategory" value="${searchCategory}maintenance"/>

<html>
<head>
	<title>Links</title>
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
					<li><a href="${StrutsActionForm.feedbackUrl}">${StrutsActionForm.pageTitles['edu.iu.uis.hr.client.IncidentReportForm']}</a></li>
				</ul>
		   </td>
		</tr>
	</table>
</body>
</html>