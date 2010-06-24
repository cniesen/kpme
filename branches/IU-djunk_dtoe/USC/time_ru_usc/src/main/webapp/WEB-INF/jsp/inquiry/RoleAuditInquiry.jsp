<%@ page language="java"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<hr:search
		searchCriteria="department"
		resultColumns="roleUniversityId,userUniversityId,department,timestamp,role,operation,location"
	/>
</hr:page>