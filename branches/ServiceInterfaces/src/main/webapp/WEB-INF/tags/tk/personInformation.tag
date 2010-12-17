<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<fieldset>
	<legend>Information</legend>
	<table width="100%">
		<tbody>
			<tr>
				<td>
					<table width="100%">
						<tbody>
							<tr>
								<td>Employee Name:</td>
								<td><c:out value="${KualiForm.user.actualPerson.name}"/></td>
							</tr>
							<tr>
								<td>Employee Number:</td>
								<td></td>
							</tr>
							<tr>
								<td>USC ID Number:</td>
								<td></td>
							</tr>
							<tr>
								<td>Employee User Type:</td>
								<td></td>
							</tr>
							<tr>
								<td>Home Department Name:</td>
								<td></td>
							</tr>
							<tr>
								<td>Home Department Number:</td>
								<td></td>
							</tr>
							<tr>
								<td>Employee Work State:</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</td>
				<td>
					<table width="100%">
						<tbody>
							<tr>
								<td>Supervisor Name:</td>
								<td></td>
							</tr>
							<tr>
								<td>Home Dept. Coordinator:</td>
								<td></td>
							</tr>
							<tr>
								<td>Home Dept. Phone Number:</td>
								<td></td>
							</tr>
							<tr>
								<td>Home Department Email:</td>
								<td></td>
							</tr>
							<tr>
								<td>Start Time:</td>
								<td><c:out value="${KualiForm.payCalendarEntry.beginPeriodDateTime}"/></td>
							</tr>
							<tr>
								<td>Timesheet Status:</td>
								<td></td>
							</tr>
							<tr>
								<td>Period Ending Date:</td>
								<td><c:out value="${KualiForm.payCalendarEntry.endPeriodDateTime}"/></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</fieldset>