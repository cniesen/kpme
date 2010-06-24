package edu.iu.uis.hr.tk.client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.client.AbstractStrutsAction;
import edu.iu.uis.hr.client.StrutsActionForm;
import edu.iu.uis.hr.tk.entity.report.ExtractCount;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ReportAction  extends AbstractStrutsAction {

	private static final Logger LOG = Logger.getLogger(ReportAction.class);
	private static final String EXCEL_TEMPLATE_FILE_NAME = "extractReportTemplate.xls";
	private static final String REPORT_FILE_NAME_PREFIX = "payrollExtract";
	private static final String REPORT_FILE_NAME_SUFFIX = ".xls";
	private static final int NUMBER_OF_THREADS = 10;
	private static Collection enrouteCollection = Collections.synchronizedSet(new TreeSet());
	private static Collection canceledColelction = Collections.synchronizedSet(new TreeSet());
	private static Collection approvedCollection = Collections.synchronizedSet(new TreeSet());
	private Map beans = new HashMap();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!(form instanceof StrutsActionForm)) {
			throw new IllegalArgumentException("The execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) method of AbstractStrutsAction expects an ActionForm of type StrutsActionForm");
		}

		LOG.warn("Payroll extract: Starting report process.");
		// the pay end date comes in on the url as milliseconds since epoch.
		// TODO: throw an exception if parameter not found
		Date payEndDate = new Date(new Long(request.getParameter("dateAsMillis")).longValue());

		// setup the response object to download an excel document, including the pay end date in the file name.
		response.reset();
		// struggling with ensuring the user gets prompted to save the doc
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Expires", "Mon, 1 Jan 2006 05:00:00 GMT");//in the past
		response.setContentType("application/vnd.ms-excel");
		StringBuffer sb = new StringBuffer(REPORT_FILE_NAME_PREFIX);
		DateFormat df = new SimpleDateFormat("yyyy.MMM.dd");
		sb.append("_");
		sb.append(df.format(payEndDate));
		sb.append("_").append(REPORT_FILE_NAME_SUFFIX);
		response.setHeader("Content-Disposition","attachment; filename=" + sb.toString());

		// populate the bean that'll be used by the transformer (i.e. get the data for our report)
		LOG.warn("Payroll extract: Begin population of worksheet beans.");
		LOG.warn("Payroll extract: Getting counts...");
		getCounts(payEndDate);
		LOG.warn("Payroll extract: ...Got counts.");
		LOG.warn("Payroll extract: Getting AutoApproved...");
		getAutoApproved(payEndDate);
		LOG.warn("Payroll extract: ...Got AutoApproved.");
		LOG.warn("Payroll extract: Getting canceled...");
		getRoutedToCanceled(payEndDate);
		LOG.warn("Payroll extract: Got canceled.");
		LOG.warn("Payroll extract: Getting enroute...");
		getStillEnroute(payEndDate);
		LOG.warn("Payroll extract: Got enroute.");
		LOG.warn("Payroll extract: End population of worksheet beans.");
	
		// fill the excel sheet with data
		LOG.warn("Payroll extract: Begin population of worksheet with bean data.");
		XLSTransformer transformer = new XLSTransformer();
		String templateFileName = Context.getSettingsPath() + EXCEL_TEMPLATE_FILE_NAME;
		LOG.warn("Payroll extract: Trying to open Excel sheet from " + templateFileName);
		InputStream inputStream = new FileInputStream(templateFileName);
		LOG.warn("Payroll extract: Inserting data.");
		HSSFWorkbook workbook = transformer.transformXLS(inputStream, beans);
		LOG.warn("Payroll extract: Writing output to response.");
		workbook.write(response.getOutputStream());

		// make sure all this data gets pushed to the client
		response.getOutputStream().flush();
		response.getOutputStream().close();
		LOG.warn("Payroll extract: Worksheet sent.");
		LOG.warn("Payroll extract: Done.");

		return null;
	}

	private void getCounts(Date payEndDate) {

		Map beforeCounts = new HashMap();
		Map afterCounts = new HashMap();
		Collection beforeCountCollection = new HashSet();
		Collection afterCountCollection = new HashSet();
		
		List documentHeaderSnapshotList = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getDocumentHeaderSnapshots(payEndDate);
		Iterator documentHeaderSnapshotListIterator = documentHeaderSnapshotList.iterator();
		while (documentHeaderSnapshotListIterator.hasNext()) {
			DocumentHeaderSnapshot documentHeaderSnapshot = (DocumentHeaderSnapshot)documentHeaderSnapshotListIterator.next();
			String documentHeaderSnapshotStatus = documentHeaderSnapshot.getDocumentStatus();
			if (beforeCounts.containsKey(documentHeaderSnapshotStatus)) {
				beforeCounts.put(documentHeaderSnapshotStatus, new Integer(((Integer)beforeCounts.get(documentHeaderSnapshotStatus)).intValue() + 1));
			} else {
				beforeCounts.put(documentHeaderSnapshotStatus, new Integer(1));
			}
		}
		Iterator beforeIterator = beforeCounts.keySet().iterator();
		while (beforeIterator.hasNext()) {
			String status = (String)beforeIterator.next();
			int count = ((Integer)beforeCounts.get(status)).intValue();
			beforeCountCollection.add(new ExtractCount(status,count));
		}
		beans.put("beforeStatus", beforeCountCollection);		

		List documentHeaderList = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).geDocumentHeaders(payEndDate);
		Iterator documentHeaderListIterator = documentHeaderList.iterator();
		while (documentHeaderListIterator.hasNext()) {
			DocumentHeader documentHeader = (DocumentHeader)documentHeaderListIterator.next();
			String documentHeaderStatus = documentHeader.getDocumentStatus();
			if (afterCounts.containsKey(documentHeaderStatus)) {
				afterCounts.put(documentHeaderStatus, new Integer(((Integer)afterCounts.get(documentHeaderStatus)).intValue() + 1));
			} else {
				afterCounts.put(documentHeaderStatus, new Integer(1));
			}			
		}		
		Iterator afterIterator = afterCounts.keySet().iterator();
		while (afterIterator.hasNext()) {
			String status = (String)afterIterator.next();
			int count = ((Integer)afterCounts.get(status)).intValue();
			afterCountCollection.add(new ExtractCount(status,count));
		}
		beans.put("afterStatus", afterCountCollection);		
	}
	
	private void getAutoApproved(Date payEndDate) {
		List documentHeaderList = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getDocumentHeadersAutoApproved(payEndDate);
		docListToReportBeanSet(documentHeaderList,approvedCollection);
		beans.put("autoApproved", approvedCollection);		
	}

	private void getRoutedToCanceled(Date payEndDate) {
		List documentHeaderList = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getDocumentHeadersRoutedToCanceled(payEndDate);
		docListToReportBeanSet(documentHeaderList,canceledColelction);
		beans.put("routeToCanceled", canceledColelction);
	}

	private void getStillEnroute(Date payEndDate) {
		List documentHeaderList = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getDocumentHeadersEnroute(payEndDate);
		docListToReportBeanSet(documentHeaderList,enrouteCollection);
		beans.put("stillEnroute", enrouteCollection);
	}

	private void docListToReportBeanSet(List documentHeaders, Collection col) {
		try {
			
			// threading this work reduces run time by about 70%.
			
			int numThreads = NUMBER_OF_THREADS;
			
	        List[] dividedDocumentHeaders = divideList(numThreads, documentHeaders);
	        Thread[] threads = new Thread[numThreads];
	        for (int index = 0; index < threads.length; index++) {
	        	threads[index] = new Thread(new DataGather(dividedDocumentHeaders[index], col));
	        }
	        for (int index = 0; index < threads.length; index++) {
	        	threads[index].start();
	        }
	        for (int index = 0; index < threads.length; index++) {
	        	try {
	        		threads[index].join();
	        	} catch (InterruptedException e) {
	        		LOG.error("thread error 1: " + e.getMessage());
	        	}
	        }
	        

		} catch (Exception e) {
			LOG.error("thread error 2: " + e.getMessage());
		}
	}

    private List[] divideList(int numDivisions, List list) {
    	List[] dividedList = new List[numDivisions];
    	for (int index = 0; index < numDivisions; index++) {
    		dividedList[index] = new ArrayList();
    	}
    	int index = 0;
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			dividedList[index++ % numDivisions].add(iterator.next());
		}
    	return dividedList;
    }
    
	private class DataGather implements Runnable {
		
		private final List documentHeaderList;
		private Collection col;
		
		public DataGather(List documentHeaderList, Collection col) {
			this.documentHeaderList = documentHeaderList;
			this.col = col;
		}
		
		public void run() {
			((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getExtractReportInfo(documentHeaderList,col);
		}
	}
}

