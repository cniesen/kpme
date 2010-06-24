package edu.iu.uis.hr.tk.dataaccess;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.EffectiveDatedSearchCriteria;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.entity.TranslateSearchCriteria;

public class TranslateDataAccessOjb extends AbstractDataAccessOjb implements TranslateDataAccess {

    private static final Logger LOG = Logger.getLogger(TranslateDataAccessOjb.class);

    public Translate getTranslate(String fieldName, String fieldValue, Date transactionRecordEffectiveDate) {
        Translate translate = (Translate)getCurrentActiveRecord(new Translate(fieldName, fieldValue, transactionRecordEffectiveDate));
        return translate;
    }

    public List getTranslates(String fieldName, Date transactionRecordEffectiveDate) {
        Criteria currentRecordsCriteria = getCurrentRecordsCriteria(Translate.class, transactionRecordEffectiveDate);
        currentRecordsCriteria.addEqualTo(FieldNames.FIELD_NAME, fieldName);
        return getValidValuesListByQuery(QueryFactory.newQuery(Translate.class, currentRecordsCriteria));
    }

    public List getTranslates(String fieldName, List fieldValues, Date transactionRecordEffectiveDate) {
        Criteria currentRecordsCriteria = getCurrentRecordsCriteria(Translate.class, transactionRecordEffectiveDate);
        currentRecordsCriteria.addEqualTo(FieldNames.FIELD_NAME, fieldName);
        currentRecordsCriteria.addIn(FieldNames.FIELD_VALUE, fieldValues);
        return getValidValuesListByQuery(QueryFactory.newQuery(Translate.class, currentRecordsCriteria));
    }

    public TypedPersistentMaintainedEntityList lookupMaintainableTranslates(TranslateSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.FIELD_NAME, searchCriteria.getFieldName());
        getStandardLookupLogic(lookupCriteria, FieldNames.FIELD_VALUE, searchCriteria.getFieldValue());
        getStandardLookupLogic(lookupCriteria, FieldNames.DESCRIPTION, searchCriteria.getDescription());
        getLookupEffectiveLogic(lookupCriteria, Translate.class, (EffectiveDatedSearchCriteria)searchCriteria);
        return getMaintainableValuesListByQuery(QueryFactory.newQuery(Translate.class, lookupCriteria));
    }
}