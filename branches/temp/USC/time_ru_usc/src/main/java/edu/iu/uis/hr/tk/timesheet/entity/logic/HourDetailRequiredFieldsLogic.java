package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.EntityRequiredFieldsLogic;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;

public class HourDetailRequiredFieldsLogic extends EntityRequiredFieldsLogic {

//    private EarningService earningService;

    public boolean execute(Entity entity, String fieldName) {
        if (entity.getLogicExemptPropertyNames().contains(fieldName)) {
            return false;
        } else if (FieldNames.BEGIN_TIME.equals(fieldName) || FieldNames.END_TIME.equals(fieldName) || FieldNames.HOURS.equals(fieldName)) {
            if (edu.iu.uis.hr.entity.logic.Utility.hasValue(((HourDetail)entity).getEarnCode()) && getEarningService().getEarnCodesListFromEarnProgram(EarningService.HOURS_DETAIL_COLLECT_BY_HOURS_EARN_PROGRAM, ((HourDetail)entity).getHoursDetail().getDate()).contains(((HourDetail)entity).getEarnCode())) {
                return FieldNames.HOURS.equals(fieldName); 
            }
            return (FieldNames.BEGIN_TIME.equals(fieldName) || FieldNames.END_TIME.equals(fieldName));
        }
        return true;
    }

    public EarningService getEarningService() {
        return (EarningService) Context.getService(EarningService.class);
    }

//    public void setEarningService(EarningService earningService) {
//        if (earningService != null) {
//            this.earningService = earningService;
//        }
//    }
}