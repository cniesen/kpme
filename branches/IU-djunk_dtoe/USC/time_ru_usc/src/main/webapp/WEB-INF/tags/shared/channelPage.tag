<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="hiddenFields" required="false"%>
<html>
	<head>
		<hr:javaScript />
		<link rel=stylesheet href="jsp/css/hr_styles.css" type="text/css">
		<title>${StrutsActionForm.pageTitle}</title>
	</head>


	<body>
		<html:form action="${StrutsActionForm.pageBaseUrl}">
				<jsp:doBody />
				<div class="globalbuttons">
					<c:if test="${StrutsActionForm.modeApplicable}">
	                    <html:hidden name="StrutsActionForm" property="mode.editable" styleId="mode.editable"/>
						<c:forTokens items="${StrutsActionForm.mode.eventNames}" delims="," var="event" varStatus="current">
							<c:choose>
								<c:when test="${event eq 'exitCancel'}">
									<a href="${StrutsActionForm.exitCancelUrl}"><fmt:message key="${StrutsActionForm.exitCancelImageKey}" bundle="${springMessages}" /></a>
								</c:when>
								<c:otherwise>
									<c:set var="fullUrl" value="${event}Url" />
 						            <div align="left">
									<a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
									<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
                                    </div>
								</c:otherwise>
							</c:choose>
						</c:forTokens>
					</c:if>
				</div>
		</html:form>
	</body>
	
	
</html>
