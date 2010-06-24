package edu.iu.uis.hr.tk.extract.client;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.client.SimpleSubmittableForm;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.client.Utility;
import edu.iu.uis.hr.tk.client.InterfaceManagerAccessAuthorization;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.service.PayrollExtractService;

public class PayrollExtractRunForm extends AbstractStrutsActionForm implements SimpleSubmittableForm {
    private PayrollExtractSchedule payrollExtractSchedule;

    public PayrollExtractRunForm() {
        super();
        setPayrollExtractSchedule(new PayrollExtractSchedule());
    }

    public void initialize() {
        setPayrollExtractSchedule(((PayrollExtractService) getService(PayrollExtractService.class)).getExtractScheduleToRun());
    }

    public Class getModeAuthorization() {
        return PayrollExtractRunFormModeAuthorization.class;
    }

    public void prepare() {
        // TODO Auto-generated method stub

    }

    public PayrollExtractSchedule getPayrollExtractSchedule() {
        return payrollExtractSchedule;
    }

    public void setPayrollExtractSchedule(PayrollExtractSchedule payrollExtractSchedule) {
        if (payrollExtractSchedule != null) {
            this.payrollExtractSchedule = payrollExtractSchedule;
        }
    }

    public final String getRunExtractUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(Utility.QUESTION_MARK).append(Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, PayrollExtractRunAction.RUN_EXTRACT_DISPATCH_PARAM_VALUE)).toString());
    }

    public final String getSubmitUrl() {
        return getRunExtractUrl();
    }

    public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(InterfaceManagerAccessAuthorization.class);
        return accessAuthorizations;
    }

}
