<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<kul:page docTitle="Time Entry" transactionalDocument="" htmlFormAction="timeEntry">
	<link href="css/timeEntry.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script> 
	<script language="JavaScript" type="text/javascript" src="js/jquery-ui-1.8.2.custom.min.js"></script> 
	<script language="JavaScript" type="text/javascript" src="js/timeEntry.js"></script>

	<tk:personInformation />

	<tk:timeEntry />
	
	<tk:notes />
	
</kul:page>