package edu.iu.uis.hr.tk.department.entity.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;

public class MustHaveOneMemberInDepartmentWorkAreaRolesLogic extends AbstractLogic implements OperationalLogic {
    private static final String WORK_AREA = "Work Area";
    private static final String DEPARTMENT = "Department";
    private static final String SUPERVISOR = "Time Approver";
    private static final String PAYROLL_PROCESSOR = "Payroll Processor";

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        List roleUsers = new ArrayList();
        if (entity instanceof WorkArea) {
            roleUsers = ((WorkArea) entity).getRoleUsers();
            WorkArea workArea = (WorkArea) entity;
            boolean supervisorRoleExist = false;
            boolean payrollProcessorRoleExist = false;
            if (Utility.hasValue(roleUsers)) {
                for (Iterator iter = roleUsers.iterator(); iter.hasNext();) {
                    RoleUser roleUser = (RoleUser) iter.next();
                    if (supervisorRoleExist && payrollProcessorRoleExist) {
                        break;
                    } else if (roleUser.getRoleName().equals(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE)) {
                        supervisorRoleExist = true;
                    } else {
                        payrollProcessorRoleExist = true;
                    }
                }
                if (!supervisorRoleExist) {
                    workArea.getEntityErrors().add(new String[] { FieldNames.ROLE }, getMessage(MessageKeyConstants.ERROR_ROLES_REQUIRED, new String[] { SUPERVISOR }));
                }
                if (!payrollProcessorRoleExist) {
                    workArea.getEntityErrors().add(new String[] { FieldNames.ROLE }, getMessage(MessageKeyConstants.ERROR_ROLES_REQUIRED, new String[] { PAYROLL_PROCESSOR }));
                }

            } else {
                workArea.getEntityErrors().add(new String[] { FieldNames.ROLE }, getMessage(MessageKeyConstants.ERROR_ROLES_REQUIRED, new String[] { WORK_AREA }));
            }
        } else if (entity instanceof Department) {
            roleUsers = ((Department) entity).getRoleUsers();
            Department department = ((Department) entity);
            if (!Utility.hasValue(roleUsers)) {
                department.getEntityErrors().add(new String[] { FieldNames.ROLE }, getMessage(MessageKeyConstants.ERROR_ROLES_REQUIRED, new String[] { DEPARTMENT }));
            }
        } else {
            throw new IllegalArgumentException("MustHaveOneMemberInDepartmentWorkAreaRolesLogic's execute(Entity entity) method requires a non-null Entity of type WorkArea or type Department");
        }
    }

}