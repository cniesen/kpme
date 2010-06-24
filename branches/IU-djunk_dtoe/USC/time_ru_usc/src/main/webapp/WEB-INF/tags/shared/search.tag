<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="searchCriteria" required="true"%>
<%@ attribute name="resultColumns" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="hideSearchInput" required="false"%>

<c:if test="${StrutsActionForm.searchCriteria.effectiveDated}">
	<c:set var="searchCriteria" value="${searchCriteria},historyIncluded,futureIncluded" />
	<c:set var="hiddenFields" value="${hiddenFields},historyIncludedAllowed,futureIncludedAllowed" />
</c:if>

<c:if test="${!hideSearchInput || !hideSearchInput == 'true'}">
    <hr:entity columns="${searchCriteria}" hiddenFields="${hiddenFields}" nestingPath="searchCriteria" />
</c:if>

<c:if test="${StrutsActionForm.standardLookup}">
	<html:hidden name="StrutsActionForm" property="resultsSessionObjectKey" styleId="resultsSessionObjectKey" />
	<html:hidden name="StrutsActionForm" property="clientSessionObjectKey" styleId="clientSessionObjectKey" />
	<html:hidden name="StrutsActionForm" property="clientReturnUrl" styleId="clientReturnUrl" />
	<html:hidden name="StrutsActionForm" property="clientNestingPath" styleId="clientNestingPath" />
	<html:hidden name="StrutsActionForm" property="clientFieldsToReturn" styleId="clientFieldsToReturn" />
	<html:hidden name="StrutsActionForm" property="searchCategory" styleId="searchCategory" />
	<html:hidden name="StrutsActionForm" property="searchCriteria.lookupField" styleId="searchCriteria.lookupField" />
</c:if>
<c:if test="${StrutsActionForm.searchCriteria.effectiveDated}">
	<html:hidden name="StrutsActionForm" property="searchCriteria.transactionRecordEffectiveDate" styleId="searchCriteria.transactionRecordEffectiveDate" />
</c:if>

<br>
<br>
</div>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="column-left">
			<img src="jsp/images/pixel_clear.gif" alt="" width="20" height="20">
		</td>
		<td>
			<c:choose>
				<c:when test="${StrutsActionForm.standardLookup}">
					<display:table name="${StrutsActionForm.results}" id="row" cellspacing="0" pagesize="${StrutsActionForm.pageSize}" requestURI="${StrutsActionForm.pageOrSortUrl}" decorator="${StrutsActionForm.tableDecoratorClassName}">
						<c:forTokens items="${resultColumns}" delims="," var="resultColumn">
							<display:column property="${resultColumn}" title="${StrutsActionForm.labels[resultColumn]}" sortable="true" decorator="${StrutsActionForm.columnDecoratorClassName}" />
						</c:forTokens>
						<c:if test="${StrutsActionForm.inquirySearch == false}">
							<display:column property="selectLink" title="Actions" />
						</c:if>
					</display:table>
				</c:when>
				<c:otherwise>
					<c:set var="rowsToAddFullName" value="rowsToAddCollector.rowsToAdd" />
					<div id="workarea">
						<div class="h2-container-100">
							<span class="subhead-left">
								<h2>
									${StrutsActionForm.labels[rowsToAddFullName]}:

									<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[rowsToAddFullName]}" />
									&nbsp;&nbsp;&nbsp;<a href="${StrutsActionForm.selectUrl}"><font color="#ffffff">Continue</font></a>
								</h2> </span> <span class="subhead-right" valign="middle"> <c:if test="${fn:length(StrutsActionForm.results) > 0}">
									<a href="${StrutsActionForm.selectAllUrl}"><font color="#ffffff">Select All</font></a>
									<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
									<a href="${StrutsActionForm.unselectAllUrl}"><font color="#ffffff">Unselect All</font></a>
									<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
								</c:if> 
							</span>
						</div>
					</div>
					<c:if test="${fn:length(StrutsActionForm.results) > 0}">
						<table class="datatable-100" id="row" cellpadding="0" cellspacing="0" width="100%">
							<thead>
								<tr>
									<c:forTokens items="${resultColumns}" delims="," var="resultColumn">
										<c:set var="fullName" value="resultTemplate.${resultColumn}" />
										<th class="sortable">
											${StrutsActionForm.labels[fullName]}
										</th>
									</c:forTokens>
									<th>
										Select?
									</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="trStyle" value="even" />
								<logic:iterate name="StrutsActionForm" property="results" id="result" indexId="resultIndex">
									<c:choose>
										<c:when test="${trStyle == 'even'}">
											<c:set var="trStyle" value="odd" />
										</c:when>
										<c:otherwise>
											<c:set var="trStyle" value="even" />
										</c:otherwise>
									</c:choose>
									<tr class="${trStyle}">
										<c:forTokens items="${resultColumns}" delims="," var="resultColumn">
											<td>
												<c:set var="fullName" value="result[${resultIndex}].${resultColumn}" />
												<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayOnly="true" />
												<c:set var="fullName" value="result[${resultIndex}].${resultColumn}" />
											</td>
										</c:forTokens>
										<td>
											<c:set var="fullName" value="result[${resultIndex}].selected" />
											<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" />
											<c:if test="${result.auditedPersistentDatabaseMaintainedEntity}">
												<c:set var="userUniversityIdFieldName" value="result[${resultIndex}].userUniversityId" />
												<c:set var="timestampFieldName" value="result[${resultIndex}].timestamp" />
												<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[userUniversityIdFieldName]}" hidden="true" />
												<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[timestampFieldName]}" hidden="true" />
											</c:if>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</c:if>
				</c:otherwise>
			</c:choose>
		</td>
		<td class="column-right">
			<img src="jsp/images/pixel_clear.gif" alt="" width="20" height="20">
		</td>
	</tr>
</table>
<div id="workarea">