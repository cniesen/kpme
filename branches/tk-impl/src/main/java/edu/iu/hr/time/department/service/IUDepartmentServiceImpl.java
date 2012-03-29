package edu.iu.hr.time.department.service;

import java.util.List;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.department.service.DepartmentServiceImpl;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TkConstants;

import edu.iu.hr.time.util.IUTkConstants;

public class IUDepartmentServiceImpl extends DepartmentServiceImpl  {
	

    @Override
    public void populateDepartmentRoles(Department department) {
        if (department != null) {
        	List<TkRole> deptAdminRoles = TkServiceLocator.getTkRoleService().getDepartmentRoles(
                    department.getDept(),
                    TkConstants.ROLE_TK_DEPT_ADMIN,
                    department.getEffectiveDate()); 
        	List<TkRole> deptViewOnlyRoles = TkServiceLocator.getTkRoleService().getDepartmentRoles(department.getDept(),
                    TkConstants.ROLE_TK_DEPT_VO,
                    department.getEffectiveDate());
        	
        	List<TkRole> deptPayrollProcessorRoles = TkServiceLocator.getTkRoleService().getDepartmentRoles(department.getDept(),
                    IUTkConstants.ROLE_TK_PY_PROCESSOR,
                    department.getEffectiveDate());
        	
        	department.getRoles().addAll(deptAdminRoles);
        	department.getRoles().addAll(deptViewOnlyRoles);
        	department.getRoles().addAll(deptPayrollProcessorRoles);
        }
    }
}
