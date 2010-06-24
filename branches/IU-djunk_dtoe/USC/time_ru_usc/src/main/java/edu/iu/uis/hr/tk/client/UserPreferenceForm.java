package edu.iu.uis.hr.tk.client;

import java.util.ArrayList;
import java.util.List;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.client.OpenAccessAuthorization;
import edu.iu.uis.hr.client.PortalChannelActionForm;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.client.Utility;
import edu.iu.uis.hr.tk.entity.UserPreferences;
import edu.iu.uis.hr.tk.service.UserPreferenceService;

public class UserPreferenceForm extends AbstractStrutsActionForm  implements PortalChannelActionForm {
	
    private UserPreferences userPreferences;
    
    public UserPreferenceForm() {
        super();
    }

    public Class getModeAuthorization() {
        return UserPreferenceFormModeAuthorization.class;
    }
    
    public List getAccessAuthorizations() {
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(OpenAccessAuthorization.class);
        return accessAuthorizations;
    }
    
    public void initialize() {
    }

    public void prepare() {
        setUserPreferences(((UserPreferenceService) getService(UserPreferenceService.class)).getUserPreferences());
        setFieldOptions(UserPreferences.LOCATION_PREFERENCE_FIELD, ((UserPreferenceService) getService(UserPreferenceService.class)).getLocationPreferences());
    }

    public final String getSaveUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(Utility.QUESTION_MARK).append(Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, StrutsDispatchParamConstants.SAVE_DISPATCH_PARAM_VALUE)).toString());
    }

    public void save() {
        ((UserPreferenceService) getService(UserPreferenceService.class)).saveUserPreferences(getUserPreferences());
    }
    
    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        if (userPreferences != null) {
            this.userPreferences = userPreferences;
        }
    }

    public String getApplicationBaseUrl() {
        return Context.getApplicationBaseUrl();
    }

    public String getLookupBaseUrl() {
        return Context.getLookupBaseUrl();
    }

}
