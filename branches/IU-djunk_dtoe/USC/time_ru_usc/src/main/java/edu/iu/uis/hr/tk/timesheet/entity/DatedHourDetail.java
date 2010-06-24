package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AbstractNonDatabaseEntity;
import edu.iu.uis.hr.entity.TransientNonDatabaseEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;

/**
 * 
 * <p>Title: DatedHourDetail</p>
 * <p>Description: Composed of an HourDetail and a list of CheckedPayCalendarDates.</p>
 * 
 */

public class DatedHourDetail extends AbstractNonDatabaseEntity implements TransientNonDatabaseEntity {
    
    protected static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add("hourDetail");
        LOGIC_EXEMPT_PROPERTY_NAMES.add("checkedPayCalendarDates");
    }
    
    private HourDetail hourDetail;
    private TypedPersistentMaintainedEntityList checkedPayCalendarDates;

    protected Map getPropertyDescriptorMap() {
        return Utility.getDefaultMapValue();
    }

    public DatedHourDetail() {
        super();
        setHourDetail(new HourDetail());
        setCheckedPayCalendarDates(new TypedPersistentMaintainedEntityList(CheckedPayCalendarDate.class));
    }

    public TypedPersistentMaintainedEntityList getCheckedPayCalendarDates() {
        return checkedPayCalendarDates;
    }

    public void setCheckedPayCalendarDates(TypedPersistentMaintainedEntityList dates) {
        if (dates != null) {
            this.checkedPayCalendarDates = dates;
        }
    }

    public CheckedPayCalendarDate getCheckedPayCalendarDate(int index) {
        CheckedPayCalendarDate checkedPayCalendarDate = (CheckedPayCalendarDate)((TypedPersistentMaintainedEntityList)getCheckedPayCalendarDates()).get(index);
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getHourDetail().getHoursDetail()) && edu.iu.uis.hr.entity.logic.Utility.hasValue(checkedPayCalendarDate.getPayCalendarDate())) { 
            if(getHourDetail().getHoursDetail().getDate().compareTo(checkedPayCalendarDate.getPayCalendarDate()) == 0) {
                checkedPayCalendarDate.getMode().setEditable(false);
            }
        }
        return checkedPayCalendarDate;
    }

    public void setCheckedPayCalendarDate(int index, CheckedPayCalendarDate checkedPayCalendarDate) {
        ((TypedPersistentMaintainedEntityList)getCheckedPayCalendarDates()).add(index, checkedPayCalendarDate);
    }
    
    
    public HourDetail getHourDetail() {
        hourDetail.getMode().setEditable(false);
        return hourDetail;
    }

    public void setHourDetail(HourDetail hourDetail) {
        if (hourDetail != null) {
            this.hourDetail = hourDetail;
            hourDetail.getMode().setEditable(false);
        }
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

}
