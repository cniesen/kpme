package edu.iu.uis.hr.tk.dataaccess;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.dataaccess.Utility;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.entity.Preference;
import edu.iu.uis.hr.tk.entity.UserPreference;

public class PreferenceDataAccessOjb extends AbstractDataAccessOjb implements PreferenceDataAccess {
    
    private static final Logger LOG = Logger.getLogger(UserPreferenceDataAccessOjb.class);

    @CacheResult
    public List getAllPreferences() {
        Criteria preferenceCriteria = new Criteria();    
        // TODO:  Is this the right way to get all current (effdt) rows?
        Utility.getCurrentEffectiveDateLogic(preferenceCriteria, Preference.class, new Date());
        QueryByCriteria query = QueryFactory.newQuery(Preference.class, preferenceCriteria);    
        return getValidValuesListByQuery(query);
    }
    
    @CacheResult
    public List getPreferencesForRole(String role) {
        Criteria preferenceCriteria = new Criteria();
        preferenceCriteria.addColumnEqualTo(FieldNames.ROLE, role);
        List preferences = getValidValuesListByQuery(QueryFactory.newQuery(Preference.class, preferenceCriteria));      
        return preferences;
    }

    @CacheResult
	public Preference getPreference(String preferenceCode) {
        Preference p = (Preference)getCurrentActiveRecord(new Preference(preferenceCode, new Date())); 
        return p;
	}
	
    @CacheResult
   public Preference getPreferenceByUserPreferenceGroup(String universityId, String preferenceGroup) {
        Criteria preferenceCriteria = new Criteria();
        preferenceCriteria.addEqualTo(FieldNames.PREFERENCE_GROUP, preferenceGroup);
        ReportQueryByCriteria subQuery;
        
        Criteria userPreferenceCriteria = new Criteria();
        userPreferenceCriteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId); 
        subQuery = QueryFactory.newReportQuery(UserPreference.class, userPreferenceCriteria);
        subQuery.setAttributes(new String[] { FieldNames.PREFERENCE_CODE  });
        preferenceCriteria.addIn(FieldNames.PREFERENCE_CODE, subQuery); 

        List preferences=getValidValuesListByQuery(QueryFactory.newQuery(Preference.class, preferenceCriteria));
        
        if (preferences.size() > 0) {
        	return (Preference) preferences.get(0);
        }
        
        return null;
    }	
	
}