package edu.iu.uis.hr.tk.directory;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;

public class Utility {
    public static final String ADMINISTRATOR_ROLE = "TK_ADMINISTRATOR";
    public static final String EMPLOYEE_ROLE = "TK_EMPLOYEE";
    public static final String REVIEWER_ROLE = "TK_REVIEWER";
    public static final String SUPERVISOR_ROLE = "TK_SUPERVISOR";
    public static final String PAYROLL_PROCESSOR_ROLE = "TK_PAYROLL_PROCESSOR";
    public static final String PAYROLL_MANAGER_ROLE = "TK_PAYROLL_MANAGER";
    public static final String VIEW_ONLY_ROLE = "TK_VIEW_ONLY_ROLE";
    public static final String SYSTEM_ADMINISTRATOR_ROLE = "TK_SYSTEM_ADMINISTRATOR";
    public static final String INTERFACE_MANAGER_ROLE = "TK_INTERFACE_MANAGER";
    public static final String KIOSK_ADMIN_ROLE = "TK_KIOSK_ADMIN";
    public static final String KIOSK_ROLE = "TK_KIOSK_COMPUTER";
    public static final String KIOSK_OU_SUFFIX = "TIME_KIOSKS";
    public static final String REVIEWER_ROLE_LABEL = "Reviewer";
    public static final String KIOSK_ROLE_LABEL = "Kiosk";
    public static final String SUPERVISOR_ROLE_LABEL = "TIME Approver";
    public static final String PAYROLL_PROCESSOR_ROLE_LABEL = "Payroll Processor";
    public static final String PAYROLL_MANAGER_ROLE_LABEL = "Payroll Manager";
    public static final String VIEW_ONLY_ROLE_LABEL = "View Only";
    public static final String INTERFACE_MANAGER_ROLE_LABEL = "Interface Manager";
    public static final String SYSTEM_ADMINISTRATOR_ROLE_LABEL = "System Administrator";
    public static final String KIOSK_ADMINISTRATOR_ROLE_LABEL = "Kiosk Administrator";
    public static final String ROLE_NAME_FIELD = "roleName";
    private static final String ROLE_NAME_FIELD_SUFFIX = ROLE_NAME_FIELD.substring(0, 1).toUpperCase() + ROLE_NAME_FIELD.substring(1);
    public static final String LOCATION_ROLE_NAME_DROPDOWN = FieldNames.LOCATION + ROLE_NAME_FIELD_SUFFIX;
    public static final String DEPARTMENT_ROLE_NAME_DROPDOWN = FieldNames.DEPARTMENT + ROLE_NAME_FIELD_SUFFIX;
    public static final String VIEW_ONLY_ROLE_NAME_DROPDOWN = "VIEW_ONLY" + ROLE_NAME_FIELD_SUFFIX;   
    public static final String KIOSK_ADMIN_ROLE_NAME_DROPDOWN = "KIOSK" + ROLE_NAME_FIELD_SUFFIX;
    public static final String WORK_AREA_ROLE_NAME_DROPDOWN = FieldNames.WORK_AREA + ROLE_NAME_FIELD_SUFFIX;
    public static final NonDatabaseStringPropertyDescriptor ROLE_NAME_NON_DATABASE_STRING_PROPERTY_DESCRIPTOR = new NonDatabaseStringPropertyDescriptor(RoleUser.ROLE_NAME_FIELD_NAME, false, 100);

    public static final String getNestedRoleName(String parentRoleName, String roleData) {
        return new StringBuffer(parentRoleName).append(edu.iu.uis.hr.Utility.UNDERSCORE).append(roleData).toString();
    }
    
    public static final String getKioskOU(String department) {
        //TODO : need to determine location, not derive it
        String location = department.substring(0,2);
        
        
        //TODO : take out this test value and derive from supplied department
        //OU name convention will be OU=TIME_KIOSKS,OU=DEPTID,OU=LOCATION, tho' nobody has this setup yet.
        return "OU=BL-BUS-TEST,OU=BL-BUS,OU=BL";
    }
    
    public static final String getNestedRoleData(String roleName) {
        return roleName.substring(roleName.lastIndexOf(edu.iu.uis.hr.Utility.UNDERSCORE) + 1, roleName.length());
    }    
    
    public static final String getParentRoleName(String nestedRoleName) {
        return nestedRoleName.substring(0, nestedRoleName.lastIndexOf(edu.iu.uis.hr.Utility.UNDERSCORE));
    }
}