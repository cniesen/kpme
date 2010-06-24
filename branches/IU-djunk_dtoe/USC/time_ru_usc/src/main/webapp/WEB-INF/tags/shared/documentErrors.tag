<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>
</div>
<c:if test="${StrutsActionForm.erroneous}">
	<div class="left-errmsg">
		<div class="error">
			Errors found on page.
			<hr:entityErrors nestingPath="${nestingPath}"/>
		</div>
	</div>
</c:if>
<c:if test="${StrutsActionForm.warned}">
	<div class="left-warnmsg">
		<div class="warning">
			Warnings found on page.
			<hr:entityWarnings nestingPath="${nestingPath}"/>
		</div>
	</div>
</c:if>
<div id="workarea">
