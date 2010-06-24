package edu.iu.uis.hr.tk.service;

import java.util.List;

import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.entity.Preference;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.entity.UserPreferences;

public interface UserPreferenceService extends Service {
	public static final String DEFAULT_LOCATION_PREFERENCE_GROUP = "DEFAULT LOCATION";
	
    public UserPreferences getUserPreferences();
    
    public UserPreference getUserPreference(String universityId, Preference preference);
    
    public void saveUserPreferences(UserPreferences userPreferences);
 
    public List getUsersByPreference(String preferenceCode);
    
    public List getLocationPreferences();
    
    public Preference getPreference(String preferenceCode);
    
    public Preference getUserPreference(String universityId, String preferenceGroup);
    
}