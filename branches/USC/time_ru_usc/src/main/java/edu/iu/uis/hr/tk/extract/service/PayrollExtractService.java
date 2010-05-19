package edu.iu.uis.hr.tk.extract.service;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractScheduleSearchCriteria;

public interface PayrollExtractService extends Service {

	public static final String PAYROLL_EXTRACT_STATUS_PENDING = "P";
	public static final String PAYROLL_EXTRACT_STATUS_INITIATED = "I";
	public static final String PAYROLL_EXTRACT_STATUS_COMPLETE = "C";

    public TypedPersistentMaintainedEntityList lookupMaintainablePayrollExtractSchedules(PayrollExtractScheduleSearchCriteria searchCriteria);

    public void savePayrollExtractSchedules(TypedPersistentMaintainedEntityList payrollExtractSchedules);

    public PayrollExtractSchedule getExtractScheduleToRun();

    public ArrayList getPayEarnings(Date payEndDate, String universityId);

    public void savePayrollInterfaces(TypedPersistentMaintainedEntityList payrollInterfaces);

    public void saveTkPayrollInterfaces(TypedPersistentMaintainedEntityList tkPayrollInterfaces);

    public void deletePayrollInterfaces(Date payEndDate);

    public void deleteTkPayrollInterfaces(Date payEndDate);
    
    public void finalizeDocuments(HttpServletRequest request);    
    
    public void setPayrollExtractSchedule(PayrollExtractSchedule payrollExtractSchedule);
}
