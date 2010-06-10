
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
					<td><input type="text" id="timesheet-beginDate"/></td>
				</tr>
				<tr>
					<td>End Date:</td>
					<td><input type="text" id="timesheet-endDate"/></td>
				</tr>
				<tr>
					<td>Begin Time:</td>
					<td><input type="text"/></td>
				</tr>
				<tr>
					<td>End Time:</td>
					<td><input type="text"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox"/> Apply hours to each day</td>
				</tr>
				<tr>
					<td>Assignment: </td>
					<td>
						<select>
							<option>assignment #1</option>
							<option>assignment #2</option>
							<option>assignment #3</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Earn code: </td>
					<td>
						<select>
							<option>Regular (RGN)</option>
							<option>Vacation (VAC)</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" class="button" value="Submit" name="Submit"></td>
				</tr>
			</table>
		</div>
		<div id="cal" style="margin-top: 20px; float:left; width:75%;">

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