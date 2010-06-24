package edu.iu.uis.hr.tk.workflow;

import java.rmi.RemoteException;

import org.kuali.rice.kew.dto.ActionTakenEventDTO;
import org.kuali.rice.kew.dto.AfterProcessEventDTO;
import org.kuali.rice.kew.dto.BeforeProcessEventDTO;
import org.kuali.rice.kew.dto.DeleteEventDTO;
import org.kuali.rice.kew.dto.DocumentLockingEventDTO;
import org.kuali.rice.kew.dto.DocumentRouteLevelChangeDTO;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.postprocessor.PostProcessorRemote;
import org.kuali.rice.kew.util.KEWConstants;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Document;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class PostProcessorImpl implements PostProcessorRemote {

	private static String updateDocumentHeader = "update TK_DOCUMENT_HEADER_T SET document_status=? WHERE document_id = ?";

    public boolean doRouteStatusChange(DocumentRouteStatusChangeDTO statusChange) throws RemoteException {
    	IsReadyToApproveLogic logic = (IsReadyToApproveLogic)Context.getLogic(IsReadyToApproveLogic.class);

    	boolean isReadyToApprove = logic.isReadyToApprove(statusChange.getRouteHeaderId().toString());
    	
    	// if next line fails, it'll fail in PayrollExtractThread, and in CustomActionListAttributeImpl.
        if (statusChange.getNewRouteStatus().equals(DocumentConstants.FINAL) && !isReadyToApprove) {
            throw new RuntimeException("Document is not ready to approve.");
        }
        
        if (statusChange.getNewRouteStatus().equals(KEWConstants.ROUTE_HEADER_PROCESSED_CD)) {
        	Document document = getTimesheetService().getTimesheetDocument(statusChange.getRouteHeaderId().toString());
        	getTimesheetService().finalize(document);
        }
        
        updateDocumentHeader(statusChange.getRouteHeaderId().toString(),statusChange.getNewRouteStatus());
                
        return true;
    }

    public boolean doRouteLevelChange(DocumentRouteLevelChangeDTO levelChange) throws RemoteException {
        return true;
    }

    public boolean doDeleteRouteHeader(DeleteEventDTO event) throws RemoteException {
        return false;
    }

    public boolean doActionTaken(ActionTakenEventDTO actionTaken) throws RemoteException {
        return true;
    }

    public TimesheetService getTimesheetService() {
        return Context.getTimesheetService();
    }
    
	private void updateDocumentHeader(String documentId, String documentStatus) {
		TKServiceLocator.getTkJdbcTemplate().update(updateDocumentHeader, new Object[] { documentStatus, documentId });
	}

	//TODO ..what do we need to do which these
	public boolean afterProcess(AfterProcessEventDTO arg0) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean beforeProcess(BeforeProcessEventDTO arg0) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public Long[] getDocumentIdsToLock(DocumentLockingEventDTO arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}    
}