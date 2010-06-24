package edu.iu.uis.hr.tk.rule.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.iu.uis.hr.tk.LocationFilterAuthorization;

public abstract class AbstractLocationRuleMaintenanceActionForm extends AbstractRuleMaintenanceActionForm {

    private static final List FILTER_AUTHORIZATION_CLASSES = new ArrayList();
    static {
        FILTER_AUTHORIZATION_CLASSES.add(LocationFilterAuthorization.class);
    }
    
    public AbstractLocationRuleMaintenanceActionForm() {
        super();
    }
    
    public Class getModeAuthorization() {
        return LocationRuleMaintenanceFormModeAuthorization.class;
    }
    
    public List getFilterAuthorizations() {
        return FILTER_AUTHORIZATION_CLASSES;
    }
    
    public Iterator getLookupValidValueFilterClasses() {
        return FILTER_AUTHORIZATION_CLASSES.iterator();
    }
}