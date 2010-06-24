package edu.iu.uis.hr.tk.batch.job.runnables;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.EmplIdDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.dto.NoteDTO;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.entity.KimPrincipal;
import org.kuali.rice.kim.service.KIMServiceLocator;

import edu.iu.hrms.hredoc.infrastructure.HredocServiceLocator;
import edu.iu.uis.hr.tk.batch.job.SupervisorApprovalBatchJob;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;

public class SupervisorApprovalBatchRunnable extends AbstractTKBatchRunnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -775180806499765571L;
	private static final Logger LOG = Logger.getLogger(SupervisorApprovalBatchJob.class);
	
	public static String DOCUMENT_AUTO_APPROVED_FYI_MSG = "Timesheet Document was auto approved by the Timekeeping System";
	public static String DOCUMENT_AUTO_APPROVED_BY_SUPERVISOR_MSG = " during the Time Approver Batch Job. Please view the document Route Log for full approval details.";
	public static String DOCUMENT_AUTO_APPROVED_BY_PPROCESSOR_MSG = " during the Payroll Extract Batch Job. Please view the document Route Log for full approval details.";
	
	private static final String WORKFLOW_SUPER_USER = "timekeeping-system";
	
	private static final String NODE_NAME = "Supervisor";
	
	private DocumentHeader documentHeader;
	
	public SupervisorApprovalBatchRunnable(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}
	
	public void run() {
		try {
			doWork();
		} catch (Throwable t) {
			handleException(t, BatchMessageSearchCriteria.SUPERVISOR_APPROVAL_PROCESS_NAME, new Date(), this.documentHeader.getDocumentId(), this.documentHeader.getUniversityId());
		}
	}
	
	@SuppressWarnings("deprecation")
	public void doWork() throws Exception {
		DocumentHeader docHeader = documentHeader;
		this.acquireWorkflowLock(new Long(docHeader.getDocumentId()));
//		KEWServiceLocator.getRouteHeaderService().lockRouteHeader(, true); //preventing Optimistic Lock exception
		
		WorkflowDocument wd = new WorkflowDocument(new EmplIdDTO(docHeader.getUniversityId()), new Long(docHeader.getDocumentId()));
		Integer routeLevel = wd.getDocRouteLevel();
		LOG.info("Document has route level of " + routeLevel + " document id: " + docHeader.getDocumentId());

		// supervisor is level 1
		if (routeLevel == 1) {
			LOG.info("Document needs auto approved " + docHeader.getDocumentId());

			// find those that have not approved
			Set<String> didNotApprovers = new HashSet<String>();
//			Map<BigDecimal, Set<String>> workAreasPayrollProcessorNotifications = new HashMap<BigDecimal, Set<String>>();
			ActionRequestDTO[] ars = wd.getActionRequests();
			for (ActionRequestDTO actionRequestDTO : ars) {
				if (!actionRequestDTO.isDone() && !actionRequestDTO.isRoleRequest()) {
					didNotApprovers.add(actionRequestDTO.getPrincipalId());
					//String workAreaIdFromRequest = StringUtils.substringAfterLast(ActionRequestDTO.getRoleName(), edu.iu.uis.hr.Utility.UNDERSCORE);
					//addNetworkIdToWorkarea(workAreasPayrollProcessorNotifications, new BigDecimal(workAreaIdFromRequest), ActionRequestDTO.getUserVO().getNetworkId());
				}
			}

			// send fyis to approvers that didn't approve
			for (String didNotApprover : didNotApprovers) {
				LOG.info("Sending FYI to user " + didNotApprover + " document id " + docHeader.getDocumentId());
				if (! this.alreadySentFYI(didNotApprover, ars, NODE_NAME)) {
					//TODO confirm this 
					wd.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_FYI_REQ, DOCUMENT_AUTO_APPROVED_FYI_MSG, didNotApprover, "supervisor", true);
				}
			}

			if (didNotApprovers.size() > 0){ //adding note to document
				NoteDTO noteVO = new NoteDTO();
				noteVO.setNoteCreateDate(new GregorianCalendar());
				noteVO.setRouteHeaderId(wd.getRouteHeaderId());
		    	NetworkIdDTO networkIdDTO = new NetworkIdDTO(WORKFLOW_SUPER_USER);
		    	noteVO.setNoteAuthorWorkflowId(HredocServiceLocator.getHreUserGroupService().convertPrincipalNameToId(networkIdDTO.getNetworkId()));
				noteVO.setNoteText(DOCUMENT_AUTO_APPROVED_FYI_MSG + DOCUMENT_AUTO_APPROVED_BY_SUPERVISOR_MSG);
				wd.updateNote(noteVO);
				wd.saveRoutingData();
			}
			
			// rock the approval
			for (ActionRequestDTO ActionRequestDTO : ars) {
				if (ActionRequestDTO.isRoleRequest() && !ActionRequestDTO.isDone()) {
					WorkflowDocument wd2 = new WorkflowDocument(new NetworkIdDTO(WORKFLOW_SUPER_USER), new Long(docHeader.getDocumentId()));
					wd2.superUserActionRequestApprove(ActionRequestDTO.getActionRequestId(), "Approved by Supervisor Approval Batch Job");
				}
			}
			
		}
	}

	public String getDataId() {
		return documentHeader.getDocumentId();
	}

	
}
