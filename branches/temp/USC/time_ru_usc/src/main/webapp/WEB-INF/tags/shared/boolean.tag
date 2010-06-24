<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="field" required="true" type="edu.iu.uis.hr.client.EntityField"%>
<%@ attribute name="displayOnly" required="false"%>
<%@ attribute name="hidden" required="false"%>

<c:choose>
	<c:when test="${(hidden == 'true')}">
		<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}"/>
	</c:when>
	<c:when test="${(displayOnly == 'true') || field.displayOnly}">
		<html:checkbox name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" disabled="true" styleClass="radio"/>
		<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}"/>
	</c:when>
	<c:otherwise>
		<html:checkbox name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" styleClass="radio"/>
	</c:otherwise>
</c:choose>
