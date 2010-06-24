package edu.iu.uis.hr.tk.timesheet.client;

import java.util.List;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TimeBlockMaintenanceForm extends AbstractMaintenanceActionForm {
    private TypedPersistentMaintainedEntityList timeBlocks;

    public TimeBlockMaintenanceForm() {
        super();
        setTimeBlocks(new TypedPersistentMaintainedEntityList(TimeBlock.class));        
    }

    public void setLookupResults(List lookupResults) {
        getTimeBlocks().addAll(lookupResults);
    }

    protected final TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getTimeBlocks();
    }

    public Class getModeAuthorization() {
        return TimeBlockMaintenanceFormModeAuthorization.class;
    }

    public void prepare() {
    }

    public void save() {
    }

    public void delete() {
    	TimeBlock timeBlock = getTimeBlock(getIndex());
   	    ((TimesheetService)getService(TimesheetService.class)).removeTimeBlockManually(timeBlock);
    	getTimeBlocks().remove(getIndex());
    }

    public TypedPersistentMaintainedEntityList getTimeBlocks() {
        return timeBlocks;
    }

    public void setTimeBlocks(TypedPersistentMaintainedEntityList timeBlocks) {
        if (timeBlocks != null) {
            this.timeBlocks = timeBlocks;
        }
    }

    public TimeBlock getTimeBlock(int index) {
        return (TimeBlock)getTimeBlocks().get(index);
    }

    public void setTimeBlock(int index, TimeBlock timeBlock) {
        getTimeBlocks().set(index, timeBlock);
    }

}