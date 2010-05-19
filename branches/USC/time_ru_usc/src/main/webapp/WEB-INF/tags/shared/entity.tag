<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="columnCount" %>

<c:if test="${empty columnCount}">
    <c:set var="columnCount" value="2" />
</c:if>

<div id="lookup" align="center">
	<table cellpadding="0" cellspacing="0" class="datatable-100">
		<tbody>
			<c:if test="${StrutsActionForm.erroneous}">
				<tr>
					<th colspan="4">
						<hr:entityErrors nestingPath="${nestingPath}" />
					</th>
				</tr>
			</c:if>
			<c:if test="${StrutsActionForm.warned}">
				<tr>
					<th colspan="4">
						<hr:entityWarnings nestingPath="${nestingPath}" />
					</th>
				</tr>
			</c:if>
			<c:forTokens items="${columns}" delims="," var="column" varStatus="current">
				<c:set var="fullName" value="${nestingPath}.${column}" />
				<c:if test="${(current.index mod columnCount) == 0}">
					<tr>
				</c:if>
				<th width="20%" nowrap>
					${StrutsActionForm.labels[fullName]} :
				</th>
				<c:choose>
					<c:when test="${current.last && ((fn:length(fn:split(columns, ',')) mod 2) == 1)}">
						<td nowrap colspan="3">
					</c:when>
					<c:otherwise>
						<td width="30%" nowrap>
					</c:otherwise>
				</c:choose>
				<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayHelp="true" />
				</td>
				<c:if test="${(current.index mod 2) == 1}">
					</tr>
				</c:if>
			</c:forTokens>

			<c:set var="eventNames" value="${nestingPath}.mode.eventNames" />
			<c:if test='${fn:length(StrutsActionForm.jstlPropertyWrapper[eventNames]) > 0}'>
				<tr>
					<td class="infoline" colspan="4" height="30">
						<div align="center">
							<c:forTokens items="${StrutsActionForm.jstlPropertyWrapper[eventNames]}" delims="," var="event" varStatus="current">
								<c:choose>
									<c:when test="${event eq 'exitCancel'}">
										<a href="${StrutsActionForm.exitCancelUrl}"><fmt:message key="${StrutsActionForm.exitCancelImageKey}" bundle="${springMessages}" /></a>
									</c:when>
									<c:otherwise>
										<c:set var="fullUrl" value="${event}Url" />
										<a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
										<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
									</c:otherwise>
								</c:choose>
							</c:forTokens>
						</div>
					</td>
				</tr>
			</c:if>
			<html:hidden name="StrutsActionForm" property="${nestingPath}.mode.editable"  styleId="${nestingPath}.mode.editable" />
			<hr:hidden nestingPath="${nestingPath}" hiddenFields="${hiddenFields}" />

		</tbody>
	</table>
</div>
