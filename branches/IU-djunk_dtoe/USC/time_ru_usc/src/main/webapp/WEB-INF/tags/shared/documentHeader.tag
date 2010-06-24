<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="headerColumns" required="true"%>
<%@ attribute name="nestingPath" required="true"%>

<div class="headerbox">
	<table summary="document header: general information" cellpadding="0" cellspacing="0">
			<c:forTokens items="${headerColumns}" delims="," var="column" varStatus="current">
				<c:set var="fullName" value="${nestingPath}.${column}" />
				<c:if test="${(current.index mod 2) == 0}">
					<tr>
				</c:if>
				<th>
					${StrutsActionForm.labels[fullName]} :
				</th>
				<c:choose>
					<c:when test="${current.last && ((fn:length(fn:split(headerColumns, ',')) mod 2) == 1)}">
						<td nowrap colspan="3">
					</c:when>
					<c:otherwise>
						<td>
					</c:otherwise>
				</c:choose>
				<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayOnly="true" />
				</td>
				<c:if test="${(current.index mod 2) == 1}">
					</tr>
				</c:if>
			</c:forTokens>
	</table>
</div>


