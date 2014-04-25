/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.pm.position.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.core.lookup.KPMELookupableImpl;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.kpme.pm.api.position.PositionContract;
import org.kuali.kpme.pm.api.positiondepartment.PositionDepartmentContract;
import org.kuali.kpme.pm.service.base.PmServiceLocator;
import org.kuali.rice.krad.uif.view.LookupView;
import org.kuali.rice.krad.web.form.LookupForm;

public class PositionLookupableImpl extends KPMELookupableImpl {

	private static final long serialVersionUID = 8658536323175048980L;

	@Override
    protected List<?> getSearchResults(LookupForm form, Map<String, String> searchCriteria, boolean unbounded) {
    	String positionNum = searchCriteria.get("positionNumber");
        String description = searchCriteria.get("description");
        String location = searchCriteria.get("location");
        String institution = searchCriteria.get("institution");
        String primaryDepartment = searchCriteria.get("primaryDepartment");  // KPME-3189
        String classificationTitle = searchCriteria.get("classificationTitle");
        String positionType = searchCriteria.get("positionType");
        String poolEligible = searchCriteria.get("poolEligible");
        String positionStatus = searchCriteria.get("positionStatus");
        String fromEffdt = TKUtils.getFromDateString(searchCriteria.get("effectiveDate"));
        String toEffdt = TKUtils.getToDateString(searchCriteria.get("effectiveDate"));
        String active = searchCriteria.get("active");
        String showHist = searchCriteria.get("history");

        if (StringUtils.equals(positionNum, "%")) {
            positionNum = "";
        }
        
        List<? extends PositionContract> posContrasts = PmServiceLocator.getPositionService().getPositions(positionNum, description, null, classificationTitle, positionType, poolEligible, positionStatus, TKUtils.formatDateString(fromEffdt),
                        TKUtils.formatDateString(toEffdt), active, showHist);
        
        if (StringUtils.isEmpty(primaryDepartment)) {
        	return posContrasts;
        }
        
        List<PositionContract> tempContracts = new ArrayList<PositionContract>();
        for (PositionContract posContract: posContrasts) {
        	List<? extends PositionDepartmentContract> posDepartments = posContract.getDepartmentList();
        	for (PositionDepartmentContract posDepartment: posDepartments) {
        		if (posDepartment.getDeptAfflObj().isPrimaryIndicator()) {
        			if (StringUtils.contains(primaryDepartment, "*") || StringUtils.contains(primaryDepartment, "%")) {
	        			String tempPrimaryDepartment = StringUtils.remove(primaryDepartment, "*");
	        			tempPrimaryDepartment = StringUtils.remove(tempPrimaryDepartment, "%");
	        			if (posDepartment.getDepartment().toUpperCase().contains(tempPrimaryDepartment.toUpperCase())) {
	        				tempContracts.add(posContract);
	        			}
        			} else {
        				if (posDepartment.getDepartment().toUpperCase().equals(primaryDepartment.toUpperCase())) {
	        				tempContracts.add(posContract);
	        			}
        			}
        		}
        	}
        }
        
        return tempContracts;
    }  
	
	/*@Override
    protected String getActionUrlHref(LookupForm lookupForm, Object dataObject, String methodToCall, List<String> pkNames) {
		if (!StringUtils.equals(methodToCall, "maintenanceEdit")) {
			return super.getActionUrlHref(lookupForm, dataObject, methodToCall, pkNames);
		} else {
			Properties urlParameters = new Properties();

	        urlParameters.setProperty(UifParameters.DATA_OBJECT_CLASS_NAME, dataObject.getClass().getName());
	        urlParameters.setProperty(UifParameters.METHOD_TO_CALL, UifConstants.MethodToCallNames.START);
	        
	        Map<String, String> primaryKeyValues = KRADUtils.getPropertyKeyValuesFromDataObject(pkNames, dataObject);
	        for (String primaryKey : primaryKeyValues.keySet()) {
	            String primaryKeyValue = primaryKeyValues.get(primaryKey);

	            urlParameters.put(primaryKey, primaryKeyValue);
	        }
	       	        
	        String path = ConfigContext.getCurrentContextConfig().getProperty("application.url");
	        String hrefPrefix = path + "/portal.do?channelTitle=ProcessMaint&channelUrl=" + path + "/ProcessMaint.do";
//	        "http://localhost:8080/kpme-dev/kpme/positionMaintenance?hrPositionId=26&category=reorg&reason=null&viewTypeName=MAINTENANCE&returnLocation=http://localhost:8080/kpme-dev/portal.do&methodToCall=maintenanceEdit&dataObjectClassName=org.kuali.kpme.pm.position.Position"
	        Position aPosition = (Position) dataObject;
	        String paramString = "?positionId=" + aPosition.getHrPositionId();
	        return (hrefPrefix + paramString);
		}
    }*/

    @Override
    public void initSuppressAction(LookupForm lookupForm) {
    /*
     * lookupAuthorizer.canInitiateDocument(lookupForm, user) returns false in this instance, because no
     * documentTypeName can be obtained within LookupViewAuthorizeBase.canInitiateDocument(LookupForm, Person).
     */
        ((LookupView) lookupForm.getView()).setSuppressActions(false);
    }
}