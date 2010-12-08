package org.kuali.hr.time.batch;

import java.util.List;

/**
 * @author Jigar
 */

import org.kuali.hr.time.timeblock.TimeBlock;
/**
 * 
 * @author Jigar
 *
 */
public interface PayrollExtractor {

	/**
	 * 
	 * @return list of all TimeBlock objects
	 */
	public List<TimeBlock> getAllTimeBlock();
	 /**
	  * 
	  * @param documentId 
	  * @return  Objects of TimeBlock associated with given documentId
	  */
	public List<TimeBlock> getTimeBlockByDocumentId(String documentId);
	
	
}
