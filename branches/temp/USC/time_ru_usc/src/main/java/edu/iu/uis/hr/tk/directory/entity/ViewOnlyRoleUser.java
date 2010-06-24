package edu.iu.uis.hr.tk.directory.entity;

public class ViewOnlyRoleUser extends RoleUser {

    public ViewOnlyRoleUser() {
        super();
    }

    public ViewOnlyRoleUser(String name, String networkId, String universityId, String roleName) {
        super(name, networkId, universityId, roleName);
    }

    public String getDropdownName(String fieldName) {
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            return edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE_NAME_DROPDOWN;
        }
        //return super.getDropdownName(fieldName);
        return null;
    }

}
