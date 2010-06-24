package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.EntityMode;


public class HourDetailDistributionMode extends EntityMode {

    public HourDetailDistributionMode() {
        super();
    }

    public HourDetailDistributionMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        if (isEditable()) {
            return new StringBuffer(StrutsDispatchParamConstants.DELETE_DISPATCH_PARAM_VALUE).toString();
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }
    
}
