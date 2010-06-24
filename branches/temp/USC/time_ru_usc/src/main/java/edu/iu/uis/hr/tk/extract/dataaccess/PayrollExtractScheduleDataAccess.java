package edu.iu.uis.hr.tk.extract.dataaccess;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractScheduleSearchCriteria;

public interface PayrollExtractScheduleDataAccess extends DataAccessOjb {

    public TypedPersistentMaintainedEntityList lookupMaintainablePayrollExtractSchedules(PayrollExtractScheduleSearchCriteria payrollExtractScheduleSearchCriteria);

    public PayrollExtractSchedule getExtractScheduleToRun();

}
