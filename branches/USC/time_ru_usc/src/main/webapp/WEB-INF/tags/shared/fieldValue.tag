<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="field" required="true" type="edu.iu.uis.hr.client.EntityField"%>
<%@ attribute name="displayOnly" required="false"%>
<%@ attribute name="displayHelp" required="false"%>
<%@ attribute name="hidden" required="false"%>
<%@ attribute name="displayOnlyNotRestrictedByList" required="false"%>


<c:choose>
	<c:when test="${field.boolean}">
		<hr:boolean field="${field}" displayOnly="${displayOnly}" hidden="${hidden}"/>
	</c:when>
	<c:when test="${field.timestamp}">
		<hr:timestamp field="${field}" displayOnly="${displayOnly}" hidden="${hidden}"/>
	</c:when>	
	<c:when test="${field.time}">
		<hr:time field="${field}" displayOnly="${displayOnly}" hidden="${hidden}"/>
	</c:when>	
	<c:when test="${field.checkboxList}">
		<c:set var="fullName" value="${field.fullName}"/>
	    <c:set var="nestingPath" value="${fn:substring(fullName,0,fn:length(fullName)-1)}" />
		<hr:checkbox nestingPath="${nestingPath}" />
	</c:when>	
	<c:otherwise>
		<hr:text field="${field}" displayOnly="${displayOnly}" hidden="${hidden}" displayOnlyNotRestrictedByList="${displayOnlyNotRestrictedByList}"/>
	</c:otherwise>	
</c:choose>

<c:if test="${field.erroneous}">
	<img src="jsp/images/errormark.gif" class="errormark">
</c:if>

<c:if test="${field.warned && empty hidden}">
	<img src="jsp/images/warningmark.gif" class="warningmark">
</c:if>
		
