<%@ include file="/jsp/tlds.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:form action="/BatchJob">
 <head>
    <link href="kr/css/kuali.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
 <div id="headerarea" class="headerarea">
    <span class="left">
        <img border="0" alt="Logo" src="jsp/images/time_logo3.gif"/>
    </span>
    <br/>
    <br/>
    <br/>
     
 </div>
 <br/>
 <table>
 <tr>
 <td>
 <div class="portlet">
   <div class="header">
        <h2>Batch Job Entry Search</h2>    
    </div>
    <div align="right" class="portlet-content">
       Job ID: <input type="text" name="tkJobId" value="${StrutsActionForm.tkJobId}"/><br/><br/>
       Job Status: <input type="text" name="jobStatus" value="${StrutsActionForm.jobStatus}"/><br/><br/>
       IP Number:  <input type="text" name="ipNumber" value="${StrutsActionForm.ipNumber}"/><br/><br/>
       Data ID: <input type="text" name="dataId" value="${StrutsActionForm.dataId}"/><br/><br/>
       TK Batch Job ID: <input type="text" name="tkBatchJobId" value="${StrutsActionForm.tkBatchJobId}"/><br/><br/>
       <br/>
       <div align="center">
       <input type="submit" name="method" value="search"/>
  </div>
  </div>
  </td>
  <td>
   <div class="portlet">
   <div class="header">
        <h2>Batch Job Entry Update</h2>    
    </div>
    <div align="right" class="portlet-content">
       Job Status: <input type="text" name="newJobStatus"/>
       <input type="submit" name="method" value="updateStatus"/>
       <br/>
       <br/>
       IpNet: <input type="text" name="newIpNet"/>
       <input type="submit" name="method" value="updateIpNet"/>
       <br/>
       <br/>       
       <div align="center">
       <input type="submit" name="method" value="deleteAll" onclick="return confirm('Are you sure you want to Delete all the current entries?');"/>
       </div>
  </div>
  </div>
  
  </td>
  </tr>
  </table>

 <div class="workarea" id="workarea">
    <display:table name="${StrutsActionForm.batchJobs}" pagesize="100" requestURI="BatchJob.do" cellspacing="0"
                requestURIcontext="false" cellpadding="0" id="row">
        <display:column property="tkJobId"/>
        <display:column property="dataId"/>
        <display:column property="ipNet"/>
        <display:column property="totalTime"/>
        <display:column property="tkBatchJobId"/>
        <display:column property="jobStatus"/>
        <display:column property="jobType"/>
        <display:column property="jobMessage"/>
        <display:column property="exception"/>
    </display:table>
 </div>
</body>
</html:form>