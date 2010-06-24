package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.PortalChannelActionFormMode;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;

public class TimeBlockMaintenanceFormMode extends PortalChannelActionFormMode {

    public TimeBlockMaintenanceFormMode() {
        super();
    }
    
    public TimeBlockMaintenanceFormMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }
    
    public String getEventNames() {
        if (isEditable()) {
            return new StringBuffer(StrutsDispatchParamConstants.EXIT_DISPATCH_PARAM_VALUE).toString();
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }
}
