<%@ page language="java"%>
<%@ include file="/jsp/tlds.jsp"%>
<c:if test="${!empty StrutsActionForm.userRoles.networkId}">
  success||${StrutsActionForm.userRoles.name}||${StrutsActionForm.userRoles.networkId}
</c:if>
