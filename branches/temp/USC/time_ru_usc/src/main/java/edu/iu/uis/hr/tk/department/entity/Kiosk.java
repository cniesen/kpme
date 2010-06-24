package edu.iu.uis.hr.tk.department.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class Kiosk extends AbstractPersistentNonDatabaseEntity implements PersistentMaintainedEntity {

    private String kioskName;
    private boolean checked;
    private PersistentMaintainedEntity parent;

    public static final String KIOSK_NAME_FIELD_NAME = "kioskName";
    public static final String KIOSK_CHECKED_FIELD_NAME = "checked";
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        PROPERTY_DESCRIPTORS.put(Kiosk.KIOSK_NAME_FIELD_NAME, new NonDatabaseStringPropertyDescriptor(Kiosk.KIOSK_NAME_FIELD_NAME, false, 30));
        PROPERTY_DESCRIPTORS.put(Kiosk.KIOSK_CHECKED_FIELD_NAME, new NonDatabaseStringPropertyDescriptor(Kiosk.KIOSK_CHECKED_FIELD_NAME, false, 1));
    }
    private static final String PARENT_PROPERTY_NAME = "parent";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(PARENT_PROPERTY_NAME);
    }

    public Kiosk() {
        super();
    }

    public Kiosk(String kioskName) {
        this();
        setKioskName(kioskName);
    }

    public Class getModeAuthorization() {
        return KioskModeAuthorization.class;
    }

    protected Map getPropertyDescriptorMap() {
        return PROPERTY_DESCRIPTORS;
    }

    public PersistentMaintainedEntity getParent() {
        return parent;
    }

    public void setParent(PersistentMaintainedEntity parent) {
        if (parent != null) {
            this.parent = parent;
        }
    }

    public String getKioskName() {
        return kioskName;
    }

    public void setKioskName(String kioskName) {
        if (kioskName != null) {
            this.kioskName = kioskName;
        }
    }

    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
