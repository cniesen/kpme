package edu.iu.uis.hr.tk.extract.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.iu.uis.hr.client.AbstractStrutsAction;
import edu.iu.uis.hr.client.StrutsAction;

public class PayrollExtractRunAction extends AbstractStrutsAction implements StrutsAction {
    protected static final String RUN_EXTRACT_DISPATCH_PARAM_VALUE = "runExtract";

    public ActionForward runExtract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	throw new UnsupportedOperationException("This has been replaced.  Have a nice day :)");
//        PayrollExtractRunForm payrollExtractRunForm = (PayrollExtractRunForm) form;
//        PayrollExtractThread payrollExtractThread = ((PayrollExtractThread) Context.getPayrollExtractThread(PayrollExtractThread.class));
//
//
//        if (payrollExtractRunForm.getPayrollExtractSchedule().getStatus().equals("P")) {
//            payrollExtractThread.setPayrollExtractSchedule(payrollExtractRunForm.getPayrollExtractSchedule());
//            payrollExtractThread.setRequest(request);
//            Thread extractThread=new Thread(payrollExtractThread);
//            extractThread.start();
//            // TODO : temporarily.  delay 10 seconds, so the session can be set in the new thread
//            // need to find a way to set the session in new thread.
//            // if the thread setsession before request is cleaned up, then it is still OK.
//            // can we clone or copy of request ?
//            Thread.sleep(10000);
//            return finish(mapping, (StrutsActionForm) form, request, response);
//        }
//        // TODO : run actionforward
//        return null;
    }
}
