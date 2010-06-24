<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>

</div>
<div id="workarea">
	<div class="tab-container" align="center">
	<hr:tabH2Div tabTitle="${StrutsActionForm.labels[nestingPath]}" nestingPath="${nestingPath}" />
	<table cellpadding="0" cellspacing="0" class="datatable-100">		
		<c:set var="fullName" value="${nestingPath}.hoursSummaryDisplay" />
		<logic:iterate name="StrutsActionForm" property="${fullName}" id="record" indexId="recordIndex">
			<tr>
				<c:set var="listItems" value="${record}" />
				<c:forTokens items="${listItems}" delims="," var="column" varStatus="current">

				<c:choose>
					<c:when test="${current.index == 9 || current.index == 17}">
						<c:set var="dataClass" value="weeklytotal" />
					</c:when>
					<c:when test="${current.index == 18}">
						<c:set var="dataClass" value="payperiodtotal" />
					</c:when>
					<c:otherwise>
						<c:if test="${column == '0.00'}">
							<c:set var="column" value="&nbsp;"/>
						</c:if>	
						<c:set var="dataClass" value="slim-center" />
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${current.index == 0}">
						<c:set var="rowType" value="${column}" />
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${rowType == 'HEADER'}">
								<c:choose>
									<c:when test="${class == 'slim-center'}">
										<th>
											<b>${column}</b>
										</th>
									</c:when>
									<c:otherwise>
										<td class="${dataClass}">
											<b>${column}</b>
										</td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${rowType == 'EARN_CODE'}">
								<td colspan="18" class="tab-subhead-slim">${column}</td>
							</c:when>
							<c:when test="${rowType == 'TOTAL'}">
								<td class="${dataClass}"><b>${column}</b></td>
							</c:when>
							<c:when test="${rowType == 'WORK_HOURS_TOTAL'}">
								<td class="${dataClass}"><b>${column}</b></td>
							</c:when>
							<c:when test="${rowType == 'DIVIDER'}">
								<td colspan="18" class="divcell"><img src="jsp/images/pixel_clear.gif" alt="" width="3" height="3"></td>
							</c:when>
							<c:otherwise>
								<c:if test="${(current.index >= 2 && current.index <= 8) || (current.index >= 10 && current.index <= 16)}">
									<c:set var="dataClass" value="slim-center-white" />
								</c:if>
								<td class="${dataClass}">
									${column}
								</td>
							</c:otherwise>					
						</c:choose>
					</c:otherwise>
				</c:choose>
					
				</c:forTokens>
			</tr>
		</logic:iterate>
	</table>
	
	</div>