/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.hr.time.accrual.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.HrEffectiveDateActiveLookupableHelper;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Used to override lookup functionality for the accrual category lookup
 * 
 * 
 */
public class AccrualCategoryLookupableHelper extends
		HrEffectiveDateActiveLookupableHelper {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
		List<HtmlData> customActionUrls = super.getCustomActionUrls(
				businessObject, pkNames);
			List<HtmlData> overrideUrls = new ArrayList<HtmlData>();
			for(HtmlData actionUrl : customActionUrls){
				if(!StringUtils.equals(actionUrl.getMethodToCall(), "copy")){
					overrideUrls.add(actionUrl);
				}
			}

		if (TKContext.getUser().isSystemAdmin() || TKContext.getUser().isGlobalViewOnly()) {
			AccrualCategory accrualCategory = (AccrualCategory) businessObject;
			final String className = this.getBusinessObjectClass().getName();
			final String lmAccrualCategoryId = accrualCategory
					.getLmAccrualCategoryId();
			HtmlData htmlData = new HtmlData() {

				@Override
				public String constructCompleteHtmlTag() {
					return "<a target=\"_blank\" href=\"inquiry.do?businessObjectClassName="
							+ className
							+ "&methodToCall=start&lmAccrualCategoryId="
							+ lmAccrualCategoryId + "\">view</a>";
				}
			};
			overrideUrls.add(htmlData);
		} else if (overrideUrls.size() != 0) {
			overrideUrls.remove(0);
		}
		return overrideUrls;
	}

    @SuppressWarnings({"unchecked"})
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {

        String accrualCategory = fieldValues.get("accrualCategory");
        String accrualCatDescr = fieldValues.get("descr");
        String fromEffdt = fieldValues.get("rangeLowerBoundKeyPrefix_effectiveDate");
        String toEffdt = TKUtils.getToDateString(fieldValues.get("effectiveDate"));
        String active = fieldValues.get("active");
        String showHist = fieldValues.get("history");

        if (StringUtils.equals(accrualCategory, "%")) {
            accrualCategory = "";
        }

        List<AccrualCategory> accrualCategories = TkServiceLocator.getAccrualCategoryService().getAccrualCategories(accrualCategory, accrualCatDescr,
                TKUtils.formatDateString(fromEffdt), TKUtils.formatDateString(toEffdt), active, showHist);

        return accrualCategories;
    }

}
