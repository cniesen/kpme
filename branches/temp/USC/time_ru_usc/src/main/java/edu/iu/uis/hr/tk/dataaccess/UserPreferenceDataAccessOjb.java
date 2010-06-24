package edu.iu.uis.hr.tk.dataaccess;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.entity.Preference;
import edu.iu.uis.hr.tk.entity.UserPreference;

public class UserPreferenceDataAccessOjb extends AbstractDataAccessOjb implements UserPreferenceDataAccess {

    private static final Logger LOG = Logger.getLogger(UserPreferenceDataAccessOjb.class);

    public List getUserPreferences(String universityId) {
        Criteria userPreferenceCriteria = new Criteria();
        // TODO:  why doesn't FieldNames.xxx work? (no such field SQL error)
        //userPreferenceCriteria.addColumnEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        //It doesn't work 'cause it should be addEqualTo insted of addColumnEqualTo!!
        userPreferenceCriteria.addColumnEqualTo("EMPLID", universityId);
        return getValidValuesListByQuery(QueryFactory.newQuery(UserPreference.class, userPreferenceCriteria));
    }

    public List getUserPreference(String universityId, Preference preference) {
        Criteria userPreferenceCriteria = new Criteria();
        userPreferenceCriteria.addColumnEqualTo("EMPLID", universityId);  
        userPreferenceCriteria.addColumnEqualTo("PREFERENCE_CODE", preference.getPreferenceCode());
        Query query = QueryFactory.newQuery(UserPreference.class, userPreferenceCriteria);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(), getCollectionByQuery(query)); 
    }
    
    public void deleteAllPreferences(String universityId) {
            Criteria userPreferenceCriteria = new Criteria();
            userPreferenceCriteria.addColumnEqualTo("EMPLID", universityId);
            deleteByQuery(QueryFactory.newQuery(UserPreference.class, userPreferenceCriteria));
    }
    
    /**
     * Returns list of UniversityIds based on the preference code. 
     */
    public List getUsersByPreference(String preferenceCode) {
        Criteria userPreferenceCriteria = new Criteria();
        userPreferenceCriteria.addEqualTo(FieldNames.PREFERENCE_CODE, preferenceCode);
        Query query = QueryFactory.newQuery(UserPreference.class, userPreferenceCriteria);
        return new TypedPersistentMaintainedEntityList(query.getSearchClass(), getCollectionByQuery(query)); 
    } 
    
}