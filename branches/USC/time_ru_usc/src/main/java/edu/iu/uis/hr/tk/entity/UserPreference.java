package edu.iu.uis.hr.tk.entity;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Checkbox;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class UserPreference extends AbstractUserPreference implements PersistentMaintainedEntity, Checkbox {
    private static final Logger LOG = Logger.getLogger(UserPreference.class);
    private boolean checked;
    
    public UserPreference() {
        super();
    }
    
    public UserPreference(String universityId, Preference preference, boolean checked) {
        super();
        setUniversityId(universityId);
        setChecked(checked);
        setPreferenceCode(preference.getPreferenceCode());
        ArrayList preferences = new ArrayList();
        preferences.add(preference);
        setPreferences(preferences);
    }

    // TODO:  Why wasn't this generated automatically by the BDO in AbstractUserPreference?
    // TODO:  --> Apparently, if there's no query cutomiser, this method isn't generated.
    // TODO:  --> Mary will ask Lin-Long about it.  
    public Preference getPreference() {
       if (edu.iu.uis.hr.entity.logic.Utility.hasValue(getPreferences())) {
           return (Preference)getPreferences().iterator().next();
       } else {
           return new Preference();
       }
    }
    
    public String getHiddenFields() { 
        return new StringBuffer(FieldNames.UNIVERSITY_ID).append(edu.iu.uis.hr.Utility.COMMA).append(FieldNames.PREFERENCE_CODE).toString();
    }
    
    public String getLabelFieldName() {
        // TODO: The line that is commented out gives an error when the user clicks save.  Why?
        //return FieldNames.DESCRIPTION;
        return "preference.description";
    }

    public String getFieldName() {
        return CHECKED;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean value) {
        this.checked = value;
    }
    
    public String getDescription() {
        if (getPreferences() != null & getPreferences().iterator().hasNext()) {
          return ((Preference) getPreferences().iterator().next()).getDescription();
        }
        return  Utility.getDefaultStringValue();
    }
    
    public void setDescription(String description) {
        if (getPreferences() != null & getPreferences().iterator().hasNext()) {
          ((Preference) getPreferences().iterator().next()).setDescription(description);
        }
    }
}