<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search 
		searchCriteria="department,active"
		resultColumns="department,effectiveDate,effectiveSequence,workArea,description,employeeType,overtimePreference,adminDescription,active"/>
</hr:page>