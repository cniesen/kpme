<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="maintainableList" required="true"%>
<%@ attribute name="nestingPath" required="true"%>

<div class="error">
	<strong>Errors Found in Section</strong>
	<ul>
		<c:forEach items="${StrutsActionForm.jstlPropertyWrapper[maintainableList]}" var="record" varStatus="current">
			<c:if test="${record.erroneous}">

				<li>
					Record ${current.index + 1} has errors:
				</li>
				<ul style="circle">

					<c:set var="entityFullName" value="${nestingPath}[${current.index}]" />
					<c:if test="${StrutsActionForm.jstlPropertyWrapper[entityFullName].erroneous}">
						<hr:entityErrorMessages entity="${StrutsActionForm.jstlPropertyWrapper[entityFullName]}" />
					</c:if>
				</ul>
			</c:if>
		</c:forEach>
	</ul>
</div>

