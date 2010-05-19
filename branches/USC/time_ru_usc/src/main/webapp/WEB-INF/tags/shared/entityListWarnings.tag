<%@ include file="/jsp/tlds.jsp"%>
<%@ attribute name="maintainableList" required="true"%>
<%@ attribute name="nestingPath" required="true"%>

<div class="warning">
	<strong>Warnings Found in Section</strong>
	<ul>
		<c:forEach items="${StrutsActionForm.jstlPropertyWrapper[maintainableList]}" var="record" varStatus="current">
			<c:if test="${record.warned}">

				<li>
					Record ${current.index + 1} has warnings:
				</li>
				<ul style="circle">

					<c:set var="entityFullName" value="${nestingPath}[${current.index}]" />
					<c:if test="${StrutsActionForm.jstlPropertyWrapper[entityFullName].warned}">
						<hr:entityWarningMessages entity="${StrutsActionForm.jstlPropertyWrapper[entityFullName]}" />
					</c:if>
				</ul>
			</c:if>
		</c:forEach>
	</ul>
</div>

