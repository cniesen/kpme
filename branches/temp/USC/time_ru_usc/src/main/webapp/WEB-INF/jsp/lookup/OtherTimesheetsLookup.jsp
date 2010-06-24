<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/tlds.jsp"%>

<c:choose>
	<c:when test="${param.kiosk == 'true'}">
		<script language="Javascript">
			function openNewTimesheetWindow() {
			    field = document.OtherTimesheetsLookupForm['searchCriteria.payPeriod'];
		        url =  "TimesheetDocument.do?method=open&payEndDateStr=" + field.options[field.selectedIndex].value;  
		        var otherTimesheetWindowParameters = "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,directories=no,left=0,top=0";          
			    var activeWindow = window.open(url, "mainContent", otherTimesheetWindowParameters);
			    activeWindow.focus();
			}
		</script>
		<strong>Open Other Timesheets</strong><br>
	</c:when>
	<c:otherwise>
		<script language="Javascript">
				function openNewTimesheetWindow() {
				    field = document.OtherTimesheetsLookupForm['searchCriteria.payPeriod'];
			        url =  "TimesheetDocument.do?method=open&payEndDateStr=" + field.options[field.selectedIndex].value;                
				    var activeWindow = window.open(url, "_blank", windowParameters);
				    activeWindow.focus();
				}
		</script>
	</c:otherwise>
</c:choose>
<hr:channelPage>
  <hr:channelEntity columns="payPeriod" hiddenFields="" nestingPath="searchCriteria" />
</hr:channelPage>