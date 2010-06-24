package edu.iu.uis.hr.tk.extract.service;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.directory.service.WebUserService;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.service.EmailService;
import edu.iu.uis.hr.tk.extract.dataaccess.PayEarningsDataAccess;
import edu.iu.uis.hr.tk.extract.dataaccess.PayrollExtractScheduleDataAccess;
import edu.iu.uis.hr.tk.extract.dataaccess.PayrollInterfaceDataAccess;
import edu.iu.uis.hr.tk.extract.dataaccess.TkPayrollInterfaceDataAccess;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractScheduleSearchCriteria;
import edu.iu.uis.hr.tk.io.scp.Scp;

public class PayrollExtractServiceImpl extends AbstractService implements PayrollExtractService {

    private static final Logger LOG = Logger.getLogger(PayrollExtractServiceImpl.class);
    private PayrollExtractScheduleDataAccess payrollExtractScheduleDataAccess;
    private PayrollInterfaceDataAccess payrollInterfaceDataAccess;
    private TkPayrollInterfaceDataAccess tkPayrollInterfaceDataAccess;
    private PayEarningsDataAccess payEarningsDataAccess;
    private WebUserService webUserService;
    private EmailService emailService;    
    private PayrollExtractSchedule payrollExtractSchedule;
    private static final String STATUS_IN_PROGRESS = "I";

    public TypedPersistentMaintainedEntityList lookupMaintainablePayrollExtractSchedules(PayrollExtractScheduleSearchCriteria searchCriteria) {
        return getPayrollExtractScheduleDataAccess().lookupMaintainablePayrollExtractSchedules(searchCriteria);
    }

    public void savePayrollExtractSchedules(TypedPersistentMaintainedEntityList payrollExtractSchedules) {
        save(payrollExtractSchedules, getPayrollExtractScheduleDataAccess());
    }

    private PayrollExtractScheduleDataAccess getPayrollExtractScheduleDataAccess() {
        return payrollExtractScheduleDataAccess;
    }

    public void setPayrollExtractScheduleDataAccess(PayrollExtractScheduleDataAccess finalApprovalDataAccess) {
        if (finalApprovalDataAccess != null) {
            this.payrollExtractScheduleDataAccess = finalApprovalDataAccess;
        }
    }

    private WebUserService getWebUserService() {
        return webUserService;
    }

    public void setWebUserService(WebUserService webUserService) {
        if (webUserService != null) {
            this.webUserService = webUserService;
        }
    }

    public PayrollExtractSchedule getExtractScheduleToRun() {
        return getPayrollExtractScheduleDataAccess().getExtractScheduleToRun();
    }

    private PayEarningsDataAccess getPayEarningsDataAccess() {
        return payEarningsDataAccess;
    }

    public void setPayEarningsDataAccess(PayEarningsDataAccess payEarningsDataAccess) {
        if (payEarningsDataAccess != null) {
            this.payEarningsDataAccess = payEarningsDataAccess;
        }
    }

    public ArrayList getPayEarnings(Date payEndDate, String universityId) {
        return getPayEarningsDataAccess().getPayEarnings(payEndDate, universityId);
    }

    private PayrollInterfaceDataAccess getPayrollInterfaceDataAccess() {
        return payrollInterfaceDataAccess;
    }

    public void setPayrollInterfaceDataAccess(PayrollInterfaceDataAccess payrollInterfaceDataAccess) {
        if (payrollInterfaceDataAccess != null) {
            this.payrollInterfaceDataAccess = payrollInterfaceDataAccess;
        }
    }

    public void savePayrollInterfaces(TypedPersistentMaintainedEntityList payrollInterfacs) {
        save(payrollInterfacs, getPayrollInterfaceDataAccess());
    }

    public void deletePayrollInterfaces(Date payEndDate) {
        getPayrollInterfaceDataAccess().deletePayrollInterfaces(payEndDate);
    }

    private TkPayrollInterfaceDataAccess getTkPayrollInterfaceDataAccess() {
        return tkPayrollInterfaceDataAccess;
    }

    public void setTkPayrollInterfaceDataAccess(TkPayrollInterfaceDataAccess tkPayrollInterfaceDataAccess) {
        if (tkPayrollInterfaceDataAccess != null) {
            this.tkPayrollInterfaceDataAccess = tkPayrollInterfaceDataAccess;
        }
    }

    public void saveTkPayrollInterfaces(TypedPersistentMaintainedEntityList tkPayrollInterfacs) {
        save(tkPayrollInterfacs, getTkPayrollInterfaceDataAccess());
    }

    public void deleteTkPayrollInterfaces(Date payEndDate) {
        getTkPayrollInterfaceDataAccess().deleteTkPayrollInterfaces(payEndDate);
    }
    
    public PayrollExtractSchedule getPayrollExtractSchedule() {
        return payrollExtractSchedule;
    }

    public void setPayrollExtractSchedule(PayrollExtractSchedule payrollExtractSchedule) {
        if (payrollExtractSchedule != null) {
            this.payrollExtractSchedule = payrollExtractSchedule;
        }
    }    
    
    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        if (emailService != null) {
            this.emailService = emailService;
        }
    }   
    
    public void finalizeDocuments(HttpServletRequest request) {
        LOG.warn("Started run() Method");
        try {
            getWebUserService().setUserSession(request);
            payrollExtractSchedule.setActualBeginTime(new Timestamp()); //what's this line for?
            payrollExtractSchedule.setStatus(STATUS_IN_PROGRESS);
            TypedPersistentMaintainedEntityList typedPersistentMaintainedEntityList = new TypedPersistentMaintainedEntityList(PayrollExtractSchedule.class);
            typedPersistentMaintainedEntityList.add(payrollExtractSchedule);
            savePayrollExtractSchedules(typedPersistentMaintainedEntityList);
            LOG.warn("Preparing to trigger extract via scp...");
            Scp scp = new Scp();
            scp.triggerExtract();
        } catch (Exception e) {// send email for any exception          
            LOG.error(e.getMessage(), e);
            getEmailService().sendMail(e, LOG);
        }
        LOG.warn("Returned from scp.");
        LOG.warn("Finished run() Method");
    }

}
