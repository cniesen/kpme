<html:form action="/Pool">
<input type="hidden" name="method" value="" />

<b>TK Thread Pool Data</b>
<p>TK DataSource</p>
<p>Minimum Connections: ${TKDataPoolForm.minConnections}</p>
<p>Maximum Connections: ${TKDataPoolForm.maxConnections}</p>
<p>Free Connections: ${TKDataPoolForm.freeConnections}</p>
<p>Used Connections: ${TKDataPoolForm.usedConnections}</p>
<p>Total Connections: ${TKDataPoolForm.poolSize}</p>



</html:form>