package edu.iu.uis.hr.tk.report.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;

public class BatchMessageDataAccessOjb extends AbstractDataAccessOjb implements BatchMessageDataAccess {
	private static final Logger LOG = Logger.getLogger(BatchMessageDataAccessOjb.class);

	public BatchMessage getBatchMessage(String messageId) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedPersistentMaintainedEntityList lookupBatchMessage(BatchMessageSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.DOCUMENT_ID, searchCriteria.getDocumentId());
        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, searchCriteria.getUniversityId());
        getStandardLookupLogic(lookupCriteria, FieldNames.PROCESS_NAME, searchCriteria.getProcessName());
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(searchCriteria.getProcessDate())) {
        	lookupCriteria.addEqualTo(FieldNames.PROCESS_DATE, searchCriteria.getProcessDate());
        }
//        ReportQueryByCriteria q = QueryFactory.newReportQuery(BatchMessage.class, lookupCriteria);
//        q.setAttributes(new String[] {	"processName", "processDate", "documentId", "universityId", "message" });
//        Iterator iter = getReportQueryIteratorByQuery(q);
//        TypedPersistentMaintainedEntityList list = new TypedPersistentMaintainedEntityList(BatchMessage.class);
//        while (iter.hasNext()) {
//        	Object[] obj = (Object[]) iter.next(); 
//        	BatchMessage bm = new BatchMessage(null, (String)obj[0], (Date)obj[1], (String)obj[2], (String)obj[3], (String)obj[4] );
//        	list.add(bm);
//        }
        //return list;
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(BatchMessage.class, lookupCriteria));
	}

}
