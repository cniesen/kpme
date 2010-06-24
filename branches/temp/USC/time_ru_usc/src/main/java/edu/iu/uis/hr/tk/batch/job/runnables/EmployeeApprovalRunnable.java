package edu.iu.uis.hr.tk.batch.job.runnables;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.service.WorkflowDocument;

import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.report.entity.BatchMessageSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class EmployeeApprovalRunnable extends AbstractTKBatchRunnable {

	private static final long serialVersionUID = 947137358645745377L;

	private DocumentHeader documentHeader;
	private static final Logger LOG = Logger.getLogger(EmployeeApprovalRunnable.class);

	public EmployeeApprovalRunnable(DocumentHeader documentHeader) {
		this.documentHeader = documentHeader;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		if (DocumentConstants.SAVED.equals(documentHeader.getDocumentStatus())) {

			TimesheetDocument timesheetDocument = TKServiceLocator.getTimesheetService().getTimesheetDocument(documentHeader.getDocumentId());
			List<Job> jobs = TKServiceLocator.getJobService().getJobs(documentHeader.getUniversityId(), documentHeader.getPayEndDate(), true);
			if (jobs.size() == 0 && timesheetDocument.getDocumentHeader().getTimeBlocks().size() == 0) {
				try {
					WorkflowDocument wd = new WorkflowDocument(new NetworkIdDTO("timekeeping-system"), new Long(documentHeader.getDocumentId()));
					wd.superUserCancel("No active assignments or hours recorded for this document; canceled by Employee Approval Batch Job");
				}  catch (Throwable t) {
					handleException(t, BatchMessageSearchCriteria.EMPLOYEE_APPROVAL_PROCESS_NAME, new Date(), this.documentHeader.getDocumentId(), this.documentHeader.getUniversityId());
				}
			} else {
				try {
					TKServiceLocator.getTimesheetService().approve(timesheetDocument, TKServiceLocator.getDirectoryService().getNetworkIdByEmployeeId(documentHeader.getUniversityId()));
				} catch (Throwable t) {
					handleException(t, BatchMessageSearchCriteria.EMPLOYEE_APPROVAL_PROCESS_NAME, new Date(), this.documentHeader.getDocumentId(), this.documentHeader.getUniversityId());
				}
			}
		}
	}

	public String getDataId() {
		return documentHeader.getDocumentId();
	}

	public String getJobMessage() {
		return "";
	}

}