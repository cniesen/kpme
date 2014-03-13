package org.kuali.kpme.tklm.time.clocklog.web;

import java.util.List;

import org.kuali.kpme.core.lookup.KPMELookupableImpl;
import org.kuali.kpme.tklm.time.clocklog.ClockLog;
import org.kuali.rice.krad.inquiry.Inquirable;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.view.LookupView;
import org.kuali.rice.krad.uif.widget.Inquiry;
import org.kuali.rice.krad.web.form.LookupForm;

public class ClockLogLookupableImpl extends KPMELookupableImpl {


	@Override
    public void initSuppressAction(LookupForm lookupForm) {
        ((LookupView) lookupForm.getView()).setSuppressActions(false);
    }
		
}
