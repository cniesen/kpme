package org.kuali.kpme.core.api.bo.dao;

import org.joda.time.LocalDate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EffectiveObjectDao {

    <T> T getSingleEffectiveObject(Class<T> type, Collection<String> keyFields, Map<String, Object> criteria, LocalDate asOfDate);
    <T> List<T> getEffectiveObjectList(Class<T> type, Collection<String> keyFields, Map<String, Object> criteria, LocalDate asOfDate);

    <T> List<T> findEffectiveObjectList(Class<T> type, Collection<String> keyFields, Map<String, String> criteria,
                                        List<String> wildcardAsLiteralPropertyNames, boolean unbounded, boolean allPrimaryKeyValuesPresentAndNotWildcard, Integer searchResultsLimit);

    <T> T cloneAndPersistDataObject(T obj);
}
