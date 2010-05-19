<%@ include file="/jsp/tlds.jsp" %>
<%@ attribute name="nestingPath" required="true"%>
<%@ attribute name="field" required="false" type="edu.iu.uis.hr.client.EntityField"%>

	<c:set var="listName" value="${nestingPath}s"/>
	
	<logic:iterate name="StrutsActionForm" property="${listName}" id="record" indexId="recordIndex">
		     <c:set var="checkbox" value="${nestingPath}[${recordIndex}]"/>
		     <c:set var="labelFullName" value="${checkbox}.labelFieldName"/>
		     <c:set var="fieldFullName" value="${checkbox}.fieldName"/>	     
		     <c:set var="fullName" value="${checkbox}.${StrutsActionForm.jstlPropertyWrapper[fieldFullName].value}"/>           
             <hr:boolean field="${StrutsActionForm.jstlPropertyWrapper[fullName]}"  />
		     <c:set var="fullName" value="${checkbox}.${StrutsActionForm.jstlPropertyWrapper[labelFullName].value}"/>
             <hr:text field="${StrutsActionForm.jstlPropertyWrapper[fullName]}" displayOnly="true" />
		     <c:set var="fullName" value="${checkbox}.hiddenFields"/>
		     <c:set var="hiddenFields" value="${StrutsActionForm.jstlPropertyWrapper[fullName].value}"/>
			 <c:forTokens items="${hiddenFields}" delims="," var="hiddenField">
				<html:hidden name="StrutsActionForm" property="${nestingPath}[${recordIndex}].${hiddenField}" styleId="${nestingPath}[${recordIndex}].${hiddenField}"/>
			 </c:forTokens>		
            <br>
	</logic:iterate>