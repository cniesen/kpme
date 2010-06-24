package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.iu.uis.hr.TimedDate;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;

public class Hours extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {

    private PayCalendar payCalendar;
    private TypedPersistentMaintainedEntityList hoursDetails;
    private Map dateToHoursDetailMap;

    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add("payCalendar");
    }
    
    public Hours() {
        super();
        setHoursDetails(new TypedPersistentMaintainedEntityList(HoursDetail.class));
        dateToHoursDetailMap = new TreeMap();
    }

    public Hours(DocumentHeader documentHeader, PayCalendar payCalendar) {
        this();
        setPayCalendar(payCalendar);
        try {
            addHoursDetails(documentHeader);
        } catch (Exception e) {
            throw new RuntimeException("Error when trying to add hour details for this document {Hours.addHoursDetails(DocumentHeader)}");
        }
    }

    protected Map getPropertyDescriptorMap() {
        return Utility.getDefaultMapValue();
    }

    public Class getModeAuthorization() {
        return HoursModeAuthorization.class;
    }
    
    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }
    
    public Map getDateToHourDetailsMap() {
        Map dateToHourDetails = new TreeMap();
        Iterator hoursDetailsItr = hoursDetails.iterator();
        while (hoursDetailsItr.hasNext()) {
            HoursDetail hoursDetail = (HoursDetail)hoursDetailsItr.next();
            dateToHourDetails.put(new TimelessDate(hoursDetail.getDate()), hoursDetail.getHourDetails());
        }
        return dateToHourDetails;
    }

    private void addHoursDetails(DocumentHeader documentHeader) {
        if (!edu.iu.uis.hr.entity.logic.Utility.hasValue(documentHeader)) {
            throw new IllegalArgumentException("Hours's addHoursDetails(DocumentHeader documentHeader) method requires a non-null DocumentHeader");
        }
        createDateToHoursDetailMap();
        Iterator timeBlocksItr = documentHeader.getTimeBlocks().iterator();
        while (timeBlocksItr.hasNext()) {
            TimeBlock timeBlock = (TimeBlock)timeBlocksItr.next();
            HoursDetail hoursDetail = ((HoursDetail)dateToHoursDetailMap.get(new TimelessDate(timeBlock.getBeginTime().getDate())));
            HourDetail hourDetail = getHourDetail(documentHeader, hoursDetail, timeBlock);
            hoursDetail.getHourDetails().add(hourDetail);
        }
        getHoursDetails().addAll(dateToHoursDetailMap.values());
    }

    private HourDetail getHourDetail(DocumentHeader documentHeader, HoursDetail hoursDetail, TimeBlock timeBlock) {
        HourDetail hourDetail = new HourDetail();
        hourDetail.setHoursDetail(hoursDetail);
        hourDetail.setAssignment(timeBlock.getAssignment(documentHeader.getUniversityId()));
        hourDetail.setEarnCode(timeBlock.getEarnCode());
        hourDetail.setBeginTime(new TimedDate(timeBlock.getBeginTime()).getTime(timeBlock.getBeginTsTz()));
        hourDetail.setEndTime(new TimedDate(timeBlock.getEndTime()).getTime(timeBlock.getEndTsTz()));
        hourDetail.setBeginTsTz(timeBlock.getBeginTsTz());
        hourDetail.setEndTsTz(timeBlock.getEndTsTz());
        hourDetail.setHours(timeBlock.getHours());
        hourDetail.setCreatedByClock(timeBlock.isCreatedByClock());
        hourDetail.setUserUniversityId(timeBlock.getUserUniversityId());
        hourDetail.setTimestamp(timeBlock.getTimestamp());
        hourDetail.setOvertimeHours(timeBlock.getOvertimeHours());
        hourDetail.setOvertimeEarnCode(timeBlock.getOvertimeEarnCode());
        hourDetail.setShiftHours(timeBlock.getShiftHours());
        hourDetail.setShiftEarnCode(timeBlock.getShiftEarnCode());
        hourDetail.setPremiumHours(timeBlock.getPremiumHours());
        hourDetail.setPremiumEarnCode(timeBlock.getPremiumEarnCode());
        hourDetail.setEntityWarnings(timeBlock.getEntityWarnings());
        
//        if ( edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getBeginTimeOriginal()) ) {
//          hourDetail.setBeginTimeOriginal((new TimedDate(timeBlock.getBeginTimeOriginal())).getTime());
//        }
//        if ( edu.iu.uis.hr.entity.logic.Utility.hasValue(timeBlock.getEndTimeOriginal()) )  {
//           hourDetail.setEndTimeOriginal((new TimedDate(timeBlock.getEndTimeOriginal())).getTime());
//        }
        return hourDetail;
    }

    private void createDateToHoursDetailMap() {
        Iterator datesItr = getPayCalendar().getDatesInPayPeriod().iterator();
        while (datesItr.hasNext()) {
            Date date = (Date)datesItr.next();
            dateToHoursDetailMap.put(new TimelessDate(date), new HoursDetail(date));
        }
    }

    public PayCalendar getPayCalendar() {
        return payCalendar;
    }

    public void setPayCalendar(PayCalendar payCalendar) {
        if (payCalendar != null) {
            this.payCalendar = payCalendar;
        }
    }

    public TypedPersistentMaintainedEntityList getHoursDetails() {
        return hoursDetails;
    }

    public void setHoursDetails(TypedPersistentMaintainedEntityList hoursDetails) {
        if (hoursDetails != null) {
            this.hoursDetails = hoursDetails;
        }
    }

    public HoursDetail getHoursDetail(int index) {
        return (HoursDetail)getHoursDetails().get(index);
    }

    public void setHoursDetail(int index, HoursDetail hoursDetail) {
        if (hoursDetail != null) {
            getHoursDetails().add(index, hoursDetail);
        }
    }

    public Map getDateToHoursDetailMap() {
        return dateToHoursDetailMap;
    }

    
}
