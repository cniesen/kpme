package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.PortalChannelActionFormMode;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;

public class UserPreferenceFormMode extends PortalChannelActionFormMode {

    public UserPreferenceFormMode() {
        super();
    }
    
    public UserPreferenceFormMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }
    
    public String getEventNames() {
        if (isEditable()) {
            return new StringBuffer(StrutsDispatchParamConstants.SAVE_DISPATCH_PARAM_VALUE).toString();
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }
}
