<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="maintainableListName" required="true"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="recordIndex" required="false"%>
<%@ attribute name="printHeader" required="false"%>
<%@ attribute name="actions" required="false"%>

<c:if test="${printHeader}">
	<hr:tabH2Div tabTitle="${StrutsActionForm.labels[nestingPath]}: Record ${recordIndex + 1}" nestingPath="${nestingPath}" maintainableListName="${maintainableListName}" />
</c:if>
<c:if test="${!empty columns}">
	<table cellspacing="0" width="100%" style="width:100%">
		<tr>
			<c:forTokens items="${columns}" delims="," var="column">
				<c:set var="recordTemplateAttributeName" value="${maintainableListName}.template" />
				<c:set var="fullName" value="${recordTemplateAttributeName}.${column}" />
				<th>
					<hr:text field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" headerLabel="true" />
				</th>
			</c:forTokens>

			<c:if test="${empty actions}">
				<th>Actions</th>
			</c:if>

		</tr>
</c:if>
