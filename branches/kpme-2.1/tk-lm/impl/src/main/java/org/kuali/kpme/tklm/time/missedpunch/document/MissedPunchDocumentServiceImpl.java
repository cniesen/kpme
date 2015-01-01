/**
 * Copyright 2004-2015 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.tklm.time.missedpunch.document;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kpme.core.batch.BatchJobUtil;
import org.kuali.kpme.tklm.time.missedpunch.MissedPunchBo;
import org.kuali.kpme.tklm.time.missedpunch.MissedPunchDocument;
import org.kuali.kpme.tklm.time.missedpunch.dao.MissedPunchDao;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

import java.util.ArrayList;
import java.util.List;

public class MissedPunchDocumentServiceImpl implements MissedPunchDocumentService {
	
	private static final Logger LOG = Logger.getLogger(MissedPunchDocumentServiceImpl.class);

    private MissedPunchDao missedPunchDao;
	
	@Override
	public List<MissedPunchDocument> getMissedPunchDocumentsByTimesheetDocumentId(String timesheetDocumentId) {
		List<MissedPunchDocument> missedPunchDocuments = new ArrayList<MissedPunchDocument>();
		
		List<MissedPunchBo> missedPunches = getMissedPunchDao().getMissedPunchesByTimesheetDocumentId(timesheetDocumentId);
		for (MissedPunchBo missedPunch : missedPunches) {
			MissedPunchDocument missedPunchDocument = getMissedPunchDao().getMissedPunchDocument(missedPunch.getTkMissedPunchId());
			
			if (missedPunchDocument != null) {
				missedPunchDocuments.add(missedPunchDocument);
			}
		}

		return missedPunchDocuments;
	}


    
    @Override
    public void approveMissedPunchDocument(MissedPunchDocument missedPunchDocument) {
    	String batchUserPrincipalId = BatchJobUtil.getBatchUserPrincipalId();
        
        if (batchUserPrincipalId != null) {
        	String documentNumber = missedPunchDocument.getDocumentNumber();
	        WorkflowDocument wd = WorkflowDocumentFactory.loadDocument(batchUserPrincipalId, documentNumber);
	        wd.superUserBlanketApprove("Batch job superuser approving missed punch document.");
        } else {
        	String principalName = BatchJobUtil.getBatchUserPrincipalName();
        	LOG.error("Could not approve missed punch document due to missing batch user " + principalName);
        }
    }

    @Override
    public void cancelMissedPunchDocument(MissedPunchDocument missedPunchDocument) {
        cancelMissedPunchDocumentWithDocumentId(missedPunchDocument.getDocumentNumber());
    }

    @Override
    public void cancelMissedPunchDocumentWithDocumentId(String documentId) {
        String batchUserPrincipalId = BatchJobUtil.getBatchUserPrincipalId();

        if (batchUserPrincipalId != null) {
            WorkflowDocument wd = WorkflowDocumentFactory.loadDocument(batchUserPrincipalId, documentId);
            if (StringUtils.equals(wd.getDocumentTypeName(), "MissedPunchDocumentType")
                   && wd.isEnroute()) {
                wd.superUserCancel("Document cancelled due to edit of timeblock");
            }
        } else {
            String principalName = BatchJobUtil.getBatchUserPrincipalName();
            LOG.error("Could not cancel missed punch document due to missing batch user " + principalName);
        }
    }

    public MissedPunchDao getMissedPunchDao() {
    	return missedPunchDao;
    }
    
    public void setMissedPunchDao(MissedPunchDao missedPunchDao) {
        this.missedPunchDao = missedPunchDao;
    }




	@Override
	public MissedPunchDocument getMissedPunchDocumentByMissedPunchId(String tkMissedPunchId) {
		return missedPunchDao.getMissedPunchDocument(tkMissedPunchId);
	}

    @Override
    public MissedPunchDocument getMissedPunchDocumentByDocumentId(String documentId) {
        try {
            return (MissedPunchDocument)KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderIdSessionless(documentId);
        } catch (WorkflowException e) {
            LOG.error(e);
            return null;
        }
    }

}
