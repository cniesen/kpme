package edu.iu.uis.hr.tk.department.entity;

import edu.iu.uis.hr.ModeApplicable;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.EntityMode;

public class KioskMode extends EntityMode {

    public KioskMode() {
        super();
    }

    public KioskMode(boolean editable, ModeApplicable modeApplicable) {
        super(editable, modeApplicable);
    }

    public String getEventNames() {
        return Utility.getDefaultStringValue();
    }

}
