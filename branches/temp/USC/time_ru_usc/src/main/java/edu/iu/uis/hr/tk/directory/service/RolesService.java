package edu.iu.uis.hr.tk.directory.service;

import java.math.BigDecimal;
import java.util.List;

import edu.iu.uis.hr.CheckboxList;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.directory.entity.RoleAuditSearchCriteria;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;
import edu.iu.uis.hr.tk.directory.entity.UserRole;
import edu.iu.uis.hr.tk.directory.entity.UserRoles;

public interface RolesService extends edu.iu.uis.hr.service.Service {

    public boolean roleExists(UserRole role);

    public boolean hasRole(String networkId, String roleName);

    public int getRoleNetworkIdCount(String roleName);

    public List getLocationRoleNames();

    public List getRoleLocationCodes();

    public List getDepartmentRoleNames();
    
    public List getViewOnlyRoleNames();

    public List getWorkAreaRoleNames();
    
    public UserRoles getUserRoles(String universityId);

    public List getAllAdministratorUsers();

    public List getAllAdministratorMemberRoles();

    public TypedPersistentMaintainedEntityList getKiosks(Department department);
    
    public void removeNetworkIdFromRoles(String networkId);

    public List getSupervisors(BigDecimal workArea);

    public List getPayrollProcessors(BigDecimal workArea);

    public CheckboxList getSupervisorsAsEmailRecipients(BigDecimal workArea);

    public boolean isValidDelegate(String delegatorNetworkId, String delegateNetworkId, BigDecimal workArea, String roleName);

    public List getKioskAdminRoleNames();

    public List getKioskRoleNames();
    
    public boolean isAuthorizedToEnterDelegate(String userNetworkId, BigDecimal workAreaId);
    
    public RoleUser createRoleUser(String networkId, String roleName, Entity entity);
    
    public void saveRoles(UserRoles userRoles);
    
    public void saveRoles(Department department);
    
    public void saveRoles(WorkArea workArea);

    public TypedPersistentMaintainedEntityList lookupRoleChanges(RoleAuditSearchCriteria searchCriteria);
    
    public boolean isRequiredRole(String roleName);

}