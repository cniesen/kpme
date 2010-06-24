<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="maintainableListName" required="true"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="recordIndex" required="false"%>
<%@ attribute name="hiddenFields" required="true"%>
<%@ attribute name="style" required="false"%>
<%@ attribute name="printHeader" required="false"%>
<%@ attribute name="actions" required="false"%>
<%@ attribute name="displayOnlyNotRestrictedByList" required="false"%>
<c:choose>
	<c:when test="${style == 'vertical'}">
		<hr:listMaintenanceTableHeaderAndData columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" printHeader="${false}" columnCount="1" />
	</c:when>
	<c:when test="${style == 'horizontal'}">
		<hr:listMaintenanceTableHeader columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" printHeader="${false}" actions="${actions}" />
		<hr:listMaintenanceTableData columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" actions="${actions}"/>
		<c:if test="${!empty columns}"></table></c:if>
	</c:when>
	<c:when test="${style == 'horizontal-multiple-row'}">
		<hr:listMaintenanceTableHeader columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" printHeader="${false}"  actions="${actions}"/>
		<logic:iterate name="StrutsActionForm" property="${maintainableListName}" id="record" indexId="recordIndex">
			<hr:listMaintenanceTableData columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" actions="${actions}" displayOnlyNotRestrictedByList="${displayOnlyNotRestrictedByList}"/>
		</logic:iterate>
		<c:if test="${!empty columns}"></table></c:if>
	</c:when>
	<c:when test="${style == 'horizontal-multiple-row-multiple-color'}">
		<hr:listMaintenanceTableHeader columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" printHeader="${false}"  actions="${actions}"/>
		<c:set var="trStyle" value="even" />
		<logic:iterate name="StrutsActionForm" property="${maintainableListName}" id="record" indexId="recordIndex">
			<c:choose>
				<c:when test="${trStyle == 'even'}">
					<c:set var="trStyle" value="odd" />
				</c:when>
				<c:otherwise>
					<c:set var="trStyle" value="even" />
				</c:otherwise>
			</c:choose>
			<hr:listMaintenanceTableData columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" actions="${actions}" displayOnlyNotRestrictedByList="${displayOnlyNotRestrictedByList}" trStatus="${trStyle}"/>
		</logic:iterate>
		<c:if test="${!empty columns}"></table></c:if>
	</c:when>
	<c:otherwise>
		<hr:listMaintenanceTableHeaderAndData columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" printHeader="${false}" columnCount="2" />
	</c:otherwise>
</c:choose>