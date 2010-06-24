package edu.iu.uis.hr.tk.directory.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.CheckboxList;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.directory.entity.EmailRecipient;
import edu.iu.uis.hr.directory.service.DirectoryService;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.position.entity.Location;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.Kiosk;
import edu.iu.uis.hr.tk.directory.dataaccess.RoleAuditDataAccess;
import edu.iu.uis.hr.tk.directory.entity.DepartmentRole;
import edu.iu.uis.hr.tk.directory.entity.DepartmentRoleUser;
import edu.iu.uis.hr.tk.directory.entity.LocationRole;
import edu.iu.uis.hr.tk.directory.entity.RoleAudit;
import edu.iu.uis.hr.tk.directory.entity.RoleAuditSearchCriteria;
import edu.iu.uis.hr.tk.directory.entity.RoleUser;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.directory.entity.UserRole;
import edu.iu.uis.hr.tk.directory.entity.UserRoles;
import edu.iu.uis.hr.tk.directory.entity.ViewOnlyRole;
import edu.iu.uis.hr.tk.directory.entity.ViewOnlyRoleUser;
import edu.iu.uis.hr.tk.directory.entity.WorkAreaRole;
import edu.iu.uis.hr.tk.directory.entity.WorkAreaRoleUser;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;


public class RolesServiceImpl extends AbstractService implements RolesService {
    
    private static final List INTERFACE_MGR_ROLE_NAMES = new ArrayList();
    static {
        INTERFACE_MGR_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.INTERFACE_MANAGER_ROLE, edu.iu.uis.hr.tk.directory.Utility.INTERFACE_MANAGER_ROLE_LABEL));
    }
    private static final List LOCATION_ROLE_NAMES = new ArrayList();
    static {
        LOCATION_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE, edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE_LABEL));
    }
    private static final List DEPARTMENT_ROLE_NAMES = new ArrayList();
    static {
        DEPARTMENT_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE, edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE_LABEL));
        //DEPARTMENT_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE, edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE_LABEL));
    }
    // TODO - moved to dept role
    private static final List VIEW_ONLY_ROLE_NAMES = new ArrayList();
    static {
    	VIEW_ONLY_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE, edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE_LABEL));
    }
    private static final List KIOSK_ADMIN_ROLE_NAMES = new ArrayList();
    static {
        KIOSK_ADMIN_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMIN_ROLE, edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMINISTRATOR_ROLE_LABEL));
    }
    private static final List KIOSK_ROLE_NAMES = new ArrayList();
    static {
        KIOSK_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ROLE, edu.iu.uis.hr.tk.directory.Utility.KIOSK_ROLE_LABEL));
    }
    private static final List WORK_AREA_ROLE_NAMES = new ArrayList();
    static {
        WORK_AREA_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE, edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE_LABEL));
        WORK_AREA_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.REVIEWER_ROLE, edu.iu.uis.hr.tk.directory.Utility.REVIEWER_ROLE_LABEL));
        WORK_AREA_ROLE_NAMES.add(new Option(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE, edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE_LABEL));
    }
    
    private static final String ADD_OPERATION = DirectoryService.ADD_OPERATION;
    private static final String REMOVE_OPERATION = DirectoryService.REMOVE_OPERATION;
    
    private static final Logger LOG = Logger.getLogger(RolesServiceImpl.class);
    
    private WebUserService webUserService;
    private PositionService positionService;
    private AssignmentService assignmentService;
    private DirectoryService directoryService;
    private RoleAuditDataAccess roleAuditDataAccess;

    private WebUserService getWebUserService() {
        return webUserService;
    }

    public void setWebUserService(WebUserService webUserService) {
        if (webUserService != null) {
            this.webUserService = webUserService;
        }
    }

    private PositionService getPositionService() {
        return positionService;
    }

    public void setPositionService(PositionService positionService) {
        if (positionService != null) {
            this.positionService = positionService;
        }
    }

    private AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        if (assignmentService != null) {
            this.assignmentService = assignmentService;
        }
    }
    
    public DirectoryService getDirectoryService() {
        return directoryService;
    }

    public void setDirectoryService(DirectoryService directoryService) {
        if (directoryService != null) {
            this.directoryService = directoryService;
        }
    }

    public RoleAuditDataAccess getRoleAuditDataAccess() {
        return roleAuditDataAccess;
    }

    public void setRoleAuditDataAccess(RoleAuditDataAccess roleAuditDataAccess) {
        if (roleAuditDataAccess != null) {
            this.roleAuditDataAccess = roleAuditDataAccess;
        }
    }
    
    public boolean roleExists(UserRole role) {
        return getDirectoryService().roleExists(role.getNestedRoleName());
    }

    public boolean hasRole(String networkId, String roleName) {
        edu.iu.uis.hr.directory.entity.User user = getDirectoryService().getUser(networkId);
        return user.getRoles().contains(roleName.toUpperCase()) || user.getRoles().contains(roleName.toLowerCase());
    }

    public int getRoleNetworkIdCount(String roleName) {
        return getDirectoryService().getRoleNetworkIds(roleName).size();
    }

    public List getLocationRoleNames() {
        return LOCATION_ROLE_NAMES;
    }

    public List getRoleLocationCodes() {
        List groupLocations = new ArrayList();
        //List allMembers = getDirectoryService().getAllMemberRoles(edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE);
        Iterator locations = getPositionService().getDropdownLocations().iterator();
        while (locations.hasNext()) {
            Location location = (Location) locations.next();
           // if ( allMembers.contains(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE, location.getLocation()))) {
                groupLocations.add(location.getLocation());
           // }
        }
        return groupLocations;
    }

    public List getInterfaceManagerRoleNames() {
        return INTERFACE_MGR_ROLE_NAMES;
    }
    
    public List getDepartmentRoleNames() {
        return DEPARTMENT_ROLE_NAMES;
    }

    // TODO - moved to dept role
    public List getViewOnlyRoleNames() {
        return VIEW_ONLY_ROLE_NAMES;
    }
    
    public List getKioskAdminRoleNames() {
        return KIOSK_ADMIN_ROLE_NAMES;
    }

    public List getKioskRoleNames() {
        return KIOSK_ROLE_NAMES;
    }

    public List getWorkAreaRoleNames() {
        return WORK_AREA_ROLE_NAMES;
    }

    public UserRoles getUserRoles(String universityId) {
        LOG.debug("getUserRoles(String universityId) - enter, args universityId=" + universityId);
        String networkId = edu.iu.uis.hr.Utility.getDefaultStringValue();
        networkId = getDirectoryService().getNetworkIdByEmployeeId(universityId);
        User user = new edu.iu.uis.hr.tk.directory.entity.UserImpl(getDirectoryService().getUser(networkId));
        UserRoles userRoles = new UserRoles();
        userRoles.setUniversityId(user.getUniversityId());
        userRoles.setNetworkId(user.getNetworkId());
        userRoles.setName(user.getName());
        userRoles.setInterfaceManager(user.hasSystemRole());
        userRoles.setLocationRoles(getMaintainableLocationRoles(edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE, getPositionService().getLocations(user.getSystemAdministratorLocations())));
        userRoles.setDepartmentRoles(getMaintainableDepartmentRoles(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE, getPositionService().getDepartments(user.getPayrollManagerDepartments(), new Date())));
        userRoles.setViewOnlyRoles(getMaintainableViewOnlyRoles(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE, getPositionService().getDepartments(user.getViewOnlyDepartments(), new Date())));
        userRoles.setWorkAreaRoles(getMaintainableWorkAreaRoles(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE, getAssignmentService().getWorkAreas(user.getPayrollProcessorWorkAreas(), new Date())));
        userRoles.getWorkAreaRoles().addAll(getMaintainableWorkAreaRoles(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE, getAssignmentService().getWorkAreas(user.getSupervisorWorkAreas(), new Date())));
        userRoles.getWorkAreaRoles().addAll(getMaintainableWorkAreaRoles(edu.iu.uis.hr.tk.directory.Utility.REVIEWER_ROLE, getAssignmentService().getWorkAreas(user.getReviewerWorkAreas(), new Date())));
        LOG.debug("getUserRoles(String universityId) - exit");
        return userRoles;
    }

    public TypedPersistentMaintainedEntityList getKiosks(Department department) {

        //TODO: NOTE THAT KIOSK NAMES ARE COMING BACK LOWER CASE RIGHT NOW.
        Map kiosks = new HashMap();
        //get all the kiosks
        List departmentCodes = new ArrayList();
        departmentCodes.add(department.getDepartment());
        List departments = getPositionService().getDepartments(departmentCodes, new Date());
        String kioskRole;
        Iterator kioskItr;
        for (Iterator iter = departments.iterator(); iter.hasNext();) {
            //edu.iu.uis.hr.position.entity.Department iuDepartment = (edu.iu.uis.hr.position.entity.Department) iter.next();
            // TODO : OU=TIME_KIOSKS,OU=DEPTID,OU=LOCATION - remove this later
            // remove the Utility.getKioskOU also
            //kioskRole = new StringBuffer("OU=TIME_KIOSKS,OU=").append(department.getDepartment()).append(",OU=").append(iuDepartment.getLocation()).toString();
            kioskRole = edu.iu.uis.hr.tk.directory.Utility.getKioskOU(department.getDepartment());
        }

        kioskRole = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ROLE, department.getDepartment());

        TypedPersistentMaintainedEntityList kioskList = new TypedPersistentMaintainedEntityList(Kiosk.class);
        kioskItr = kiosks.values().iterator();
        while (kioskItr.hasNext()) {
            Kiosk kiosk = (Kiosk) kioskItr.next();
            if (Utility.hasValue(kiosk)) {
                kioskList.add(kiosk);
            }
        }
        return kioskList;
    }

    private TypedPersistentMaintainedEntityList getMaintainableLocationRoles(String roleName, List locations) {
        TypedPersistentMaintainedEntityList userRoles = new TypedPersistentMaintainedEntityList(LocationRole.class);
        if (Utility.hasValue(locations)) {
            Iterator locationsItr = locations.iterator();
            while (locationsItr.hasNext()) {
                userRoles.add(new LocationRole(roleName, ((Location) locationsItr.next()).getValue()));
            }
        }
        return userRoles;
    }

    private TypedPersistentMaintainedEntityList getMaintainableDepartmentRoles(String roleName, List departments) {
        TypedPersistentMaintainedEntityList userRoles = new TypedPersistentMaintainedEntityList(DepartmentRole.class);
        if (Utility.hasValue(departments)) {
            Iterator departmentsItr = departments.iterator();
            java.util.HashMap deptMap = new java.util.HashMap();
            while (departmentsItr.hasNext()) {           	
                edu.iu.uis.hr.position.entity.Department department = (edu.iu.uis.hr.position.entity.Department) departmentsItr.next();
                if(!deptMap.containsKey(department.getDepartment())){
                	deptMap.put(department.getDepartment(), null);
                    userRoles.add(new DepartmentRole(roleName, department.getDepartment(), department.getDescription()));
                }
            }
        }
        return userRoles;
    }
    
    private TypedPersistentMaintainedEntityList getMaintainableViewOnlyRoles(String roleName, List departments) {
        TypedPersistentMaintainedEntityList userRoles = new TypedPersistentMaintainedEntityList(ViewOnlyRole.class);
        if (Utility.hasValue(departments)) {
            Iterator departmentsItr = departments.iterator();
            java.util.HashMap deptMap = new java.util.HashMap();
            while (departmentsItr.hasNext()) {
                edu.iu.uis.hr.position.entity.Department department = (edu.iu.uis.hr.position.entity.Department) departmentsItr.next();
                if(!deptMap.containsKey(department.getDepartment())){
                	deptMap.put(department.getDepartment(), null);
                	userRoles.add(new ViewOnlyRole(roleName, department.getDepartment(), department.getDescription()));
                }
            }
        }
        return userRoles;
    }

    private TypedPersistentMaintainedEntityList getMaintainableWorkAreaRoles(String roleName, List workAreas) {
        TypedPersistentMaintainedEntityList userRoles = new TypedPersistentMaintainedEntityList(WorkAreaRole.class);
        if (Utility.hasValue(workAreas)) {
            Iterator workAreasItr = workAreas.iterator();
            while (workAreasItr.hasNext()) {
                WorkArea workArea = (WorkArea) workAreasItr.next();
                userRoles.add(new WorkAreaRole(roleName, workArea.getWorkArea(), workArea.getDepartment(), workArea.getDescription()));
            }
        }
        return userRoles;
    }

    public List getAllAdministratorUsers() {
        ArrayList allAdministratorUsers = new ArrayList();
        allAdministratorUsers.addAll(getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.ADMINISTRATOR_ROLE));
        allAdministratorUsers.addAll(getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMIN_ROLE));
        return allAdministratorUsers;
    }

    public List getAllAdministratorMemberRoles() {
        // TK_ADMINISTRATOR & TK_KIOSK_ADMIN are not in the list
        ArrayList allAdministratorMemberRoles = new ArrayList();
        allAdministratorMemberRoles.addAll(getDirectoryService().getAllMemberRoles(edu.iu.uis.hr.tk.directory.Utility.ADMINISTRATOR_ROLE));
        allAdministratorMemberRoles.addAll(getDirectoryService().getAllMemberRoles(edu.iu.uis.hr.tk.directory.Utility.KIOSK_ADMIN_ROLE));
        return allAdministratorMemberRoles;
    }

    public void removeNetworkIdFromRoles(String networkId) {
    }

    public List getSupervisors(BigDecimal workArea) {
        return getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE, workArea.toString()));
    }

    public List getPayrollProcessors(BigDecimal workArea) {
        return getDirectoryService().getRoleNetworkIds(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE, workArea.toString()));
    }

    public CheckboxList getSupervisorsAsEmailRecipients(BigDecimal workArea) {
        CheckboxList supervisors = new CheckboxList(EmailRecipient.class);
        Iterator supervisorsItr = getSupervisors(workArea).iterator();
        while (supervisorsItr.hasNext()) {
            edu.iu.uis.hr.directory.entity.User user = getDirectoryService().getUser((String) supervisorsItr.next(), false);
            supervisors.add(new EmailRecipient(user));
        }
        if (supervisors.size() == 1) {
            ((EmailRecipient) supervisors.get(0)).setChecked(true);
        }
        return supervisors;
    }

    public boolean isValidDelegate(String delegatorNetworkId, String delegateNetworkId, BigDecimal workAreaId, String roleName) {
        edu.iu.uis.hr.directory.entity.User delegator = getDirectoryService().getUser(delegatorNetworkId);
        WorkArea workArea = getAssignmentService().getWorkArea(workAreaId, new Date());
        // TODO : remove comments after test successfully with workflow
        // need to check null work area
        
        if (workArea == null) {
        	return false;
        }
        if (delegator == null || (!delegator.getRoles().contains(edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(roleName, workAreaId.toString())))) {
            return false;
        } else {
            //edu.iu.uis.hr.directory.entity.User delegate = getDirectoryService().getUser(delegateNetworkId);
            User delegate = new edu.iu.uis.hr.tk.directory.entity.UserImpl(getDirectoryService().getUser(delegateNetworkId));
            //  delegate=null?
            List workAreaIds = new ArrayList();
            workAreaIds.addAll(delegate.getPayrollProcessorWorkAreas());
            workAreaIds.addAll(delegate.getSupervisorWorkAreas());
            ArrayList workAreas = (ArrayList) getAssignmentService().getWorkAreas(workAreaIds, new Date());
            if (Utility.hasValue(workAreas)) {
                for (Iterator iter = workAreas.iterator(); iter.hasNext();) {
                    WorkArea element = (WorkArea) iter.next();
                    if (element.getDepartment().equals(workArea.getDepartment())) {
                        return true; // either supv/payp
                    }
                }
            }
            if (delegate.hasSystemRole()) {
                return true; // IM
            } else {
                ArrayList departmentCodes = new ArrayList();
                departmentCodes.add(workArea.getDepartment());
                List departments = getPositionService().getDepartments(departmentCodes, new Date());
                if (delegate.hasJurisdictionOverDepartments(departments)) {
                    // payroll manager
                    return true;
                } else {
                    for (Iterator iter = departments.iterator(); iter.hasNext();) {
                        edu.iu.uis.hr.position.entity.Department department = (edu.iu.uis.hr.position.entity.Department) iter.next();
                        if (delegate.hasJurisdictionOver(department.getLocationObject())) {
                            return true; // System Admin
                        }
                    }
                    return false;
                }
            }
        }
    }

    public boolean isAuthorizedToEnterDelegate(String userNetworkId, BigDecimal workAreaId) {
        // TODO : remove comments after test successfully with workflow
        User user = (User) getWebUserService().getUser(userNetworkId);
        WorkArea workArea = getAssignmentService().getWorkArea(workAreaId, new Date());
        if (user.hasSystemRole()) {
            return true; // IM
        } else {
            ArrayList departmentCodes = new ArrayList();
            departmentCodes.add(workArea.getDepartment());
            List departments = getPositionService().getDepartments(departmentCodes, new Date());
            if (user.hasJurisdictionOverDepartments(departments)) {
                // payroll manager
                return true;
            } else {
                for (Iterator iter = departments.iterator(); iter.hasNext();) {
                    edu.iu.uis.hr.position.entity.Department department = (edu.iu.uis.hr.position.entity.Department) iter.next();
                    if (user.hasJurisdictionOver(department.getLocationObject())) {
                        return true; // System Admin
                    }
                }
                return false;
            }
        }
    }

    public RoleUser createRoleUser(String networkId, String roleName, Entity entity) {
        // TODO - used as a factory method for the correct RoleUser subtype - maybe belongs elsewhere
        edu.iu.uis.hr.directory.entity.User user = getDirectoryService().getUser(networkId, false);
        if (entity instanceof Department) {
            if (isViewOnlyRole(roleName, true)) {
                return new ViewOnlyRoleUser(user.getName(), user.getNetworkId(), user.getUniversityId(), roleName);
            } else if (isDepartmentRole(roleName, true)) {
                return new DepartmentRoleUser(user.getName(), user.getNetworkId(), user.getUniversityId(), roleName);
            } else {
                throw new IllegalArgumentException("RolesServiceImpl: " + roleName + " is an invalid Department role.");
            }
        } else if (entity instanceof edu.iu.uis.hr.tk.department.entity.WorkArea) {
            return new WorkAreaRoleUser(user.getName(), user.getNetworkId(), user.getUniversityId(), roleName);
        } else {
            throw new IllegalArgumentException("RolesServiceImpl: " + roleName + " is an invalid Department or WorkArea role.");
        }
    }

    public void saveRoles(UserRoles userRoles) {
        LOG.debug("saveRoles(UserRoles userRoles) - enter");
        edu.iu.uis.hr.directory.entity.User user = getDirectoryService().getUser(userRoles.getNetworkId(), false);
        List currentRoleNames = getUserRoles(userRoles.getUniversityId()).getRoleNames();
        List modifiedRoleNames = userRoles.getRoleNames();
        Map operationToRoleNameMap = getDirectoryService().saveRoles(currentRoleNames, modifiedRoleNames, userRoles.getNetworkId());
        // check removed roles getRoleNames
        List removedRoleNames = (List) operationToRoleNameMap.get(REMOVE_OPERATION);
        
        if (removedRoleNames.size() > 0) {
            Iterator removedRoleNamesIterator = removedRoleNames.iterator();
            while (removedRoleNamesIterator.hasNext()) {
                String nestedRoleName = (String) removedRoleNamesIterator.next();
                saveRoleAudit(user, nestedRoleName, REMOVE_OPERATION);
            }
        }
        List addedRoleNames = (List) operationToRoleNameMap.get(ADD_OPERATION);
        if(addedRoleNames.size() > 0) {
            Iterator addedRoleNamesIterator = addedRoleNames.iterator();
            while (addedRoleNamesIterator.hasNext()) {
                String nestedRoleName = (String) addedRoleNamesIterator.next();
                saveRoleAudit(user, nestedRoleName, ADD_OPERATION);
            }
        }
        LOG.debug("saveRoles(UserRoles userRoles) - exit");
    }

    public void saveRoles(Department department) {
        LOG.debug("saveRoles(Department department) - enter");
        Iterator departmentRoleNamesIterator = getDepartmentRoleNames().iterator();
        while (departmentRoleNamesIterator.hasNext()) {
            String departmentRoleName = ((Option)departmentRoleNamesIterator.next()).getValue();
            String nestedDepartmentRoleName = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(departmentRoleName, department.getDepartment());
            Map operationToNetworkIdMap = getDirectoryService().saveRoles(department.getNetworkIds(nestedDepartmentRoleName), nestedDepartmentRoleName, departmentRoleName);
            saveRoles(operationToNetworkIdMap, department.getDepartment(), nestedDepartmentRoleName);
        }
        Iterator viewOnlyRoleNamesIterator = getViewOnlyRoleNames().iterator();
        while (viewOnlyRoleNamesIterator.hasNext()) {
            String viewOnlyRoleName = ((Option)viewOnlyRoleNamesIterator.next()).getValue();
            String nestedViewOnlyRoleName = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(viewOnlyRoleName, department.getDepartment());
            Map operationToNetworkIdMap = getDirectoryService().saveRoles(department.getViewOnlyNetworkIds(nestedViewOnlyRoleName), nestedViewOnlyRoleName, viewOnlyRoleName);
            saveRoles(operationToNetworkIdMap, department.getDepartment(), nestedViewOnlyRoleName);
        }
        LOG.debug("saveRoles(Department department) - exit");
    }
    
    public void saveRoles(edu.iu.uis.hr.tk.department.entity.WorkArea workArea) {
        LOG.debug("saveRoles(WorkArea workArea) - enter");
        Iterator workAreaRoleNamesIterator = getWorkAreaRoleNames().iterator();
        while (workAreaRoleNamesIterator.hasNext()) {
            String workAreaRoleName = ((Option)workAreaRoleNamesIterator.next()).getValue();
            String nestedWorkAreaRoleName = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleName(workAreaRoleName, workArea.getWorkArea().toString());
            Map operationToNetworkIdMap = getDirectoryService().saveRoles(workArea.getNetworkIds(nestedWorkAreaRoleName), nestedWorkAreaRoleName, workAreaRoleName);
            saveRoles(operationToNetworkIdMap, workArea.getDepartment(), nestedWorkAreaRoleName);
        }
        LOG.debug("saveRoles(WorkArea workArea) - exit");
    }

    private void saveRoles(Map map, String department, String nestedRoleName) {
        List removedNetworkIds = (List) map.get(REMOVE_OPERATION);
        if (removedNetworkIds.size() > 0) {
            Iterator removedIterator = removedNetworkIds.iterator();
            while (removedIterator.hasNext()) {
                String networkId = (String) removedIterator.next();
                edu.iu.uis.hr.directory.entity.User user = getDirectoryService().getUser(networkId, false);
                saveRoleAudit(user, nestedRoleName, department, getLocation(department), REMOVE_OPERATION);
            }
        }
        List addedNetworkIds = (List) map.get(ADD_OPERATION);
        if (addedNetworkIds.size() > 0) {
            Iterator addedIterator = addedNetworkIds.iterator();
            while (addedIterator.hasNext()) {
                String networkId = (String) addedIterator.next();
                edu.iu.uis.hr.directory.entity.User user = getDirectoryService().getUser(networkId, false);
                saveRoleAudit(user, nestedRoleName, department, getLocation(department), ADD_OPERATION);
            }
        }
    }
    
    private void saveRoleAudit(edu.iu.uis.hr.directory.entity.User user, String nestedRoleName, String operation) {
        if (isInterfaceManager(nestedRoleName)) {
            saveRoleAudit(user, nestedRoleName, null, null, operation);
        }
        if (isLocationRole(nestedRoleName)) {
            String roleData = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleData(nestedRoleName);
            saveRoleAudit(user, nestedRoleName, null, roleData, operation);
        }
        if (isDepartmentRole(nestedRoleName)) {
            String roleData = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleData(nestedRoleName);
            String location = getLocation(roleData);
            saveRoleAudit(user, nestedRoleName, roleData, location, operation);
        }
        if (isViewOnlyRole(nestedRoleName)) {
            String roleData = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleData(nestedRoleName);
            String location = getLocation(roleData);
            saveRoleAudit(user, nestedRoleName, roleData, location, operation);
        }
        if (isWorkAreaRole(nestedRoleName)) {
            String roleData = edu.iu.uis.hr.tk.directory.Utility.getNestedRoleData(nestedRoleName);
            WorkArea workArea = getAssignmentService().getWorkArea(new BigDecimal(roleData), new Date());
            String location = getLocation(workArea.getDepartment());
            saveRoleAudit(user, nestedRoleName, workArea.getDepartment(), location, operation);
        }
    }
    
    private void saveRoleAudit(edu.iu.uis.hr.directory.entity.User user, String nestedRoleName, String department, String location, String operation) {
        RoleAudit roleAudit = createRoleAudit(user.getUniversityId(), nestedRoleName, department, location, operation);
        save(roleAudit, getRoleAuditDataAccess());
    }
    
    private RoleAudit createRoleAudit(String roleUniversityId, String role, String department, String location, String operation) {
        LOG.debug("createRoleAudit(String userUniversityId, String role, String department, String location, String operation) - enter");
        RoleAudit roleAudit = new RoleAudit();
        roleAudit.setRoleUniversityId(roleUniversityId);
        roleAudit.setOperation(operation);
        //roleAudit.setUserUniversityId(getWebUserService().getUser().getUniversityId());
        roleAudit.setRole(role);
        roleAudit.setDepartment(department);
        roleAudit.setLocation(location);
        //Timestamp now = new Timestamp();
        //now.setDate(new Date());
        //roleAudit.setTimestamp(now);
        LOG.debug("createRoleAudit(String userUniversityId, String role, String department, String location, String operation) - exit");
        return roleAudit;
    }

    public TypedPersistentMaintainedEntityList lookupRoleChanges(RoleAuditSearchCriteria searchCriteria) {
        return getRoleAuditDataAccess().lookupRoleAudit(searchCriteria);
    }
    
    public boolean isRequiredRole(String roleName) {
        if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.INTERFACE_MANAGER_ROLE)) {
            return true;
        }
        if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.SYSTEM_ADMINISTRATOR_ROLE)) {
            return true;
        }
        if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_MANAGER_ROLE)) {
            return true;
        }
        if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.PAYROLL_PROCESSOR_ROLE)) {
            return true;
        }
        if (roleName.equals(edu.iu.uis.hr.tk.directory.Utility.SUPERVISOR_ROLE)) {
            return true;
        }
        return false;
    }
    
    private boolean isInterfaceManager(String roleName) {
        return hasRole(roleName, getInterfaceManagerRoleNames(), true);
    }

    private boolean isLocationRole(String roleName) {
        return hasRole(roleName, getLocationRoleNames(), false);
    }
    
    private boolean isDepartmentRole(String roleName) {
        return hasRole(roleName, getDepartmentRoleNames(), false);
    }
    
    private boolean isWorkAreaRole(String roleName) {
        return hasRole(roleName, getWorkAreaRoleNames(), false);
    }
    
    private boolean isViewOnlyRole(String roleName) {
        return hasRole(roleName, getViewOnlyRoleNames(), false);
    }
    
    private boolean isDepartmentRole(String roleName, boolean checkParent) {
        return hasRole(roleName, getDepartmentRoleNames(), true);
    }
    
    private boolean isViewOnlyRole(String roleName, boolean checkParent) {
        return hasRole(roleName, getViewOnlyRoleNames(), true);
    }
    
    private boolean hasRole(String nestedRoleName, List roleOptions, boolean checkParent) {
        String parentRoleName = nestedRoleName.substring(0, nestedRoleName.lastIndexOf(edu.iu.uis.hr.Utility.UNDERSCORE));
        Iterator optionsIterator = roleOptions.iterator();
        while (optionsIterator.hasNext()) {
            Option option = (Option) optionsIterator.next();
            if (checkParent) {
                if (option.getValue().equals(nestedRoleName)) {
                    return true;
                }
            } else {
                if (option.getValue().equals(parentRoleName)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private String getLocation(String department) {
        return department.substring(0,2);
    }
}