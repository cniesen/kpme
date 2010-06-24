<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language=JavaScript src="https://docs.onestart.iu.edu/dav/MY/shared/OneStartGlobal.js"></script>

<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="headerColumns" required="false"%>
<%@ attribute name="nestingPath" required="false"%>
<%@ attribute name="startClock" required="false"%>

<html>
	<head>
		<hr:javaScript />
		<fmt:message key="css.html" bundle="${springMessages}" />
		<title>${StrutsActionForm.pageTitle}</title>
	</head>
	<body>
		<div id="workarea">
			<jsp:doBody/>
		</div>
  	</body>
</html>
