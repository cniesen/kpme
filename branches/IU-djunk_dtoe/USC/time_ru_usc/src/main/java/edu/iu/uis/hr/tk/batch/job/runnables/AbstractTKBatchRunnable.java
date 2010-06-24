package edu.iu.uis.hr.tk.batch.job.runnables;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.service.KEWServiceLocator;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.report.dataaccess.BatchMessageDataAccess;
import edu.iu.uis.hr.tk.report.entity.BatchMessage;

public abstract class AbstractTKBatchRunnable implements TKBatchRunnable {

	public static final Logger LOG = Logger.getLogger(AbstractTKBatchRunnable.class);
	
	private static final long serialVersionUID = -1983855759156091222L;
	
	private Long jobId;
	private Long batchId;
	
	public Long getJobId() {
		return jobId;
	}

	public Long getBatchId() {
		return this.batchId;
	}
	
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getJobMessage() {
		return "";
	}

	public void handleException(Throwable t, String processName, Date processDate, String documentId, String universityId) {
		try {
			BatchMessage batchMessage = new BatchMessage(null, processName, new Date(), documentId, universityId, processName + " Batch run failed: " + t.getMessage());
			getBatchMessageDataAccess().store(batchMessage);
		} catch (Throwable th) {
			LOG.error("AbstractTKBatchRunnable caught exception", th);
		}
	}

	public void acquireWorkflowLock(Long documentId) {
	
		int i = 0;
		while (i++ < 4) {
			try {
				KEWServiceLocator.getRouteHeaderService().lockRouteHeader(documentId, false); //preventing Optimistic Lock exception
				return;
			} catch (Exception e) {
				LOG.warn("Caught exception locking document " + documentId, e);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}
	
	public boolean alreadySentFYI(String networkId, ActionRequestDTO[] ars, String nodeName) {
		for (ActionRequestDTO actionRequestDTO : ars) {
			if (!actionRequestDTO.isDone() && !actionRequestDTO.isRoleRequest() && actionRequestDTO.isFyiRequest() && StringUtils.equals(actionRequestDTO.getNodeName(), nodeName)
					&& StringUtils.equals(HredocServiceLocator.getHreUserGroupService().convertPrincipalIDToPrincipalName(actionRequestDTO.getPrincipalId()), networkId)) {
				return true;
			}
		}
		return false;
	}

	protected BatchMessageDataAccess getBatchMessageDataAccess() {
		return TKServiceLocator.getBatchMessageDataAccess();
	}

}
