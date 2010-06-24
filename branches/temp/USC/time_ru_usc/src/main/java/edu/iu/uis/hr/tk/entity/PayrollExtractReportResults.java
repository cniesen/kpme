package edu.iu.uis.hr.tk.entity;

import java.util.HashMap;
import java.util.Map;

import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.AbstractPersistentNonDatabaseEntity;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;

public class PayrollExtractReportResults extends AbstractPersistentNonDatabaseEntity {

	
    private static final Map PROPERTY_DESCRIPTORS = new HashMap();
    static {
        NonDatabasePropertyDescriptor payEndDatePropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.PAY_END_DATE, DocumentHeaderSnapshot.class);
        payEndDatePropertyDescriptor.setDisplayOnly(true);
        PROPERTY_DESCRIPTORS.put(payEndDatePropertyDescriptor.getName(), payEndDatePropertyDescriptor);
        
        NonDatabasePropertyDescriptor dateAsMillisDescriptor = new NonDatabaseStringPropertyDescriptor("dateAsMillis",20);
        PROPERTY_DESCRIPTORS.put("dateAsMillis", dateAsMillisDescriptor);
    }
    
	protected Map getPropertyDescriptorMap() {
		return PROPERTY_DESCRIPTORS;
	}

}
