package edu.iu.uis.hr.tk.extract.client;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsActionFormMode;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;

public class PayrollExtractRunFormMode extends StrutsActionFormMode {

    public PayrollExtractRunFormMode() {
        super();
    }

    public PayrollExtractRunFormMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
         return new StringBuffer(PayrollExtractRunAction.RUN_EXTRACT_DISPATCH_PARAM_VALUE).append(edu.iu.uis.hr.Utility.COMMA).append(StrutsDispatchParamConstants.EXIT_DISPATCH_PARAM_VALUE).toString();
    }
}
