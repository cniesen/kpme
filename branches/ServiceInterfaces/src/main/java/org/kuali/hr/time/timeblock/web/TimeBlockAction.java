package org.kuali.hr.time.timeblock.web;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.Flushable;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.batch.PayrollExtractor;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.rice.kns.web.struts.action.KualiAction;

public class TimeBlockAction extends KualiAction {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PayrollExtractor payrollExtractor = SpringContext
				.getBean(PayrollExtractor.class);
		List<TimeBlock> timeBlocks = payrollExtractor.getAllTimeBlock();
		// Creating data folder
		String filePath = getServlet().getServletContext().getRealPath("/");
		File dataFolder = new File(filePath + File.separator + "data");
		if (!dataFolder.exists()) {
			dataFolder.mkdir();
		}

		BufferedWriter out = null;
		FileWriter fstream = null;
		String fileName = "TimeBlocks"
				+ Calendar.getInstance().getTimeInMillis() + ".csv";
		filePath = filePath + File.separator + "data" + File.separator
				+ fileName;
		System.out.println("**********" + filePath);
		try {
			// Create file

			System.out.println("File Written at:" + filePath);
			fstream = new FileWriter(filePath, true);
			out = new BufferedWriter(fstream);
			for (TimeBlock timeBlock : timeBlocks) {
				out.write(timeBlock.toCSVString());
			}

			// Close the output stream

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {

			close(out);

		}
		TimeBlockActionForm timeBlockActionForm = (TimeBlockActionForm) form;
		timeBlockActionForm.setFileName(fileName);
		timeBlockActionForm.setTimeBlocks(timeBlocks);
		return super.execute(mapping, form, request, response);
	}

	// public ActionForward add(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// System.out.println("Hello World123123");
	// PayrollExtractor payrollExtractor = SpringContext
	// .getBean(PayrollExtractor.class);
	// List<TimeBlock> timeBlocks = payrollExtractor.getAllTimeBlock();
	// BufferedWriter out = null;
	// FileWriter fstream = null;
	// String fileName = System.getProperty("user.home") + File.separator
	// + "TimeBlocks" + Calendar.getInstance().getTimeInMillis()
	// + ".csv";
	// try {
	// // Create file
	//
	// System.out.println("File Written at:" + fileName);
	// fstream = new FileWriter(fileName, true);
	// out = new BufferedWriter(fstream);
	// for (TimeBlock timeBlock : timeBlocks) {
	// out.write(timeBlock.toCSVString());
	// }
	//
	// // Close the output stream
	//
	// } catch (Exception e) {// Catch exception if any
	// System.err.println("Error: " + e.getMessage());
	// e.printStackTrace();
	// } finally {
	//
	// close(out);
	//
	// }
	//
	// return mapping.findForward("basic");
	// }

	public void close(Closeable closeable) {
		if (closeable == null) {
			return;
		} else {
			try {
				closeable.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
