package org.kuali.kpme.tklm.time.clocklog.web;

import java.util.List;

import org.kuali.kpme.core.lookup.KPMELookupableImpl;
import org.kuali.kpme.tklm.time.clocklog.ClockLog;
import org.kuali.rice.krad.inquiry.Inquirable;
import org.kuali.rice.krad.uif.view.LookupView;
import org.kuali.rice.krad.uif.widget.Inquiry;
import org.kuali.rice.krad.web.form.LookupForm;

public class ClockLogLookupableImpl extends KPMELookupableImpl {

	@Override
	protected String getActionUrlHref(LookupForm lookupForm, Object dataObject, String methodToCall, List<String> pkNames) {
		
		String actionUrlHref = super.getActionUrlHref(lookupForm, dataObject, methodToCall, pkNames);
        String tkClockLogId = null;
        if(dataObject instanceof ClockLog) {
            ClockLog clockLog = (ClockLog) dataObject;
            tkClockLogId = clockLog.getTkClockLogId();
        }
        
        if(tkClockLogId == null) {
            return null;
        }
        return actionUrlHref;
	}
	
	@Override
    public void initSuppressAction(LookupForm lookupForm) {
        ((LookupView) lookupForm.getView()).setSuppressActions(false);
    }
	
	public void buildInquiryLink(Object dataObject, String propertyName, Inquiry inquiry) {
        Class inquirableClass = dataObject.getClass();
        if(dataObject instanceof ClockLog) {
            ClockLog clockLog = (ClockLog) dataObject;
            if (clockLog != null) {
                inquirableClass = ClockLog.class;
            }
        }

        Inquirable inquirable = getViewDictionaryService().getInquirable(inquirableClass, inquiry.getViewName());
        if (inquirable != null) {
            if(!inquirableClass.equals(ClockLog.class)) {
                inquirable.buildInquirableLink(dataObject, propertyName, inquiry);
            }
        } else {
            // TODO: should we really not render the inquiry just because the top parent doesn't have an inquirable?
            // it is possible the path is nested and there does exist an inquiry for the property
            // inquirable not found, no inquiry link can be set
            inquiry.setRender(false);
        }
    }
	
}
