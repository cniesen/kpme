package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;

public class HoursDetailToDistribute extends HourDetail {

    private TypedPersistentMaintainedEntityList distributedHours = new TypedPersistentMaintainedEntityList(HourDetailDistribution.class);

    public Class getModeAuthorization() {
        return HourDetailToDistributeModeAuthorization.class;
    }

    public TypedPersistentMaintainedEntityList getDistributedHours() {
        return distributedHours;
    }

    public void setDistributedHours(TypedPersistentMaintainedEntityList distributedHours) {
        if (distributedHours != null) {
            this.distributedHours = distributedHours;
        }
    }

    public HourDetail getDistributedHour(int index) {
        return (HourDetail)getDistributedHours().get(index);
    }

    public void setDistributedHour(int index, HourDetail distributedHours) {
        if (distributedHours != null) {
            getDistributedHours().add(index, distributedHours);
        }
    }

    public boolean isDisplayOnly() {
        return true;
    }

}