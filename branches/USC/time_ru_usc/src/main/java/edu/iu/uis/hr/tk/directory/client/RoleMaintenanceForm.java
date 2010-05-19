package edu.iu.uis.hr.tk.directory.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.position.service.PositionService;
import edu.iu.uis.hr.tk.DepartmentFilterAuthorization;
import edu.iu.uis.hr.tk.LocationFilterAuthorization;
import edu.iu.uis.hr.tk.WorkAreaFilterAuthorization;
import edu.iu.uis.hr.tk.client.MaintenanceActionFormModeAuthorization;
import edu.iu.uis.hr.tk.directory.entity.UserRoles;
import edu.iu.uis.hr.tk.directory.service.RolesService;

public class RoleMaintenanceForm extends AbstractMaintenanceActionForm {

    private static final Logger LOG = Logger.getLogger(RoleMaintenanceForm.class);
    
    private static final List FILTER_AUTHORIZATION_CLASSES = new ArrayList();
    
    static {
        FILTER_AUTHORIZATION_CLASSES.add(LocationFilterAuthorization.class);
        FILTER_AUTHORIZATION_CLASSES.add(DepartmentFilterAuthorization.class);
        FILTER_AUTHORIZATION_CLASSES.add(WorkAreaFilterAuthorization.class);
    }
    
    private UserRoles userRoles;

    public RoleMaintenanceForm() {
        super();
        setUserRoles(new UserRoles());
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        if (userRoles != null) {
            this.userRoles = userRoles;
        }
    }

    public Class getModeAuthorization() {
        return MaintenanceActionFormModeAuthorization.class;
    }
    
    public void setLookupResults(List lookupResults) {
        throw new UnsupportedOperationException("RoleMaintenanceForm does not implement setLookupResults(List lookupResults)");
    }

    protected TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        throw new UnsupportedOperationException("RoleMaintenanceForm does not implement getDefaultMaintainableList()");
    }

    public void initialize() {
        LOG.debug("initialize() - enter");
        setUserRoles(((RolesService)getService(RolesService.class)).getUserRoles(getUserRoles().getUniversityId()));
        LOG.debug("initialize() - exit");
    }

    public void prepare() {
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.LOCATION_ROLE_NAME_DROPDOWN, ((RolesService)getService(RolesService.class)).getLocationRoleNames());
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.DEPARTMENT_ROLE_NAME_DROPDOWN, ((RolesService)getService(RolesService.class)).getDepartmentRoleNames());
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.VIEW_ONLY_ROLE_NAME_DROPDOWN, ((RolesService)getService(RolesService.class)).getViewOnlyRoleNames());
        setFieldOptions(edu.iu.uis.hr.tk.directory.Utility.WORK_AREA_ROLE_NAME_DROPDOWN, ((RolesService)getService(RolesService.class)).getWorkAreaRoleNames());
        setFieldOptions(FieldNames.LOCATION, ((PositionService)getService(PositionService.class)).getDropdownLocations());
    }

    public void save() {
        ((RolesService)getService(RolesService.class)).saveRoles(getUserRoles());
    }

    public List getFilterAuthorizations() {
        return FILTER_AUTHORIZATION_CLASSES;
    }
    
    public Iterator getLookupValidValueFilterClasses() {
        return FILTER_AUTHORIZATION_CLASSES.iterator();
    }
}