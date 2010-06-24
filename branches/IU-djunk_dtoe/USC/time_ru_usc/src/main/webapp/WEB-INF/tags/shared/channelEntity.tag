<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>

<div id="lookup" align="left">
	<table cellpadding="0" cellspacing="0" class="datatable-100">
		<tbody>
			<c:forTokens items="${columns}" delims="," var="column" varStatus="current">
			  <c:set var="fullName" value="${nestingPath}.${column}" />
		      <tr>
				<th>
				  <div align="left">
					${StrutsActionForm.labels[fullName]}:
			      </div>
				</th>
		      </tr>	      
		      <tr>
				<td>
					<hr:channelFieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayHelp="true" />
				</td>
        	  </tr>
			</c:forTokens>
		</tbody>
	</table>
</div>
<div align="left">
	<c:set var="eventNames" value="${nestingPath}.mode.eventNames" />
	<c:if test='${fn:length(StrutsActionForm.jstlPropertyWrapper[eventNames]) > 0}'>
	        <br />
			<c:forTokens items="${StrutsActionForm.jstlPropertyWrapper[eventNames]}" delims="," var="event" varStatus="current">
			  <c:set var="fullUrl" value="${event}Url" />
			  <a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
			  <fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
			</c:forTokens>
	</c:if>
	<html:hidden name="StrutsActionForm" property="${nestingPath}.mode.editable" styleId="${nestingPath}.mode.editable"/>
	<hr:hidden nestingPath="${nestingPath}" hiddenFields="${hiddenFields}" />
</div>