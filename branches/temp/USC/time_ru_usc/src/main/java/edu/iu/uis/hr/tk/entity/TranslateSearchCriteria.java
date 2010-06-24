package edu.iu.uis.hr.tk.entity;

import java.util.HashSet;
import java.util.Set;

import edu.iu.uis.hr.entity.AbstractEffectiveDatedSearchCriteria;

public class TranslateSearchCriteria extends AbstractEffectiveDatedSearchCriteria {

    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
    static {
        PERSISTENT_DATABASE_ENTITY_CLASSES.add(Translate.class);
    }

    private String fieldName;
    private String fieldValue;
    private String description;

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

    public void clear() {
        setFieldName(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setFieldValue(edu.iu.uis.hr.Utility.getDefaultStringValue());
        setDescription(edu.iu.uis.hr.Utility.getDefaultStringValue());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        if (fieldName != null) {
            this.fieldName = fieldName;
        }
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        if (fieldValue != null) {
            this.fieldValue = fieldValue;
        }
    }
}