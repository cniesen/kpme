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
import edu.iu.uis.hr.tk.batch.job.runnables.EmployeeApprovalRunnable;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.util.TkConstants;

public class EmployeeApprovalBatchJobPopulator extends TKBatchJobPopulator {

	private static final long serialVersionUID = -8429861970695966985L;
	private static final Logger LOG = Logger.getLogger(EmployeeApprovalBatchJobPopulator.class);
	

	@SuppressWarnings("unchecked")
	@Override
	public List<TKBatchRunnable> getTKBatchRunnables() {
		LOG.info("Kicking off EmployeeApprovalBatchJob batch job");
		List<DocumentHeader> documentHeaders;

		if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) { 
			// routing documents for current pay period
			documentHeaders = (List<DocumentHeader>)TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
		} else { //routing documents for previous pay period
			documentHeaders = (List<DocumentHeader>)TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
		}
		
		LOG.info("Processing EmployeeApprovalBatchJob " + documentHeaders.size() + " documents");
		try {
			sendKickOffEmail(documentHeaders.size());
		}catch (Exception e){
			LOG.error("Error sending email from " + getName() + ": " + e);
		}			
		
		List<TKBatchRunnable> batchRunnables = new ArrayList<TKBatchRunnable>();
		for (DocumentHeader documentHeader : documentHeaders) {
			if (DocumentConstants.SAVED.equals(documentHeader.getDocumentStatus())){ //Processing just documents with Saved status
				batchRunnables.add(new EmployeeApprovalRunnable(documentHeader));
			}
		}
		
		return batchRunnables;
	}
	
	@Override
	public String getType() {
		return TkConstants.BatchJobTypes.Document;
	}

	@Override
	public String getName() {
		return "EmployeeApprovalBatchJob";
	}
	
//	public EmployeeApprovalBatchJob() {
//		setName("EmployeeApprovalBatchJob");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	protected BatchJobDescription setupJobs() {
//		LOG.info("Kicking off EmployeeApprovalBatchJob batch job");
//		List documentHeaders;
//
//		if (StringUtils.equals(ConfigContext.getCurrentContextConfig().getProperty("batch.current.payPeriod"), "true")) { 
//			// routing documents for current pay period
//			documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPayCalendar(new Date()).getPayEndDate());
//		} else { //routing documents for previous pay period
//			documentHeaders = TKServiceLocator.getTimesheetService().geDocumentHeaders(TKServiceLocator.getPayCalendarService().getPreviousPayCalendar(new Date()).getPayEndDate());
//		}
//		
//		//documentHeaders = documentHeaders.subList(6, 10); 
//		sendKickOffEmail(documentHeaders.size());
//		return new BatchJobDescription(getName(), new Timestamp(System.currentTimeMillis()), null, documentHeaders);
//	}
//
//	public static class EmployeeApprovalBatchRunnable extends BatchRunnable {
//		private static final long serialVersionUID = -6003334424490667073L;
//
//		@Override
//		protected void doWork() {
//			DocumentHeader docHeader = (DocumentHeader) getObject();
//			if (DocumentConstants.SAVED.equals(docHeader.getDocumentStatus())) {
//				TKServiceLocator.getTimesheetService().approve(TKServiceLocator.getTimesheetService().getTimesheetDocument(docHeader.getDocumentId()),
//						TKServiceLocator.getDirectoryService().getNetworkIdByEmployeeId(docHeader.getUniversityId()));
//				//TKServiceLocator.getTimesheetService().getTimesheetDocument(docHeader.getDocumentId());
//			}
//		}
//
//		@Override
//		protected String getDataId() {
//			return ((DocumentHeader) getObject()).getDocumentId();
//		}
//	}
//
//	@Override
//	protected BatchRunnable getBatchRunnable() {
//		return new EmployeeApprovalBatchRunnable();
//	}
//
//	@Override
//	protected String getType() {
//		return TkConstants.BatchJobTypes.Document;
//	}
}
