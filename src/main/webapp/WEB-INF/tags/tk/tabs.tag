<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp" %>

<c:if test="${!empty UserSession.loggedInUserPrincipalName}">
    <c:set var="systemAdmin" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isSystemAdmin()%>' />
    <c:set var="locationAdmin" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isLocationAdmin()%>' />
    <c:set var="locationViewOnly" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isDeptViewOnly()%>' />
    <c:set var="departmentAdmin" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isDepartmentAdmin()%>' />
    <c:set var="globalViewOnly" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isGlobalViewOnly()%>' />
    <c:set var="timesheetApprover" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isTimesheetApprover()%>' />
    <c:set var="timesheetReviewer" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isTimesheetReviewer()%>' /> 
    <c:set var="targetActiveEmployee" value='<%=org.kuali.hr.time.util.TKUser.getCurrentTargetRoles().isActiveEmployee()%>' />
    <c:set var="targetSynchronous" value='<%=org.kuali.hr.time.util.TKUser.getCurrentTargetRoles().isSynchronous()%>' />
</c:if>

<div id="tab-section">
    <li id="help" class="ui-state-default ui-corner-top"><a href="Help.do">Help</a></li>
    <c:if test="${systemAdmin || locationAdmin || departmentAdmin || globalViewOnly || locationViewOnly}">
        <li id="departmentAdmin" class="ui-state-default ui-corner-top"><a href="DepartmentAdmin.do">Department Admin</a></li>
    </c:if>
    <li id="personInfo" class="ui-state-default ui-corner-top"><a href="PersonInfo.do">Person Info</a></li>
    <c:if test="${timesheetApprover || timesheetReviewer}">
        <li id="approvals" class="ui-state-default ui-corner-top"><a
                href="TimeApproval.do?methodToCall=loadApprovalTab">Time Approval</a></li>
    </c:if>
    <c:if test="${timesheetApprover || timesheetReviewer}">
    	<li id="leaveApprovals" class="ui-state-default ui-corner-top"><a
            href="LeaveApproval.do?methodToCall=loadApprovalTab">Leave Approval</a></li>
    </c:if>
    <c:if test="${targetActiveEmployee}">
        <c:if test="${Form.leaveEnabled}">
            <li id="leaveCalendar" class="ui-state-default ui-corner-top"><a href="LeaveCalendar.do">Leave Calendar</a></li>
        </c:if>
        <c:if test="${Form.timeEnabled}">
        	<li id="timeDetail" class="ui-state-default ui-corner-top"><a href="TimeDetail.do">Time Detail</a></li>
        </c:if>
        <c:if test="${targetSynchronous and Form.timeEnabled}">
            <li id="clock" class="ui-state-default ui-corner-top"><a href="Clock.do">Clock</a></li>
        </c:if>
    </c:if>
</div>