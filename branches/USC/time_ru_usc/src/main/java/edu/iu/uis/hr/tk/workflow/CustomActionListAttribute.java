package edu.iu.uis.hr.tk.workflow;

import org.kuali.rice.kew.actionitem.ActionItem;
import org.kuali.rice.kew.actionlist.DisplayParameters;
import org.kuali.rice.kew.actions.ActionSet;
import org.kuali.rice.kew.web.session.UserSession;

public class CustomActionListAttribute implements org.kuali.rice.kew.actionlist.CustomActionListAttribute {
	
    

    /**
	 * 
	 */
	private static final long serialVersionUID = -4256308396837625998L;

	public ActionSet getLegalActions(UserSession userSession, ActionItem actionItem) throws Exception {
        return new CustomActionListAttributeImpl().getLegalActions(userSession, actionItem);
    }

    public DisplayParameters getDocHandlerDisplayParameters(UserSession userSession, ActionItem actionItem) throws Exception {
        return new CustomActionListAttributeImpl().getDocHandlerDisplayParameters(userSession, actionItem);

    }
}
