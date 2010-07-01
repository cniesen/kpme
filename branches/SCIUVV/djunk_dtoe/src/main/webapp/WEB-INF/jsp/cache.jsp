<%@ include file="/jsp/tlds.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.net.*"%>
<%@page import="com.opensymphony.oscache.extra.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />

<SCRIPT type="text/javascript">
      function jobAction(jobName, jobGroup, jobAction) {
            document.forms[0].elements['existingJobName'].value = jobName;
            document.forms[0].elements['existingJobGroup'].value = jobGroup;
            document.forms[0].elements['method'].value = jobAction;
            document.forms[0].submit();
        }
      function scheduleJob() {
            document.forms[0].elements['method'].value = 'scheduleJob';
            document.forms[0].submit();
        }        
</SCRIPT>

</head>
<body>
<div id="headerarea" class="headerarea"><span class="left">
<img border="0" alt="Logo" src="jsp/images/time_logo3.gif" /> </span> <br />
</div>
<center><html:form action="/CacheAdministration">
	<input type="hidden" name="method" value="" />
	<a href="CacheAdministration.do?methodToCall=start">Show All</a>
	<br />
	<br />
	<table width="75%" border="2" cellpadding="4" cellspacing="4"
		class="datatable">
		<tr>
			<th align=left>Group Key</th>
			<th align=left>Group Action</th>
		</tr>
		<c:forEach items="${groups}" var="mapEntry" varStatus="groupStatus">
			<tr>
				<td valign=top>${mapEntry.key}</td>
				<td>
				<table border="2" cellpadding="2" cellspacing="2" class="datatable">
					<tr>
						<td align="left" valign="top"><a
							href="CacheAdministration.do?methodToCall=flushGroup&amp;groupKey=${mapEntry.key}">Flush</a>
						</td>
						<td align="right"><c:choose>
							<c:when test="${KualiForm.groupKey != mapEntry.key}">
								<a
									href="CacheAdministration.do?methodToCall=showGroup&amp;groupKey=${mapEntry.key}">Show</a>
							</c:when>
							<c:otherwise>
								<table width="25%" border="2" cellpadding="2" cellspacing="2"
									class="datatable" align="right">
									<tr>
										<th align=left>Item Key</th>
										<th align=left>Item Action</th>
									</tr>
									<c:forEach items="${mapEntry.value}" var='valueEntry'
										varStatus="objectStatus">
										<tr>
											<td valign=top>${valueEntry}</td>
											<td align="center"><a
												href="CacheAdministration.do?methodToCall=flushItem&amp;itemKey=${valueEntry}">Flush</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose></td>
					</tr>
				</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</html:form>
<br />
<br />
<%
	Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

	for (NetworkInterface netint : Collections.list(nets)) {

		Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

		for (InetAddress inetAddress : Collections.list(inetAddresses)) {

			pageContext.getOut().write("InetAddress : " + inetAddress + "<br>");
		}
	}
	  
	StatisticListenerImpl stats = new StatisticListenerImpl();
	pageContext.getOut().write(stats.toString());
%>
</center>
</body>

</html>