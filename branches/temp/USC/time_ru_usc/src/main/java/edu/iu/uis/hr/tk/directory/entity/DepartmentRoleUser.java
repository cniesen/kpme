package edu.iu.uis.hr.tk.directory.entity;

public class DepartmentRoleUser extends RoleUser {

    public DepartmentRoleUser() {
        super();
    }

    public DepartmentRoleUser(String name, String networkId, String universityId, String roleName) {
        super(name, networkId, universityId, roleName);
    }

    public String getDropdownName(String fieldName) {
        if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
            return edu.iu.uis.hr.tk.directory.Utility.DEPARTMENT_ROLE_NAME_DROPDOWN;
        }
        //return super.getDropdownName(fieldName);
        return null;
    }
}
