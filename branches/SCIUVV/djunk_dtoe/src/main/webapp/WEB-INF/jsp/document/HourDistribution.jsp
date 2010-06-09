<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/jsp/tlds.jsp"%>


       <hr:page nestingPath="timesheetDocument" headerColumns="documentHeader.documentId,documentHeader.payEndDate,documentHeader.universityId,name" hiddenFields="documentId,documentStatus,documentHeader.documentLock.documentId,documentHeader.documentLock.userUniversityId,documentHeader.documentLock.timestamp,documentHeader.documentStatus" startClock="false">

			<hr:listMaintenance nestingPath="timesheetDocument.timeBlockToDistributeList" columns="assignment,beginTime,endTime,hours,hoursDetail.date" hiddenFields="earnCode,createdByClock,overtimeEarnCode,userUniversityId,timestamp,beginTsTz,endTsTz" style="horizontal" subheadDisplayField="hoursDetail.date" actions="suppress" suppressShowHide="true">
               <hr:listMaintenance nestingPath="distributedHour" columns="assignment,hours,hourDistributionPercent" hiddenFields=""  childList="true" style="horizontal-multiple-row" subheadDisplayField="Hour Distribution"  suppressShowHide="true"/>			
            </hr:listMaintenance>
            <hr:tabFooter/>
            
            <hr:hidden nestingPath="timesheetDocument.clock" hiddenFields="userDoingHourDistribution"/>

           <br><br>
 
		</hr:page>

		
		
		