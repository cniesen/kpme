package org.kuali.kpme.core.data;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.jpa.JpaCriteriaBuilder;
import org.kuali.kpme.core.api.bo.dao.EffectiveObjectDao;
import org.kuali.kpme.core.api.mo.Effective;
import org.kuali.kpme.core.api.util.HrApiConstants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.service.impl.KRADLegacyDataAdapterImpl;
import org.kuali.rice.krad.service.impl.LookupCriteriaGenerator;

import java.util.*;


public class KhrLegacyDataAdapterImpl extends KRADLegacyDataAdapterImpl {
    private LookupCriteriaGenerator lookupCriteriaGenerator;
    private EffectiveObjectDao effectiveObjectDao;

    /*@Override
    protected <T> Collection<T> performDataObjectServiceLookup(Class<T> dataObjectClass, Map<String, String> formProperties, boolean unbounded, boolean allPrimaryKeyValuesPresentAndNotWildcard, Integer searchResultsLimit) {

        if (Effective.class.isAssignableFrom(dataObjectClass)) {
            return Collections.<T>emptyList();
        }
        return super.performDataObjectServiceLookup(dataObjectClass, formProperties, unbounded, allPrimaryKeyValuesPresentAndNotWildcard, searchResultsLimit);
    }*/

    @Override
    protected <T> Collection<T> performDataObjectServiceLookup(Class<T> dataObjectClass, Map<String, String> formProperties, List<String> wildcardAsLiteralPropertyNames, boolean unbounded, boolean allPrimaryKeyValuesPresentAndNotWildcard, Integer searchResultsLimit) {
        Map<String, String> filteredProperties = new HashMap<String, String>();
        filteredProperties.putAll(formProperties);
        if (Effective.class.isAssignableFrom(dataObjectClass)) {
            String history = formProperties.get("history");
            if (StringUtils.equals("N", history)) {

                filteredProperties.remove("history");
                //get key fields
                List<String> businessKeys = new ArrayList<String>();
                try {
                    businessKeys = (List<String>) dataObjectClass.getDeclaredField(HrApiConstants.CommonFieldNames.BUSINESS_KEYS).get(dataObjectClass);
                } catch (NoSuchFieldException | IllegalAccessException ignored) {
                }
                return effectiveObjectDao.findEffectiveObjectList(dataObjectClass, businessKeys, filteredProperties, wildcardAsLiteralPropertyNames, unbounded, allPrimaryKeyValuesPresentAndNotWildcard, searchResultsLimit);
            } else if (StringUtils.equals("Y", history)) {
                filteredProperties.remove("history");
            }
        }
        return super.performDataObjectServiceLookup(dataObjectClass, filteredProperties, wildcardAsLiteralPropertyNames, unbounded, allPrimaryKeyValuesPresentAndNotWildcard, searchResultsLimit);
    }

    public void setLookupCriteriaGenerator(LookupCriteriaGenerator lookupCriteriaGenerator) {
        this.lookupCriteriaGenerator = lookupCriteriaGenerator;
        super.setLookupCriteriaGenerator(lookupCriteriaGenerator);
    }

    public void setEffectiveObjectDao(EffectiveObjectDao effectiveObjectDao) {
        this.effectiveObjectDao = effectiveObjectDao;
    }
}
