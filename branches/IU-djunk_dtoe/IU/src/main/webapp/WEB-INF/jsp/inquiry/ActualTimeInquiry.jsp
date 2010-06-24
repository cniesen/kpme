<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<hr:page>
	<hr:search
		searchCriteria="documentId"
		hideSearchInput="${param.hideSearchInput}"
		resultColumns="date,assignment,beginTime,actualBeginTime,endTime,actualEndTime"
	/>
</hr:page>