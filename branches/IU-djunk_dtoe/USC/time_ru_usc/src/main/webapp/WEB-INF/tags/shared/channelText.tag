<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="field" required="true" type="edu.iu.uis.hr.client.EntityField"%>
<%@ attribute name="displayOnly" required="false"%>
<%@ attribute name="headerLabel" required="false"%>
<%@ attribute name="hidden" required="false"%>

<c:choose>
	<c:when test="${headerLabel}">
		<c:if test="${field.required}">
			<b>*</b>
		</c:if>
		<c:choose>
			<c:when test="${!StrutsActionForm.helpPage}">
				<a href="javascript:openHelpWindow('${StrutsActionForm.helpUrl}FLH&helpEntry.subject=${field.name}')" style="text-decoration:none">${StrutsActionForm.labels[field.name]}</a>
			</c:when>
			<c:otherwise>
				${StrutsActionForm.labels[field.name]}
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${(hidden == 'true')}">
		<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" />
	</c:when>
	<c:when test="${(displayOnly == 'true') || field.displayOnly || (fn:length(StrutsActionForm.optionsMap[field.dropdownName]) < 1)}">
		<c:choose>
			<c:when test="${(displayOnly == 'true') || field.displayOnly}">
				<c:choose>
					<c:when test="${field.size > 100}">
						<html:textarea name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" cols="40" rows="5" readonly="true" />
					</c:when>
					<c:otherwise>
						${field.value}
    			    </c:otherwise>
				</c:choose>
				<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" />
			</c:when>
			<c:otherwise>
				<c:forEach items="${StrutsActionForm.optionsMap[field.dropdownName]}" var="option">
					<c:if test="${field.value == option.value}">
						<c:set var="foundOption" value="true" />
						${option.label}
						<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" />
					</c:if>
				</c:forEach>
				<c:if test="${foundOption == 'false'}">&nbsp;</c:if>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${fn:length(StrutsActionForm.optionsMap[field.dropdownName]) > 0}">
				<html:select name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" size="1">
					<c:forEach items="${StrutsActionForm.optionsMap[field.dropdownName]}" var="option">
						<c:if test="${!field.allOptionExcluded || (option.value != '@')}">
							<option value="${option.value}" <c:if test="${field.value == option.value}">
							selected="true"
						</c:if>>
								${option.label}
							</option>
						</c:if>
					</c:forEach>
				</html:select>
			</c:when>
			<c:otherwise>
               None available.
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
