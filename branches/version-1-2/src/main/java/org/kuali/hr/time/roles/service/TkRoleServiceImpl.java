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
package org.kuali.hr.time.roles.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.roles.dao.TkRoleDao;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKUser;
import org.kuali.hr.time.util.TkConstants;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TkRoleServiceImpl implements TkRoleService {

    private static final Logger LOG = Logger.getLogger(TkRoleServiceImpl.class);

	private TkRoleDao tkRoleDao;

	@Override
	public List<TkRole> getDepartmentRoles(String department) {
		List<TkRole> departmentRoles = new ArrayList<TkRole>();
		departmentRoles.addAll(tkRoleDao.findRoles(null, null, TkConstants.ROLE_TK_DEPT_ADMIN, null, department, null));
		departmentRoles.addAll(tkRoleDao.findRoles(null, null, TkConstants.ROLE_TK_DEPT_VO, null, department, null));
		departmentRoles.addAll(tkRoleDao.findRoles(null, null, TkConstants.ROLE_LV_DEPT_ADMIN, null, department, null));
		departmentRoles.addAll(tkRoleDao.findRoles(null, null, TkConstants.ROLE_LV_DEPT_VO, null, department, null));
		return departmentRoles;
	}

	@Override
	public List<TkRole> getDepartmentRoles(String department, String roleName, Date asOfDate) {
		return tkRoleDao.findRoles(null, asOfDate, roleName, null, department, null);
	}
	
	@Override
	public List<TkRole> getDepartmentInactiveRoles(String department) {
		List<TkRole> departmentRoles = new ArrayList<TkRole>();
		departmentRoles.addAll(tkRoleDao.findInActiveRoles(null, null, TkConstants.ROLE_TK_DEPT_ADMIN, null, department, null));
		departmentRoles.addAll(tkRoleDao.findInActiveRoles(null, null, TkConstants.ROLE_TK_DEPT_VO, null, department, null));
		departmentRoles.addAll(tkRoleDao.findInActiveRoles(null, null, TkConstants.ROLE_LV_DEPT_ADMIN, null, department, null));
		departmentRoles.addAll(tkRoleDao.findInActiveRoles(null, null, TkConstants.ROLE_LV_DEPT_VO, null, department, null));
		return departmentRoles;
	}
	
	@Override
	public List<TkRole> getDepartmentInactiveRoles(String department, String roleName, Date asOfDate) {
		return tkRoleDao.findInActiveRoles(null, asOfDate, roleName, null, department, null);
	}

	@Override
	public List<TkRole> getWorkAreaRoles(Long workArea) {
		return tkRoleDao.findRoles(null, null, null, workArea, null, null);
	}

	@Override
	public List<TkRole> getWorkAreaRoles(Long workArea, String roleName, Date asOfDate) {
		return tkRoleDao.findRoles(null, asOfDate, roleName, workArea, null, null);
	}
	
	@Override
	public List<TkRole> getInActiveWorkAreaRoles(Long workArea) {
		return tkRoleDao.findInActiveRoles(null, null, null, workArea, null, null);
	}

	@Override
	public List<TkRole> getInActiveWorkAreaRoles(Long workArea, String roleName, Date asOfDate) {
		return tkRoleDao.findInActiveRoles(null, asOfDate, roleName, workArea, null, null);
	}

	public void setTkRoleDao(TkRoleDao tkRoleDao) {
		this.tkRoleDao = tkRoleDao;
	}

	@Override
	public void saveOrUpdate(List<TkRole> roles) {
		this.tkRoleDao.saveOrUpdateRoles(roles);
	}

	@Override
	public void saveOrUpdate(TkRole role) {
		this.tkRoleDao.saveOrUpdateRole(role);
	}

	/**
	 * Returns all active roles for the given principal as of the indi
	 */
	public List<TkRole> getRoles(String principalId, Date asOfDate) {
		return tkRoleDao.findRoles(principalId, asOfDate, null, null, null, null);
	}

	/**
	 * Returns all active roles for the given principal as of the indi
	 */
	public List<TkRole> getInactiveRoles(String principalId, Date asOfDate) {
		return tkRoleDao.findInActiveRoles(principalId, asOfDate, null, null, null, null);
	}
	
	/**
	 * Return a List of TkRoles that match the principal ID and roleName.
	 *
	 * ex:
	 *
	 * admin,TK_APPROVER will return all TK_APPROVER roles for the user admin.
	 */
	public List<TkRole> getRoles(String principalId, String roleName, Date asOfDate) {
		return this.tkRoleDao.findRoles(principalId, asOfDate, roleName, null, null, null);
	}

    /**
     * Return a List of TkRoles that matches criteria.
     * @param principalId
     * @param asOfDate
     * @param roleName
     * @param workArea
     * @param department
     * @return
     */
    @Override
    public List<TkRole> getRoles(String principalId, Date asOfDate, String roleName, Long workArea, String department) {
        return this.tkRoleDao.findRoles(principalId, asOfDate, roleName, workArea, department, null);
    }

    //TODO: this won't work at all.  We can't use TKUser here, as that just grabs stuff from the session
    // we need a wrapper class for TKUser, though I'm not sure why we can't just return Person, or Entity...
	public List<TKUser> getEmployeesForWorkArea(Long workArea, Date asOfDate){
		List<TKUser> lstEmployees = new ArrayList<TKUser>();
		List<Assignment> lstActiveAssignments = TkServiceLocator.getAssignmentService().getActiveAssignmentsForWorkArea(workArea, asOfDate);

        for(Assignment assign: lstActiveAssignments){
			TKUser tkUser = TKUser.getUser(assign.getPrincipal(), assign.getEffectiveDate());
			lstEmployees.add(tkUser);
		}
		return lstEmployees;
	}

    @Override
    public List<String> getResponsibleParties(Assignment a, String roleName, Date asOfDate) {
        List<String> users = new ArrayList<String>();

        List<TkRole> roles = this.getWorkAreaRoles(a.getWorkArea(), roleName, asOfDate);
        for (TkRole role: roles) {
        	if(StringUtils.isNotBlank(role.getPrincipalId())){
        		users.add(role.getPrincipalId());
        	} else if(StringUtils.isNotBlank(role.getPositionNumber())){
        		List<Job> lstJobs = TkServiceLocator.getJobService().getActiveJobsForPosition(role.getPositionNumber(), asOfDate);
        		for(Job job : lstJobs){
        			users.add(job.getPrincipalId());
        		}
        	
        	}
        }

        return users;
    }

    @Override
    public Set<Long> getWorkAreasForApprover(String principalId, Date asOfDate) {
        Set<Long> workAreas = new HashSet<Long>();

        List<TkRole> roles = this.getRoles(principalId, TkConstants.ROLE_TK_APPROVER, asOfDate);
        for (TkRole role : roles) {
            Long wa = role.getWorkArea();
            if (wa != null)
                workAreas.add(wa);
            else
                LOG.warn(TkConstants.ROLE_TK_APPROVER + " found without WorkArea number, ignoring roleId: " + role.getHrRolesId());
        }

        return workAreas;
    }



    @Override
    public Set<String> getActivePrincipalsForWorkAreas(Set<Long> workAreas, Date asOfDate) {
        Set<String> principals = new HashSet<String>();

        for (Long workArea : workAreas) {
            List<Assignment> assignments = TkServiceLocator.getAssignmentService().getActiveAssignmentsForWorkArea(workArea, asOfDate);
            for (Assignment assignment : assignments) {
                principals.add(assignment.getPrincipalId());
            }
        }

        return principals;
    }

	@Override
	public TkRole getRole(String tkRoleId) {
		return tkRoleDao.getRole(tkRoleId);
	}
	
	@Override
	public TkRole getRolesByPosition(String positionNumber) {
		return tkRoleDao.getRolesByPosition(positionNumber);
	}
	
	@Override
	public TkRole getInactiveRolesByPosition(String positionNumber) {
		return tkRoleDao.getInactiveRolesByPosition(positionNumber);
	}

	@Override
	public List<TkRole> getPositionRolesForWorkArea(Long workArea, Date asOfDate) {
		return tkRoleDao.getPositionRolesForWorkArea(workArea, asOfDate);
	}
}
