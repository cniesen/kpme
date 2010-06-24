<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/tlds.jsp"%>

<c:choose>
	<c:when test="${param.kiosk == 'true'}">
		<script language="Javascript">
				function openSupervisorNotificationWindow(networkId) {		
				    field = document.NotifySupervisorLookupForm['searchCriteria.assignment'];
			        url =  "TimeApproverNotification.do?method=load"
			             + "&supervisorNotification.networkId=" + networkId	             
			             + "&supervisorNotification.assignment=" + field.options[field.selectedIndex].value;	 
			        var notifySupervisorsWindowParameters = "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=no,directories=no,left=0,top=0";          
				    var activeWindow = window.open(url, "mainContent", notifySupervisorsWindowParameters);
				    activeWindow.focus();
			}
		</script>
		<strong>Notify Supervisor</strong><br>
	</c:when>
	<c:otherwise>
		<script language="Javascript">
				function openSupervisorNotificationWindow(networkId) {		
				    field = document.NotifySupervisorLookupForm['searchCriteria.assignment'];
			        url =  "TimeApproverNotification.do?method=load"
			             + "&supervisorNotification.networkId=" + networkId	             
			             + "&supervisorNotification.assignment=" + field.options[field.selectedIndex].value;	                            
				    var activeWindow = window.open(url, "_blank", windowParameters);
				    activeWindow.focus();	    
				}
		</script>
	</c:otherwise>
</c:choose>
<hr:channelPage>
  <hr:channelEntity columns="assignment" hiddenFields="" nestingPath="searchCriteria" />
</hr:channelPage>