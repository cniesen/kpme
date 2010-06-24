package edu.iu.uis.hr.tk.rule.client;


public abstract class AbstractSystemRuleMaintenanceActionForm extends AbstractRuleMaintenanceActionForm {
    
    public AbstractSystemRuleMaintenanceActionForm() {
        super();
    }
    
    public Class getModeAuthorization() {
        return SystemRuleMaintenanceFormModeAuthorization.class;
    }
    
}