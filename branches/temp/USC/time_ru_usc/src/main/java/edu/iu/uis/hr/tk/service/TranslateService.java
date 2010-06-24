package edu.iu.uis.hr.tk.service;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.service.Service;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.entity.TranslateSearchCriteria;

public interface TranslateService extends Service {

    public static final String EMPLOYEE_TYPE_BIWEEKLY = "E";
    public static final String EMPLOYEE_TYPE_HOURLY = "H";
    public static final String EMPLOYEE_TYPE_BIWEEKLY_LABEL = "Biweekly";
    public static final String EMPLOYEE_TYPE_HOURLY_LABEL = "Hourly";
    
    public Translate getTranslate(String persistentDatabaseEntityFieldName, String fieldValue, Date transactionRecordEffectiveDate);

    public List getTranslates(String persistentDatabaseEntityFieldName, Date transactionRecordEffectiveDate);

    public List getTranslates(String persistentDatabaseEntityFieldName, List fieldValues, Date transactionRecordEffectiveDate);

    public List getDropdownTranslates(String persistentDatabaseEntityFieldName, Date transactionRecordEffectiveDate);

    public List getDropdownTranslates(String persistentDatabaseEntityFieldName, List fieldValues, Date transactionRecordEffectiveDate);

    public TypedPersistentMaintainedEntityList lookupMaintainableTranslates(TranslateSearchCriteria searchCriteria);

    public void saveTranslates(TypedPersistentMaintainedEntityList translates);
    
    public List getEmployeeTypes();    
}