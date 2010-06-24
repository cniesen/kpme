package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.tk.rule.entity.ShiftDifferentialRule;
import edu.iu.uis.hr.tk.timesheet.ShiftHourDetailComparator;

public class ShiftHours extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {
    private ShiftDifferentialRule shiftDifferentialRule;
    private List jobs;
    private Set hourDetails;

    public ShiftHours() {
        super();
        setHourDetails(new TreeSet(new ShiftHourDetailComparator()));
        setJobs(Utility.getDefaultListValue());
    }

    public ShiftHours(ShiftDifferentialRule shiftDifferentialRule) {
        this();
        setShiftDifferentialRule(shiftDifferentialRule);
    }

    protected Map getPropertyDescriptorMap() {
        // TODO Auto-generated method stub
        return null;
    }

    public Set getHourDetails() {
        return hourDetails;
    }

    public void setHourDetails(Set hourDetails) {
        if (hourDetails != null) {
            this.hourDetails = hourDetails;
        }
    }

    public ShiftDifferentialRule getShiftDifferentialRule() {
        return shiftDifferentialRule;
    }

    public void setShiftDifferentialRule(ShiftDifferentialRule shiftDifferentialRule) {
        if (shiftDifferentialRule != null) {
            this.shiftDifferentialRule = shiftDifferentialRule;
        }
    }

    public List getJobs() {
        return jobs;
    }

    public void setJobs(List jobs) {
        if (jobs != null) {
            this.jobs = jobs;
        }
    }

}
