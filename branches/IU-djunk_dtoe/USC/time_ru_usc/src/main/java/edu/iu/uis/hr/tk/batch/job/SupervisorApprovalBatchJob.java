package edu.iu.uis.hr.tk.batch.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.config.ConfigContext;

import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.batch.TKBatchJobPopulator;
import edu.iu.uis.hr.tk.batch.TKBatchRunnable;
import edu.iu.uis.hr.tk.batch.job.runnables.SupervisorApprovalBatchRunnable;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.util.TkConstants;

public class SupervisorApprovalBatchJob extends TKBatchJobPopulator {

	private static final long serialVersionUID = 3542319850409528467L;
	private static final Logger LOG = Logger.getLogger(SupervisorApprovalBatchJob.class);
	
	@Override
	public String getName() {
		return "SupervisorApprovalBatchJob";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		LOG.info("Kicking off SupervisorApprovalBatchJob batch job");
		List<DocumentHeader> documentHeaders = new ArrayList();

		if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) { 
			// processing documents for current pay period instead of next
			documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
		} else {
			documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
		}

		List<TKBatchRunnable> tkbatchRunnables = new ArrayList<TKBatchRunnable>();
		int documentsToProcessed = 0;
		
		for (DocumentHeader documentHeader : documentHeaders) {
			if (DocumentConstants.ROUTED.equals(documentHeader.getDocumentStatus())) { //adding only Routed documents
			   tkbatchRunnables.add(new SupervisorApprovalBatchRunnable(documentHeader));
			   documentsToProcessed++;
			}
		}

		LOG.info("Processing SupervisorApprovalBatchJob " + documentsToProcessed + " documents");
		
		try{
			sendKickOffEmail(documentsToProcessed);
		}catch (Exception e){
			LOG.debug("Error sending email from " + getName() + ": " + e);
		}			
		
		return tkbatchRunnables;
	}
	
	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.Document;
	}
	

//	private static final String WORKFLOW_SUPER_USER = "timekeeping-system";

//	public SupervisorApprovalBatchJob() {
//		setName("SupervisorApprovalBatchJob");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.info("Kicking off EmployeeApprovalBatchJob batch job");
//		List documentHeaders = new ArrayList();
//
//		if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) { 
//			// processing documents for current pay period instead of next
//			documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
//		} else {
//			//documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
//			documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date(new GregorianCalendar(2009,3,15).getTimeInMillis())).getPayEndDate());
//		}
//		sendKickOffEmail(documentHeaders.size());
//		return new BatchJobDescription(getName(), new java.sql.Timestamp(System.currentTimeMillis()), null, documentHeaders);
//	}
//
//	public static class SupervisorApprovalBatchRunnable extends BatchRunnable {
//		private static final long serialVersionUID = -1640489669179836733L;
//		private String jobMessage;
//		
//		@SuppressWarnings({ "unchecked", "deprecation" })
//		@Override
//		protected void doWork() throws Exception {
//
//			DocumentHeader docHeader = (DocumentHeader) getObject();
//			KEWServiceLocator.getRouteHeaderService().lockRouteHeader(new Long(docHeader.getDocumentId()), true); //preventing Optimistic Lock exception
//			
//			WorkflowDocument wd = new WorkflowDocument(new EmplIdDTO(docHeader.getUniversityId()), new Long(docHeader.getDocumentId()));
//			Integer routeLevel = wd.getDocRouteLevel();
//			LOG.info("Document has route level of " + routeLevel + " document id: " + docHeader.getDocumentId());
//
//			// supervisor is level 1
//			if (routeLevel == 1) {
//				LOG.info("Document needs auto approved " + docHeader.getDocumentId());
//
//				// find those that have not approved
//				Set<String> didNotApprovers = new HashSet<String>();
//				Map<BigDecimal, Set<String>> workAreasPayrollProcessorNotifications = new HashMap<BigDecimal, Set<String>>();
//				ActionRequestDTO[] ars = wd.getActionRequests();
//				for (ActionRequestDTO ActionRequestDTO : ars) {
//					if (!ActionRequestDTO.isDone() && !ActionRequestDTO.isRoleRequest()) {
//						didNotApprovers.add(ActionRequestDTO.getUserVO().getNetworkId());
//						//String workAreaIdFromRequest = StringUtils.substringAfterLast(ActionRequestDTO.getRoleName(), edu.iu.uis.hr.Utility.UNDERSCORE);
//						//addNetworkIdToWorkarea(workAreasPayrollProcessorNotifications, new BigDecimal(workAreaIdFromRequest), ActionRequestDTO.getUserVO().getNetworkId());
//					}
//				}
//
//				// send fyis to approvers that didn't approve
//				for (String didNotApprover : didNotApprovers) {
//					LOG.info("Sending FYI to user " + didNotApprover + " document id " + docHeader.getDocumentId());
//					wd.appSpecificRouteDocumentToUser(KEWConstants.ACTION_REQUEST_FYI_REQ, DOCUMENT_AUTO_APPROVED_FYI_MSG, new NetworkIdDTO(didNotApprover), "supervisor", true);
//				}
//
//				// rock the approval
//				for (ActionRequestDTO ActionRequestDTO : ars) {
//					if (ActionRequestDTO.isRoleRequest() && !ActionRequestDTO.isDone()) {
//						WorkflowDocument wd2 = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_SUPER_USER), new Long(docHeader.getDocumentId()));
//						wd2.superUserActionRequestApprove(ActionRequestDTO.getActionRequestId(), "Approved by Supervisor Approval Batch Job");
//					}
//				}
//				
//				if (didNotApprovers.size() > 0){ //adding note to document
//					NoteDTO noteVO = new NoteDTO();
//					noteVO.setNoteCreateDate(new GregorianCalendar());
//					noteVO.setRouteHeaderId(wd.getRouteHeaderId());
//			    	NetworkIdDTO NetworkIdDTO = new NetworkIdDTO(WORKFLOW_SUPER_USER);
//					noteVO.setNoteAuthorWorkflowId(new WorkflowInfo().getWorkflowUser(NetworkIdDTO).getWorkflowId());
//					noteVO.setNoteText(DOCUMENT_AUTO_APPROVED_FYI_MSG + DOCUMENT_AUTO_APPROVED_BY_SUPERVISOR_MSG);
//					wd.updateNote(noteVO);
//					wd.saveRoutingData();
//				}
//			}
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
//		
//	}
//
//	protected static String getPayrollProcessorFyiMessage(String startMessage, BigDecimal workareaId, Set<String> supervisorIds) {
//		String message = startMessage + " Supervisors in Work Area " + workareaId + " did not approve timesheets: ";
//		int i = 0;
//		for (String supervisorNetworkid : supervisorIds) {
//			message += supervisorNetworkid + (++i >= supervisorIds.size() ? " " : ",");
//		}
//		return message;
//	}
//
//	protected static void addNetworkIdToWorkarea(Map<BigDecimal, Set<String>> workAreasPayrollProcessorNotifications, BigDecimal workareaId, String networkid) {
//		Set<String> supervisorNetworkIds = workAreasPayrollProcessorNotifications.get(workareaId);
//		if (supervisorNetworkIds == null) {
//			workAreasPayrollProcessorNotifications.put(workareaId, new HashSet<String>());
//			supervisorNetworkIds = workAreasPayrollProcessorNotifications.get(workareaId);
//		}
//		supervisorNetworkIds.add(networkid);
//	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new SupervisorApprovalBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.Document;
//	}
	

}