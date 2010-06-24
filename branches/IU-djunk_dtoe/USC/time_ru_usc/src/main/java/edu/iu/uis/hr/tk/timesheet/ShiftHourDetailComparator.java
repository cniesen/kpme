package edu.iu.uis.hr.tk.timesheet;

import java.io.Serializable;
import java.util.Comparator;

import edu.iu.uis.hr.tk.timesheet.entity.ShiftHourDetail;

public class ShiftHourDetailComparator implements Comparator, Serializable {

    public int compare(Object compareToHourDetail, Object comparisonHourDetail) {
        if (!(compareToHourDetail instanceof ShiftHourDetail) || !(comparisonHourDetail instanceof ShiftHourDetail) || !compareToHourDetail.getClass().equals(comparisonHourDetail.getClass())) {
            throw new ClassCastException(new StringBuffer("One or both of the Objects presented to the compare(Object compareToHourDetail, Object comparisonHourDetail) method of ShiftHourDetailComparator was null or not an instance of ShiftHourDetail, or the types of the Objects did not match - Parameters: ").append(compareToHourDetail).append(", ").append(comparisonHourDetail).toString());
        }
        int relationship;
        relationship = ((ShiftHourDetail) compareToHourDetail).getHoursDetail().getDate().compareTo(((ShiftHourDetail) comparisonHourDetail).getHoursDetail().getDate());
        if (relationship == 0) {
            relationship = ((ShiftHourDetail) compareToHourDetail).getBeginTime().compareTo(((ShiftHourDetail) comparisonHourDetail).getBeginTime(), ((ShiftHourDetail)comparisonHourDetail).getHoursDetail().getDate());
            if (relationship == 0) {
                // TODO : do we really have to worry about this overlap situation same begtime ?
                relationship = ((ShiftHourDetail) compareToHourDetail).getEndTime().compareTo(((ShiftHourDetail) comparisonHourDetail).getEndTime(), ((ShiftHourDetail)comparisonHourDetail).getHoursDetail().getDate());
            }
        }
        return relationship;
    }
}
