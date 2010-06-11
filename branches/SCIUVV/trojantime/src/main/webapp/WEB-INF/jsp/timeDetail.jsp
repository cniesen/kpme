
<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<tk:tkHeader tabId="timeDetail">
	<html:hidden property="methodToCall" value=""/>

	<div style="height:500px;" class="ui-widget-content">
		<div class="ui-widget" id="timesheet-panel">
			<table>
				<tr>
					<td colspan="2"><span class="timesheet-panel-title">Add time blocks :</span><br/></td>
				</tr>
				<tr>
					<td>Begin Date:</td>
					<td><input type="text" id="timesheet-beginDate" size="10"/></td>
				</tr>
				<tr>
					<td>End Date:</td>
					<td><input type="text" id="timesheet-endDate" size="10"/></td>
				</tr>
				<tr>
					<td>Begin Time:</td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<select>
							<c:forEach var="i" begin="1" end="12" step="1">
								<option>${i}</option>
							</c:forEach>
						</select> :
						<select>
							<c:forEach var="i" begin="1" end="60" step="1">
								<option>${i}</option>
							</c:forEach>
						</select>
						<select>
							<option>am</option>
							<option>pm</option>
						</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>End Time:</td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<select>
							<c:forEach var="i" begin="1" end="12" step="1">
								<option>${i}</option>
							</c:forEach>
						</select> :
						<select>
							<c:forEach var="i" begin="1" end="60" step="1">
								<option>${i}</option>
							</c:forEach>
						</select>
						<select>
							<option>am</option>
							<option>pm</option>
						</select>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox"/> Apply hours to each day</td>
					<td></td>
				</tr>
				<tr>
					<td>Assignment: </td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<select>
							<option>Operations/AIS</option>
							<option>AIS Technical & User Support</option>
							<option>System Support Services</option>
						</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td>Earn code: </td>
					<td>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<select>
							<option>RGN: Regular</option>
							<option>WEP: Emergency Weather Premium</option>
							<option>HAZ: Hazard Pay - 1.50</option>
							<option>HIP: Holiday Incentive</option>
							<option>OC1: On Call - 1.50</option>
							<option>OC2: On Call - 2.00</option>
							<option>PRM: Premium</option>
						</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" class="button" value="Submit" name="Submit"></td>
				</tr>
			</table>
		</div>
		<div id="cal" style="margin-top: 20px; float:left; width:74%; font-size:.9em;">

			<div id="timesheet-summary">
			<table>
				<thead>
					<tr>
						<td class="header" colspan="15">Summary</td>
					</tr>
					<tr class="ui-state-default">
						<td></td>
						<td>5/30 Sun</td>
						<td>5/31 Mon</td>
						<td>6/1 Tue</td>
						<td>6/2 Wed</td>
						<td>6/3 Thu</td>
						<td>6/4 Fri</td>
						<td>6/5 Sat</td>
						<td>6/6 Sun</td>
						<td>6/7 Mon</td>
						<td>6/8 Tue</td>
						<td>6/9 Wed</td>
						<td>6/10 Thu</td>
						<td>6/11 Fri</td>
						<td>6/12 Sat</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Hours</td>
						<td></td>
						<td></td>
						<td></td>
						<td>8 RGN</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			</div>

		</div>


	</div>
</tk:tkHeader>