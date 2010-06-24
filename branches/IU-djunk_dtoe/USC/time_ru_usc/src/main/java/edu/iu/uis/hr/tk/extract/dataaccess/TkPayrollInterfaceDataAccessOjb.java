package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.extract.entity.TkPayrollInterface;

public class TkPayrollInterfaceDataAccessOjb extends AbstractDataAccessOjb implements TkPayrollInterfaceDataAccess {
    private static final Logger LOG = Logger.getLogger(TkPayrollInterfaceDataAccessOjb.class);

    public void deleteTkPayrollInterfaces(Date payEndDate) {
        LOG.info("Started deleteTkPayrollInterfaces(Date payEndDate) Method");
        Criteria tkPayrollInterfaceCriteria = new Criteria();
        tkPayrollInterfaceCriteria.addEqualTo(FieldNames.PAY_END_DATE,payEndDate);
        LOG.info("Finished deleteTkPayrollInterfaces(Date payEndDate) Method");        
        delete(QueryFactory.newQuery(TkPayrollInterface.class, tkPayrollInterfaceCriteria));
    }


}
