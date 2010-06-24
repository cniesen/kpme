package edu.iu.uis.hr.tk.client;

import java.util.List;

import edu.iu.uis.hr.client.AbstractMaintenanceActionForm;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.entity.Translate;
import edu.iu.uis.hr.tk.service.TranslateService;

public class TranslateMaintenanceForm extends AbstractMaintenanceActionForm {
    private TypedPersistentMaintainedEntityList translates;

    public TranslateMaintenanceForm() {
        super();
        setTranslates(new TypedPersistentMaintainedEntityList(Translate.class));
    }

    public Class getModeAuthorization() {
        return TranslateMaintenanceFormModeAuthorization.class;
    }
    
    public void setLookupResults(List lookupResults) {
        getTranslates().addAll(lookupResults);
    }

    protected TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        return getTranslates();
    }

    public void prepare() {
    }

    public void save() {
        ((TranslateService) getService(TranslateService.class)).saveTranslates(getTranslates());
    }

    public TypedPersistentMaintainedEntityList getTranslates() {
        return translates;
    }

    public void setTranslates(TypedPersistentMaintainedEntityList translates) {
        if (translates != null) {
            this.translates = translates;
        }
    }

    public Translate getTranslate(int index) {
        return (Translate) getTranslates().get(index);
    }

    public void setTranslate(int index, Translate translate) {
        if (translate != null) {
            getTranslates().add(index, translate);
        }
    }
}