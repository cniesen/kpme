package edu.iu.uis.hr.tk.dataaccess;

import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.tk.entity.Preference;

public interface PreferenceDataAccess extends DataAccess {
    public List getAllPreferences();
    public List getPreferencesForRole(String role);
    public Preference getPreference(String preferenceCode);
    public Preference getPreferenceByUserPreferenceGroup(String universityId, String preferenceGroup);    
}