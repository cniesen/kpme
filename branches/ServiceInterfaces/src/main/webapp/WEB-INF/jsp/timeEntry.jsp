<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<kul:page docTitle="Time Entry" transactionalDocument="" htmlFormAction="timeEntry">
	<link href="css/timeEntry.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" type="text/javascript" src="js/jquery-1.4.2.min.js"></script> 
	<script language="JavaScript" type="text/javascript" src="js/jquery-ui-1.8.2.custom.min.js"></script> 
	<script language="JavaScript" type="text/javascript" src="js/timeEntry.js"></script>

	<tk:personInformation />

	<tk:timeEntry />
	
	<tk:notes />

	<div class="globalbuttons" id="globalbuttons">
    	<html:image property="methodToCall.submit" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_submit.gif" alt="Submit" title="Submit" styleClass="globalbuttons"/>
    	<html:image property="methodToCall.back" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" alt="Close" title="Close" styleClass="globalbuttons"/>
    </div>
	
</kul:page>