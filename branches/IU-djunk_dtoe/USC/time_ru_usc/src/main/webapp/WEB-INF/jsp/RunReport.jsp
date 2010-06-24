<%@ include file="/jsp/tlds.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
	<link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />
	<style>
		.odd { background-color: #FFFFFF; }
		.even { background-color: #F0F0F0; }
	</style>	
 </head>
 <body>
 <div id="headerarea" class="headerarea">
<span class="left">
<img border="0" alt="Logo" src="jsp/images/time_logo3.gif"/>
</span>
<br/>
<span class="right" style="margin-right: 10px;">
<a href="TemporaryIndex.do">Admin Index</a> - 
<a href="RunReport.do">Refresh Page</a>
</span>
</div>
 <div align="center" id="main">
 <br/><br/>
 <table width="95%" cellpadding="0" cellspacing="0" style="border-style:ridge; border-color:#7d1100; border-width:thin">
   <tr>
    <td style="background-color:#F0F0F0">
		   <div class="portlet">
	          <div class="header">
	             <h2>Reports</h2>
	          </div>
			  <div class="portlet-content">
		       <c:forEach var="report" items="${TKRunReportForm.reports}">
				<html:form action="/RunReport">
				<input type="hidden" name="method" value="runReport" />
				<html:hidden property="reportToRun" value="${TKRunReportForm.reports[report.key].reportName}"/>		       
				<div><b>${TKRunReportForm.reports[report.key].reportName}</b></div>
					<c:forEach var="myParam" items="${TKRunReportForm.reports[report.key].params}">
						<div>${myParam}: <input type="text" name="paramsForReport" id="paramsForReport" /></div>
					</c:forEach>
				<div><input type="submit" value="Run"/></div><br/>
				</html:form>
		       </c:forEach>
			   </div>    
           </div>
           <c:if test="${TKRunReportForm.reportRunComplete}">
	  		   <div class="portlet">
		          <div class="header">
		             <h2>Report Results</h2>
		          </div>
				  <div class="portlet-content" style="">
				  	<c:if test="${not empty TKRunReportForm.results}">
					    <display:table name="${TKRunReportForm.results}" pagesize="50" requestURI="RunReport.do" cellspacing="0"
					                requestURIcontext="false" cellpadding="0" export="true" id="result" sort="list">					                			                
					    </display:table>
				  	</c:if>
					<c:if test="${empty TKRunReportForm.results}">
						No rows returned.
					</c:if>
				   </div>    
	           </div>
           </c:if>
           <c:if test="${not empty TKRunReportForm.reportError}">
	  		   <div class="portlet">
		          <div class="header">
		             <h2>Oops!</h2>
		          </div>
				  <div class="portlet-content">
					${TKRunReportForm.reportError}
				  </div>    
	           </div>
           </c:if>           
    <br/><br/>
    </td>
   </tr>
 </table>           
<br/><br/>
</div>
</body>

</html>