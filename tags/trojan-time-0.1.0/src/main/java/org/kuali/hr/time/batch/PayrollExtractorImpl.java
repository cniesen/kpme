package org.kuali.hr.time.batch;

import java.util.List;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * 
 * @author Jigar
 *
 */
public class PayrollExtractorImpl implements PayrollExtractor{

	@Override
	public List<TimeBlock> getAllTimeBlock() {
		List<TimeBlock> timeBlocks = (List<TimeBlock>) KNSServiceLocator.getBusinessObjectService().findAll(TimeBlock.class);
		return timeBlocks;		
	}

	@Override
	public List<TimeBlock> getTimeBlockByDocumentId(String documentId) {
		Criteria crit = new Criteria();
		crit.addEqualTo("documentId", documentId);
		
		Query query = QueryFactory.newQuery(TimeBlock.class, crit);
		List<TimeBlock> timeBlocks = (List<TimeBlock>) PersistenceBrokerFactory.defaultPersistenceBroker().getCollectionByQuery(query);		
		
		return timeBlocks;
	}

}
