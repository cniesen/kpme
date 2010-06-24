<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/tlds.jsp"%>
<script language="JavaScript">
</script>
<hr:page nestingPath="timesheetDocument" headerColumns="documentHeader.documentId,documentHeader.payEndDate,documentHeader.universityId,name" hiddenFields="documentId,documentStatus,documentHeader.documentLock.documentId,documentHeader.documentLock.userUniversityId,documentHeader.documentLock.timestamp,documentHeader.documentStatus" startClock="true">
	<hr:hiddenMaintainableList nestingPath="timesheetDocument.documentHeader.timeBlock" hiddenFields="documentId,employeeRecord,workArea,task,earnCode,beginTime,endTime,createdByClock"/>
	<hr:hidden nestingPath="timesheetDocument.hours.payCalendar" hiddenFields="company,paygroup,payEndDate,payBeginDate,offCycle,period,periodWeeks"/>
	<hr:hidden nestingPath="datedHourDetail.hourDetail.hoursDetail" hiddenFields="date,tabStatus"/>
	<hr:entityContainer nestingPath="datedHourDetail.hourDetail" columns="assignment,earnCode,hours,beginTime,endTime"/>
	<br />
	<br />
	<hr:listMaintenance nestingPath="datedHourDetail.checkedPayCalendarDate" columns="displayDate,checked" style="horizontal-multiple-row-multiple-color" actions="supress" suppressShowHide="true"/>
	<hr:tabFooter/>
	<hr:hiddenMaintainableList nestingPath="timesheetDocument.hours.hoursDetail" hiddenFields="date,tabStatus">
	    <hr:hiddenMaintainableList nestingPath="hourDetail" hiddenFields="assignment,earnCode,hours,beginTime,endTime,overtimeEarnCode,overtimeHours,userUniversityId,timestamp,shiftEarnCode,shiftHours,premiumEarnCode,premiumHours"/>			
	</hr:hiddenMaintainableList>
</hr:page>
