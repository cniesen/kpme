<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<tk:tkHeader tabId="timeDetail">
	<html:hidden property="methodToCall" value=""/>

	<div style="height:400px;" class="ui-widget-content">
		<div class="ui-widget" id="timesheet-panel">
			<table>
				<tr>
					<td colspan="2"><span class="timesheet-panel-title">Add time blocks :</span><br/></td>
				</tr>
				<tr>
					<td>Date:</td>
					<td><input type="text" id="timesheet-date"/></td>
				</tr>
				<tr>
					<td>Begin time:</td>
					<td><input type="text"/></td>
				</tr>
				<tr>
					<td>End Date:</td>
					<td><input type="text"/></td>
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
		<div id="cal" style="margin-top: 20px; float:left; width:75%;"></div>
	</div>
</tk:tkHeader>