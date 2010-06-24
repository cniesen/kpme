<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="childList" required="false"%>
<%@ attribute name="style" required="false"%>
<%@ attribute name="tabHeader" required="false"%>
<%@ attribute name="tabFooter" required="false"%>
<%@ attribute name="subheadDisplayField" required="false"%>
<%@ attribute name="actions" required="false"%>
<%@ attribute name="tabStatus" required="false"%>
<%@ attribute name="displayOnlyNotRestrictedByList" required="false"%>
<%@ attribute name="suppressShowHide" required="false"%>

<c:if test="${!empty nestedNestingPath}">
	<c:set var="originalNestedNestingPath" value="${nestedNestingPath}" />
	<c:set var="nestingPath" value="${nestedNestingPath}.${nestingPath}" />
	<c:set var="isNested" value="true" />
</c:if>

<c:set var="maintainableListName" value="${nestingPath}s" />
<c:set var="maintainableListErrorPath" value="${maintainableListName}.errorMessages" />
<c:set var="maintainableListErroneous" value="${fn:length(StrutsActionForm.jstlPropertyWrapper[maintainableListErrorPath]) > 0}" />
<c:set var="maintainableListWarningPath" value="${maintainableListName}.warningMessages" />
<c:set var="maintainableListWarned" value="${fn:length(StrutsActionForm.jstlPropertyWrapper[maintainableListWarningPath]) > 0}" />
<c:forEach items="${StrutsActionForm.jstlPropertyWrapper[maintainableListName]}" var="record">
	<c:set var="recordsErroneous" value="${recordsErroneous || record.erroneous}" />
	<c:set var="recordsWarned" value="${recordsWarned || record.warned}" />
	<c:set var="recordsEffective" value="${recordsEffective || record.effectivePersistentDatabaseMaintainedEntity}" />
	<c:set var="recordsAudited" value="${recordsAudited || record.auditedPersistentDatabaseMaintainedEntity}" />
</c:forEach>

<c:if test="${recordsEffective}">
	<c:set var="columns" value="${columns},effectiveDate,effectiveSequence,active" />
</c:if>

<c:if test="${recordsAudited}">
	<c:set var="columns" value="${columns},userUniversityId,timestamp" />
</c:if>

<c:if test="${empty tableBodyId}">
	<c:set var="tableBodyId" value="1" scope="request" />
</c:if>

<c:if test="${!empty tabStatus && tabStatus=='collapsed'}">
	<c:set var="tabStyle" value="style='display:none'"  />
</c:if>

<c:if test="${!childList}">
	<hr:tabHeader tabHeader="${tabHeader}" maintainableListName="${StrutsActionForm.labels[maintainableListName]}" id="${tableBodyId}" erroneous="${maintainableListErroneous || recordsErroneous}" tabStatus="${tabStatus}" suppressShowHide="${suppressShowHide}" />
	<div class="tab-container" align="center" id="G${tableBodyId}" ${tabStyle}>
		<c:set var="tableBodyId" value="${tableBodyId + 1}" scope="request" />
</c:if>

<c:if test="${maintainableListErroneous || recordsErroneous}">
	<c:if test="${maintainableListErroneous}">
		<hr:entityErrors nestingPath="${maintainableListErrorPath}" />
	</c:if>
	<c:if test="${recordsErroneous || recordsWarned}">
		<hr:entityListErrors maintainableList="${maintainableListName}" nestingPath="${nestingPath}" />
	</c:if>
</c:if>

<c:if test="${maintainableListWarned || recordsWarned}">
	<c:if test="${maintainableListWarned}">
		<hr:entityWarnings nestingPath="${maintainableListWarningPath}" />
	</c:if>
	<c:if test="${recordsWarned}">
		<hr:entityListWarnings maintainableList="${maintainableListName}" nestingPath="${nestingPath}" />
	</c:if>
</c:if>

<hr:listMaintenanceTabSubheadTableWrapper maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" actions="${actions}">
<c:set var="viewOnlyMode" value="${maintainableListName}.mode.viewOnlyMode" />
<c:if test="${StrutsActionForm.jstlPropertyWrapper[viewOnlyMode]}">
	<c:set var="actions" value="supress" />
</c:if>

	<c:choose>
		<c:when test="${style == 'horizontal-multiple-row'}">

				<hr:listMaintenanceTabSubhead nestingPath="${nestingPath}" id="${tableBodyId}" subheadDisplayField="${subheadDisplayField}" suppressShowHide="${suppressShowHide}">
					<hr:listMaintenanceTableContents columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" printHeader="${false}" style="${style}" actions="${actions}" displayOnlyNotRestrictedByList="${displayOnlyNotRestrictedByList}" />
					
					<c:set var="nestedNestingPath" value="${nestingPath}[${recordIndex}]" scope="request" />
					<jsp:doBody var="maintenanceBody" />
					<c:if test="${!empty maintenanceBody}">
					${maintenanceBody}
				</c:if>
				</hr:listMaintenanceTabSubhead>

		</c:when>
		<c:when test="${style == 'horizontal-multiple-row-multiple-color'}">

				<hr:listMaintenanceTabSubhead nestingPath="${nestingPath}" id="${tableBodyId}" subheadDisplayField="${subheadDisplayField}" suppressShowHide="${suppressShowHide}">
					<hr:listMaintenanceTableContents columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" printHeader="${false}" style="${style}" actions="${actions}" displayOnlyNotRestrictedByList="${displayOnlyNotRestrictedByList}" />
					
					<c:set var="nestedNestingPath" value="${nestingPath}[${recordIndex}]" scope="request" />
					<jsp:doBody var="maintenanceBody" />
					<c:if test="${!empty maintenanceBody}">
					${maintenanceBody}
				</c:if>
				</hr:listMaintenanceTabSubhead>

		</c:when>
		<c:otherwise>
			<logic:iterate name="StrutsActionForm" property="${maintainableListName}" id="record" indexId="recordIndex">
				<hr:listMaintenanceTabSubhead nestingPath="${nestingPath}" recordIndex="${recordIndex}" id="${tableBodyId}" subheadDisplayField="${subheadDisplayField}" suppressShowHide="${suppressShowHide}">
					<hr:listMaintenanceTableContents columns="${columns}" maintainableListName="${maintainableListName}" nestingPath="${nestingPath}" recordIndex="${recordIndex}" hiddenFields="${hiddenFields}" printHeader="${false}" style="${style}" actions="${actions}"  />
					<c:set var="nestedNestingPath" value="${nestingPath}[${recordIndex}]" scope="request" />
					<jsp:doBody var="maintenanceBody" />
					<c:if test="${!empty maintenanceBody}">
					${maintenanceBody}
				</c:if>
				</hr:listMaintenanceTabSubhead>
			</logic:iterate>
		</c:otherwise>
	</c:choose>

</hr:listMaintenanceTabSubheadTableWrapper>

<c:set var="nestedNestingPath" value="${originalNestedNestingPath}" scope="request" />
<c:choose>
	<c:when test="${!empty tabFooter}">
		</div>
		<hr:tabFooter />
	</c:when>
	<c:otherwise>
		</div>
	</c:otherwise>
</c:choose>
