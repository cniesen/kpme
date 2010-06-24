<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>

<c:if test="${StrutsActionForm.maintenanceSearch && rowsToAddCollector}">
	<c:set var="nestingPath" value="${nestingPath},rowsToAddCollector"/>
</c:if>

<div class="error">
  <c:if test="${StrutsActionForm.jstlPropertyWrapper[nestingPath].erroneous}">
 	<strong>Errors:</strong>
  </c:if>

<c:forTokens items="${nestingPath}" delims="," var="entityFullName">
	<hr:entityErrorMessages entity="${StrutsActionForm.jstlPropertyWrapper[entityFullName]}"/>	
</c:forTokens>

</div>