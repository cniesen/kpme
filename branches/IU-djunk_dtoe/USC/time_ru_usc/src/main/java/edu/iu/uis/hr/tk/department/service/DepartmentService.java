package edu.iu.uis.hr.tk.department.service;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.DepartmentWorkAreaSearchCriteria;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;

public interface DepartmentService extends Service {

    public void setDepartmentRolesUsers(Department department);
    
    public void setDepartmentViewOnlyRolesUsers(Department department);

//    public void setKiosks(Department department);

    public void setWorkAreaRolesUsers(WorkArea workArea);

    public TypedPersistentMaintainedEntityList getDepartmentWorkAreas(Department department);

    public TypedPersistentMaintainedEntityList lookupDepartmentWorkAreas(DepartmentWorkAreaSearchCriteria searchCriteria);

    public void saveDepartmentRoleUsers(Department department);
    
    //public void saveDepartmentViewOnlyRoleUsers(Department department);

    public void saveWorkArea(WorkArea workArea);

    public void setRoleUsersInfo(Department department);

    public void setWorkAreaDepartments(Department department);

    public String getRoleUsersNetworkId(RoleUser roleUser);

    public String getRoleUsersName(RoleUser roleUser);

    public void setDepartmentKioskManagers(Department department);

    public void saveDepartmentKioskManagers(Department department);

    public void saveDepartmentKiosks(Department department);

}