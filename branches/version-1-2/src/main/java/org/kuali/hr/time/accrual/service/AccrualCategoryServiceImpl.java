/**
 * Copyright 2004-2012 The Kuali Foundation
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

import org.apache.log4j.Logger;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.accrual.dao.AccrualCategoryDao;

import java.sql.Date;
import java.util.List;

public class AccrualCategoryServiceImpl implements AccrualCategoryService {

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(AccrualCategoryServiceImpl.class);
	private AccrualCategoryDao accrualCategoryDao;
	public AccrualCategoryServiceImpl() {
	}

	public AccrualCategory getAccrualCategory(String accrualCategory, Date asOfDate) {
		return accrualCategoryDao.getAccrualCategory(accrualCategory, asOfDate);
	}

	@Override
	public void saveOrUpdate(AccrualCategory accrualCategory) {
		accrualCategoryDao.saveOrUpdate(accrualCategory);
	}

	public AccrualCategoryDao getAccrualCategoryDao() {
		return accrualCategoryDao;
	}

	public void setAccrualCategoryDao(AccrualCategoryDao accrualCategoryDao) {
		this.accrualCategoryDao = accrualCategoryDao;
	}

	@Override
	public AccrualCategory getAccrualCategory(String lmAccrualCategoryId) {
		return accrualCategoryDao.getAccrualCategory(lmAccrualCategoryId);
	}

	@Override
	public List <AccrualCategory> getActiveAccrualCategories(Date asOfDate){
		return accrualCategoryDao.getActiveAccrualCategories(asOfDate);
	}

    @Override
    public List<AccrualCategory> getAccrualCategories(String accrualCategory, String accrualCatDescr, Date fromEffdt, Date toEffdt, String active, String showHistory) {
        return accrualCategoryDao.getAccrualCategories(accrualCategory, accrualCatDescr, fromEffdt, toEffdt, active, showHistory);
    }
}
