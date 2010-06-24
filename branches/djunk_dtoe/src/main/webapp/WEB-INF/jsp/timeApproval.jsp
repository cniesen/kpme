<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<tk:tkHeader tabId="approvals">
	<html:hidden property="methodToCall" value=""/>

	<div class="ui-widget ui-widget-content approvals">

		<table id="approvals-table">
			<tr>
				<td colspan="17" align="center" style="border:none;">
					<span style="font-weight: bold; font-size: 1.5em;">Aaron Neal</span>
				<span style="clear:both; float: right;">Pay Period:
					<select>
						<option>5/2 - 5/16</option>
						<option>5/6 - 5/20</option>
					</select>
					&nbsp;&nbsp;Approval types:
					<select>
						<option>Time</option>
						<option>Leave</option>
					</select>

					</span>
				</td>
			</tr>
			<tr class="ui-widget-header" style="border:none;">
				<td style="border: none; background: #EEEEEE;"></td>
				<td>5/2</td>
				<td>5/3</td>
				<td>5/4</td>
				<td>5/5</td>
				<td>5/6</td>
				<td>5/7</td>
				<td>5/8</td>
				<td>5/9</td>
				<td>5/10</td>
				<td>5/11</td>
				<td>5/12</td>
				<td>5/13</td>
				<td>5/14</td>
				<td>5/15</td>
				<td>5/16</td>
				<td align="center" style="white-space: nowrap;">Select all / none<br/><input type="checkbox" id="selectAll"/></td>
			</tr>

			<tr>
				<td style="border: none; white-space: nowrap;">Kenneth Lee (assignment #1)</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td></td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td></td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td align="center"><input type="checkbox" name="selectedEmpl" /></td>
			</tr>

			<tr>
				<td style="border: none; white-space: nowrap;">Kenneth Lee (assignment #2)</td>
				<td>RGN: 3</td>
				<td>RGN: 4</td>
				<td>RGN: 2</td>
				<td>RGN: 4</td>
				<td>RGN: 1</td>
				<td>RGN: 1</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td></td>
				<td></td>
				<td></td>
				<td>RGN: 4</td>
				<td></td>
				<td>RGN: 4</td>
				<td align="center"><input type="checkbox" name="selectedEmpl" /></td>
			</tr>

			<tr>
				<td style="border: none; white-space: nowrap;">Allen Fox (assignment #1)</td>
				<td>RGN: 3</td>
				<td>RGN: 4</td>
				<td>RGN: 5</td>
				<td>RGN: 4</td>
				<td>RGN: 2</td>
				<td></td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td></td>
				<td>RGN: 2</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td align="center"><input type="checkbox" name="selectedEmpl" /></td>
			</tr>

			<tr>
				<td style="border: none; white-space: nowrap;">Damion Junk (assignment #1)</td>
				<td>RGN: 7</td>
				<td>RGN: 4</td>
				<td>RGN: 6</td>
				<td>RGN: 4</td>
				<td></td>
				<td>RGN: 4</td>
				<td>RGN: 3</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td></td>
				<td>RGN: 5</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td>RGN: 4</td>
				<td align="center"><input type="checkbox" name="selectedEmpl" /></td>
			</tr>
			<tr><td colspan="17" align="center" style="border:none;"><input type="button" class="button" value="Approve" name="Approve"></td></tr>
		</table>
	</div>

</tk:tkHeader>