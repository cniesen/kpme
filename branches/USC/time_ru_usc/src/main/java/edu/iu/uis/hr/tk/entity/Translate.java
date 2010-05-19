package edu.iu.uis.hr.tk.entity;

import java.util.Date;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Optionable;
import edu.iu.uis.hr.entity.AuditedPersitentDatabaseMaintainedEntity;
import edu.iu.uis.hr.entity.EffectivePersitentDatabaseMaintainedEntity;

public class Translate extends AbstractTranslate implements Optionable, EffectivePersitentDatabaseMaintainedEntity, AuditedPersitentDatabaseMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(Translate.class);

    public Translate() {
        super();
    }

    public Translate(String fieldName, String fieldValue, Date effectiveDate) {
        this();
        setFieldName(fieldName);
        setFieldValue(fieldValue);
        setEffectiveDate(effectiveDate);
    }

    public Class getModeAuthorization() {
        return TranslateModeAuthorization.class;
    }

    public String getLabel() {
        return getDescription();
    }

    public void setLabel(String label) {
        if (label != null) {
            setDescription(label);
        }
    }

    public String getValue() {
        return getFieldValue();
    }

    public void setValue(String value) {
        if (value != null) {
            setFieldValue(value);
        }
    }

}
