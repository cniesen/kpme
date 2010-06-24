package edu.iu.uis.hr.tk.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.iu.uis.hr.client.NonDatabaseDatePropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractSearchCriteria;

public class PayrollExtractReportSearchCriteria extends AbstractSearchCriteria {
	private static final String PAY_END_DATE = "payEndDate";
    private static final Set PERSISTENT_DATABASE_ENTITY_CLASSES = new HashSet();
//    static {
//        PERSISTENT_DATABASE_ENTITY_CLASSES.add(PayrollExtractReportResults.class);
//    }
    private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
    static {
    	CUSTOM_PROPERTY_DESCRIPTORS.put(PAY_END_DATE, new NonDatabaseDatePropertyDescriptor(PAY_END_DATE,false, false));
    }

    private Date payEndDate;
    
    public PayrollExtractReportSearchCriteria() {

    }
    
    protected Map getPropertyDescriptorMap() {
        Map propertyDescriptorMap = super.getPropertyDescriptorMap();
        propertyDescriptorMap.putAll(CUSTOM_PROPERTY_DESCRIPTORS);
        return propertyDescriptorMap;
    }

    public void clear() {
    }

    protected Set getPersistentDatabaseEntityClasses() {
        return PERSISTENT_DATABASE_ENTITY_CLASSES;
    }

	public Date getPayEndDate() {
		return payEndDate;
	}

	public void setPayEndDate(Date payEndDate) {
		this.payEndDate = payEndDate;
	}
}