package edu.iu.uis.hr.tk.department.dataaccess;

import java.util.Collection;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.department.entity.DepartmentWorkAreaSearchCriteria;

public interface DepartmentDataAccess extends DataAccess {

    public Collection getDepartmentWorkAreas(String department);

    public TypedPersistentMaintainedEntityList lookupDepartmentWorkAreas(DepartmentWorkAreaSearchCriteria searchCriteria);
}
