package edu.iu.uis.hr.tk.timesheet.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.SearchCriteriaMode;

public class DocumentHeaderMaintenanceMode extends SearchCriteriaMode {
    
    public DocumentHeaderMaintenanceMode() {
        super();
    }

    public DocumentHeaderMaintenanceMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }
    
    public String getEventNames() {
        if (!isEditable()) {
            return new StringBuffer(StrutsDispatchParamConstants.APPROVE_DISPATCH_PARAM_VALUE).toString();
        } 
        return edu.iu.uis.hr.Utility.getDefaultStringValue();
    }

}
