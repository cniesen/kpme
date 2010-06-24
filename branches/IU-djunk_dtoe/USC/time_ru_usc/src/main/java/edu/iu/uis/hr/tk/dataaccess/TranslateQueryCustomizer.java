package edu.iu.uis.hr.tk.dataaccess;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;

import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.entity.Translate;

public class TranslateQueryCustomizer extends QueryCustomizerDefaultImpl {
    private static final Logger LOG = Logger.getLogger(TranslateQueryCustomizer.class);

    public Query customizeQuery(Object persistentDatabaseEntityObject, PersistenceBroker persistenceBroker, 
                                CollectionDescriptor collectionDescriptor, QueryByCriteria queryByCriteria) {
        
       if (!Translate.class.equals(queryByCriteria.getBaseClass())) {
            throw new IllegalArgumentException("TranslateQueryCustomizer.customizeQuery() expects a QueryByCriteria with a base class of Translate.");
        }

       String fieldName = getAttribute(FieldNames.FIELD_NAME);
       String fieldValue = getAttribute(FieldNames.FIELD_VALUE);
       queryByCriteria.getCriteria().addEqualTo(FieldNames.FIELD_NAME, fieldName);
       queryByCriteria.getCriteria().addEqualTo(FieldNames.FIELD_VALUE, fieldValue);
       
        return queryByCriteria;
    }
}