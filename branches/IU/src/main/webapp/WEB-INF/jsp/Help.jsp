<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ include file="/jsp/tlds.jsp"%>
<hr:page>
	<hr:helpDisplay nestingPath="helpEntry" columns="type,subject,text"/>
	<hr:listDisplay nestingPath="helpEntry.step" columns="order,text"/>
	<hr:listDisplay nestingPath="helpEntry.reference" columns="order,text"/>
</hr:page>