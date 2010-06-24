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

public class RoleMustHaveOneMemberLogic extends AbstractLogic implements OperationalLogic {

    private static final Logger LOG = Logger.getLogger(RoleMustHaveOneMemberLogic.class);

//    private RolesService rolesService;

    public boolean isMatch() {
        return true;
    }

    public void execute(Entity entity) {
        if (!(entity instanceof UserRoles)) {
            throw new IllegalArgumentException("RoleMustHaveOneMemberLogic's execute(Entity entity) method requires a non-null Entity of type UserRoles");
        }
        UserRoles currentUserRoles = getRolesService().getUserRoles(((UserRoles)entity).getUniversityId());
        checkMembership(entity, currentUserRoles.getDepartmentRoles(), ((UserRoles)entity).getDepartmentRoles(), FieldNames.DEPARTMENT);
        checkMembership(entity, currentUserRoles.getWorkAreaRoles(), ((UserRoles)entity).getWorkAreaRoles(), FieldNames.WORK_AREA);
        checkMembership(entity, currentUserRoles.getLocationRoles(), ((UserRoles)entity).getLocationRoles(), FieldNames.LOCATION);
    }

    private void checkMembership(Entity entity, List currentRoles, List roles, String field) {
        List rolesToDelete = edu.iu.uis.hr.Utility.getRemovedObjects(currentRoles, roles);
        Iterator rolesItr = rolesToDelete.iterator();
        while (rolesItr.hasNext()) {
            UserRole role = (UserRole)rolesItr.next();
            if (getRolesService().isRequiredRole(role.getRoleName())) {
                if (getRolesService().hasRole(((UserRoles)entity).getNetworkId(), role.getNestedRoleName()) && (getRolesService().getRoleNetworkIdCount(role.getNestedRoleName()) == 1)) {
                    role.getEntityErrors().add(new String[] { field }, getMessage(MessageKeyConstants.ERROR_ONE_ROLE_MEMBER_REQUIRED, new Object[] { ((UserRoles)entity).getName(), role.getRoleData() }));
                    roles.add(role);
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