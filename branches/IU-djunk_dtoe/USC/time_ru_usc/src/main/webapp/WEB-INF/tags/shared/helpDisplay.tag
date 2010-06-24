<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="field" required="false" type="edu.iu.uis.hr.client.EntityField"%>
	
<tr>
	<td> 
		<table class="datatable-100">
			<c:forTokens items="${columns}" delims="," var="column" varStatus="current">
				<c:set var="fullName" value="${nestingPath}.${column}"/>
				<c:set var="field" value="${StrutsActionForm.jstlPropertyWrapper[fullName]}"/>
				<tr>
					<th align="right">
						${StrutsActionForm.labels[fullName]}
						:
					</th>
					<td class="datacell">
						<c:out value="${field.value}" escapeXml="false"/>
					</td>
				</tr>
			</c:forTokens>
		</table>
	</td>
</tr>
<tr>
	<td class="infoline">
		<a href="${StrutsActionForm.exitCancelUrl}"><fmt:message key="${StrutsActionForm.exitCancelImageKey}" bundle="${springMessages}"/></a>
	</td>
</tr>
