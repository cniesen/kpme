package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.extract.entity.PayEarnings;

public class PayEarningsDataAccessOjb extends AbstractDataAccessOjb implements PayEarningsDataAccess {
    private static final Logger LOG = Logger.getLogger(PayEarningsDataAccessOjb.class);
    private static final List SINGLE_CHECK_USES=new ArrayList();
    static {
        SINGLE_CHECK_USES.add("P");
        SINGLE_CHECK_USES.add("N");
    }
    
    public ArrayList getPayEarnings(Date payEndDate, String universityId) {
        LOG.info("Started ArrayList getPayEarnings(Date payEndDate, String universityId) Method");
        Criteria payEarningsCriteria = new Criteria();
        payEarningsCriteria.addEqualTo(FieldNames.PAY_END_DATE,payEndDate);
        payEarningsCriteria.addEqualTo(FieldNames.UNIVERSITY_ID,universityId);
        payEarningsCriteria.addIn(FieldNames.SINGLE_CHECK_USE,SINGLE_CHECK_USES);
        LOG.info("Finished ArrayList getPayEarnings(Date payEndDate, String universityId) Method");
        
        return new ArrayList(getCollectionByQuery(QueryFactory.newQuery(PayEarnings.class, payEarningsCriteria)));
    }

}
