<%@include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<tk:tkHeader tabId="TimeSheetCreator">
	
<c:set var="Form" value="${TimeSheetCreatorActionForm}" scope="request"/>

	
	<html:form action="/TimeSheetCreator.do" styleId="time-detail">
	<html:hidden property="methodToCall" value="createTimeSheetForActiveUser"/>
		<table>
		
		<tr>
				<td>Click Submit to create timesheet for principal with active assignment</td>
				<td>${Form.message}</td>
		</tr>
		</table> 
		<input type="submit" value=""/>

	</html:form>
	
</tk:tkHeader>