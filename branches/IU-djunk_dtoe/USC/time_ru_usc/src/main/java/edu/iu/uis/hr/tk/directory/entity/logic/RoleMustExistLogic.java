package edu.iu.uis.hr.tk.directory.entity.logic;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.directory.entity.UserRole;
import edu.iu.uis.hr.tk.directory.entity.UserRoles;
import edu.iu.uis.hr.tk.directory.service.RolesService;

public class RoleMustExistLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(RoleMustExistLogic.class);

//    private RolesService rolesService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof UserRoles)) {
            throw new IllegalArgumentException("RoleMustExistLogic's execute(Entity entity) method requires a non-null Entity of type UserRoles");
        }
        checkExistence(((UserRoles)entity).getDepartmentRoles(), FieldNames.DEPARTMENT);
        checkExistence(((UserRoles)entity).getWorkAreaRoles(), FieldNames.WORK_AREA);
    }

    private void checkExistence(List roles, String field) {
        Iterator rolesItr = roles.iterator();
        while (rolesItr.hasNext()) {
            UserRole role = (UserRole)rolesItr.next();
            if (getRolesService().isRequiredRole(role.getRoleName())) {
                if (!getRolesService().roleExists(role)) {
                    role.getEntityErrors().add(new String[] { field }, getMessage(MessageKeyConstants.ERROR_ROLE_DOES_NOT_EXIST,  new Object[] { edu.iu.uis.hr.Utility.splitCamelCappedString(field),role.getRoleData().toString() }));
                }
            }
        }
    }
    
    private RolesService getRolesService() {
        return (RolesService) Context.getService(RolesService.class);
    }

//    public void setRolesService(RolesService rolesService) {
//        if (rolesService != null) {
//            this.rolesService = rolesService;
//        }
//    }
}