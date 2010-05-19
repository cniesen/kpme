package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.extract.entity.PayrollInterface;

public class PayrollInterfaceDataAccessOjb extends AbstractDataAccessOjb implements PayrollInterfaceDataAccess {
    private static final Logger LOG = Logger.getLogger(PayrollInterfaceDataAccessOjb.class);
    private static final String TIME = "TIME";

    public void deletePayrollInterfaces(Date payEndDate) {
        LOG.info("Started deletePayrollInterfaces(Date payEndDate) Method");
        Criteria PayrollInterfaceCriteria = new Criteria();
        PayrollInterfaceCriteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        PayrollInterfaceCriteria.addEqualTo(FieldNames.CREATED_OPR_ID, TIME);
        LOG.info("Finished deletePayrollInterfaces(Date payEndDate) Method");
        delete(QueryFactory.newQuery(PayrollInterface.class, PayrollInterfaceCriteria));
    }
}
