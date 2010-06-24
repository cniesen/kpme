<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>


<!--  is this part needed? -->
<c:if test="${StrutsActionForm.maintenanceSearch && rowsToAddCollector}">
	<c:set var="nestingPath" value="${nestingPath},rowsToAddCollector"/>
</c:if>

<div class="warning">
  <c:if test="${StrutsActionForm.jstlPropertyWrapper[nestingPath].warned}">
 	<strong>Warnings:</strong>
  </c:if>

<c:forTokens items="${nestingPath}" delims="," var="entityFullName">
	<hr:entityWarningMessages entity="${StrutsActionForm.jstlPropertyWrapper[entityFullName]}"/>	
</c:forTokens>

</div>