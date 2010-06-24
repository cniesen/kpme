package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.Utility;


public class HourDetailDistribution extends HourDetail{

    public HourDetailDistribution() {
        super();
        setAssignment(Utility.getDefaultStringValue());
        setEarnCode(Utility.getDefaultStringValue());
        setOvertimeEarnCode(Utility.getDefaultStringValue());
        setShiftEarnCode(Utility.getDefaultStringValue());
        setPremiumEarnCode(Utility.getDefaultStringValue());
        setUserUniversityId(Utility.getDefaultStringValue());
    }
    
    public Class getModeAuthorization() {
        return HourDetailDistributionModeAuthorization.class;
    }

}