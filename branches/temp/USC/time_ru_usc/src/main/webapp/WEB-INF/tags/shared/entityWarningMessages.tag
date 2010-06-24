<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="entity" required="true" type="edu.iu.uis.hr.entity.Entity"%>

<c:forEach items="${entity.entityWarnings.warningMessages}" var="warningMessage">
		<li style="circle">${warningMessage}</li>
</c:forEach>
