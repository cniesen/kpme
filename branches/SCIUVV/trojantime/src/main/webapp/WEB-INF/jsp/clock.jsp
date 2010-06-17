<%@include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<c:set var="Form" value="${ClockActionForm}" scope="request"/>
<c:choose>
	<c:when test="${Form.clockAction eq 'CI'}">
		<c:set var="clockActionDescription" value="Clock In"/>
		<c:set var="lastClockActionMessage" value="Clocked out since ${Form.lastClockActionTimestampFormatted}"/>
	</c:when>
	<c:otherwise>
		<c:set var="clockActionDescription" value="Clock Out"/>
		<c:set var="lastClockActionMessage" value="Clocked in since ${Form.lastClockActionTimestampFormatted}"/>
	</c:otherwise>
</c:choose>
<tk:tkHeader tabId="clock">


	<html:form action="/Clock" method="post">
	<html:hidden property="methodToCall" value=""/>
	<html:hidden property="clockAction"/>
	<html:hidden property="lastClockedInTime" value="${Form.lastClockActionTimestamp}" styleId="lastClockedInTime"/>

	<div id="clock">
		<table>
			<tr class="header"><td colspan="2">Clock</td></tr>
			<tr>
				<td class="sub-header">Current Time : </td>
				<td><div class="jClock"></div></td>
			</tr>
			<tr>
				<td class="sub-header">Work Status : </td>
				<td>${lastClockActionMessage}</td>
			</tr>
			<tr>
				<td class="sub-header">Elapsed Clocked In Time : </td>

				<td>
					<c:choose>
						<c:when test="${Form.clockAction eq 'CO'}">
							<span class="elapsedTime"></span>
						</c:when>
						<c:otherwise>
							00:00:00
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="sub-header">Clock Assignment : </td>
				<td>
					<select>
							<option>Operations/AIS</option>
							<option>AIS Technical & User Support</option>
							<option>System Support Services</option>
					</select>
				</td>
			</tr>
			<tr class="footer">
				<td colspan="2" align="center"><input type="button" class="button" value="${clockActionDescription}" name="clockAction" onclick="this.form.methodToCall.value='clockAction'; this.form.submit();"></td>
			</tr>
		</table>
	</div>

	</html:form>
</tk:tkHeader>

<script language="JavaScript" type="text/javascript">
<!--

var current_date = new Date();
var current_timezone = current_date.getTimezoneOffset();

//document.write("Your time zone is " + current_timezone + " minutes from GMT<br/>");
//document.write("Your time zone is " + current_timezone/60 + " hours from GMT");

//-->
</script>