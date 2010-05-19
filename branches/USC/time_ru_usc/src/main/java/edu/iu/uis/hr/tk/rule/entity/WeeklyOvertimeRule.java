package edu.iu.uis.hr.tk.rule.entity;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.FieldNames;

public class WeeklyOvertimeRule extends AbstractWeeklyOvertimeRule implements SystemRule, Comparable {
    private static final Logger LOG = Logger.getLogger(WeeklyOvertimeRule.class);

    public WeeklyOvertimeRule() {
        super();
    }

    public Class getModeAuthorization() {
        return SystemRuleModeAuthorization.class;
    }

    public String getLookupFieldsToReturn(String fieldName) {
        if (fieldName.equals(FieldNames.CONVERT_TO_EARN_CODE)) {
            return new StringBuffer(FieldNames.CONVERT_TO_EARN_CODE).append(edu.iu.uis.hr.Utility.COLON).append(FieldNames.EARN_CODE).toString();
        } else if (fieldName.equals(FieldNames.MAX_HOURS_EARN_PROGRAM)) {
            return new StringBuffer(FieldNames.MAX_HOURS_EARN_PROGRAM).append(edu.iu.uis.hr.Utility.COLON).append(FieldNames.EARN_PROGRAM).toString();
        } else {
            return new StringBuffer(FieldNames.CONVERT_FROM_EARN_PROGRAM).append(edu.iu.uis.hr.Utility.COLON).append(FieldNames.EARN_PROGRAM).toString();
        }
    }

    /** 
     *  Orders weekly overtime rules by Steps
     */
    public int compareTo(Object o) {
    int result = -1;
    if (o instanceof WeeklyOvertimeRule)  {
        WeeklyOvertimeRule compareObj = (WeeklyOvertimeRule) o;
        result = (this.getStep()).compareTo((compareObj.getStep()));
    }
    return result;
    }
    
}