package org.kuali.hr.time.timeblock.web;

import java.util.List;
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
		//check if user is to payroll admin
		PayrollExtractor payrollExtractor = SpringContext
				.getBean(PayrollExtractor.class);
		List<TimeBlock> timeBlocks = payrollExtractor.getAllTimeBlock();

		TimeBlockActionForm timeBlockActionForm = (TimeBlockActionForm) form;

		timeBlockActionForm.setTimeBlocks(timeBlocks);
		return super.execute(mapping, form, request, response);
	}

}
