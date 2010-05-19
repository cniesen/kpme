package edu.iu.uis.hr.tk.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.EntityMode;

public class TopLevelEntityMode extends EntityMode  {

    public TopLevelEntityMode() {
        super();
    }
    
    public TopLevelEntityMode(boolean editable, ModeApplicable modeApplicable)  {
        super(editable, modeApplicable);
    }
    
    public String getEventNames() {
        return Utility.getDefaultStringValue();
    }
    
}
