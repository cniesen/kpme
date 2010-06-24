<!doctype html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script language="JavaScript" src="https://docs.onestart.iu.edu/dav/MY/shared/OneStartGlobal.js"></script>
<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="headerColumns" required="false"%>
<%@ attribute name="nestingPath" required="false"%>
<%@ attribute name="startClock" required="false"%>
<%@ attribute name="waitToFinishLoadingPage" required="false"%>
<%@ attribute name="isLiteMode" required="false" type="java.lang.Boolean"%>


<c:if test="${!empty StrutsActionForm.environment && StrutsActionForm.environment=='prd'}">
  <meta http-equiv="refresh" content="1800;url=Logout.do?method=sessionExpired">
</c:if>

<c:if test="${!empty waitToFinishLoadingPage}">
   <script language="javascript">
	   var finishedLoadingPage = false;
   </script>
</c:if>

<html>
	<head>
	    <c:if test="${!isLiteMode}">
		<hr:javaScript />
		</c:if>
		<fmt:message key="css.html" bundle="${springMessages}" />
		<title>${StrutsActionForm.pageTitle}</title>
	</head>

	<c:choose>
		<c:when test="${!empty startClock}">
		<c:choose>
			<c:when test="${!isLiteMode}">
			     <body onLoad="checkBrowserSupport();initiateDisplayTimeOffset();displayServerTime();initializeEarnCodeDropdowns();scrollToCoordinates();setLinksToNotRegisterUnload();" onBeforeUnload="return onBeforeUnloadEventBrowserTest();">
			</c:when>
			<c:otherwise>
    			 <body>
			</c:otherwise>
		</c:choose>

		</c:when>
		<c:otherwise>
			<body>
		</c:otherwise>
	</c:choose>

	<html:form action="${StrutsActionForm.pageBaseUrl}" >

		<div class="headerarea" id="headerarea">
			<span class="left">${StrutsActionForm.logoImgHtml}</span>
			<c:if test="${StrutsActionForm.document && StrutsActionForm.kioskUser}">
			         <html:hidden name="StrutsActionForm" property="kioskUser"/>
					 <div id="kioskLogout" class="headerarea" style="background:#ffff77">
					 <span style="position: relative; left:5px;"><br/>
					  <b>For security purposes please
					  <a href="Logout.do?method=logout" target="_top"><img src="jsp/images/buttonsmall_logout.gif" alt="Logout" align="middle" /></a>
					  when you are finished.</b>
					 </span>
					 </div>
			</c:if>
			<c:choose>
				<c:when test="${!empty headerColumns}">
					<hr:documentHeader headerColumns="${headerColumns}" nestingPath="${nestingPath}" />
				</c:when>
				<c:otherwise>
					<span class="right">
						<br>
						<c:choose>
 							<c:when test="${!StrutsActionForm.incidentReport}">
								<a href="${StrutsActionForm.feedbackUrl}"><fmt:message key="feedback.text" bundle="${springMessages}" /></a>&nbsp;
								<c:if test="${!StrutsActionForm.document}">
									|&nbsp;<a href="${StrutsActionForm.exitUrl}"><fmt:message key="close.text" bundle="${springMessages}" /></a>&nbsp;
								</c:if>
							</c:when>
							<c:otherwise>
								<a href="${StrutsActionForm.exitUrl}"><fmt:message key="close.text" bundle="${springMessages}" /></a>&nbsp;
							</c:otherwise>
						</c:choose>
					 </span>
				</c:otherwise>
			</c:choose>
		</div>

		<br>

		<html:hidden name="StrutsActionForm" property="fieldsToReturn"  styleId="fieldsToReturn"/>
		<html:hidden name="StrutsActionForm" property="backdoorId" styleId="backDoorId"/>
		<hr:hidden nestingPath="${nestingPath}" hiddenFields="${hiddenFields}" />
		<span class="left"> <strong>&nbsp;&nbsp;${StrutsActionForm.pageTitle}</strong> <c:if test="${!StrutsActionForm.helpPage}">
				<a href="javascript:openHelpWindow('${StrutsActionForm.helpUrl}PLH&helpEntry.subject=${StrutsActionForm.pageName}')"><fmt:message key="help.image.html" bundle="${springMessages}" /></a>&nbsp;
				</c:if> </span>
		<c:if test="${!empty headerColumns}">
			<span class="right">
				<c:choose>
					<c:when test="${!StrutsActionForm.incidentReport}">
						<a href="${StrutsActionForm.feedbackUrl}"><fmt:message key="feedback.text" bundle="${springMessages}" /></a>&nbsp;
						<c:if test="${!StrutsActionForm.document}">
							|&nbsp;<a href="${StrutsActionForm.exitUrl}"><fmt:message key="close.text" bundle="${springMessages}" /></a>&nbsp;
						</c:if>
					</c:when>
					<c:otherwise>
						<a href="${StrutsActionForm.exitUrl}"><fmt:message key="close.text" bundle="${springMessages}" /></a>&nbsp;
					</c:otherwise>
				</c:choose>
			</span>
		</c:if>

		<br>
		<br>

		<hr:tableWrapper>
		<c:if test="${StrutsActionForm.document}">
			<hr:documentErrors nestingPath="${nestingPath}"/>
		</c:if>

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
								<a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
								<fmt:message key="clearpixel.image.html" bundle="${springMessages}" />

							</c:otherwise>
						</c:choose>
					</c:forTokens>
				</c:if>
				<br>
				<img src="jsp/images/pixel_clear.gif" alt="" width="20" height="20">
			</div>
		</hr:tableWrapper>
	</html:form>
  </body>
</html>
