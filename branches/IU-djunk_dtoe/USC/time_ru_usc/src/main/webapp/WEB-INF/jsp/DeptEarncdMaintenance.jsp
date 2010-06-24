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
<a href="DeptEarncdMaintenance.do">Refresh Page</a>
</span>
</div>
<br/>
<c:forEach var="message" items="${DeptEarncdMaintenanceForm.messages}">
<div style="font-color: green;">${message}</div>
</c:forEach>
  <div class="portlet">
   <div class="header">
        <h2>Department Earn Code Maintenance</h2>    
    </div>
    <div class="portlet-content">
    <table>
    <tr><td style="vertical-align: top; padding: 15px; text-align: right;">
		 <html:form action="DeptEarncdMaintenance.do">
		 <fieldset>
		 <legend>Dept Earn Code Search</legend><br/>
			 Earn Code: <input type="text" name="earnCode" id="earnCode" /><br/>
			 * = wildcard<br/><br/><br/>
			 <input type="hidden" value="earnCodeSearch" name="method"/>
			 <input type="submit" value="Search"/>
		 </fieldset>
		 </html:form>
 	</td><td style="vertical-align: top; padding: 15px; text-align: right;">
		 <html:form method="get" action="DeptEarncdMaintenance.do">
		 <fieldset>
		 <legend>Add New Dept Earn Code Row</legend>		 
		 <table>
		 <tr><td style="vertical-align: top; text-align: right; padding: 15px;">
			 Department Id: <input type="text" name="addRow.deptid" id="addRow.deptid" /><br/>@ = any<br/><br/>
			 Sal Admin Plan: <input type="text" name="addRow.sal_admin_plan" id="addRow.sal_admin_plan" /><br/><br/>
			 Location: <input type="text" name="addRow.location" id="addRow.location" /><br/>@ = any<br/><br/>
			 Earn Code: <input type="text" name="addRow.erncd" id="addRow.erncd" /><br/><br/>
			 
			 </td><td style="vertical-align: top; text-align: right; padding: 15px;">
			 Employee: <html:checkbox property="addRow.employee"/><br/><br/>
			 Supervisor: <html:checkbox property="addRow.supervisor"/><br/><br/>
			 Payroll Processor: <html:checkbox property="addRow.payrollprocessor"/><br/><br/>
			 Active: <html:checkbox property="addRow.active"/><br/><br/>
			 <input type="hidden" value="addEarnCode" name="method"/>
			 
		 </td></tr></table>
		 <br/>
		 <input type="submit" value="Add New Row"/>
		 </fieldset>
		 </html:form>
 	</td></tr>
 	</table>	
 	</div>
 </div>
            <c:if test="${DeptEarncdMaintenanceForm.runSearch}">
	  		   <div class="portlet">
		          <div class="header">
		             <h2>Department Earn Code Search Results</h2>
		          </div>
				  <div class="portlet-content" style="">
				  	<c:if test="${not empty DeptEarncdMaintenanceForm.searchResults}">
				  		<html:form method="get" action="DeptEarncdMaintenance.do">
					    <display:table name="${DeptEarncdMaintenanceForm.searchResults}" requestURI="DeptEarncdMaintenance.do" cellspacing="0"
					                requestURIcontext="false" cellpadding="0" export="true" id="result" sort="list">
							
							<c:set var="deptErncdKey" value="${result.deptid}-${result.sal_admin_plan}-${result.location}-${result.erncd}" />					                
					    	<c:choose>
					    	<c:when test="${DeptEarncdMaintenanceForm.editRow == deptErncdKey}">
							    	<display:column title="Department Id">
										<input type="hidden" value="saveDeptEarncdRow" name="method"/>
										<input type="hidden" value="${DeptEarncdMaintenanceForm.earnCode}" name="earnCode"/>
										<input type="hidden" value="${deptErncdKey}" name="editRow"/>							    	
							    		<input type="text" id="editRowBean.deptid" name="editRowBean.deptid" value="${result.deptid}" />
							    	</display:column>
							    	<display:column title="Sal Admin Plan" >
							    		<input type="text" id="editRowBean.sal_admin_plan" name="editRowBean.sal_admin_plan" value="${result.sal_admin_plan}" />
							    	</display:column>
							    	<display:column title="Location" >
							    		<input type="text" id="editRowBean.location" name="editRowBean.location" value="${result.location}" />
							    	</display:column>
							    	<display:column title="Earn Code" >	
							    		<input type="text" id="editRowBean.erncd" name="editRowBean.erncd" value="${result.erncd}" />
							    	</display:column>
							    	<display:column title="Employee" >
							    		<c:choose>
							    		<c:when test="${result.employee == true}">
							    			<input type="checkbox" id="editRowBean.employee" name="editRowBean.employee" CHECKED />
							    		</c:when>
							    		<c:otherwise>
							    			<input type="checkbox" id="editRowBean.employee" name="editRowBean.employee" />
							    		</c:otherwise>
							    		</c:choose>		
							    	</display:column>
							    	<display:column title="Supervisor" >
							    		<c:choose>
							    		<c:when test="${result.supervisor == true}">
							    			<input type="checkbox" id="editRowBean.supervisor" name="editRowBean.supervisor" CHECKED />
							    		</c:when>
							    		<c:otherwise>
							    			<input type="checkbox" id="editRowBean.supervisor" name="editRowBean.supervisor" />
							    		</c:otherwise>
							    		</c:choose>
							    	</display:column>
							    	<display:column title="Payroll Processor" >
							    		<c:choose>
							    		<c:when test="${result.payrollprocessor == true}">
							    			<input type="checkbox" id="editRowBean.payrollprocessor" name="editRowBean.payrollprocessor" CHECKED />
							    		</c:when>
							    		<c:otherwise>
							    			<input type="checkbox" id="editRowBean.payrollprocessor" name="editRowBean.payrollprocessor" />
							    		</c:otherwise>
							    		</c:choose>
							    	</display:column>
							    	<display:column title="Active" >
							    		<c:choose>
							    		<c:when test="${result.active == true}">
							    			<input type="checkbox" id="editRowBean.active" name="editRowBean.active" CHECKED />
							    		</c:when>
							    		<c:otherwise>
							    			<input type="checkbox" id="editRowBean.active" name="editRowBean.active" />
							    		</c:otherwise>
							    		</c:choose>
							    	</display:column>	
							    	<display:column title="Actions">
							    		<input type="submit" value="Save"/>
							    		<a name="${result.deptid}-${result.sal_admin_plan}-${result.location}-${result.erncd}"></a>
							    		<a href="DeptEarncdMaintenance.do?method=editDeptEarncdRow&amp;earnCode=${fn:replace(DeptEarncdMaintenanceForm.earnCode,'%', '*')}">cancel</a>
							    	</display:column>
							 </c:when>      
							 <c:otherwise>       					                
						    	<display:column title="Department Id">${result.deptid}</display:column>
						    	<display:column title="Sal Admin Plan">${result.sal_admin_plan} </display:column>
						    	<display:column title="Location">${result.location} </display:column>
						    	<display:column title="Earn Code">${result.erncd} </display:column>
						    	<display:column title="Employee">${result.employee} </display:column>
						    	<display:column title="Supervisor">${result.supervisor} </display:column>
						    	<display:column title="Payroll Processor">${result.payrollprocessor} </display:column>
						    	<display:column title="Active">${result.active} </display:column>	
						    	<display:column title="Actions">
						    	<a href="DeptEarncdMaintenance.do?method=editDeptEarncdRow&amp;earnCode=${fn:replace(DeptEarncdMaintenanceForm.earnCode,'%', '*')}&amp;editRow=${deptErncdKey}#${deptErncdKey}">edit</a>
						    	</display:column>		
					    	</c:otherwise>
    						</c:choose>								                			                
					    </display:table>
					    </html:form>
				  	</c:if>
					<c:if test="${empty DeptEarncdMaintenanceForm.searchResults}">
						No rows returned.
					</c:if>
				   </div>    
	           </div>
           </c:if>
 
             <c:if test="${not empty DeptEarncdMaintenanceForm.sqlError}">
	  		   <div class="portlet">
		          <div class="header">
		             <h2>Oops!</h2>
		          </div>
				  <div class="portlet-content">
					${DeptEarncdMaintenanceForm.sqlError}
				  </div>    
	           </div>
           </c:if> 
</body>
</html>