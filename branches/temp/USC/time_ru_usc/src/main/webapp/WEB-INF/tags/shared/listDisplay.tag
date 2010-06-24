<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="columns" required="true"%>

<c:set var="listName" value="${nestingPath}s"/>
<c:set var="results" value="StrutsActionForm.${nestingPath}s" />

<c:if test='${fn:length(StrutsActionForm.jstlPropertyWrapper[listName]) > 0}'>
<tr>
  <td>
    ${StrutsActionForm.labels[listName]}
  </td>
</tr>
<tr>
  <td>
	<display:table name="${results}" id="result" >
		<c:forTokens items="${columns}" delims="," var="column">
			<display:column property="${column}" title="${StrutsActionForm.labels[column]}" decorator="${StrutsActionForm.columnDecoratorClassName}"/>
		</c:forTokens>
	</display:table>
  </td>
</tr>
</c:if>
    
