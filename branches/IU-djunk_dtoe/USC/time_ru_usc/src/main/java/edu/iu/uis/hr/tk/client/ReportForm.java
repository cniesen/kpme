package edu.iu.uis.hr.tk.client;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.client.AbstractPortalChannelActionForm;

public class ReportForm extends AbstractPortalChannelActionForm {
    public ReportForm() {
        super();
    }

    public void prepare() {
    }
    
    public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        return accessAuthorizations;
    }
}