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
package org.kuali.hr.time.earncode.service;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.earncode.EarnCode;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface EarnCodeService {
    /**
     * Fetch a list of earn codes for a particular assignment
     * @param a
     * @param asOfDate
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'a=' + #p0.getTkAssignmentId() + '|' + 'asOfDate=' + #p1")
    public List<EarnCode> getEarnCodes(Assignment a, Date asOfDate);

    /**
     * Fetch a list of earn codes for a particular assignment and earnTypeCode
     * @param a
     * @param asOfDate
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'a=' + #p0.getTkAssignmentId() + '|' + 'asOfDate=' + #p1 + '|' + 'earnTypeCode=' + #p2")
    public List<EarnCode> getEarnCodes(Assignment a, Date asOfDate, String earnTypeCode);

    /**
     * Fetch an EarnCode as of a particular date
     * @param earnCode
     * @param asOfDate
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'earnCode=' + #p0 + '|' + 'asOfDate=' + #p1")
	public EarnCode getEarnCode(String earnCode, Date asOfDate);

    /**
     * Fetch the earn code type for a particular date
     * @param earnCode
     * @param asOfDate
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'{getEarnCodeType}' + 'earnCode=' + #p0 + '|' + 'asOfDate=' + #p1")
    String getEarnCodeType(String earnCode, Date asOfDate);
    
    /**
     * Fetch earn code by id
     * @param earnCodeId
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'earnCodeId=' + #p0")
    public EarnCode getEarnCodeById(String earnCodeId);
    
    /**
     * Fetch list of system defined overtime earn codes
     * @param asOfDate
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'{getOvertimeEarnCodes}' + 'asOfDate=' + #p0")
    public List<EarnCode> getOvertimeEarnCodes(Date asOfDate);


    /**
     * Fetch list of system defined overtime earn codes as strings
     * @param asOfDate
     * @return
     */
    @Cacheable(value=EarnCode.CACHE_NAME, key="'{getOvertimeEarnCodesStrs}' + 'asOfDate=' + #p0")
    public List<String> getOvertimeEarnCodesStrs(Date asOfDate);

    //make caching by who is looking at this as it is based on that what shows up
    @Cacheable(value= EarnCode.CACHE_NAME, key="'a=' + #p0.getTkAssignmentId() + '|' + 'asOfDate=' + #p1")
    public List<EarnCode> getEarnCodesForTime(Assignment a, Date asOfDate);

    /**
	 * get count of earn code with give earnCode
	 * @param earnCode
	 * @return int
	 */
    public int getEarnCodeCount(String earnCode);

    /**
	 * get count of newer version of earn code with give earnCode and date
	 * @param earnCode
	 * @param effdt
	 * @return int
	 */
    public int getNewerEarnCodeCount(String earnCode, Date effdt);

    List<EarnCode> getEarnCodes(String earnCode, String ovtEarnCode, String descr, Date fromEffdt, Date toEffdt, String active, String showHist);
}
