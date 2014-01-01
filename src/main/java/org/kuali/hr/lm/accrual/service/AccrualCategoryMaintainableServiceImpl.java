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
package org.kuali.hr.lm.accrual.service;

import org.kuali.hr.core.cache.CacheUtils;
import org.kuali.hr.lm.accrual.AccrualCategory;
import org.kuali.hr.lm.accrual.AccrualCategoryRule;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Override the Maintenance page behavior for Leave Accrual Category object
 * 
 * 
 */
public class AccrualCategoryMaintainableServiceImpl extends HrBusinessObjectMaintainableImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
	protected void setNewCollectionLineDefaultValues(String arg0,
			PersistableBusinessObject arg1) {
    	if(arg1 instanceof AccrualCategoryRule){
    		AccrualCategoryRule leaveAccrualCategoryRule = (AccrualCategoryRule)arg1;
    		AccrualCategory leaveAccrualCategory = (AccrualCategory) this.getBusinessObject();
    		leaveAccrualCategoryRule.setActive(leaveAccrualCategory.isActive());
    	}
		super.setNewCollectionLineDefaultValues(arg0, arg1);
	}

	@Override
	public void customSaveLogic(HrBusinessObject hrObj) {
		AccrualCategory leaveAccrualCategory = (AccrualCategory)hrObj;
		for (AccrualCategoryRule accCatRule : leaveAccrualCategory.getAccrualCategoryRules()) {
			if(!isOldBusinessObjectInDocument()){ //prevents duplicate object on edit
				accCatRule.setLmAccrualCategoryId(null);
			}
			accCatRule.setLmAccrualCategoryId(leaveAccrualCategory.getLmAccrualCategoryId());
			accCatRule.setLmAccrualCategoryRuleId(null);
		}

        //CacheUtils.flushCache(AccrualCategory.CACHE_NAME);
	}

	@Override
	public HrBusinessObject getObjectById(String id) {
		return TkServiceLocator.getAccrualCategoryService().getAccrualCategory(id);
	}

}
