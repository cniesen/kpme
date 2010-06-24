package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.EntityMode;

public class TimeBlockMaintenanceMode extends EntityMode {

    public TimeBlockMaintenanceMode() {
        super();
    }

    public TimeBlockMaintenanceMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
            return new StringBuffer(StrutsDispatchParamConstants.DELETE_DISPATCH_PARAM_VALUE).toString();
    }
    
}
