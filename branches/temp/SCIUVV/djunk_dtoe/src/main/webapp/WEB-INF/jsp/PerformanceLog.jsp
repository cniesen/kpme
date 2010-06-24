<%@ page language="java" import="java.util.*" %>
<%@ page import="edu.iu.uis.hr.tk.log.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
 <head>
	<style>
	table {
	 font-size: 95%;
	 font-family: 'Lucida Grande', Helvetica, verdana, sans-serif;
	 background-color:#fff;
	 border-collapse: collapse;
	 width: 100%;
	 line-height: 1.2em;
	}
	caption {
	 font-size: 30px;
	 font-weight: bold;
	 color: #002084;
	 text-align: left;
	 padding: 10px 0px;
	 margin-bottom: 2px;
	 text-transform: capitalize;
	}
	thead th {
	 border-right: 2px solid #fff;
	 color:#fff;
	 text-align:center;
	 padding:2px;
	 height:25px;
	 background-color: #004080;
	}
	tfoot {
	 color:#002084;
	 padding:2px;
	 text-transform:uppercase;
	 font-size:1.2em; 
	 font-weight: bold;
	 margin-top:6px;
	 border-top: 6px solid #004080;
	 border-bottom: 6px solid #004080;
	}
	tbody tr {
	 background-color:#fff;
	 border-bottom: 2px solid #c0c0c0;
	}
	tbody td {
	 color:#002084;
	 padding:5px;
	 text-align:left;
	}
	tbody th {
	 text-align:left;
	 padding: 2px;
	}
	tbody td a, tbody th a {
	 color:#002084;
	 text-decoration:underline;
	 font-weight:normal; 
	}
	tbody td a:hover, tbody th a:hover {
	 text-decoration:none;
	}
	</style>
 </head>
 <body>
 <table>
 <tbody>
 <tr>
 <td>Class</td>
 <td>Method</td>
 <td>Time Taken</td>
 <td>Date Run</td>
 </tr>
 <%	  
	  	PerformanceInfoManager infoManager = new PerformanceInfoManager();
 
	  	for (Iterator iter = infoManager.getPerformanceList().iterator(); iter.hasNext();) {
	  		PerformanceInfo performanceInfo = (PerformanceInfo) iter.next();
			out.println("<tr><td>"+performanceInfo.getClazz()+"</td>"
						+ "<td>"+performanceInfo.getMethod()+"</td>"
						+ "<td>"+performanceInfo.getTimeTaken()+"</td>"
						+ "<td>"+performanceInfo.getTimeDateOccured()+"</td>"
						+ "</tr>");

	  	}
%>
</tbody>
 </table>
 </body>
</html>