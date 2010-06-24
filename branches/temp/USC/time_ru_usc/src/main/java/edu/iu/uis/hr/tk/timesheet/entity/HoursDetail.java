package edu.iu.uis.hr.tk.timesheet.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;

public class HoursDetail extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {

    private static final String DATE_FIELD_NAME = "date";
    private static final String DISPLAY_DATE_FIELD_NAME = "displayDate";
    
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor datePropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.PAY_END_DATE, PayCalendar.class);
        datePropertyDescriptor.setDisplayOnly(true);
        datePropertyDescriptor.setName(DATE_FIELD_NAME);
        PROPERTY_DESCRIPTORS.put(datePropertyDescriptor.getName(), datePropertyDescriptor);
        PROPERTY_DESCRIPTORS.put(DISPLAY_DATE_FIELD_NAME, new NonDatabaseStringPropertyDescriptor(DISPLAY_DATE_FIELD_NAME, true, 50));
        PROPERTY_DESCRIPTORS.put("tabStatus", new NonDatabaseStringPropertyDescriptor("tabStatus", true, 10));
    }    
    

    private Date date;
    private String displayDate;
    private TypedPersistentMaintainedEntityList hourDetails;
    private String tabStatus;
    

    public HoursDetail() {
        super();
        setHourDetails(new TypedPersistentMaintainedEntityList(HourDetail.class, this));
        setDate(new Date());
        setDisplayDate(Utility.getDefaultStringValue());
    }

    public HoursDetail(Date date) {
        this();
        setDate(date);
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    
    public Class getModeAuthorization() {
        return HoursDetailModeAuthorization.class;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
            setDisplayDate(getDisplayDate());
        }
    }

    public TypedPersistentMaintainedEntityList getHourDetails() {
        return hourDetails;
    }

    public void setHourDetails(TypedPersistentMaintainedEntityList hourDetails) {
        if (hourDetails != null) {
            this.hourDetails = hourDetails;
        }
    }

    public HourDetail getHourDetail(int index) {
        return (HourDetail)getHourDetails().get(index);
    }

    public void setHoursDetail(int index, HourDetail hourDetail) {
        if (hourDetail != null) {
            getHourDetails().add(index, hourDetail);
        }
    }

    public String getDisplayDate() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("EEE, MMMM dd, yyyy" );
        return formattedDate.format(getDate());
    }

    public void setDisplayDate(String displayDate) {
        if (displayDate != null) {
            this.displayDate = displayDate;
        }
    }

    public String getTabStatus() {
        return tabStatus;
    }

    public void setTabStatus(String tabStatus) {
        if (tabStatus != null) {
            this.tabStatus = tabStatus;
        }
    }

}
