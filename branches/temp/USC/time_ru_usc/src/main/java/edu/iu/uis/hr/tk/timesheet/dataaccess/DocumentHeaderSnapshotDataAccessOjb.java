package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.entity.PayrollExtractReportSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshotSearchCriteria;

public class DocumentHeaderSnapshotDataAccessOjb extends AbstractDataAccessOjb implements DocumentHeaderSnapshotDataAccess {
    private static final Logger LOG = Logger.getLogger(DocumentHeaderSnapshotDataAccessOjb.class);

    public DocumentHeaderSnapshot getDocumentHeaderSnapshot(String documentId) {
        DocumentHeaderSnapshot documentHeaderSnapshot = (DocumentHeaderSnapshot)getCurrentActiveRecord(new DocumentHeaderSnapshot(documentId));
        return documentHeaderSnapshot;
    }

    public DocumentHeaderSnapshot getDocumentHeaderSnapshot(String universityId, Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        List documentHeaderSnapshots = new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeaderSnapshot.class, criteria)));        
        if (Utility.hasValue(documentHeaderSnapshots)) {
           return (DocumentHeaderSnapshot)documentHeaderSnapshots.get(0);
        }
        return null;
    }
    
    public List getDocumentHeaderSnapshots(Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        return new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeaderSnapshot.class, criteria)));
    }

	public TypedPersistentMaintainedEntityList lookupDocumentHeaderSnapshots(DocumentHeaderSnapshotSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        // allowing users searching by a specific pay end date
        if (Utility.hasValue(searchCriteria.getPayEndDate())) {
           lookupCriteria.addEqualTo(FieldNames.PAY_END_DATE, searchCriteria.getPayEndDate());
        }
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(DocumentHeaderSnapshot.class, lookupCriteria));
		
	}
	
	public TypedPersistentMaintainedEntityList lookupPayrollExtractReports(PayrollExtractReportSearchCriteria searchCriteria) {
        
		//TODO - this query should join with the extract schedule table to make sure that
		// the pay end date(s) chosen correspond to payrolls that have actually been extracted.
		// As written, a user could create an extract report *during* an extract and they'd
		// get crazy report results.
		
		// return a list of docheadersnap objects that are essentially a list of distinct pay end dates. total hack.
        Criteria crit = new Criteria();

        if (Utility.hasValue(searchCriteria.getPayEndDate())) {
            crit.addEqualTo(FieldNames.PAY_END_DATE, searchCriteria.getPayEndDate());
        }
        ReportQueryByCriteria q = QueryFactory.newReportQuery(DocumentHeaderSnapshot.class, crit, true);
        q.setAttributes(new String[] {FieldNames.PAY_END_DATE });

        Iterator iter = getPersistenceBroker().getReportQueryIteratorByQuery(q);
        TypedPersistentMaintainedEntityList results = new TypedPersistentMaintainedEntityList(DocumentHeaderSnapshot.class);
        while (iter.hasNext()) {
        	Object[] obj= (Object[])iter.next();
        	Date dt = (Date)obj[0];
        	results.add(new DocumentHeaderSnapshot(null,null,dt));
        }
        return results;
        
	}
}
