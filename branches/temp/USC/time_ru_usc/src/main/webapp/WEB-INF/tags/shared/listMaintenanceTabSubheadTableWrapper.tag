<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="maintainableListName" required="true"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="actions" required="false"%>

<hr:tabH2Div tabTitle="${StrutsActionForm.labels[maintainableListName]}" nestingPath="${nestingPath}"  maintainableListName="${maintainableListName}" actions="${actions}"/>
<table width="100%" cellpadding="0" cellspacing="0" summary="" class="null">
	<jsp:doBody />
</table>