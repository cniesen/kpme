<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="maintainableListName" required="true"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="recordIndex" required="true"%>
<%@ attribute name="printHeader" required="false"%>
<%@ attribute name="columnCount" required="false"%>
<%@ attribute name="hiddenFields" required="false"%>

<c:set var="columnsLength" value="${fn:length(fn:split(columns, ','))}" />

<c:if test="${printHeader}">
	<hr:tabH2Div tabTitle="${StrutsActionForm.labels[nestingPath]}: Record ${recordIndex + 1}" nestingPath="${nestingPath}"  maintainableListName="${maintainableListName}"/>
</c:if>
<table cellspacing="0" width="100%" cellpadding="0" style="width:100%">
	<c:forTokens items="${columns}" delims="," var="column" varStatus="current">

		<c:choose>
			<c:when test="${((current.index + 1) mod 2) == 1}">
				<tr>
			</c:when>
			<c:otherwise>
				<c:if test="${columnCount == 1}">
					<tr>
				</c:if>
			</c:otherwise>
		</c:choose>

		<c:set var="recordTemplateAttributeName" value="${maintainableListName}.template" />
		<c:set var="fullName" value="${recordTemplateAttributeName}.${column}" />
		<td class="headerright">
			<strong>
			<hr:text field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" headerLabel="true" />
			:
			</strong>
		</td>

		<c:choose>
			<c:when test="${current.last && ((columnsLength mod 2) != 0) && columnCount == 2}">
				<td colspan="3">
			</c:when>
			<c:otherwise>
				<td>
			</c:otherwise>
		</c:choose>

		<c:set var="fullName" value="${nestingPath}[${recordIndex}].${column}" />
		<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayHelp="${recordIndex == 0}" />
		</td>


		<c:choose>
			<c:when test="${((current.index + 1) mod 2) == 0}">
				</tr>
			</c:when>
			<c:otherwise>
				<c:if test="${columnCount == 1}">
					</tr>
				</c:if>
			</c:otherwise>
		</c:choose>
		
<html:hidden name="StrutsActionForm" property="${nestingPath}[${recordIndex}].mode.editable" styleId="${nestingPath}[${recordIndex}].mode.editable"/>
<c:forTokens items="${hiddenFields}" delims="," var="hiddenField">
	<c:set var="fullName" value="${nestingPath}[${recordIndex}].${hiddenField}" />
	<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" hidden="true" />
</c:forTokens>

	</c:forTokens>
	<tr align="center">
		<td colspan="${columnCount * 2}" class="infoline" height="30">
<div align="center">
<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
			<c:set var="eventNames" value="${nestingPath}[${recordIndex}].mode.eventNames" />
			<c:if test='${fn:length(StrutsActionForm.jstlPropertyWrapper[eventNames]) > 0}'>
				<c:forTokens items="${StrutsActionForm.jstlPropertyWrapper[eventNames]}" delims="," var="event">
					<c:set var="fullUrl" value="${event}Url" />
					<a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}&index=${recordIndex}&maintenanceNestingPath=${nestingPath}')"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
					<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
				</c:forTokens>
			</c:if>
<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
</div>
		</td>
	</tr>
</table>
