package edu.iu.uis.hr.tk.directory.entity;

public class KioskAdminRoleUser extends RoleUser {

    public KioskAdminRoleUser() {
        super();
    }

    public KioskAdminRoleUser(String name, String networkId, String universityId, String roleName) {
        super(name, networkId, universityId, roleName);
    }

    public String getDropdownName(String fieldName) {
        // TODO : this class is created primarily for this method.   Don't have a good solution
        // to set up this KIOSK_ADMIN_ROLE_NAME_DROPDOWN in RoleUser ?
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            return edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMIN_ROLE_NAME_DROPDOWN;
        }
        //return super.getDropdownName(fieldName);
        return null;
    }

}
