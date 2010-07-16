<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="tab" value="monthlyCalendar"/>
<c:set var="Form" value="${CalendarForm}"/>

<%@ include file="/WEB-INF/jsp/t2Header.jsp"%>

<div class="container">
	<div class="span-4">
<%-- TODO 	   <%@ include file="/WEB-INF/jsp/widgets/addDateRange.jsp"%> 
	   <%@ include file="/WEB-INF/jsp/widgets/correctionMode.jsp"%>  --%>
	</div>
    <div class="span-19 push-1 last">
	   <%@ include file="/WEB-INF/jsp/viewCalendar.jsp"%>
<%-- TODO	   <%@ include file="/WEB-INF/jsp/widgets/balanceInformation.jsp"%> --%>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/t2Footer.jsp"%>
