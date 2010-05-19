package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractSchedule;
import edu.iu.uis.hr.tk.extract.entity.PayrollExtractScheduleSearchCriteria;

public class PayrollExtractScheduleDataAccessOjb extends AbstractDataAccessOjb implements PayrollExtractScheduleDataAccess {
    private static final Logger LOG = Logger.getLogger(PayrollExtractScheduleDataAccessOjb.class);

    public TypedPersistentMaintainedEntityList lookupMaintainablePayrollExtractSchedules(PayrollExtractScheduleSearchCriteria payrollExtractScheduleSearchCriteria) {
        LOG.info("Started lookupMaintainablePayrollExtractSchedules(PayrollExtractScheduleSearchCriteria payrollExtractScheduleSearchCriteria) Method");
        Criteria lookupCriteria = new Criteria();
        if (payrollExtractScheduleSearchCriteria.getPayEndDate() != null) {
            lookupCriteria.addEqualTo(FieldNames.PAY_END_DATE, payrollExtractScheduleSearchCriteria.getPayEndDate());
        }
        getStandardLookupLogic(lookupCriteria, FieldNames.STATUS, payrollExtractScheduleSearchCriteria.getStatus());
        getLookupEffectiveLogic(lookupCriteria, PayrollExtractSchedule.class, payrollExtractScheduleSearchCriteria);
        LOG.info("Finished lookupMaintainablePayrollExtractSchedules(PayrollExtractScheduleSearchCriteria payrollExtractScheduleSearchCriteria) Method");
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(PayrollExtractSchedule.class, lookupCriteria));
    }

    public PayrollExtractSchedule getExtractScheduleToRun() {

        Criteria extractCriteria = getCurrentRecordsCriteria(PayrollExtractSchedule.class, new Date());
        Criteria payEndDateSubCriteria = getCurrentRecordsCriteria(PayrollExtractSchedule.class, new Date());
        payEndDateSubCriteria.addLessThan(FieldNames.PAY_END_DATE, new Date());
        ReportQueryByCriteria payEndDateSubQuery = QueryFactory.newReportQuery(PayrollExtractSchedule.class, payEndDateSubCriteria);
        payEndDateSubQuery.setAttributes(new String[] { new StringBuffer("max(").append(FieldNames.PAY_END_DATE).append(")").toString() });
        extractCriteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDateSubQuery);
        return (PayrollExtractSchedule) getObjectByQuery(QueryFactory.newQuery(PayrollExtractSchedule.class, extractCriteria));

    }

}
