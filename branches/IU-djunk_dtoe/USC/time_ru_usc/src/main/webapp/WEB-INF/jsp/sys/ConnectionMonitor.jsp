<%@ page language="java" import="java.sql.Connection,org.apache.commons.dbcp.BasicDataSource,java.sql.DriverManager,javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.sql.DataSource;"%>

<html:html>
<head>

</head>
<body>
<%
InitialContext context = new InitialContext( );
String environment = edu.iu.uis.hr.Context.getEnvironment();
DataSource tkDataSource = (DataSource) context.lookup("java:comp/env/jdbc/" + environment + "/hr/TKDB");      
DataSource hrDataSource = (DataSource) context.lookup("java:comp/env/jdbc/" + environment + "/hr/HRSP");      
if (tkDataSource instanceof BasicDataSource) {
	out.println("<br>TK Datasource Connections");
	BasicDataSource basicDs = (BasicDataSource)tkDataSource;
	out.println("<br>Number of Idle connections:"+basicDs.getNumIdle());
	out.println("<br>Number of Active connections:"+basicDs.getNumActive());
	out.println("<br>Initial size:"+basicDs.getInitialSize());
	out.println("<br>Max Active:"+basicDs.getMaxActive());
	out.println("<br>Max Idle:"+basicDs.getMaxIdle());
}
if (hrDataSource instanceof BasicDataSource) {
	out.println("<br>HR Datasource Connections");
	BasicDataSource basicDs = (BasicDataSource)hrDataSource;
	out.println("<br>Number of Idle connections:"+basicDs.getNumIdle());
	out.println("<br>Number of Active connections:"+basicDs.getNumActive());
    out.println("<br>Initial size:"+basicDs.getInitialSize());
    out.println("<br>Max Active:"+basicDs.getMaxActive());
    out.println("<br>Max Idle:"+basicDs.getMaxIdle());
}
%>
</body>
</html:html>
 	
 	