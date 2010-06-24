package edu.iu.uis.hr.tk.department.entity.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.department.service.DepartmentService;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;

public class ValidRoleUserNetworkId extends AbstractLogic implements OperationalLogic {

//    private DepartmentService departmentService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        List roleUsers = new ArrayList();
        // TODO - moved to dept role
        List viewOnlyRoleUsers = new ArrayList();
        //List kioskAdmins = new ArrayList();
        if (entity instanceof WorkArea) {
            roleUsers = ((WorkArea)entity).getRoleUsers();
        } else if (entity instanceof Department) {
            roleUsers = ((Department)entity).getRoleUsers();
            // TODO - moved to dept role
            viewOnlyRoleUsers = ((Department)entity).getViewOnlyRoleUsers();
            //kioskAdmins = ((Department)entity).getKioskManagers();
        } else {
            throw new IllegalArgumentException("ValidRoleUserNetworkId's execute(Entity entity) method requires a non-null Entity of type WorkArea or type Department");
        }

        Iterator roleUsersIterator = roleUsers.iterator();
        while (roleUsersIterator.hasNext()) {
            RoleUser roleUser = (RoleUser)roleUsersIterator.next();
            if (setRoleUsersNetworkId(roleUser)) {
              setRoleUsersName(roleUser);
            }
        }
        // TODO - moved to dept role
        Iterator viewOnlyRoleUsersIterator = viewOnlyRoleUsers.iterator();
        while (viewOnlyRoleUsersIterator.hasNext()) {
            RoleUser roleUser = (RoleUser)viewOnlyRoleUsersIterator.next();
            if (setRoleUsersNetworkId(roleUser)) {
              setRoleUsersName(roleUser);
            }
        }
        // TODO - implement this check
        /*Iterator kioskAdminIterator = kioskAdmins.iterator();
        while (kioskAdminIterator.hasNext()) {
            RoleUser roleUser = (RoleUser)kioskAdminIterator.next();
            if (setRoleUsersNetworkId(roleUser)) {
              setRoleUsersName(roleUser);
            }
        }*/
        
    }

    private boolean setRoleUsersNetworkId(RoleUser roleUser) {
        if (Utility.hasValue(roleUser.getUniversityId()) || !Utility.hasValue(roleUser.getNetworkId())) {
            String networkId = getDepartmentService().getRoleUsersNetworkId(roleUser);
            if (Utility.hasValue(networkId)) {
                roleUser.setNetworkId(networkId);
            } else {
                roleUser.getEntityErrors().add(new String[] { FieldNames.UNIVERSITY_ID }, getMessage(MessageKeyConstants.ERROR_CANNOT_LOCATE_ROLE_USER_IN_DIRECTORY, new String[] { roleUser.getUniversityId() }));
                return false;
            }
        }
        return true;
    }

    private void setRoleUsersName(RoleUser roleUser) {
        if (Utility.hasValue(roleUser.getUniversityId())) {
            String name = getDepartmentService().getRoleUsersName(roleUser);
            if (Utility.hasValue(name)) {
                roleUser.setName(name);
            } else {
                roleUser.getEntityErrors().add(new String[] { FieldNames.UNIVERSITY_ID }, getMessage(MessageKeyConstants.ERROR_CANNOT_LOCATE_ROLE_USER_IN_DIRECTORY, new String[] { roleUser.getUniversityId() }));
            }
        }
    }

//    public void setDepartmentService(DepartmentService departmentService) {
//        if (departmentService != null) {
//            this.departmentService = departmentService;
//        }
//    }

    private DepartmentService getDepartmentService() {
        return (DepartmentService)Context.getService(DepartmentService.class);
    }
}
