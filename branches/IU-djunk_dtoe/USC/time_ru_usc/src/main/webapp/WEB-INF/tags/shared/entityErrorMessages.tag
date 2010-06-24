<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="entity" required="true" type="edu.iu.uis.hr.entity.Entity"%>

<c:forEach items="${entity.entityErrors.errorMessages}" var="errorMessage">
		<li style="circle">${errorMessage}</li>
</c:forEach>
