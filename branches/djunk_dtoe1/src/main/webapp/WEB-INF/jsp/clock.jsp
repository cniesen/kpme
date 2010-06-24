<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<tk:tkHeader tabId="clock">
	<c:set var="Form" value="${ClockActionForm}"/>

	<html:form action="/Clock" method="post">
	<html:hidden property="methodToCall" value=""/>

	<div id="clock">
		<table>
			<tr class="header"><td colspan="2">Clock</td></tr>
			<tr>
				<td class="sub-header">Work Status : </td>
				<td>Clocked Out since 10/15/2009 05:12 AM</td>
			</tr>
			<tr>
				<td class="sub-header">Clock Assignment : </td>
				<td>
					<select>
						<option>HRMS Java Development: $20/hr Rcd#4 UA-VPIT</option>
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

document.write("Your time zone is " + current_timezone + " minutes from GMT<br/>");
document.write("Your time zone is " + current_timezone/60 + " hours from GMT");

//-->
</script>