<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="field" required="true" type="edu.iu.uis.hr.client.EntityField"%>
<%@ attribute name="displayOnly" required="false"%>
<%@ attribute name="hidden" required="false"%>

<c:set var="fullName" value="${field.fullName}" />
<c:set var="dateName" value="${fullName}.date" />
<c:set var="hourName" value="${fullName}.hour" />
<c:set var="minuteName" value="${fullName}.minute" />
<c:set var="secondName" value="${fullName}.second" />
<c:set var="amPmName" value="${fullName}.amPm" />
<c:set var="hourValue" value="${StrutsActionForm.jstlPropertyWrapper[StrutsActionForm.jstlPropertyWrapper[hourName].fullName].value}" />
<span class="nowrap">
<c:choose>
	<c:when test="${field.displayOnly}">
		<c:if test="${!hidden}">
			${StrutsActionForm.jstlPropertyWrapper[dateName].value}
			${StrutsActionForm.jstlPropertyWrapper[hourName].value}:${StrutsActionForm.jstlPropertyWrapper[minuteName].value}
			${StrutsActionForm.jstlPropertyWrapper[amPmName].value}
			<c:set var="hidden" value="${true}" />
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${!hidden}">
			<html:text name="StrutsActionForm" property="${dateName}" styleId="${dateName}" size="9" maxlength="10" />
			<html:text name="StrutsActionForm" property="${hourName}" styleId="${hourName}" size="1" maxlength="2" />:
		    <html:text name="StrutsActionForm" property="${minuteName}" styleId="${minuteName}" size="1" maxlength="2" />
			<hr:text field="${StrutsActionForm.jstlPropertyWrapper[secondName]}" displayOnly="${displayOnly}" hidden="true" />
			<html:select name="StrutsActionForm" property="${amPmName}" styleId="${amPmName}" size="1" styleClass="thin">
				<c:forEach items="${StrutsActionForm.optionsMap[StrutsActionForm.jstlPropertyWrapper[amPmName].dropdownName]}" var="option">
					<option value="${option.value}" <c:if test="${StrutsActionForm.jstlPropertyWrapper[amPmName].value == option.value}">selected="true"</c:if>>
					${option.label}
                    </option>
				</c:forEach>
			</html:select>
		</c:if>
	</c:otherwise>
</c:choose> 
</span>
<c:if test="${hidden}">
	<html:hidden name="StrutsActionForm" property="${dateName}" styleId="${dateName}" />
	<html:hidden name="StrutsActionForm" property="${hourName}" styleId="${hourName}" />
	<html:hidden name="StrutsActionForm" property="${minuteName}" styleId="${minuteName}" />
	<html:hidden name="StrutsActionForm" property="${secondName}" styleId="${secondName}" />
	<html:hidden name="StrutsActionForm" property="${amPmName}" styleId="${amPmName}" />
</c:if>
