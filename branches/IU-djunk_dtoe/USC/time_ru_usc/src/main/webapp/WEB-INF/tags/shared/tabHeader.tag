<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="maintainableListName" required="true"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="erroneous" required="true"%>
<%@ attribute name="tabHeader" required="false"%>
<%@ attribute name="tabStatus" required="false"%>
<%@ attribute name="suppressShowHide" required="false"%>

<c:choose>
	<c:when test="${empty tabHeader}">
		<c:set var="tabLeftClass" value="tabtable1-left"/>
		<c:set var="tabLeftImage" value="jsp/images/tab-topleft.gif"/>
		<c:set var="tabMidClass" value="tabtable1-mid"/>
		<c:set var="tabRightClass" value="tabtable1-right"/>
		<c:set var="tabRightImage" value="jsp/images/tab-topright.gif"/>
	</c:when>
	<c:otherwise>
		<c:set var="tabLeftClass" value="tabtable1-left"/>
		<c:set var="tabLeftImage" value="jsp/images/tab-topleft1.gif"/>
		<c:set var="tabMidClass" value="tabtable2-mid"/>
		<c:set var="tabRightClass" value="tabtable2-right"/>
		<c:set var="tabRightImage" value="jsp/images/tab-topright1.gif"/>
	</c:otherwise>
</c:choose>

<c:set var="showHideImage" value="jsp/images/tinybutton-hide.gif"/>
<c:if test="${!empty tabStatus && tabStatus=='collapsed'}">
	<c:set var="showHideImage" value="jsp/images/tinybutton-show.gif"/>
</c:if>


<table cellspacing="0" class="tab">
	<tr>
		<td class="${tabLeftClass}">
			<img src="${tabLeftImage}" alt="" width="12" height="29" align="absmiddle" />
			${maintainableListName}
			<c:if test="${erroneous}"><img src="jsp/images/errormark.gif" class="errormark"></c:if>
			<c:if test="${warned}"><img src="jsp/images/warningmark.gif" class="warningmark"></c:if>
		</td>
		<td class="${tabMidClass}">
		<c:choose>
			<c:when test="${id == '0' || !empty suppressShowHide}">
				&nbsp;
			</c:when>
			<c:otherwise>
				<a id="A${id}" onClick="rend(this, false)"><img src="${showHideImage}" alt="show/hide tab section" class="tinybutton" width="45" height="15" border="0" id="F${id}"></a>
			</c:otherwise>
		</c:choose>
		</td>
		<td class="${tabRightClass}">
			<img src="${tabRightImage}" alt="" width="12" height="29" align="absmiddle" />
		</td>
	</tr>
</table>
