package edu.iu.uis.hr.tk.extract.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.EffectivePersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.tk.extract.entity.logic.PayrollExtractScheduleDisplayOnlyFieldsLogic;
import edu.iu.uis.hr.tk.extract.entity.logic.PayrollExtractScheduleExtractDateAfterPayEndDateLogic;
import edu.iu.uis.hr.tk.extract.entity.logic.PayrollExtractScheduleExtractDateMinimumBeginDateLogic;

public class PayrollExtractSchedule extends AbstractPayrollExtractSchedule implements EffectivePersitentDatabaseMaintainedEntity, AuditedPersitentDatabaseMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(PayrollExtractSchedule.class);
    // TODO : payrollextractrun is calling setdisplayonly().  once, it works out, then remove this
    //    private boolean displayOnly;

    public PayrollExtractSchedule() {
        super();
    }

    public Class getDisplayOnlyFieldsLogic() {
        return PayrollExtractScheduleDisplayOnlyFieldsLogic.class;
    }

    public List getOperationalLogics(SaveEvent saveEvent) {
        List logics = new ArrayList();
        logics.add(PayrollExtractScheduleExtractDateMinimumBeginDateLogic.class);
        logics.add(PayrollExtractScheduleExtractDateAfterPayEndDateLogic.class);
        return logics;
    }

    public Class getModeAuthorization() {
        return PayrollExtractScheduleModeAuthorization.class;
    }

//    public boolean isDisplayOnly() {
//        return displayOnly;
//    }
//
//    public void setDisplayOnly(boolean displayOnly) {
//        this.displayOnly = displayOnly;
//    }

}