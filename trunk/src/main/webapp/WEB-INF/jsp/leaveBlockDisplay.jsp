<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<c:set var="Form" value="${LeaveBlockDisplayForm}" scope="request" />
<c:set var="KualiForm" value="${LeaveBlockDisplayForm}" scope="request" />

<tk:tkHeader tabId="leaveBlockDisplay">
	<html:form action="/LeaveBlockDisplay.do" method="POST">
		<html:hidden property="navString" styleId="navString" />
		<html:hidden property="year" styleId="year" value="${Form.year}" />
		<table align="center">
			<tbody>
			<tr>
				<td align="center">${Form.targetName}</td>
			</tr>
				<tr>
					<td>
						<button id="nav_lb_prev"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only"
							role="button" title="Previous"
							onclick="this.form.navString.value='PREV';this.form.submit();">
							<span
								class="ui-button-icon-primary ui-icon ui-icon-circle-triangle-w"></span>
							<span class="ui-button-text">Previous</span>
						</button> <span class="header-title">${Form.year}</span>
						<button id="nav_lb_next"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only"
							role="button" title="Next"
							onclick="this.form.navString.value='NEXT';this.form.submit();">
							<span
								class="ui-button-icon-primary ui-icon ui-icon-circle-triangle-e"></span>
							<span class="ui-button-text">Next</span>
						</button>
					</td>
				</tr>
			</tbody>
		</table>

	</html:form>
	
	<div class="leave-block-display">
	
		<div id="leave-block-display-accordion">
			<h3>
				<a href="#">Leave Blocks for ${Form.year}</a>
			</h3>
			<div>
				<display:table name="${Form.leaveEntries}" class="datatable-100"
					cellspacing="0" uid="leaveEntry" sort="external" defaultsort="1" >
					
					<display:column title="Date of Leave" sortable="true" sortProperty="leaveDate" >
						<a href="LeaveCalendar.do?documentId=${leaveEntry.documentId}&calEntryId=${leaveEntry.calendarId}">
							<fmt:formatDate type="date" value="${leaveEntry.leaveDate}"
							pattern="MMM-dd-yyyy" />
						</a>
					</display:column>
					<display:column property="leaveCode" title="Leave Code"/>
					<display:column property="description" title="Description" />
					<display:column property="requestStatus" title="Status" />
					<display:column property="leaveAmount" title="Amount" />
					<display:column title="Timestamp">
						<fmt:formatDate type="both" value="${leaveEntry.timestamp}"
							pattern="MM/dd/yyyy hh:mm a" />
					</display:column>
					<display:column property="assignmentTitle" title="Assignment"/>
					<display:column property="accrualBalance" title="Accrual balances" />
				</display:table>
			</div>

			<h3>
				<a href="#">Days removed in correction</a>
			</h3>
			<div>
				<display:table name="${Form.correctedLeaveEntries}"
					class="datatable-100" cellspacing="0" requestURIcontext="false"
					uid="correctedEntries">
					<display:column title="Date of Leave" >
						<a href="LeaveCalendar.do?documentId=${correctedEntries.documentId}&calEntryId=${correctedEntries.calendarId}">
							<fmt:formatDate type="date" value="${correctedEntries.leaveDate}"
							pattern="MMM-dd-yyyy" />
						</a>
					</display:column>
					<display:column property="leaveCode" title="Leave Code" />
					<display:column property="description" title="Description" />
					<display:column property="principalIdModified"
						title="User Deleted/Modified" />
					<display:column title="Timestamp Deleted/Modified">
						<fmt:formatDate type="both" value="${correctedEntries.timestamp}"
							pattern="MM/dd/yyyy hh:mm a" />
					</display:column>
					<display:column property="leaveAmount" title="Amount" />
					<display:column property="assignmentTitle" title="Assignment"/>
				</display:table>
			</div>

			<h3>
				<a href="#">Inactive Leave Blocks</a>
			</h3>
			<div>
				<display:table name="${Form.inActiveLeaveEntries}"
					class="datatable-100" cellspacing="0" requestURIcontext="false"
					uid="inActiveEntries">
					<display:column title="Date of Leave" >
						<a href="LeaveCalendar.do?documentId=${inActiveEntries.documentId}&calEntryId=${inActiveEntries.calendarId}">
							<fmt:formatDate type="date" value="${inActiveEntries.leaveDate}"
							pattern="MMM-dd-yyyy" />
						</a>
					</display:column>
					<display:column property="leaveCode" title="Leave Code" />
					<display:column property="requestStatus" title="Status" />
					<display:column property="description" title="Description" />
					<display:column property="leaveAmount" title="Amount" />
					<display:column title="Timestamp Deleted/Modified">
						<fmt:formatDate type="both" value="${inActiveEntries.timestamp}"
							pattern="MM/dd/yyyy hh:mm a" />
					</display:column>
					<display:column property="principalIdModified"
						title="User Deleted/Modified" />
				    <display:column property="assignmentTitle" title="Assignment"/>
				</display:table>
			</div>
		</div>
	</div>
</tk:tkHeader>