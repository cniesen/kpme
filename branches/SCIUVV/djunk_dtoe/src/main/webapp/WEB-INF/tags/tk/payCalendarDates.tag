<%@ include file="/WEB-INF/jsp/TkTldHeader.jsp"%>

<c:set var="pcdAttributes" value="${DataDictionary.PayCalendarDates.attributes}" />

<kul:tab tabTitle="Dates" defaultOpen="true" tabErrorKey="document.payCalendar.payCalendarDates.*">
	<div class="tab-container" align="center">
        
<!--  Inner section for actual pay calendar dates entry -->
   <table cellpadding="0" cellspacing="0" summary="">
     	<tr>
    		<th><div align="left">&nbsp;</div></th> 
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${pcdAttributes.beginPeriodDate}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${pcdAttributes.beginPeriodTime}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${pcdAttributes.endPeriodDate}" noColon="true" /></div></th>
    		<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${pcdAttributes.endPeriodTime}" noColon="true" /></div></th>
           	<c:if test="${not inquiry}">	
              	<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	</c:if>
       	</tr>     
        <c:if test="${not inquiry and not readOnly}">	          	
          	<tr>
				<th class="infoline">
					<c:out value="Add:" />
				</th>				
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newPayCalendarDate.beginPeriodDate" attributeEntry="${pcdAttributes.beginPeriodDate}" datePicker="true" readOnly="${readOnly}"/>
					</div>
				</td>
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newPayCalendarDate.beginPeriodTime" attributeEntry="${pcdAttributes.beginPeriodTime}" readOnly="${readOnly}"/>
					</div>
				</td>
                <td align="left" valign="middle" class="infoline" >
                	<div align="center">
	                	<kul:htmlControlAttribute property="newPayCalendarDate.endPeriodDate" attributeEntry="${pcdAttributes.endPeriodDate}" datePicker="true" readOnly="${readOnly}"/>
					</div>
				</td>
	            <td align="left" valign="middle">
	                <div align="center">
	                	<kul:htmlControlAttribute property="newPayCalendarDate.endPeriodTime"  attributeEntry="${pcdAttributes.endPeriodTime}"  readOnly="${readOnly}"/>
					</div>
				</td>
                <td class="infoline">
					<div align=center>
						<html:image property="methodToCall.addPayCalendarDate.anchor${tabKey}"
							src='${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
					</div>
                </td>				
			</tr>
        	<c:forEach var="pcd" items="${KualiForm.document.payCalendar.payCalendarDates}" varStatus="status">
       		<tr>
        		
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="pcd.beginPeriodDate"  attributeEntry="${pcdAttributes.beginPeriodDate}" readOnly="true"  />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="pcd.beginPeriodTime"  attributeEntry="${pcdAttributes.beginPeriodTime}" readOnly="true"  />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="pcd.endPeriodDate"  attributeEntry="${pcdAttributes.endPeriodDate}" readOnly="true"  />
					</div>
				</td>
                <td align="left" valign="middle">
                	<div align="center"> <kul:htmlControlAttribute property="pcd.endPeriodTime"  attributeEntry="${pcdAttributes.endPeriodTime}" readOnly="true"/>
					</div>
           		<c:if test="${not inquiry}">						
					<td>
						<div align=center>&nbsp;
		        	     <c:choose>
		        	       <c:when test="${group.edit  or readOnly}">
		        	          <img class='nobord' src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete2.gif' styleClass='tinybutton'/>
		        	       </c:when>
		        	       <c:otherwise>
		        	          <html:image property='methodToCall.deletePayCalendarDate.line${status.index}.anchor${currentTabIndex}'
									src='${ConfigProperties.kr.externalizable.images.url}tinybutton-delete1.gif' styleClass='tinybutton'/>
		        	       </c:otherwise>
		        	     </c:choose>  
						</div>
	                </td>
	            </c:if>    
        		        		
       		</tr>
        	</c:forEach>			
		</c:if>
	</table>
	
	</div>
</kul:tab>
