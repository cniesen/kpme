package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.EntityMode;

/**
 * 
 * <p>Title: HourDetailMode</p>
 * <p>Description: Mode to control GUI actions for an HourDetail</p>
 * 
 */

public class HourDetailMode extends EntityMode {

    public HourDetailMode() {
        super();
    }

    public HourDetailMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        if (isEditable()) {
            return new StringBuffer(StrutsDispatchParamConstants.DELETE_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(StrutsDispatchParamConstants.COPY_DISPATCH_PARAM_VALUE).toString();
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }
    
}
