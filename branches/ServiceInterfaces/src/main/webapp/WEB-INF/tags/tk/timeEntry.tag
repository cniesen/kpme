<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Time Entry Week 1</a></li>
		<li><a href="#tabs-2">Time Entry Week 2</a></li>
	</ul>
	<input type="hidden" value="${fn:length(KualiForm.timeCaptureRows)}" id="timeCaptureRowCount"/>
	<div id="tabs-1">
		<table width="100%">
			<thead>
				<tr>
					<th>
						<html:image property="methodToCall.addTimeCapture" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" alt="Add" title="Add" styleClass="globalbuttons"/>
					</th>
					<th>
						Account Name
					</th>
					<th>
						Time Type
					</th>
					<c:forTokens items="0,1,2,3,4,5,6" delims="," var="i">
						<th>
							<c:out value="${KualiForm.timeCaptureRows[0].dates[i].formattedDate}" escapeXml="false"/>
						</th>
					</c:forTokens>
					<th>
						Approver
					</th>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="timeCaptureRows" indexId="i" name="KualiForm" property="timeCaptureRows" >
					<tr>
						<td></td>
						<td align="center">
							<html:select property="timeCaptureRows[${i}].assignment" style="width: 150px;">
								<html:optionsCollection property="assignments" label="description" value="tkAssignmentId"/>
							</html:select>
						</td>
						<td align="center">
							<div>
								<html:select property="timeCaptureRows[${i}].timeType" styleClass="timeTypeSelect1${i}">
									<html:option value="JURY_DUTY">Jury Duty</html:option> 
									<html:option value="TIME_IN_OUT">Time In</html:option>
									<html:option value="HOURS">Hours Worked</html:option>
								</html:select>
							</div>
							<div class="timeOutClass1<c:out value="${i}"/>" style="display: none">
								<select disabled="disabled">
									<option>Time Out</option>
								</select> 
							</div>							
						</td>
						<c:forTokens items="0,1,2,3,4,5,6" delims="," var="j">
							<td align="center">
								<div>
									<html:text property="timeCaptureRows[${i}].dates[${j}].hours1" size="10" styleClass="ti"></html:text>
								</div>
								<div class="timeOutClass1<c:out value="${i}"/>" style="display: none">
								 	<html:text property="timeCaptureRows[${i}].dates[${j}].hours2" size="10" styleClass="ti"></html:text>
								</div>
							</td>
						</c:forTokens>
					</tr>
				</logic:iterate>
				<tr>
					<td><!-- Empty Add Column --></td>
					<td><!-- Empty Account Column --></td>
					<td>Total:</td>
					<td>
						<input type="text" disabled="disabled" id="totalField0" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField1" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField2" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField3" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField4" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField5" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField6" size="10"/>
					</td>
					<td><!-- Empty Approver Column --></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="tabs-2">
		<table width="100%">
			<thead>
				<tr>
					<th>
						<html:image property="methodToCall.addTimeCapture" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif" alt="Add" title="Add" styleClass="globalbuttons"/>
					</th>
					<th>
						Account Name
					</th>
					<th>
						Time Type
					</th>
					<c:forTokens items="7,8,9,10,11,12,13" delims="," var="i">
						<th>
							<c:out value="${KualiForm.timeCaptureRows[0].dates[i].formattedDate}" escapeXml="false"/>
						</th>
					</c:forTokens>
					<th>
						Approver
					</th>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="timeCaptureRows" indexId="i" name="KualiForm" property="timeCaptureRows" >
					<tr>
						<td></td>
						<td align="center">
							<html:select property="timeCaptureRows[${i}].assignment" style="width: 150px;">
								<html:optionsCollection property="assignments" label="description" value="tkAssignmentId"/>
							</html:select>
						</td>
						<td align="center">
							<div>
								<html:select property="timeCaptureRows[${i}].timeType" styleClass="timeTypeSelect2${i}">
									<html:option value="JURY_DUTY">Jury Duty</html:option> 
									<html:option value="TIME_IN_OUT">Time In</html:option>
									<html:option value="HOURS">Hours Worked</html:option>
								</html:select>
							</div>
							<div class="timeOutClass2<c:out value="${i}"/>" style="display: none">
								<select disabled="disabled">
									<option>Time Out</option>
								</select> 
							</div>							
						</td>
						<c:forTokens items="7,8,9,10,11,12,13" delims="," var="j">
							<td align="center">
								<div>
									<html:text property="timeCaptureRows[${i}].dates[${j}].hours1" size="10" styleClass="ti"></html:text>
								</div>
								<div class="timeOutClass2<c:out value="${i}"/>" style="display: none">
								 	<html:text property="timeCaptureRows[${i}].dates[${j}].hours2" size="10" styleClass="ti"></html:text>
								</div>
							</td>
						</c:forTokens>
					</tr>
				</logic:iterate>
				<tr>
					<td><!-- Empty Add Column --></td>
					<td><!-- Empty Account Column --></td>
					<td>Total:</td>
					<td>
						<input type="text" disabled="disabled" id="totalField7" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField8" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField9" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField10" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField11" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField12" size="10"/>
					</td>
					<td>
						<input type="text" disabled="disabled" id="totalField13" size="10"/>
					</td>
					<td><!-- Empty Approver Column --></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>