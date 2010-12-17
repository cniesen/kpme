<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<fieldset>
	<legend>Notes</legend>
	<html:textarea property="newNote" cols="50" rows="5"></html:textarea>
	<html:image property="methodToCall.addNote" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" alt="Add" title="Add" styleClass="globalbuttons"/>
	<logic:iterate id="notes" indexId="i" name="KualiForm" property="notes">
		<table>
			<tr>
				<td>
					<c:out value="${KualiForm.notes[i].formattedDate}"/>
				</td>
				<td>
					<c:out value="${KualiForm.notes[i].creator.name}"/>:
				</td>
				<td bordercolor="black">
					<c:out value="${KualiForm.notes[i].text}"></c:out>
				</td>
			</tr>
		</table>
	</logic:iterate>
	
</fieldset>