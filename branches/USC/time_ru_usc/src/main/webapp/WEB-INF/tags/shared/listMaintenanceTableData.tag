<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="columns" required="true"%>
<%@ attribute name="maintainableListName" required="true"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="recordIndex" required="true"%>
<%@ attribute name="hiddenFields" required="false"%>
<%@ attribute name="actions" required="false"%>
<%@ attribute name="displayOnlyNotRestrictedByList" required="false"%>
<%@ attribute name="trStatus" required="false"%>

<c:if test="${!empty columns}">
	<c:choose>
		<c:when test="${!empty trStatus}">
			<tr class="${trStatus}">
		</c:when>
		<c:otherwise>
			<tr>
		</c:otherwise>
	</c:choose>
		<c:forTokens items="${columns}" delims="," var="column">
			<td>
				<c:set var="fullName" value="${nestingPath}[${recordIndex}].${column}" />
				<c:set var="notRestrictByList" value="false" />
				<c:forTokens items="${displayOnlyNotRestrictedByList}" delims="," var="dispCol">
					<c:choose>	   
						<c:when test="${dispCol == column}">
							<c:set var="notRestrictByList" value="true" />
						</c:when>
					</c:choose>
				</c:forTokens>
				<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayOnly="${displayOnly}" displayOnlyNotRestrictedByList="${notRestrictByList}"/>
			</td>
		</c:forTokens>

   <c:if test="${empty actions}">
		<td>
           <div align="center">
              <fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
			  <c:set var="eventNames" value="${nestingPath}[${recordIndex}].mode.eventNames" />
			    <c:if test='${fn:length(StrutsActionForm.jstlPropertyWrapper[eventNames]) > 0}'>
				   <c:forTokens items="${StrutsActionForm.jstlPropertyWrapper[eventNames]}" delims="," var="event" varStatus="current">
					  <c:set var="fullUrl" value="${event}Url" />
					  <a href="${StrutsActionForm.jstlPropertyWrapper[fullUrl]}&index=${recordIndex}&maintenanceNestingPath=${nestingPath}')"><fmt:message key="${event}.image.html" bundle="${springMessages}" /></a>
					  <fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
				   </c:forTokens>
			     </c:if>
               <fmt:message key="clearpixel.image.html" bundle="${springMessages}" />
               <html:hidden name="StrutsActionForm" property="${nestingPath}[${recordIndex}].mode.editable" styleId="${nestingPath}[${recordIndex}].mode.editable" />
             </div>
		  </td>
    </c:if>
  <td>
</c:if>

<c:forTokens items="${hiddenFields}" delims="," var="hiddenField">
	<c:set var="fullName" value="${nestingPath}[${recordIndex}].${hiddenField}" />
	<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" hidden="true" />
</c:forTokens>

<c:if test="${!empty columns}">
    </td>
  </tr>
</c:if>
