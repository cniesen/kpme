package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.SearchCriteriaMode;

public class OtherTimesheetsSearchCriteriaMode extends SearchCriteriaMode {
    public OtherTimesheetsSearchCriteriaMode() {
        super();
    }

    public OtherTimesheetsSearchCriteriaMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }
    
    public String getEventNames() {
        if (isEditable()) {
            return new StringBuffer(StrutsDispatchParamConstants.OPEN_DISPATCH_PARAM_VALUE).toString();
        }
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }
}
