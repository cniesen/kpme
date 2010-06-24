package edu.iu.uis.hr.tk.dataaccess;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccess;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.entity.TranslateSearchCriteria;

public interface TranslateDataAccess extends DataAccess {
    public Translate getTranslate(String fieldName, String fieldValue, Date transactionRecordEffectiveDate);

    public List getTranslates(String fieldName, Date transactionRecordEffectiveDate);

    public List getTranslates(String fieldName, List fieldValues, Date transactionRecordEffectiveDate);

    public TypedPersistentMaintainedEntityList lookupMaintainableTranslates(TranslateSearchCriteria searchCriteria);
}