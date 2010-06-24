package edu.iu.uis.hr.tk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.AbstractService;
import edu.iu.uis.hr.tk.dataaccess.TranslateDataAccess;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.entity.TranslateSearchCriteria;

public class TranslateServiceImpl extends AbstractService implements TranslateService {

    private static final Map PERSISTENT_DATABASE_ENTITY_NAME_TO_FIELD_NAME_MAP = new HashMap();
    static {
        PERSISTENT_DATABASE_ENTITY_NAME_TO_FIELD_NAME_MAP.put(FieldNames.ACTION, "CLOCK_ACTION_CD");
        PERSISTENT_DATABASE_ENTITY_NAME_TO_FIELD_NAME_MAP.put(FieldNames.STATUS, "EXTRACT_STATUS");
        PERSISTENT_DATABASE_ENTITY_NAME_TO_FIELD_NAME_MAP.put(FieldNames.HELP_TYPE, "HELP_TYPE");
        PERSISTENT_DATABASE_ENTITY_NAME_TO_FIELD_NAME_MAP.put(FieldNames.DETAIL_TYPE, "DETAIL_TYPE");
    }

    private static final List EMPLOYEE_TYPES = new ArrayList();
    static {
        EMPLOYEE_TYPES.add(new Option(EMPLOYEE_TYPE_BIWEEKLY, EMPLOYEE_TYPE_BIWEEKLY_LABEL));
        EMPLOYEE_TYPES.add(new Option(EMPLOYEE_TYPE_HOURLY, EMPLOYEE_TYPE_HOURLY_LABEL));
    }
    

    private TranslateDataAccess translateDataAccess;

    @CacheResult
    public Translate getTranslate(String persistentDatabaseEntityFieldName, String fieldValue, Date transactionRecordEffectiveDate) {
        return getTranslateDataAccess().getTranslate(getFieldName(persistentDatabaseEntityFieldName), fieldValue, transactionRecordEffectiveDate);
    }

    public List getTranslates(String persistentDatabaseEntityFieldName, Date transactionRecordEffectiveDate) {
        return getSortedOptions(getTranslateDataAccess().getTranslates(getFieldName(persistentDatabaseEntityFieldName), transactionRecordEffectiveDate));
    }

    public List getTranslates(String persistentDatabaseEntityFieldName, List fieldValues, Date transactionRecordEffectiveDate) {
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(fieldValues)) {
            return getSortedOptions(getTranslateDataAccess().getTranslates(getFieldName(persistentDatabaseEntityFieldName), fieldValues, transactionRecordEffectiveDate));
        }
        return getTranslates(persistentDatabaseEntityFieldName, transactionRecordEffectiveDate);
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableTranslates(TranslateSearchCriteria searchCriteria) {
        return getTranslateDataAccess().lookupMaintainableTranslates(searchCriteria);
    }

    public List getDropdownTranslates(String persistentDatabaseEntityFieldName, Date transactionRecordEffectiveDate) {
        return getSortedOptions(getTranslateDataAccess().getTranslates(getFieldName(persistentDatabaseEntityFieldName), transactionRecordEffectiveDate));
    }

    public List getDropdownTranslates(String persistentDatabaseEntityFieldName, List fieldValues, Date transactionRecordEffectiveDate) {
        if (edu.iu.uis.hr.entity.logic.Utility.hasValue(fieldValues)) {
            return getSortedOptions(getTranslateDataAccess().getTranslates(getFieldName(persistentDatabaseEntityFieldName), fieldValues, transactionRecordEffectiveDate));
        }
        return getDropdownTranslates(persistentDatabaseEntityFieldName, transactionRecordEffectiveDate);
    }

    public void saveTranslates(TypedPersistentMaintainedEntityList translates) {
        save(translates, getTranslateDataAccess());
    }
    
    public List getEmployeeTypes() {
        return EMPLOYEE_TYPES;
    }    

    private TranslateDataAccess getTranslateDataAccess() {
        return translateDataAccess;
    }

    public void setTranslateDataAccess(TranslateDataAccess translateDataAccess) {
        if (translateDataAccess != null) {
            this.translateDataAccess = translateDataAccess;
        }
    }

    private String getFieldName(String persistentDatabaseEntityName) {
        return (String) PERSISTENT_DATABASE_ENTITY_NAME_TO_FIELD_NAME_MAP.get(persistentDatabaseEntityName);
    }
}