package edu.iu.uis.hr.tk.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.CheckboxList;
import edu.iu.uis.hr.SubmitEvent;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractNonDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.TransientNonDatabaseEntity;
import edu.iu.uis.hr.tk.entity.logic.UserPreferencesDisplayOnlyFieldsLogic;

public class UserPreferences extends AbstractNonDatabaseEntity implements TransientNonDatabaseEntity {
    private static final String PREFERENCES = "preferences";
    public static final String DEFAULT_LOCATION_PREFERENCE_VALUE="NONE";
    public static final String LOCATION_PREFERENCE_FIELD = "locationPreference";
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor locationPrferencePropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor("description", Preference.class);
        locationPrferencePropertyDescriptor.setDisplayOnly(false);
        PROPERTY_DESCRIPTORS.put(LOCATION_PREFERENCE_FIELD, locationPrferencePropertyDescriptor);
    }
    
    private CheckboxList preferences;
    private String locationPreference;
    
    public UserPreferences() {
        super();
        setPreferences(new CheckboxList(UserPreference.class));
        setLocationPreference(DEFAULT_LOCATION_PREFERENCE_VALUE);
    }
    
    public Class getDisplayOnlyFieldsLogic() {
        return UserPreferencesDisplayOnlyFieldsLogic.class;
    }
    
    protected List getOperationalLogics(SubmitEvent submitEvent) {
        List operationalLogics = edu.iu.uis.hr.Utility.getDefaultListValue();
        return operationalLogics;
    }
    
    public Class getModeAuthorization() {
        return UserPreferenceModeAuthorization.class;
    }
    
    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public CheckboxList getPreferences() {
        return preferences;
    }

    public void setPreferences(CheckboxList preferences) {
        if (preferences != null) {
            this.preferences = preferences;
        }
    }

    public void setPreference(int index, UserPreference preference) {
        getPreferences().set(index, preference);
    }

    public UserPreference getPreference(int index) {
        return (UserPreference) getPreferences().get(index);
    }

	public String getLocationPreference() {
		return locationPreference;
	}

	public void setLocationPreference(String locationPreference) {
		this.locationPreference = locationPreference;
	}
}
