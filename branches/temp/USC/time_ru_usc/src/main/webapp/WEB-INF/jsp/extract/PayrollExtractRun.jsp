<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ include file="/jsp/tlds.jsp"%>
<hr:page>
	<hr:entity nestingPath="payrollExtractSchedule" columns="payEndDate,scheduledBeginTime,status" hiddenFields="effectiveDate,effectiveSequence,active" />
</hr:page>