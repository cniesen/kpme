<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="recordIndex" required="false"%>
<%@ attribute name="style" required="false"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="subheadDisplayField" required="false"%>
<%@ attribute name="suppressShowHide" required="false"%>
<%@ attribute name="suppressSubHead" required="false"%>


<c:if test="${!empty subheadDisplayField && subheadDisplayField != 'suppress'}">
<tr>
	<td width="5%" class="tab-subhead">
		<c:choose>
			<c:when test="${empty suppressShowHide}">
				<a id="A${id}" onclick="toggleTab(this, true, '${nestingPath}', '${recordIndex}')"><img src="jsp/images/tinybutton-hide.gif" alt="show/hide tab section" width=45 height=15 border=0 align="absmiddle" id="F${id}"></a>
			</c:when>
			<c:otherwise>
				&nbsp;
			</c:otherwise>
		</c:choose>
	</td>
	<td width="95%" class="tab-subhead">
		<c:choose>
			<c:when test="${empty subheadDisplayField}">
				${StrutsActionForm.labels[nestingPath]}: Record ${recordIndex + 1}
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${empty recordIndex}">
						${subheadDisplayField}
					</c:when>
					<c:otherwise>
						<c:set var="fullName" value="${nestingPath}[${recordIndex}].${subheadDisplayField}" />
						<hr:fieldValue field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayOnly="true" />
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>

	</td>
</tr>
</c:if>

<c:choose>
  <c:when test="${!empty subheadDisplayField && subheadDisplayField != 'suppress'}">
     <tbody id="G${id}">
  </c:when>
  <c:otherwise>
     <tbody>
  </c:otherwise>
</c:choose>


	<c:set var="tableBodyId" value="${id + 1}" scope="request" />
	<tr>
		<td class="slim" colspan="2" width="100%" valign="top">
			<jsp:doBody />
		</td>
	</tr>
</tbody>
<c:if test="${!empty recordIndex}">
  <script language="javascript">
    try{
       if (eval('document.forms[0]["${nestingPath}[${recordIndex}].tabStatus"].value')=="true") {       
          expandTab(${id});
       }
       else {
            collapseTab(${id});
       }
     }catch(e){}
   </script>
</c:if>
