<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>

<c:forTokens items="${hiddenFields}" delims="," var="hiddenField">
	<c:set var="fullName" value="${nestingPath}.${hiddenField}"/>
    <hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" hidden="true"/>
</c:forTokens>
