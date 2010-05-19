package edu.iu.uis.hr.tk.department.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.department.dataaccess.DepartmentDataAccess;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.DepartmentWorkAreaSearchCriteria;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;
import edu.iu.uis.hr.tk.directory.service.RolesService;
import edu.iu.uis.hr.tk.log.LogPerformanceMethod;

public class DepartmentServiceImpl extends AbstractService implements DepartmentService {

    private static final Logger LOG = Logger.getLogger(DepartmentServiceImpl.class);
    private RolesService rolesService;
    private DirectoryService directoryService;
    private DepartmentDataAccess departmentDataAccess;

    public void setDepartmentRolesUsers(Department department) {
        LOG.debug("Started setDepartmentRolesUsers(Department department) method of DepartmentServiceImpl");
        Iterator departmentRoleNamesKeyValueIterator = getRolesService().getDepartmentRoleNames().iterator();
        List departmentRolesUsersNetworkIds = new ArrayList();
        while (departmentRoleNamesKeyValueIterator.hasNext()) {
            departmentRolesUsersNetworkIds.addAll(getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(((Option)departmentRoleNamesKeyValueIterator.next()).getValue(), department.getDepartment())));
        }
        Iterator departmentRolesUsersNetworkIdsIterator = departmentRolesUsersNetworkIds.iterator();
        while (departmentRolesUsersNetworkIdsIterator.hasNext()) {
            department.getRoleUsers().add(getRolesService().createRoleUser((String)departmentRolesUsersNetworkIdsIterator.next(), edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE, department));
        }
       LOG.debug("Finished setDepartmentRolesUsers(Department department) method of DepartmentServiceImpl");
    }
    
     public void setDepartmentViewOnlyRolesUsers(Department department) {
        LOG.debug("Started setViewOnlyRolesUsers(Department department) method of DepartmentServiceImpl");
        Iterator viewOnlyRoleNamesIterator = getRolesService().getViewOnlyRoleNames().iterator();
        List viewOnlyRolesUsersNetworkIds = new ArrayList();
         while (viewOnlyRoleNamesIterator.hasNext()) {
        	 viewOnlyRolesUsersNetworkIds.addAll(getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(((Option)viewOnlyRoleNamesIterator.next()).getValue(), department.getDepartment())));
         }
         Iterator viewOnlyRolesUsersNetworkIdsIterator = viewOnlyRolesUsersNetworkIds.iterator();
         while (viewOnlyRolesUsersNetworkIdsIterator.hasNext()) {
             department.getViewOnlyRoleUsers().add(getRolesService().createRoleUser((String)viewOnlyRolesUsersNetworkIdsIterator.next(), edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE, department));
         }
         LOG.debug("Finished setViewOnlyRolesUsers(Department department) method of DepartmentServiceImpl");
     }
    @LogPerformanceMethod
    public void setDepartmentKioskManagers(Department department) {
        LOG.debug("Started setDepartmentKioskManagers(Department department) method of DepartmentServiceImpl");
        Iterator kioskAdminRoleNamesKeyValueIterator = getRolesService().getKioskAdminRoleNames().iterator();
        List kioskAdminRolesUsersNetworkIds = new ArrayList();
        while (kioskAdminRoleNamesKeyValueIterator.hasNext()) {
            kioskAdminRolesUsersNetworkIds.addAll(getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(((Option)kioskAdminRoleNamesKeyValueIterator.next()).getValue(), department.getDepartment())));
        }
        Iterator kioskAdminRolesUsersNetworkIdsIterator = kioskAdminRolesUsersNetworkIds.iterator();
        while (kioskAdminRolesUsersNetworkIdsIterator.hasNext()) {
            department.getKioskManagers().add(getRolesService().createRoleUser((String)kioskAdminRolesUsersNetworkIdsIterator.next(), edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMIN_ROLE, department));
        }
        LOG.debug("Finished setDepartmentKioskManagers(Department department) method of DepartmentServiceImpl");
    }

    public void setKiosks(Department department) {
        LOG.debug("Started setKiosks method of DepartmentServiceImpl");
        // TODO : just a temporary solution. SO, the kiosks will not be added for every load
        if (!Utility.hasValue(department.getKiosks())) {
            department.getKiosks().addAll(getRolesService().getKiosks(department));
        }

        LOG.debug("Finished setKiosks method of DepartmentServiceImpl");
    }

    public void setWorkAreaRolesUsers(WorkArea workArea) {
        LOG.debug("Started setWorkAreaRoleUsers method of DepartmentServiceImpl");
        Iterator workAreaRoleNamesIterator = getRolesService().getWorkAreaRoleNames().iterator();
        while (workAreaRoleNamesIterator.hasNext()) {
            String workAreaName = ((Option)workAreaRoleNamesIterator.next()).getValue();
            Iterator rolesUsersNetworkIdsIterator = getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(workAreaName, workArea.getWorkArea().toString())).iterator();
            while (rolesUsersNetworkIdsIterator.hasNext()) {
                workArea.getRoleUsers().add(getRolesService().createRoleUser((String)rolesUsersNetworkIdsIterator.next(), workAreaName, workArea));
            }
            workArea.setRoleUsers(workArea.getRoleUsers());
        }
        LOG.debug("Finished setWorkAreaRoleUsers method of DepartmentServiceImpl");
    }

    public TypedPersistentMaintainedEntityList getDepartmentWorkAreas(Department department) {
        LOG.debug("Started getDepartmentWorkAreas method of DepartmentServiceImpl");
        TypedPersistentMaintainedEntityList workAreaList = new TypedPersistentMaintainedEntityList(WorkArea.class);
        workAreaList.addAll(getDepartmentDataAccess().getDepartmentWorkAreas(department.getDepartment()));
        LOG.debug("Finished getDepartmentWorkAreas method of DepartmentServiceImpl");
        return workAreaList;
    }

    public void saveDepartmentRoleUsers(Department timeDepartment) {
        LOG.debug("Started saveDepartmentRoleUsers method of DepartmentServiceImpl");
        edu.iu.uis.hr.position.entity.Department department = new edu.iu.uis.hr.position.entity.Department();
        department.setDepartment(timeDepartment.getDepartment());
        getRolesService().saveRoles(timeDepartment);
        LOG.debug("Finished saveDepartmentRoleUsers method of DepartmentServiceImpl");
    }

    // TODO these have been pushed down to roles service
    public void saveDepartmentViewOnlyRoleUsers(Department timeDepartment) {
        LOG.debug("Started saveViewOnlyRoleUsers method of DepartmentServiceImpl");
        edu.iu.uis.hr.position.entity.Department department = new edu.iu.uis.hr.position.entity.Department();
        department.setDepartment(timeDepartment.getDepartment());
        Iterator viewOnlyRoleNamesIterator = getRolesService().getViewOnlyRoleNames().iterator();
        while (viewOnlyRoleNamesIterator.hasNext()) {
            String viewOnlyRoleName = ((Option)viewOnlyRoleNamesIterator.next()).getValue();
            String nestedViewOnlyRoleName = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(viewOnlyRoleName, timeDepartment.getDepartment());
            getDirectoryService().saveRoles(timeDepartment.getNetworkIds(nestedViewOnlyRoleName), nestedViewOnlyRoleName, viewOnlyRoleName);
        }
        LOG.debug("Finished saveViewOnlyRoleUsers method of DepartmentServiceImpl");
    }
        
    // TODO these have been pushed down to roles service
    public void saveDepartmentKioskManagers(Department timeDepartment) {
        LOG.debug("Started saveDepartmentKioskManagers method of DepartmentServiceImpl");
        edu.iu.uis.hr.position.entity.Department department = new edu.iu.uis.hr.position.entity.Department();
        department.setDepartment(timeDepartment.getDepartment());
        Iterator kioskManagerRoleNamesIterator = getRolesService().getKioskAdminRoleNames().iterator();
        while (kioskManagerRoleNamesIterator.hasNext()) {
            String kioskManagerRoleName = ((Option)kioskManagerRoleNamesIterator.next()).getValue();
            String nestedKioskManagerRoleName = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(kioskManagerRoleName, timeDepartment.getDepartment());
            getDirectoryService().saveRoles(timeDepartment.getNetworkIds(nestedKioskManagerRoleName), nestedKioskManagerRoleName, kioskManagerRoleName);
        }
        LOG.debug("Finished saveDepartmentKioskManagers method of DepartmentServiceImpl");
    }

    // TODO these have been pushed down to roles service
    public void saveDepartmentKiosks(Department timeDepartment) {
        LOG.debug("Started saveDepartmentKiosks method of DepartmentServiceImpl");
        edu.iu.uis.hr.position.entity.Department department = new edu.iu.uis.hr.position.entity.Department();
        department.setDepartment(timeDepartment.getDepartment());
        Iterator kioskRoleNamesIterator = getRolesService().getKioskRoleNames().iterator();
        while (kioskRoleNamesIterator.hasNext()) {
            String kioskRoleName = ((Option)kioskRoleNamesIterator.next()).getValue();
            String nestedKioskRoleName = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(kioskRoleName, timeDepartment.getDepartment());
            getDirectoryService().saveRoles(timeDepartment.getNetworkIds(nestedKioskRoleName), nestedKioskRoleName, kioskRoleName);
        }

        LOG.debug("Finished saveDepartmentKiosks method of DepartmentServiceImpl");
    }

    public void saveWorkArea(WorkArea workArea) {
        LOG.debug("Started saveWorkArea method of DepartmentServiceImpl");
        TypedPersistentMaintainedEntityList tempList = new TypedPersistentMaintainedEntityList(WorkArea.class);
        tempList.add(workArea);
        save(tempList, departmentDataAccess);
        getRolesService().saveRoles(workArea);
        LOG.debug("WorkArea " + workArea.getWorkArea() + " saved successfully");
        LOG.debug("Finished saveWorkArea method of DepartmentServiceImpl");
    }

    public void setWorkAreaDepartments(Department department) {
        Iterator workAreaIterator = department.getWorkAreas().iterator();
        while (workAreaIterator.hasNext()) {
            ((WorkArea)workAreaIterator.next()).setDepartment(department.getDepartment());
        }
    }

    public void setRoleUsersInfo(Department department) {
        LOG.debug("Started setRoleUsersNetworkIds method of DepartmentServiceImpl");
        Iterator departmentRoleUsersIterator = department.getRoleUsers().iterator();
        while (departmentRoleUsersIterator.hasNext()) {
            RoleUser roleUser = (RoleUser)departmentRoleUsersIterator.next();
            setRoleUsersInfo(roleUser);        
        }
        Iterator departmentKioskManagersIterator = department.getKioskManagers().iterator();
        while (departmentKioskManagersIterator.hasNext()) {
            RoleUser roleUser = (RoleUser)departmentKioskManagersIterator.next();
            setRoleUsersInfo(roleUser);        
        }
        
        Iterator departmentViewOnlyIterator = department.getViewOnlyRoleUsers().iterator();
        while (departmentViewOnlyIterator.hasNext()) {
            RoleUser roleUser = (RoleUser)departmentViewOnlyIterator.next();
            setRoleUsersInfo(roleUser);
        }

        Iterator workAreas = department.getWorkAreas().iterator();
        while (workAreas.hasNext()) {
            WorkArea workArea = (WorkArea)workAreas.next();
            List workAreaRoleUsers = workArea.getRoleUsers();
            Iterator workAreaRoleUsersIterator = workAreaRoleUsers.iterator();
            while (workAreaRoleUsersIterator.hasNext()) {
                RoleUser roleUser = (RoleUser)workAreaRoleUsersIterator.next();
                setRoleUsersInfo(roleUser);        
            }
        }
        LOG.debug("Finished setRoleUsersNetworkIds method of DepartmentServiceImpl");
    }

    public String getRoleUsersNetworkId(RoleUser roleUser) {
        if (Utility.hasValue(roleUser.getUniversityId())) {
            try {
                return getDirectoryService().getNetworkIdByEmployeeId(roleUser.getUniversityId());
            } catch (Exception e) {
                return edu.iu.uis.hr.Utility.getDefaultStringValue();
            }
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }

    public String getRoleUsersName(RoleUser roleUser) {
        if (Utility.hasValue(roleUser.getUniversityId())) {
            try {
                return (getDirectoryService().getUser(roleUser.getNetworkId(), false)).getName();
            } catch (Exception e) {
                return edu.iu.uis.hr.Utility.getDefaultStringValue();
            }
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }

    private void setRoleUsersInfo(RoleUser roleUser) {
        // only reload from DirectoryService if neccessary
        if(Utility.hasValue(roleUser.getNetworkId())) {
            if (!Utility.hasValue(roleUser.getName())) {
                User user = getDirectoryService().getUser(roleUser.getNetworkId(), false);
                roleUser.setNetworkId(user.getNetworkId());
                roleUser.setName(user.getName());
            }
        }
    }

    private void setRoleUsersName(RoleUser roleUser) {
        roleUser.setName(getRoleUsersName(roleUser));
    }

    private RolesService getRolesService() {
        return rolesService;
    }

    public void setRolesService(RolesService rolesService) {
        if (rolesService != null) {
            this.rolesService = rolesService;
        }
    }

    public DepartmentDataAccess getDepartmentDataAccess() {
        return departmentDataAccess;
    }

    public void setDepartmentDataAccess(DepartmentDataAccess departmentDataAccess) {
        if (departmentDataAccess != null) {
            this.departmentDataAccess = departmentDataAccess;
        }
    }

    public TypedPersistentMaintainedEntityList lookupDepartmentWorkAreas(DepartmentWorkAreaSearchCriteria searchCriteria) {
        return getDepartmentDataAccess().lookupDepartmentWorkAreas(searchCriteria);
    }

    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public void setDirectoryService(DirectoryService directoryService) {
        if (directoryService != null) {
            this.directoryService = directoryService;
        }
    }
}
