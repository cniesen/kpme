package edu.iu.uis.hr.tk.timesheet.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Checkbox;
import edu.iu.uis.hr.ExceptionAdapter;
import edu.iu.uis.hr.Mode;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.client.NonDatabaseBooleanPropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractNonDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TransientNonDatabaseEntity;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;

public class CheckedPayCalendarDate extends AbstractNonDatabaseEntity implements TransientNonDatabaseEntity, Checkbox, PersistentMaintainedEntity{

    private static final Logger LOG = Logger.getLogger(CheckedPayCalendarDate.class);

    private static final String DATE_FORMAT = "EEE, MMMM dd, yyyy";
    private static final String DISPLAY_DATE_FIELD_NAME = "displayDate";
    private static final String DATE_FIELD = "payCalendarDate";
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor datePropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.PAY_END_DATE, PayCalendar.class);
        datePropertyDescriptor.setDisplayOnly(false);
        datePropertyDescriptor.setName(DATE_FIELD);
        PROPERTY_DESCRIPTORS.put(datePropertyDescriptor.getName(), datePropertyDescriptor);
        PROPERTY_DESCRIPTORS.put(DISPLAY_DATE_FIELD_NAME, new NonDatabaseStringPropertyDescriptor(DISPLAY_DATE_FIELD_NAME, true, true, 50));
        PROPERTY_DESCRIPTORS.put(Checkbox.CHECKED, new NonDatabaseBooleanPropertyDescriptor(CHECKED, false, true));
    }

    private Date payCalendarDate;
    private String displayDate;
    private boolean checked;
    
    public CheckedPayCalendarDate() {
        super();
        setPayCalendarDate(new Date());
        setDisplayDate(Utility.getDefaultStringValue());
        setChecked(false);
        getMode().setEditable(true);
    }

    public CheckedPayCalendarDate(Date date) {
        this();
        setPayCalendarDate((new TimelessDate(date)).getDate());
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public String getFieldName() {
        return CHECKED;
    }

    public String getHiddenFields() {
        return DATE_FIELD;
    }

    public String getLabelFieldName() {
        return DISPLAY_DATE_FIELD_NAME;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean value) {
        this.checked = value;
    }

    public Date getPayCalendarDate() {
        return payCalendarDate;
    }

    public void setPayCalendarDate(Date date) {
        if (date != null) {
            this.payCalendarDate = date;
            SimpleDateFormat formattedDate = new SimpleDateFormat(DATE_FORMAT);
            this.displayDate = formattedDate.format(this.payCalendarDate);
        }
    }

    public String getDisplayDate() {
        SimpleDateFormat formattedDate = new SimpleDateFormat(DATE_FORMAT);
        this.displayDate = formattedDate.format(this.payCalendarDate);
        return this.displayDate;
    }

    public void setDisplayDate(String displayDate) {
        if ((displayDate != null) && (displayDate.length() > 0)) {
            this.displayDate = displayDate;
            SimpleDateFormat formattedDate = new SimpleDateFormat(DATE_FORMAT);
            try{
                this.payCalendarDate = formattedDate.parse(this.displayDate);
            } catch (ParseException e) {
                throw new ExceptionAdapter(e, LOG);
            }
        }
    }

    public Mode getMode() {
        return new CheckedPayCalendarDateMode(true, this);
    }
}
