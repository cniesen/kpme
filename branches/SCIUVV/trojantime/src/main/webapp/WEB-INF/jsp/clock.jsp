<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<c:set var="Form" value="${ClockActionForm}" scope="request"/>

<c:out value="${ClockActionForm}"/>

<tk:tkHeader tabId="clock">

	Principal ID: ${principalId}
	<br/>
	Assignments : ${assignments}

	<c:choose>
		<c:when test="">
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>


	<html:form action="/Clock" method="post">
	<html:hidden property="methodToCall" value=""/>

	<div id="clock">
		<table>
			<tr class="header"><td colspan="2">Clock</td></tr>
			<tr>
				<td class="sub-header">Current Time : </td>
				<td><div class="jClock"></div></td>
			</tr>
			<tr>
				<td class="sub-header">Work Status : </td>
				<td>Clocked Out since 10/15/2009 05:12 AM</td>
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
				<td colspan="2" align="center"><input type="button" class="button" value="Clock In" name="clockIn" onclick="this.form.methodToCall.value='clockIn'; this.form.submit();"></td>
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