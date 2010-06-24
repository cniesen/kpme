package edu.iu.uis.hr.tk.department.client;

import edu.iu.uis.hr.client.AbstractMaintenanceLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.department.entity.DepartmentWorkAreaSearchCriteria;
import edu.iu.uis.hr.tk.department.entity.WorkArea;
import edu.iu.uis.hr.tk.department.service.DepartmentService;

public class DepartmentMaintenanceLookupForm extends AbstractMaintenanceLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new DepartmentWorkAreaSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        return new WorkArea();
    }

    public Class getMaintenanceFormClass() {
        return DepartmentMaintenanceForm.class;
    }

    public void initialize() {
        ((DepartmentWorkAreaSearchCriteria)getSearchCriteria()).setActive(true);
    }
    
    public void prepare() {
    }

    public void search() {
        setResults(((DepartmentService) getService(DepartmentService.class)).lookupDepartmentWorkAreas((DepartmentWorkAreaSearchCriteria) getSearchCriteria()));
    }

    public String getFieldToPass() {
        return ((DepartmentWorkAreaSearchCriteria)getSearchCriteria()).getDepartment();
    }

}
