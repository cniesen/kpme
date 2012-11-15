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
package org.kuali.hr.time.earncode.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.earncodesec.EarnCodeSecurity;
import org.kuali.hr.earncodesec.EarnCodeType;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.earncode.dao.EarnCodeDao;
import org.kuali.hr.time.roles.TkUserRoles;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.util.*;

public class EarnCodeServiceImpl implements EarnCodeService {

	private EarnCodeDao earnCodeDao;

	public void setEarnCodeDao(EarnCodeDao earnCodeDao) {
		this.earnCodeDao = earnCodeDao;
	}


    @Override
    public List<EarnCode> getEarnCodes(Assignment a, Date asOfDate) {
        return getEarnCodesForTime(a, asOfDate);
    }

    @Override
    public List<EarnCode> getEarnCodes(Assignment a, Date asOfDate, String earnTypeCode) {
        List<EarnCode> earnCodes = new LinkedList<EarnCode>();

        // Note: https://jira.kuali.org/browse/KPME-689
        // We are grabbing a TkUser from the current thread local context here.
        // really, this should probably be passed in..

        TKUser user = TKContext.getUser();
        if (user == null) {
            // TODO: Determine how to fail if there is no TkUser
            throw new RuntimeException("No User on context.");
        }

        if (a == null)
            throw new RuntimeException("Can not get earn codes for null assignment");
        Job job = a.getJob();
        if (job == null || job.getPayTypeObj() == null)
            throw new RuntimeException("Null job/job paytype on assignment!");

        EarnCode regularEc = getEarnCode(job.getPayTypeObj().getRegEarnCode(), asOfDate);
        if (regularEc == null)
            throw new RuntimeException("No regular earn code defined.");
        earnCodes.add(regularEc);
        List<EarnCodeSecurity> decs = TkServiceLocator.getEarnCodeSecurityService().getEarnCodeSecurities(job.getDept(), job.getHrSalGroup(), job.getLocation(), asOfDate);
        for (EarnCodeSecurity dec : decs) {
            if (StringUtils.isBlank(earnTypeCode)
                    || earnTypeCode.equals(dec.getEarnCodeType())
                    ) {

                boolean addEarnCode = false;
                // Check employee flag
                if (dec.isEmployee() &&
                        (StringUtils.equals(TKUser.getCurrentTargetPerson().getEmployeeId(), GlobalVariables.getUserSession().getPerson().getEmployeeId()))) {
                    addEarnCode = true;
                }
                // Check approver flag
                if (!addEarnCode && dec.isApprover()) {
                    Set<Long> workAreas = TkUserRoles.getUserRoles(GlobalVariables.getUserSession().getPrincipalId()).getApproverWorkAreas();
                    for (Long wa : workAreas) {
                        WorkArea workArea = TkServiceLocator.getWorkAreaService().getWorkArea(wa, asOfDate);
                        if (workArea!= null && a.getWorkArea().compareTo(workArea.getWorkArea())==0) {
                            addEarnCode = true;
                            break;
                        }
                    }
                }
                if (addEarnCode) {
                    EarnCode ec = getEarnCode(dec.getEarnCode(), asOfDate);
                    if(ec!=null){
                        earnCodes.add(ec);
                    }
                }
            }
        }

        return earnCodes;
    }

    public EarnCode getEarnCode(String earnCode, Date asOfDate) {
        EarnCode ec = null;

        ec = earnCodeDao.getEarnCode(earnCode, asOfDate);

        return ec;
    }

    @Override
    public String getEarnCodeType(String earnCode, Date asOfDate) {
        EarnCode earnCodeObj = getEarnCode(earnCode, asOfDate);
        return earnCodeObj.getEarnCodeType();
    }

	@Override
	public EarnCode getEarnCodeById(String earnCodeId) {
		return earnCodeDao.getEarnCodeById(earnCodeId);
	}
	
	public List<EarnCode> getOvertimeEarnCodes(Date asOfDate){
		return earnCodeDao.getOvertimeEarnCodes(asOfDate);
	}
	
	public List<String> getOvertimeEarnCodesStrs(Date asOfDate){
		List<String> ovtEarnCodeStrs = new ArrayList<String>();
		List<EarnCode> ovtEarnCodes = getOvertimeEarnCodes(asOfDate);
		if(ovtEarnCodes != null){
			for(EarnCode ovtEc : ovtEarnCodes){
				ovtEarnCodeStrs.add(ovtEc.getEarnCode());
			}
		}
		return ovtEarnCodeStrs;
	}
	
	@Override
	public int getEarnCodeCount(String earnCode) {
		return earnCodeDao.getEarnCodeCount(earnCode);
	}
	
	@Override
	public int getNewerEarnCodeCount(String earnCode, Date effdt) {
		return earnCodeDao.getNewerEarnCodeCount(earnCode, effdt);
	}

    @Override
    public List<EarnCode> getEarnCodes(String earnCode, String ovtEarnCode, String descr, Date fromEffdt, Date toEffdt, String active, String showHist) {
        return earnCodeDao.getEarnCodes(earnCode, ovtEarnCode, descr, fromEffdt, toEffdt, active, showHist);
    }


    public List<EarnCode> getEarnCodesForTime(Assignment a, Date asOfDate) {
        // code moved over from earncodeservice in kpme 1.5
        if (a == null) throw new RuntimeException("No assignment parameter.");
        Job job = a.getJob();
        if (job == null || job.getPayTypeObj() == null) throw new RuntimeException("Null job or null job pay type on assignment.");

        List<EarnCode> earnCodes = new LinkedList<EarnCode>();
        String earnTypeCode = EarnCodeType.TIME.getCode();

        boolean isClockUser = a.getTimeCollectionRule().isClockUserFl();
        boolean isUsersTimesheet = StringUtils.equals(TKContext.getPrincipalId(),a.getPrincipalId());

        // Reg earn codes will typically not be defined in the earn code security table
        EarnCode regularEarnCode = getEarnCode(job.getPayTypeObj().getRegEarnCode(), asOfDate);
        if (regularEarnCode == null) {
            throw new RuntimeException("No regular earn code defined for job pay type.");
        } else {
            //  if you are a clock user and this is your timesheet and you are processing the reg earn code, do not add this earn code. Use the clock in/out mechanism.
            if (isClockUser && isUsersTimesheet) {
                // do not add reg earn code. use clock.
            } else {
                earnCodes.add(regularEarnCode);
            }
        }
        List<EarnCodeSecurity> decs = TkServiceLocator.getEarnCodeSecurityService().getEarnCodeSecurities(job.getDept(), job.getHrSalGroup(), job.getLocation(), asOfDate);
        for (EarnCodeSecurity dec : decs) {
            if (earnTypeCode.equals(dec.getEarnCodeType())
                    || EarnCodeType.BOTH.getCode().equals(dec.getEarnCodeType())) {

                boolean addEarnCode = addEarnCodeBasedOnEmployeeApproverSettings(dec, a, asOfDate);
                //  this earn code could possibly be for reg earnings. reg was processed before the earn code security table of earn codes, so it should be skipped here.
                if ( StringUtils.equals(job.getPayTypeObj().getRegEarnCode(),dec.getEarnCode()) ){
                    addEarnCode = false;
                }

                if (addEarnCode) {
                    EarnCode ec = getEarnCode(dec.getEarnCode(), asOfDate);
                    if(ec!=null){
                        earnCodes.add(ec);
                    }
                }
            }
        }

        return earnCodes;
        //        if (a == null) throw new RuntimeException("No assignment parameter.");
//        Job job = a.getJob();
//        if (job == null || job.getPayTypeObj() == null) throw new RuntimeException("Null job or null job pay type on assignment.");
//
//        List<EarnCode> earnCodes = new LinkedList<EarnCode>();
//
//        EarnCode regularEarnCode = getEarnCode(job.getPayTypeObj().getRegEarnCode(), asOfDate);
//        if (regularEarnCode == null) {
//            throw new RuntimeException("No regular earn code defined for job pay type.");
//        } else {
//            earnCodes.add(regularEarnCode);
//        }
//        List<EarnCodeSecurity> decs = TkServiceLocator.getEarnCodeSecurityService().getEarnCodeSecurities(job.getDept(), job.getHrSalGroup(), job.getLocation(), asOfDate);
//        for (EarnCodeSecurity dec : decs) {
//                boolean addEarnCode = false;
//                // Check employee flag
//                if (dec.isEmployee() &&
//                        (StringUtils.equals(TKUser.getCurrentTargetPerson().getEmployeeId(), GlobalVariables.getUserSession().getPerson().getEmployeeId()))) {
//                    addEarnCode = true;
//                }
//                // Check approver flag
//                if (!addEarnCode && dec.isApprover()) {
//                    Set<Long> workAreas = TkUserRoles.getUserRoles(GlobalVariables.getUserSession().getPrincipalId()).getApproverWorkAreas();
//                    for (Long wa : workAreas) {
//                        WorkArea workArea = TkServiceLocator.getWorkAreaService().getWorkArea(wa, asOfDate);
//                        if (workArea!= null && a.getWorkArea().compareTo(workArea.getWorkArea())==0) {
//                            addEarnCode = true;
//                            break;
//                        }
//                    }
//                }
//                if (addEarnCode) {
//                    EarnCode ec = getEarnCode(dec.getEarnCode(), asOfDate);
//                    if(ec!=null){
//                        earnCodes.add(ec);
//                    }
//                }
//        }
        // return earnCodes;
    }

    private boolean addEarnCodeBasedOnEmployeeApproverSettings(EarnCodeSecurity security, Assignment a, Date asOfDate) {
        boolean addEarnCode = false;
        if (security.isEmployee() &&
                (StringUtils.equals(TKUser.getCurrentTargetPerson().getEmployeeId(), GlobalVariables.getUserSession().getPerson().getEmployeeId()))) {
            addEarnCode = true;
        }
        // Check approver flag
        if (!addEarnCode && security.isApprover()) {
            Set<Long> workAreas = TkUserRoles.getUserRoles(GlobalVariables.getUserSession().getPrincipalId()).getApproverWorkAreas();
            for (Long wa : workAreas) {
                WorkArea workArea = TkServiceLocator.getWorkAreaService().getWorkArea(wa, asOfDate);
                if (workArea!= null && a.getWorkArea().compareTo(workArea.getWorkArea())==0) {
                    addEarnCode = true;
                    break;
                }
            }
        }
        return addEarnCode;
    }

    /* not using yet, may not be needed
    @Override
    public Map<String, String> getEarnCod e s ForDisplayWithAssignment(Assignment assignment, Date asOfDate) {
        List<EarnCode> earnCodes = this.getEarnCod e s ( assignment, asOfDate);

        Date currentDate = TKUtils.getCurrentDate();
        boolean futureDate = asOfDate.after(currentDate);
        List<EarnCode> copyList = new ArrayList<EarnCode>();
        copyList.addAll(earnCodes);
        for (EarnCode earnCode : copyList) {
            if ( futureDate
                    && !earnCode.getAllowScheduledLeave().equalsIgnoreCase("Y")) {
                earnCodes.remove(earnCode);
            }
        }
        Comparator<EarnCode> earnCodeComparator = new Comparator<EarnCode>() {
            @Override
            public int compare(EarnCode ec1, EarnCode ec2) {
                return ec1.getEarnCode().compareToIgnoreCase(ec2.getEarnCode());
            }
        };
        // Order by leaveCode ascending
        Ordering<EarnCode> ordering = Ordering.from(earnCodeComparator);

        Map<String, String> earnCodesForDisplay = new LinkedHashMap<String, String>();
        for (EarnCode earnCode : ordering.sortedCopy(earnCodes)) {
            earnCodesForDisplay.put(earnCode.getEarnCodeKeyForDisplay(), earnCode.getEarnCodeValueForDisplay());
        }
        return earnCodesForDisplay;
    }

    */
}
