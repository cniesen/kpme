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

public class PostProcessor implements PostProcessorRemote {
	
    private PostProcessorRemote wrappedPostProcessor = new PostProcessorImpl();

    public boolean doRouteStatusChange(DocumentRouteStatusChangeDTO statusChange) throws RemoteException {
        return wrappedPostProcessor.doRouteStatusChange(statusChange);
    }

    public boolean doRouteLevelChange(DocumentRouteLevelChangeDTO levelChange) throws RemoteException {
        return wrappedPostProcessor.doRouteLevelChange(levelChange);
    }

    public boolean doDeleteRouteHeader(DeleteEventDTO event) throws RemoteException {
        return wrappedPostProcessor.doDeleteRouteHeader(event);
    }

    public boolean doActionTaken(ActionTakenEventDTO actionTaken) throws RemoteException {
        return wrappedPostProcessor.doActionTaken(actionTaken);
    }

	public boolean afterProcess(AfterProcessEventDTO arg0) throws Exception {
		return true;
	}

	public boolean beforeProcess(BeforeProcessEventDTO arg0) throws Exception {
		return true;
	}

	public Long[] getDocumentIdsToLock(DocumentLockingEventDTO arg0)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
