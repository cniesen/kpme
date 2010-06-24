<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="tabTitle" required="true"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="maintainableListName" required="false"%>
<%@ attribute name="actions" required="false"%>

<div class="h2-container">
	<span class="subhead-left">
		<h2>
			${tabTitle} &nbsp;&nbsp;&nbsp;
		</h2> 
	</span>
    <c:if test="${tabTitle=='Hours Details' && !empty (StrutsActionForm.timesheetDocument) && StrutsActionForm.timesheetDocument.clock.hasClockAssignment}">
		 <span class="subhead-right">
        <a href="javascript:showActualTimeInquiry(${StrutsActionForm.timesheetDocument.documentHeader.documentId})" class="whiteLink"> Actual Time Inquiry</a>
	  </span>
	</c:if>	
	<c:if test="${StrutsActionForm.maintenance}">
		<span class="subhead-left">
		  <c:if test="${!empty maintainableListName}">
			<c:set var="viewOnlyMode" value="${maintainableListName}.mode.viewOnlyMode" />
				<c:if test="${!StrutsActionForm.jstlPropertyWrapper[viewOnlyMode]}">
					<c:choose>
						<c:when test="${empty actions}">
							<a href="${StrutsActionForm.addUrl}&maintenanceNestingPath=${nestingPath}')"><fmt:message key="add.image.html" bundle="${springMessages}" /></a>
						</c:when>
						<c:otherwise>
							<!-- supress actions -->
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:if> 
		</span>
	</c:if>
	<c:if test="${StrutsActionForm.maintenance}">
		<span class="subhead-right"> 
		  <c:if test="${!empty maintainableListName}">
			<c:set var="viewOnlyMode" value="${maintainableListName}.mode.viewOnlyMode" />
				<c:if test="${!StrutsActionForm.jstlPropertyWrapper[viewOnlyMode]}">
					<c:choose>
						<c:when test="${empty actions}">
							<a href="${StrutsActionForm.addUrl}&maintenanceNestingPath=${nestingPath}')"><fmt:message key="add.image.html" bundle="${springMessages}" /></a>
						</c:when>
						<c:otherwise>
							<!-- supress actions -->
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:if> 
		</span>
	</c:if>
</div>
