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
package org.kuali.kpme.tklm.leave.donation.web;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.kpme.core.lookup.KPMELookupableHelperServiceImpl;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.kpme.tklm.leave.donation.LeaveDonation;
import org.kuali.kpme.tklm.leave.service.LmServiceLocator;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

@SuppressWarnings("deprecation")
public class LeaveDonationLookupableHelperServiceImpl extends KPMELookupableHelperServiceImpl {

	private static final long serialVersionUID = 4181583515349590532L;

	@Override
	@SuppressWarnings("rawtypes")
	public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
		List<HtmlData> customActionUrls = super.getCustomActionUrls(businessObject, pkNames);

		LeaveDonation leaveDonation = (LeaveDonation) businessObject;
		String lmLeaveDonationId = leaveDonation.getLmLeaveDonationId();
		
		Properties params = new Properties();
		params.put(KRADConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE, getBusinessObjectClass().getName());
		params.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.MAINTENANCE_NEW_METHOD_TO_CALL);
		params.put("lmLeaveDonationId", lmLeaveDonationId);
		AnchorHtmlData viewUrl = new AnchorHtmlData(UrlFactory.parameterizeUrl(KRADConstants.INQUIRY_ACTION, params), "view");
		viewUrl.setDisplayText("view");
		viewUrl.setTarget(AnchorHtmlData.TARGET_BLANK);
		customActionUrls.add(viewUrl);
		
		return customActionUrls;
	}
	
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
    	String fromEffdt = TKUtils.getFromDateString(fieldValues.get("effectiveDate"));
        String toEffdt = TKUtils.getToDateString(fieldValues.get("effectiveDate"));
    	String donorsPrincipalId = fieldValues.get("donorsPrincipalID");
        String donatedAccrualCategory = fieldValues.get("donatedAccrualCategory");
        String amountDonated = fieldValues.get("amountDonated");
        String recipientsPrincipalId = fieldValues.get("recipientsPrincipalID");
        String recipientsAccrualCategory = fieldValues.get("recipientsAccrualCategory");
        String amountReceived = fieldValues.get("amountReceived");
        String active = fieldValues.get("active");
        String showHist = fieldValues.get("history");
        
        return LmServiceLocator.getLeaveDonationService().getLeaveDonations(TKUtils.formatDateString(fromEffdt), TKUtils.formatDateString(toEffdt), 
        		donorsPrincipalId, donatedAccrualCategory, amountDonated, recipientsPrincipalId, recipientsAccrualCategory, amountReceived, active, showHist);
    }

}
