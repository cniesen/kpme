package edu.iu.uis.hr.tk.batch.job;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;

import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.PayrollExtractBatchRunnable;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.util.TkConstants;

public class PayrollExtractBatchJob extends TKBatchJobPopulator {
	/**
	 * 
	 */
	private static final long serialVersionUID = -713129684794635734L;

	private static final Logger LOG = Logger.getLogger(PayrollExtractBatchJob.class);


	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		LOG.info("Setting up Payroll Extract");
		PayCalendar payCalendar = new PayCalendar();

		if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) {
			// processing documents for current pay period instead of next
			payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new java.util.Date()).getDate());
		} else {
			payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date(System.currentTimeMillis()));
		}

		TKServiceLocator.getTimesheetService().takeSnapshot(payCalendar.getPayEndDate());
		List<DocumentHeader> documentHeaders = (List<DocumentHeader>)TKServiceLocator.getTimesheetService().geDocumentHeaders(payCalendar.getPayEndDate());
		
		LOG.info("Kicking jobs back to quartz");
		
		int documentsToProcessed = 0;
		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		for (DocumentHeader documentHeader : documentHeaders) {
			if (DocumentConstants.ROUTED.equals(documentHeader.getDocumentStatus())) { //adding only Routed documents
			   tkbatchRunnables.add(new PayrollExtractBatchRunnable(documentHeader));
			   documentsToProcessed++;
			}
		}

		LOG.info("Sending kickoff Email for " + documentsToProcessed + " documents.");
		
		try {
			sendKickOffEmail(documentsToProcessed);
		}catch (Exception e){
			LOG.debug("Error sending email from " + getName() + ": " + e);
		}			

		return tkbatchRunnables;
	}
	
	@Override
	public String getName() {
		return "PayrollExtractBatchJob";
	}


	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.Document;
	}

	
	
	
//	public PayrollExtractBatchJob() {
//		setName("PayrollExtractBatchJob");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.info("Setting up Payroll Extract");
//		PayCalendar payCalendar = new PayCalendar();
//
//		if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) {
//			// processing documents for current pay period instead of next
//			payCalendar = TKServiceLocator.getPayCalendarService().getPayCalendar(new TimelessDate(new java.util.Date()).getDate());
//		} else {
//			//payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date(System.currentTimeMillis()));
//			payCalendar = TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date(new GregorianCalendar(2009,3,15).getTimeInMillis()));
//		}
//
//		TKServiceLocator.getTimesheetService().takeSnapshot(payCalendar.getPayEndDate());
//		List documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(payCalendar.getPayEndDate());
//		
//		LOG.info("Sending kickoff Email for " + documentHeaders.size() + " documents.");
//		sendKickOffEmail(documentHeaders.size());
//
////		Following lines seem to have been replaced by tk_batch_jobs and tk_batch_job_entries tables. 			
////		String removeRows = "delete from TK_PAYROLL_EXT_STATUS";
////		TKServiceLocator.getTkJdbcTemplate().execute(removeRows);
////		String updatePayrollDocs = "insert into TK_PAYROLL_EXT_STATUS values(?,0)";
////		TKServiceLocator.getTkJdbcTemplate().update(updatePayrollDocs, new Object[] { documentHeaders.size() });
//		
//		LOG.info("Kicking jobs back to quartz");
//		return new BatchJobDescription(getName(), new java.sql.Timestamp(System.currentTimeMillis()), null, documentHeaders);
//	}
//
//	public static class PayrollExtractBatchRunnable extends BatchRunnable {
//		private static final long serialVersionUID = 2904979506258130276L;
//		private static String updateDocumentHeader = "update TK_DOCUMENT_HEADER_T SET document_status=? WHERE document_id = ?";
//		private String jobMessage;
//
//		@Override
//		protected void doWork() throws Exception {
//			DocumentHeader documentHeader = (DocumentHeader) getObject();
//			KEWServiceLocator.getRouteHeaderService().lockRouteHeader(new Long(documentHeader.getDocumentId()), true); //preventing Optimistic Lock exception
//			
//			boolean finalApproved = TKServiceLocator.getTimesheetService().isFinalApproved(WORKFLOW_SUPER_USER, documentHeader.getDocumentId());
//			if (finalApproved) {
//				return;
//			}
//
//			IsReadyToApproveLogic logic = (IsReadyToApproveLogic) Context.getLogic(IsReadyToApproveLogic.class);
//			WorkflowDocument wd = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_SUPER_USER), new Long(documentHeader.getDocumentId()));
//			
//			boolean batchWorkflowActionTakenOnDocument = false;
//			Set<String> didNotApprovers = new HashSet<String>();
//			List<ActionRequestDTO> fyiRequestsToDuplicate = getSupervisorFYIRequestsToDuplicate(wd);
//			
//			if (!logic.isReadyToApprove(documentHeader.getDocumentId())) { //auto disapproving documents
//				batchWorkflowActionTakenOnDocument = true;
//				didNotApprovers = getNotApproversList(wd);
//				wd.superUserDisapprove("Document is not ready to approve. Disapproved by Payroll Extract Batch Job");
//				this.setJobMessage("Auto Disapproved Document.");
//				batchWorkflowActionTakenOnDocument = true;
//				this.updateDocumentHeader(documentHeader.getDocumentId(),DocumentConstants.DISAPPROVED);				
//				
//			} else { 
//				if (isBlankTimesheetCanceled(documentHeader)){
//					return;
//				}
//			}
//
//			if (!batchWorkflowActionTakenOnDocument){ //finalize document
//				batchWorkflowActionTakenOnDocument = true;
//				didNotApprovers = getNotApproversList(wd);
//				this.setJobMessage("Auto Finalizing Document.");
//				this.updateDocumentHeader(documentHeader.getDocumentId(),DocumentConstants.FINAL);
//				
//				ActionRequestDTO[] ars = wd.getActionRequests();
//				for (ActionRequestDTO ActionRequestDTO : ars) {
//					if (ActionRequestDTO.isRoleRequest() && !ActionRequestDTO.isDone()) {
//						wd.superUserActionRequestApprove(ActionRequestDTO.getActionRequestId(), "Approved by Payroll Processor Approval Batch Job");
//					}
//				}
//				
//			}
//			
//			//TODO: Move note creation to a utility class
//			NoteDTO noteVO = new NoteDTO();
//			noteVO.setNoteCreateDate(new GregorianCalendar());
//			noteVO.setRouteHeaderId(wd.getRouteHeaderId());
//		    NetworkIdDTO NetworkIdDTO = new NetworkIdDTO(WORKFLOW_SUPER_USER);
//			noteVO.setNoteAuthorWorkflowId(new WorkflowInfo().getWorkflowUser(NetworkIdDTO).getWorkflowId());
//			noteVO.setNoteText(DOCUMENT_AUTO_APPROVED_FYI_MSG + DOCUMENT_AUTO_APPROVED_BY_PPROCESSOR_MSG);
//			wd.updateNote(noteVO);
//			wd.saveRoutingData();
//			
//			//sending FYIs for documents auto-disapproved or auto-finalized
//			if (batchWorkflowActionTakenOnDocument) {
//				fyiNotApproversPayrollProcessors(wd, didNotApprovers);
//				duplicateActionRequests(wd, fyiRequestsToDuplicate);
//			}
//		}
//
//		
//		private boolean isBlankTimesheetCanceled(DocumentHeader documentHeader) throws Exception {
//			LOG.info("Confirming hours for document " + documentHeader.getDocumentId());
//			boolean cancel = false;
//			if (documentHeader != null  && documentHeader.getTimeBlocks().size() == 0) {
//				WorkflowDocument wd = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_SUPER_USER), new Long(documentHeader.getDocumentId()));
//				wd.superUserCancel("No hours recorded for this document canceled by Payroll Extract Batch Job");
//				this.setJobMessage("Auto Canceled Document.  No work hours.");
//				this.updateDocumentHeader(documentHeader.getDocumentId(),DocumentConstants.CANCELLED);
//				cancel = true;
//			}
//
//			return cancel;
//		}
//		
//		private void updateDocumentHeader(String documentId, String documentStatus) {
//			TKServiceLocator.getTkJdbcTemplate().update(updateDocumentHeader, new Object[] { documentStatus, documentId });
//		}
//		
//		private Set<String> getNotApproversList(WorkflowDocument wd) throws Exception {
//			Integer routeLevel = wd.getDocRouteLevel();
//			Set<String> didNotApprovers = new HashSet<String>();
//			// payroll pro is level 2
//			if (routeLevel == 2) {
//				// find those that have not approved
//				ActionRequestDTO[] ars = wd.getActionRequests();
//				for (ActionRequestDTO ActionRequestDTO : ars) {
//					if (!ActionRequestDTO.isDone() && !ActionRequestDTO.isRoleRequest() && ActionRequestDTO.getRouteLevel()==2) {
//						didNotApprovers.add(ActionRequestDTO.getUserVO().getNetworkId());
//					}
//				}
//			}
//			return didNotApprovers;
//		}
//		
//		private List<ActionRequestDTO> getSupervisorFYIRequestsToDuplicate(WorkflowDocument wd) throws WorkflowException  {
//			List<ActionRequestDTO> actionRequestsToDuplicate = new ArrayList<ActionRequestDTO>();
//			ActionRequestDTO[] ars = wd.getActionRequests();
//			for (ActionRequestDTO ActionRequestDTO : ars) {
//				if (!ActionRequestDTO.isDone() && !ActionRequestDTO.isRoleRequest() && ActionRequestDTO.isFyiRequest() && ActionRequestDTO.getResponsibilityDesc().contains("supervisor")) {
//					actionRequestsToDuplicate.add(ActionRequestDTO);
//				}
//			}
//			return actionRequestsToDuplicate;
//		}
//	
//		private void duplicateActionRequests(WorkflowDocument wd, List<ActionRequestDTO> ars) throws WorkflowException  {
//			for (ActionRequestDTO ActionRequestDTO : ars) {
//					wd.appSpecificRouteDocumentToUser(KEWConstants.ACTION_REQUEST_FYI_REQ, DOCUMENT_AUTO_APPROVED_FYI_MSG, new NetworkIdDTO(ActionRequestDTO.getUserVO().getNetworkId()), "supervisor", true);
//			}
//		}
//				
//		private void fyiNotApproversPayrollProcessors(WorkflowDocument wd, Set<String> didNotApprovers) throws Exception {	
//			// send fyis to payroll processors that didn't approve
//			for (String didNotApprover : didNotApprovers) {
//				wd.appSpecificRouteDocumentToUser(KEWConstants.ACTION_REQUEST_FYI_REQ, DOCUMENT_AUTO_APPROVED_FYI_MSG, new NetworkIdDTO(didNotApprover), "payrollProcessor", true);
//			}
//		}
//		
//		@Override
//		public boolean isJobFinished() {
//			boolean jobFinished = super.isJobFinished();
//			if (jobFinished) {
//				runScpToKickoffExtract();
//			}
//			return jobFinished;
//		}
//
//		@Override
//		protected String getDataId() {
//			return ((DocumentHeader) getObject()).getDocumentId();
//		}
//		
//		@Override
//		protected String getJobMessage() {
//			return this.jobMessage;
//		}
//		
//		public void setJobMessage(String jobMessage) {
//			this.jobMessage = jobMessage;
//		}
//	}
//
//	public static void runScpToKickoffExtract() {
//		LOG.warn("Preparing to trigger extract via scp...");
//		@SuppressWarnings("unused")
//		Scp scp = new Scp();
//		// scp.triggerExtract();
//		LOG.warn("Returned from scp.");
//
//	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new PayrollExtractBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.Document;
//	}
	
}