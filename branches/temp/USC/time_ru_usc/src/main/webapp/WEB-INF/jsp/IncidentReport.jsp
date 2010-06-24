<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ include file="/jsp/tlds.jsp"%>
<hr:page>
	<div align="center"><p>Please use the Incident Report form below to report problems or feedback related to the TIME system. <p>This information will be forwarded to our support team. If you were taken to the Incident Report while attempting an action in TIME, please describe what action you were taking when the problem occurred.</div><br>
	<hr:entity nestingPath="incident" columns="documentId,errorMessage,description" hiddenFields="applicationCode,inputPage,problemType"/>
</hr:page>
