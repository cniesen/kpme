<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<%@ attribute name="tabId" required="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Trojan Time</title>
		<tk:tkInclude/>
	</head>
	<body>
	<input type="hidden" id="tabId" value="${tabId}"/>
	<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
		<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-corner-all ui-header">
			<span class="title">
			<img src="images/usc_logo.gif" style="width:2em; height:2em; vertical-align: middle"/>
				Trojan Time
			</span>
		</ul>
		<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
			<li id="approvals" class="ui-state-default ui-corner-top"><a href="TimeApproval.do">Approvals</a></li>
			<li id="timeDetail" class="ui-state-default ui-corner-top"><a href="TimeDetail.do">Time Detail</a></li>
			<li id="clock" class="ui-state-default ui-corner-top"><a href="Clock.do">Clock</a></li>
		</ul>

		<jsp:doBody />
	</div>

	<tk:tkFooter/>

	</body>
</html>
