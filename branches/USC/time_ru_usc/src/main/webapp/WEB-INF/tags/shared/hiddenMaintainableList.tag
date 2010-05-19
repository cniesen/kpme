<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="hiddenFields" required="true"%>

<c:if test="${!empty nestedNestingPath}">
	<c:set var="originalNestedNestingPath" value="${nestedNestingPath}" />
	<c:set var="nestingPath" value="${nestedNestingPath}.${nestingPath}" />
</c:if>

<c:set var="maintainableListName" value="${nestingPath}s" />

<c:forEach items="${StrutsActionForm.jstlPropertyWrapper[maintainableListName]}" var="record">
	<c:set var="recordsEffective" value="${recordsEffective || record.effectivePersistentDatabaseMaintainedEntity}" />
	<c:set var="recordsAudited" value="${recordsAudited || record.auditedPersistentDatabaseMaintainedEntity}" />
</c:forEach>

<c:if test="${recordsEffective}">
   <c:set var="hiddenFields" value="${hiddenFields},effectiveDate,effectiveSequence,active" />
</c:if>

<c:if test="${recordsAudited}">
   <c:set var="hiddenFields" value="${hiddenFields},userUniversityId,timestamp" />
</c:if>

<logic:iterate name="StrutsActionForm" property="${maintainableListName}" id="record" indexId="recordIndex">

	<c:forTokens items="${hiddenFields}" delims="," var="hiddenField">
		<c:set var="fullName" value="${nestingPath}[${recordIndex}].${hiddenField}"/>
	    <hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" hidden="true"/>
	</c:forTokens>


	<c:set var="nestedNestingPath" value="${nestingPath}[${recordIndex}]" scope="request" />
	<jsp:doBody var="maintenanceBody" />
	<c:if test="${!empty maintenanceBody}">
			${maintenanceBody}
	</c:if>
</logic:iterate>

<c:set var="nestedNestingPath" value="${originalNestedNestingPath}" scope="request" />




