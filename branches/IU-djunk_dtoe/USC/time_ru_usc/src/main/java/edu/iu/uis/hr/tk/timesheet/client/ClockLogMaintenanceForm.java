package edu.iu.uis.hr.tk.timesheet.client;

import java.util.List;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ClockLogMaintenanceForm extends AbstractMaintenanceActionForm {
    private TypedPersistentMaintainedEntityList clockLogs;

    public ClockLogMaintenanceForm() {
        super();
        setClockLogs(new TypedPersistentMaintainedEntityList(ClockLog.class));
    }

    public void setLookupResults(List lookupResults) {
        getClockLogs().addAll(lookupResults);
    }

    protected final TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getClockLogs();
    }

    public Class getModeAuthorization() {
        return ClockLogMaintenanceFormModeAuthorization.class;
    }

    public void prepare() {
    }

    public void save() {
       ((TimesheetService)getService(TimesheetService.class)).saveClockLogByEmployee((ClockLog)getClockLogs().get(0));
    }

    public TypedPersistentMaintainedEntityList getClockLogs() {
        return clockLogs;
    }

    public void setClockLogs(TypedPersistentMaintainedEntityList clockLogs) {
        if (clockLogs != null) {
            this.clockLogs = clockLogs;
        }
    }

    public ClockLog getClockLog(int index) {
        return (ClockLog)getClockLogs().get(index);
    }

    public void setClockLog(int index, ClockLog clockLog) {
        getClockLogs().set(index, clockLog);
    }

}