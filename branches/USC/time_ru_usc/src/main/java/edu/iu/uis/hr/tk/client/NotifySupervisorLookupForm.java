package edu.iu.uis.hr.tk.client;

import edu.iu.uis.hr.client.AbstractLookupActionForm;
import edu.iu.uis.hr.entity.PersistentDatabaseEntity;
import edu.iu.uis.hr.entity.SearchCriteria;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.entity.NotifySupervisorSearchCriteria;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;

public class NotifySupervisorLookupForm extends AbstractLookupActionForm {
    protected SearchCriteria getNewSearchCriteria() {
        return new NotifySupervisorSearchCriteria();
    }

    public PersistentDatabaseEntity getResultTemplate() {
        // no-op (there is no result template)
        return null;
    }

    public void prepare() {
        getLabels().put("Supervisors", "TIME Approvers");        
        String universityId = ((WebUserService) getService(WebUserService.class)).getUser().getUniversityId();    
        setFieldOptions("assignment", ((AssignmentService)getService(AssignmentService.class)).getDropdownAssignments(universityId));
    }

    public void search() {
        // no-op (there is no search)
    }
    
    public final String getOpenUrl() {
        String networkId = ((WebUserService) getService(WebUserService.class)).getUser().getNetworkId();
        return "javascript:openSupervisorNotificationWindow('" + networkId + "')";
    }
}