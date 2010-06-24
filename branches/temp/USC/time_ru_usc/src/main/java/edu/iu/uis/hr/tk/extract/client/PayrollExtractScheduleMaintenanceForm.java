package edu.iu.uis.hr.tk.extract.client;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.service.PayrollExtractService;
import edu.iu.uis.hr.tk.service.TranslateService;

public class PayrollExtractScheduleMaintenanceForm extends AbstractMaintenanceActionForm {
    private TypedPersistentMaintainedEntityList payrollExtractSchedules;

    public PayrollExtractScheduleMaintenanceForm() {
        super();
        setPayrollExtractSchedules(new TypedPersistentMaintainedEntityList(PayrollExtractSchedule.class));
    }
    
    protected TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getPayrollExtractSchedules();
    }

    public Class getModeAuthorization() {
        return PayrollExtractScheduleMaintenanceFormModeAuthorization.class;
    }
    
    public void save() {
        ((PayrollExtractService) getService(PayrollExtractService.class)).savePayrollExtractSchedules(getPayrollExtractSchedules());
    }

    public void setLookupResults(List lookupResults) {
        getPayrollExtractSchedules().addAll(lookupResults);
    }

    public void prepare() {
        setFieldOptions(FieldNames.STATUS, ((TranslateService) getService(TranslateService.class)).getDropdownTranslates(FieldNames.STATUS, new Date()));
        setFieldOptions(Timestamp.AM_PM_FIELD, Timestamp.getAmPmValues());

    }

    public TypedPersistentMaintainedEntityList getPayrollExtractSchedules() {
        return payrollExtractSchedules;
    }

    public void setPayrollExtractSchedules(TypedPersistentMaintainedEntityList payrollExtractSchedules) {
        if (payrollExtractSchedules != null) {
            this.payrollExtractSchedules = payrollExtractSchedules;
        }
    }

    public PayrollExtractSchedule getPayrollExtractSchedule(int index) {
        return (PayrollExtractSchedule) getPayrollExtractSchedules().get(index);
    }

    public void setPayrollExtractSchedule(int index, PayrollExtractSchedule payrollExtractSchedule) {
        if (payrollExtractSchedule != null) {
            getPayrollExtractSchedules().add(index, payrollExtractSchedule);
        }
    }

}
