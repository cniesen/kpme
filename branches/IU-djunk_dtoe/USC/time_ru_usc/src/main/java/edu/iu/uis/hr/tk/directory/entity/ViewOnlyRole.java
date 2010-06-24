package edu.iu.uis.hr.tk.directory.entity;

public class ViewOnlyRole extends DepartmentRole {
  
    public ViewOnlyRole() {
    }

    public ViewOnlyRole(String roleName, String department, String description) {
        super(roleName, department, description);
     
    }

    public Class getModeAuthorization() {
        return ViewOnlyRoleModeAuthorization.class;
    }

    public String getDropdownName(String fieldName) {
      if (edu.iu.uis.hr.tk.directory.Utility.ROLE_NAME_FIELD.equals(fieldName)) {
       		return edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE_NAME_DROPDOWN;
      }
 
        return super.getDropdownName(fieldName);
    }

}