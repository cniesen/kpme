<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/rice-portal/jsp/sys/riceTldHeader.jsp"%>

<c:set var="systemAdmin" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isSystemAdmin()%>' />
<c:set var="globalViewOnly" value='<%=org.kuali.hr.time.roles.TkUserRoles.getUserRoles(org.kuali.rice.krad.util.GlobalVariables.getUserSession().getPrincipalId()).isGlobalViewOnly()%>' />

<c:if test="${systemAdmin || globalViewOnly}">
    <channel:portalChannelTop channelTitle="Administrative" />
    <div class="body">
        <ul class="chan">
            <li>
                <portal:portalLink displayTitle="true" title="Account"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.Account&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
            </li>
            <li>
                <portal:portalLink displayTitle="true" title="Chart"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.Chart&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
            </li>
            <li>
                <portal:portalLink displayTitle="true" title="Department"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.hr.time.department.Department&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
    		</li>
            <li>
                <portal:portalLink displayTitle="true" title="Location"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.hr.location.Location&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
            </li>
    		<li>
                <portal:portalLink displayTitle="true" title="Object Code"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.ObjectCode&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
    		</li>
    		<li>
                <portal:portalLink displayTitle="true" title="Organization"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.Organization&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
    		</li>
    		<li>
                <portal:portalLink displayTitle="true" title="Project Code"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.ProjectCode&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
    		</li>
    		<li>
                <portal:portalLink displayTitle="true" title="Sub Account"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.SubAccount&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
    		</li>
    		<li>
                <portal:portalLink displayTitle="true" title="Sub Object Code"
                                   url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kfs.coa.businessobject.SubObjectCode&returnLocation=${ConfigProperties.application.url}/portal.do&showMaintenanceLinks=true&hideReturnLink=true&docFormKey=88888888&active=Y" />
    		</li>
    	</ul>
    </div>
    <channel:portalChannelBottom />
</c:if>