<%@ page language="java" import="java.sql.Connection,org.apache.commons.dbcp.BasicDataSource,java.sql.DriverManager,javax.naming.Context,javax.naming.InitialContext,javax.naming.NamingException,javax.sql.DataSource;"%>

<html:html>
<head>

</head>
<body>
<%



InitialContext context = new InitialContext( );
DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/stg/hr/TKDB");      

if (ds instanceof BasicDataSource) {
            	BasicDataSource basicDs = (BasicDataSource)ds;
            	out.println("<br>Number of Idle connections:"+basicDs.getNumIdle());
            	out.println("<br>Number of Active connections:"+basicDs.getNumActive());
                out.println("<br>Initial size:"+basicDs.getInitialSize());
                out.println("<br>Max Active:"+basicDs.getMaxActive());
                out.println("<br>Max Idle:"+basicDs.getMaxIdle());
          
            	}
  %>
</body>
</html:html>
 	
 	