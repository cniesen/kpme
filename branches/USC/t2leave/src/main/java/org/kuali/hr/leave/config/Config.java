package org.kuali.hr.leave.config;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class Config extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String configKey;
    private String configValue;
    
    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub
	return null;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getConfigKey() {
        return configKey;
    }


    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }


    public String getConfigValue() {
        return configValue;
    }


    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

}
