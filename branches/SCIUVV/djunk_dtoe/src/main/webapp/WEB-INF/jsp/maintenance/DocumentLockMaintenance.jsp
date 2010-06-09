<%@ page language="java"%>
<%@ include file="/jsp/tlds.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/shared" prefix="hr"%>

<hr:page>
	<c:choose>
	    <c:when test="${StrutsActionForm.jstlPropertyWrapper['fieldsToReturn']=='-1'}">
            <br><br><br><div align="center"> The document lock has been removed successfully </div>
        </c:when>
		<c:when	test="${empty (StrutsActionForm.jstlPropertyWrapper['documentLocks'][0].documentId)}">
             <br><br><br><div align="center"> Please click on the "Save" button below to complete removing the document lock </div>
        </c:when>
		<c:otherwise>
			<hr:listMaintenance nestingPath="documentLock" columns="documentId"
				tabFooter="true" />
		</c:otherwise>
	</c:choose>
</hr:page>

