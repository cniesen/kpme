<%@ include file="/jsp/tlds.jsp"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="label" required="true"%>
<%@ attribute name="inputName" required="true" %>
<%@ attribute name="methodName" required="false"%>


		<tr>
			<td style="background-color: #F0F0F0" valign="top" width="50%">
       <table border="0">
        <tr>
       		<td style="background-color:#F0F0F0" nowrap>
	       		<div class="portlet">
	              <div class="header">
	                  <h2><%= title %></h2>
	              </div>
	              <div class="portlet-content">
	            	  <%=label %>: <input type="text" name="<%=inputName %>">
	       			  <input type="submit" name="method" value="<%=methodName %>">
	              </div>
        	</td>
        </tr>
        </table>
        </td>
        </tr>
        