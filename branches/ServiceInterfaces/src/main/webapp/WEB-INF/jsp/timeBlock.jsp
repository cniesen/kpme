<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<html>
    <head>
        
    </head>
    <body>
	<display:table id="data" name="sessionScope.TimeBlockActionForm.timeBlocks" requestURI="/TimeBlock.do" pagesize="10" export="true">
            <display:column property="tkTimeBlockId" title="ID" sortable="true" />
            <display:column property="documentId" title="DocumentId"  />
            <display:column property="jobNumber" title="JobNumber"  />
            <display:column property="workArea" title="WorkArea" />         
            <display:column property="task" title="Task" />
            <display:column property="hrJobId" title="Job Id" sortable="true" />
            <display:column property="tkWorkAreaId" title="WorkArea Id" sortable="true" />
            <display:column property="tkTaskId" title="Task Id" sortable="true" />
            <display:column property="beginTimestamp" title="BeginTimeStamp" />
            <display:column property="endTimestamp" title="EndTimeStamp" />
            <display:column property="clockLogCreated" title="ClockLogCreated" />
            <display:column property="hours" title="hours"  />
            <display:column property="amount" title="amount" />
            <display:column property="userPrincipalId" title="UserPrincipalId" />
            <display:column property="beginTimestampTimezone" title="BeginTimestampTimeZone" />
            <display:column property="endTimestampTimezone" title="EndTimestampTimeZone" />
            
            
            <display:setProperty name="export.excel.filename" value="TimeBlocks.xls"/>
            <display:setProperty name="export.pdf.filename" value="TimeBlocks.pdf"/>
            <display:setProperty name="export.csv.filename" value="TimeBlocks.CSV"/>
            <display:setProperty name="export.pdf" value="true" />
        </display:table>
              
        
    </body>
</html>