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
    @Cacheable(value= EarnCode.CACHE_NAME, key="'a=' + #p0.getTkAssignmentId() + '|' + 'asOfDate=' + #p1")
    public List<EarnCode> getEarnCodes(Assignment a, Date asOfDate);

    /**
     * Fetch a list of earn codes for a particular assignment and earnTypeCode
     * @param a
     * @param asOfDate
     * @return
     */
    @Cacheable(value= EarnCode.CACHE_NAME, key="'a=' + #p0.getTkAssignmentId() + '|' + 'asOfDate=' + #p1 + '|' + 'earnTypeCode=' + #p2")
    public List<EarnCode> getEarnCodes(Assignment a, Date asOfDate, String earnTypeCode);

    /**
     * Fetch an EarnCode as of a particular date
     * @param earnCode
     * @param asOfDate
     * @return
     */
    @Cacheable(value= EarnCode.CACHE_NAME, key="'earnCode=' + #p0 + '|' + 'asOfDate=' + #p1")
	public EarnCode getEarnCode(String earnCode, Date asOfDate);

    /**
     * Fetch the earn code type for a particular date
     * @param earnCode
     * @param asOfDate
     * @return
     */
    @Cacheable(value= EarnCode.CACHE_NAME, key="'{getEarnCodeType}' + 'earnCode=' + #p0 + '|' + 'asOfDate=' + #p1")
    String getEarnCodeType(String earnCode, Date asOfDate);
    
    /**
     * Fetch earn code by id
     * @param earnCodeId
     * @return
     */
    @Cacheable(value= EarnCode.CACHE_NAME, key="'earnCodeId=' + #p0")
    public EarnCode getEarnCodeById(String earnCodeId);
    
    /**
     * Fetch list of system defined overtime earn codes
     * @param asOfDate
     * @return
     */
    @Cacheable(value= EarnCode.CACHE_NAME, key="'{getOvertimeEarnCodes}' + 'asOfDate=' + #p0")
    public List<EarnCode> getOvertimeEarnCodes(Date asOfDate);


    /**
     * Fetch list of system defined overtime earn codes as strings
     * @param asOfDate
     * @return
     */
    @Cacheable(value= EarnCode.CACHE_NAME, key="'{getOvertimeEarnCodesStrs}' + 'asOfDate=' + #p0")
    public List<String> getOvertimeEarnCodesStrs(Date asOfDate);

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

}
