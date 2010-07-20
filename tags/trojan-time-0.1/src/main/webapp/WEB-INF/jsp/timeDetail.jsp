<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<tk:tkHeader tabId="timeDetail">
	<html:hidden property="methodToCall" value=""/>

	<div style="clear:both;" class="ui-widget-content">
		<%--
		<div class="ui-widget" id="timesheet-panel">
			<table>
				<tr>
					<td colspan="2"><span class="timesheet-panel-title">Add time blocks :</span><br/></td>
				</tr>
				<tr>
					<td>Clocked In:</td>
					<td>
						<input name="beginTime" id="beginTimeField" type="text" size="10" onblur="magicTime(this)" onfocus="if (this.className != 'error') this.select()"/>
						 <button tabindex="-1" style="width:20px; height:20px; vertical-align: text-top"
						 title="Supported formats:<br/>9a, 9 am, 9 a.m.,  9:00a, 9:45a, 3p, 0900, 15:30, 1530"
						 id="beginTimeHelp">help</button>
						<div id="beginTimeField-messages"></div>
					</td>
				</tr>
				<tr>
					<td>Clocked Out:</td>
					<td>
						<input name="endTime" id="endTimeField" type="text" size="10" onblur="magicTime(this)" onfocus="if (this.className != 'error') this.select()"/>
						<button style="width:20px; height:20px; vertical-align: text-top" id="endTimeHelp"
						title="Supported formats:<br/>9a, 9 am, 9 a.m.,  9:00a, 9:45a, 3p, 0900, 15:30, 1530">help</button>
						<div id="endTimeField-messages"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox"/> Apply time to each day</td>
					<td></td>
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
						<tk:earnCode/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" class="button" value="Submit" name="Submit"></td>
				</tr>
			</table>
		</div>
		--%>
		<div id="cal" style="margin-top: 20px; width:100%; font-size:.9em;">

		<div id="dialog-form" title="Add time blocks:">
			<%-- <p class="validateTips">All form fields are required.</p>  --%>

			<form>
				<div class="ui-widget" id="timesheet-panel">
					<table>
						<tr>
							<td>Clocked In:</td>
							<td>
								<input name="beginTime" id="beginTimeField" type="text" size="10" onblur="magicTime(this)" onfocus="if (this.className != 'error') this.select()"/>
								 <button tabindex="-1" style="width:20px; height:20px; vertical-align: text-top"
								 title="Supported formats:<br/>9a, 9 am, 9 a.m.,  9:00a, 9:45a, 3p, 0900, 15:30, 1530"
								 id="beginTimeHelp">help</button>
								<%--<div id="beginTimeField-messages"></div>  --%>
							</td>
						</tr>
						<tr>
							<td>Clocked Out:</td>
							<td>
								<input name="endTime" id="endTimeField" type="text" size="10" onblur="magicTime(this)" onfocus="if (this.className != 'error') this.select()"/>
								<button style="width:20px; height:20px; vertical-align: text-top" id="endTimeHelp"
								title="Supported formats:<br/>9a, 9 am, 9 a.m.,  9:00a, 9:45a, 3p, 0900, 15:30, 1530">help</button>
								<%--<div id="endTimeField-messages"></div>  --%>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="checkbox"/> Apply time to each day</td>
							<td></td>
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
							<td>Assignment: </td>
							<td>
								<select>
									<option>Operations/AIS</option>
									<option>AIS Technical & User Support</option>
									<option>System Support Services</option>
								</select>
							</td>
						<tr>
							<td>Earn code: </td>
							<td>
								<tk:earnCode/>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>


		<form id="timesheetForm" method="post" action="#">
		<div id="timesheet">
			<!-- <div class="toolbar-timesheet-table-week1"></div>  -->
			<table class="timesheet-table-week1">
				<thead>
					<tr>
						<th></th>
						<th><bean:message key="time.detail.assignment"/></th>
						<th><bean:message key="time.detail.earnCode"/></th>
						<th><bean:message key="time.detail.action"/></th>
						<th>4/1 Sun</th>
						<th>4/2 Mon</th>
						<th>4/3 Tue</th>
						<th>4/4 Wed</th>
						<th>4/5 Thu</th>
						<th>4/6 Fri</th>
						<th>4/7 Sat</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td>
							<select>
								<option>HRMS Java Developer</option>
								<option>HRMS PS Developer</option>
							</select>
						</td>
						<td><tk:earnCode/></td>
						<td>Time In <br/> Time Out</td>
						<td><input type="text" size="8" name="1"/><br/><input type="text" size="8" name="2"/></td>
						<td><input type="text" size="8" name="3"/><br/><input type="text" size="8" name="4"/></td>
						<td><input type="text" size="8" name="5"/><br/><input type="text" size="8" name="6"/></td>
						<td><input type="text" size="8" name="7"/><br/><input type="text" size="8" name="8"/></td>
						<td><input type="text" size="8" name="9"/><br/><input type="text" size="8" name="10"/></td>
						<td><input type="text" size="8" name="11"><br/><input type="text" size="8" name="12"/></td>
						<td><input type="text" size="8" name="13"><br/><input type="text" size="8" name="14"/></td>
					</tr>
					<%--
					<tr>
						<td></td>
						<td>RGN</td>
						<td>Time Out</td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
					</tr>
					 --%>
				</tbody>
			</table>

			<!-- <div class="toolbar-timesheet-table-week2"></div>  -->
			<table class="timesheet-table-week2">
				<thead>
					<tr>
						<th></th>
						<th>Account Name</th>
						<th>Earn Code</th>
						<th>Action</th>
						<th>4/8 Sun</th>
						<th>4/9 Mon</th>
						<th>4/10 Tue</th>
						<th>4/11 Wed</th>
						<th>4/12 Thu</th>
						<th>4/13 Fri</th>
						<th>4/14 Sat</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td>
							<select>
								<option>HRMS Java Developer</option>
								<option>HRMS PS Developer</option>
							</select>
						</td>
						<td><tk:earnCode/></td>
						<td>Time In <br/> Time Out</td>
						<td><input type="text" size="8" name="1"/><br/><input type="text" size="8" name="2"/></td>
						<td><input type="text" size="8" name="3"/><br/><input type="text" size="8" name="4"/></td>
						<td><input type="text" size="8" name="5"/><br/><input type="text" size="8" name="6"/></td>
						<td><input type="text" size="8" name="7"/><br/><input type="text" size="8" name="8"/></td>
						<td><input type="text" size="8" name="9"/><br/><input type="text" size="8" name="10"/></td>
						<td><input type="text" size="8" name="11"><br/><input type="text" size="8" name="12"/></td>
						<td><input type="text" size="8" name="13"><br/><input type="text" size="8" name="14"/></td>
					</tr>
					<%--
					<tr>
						<td></td>
						<td>RGN</td>
						<td>Time Out</td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
						<td><input type="text" size="8"/></td>
					</tr>
					 --%>
				</tbody>
			</table>
			<div style="width:100%; font-size: .8em; text-align: center; margin-top: 10px;"><input type="submit" class="button" name="submit" value="Submit"/></div>
		</div>
		</form>

		<div id="timesheet-summary">
			<div style="clear:both; text-align:center; font-weight: bold; margin-top:20px; margin-bottom: 5px;">Summary <%--(<a href="#" id="basic">Basic</a> / <a href="#" id="advance">Advanced</a> ) --%></div>
			<div id="timesheet-table-basic">

			<table style="width:100%;">
				<thead>
					<tr class="ui-state-default">
						<td></td>
						<td>5/30 Sun</td>
						<td>5/31 Mon</td>
						<td>6/1 Tue</td>
						<td>6/2 Wed</td>
						<td>6/3 Thu</td>
						<td>6/4 Fri</td>
						<td>6/5 Sat</td>
						<td>Weekly Total</td>
						<td>6/6 Sun</td>
						<td>6/7 Mon</td>
						<td>6/8 Tue</td>
						<td>6/9 Wed</td>
						<td>6/10 Thu</td>
						<td>6/11 Fri</td>
						<td>6/12 Sat</td>
						<td>Weekly Total</td>
						<td>Period Total</td>
					</tr>
				</thead>
				<tbody>
					<tr style="border-bottom-style: double; font-weight: bold;">
						<td>Worked Hours:</td>
						<td>4.00</td>
						<td></td>
						<td></td>
						<td>4.00</td>
						<td></td>
						<td></td>
						<td>2.00</td>
						<td style="background-color: #CFCFCF; font-weight: bold;">10.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>2.00</td>
						<td style="background-color: #CFCFCF">22.00</td>
						<td style="background-color: #CFCFCF">32.00</td>
					</tr>
					<tr>
						<td colspan="18" style="text-align:left; font-weight: bold;">RGH: Regular Pay Hourly</td>
					</tr>
					<tr style="">
						<td>HRMS Java Developer:</td>
						<td>4.00</td>
						<td></td>
						<td></td>
						<td>4.00</td>
						<td></td>
						<td></td>
						<td></td>
						<td style="background-color: #CFCFCF;font-weight: bold;">8:00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td></td>
						<td style="background-color: #CFCFCF; font-weight: bold;">20.00</td>
						<td style="background-color: #CFCFCF; font-weight: bold;">28.00</td>
					</tr>
					<tr style="font-weight:bold; border-bottom-style: double;">
						<td>Regular Pay Hours:</td>
						<td>4.00</td>
						<td></td>
						<td></td>
						<td>4.00</td>
						<td></td>
						<td></td>
						<td></td>
						<td style="background-color: #CFCFCF">8.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td>4.00</td>
						<td></td>
						<td style="background-color: #CFCFCF">20.00</td>
						<td style="background-color: #CFCFCF">28.00</td>
					</tr>
					<tr>
						<td colspan="18" style="text-align:left; font-weight: bold;">OVT: Overtime</td>
					</tr>
					<tr >
						<td>HRMS Java Developer:</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>2.00</td>
						<td style="background-color: #CFCFCF; font-weight: bold">2.00</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>2.00</td>
						<td style="background-color: #CFCFCF; font-weight: bold;">2.00</td>
						<td style="background-color: #CFCFCF; font-weight: bold;">4.00</td>
					</tr>
					<tr style="font-weight:bold; border-bottom-style: double;">
						<td>Overtime:</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>2.00</td>
						<td style="background-color: #CFCFCF">2.00</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>2.00</td>
						<td style="background-color: #CFCFCF">2.00</td>
						<td style="background-color: #CFCFCF">4.00</td>
					</tr>
				</tbody>
			</table>

			<%--
			<table style="width:100%;">
				<thead>
					<tr class="ui-state-default">
						<td colspan="2">Hours / Assignments</td>
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
						<td rowspan="2" style="vertical-align: middle;">Hours</td>
						<td>HRMS Java developer</td>
						<td></td>
						<td></td>
						<td></td>
						<td>8 RGN</td>
						<td></td>
						<td>4 VAC</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>HRMS PS developer</td>
						<td></td>
						<td></td>
						<td></td>
						<td>8 RGN</td>
						<td></td>
						<td>4 VAC</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="font-weight:bold;">
						<td rowspan="2" style="vertical-align: middle;">Total</td>
						<td>HRMS Java developer</td>
						<td colspan="14">8 RGN, 4 VAC</td>
					</tr>
					<tr style="font-weight:bold;">
						<td>HRMS PS developer</td>
						<td colspan="14">8 RGN, 4 VAC</td>
					</tr>
				</tbody>
			</table>
			</div>

			<div id="timesheet-table-advance" style="display:none;">
			<table style="width:100%;">
				<thead>
					<tr class="ui-state-default">
						<td colspan="2">Hours / Assignments</td>
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
						<td rowspan="2" style="vertical-align: middle;">Hours</td>
						<td>HRMS Java developer</td>
						<td></td>
						<td></td>
						<td></td>
						<td>In:08:00am - Out:12:00pm<br/>Lunch:12:00pm - 1:00pm</td>
						<td></td>
						<td>4 VAC</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>HRMS PS developer</td>
						<td></td>
						<td></td>
						<td></td>
						<td>In:08:00am - Out:12:00pm</td>
						<td></td>
						<td>4 VAC</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr style="font-weight:bold;">
						<td rowspan="2" style="vertical-align: middle;">Total</td>
						<td>HRMS Java developer</td>
						<td colspan="14">8 RGN, 4 VAC</td>
					</tr>
					<tr style="font-weight:bold;">
						<td>HRMS PS developer</td>
						<td colspan="14">8 RGN, 4 VAC</td>
					</tr>
				</tbody>
			</table>
			--%>
			</div>
		</div>
	</div>
</tk:tkHeader>