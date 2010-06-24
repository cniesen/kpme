package edu.iu.uis.hr.tk.batch.job.runnables;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.dto.NoteDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.service.KIMServiceLocator;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;

public class PayrollExtractBatchRunnable extends AbstractTKBatchRunnable {

	private static final long serialVersionUID = -5979346844531410253L;
	
	private static final Logger LOG = Logger.getLogger(PayrollExtractBatchRunnable.class);
	
	private static final String WORKFLOW_SUPER_USER = "timekeeping-system";
	public static String DOCUMENT_AUTO_APPROVED_FYI_MSG = "Timesheet Document was auto approved by the Timekeeping System";
	public static String DOCUMENT_AUTO_DISAPPROVED_FYI_MSG = "Timesheet Document was Disapproved by the Timekeeping System";
	public static String DOCUMENT_AUTO_APPROVED_BY_SUPERVISOR_MSG = " during the Time Approver Batch Job. Please view the document Route Log for full approval details.";
	public static String DOCUMENT_AUTO_APPROVED_BY_PPROCESSOR_MSG = " during the Payroll Extract Batch Job. Please view the document Route Log for full approval details.";
	
	private static final String NODE_NAME = "PayrollProcessor";
	
	private DocumentHeader documentHeader;
	//private static String updateDocumentHeader = "update TK_DOCUMENT_HEADER_T SET document_status=? WHERE document_id = ?";
	
	private String jobMessage;
	
	public PayrollExtractBatchRunnable(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}

	public String getDataId() {
		return this.documentHeader.getDocumentId();
	}

	public void run() {
		try {
			doWork();
		} catch (Throwable t) {
			handleException(t, BatchMessageSearchCriteria.FINALIZE_TIMESHEETS_PROCESS_NAME, new Date(), this.documentHeader.getDocumentId(), this.documentHeader.getUniversityId());
		}
	}
	
	public void doWork() throws Exception {
		
		super.acquireWorkflowLock(new Long(documentHeader.getDocumentId()));
		
		IsReadyToApproveLogic logic = (IsReadyToApproveLogic) Context.getLogic(IsReadyToApproveLogic.class);
		WorkflowDocument wd = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_SUPER_USER), new Long(documentHeader.getDocumentId()));

		if (!wd.stateIsEnroute()) { //Document must be in route to go through this process
			return;
		}
		
		boolean batchWorkflowActionTakenOnDocument = false;
		Set<String> didNotApprovers = new HashSet<String>();
		List<ActionRequestDTO> fyiRequestsToDuplicate = getSupervisorFYIRequestsToDuplicate(wd);
		
		if (!logic.isReadyToApprove(documentHeader.getDocumentId())) { //auto disapproving documents
			batchWorkflowActionTakenOnDocument = true;
			didNotApprovers = getNotApproversList(wd);
			wd.superUserDisapprove("Document is not ready to approve. Disapproved by Payroll Extract Batch Job");
			this.setJobMessage("Auto Disapproved Document.");
			batchWorkflowActionTakenOnDocument = true;
			//this.updateDocumentHeader(documentHeader.getDocumentId(),DocumentConstants.DISAPPROVED);				
			
		} else { 
			if (isBlankTimesheetCanceled(documentHeader)){
				return;
			}
		}

		ActionRequestDTO[] ars = wd.getActionRequests();
		if (!batchWorkflowActionTakenOnDocument) { //finalize document
			batchWorkflowActionTakenOnDocument = true;
			didNotApprovers = getNotApproversList(wd);
			this.setJobMessage("Auto Finalizing Document.");
			//this.updateDocumentHeader(documentHeader.getDocumentId(),DocumentConstants.FINAL);
			
			
			for (ActionRequestDTO ActionRequestDTO : ars) {
				if (ActionRequestDTO.isRoleRequest() && !ActionRequestDTO.isDone()) {
					wd.superUserActionRequestApprove(ActionRequestDTO.getActionRequestId(), "Approved by Payroll Processor Approval Batch Job");
				}
			}
		}
		
		//TODO: Move note creation to a utility class
		NoteDTO noteVO = new NoteDTO();
		noteVO.setNoteCreateDate(new GregorianCalendar());
		noteVO.setRouteHeaderId(wd.getRouteHeaderId());
	    NetworkIdDTO networkIdDTO = new NetworkIdDTO(WORKFLOW_SUPER_USER);
	    
	    //get the workflow id here
		noteVO.setNoteAuthorWorkflowId(HredocServiceLocator.getHreUserGroupService().convertPrincipalNameToId(networkIdDTO.getNetworkId()));
		noteVO.setNoteText(DOCUMENT_AUTO_APPROVED_FYI_MSG + DOCUMENT_AUTO_APPROVED_BY_PPROCESSOR_MSG);
		wd.updateNote(noteVO);
		wd.saveRoutingData();
		
		//sending FYIs for documents auto-disapproved or auto-finalized
		if (batchWorkflowActionTakenOnDocument) {
			fyiNotApproversPayrollProcessors(wd, didNotApprovers, ars);
		}
	}
	
	private boolean isBlankTimesheetCanceled(DocumentHeader documentHeader) throws Exception {
		LOG.info("Confirming hours for document " + documentHeader.getDocumentId());
		boolean cancel = false;
		if (documentHeader != null  && documentHeader.getTimeBlocks().size() == 0) {
			WorkflowDocument wd = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_SUPER_USER), new Long(documentHeader.getDocumentId()));
			wd.superUserCancel("No hours recorded for this document canceled by Payroll Extract Batch Job");
			this.setJobMessage("Auto Canceled Document.  No work hours.");
			//this.updateDocumentHeader(documentHeader.getDocumentId(),DocumentConstants.CANCELLED);
			cancel = true;
		}

		return cancel;
	}
	
//	private void updateDocumentHeader(String documentId, String documentStatus) {
//		TKServiceLocator.getTkJdbcTemplate().update(updateDocumentHeader, new Object[] { documentStatus, documentId });
//	}
	
	private Set<String> getNotApproversList(WorkflowDocument wd) throws Exception {
		Integer routeLevel = wd.getDocRouteLevel();
		Set<String> didNotApprovers = new HashSet<String>();
		// payroll pro is level 2
		if (routeLevel == 2) {
			// find those that have not approved
			ActionRequestDTO[] ars = wd.getActionRequests();
			for (ActionRequestDTO actionRequestDTO : ars) {
				if (!actionRequestDTO.isDone() && !actionRequestDTO.isRoleRequest() && actionRequestDTO.getRouteLevel()==2) {
					didNotApprovers.add(actionRequestDTO.getPrincipalId());
				}
			}
		}
		return didNotApprovers;
	}
	
	private List<ActionRequestDTO> getSupervisorFYIRequestsToDuplicate(WorkflowDocument wd) throws WorkflowException  {
		List<ActionRequestDTO> actionRequestsToDuplicate = new ArrayList<ActionRequestDTO>();
		ActionRequestDTO[] ars = wd.getActionRequests();
		for (ActionRequestDTO ActionRequestDTO : ars) {
			if (!ActionRequestDTO.isDone() && !ActionRequestDTO.isRoleRequest() && ActionRequestDTO.isFyiRequest() && ActionRequestDTO.getResponsibilityDesc().contains("supervisor")) {
				actionRequestsToDuplicate.add(ActionRequestDTO);
			}
		}
		return actionRequestsToDuplicate;
	}

	private void duplicateActionRequests(WorkflowDocument wd, List<ActionRequestDTO> ars) throws WorkflowException  {
		for (ActionRequestDTO actionRequestDTO : ars) {
				wd.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_FYI_REQ, DOCUMENT_AUTO_DISAPPROVED_FYI_MSG, actionRequestDTO.getPrincipalId(), "supervisor", true);
		}
	}
			
	private void fyiNotApproversPayrollProcessors(WorkflowDocument wd, Set<String> didNotApprovers, ActionRequestDTO[] ars) throws Exception {	
		// send fyis to payroll processors that didn't approve
		for (String didNotApprover : didNotApprovers) {
			if (! this.alreadySentFYI(didNotApprover, ars, NODE_NAME)) {
				wd.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_FYI_REQ, DOCUMENT_AUTO_APPROVED_FYI_MSG, didNotApprover, "payrollProcessor", true);
			}
		}
	}
	
//	public boolean isJobFinished() {
//		boolean jobFinished = Context.getTKThreadPoolManager().isJobFinished(this);
//		if (jobFinished) {
//			runScpToKickoffExtract();
//		}
//		return jobFinished;
//	}
//	
//	public static void runScpToKickoffExtract() {
//		LOG.warn("Preparing to trigger extract via scp...");
//		@SuppressWarnings("unused")
//		Scp scp = new Scp();
//		scp.triggerExtract();
//		LOG.warn("Returned from scp.");
//
//	}
	
	@Override
	public String getJobMessage() {
		return this.jobMessage;
	}
	
	public void setJobMessage(String jobMessage) {
		this.jobMessage = jobMessage;
	}
}