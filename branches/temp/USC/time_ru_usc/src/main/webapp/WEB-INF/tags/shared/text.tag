<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="field" required="true" type="edu.iu.uis.hr.client.EntityField"%>
<%@ attribute name="displayOnly" required="false"%>
<%@ attribute name="headerLabel" required="false"%>
<%@ attribute name="hidden" required="false"%>
<%@ attribute name="displayOnlyNotRestrictedByList" required="false"%>
<c:set var="foundOption" value="false" />
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
	<c:when test="${(displayOnly == 'true') || field.displayOnly}">
		<c:choose>
			<c:when test="${((displayOnly == 'true') || field.displayOnly) && (fn:length(StrutsActionForm.optionsMap[field.dropdownName]) < 1)}">
				<c:choose>
					<c:when test="${field.size > 100}">
						<html:textarea name="StrutsActionForm"  styleId="${field.fullName}" property="${field.fullName}" cols="40" rows="5" readonly="true" />
					</c:when>
					<c:otherwise>
						<span id='${field.fullName}'>
				 		   ${field.value}
				 		</span>
    			    </c:otherwise>
				</c:choose>
				<html:hidden name="StrutsActionForm" property="${field.fullName}"  styleId="${field.fullName}"/>
			</c:when>
			<c:otherwise>
				<c:forEach items="${StrutsActionForm.optionsMap[field.dropdownName]}" var="option">
					<c:if test="${field.value == option.value}">
						<c:set var="foundOption" value="true" />
						${option.label}
						<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" />
					</c:if>
				</c:forEach>
				<c:if test="${foundOption == 'false'}">
					<c:if test="${displayOnlyNotRestrictedByList == 'true'}">
					${field.value}
					<html:hidden name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" />
					</c:if>
				&nbsp;
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${fn:length(StrutsActionForm.optionsMap[field.dropdownName]) > 0}">
				<html:select name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" size="1" styleClass="thin" onchange="${field.javascriptEventHandler}">
					<c:forEach items="${StrutsActionForm.optionsMap[field.dropdownName]}" var="option">
						<c:if test="${!field.allOptionExcluded || (option.value != '@')}">
							<option value="${option.value}" 
								<c:if test="${field.value == option.value}">
									selected="true"
									<c:set var="foundOption" value="true" />
								</c:if>
							>
								${option.label}
							</option>
						</c:if>
					</c:forEach>
					<c:if test="${foundOption == 'false' &&  fn:length(StrutsActionForm.optionsMap[field.dropdownName]) > 1}"><option value="" selected="true">Select ${StrutsActionForm.labels[field.name]}</c:if>
				</html:select>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${field.size > 100}">
						<html:textarea name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" cols="40" rows="5" />
					</c:when>
					<c:otherwise>
						<html:text name="StrutsActionForm" property="${field.fullName}" styleId="${field.fullName}" size="${field.size + 1}" maxlength="${field.size}" onchange="${field.javascriptEventHandler}"/>
						<c:if test='${field.lookupName != null}'>
							<a href="${StrutsActionForm.lookupUrl}${field.lookupName}&fieldsToReturn=${field.lookupFieldsToReturn}&lookupField=${field.name}&nestingPath=${field.nestingPath}')"><img src="<fmt:message key="lookup.image.url" bundle="${springMessages}"/>"
								name="${field.nestingPath}_lookup"	alt="Lookup ${StrutsActionForm.labels[field.fullName]}" border=0></a>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>