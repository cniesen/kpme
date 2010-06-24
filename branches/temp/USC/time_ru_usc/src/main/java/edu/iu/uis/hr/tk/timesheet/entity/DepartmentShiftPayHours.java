package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Map;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;

public class DepartmentShiftPayHours extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private String department;
    private TypedPersistentMaintainedEntityList shiftHoursDetails;

    public DepartmentShiftPayHours() {
        super();
        setShiftHoursDetails(new TypedPersistentMaintainedEntityList(ShiftHours.class));
    }

    public DepartmentShiftPayHours(String department) {
        this();
        setDepartment(department);
    }

    protected Map getPropertyDescriptorMap() {
        return Utility.getDefaultMapValue();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department;
        }
    }

    public TypedPersistentMaintainedEntityList getShiftHoursDetails() {
        return shiftHoursDetails;
    }

    public void setShiftHoursDetails(TypedPersistentMaintainedEntityList shiftHoursDetails) {
        if (shiftHoursDetails != null) {
            this.shiftHoursDetails = shiftHoursDetails;
        }
    }

}
