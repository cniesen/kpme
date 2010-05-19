package edu.iu.uis.hr.tk.rule.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.entity.EntityMode;


public class RuleMode extends EntityMode {

    public RuleMode() {
        super();
    }

    public RuleMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
            return new StringBuffer(StrutsDispatchParamConstants.REMOVE_DISPATCH_PARAM_VALUE).toString();
    }
    
}
