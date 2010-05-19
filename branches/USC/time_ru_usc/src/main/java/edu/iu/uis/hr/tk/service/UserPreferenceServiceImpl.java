package edu.iu.uis.hr.tk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.CheckboxList;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.directory.entity.User;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.dataaccess.PreferenceDataAccess;
import edu.iu.uis.hr.tk.dataaccess.UserPreferenceDataAccess;
import edu.iu.uis.hr.tk.directory.Utility;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.entity.Preference;
import edu.iu.uis.hr.tk.entity.UserPreference;
import edu.iu.uis.hr.tk.entity.UserPreferences;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.log.LogPerformanceMethod;

public class UserPreferenceServiceImpl extends AbstractService implements UserPreferenceService {
	
	private UserPreferenceDataAccess userPreferenceDataAccess;
    private PreferenceDataAccess preferenceDataAccess;
    private WebUserService webUserService;
    private AssignmentService assignmentService;
    private static final Logger LOG_PERFORM = Logger.getLogger(LogPerformanceMethod.class);

    //TODO: This whole class is pretty ugly at this point - it was a mistake
    // to try shoving the preference-group concept into the existing code.
    // It'd be better to go back and implement methods that get preferences for 
    // specific preference groups - we'd then have a bit more code/data 
    // interdependence, but I think it's too much to ask to have the code
    // figure everything out on its own. This is fine for now, but it's confusing 
    // and won't work at all should we want to introduce another preference group
    // into the preference table. Of course, all classes from the form on down
    // will need to be modified as well.
    
    public UserPreferences getUserPreferences() {  
        User user = getWebUserService().getUser();  
        
        // determine which preferences are available, based on the user's role(s)
        List allPreferences = getPreferenceDataAccess().getAllPreferences();             
        HashMap preferenceMap = new HashMap();
        HashMap preferenceGroupMultiMap = new HashMap();
        Iterator preferenceIterator = allPreferences.iterator();
        while (preferenceIterator.hasNext()) {
            Preference preference = (Preference) preferenceIterator.next();
            if (user.hasRole(preference.getRole()) || (preference.getRole().equals(Utility.EMPLOYEE_ROLE) && getAssignmentService().hasAssignment(user.getUniversityId()))) {
                preferenceMap.put(preference.getPreferenceCode(), preference);
                if (preferenceGroupMultiMap.containsKey(preference.getPreferenceGroup()+preference.getRole())) {
                	preferenceGroupMultiMap.put(preference.getPreferenceGroup()+preference.getRole(), Boolean.TRUE);
                } else {
                	preferenceGroupMultiMap.put(preference.getPreferenceGroup()+preference.getRole(), Boolean.FALSE);
                }
            }
        }

        
        // determine which of these preferences the user has already selected
        Iterator preferenceMapIterator = preferenceMap.values().iterator();
        ArrayList userPreferenceList = new ArrayList();
        String locationPreference = "";
        while (preferenceMapIterator.hasNext()) {
            Preference preference = (Preference) preferenceMapIterator.next();
            UserPreference userPreference = getUserPreference(user.getUniversityId(), preference);
            if (userPreference != null && !((Boolean)preferenceGroupMultiMap.get(preference.getPreferenceGroup()+preference.getRole())).booleanValue()) {
                userPreference.setChecked(true);
                userPreferenceList.add(userPreference);
            } else if (userPreference != null && ((Boolean)preferenceGroupMultiMap.get(preference.getPreferenceGroup()+preference.getRole())).booleanValue()){
            	locationPreference = userPreference.getPreferenceCode();
            } else if (!((Boolean)preferenceGroupMultiMap.get(preference.getPreferenceGroup()+preference.getRole())).booleanValue()) {
                userPreferenceList.add(new UserPreference(user.getUniversityId(), preference, false));
            }
        }
    
        
        CheckboxList checkboxList = new CheckboxList(UserPreference.class);
        checkboxList.addAll(userPreferenceList);
        UserPreferences userPreferences = new UserPreferences();
        userPreferences.setPreferences(checkboxList);
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(locationPreference)) {
        	userPreferences.setLocationPreference(locationPreference);
        }
        return userPreferences;
    }
    
    public List getLocationPreferences() {
    	
        User user = getWebUserService().getUser();  
        
        // determine which preferences are available, based on the user's role(s)
        List allPreferences = getPreferenceDataAccess().getAllPreferences();             
        TreeMap preferenceMap = new TreeMap();
        HashMap preferenceGroupMultiMap = new HashMap();
        Iterator preferenceIterator = allPreferences.iterator();
        while (preferenceIterator.hasNext()) {
            Preference preference = (Preference) preferenceIterator.next();
            if (user.hasRole(preference.getRole()) || (preference.getRole().equals(Utility.EMPLOYEE_ROLE) && getAssignmentService().hasAssignment(user.getUniversityId()))) {
                preferenceMap.put(preference.getPreferenceCode(), preference);
                if (preferenceGroupMultiMap.containsKey(preference.getPreferenceGroup()+preference.getRole())) {
                	preferenceGroupMultiMap.put(preference.getPreferenceGroup()+preference.getRole(), Boolean.TRUE);
                } else {
                	preferenceGroupMultiMap.put(preference.getPreferenceGroup()+preference.getRole(), Boolean.FALSE);
                }
            }
        }

        // eliminate those preferences that have only one preference in their group
        preferenceIterator = preferenceMap.values().iterator();
        while (preferenceIterator.hasNext()) {
        	Preference preference = (Preference)preferenceIterator.next();
        	if (!((Boolean)preferenceGroupMultiMap.get(preference.getPreferenceGroup()+preference.getRole())).booleanValue()) {
        		preferenceIterator.remove();
        	}
        }
        

        List locationPreferenceList = new ArrayList();
        locationPreferenceList.add(new Option(UserPreferences.DEFAULT_LOCATION_PREFERENCE_VALUE,edu.iu.uis.hr.Utility.getDefaultStringValue()));
        Iterator preferenceMapIterator = preferenceMap.values().iterator();
        while (preferenceMapIterator.hasNext()) {
            Preference preference = (Preference) preferenceMapIterator.next();
            locationPreferenceList.add(new Option(preference.getPreferenceCode(),preference.getDescription()));
        }
        
        return locationPreferenceList;
    	
    }
    
    public List getUsersByPreference(String preferenceCode) {
        List usersByPreferenceList = getUserPreferenceDataAccess().getUsersByPreference(preferenceCode);  
        return usersByPreferenceList; 
    } 
    
    public void saveUserPreferences(UserPreferences userPreferences) {  
    	 getUserPreferenceDataAccess().deleteAllPreferences(getWebUserService().getUser().getUniversityId());
        Iterator iter = userPreferences.getPreferences().iterator();
        while (iter.hasNext()) {
            UserPreference userPreference = (UserPreference) iter.next();
            if (userPreference.isChecked()) {
            	getUserPreferenceDataAccess().clearCache();
            	getUserPreferenceDataAccess().store(userPreference);
            }
        }
        Preference locationPreference = getPreference(userPreferences.getLocationPreference());
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(locationPreference) && !locationPreference.equals(UserPreferences.DEFAULT_LOCATION_PREFERENCE_VALUE)) {
        	UserPreference userPreference = new UserPreference(getWebUserService().getUser().getUniversityId(),locationPreference,true);
        	getUserPreferenceDataAccess().store(userPreference);
        }
    }
    
    public UserPreference getUserPreference(String universityId, Preference preference) {
        List userPreferenceList = getUserPreferenceDataAccess().getUserPreference(universityId, preference);  
        if (userPreferenceList != null && userPreferenceList.iterator().hasNext()) {
            UserPreference userPreference = (UserPreference) userPreferenceList.iterator().next();
            List preferences = new ArrayList();
            preferences.add(preference);
            userPreference.setPreferences(preferences);
        	return userPreference;
        }
        return null;
    }
    
    @CacheResult
    public Preference getUserPreference(String universityId, String preferenceGroup) {
	    	Preference preference = getPreferenceDataAccess().getPreferenceByUserPreferenceGroup(universityId, preferenceGroup);
	    	return preference;
    }
    
	public Preference getPreference(String preferenceCode) {
		return getPreferenceDataAccess().getPreference(preferenceCode);
		
	}
    
    private UserPreferenceDataAccess getUserPreferenceDataAccess() {
        return userPreferenceDataAccess;
    }

    public void setUserPreferenceDataAccess(UserPreferenceDataAccess userPreferenceDataAccess) {
        if (userPreferenceDataAccess != null) {
            this.userPreferenceDataAccess = userPreferenceDataAccess;
        }
    }
    
    private PreferenceDataAccess getPreferenceDataAccess() {
        return preferenceDataAccess;
    }

    public void setPreferenceDataAccess(PreferenceDataAccess preferenceDataAccess) {
        if (preferenceDataAccess != null) {
            this.preferenceDataAccess = preferenceDataAccess;
        }
    }
    
    private WebUserService getWebUserService() {
        return webUserService;
    }

    public void setWebUserService(WebUserService webUserService) {
        if (webUserService != null) {
            this.webUserService = webUserService;
        }
    } 

    private AssignmentService getAssignmentService() {
        return assignmentService;
    }

    public void setAssignmentService(AssignmentService assignmentService) {
        if (assignmentService != null) {
            this.assignmentService = assignmentService;
        }
    }

}