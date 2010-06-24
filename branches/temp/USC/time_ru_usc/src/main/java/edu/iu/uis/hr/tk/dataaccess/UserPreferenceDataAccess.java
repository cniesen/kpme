package edu.iu.uis.hr.tk.dataaccess;

import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.tk.entity.Preference;

public interface UserPreferenceDataAccess extends DataAccessOjb {
    public List getUserPreferences(String networkId);

    public List getUserPreference(String universityId, Preference preference);

    public List getUsersByPreference(String preferenceCode);
    
    public void deleteAllPreferences(String universityId);
    
    public void clearCache();
    
    
    
    
}