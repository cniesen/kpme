package org.kuali.hr.time.task;

import java.util.LinkedHashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;
@XmlAccessorType(value=XmlAccessType.NONE)
public class Task extends PersistableBusinessObjectBase {
    /**
     *
     */
    
    @XmlElement
    private Long tkTaskId;
    @XmlElement
    private Long task;
    @XmlElement
    private Long workArea;
    @XmlElement(nillable=false)
    private Long tkWorkAreaId;
    @XmlElement
    private String description;
    @XmlElement
    private String userPrincipalId;
    @XmlElement
    private String administrativeDescription;
    
    private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap<String, Object> toStringMap = new LinkedHashMap<String,Object>();
		toStringMap.put("tkTaskId", tkTaskId);
		toStringMap.put("task", task);
		toStringMap.put("workArea", workArea);
		toStringMap.put("tkWorkAreaId", tkWorkAreaId);
		toStringMap.put("description", description);
		toStringMap.put("userPrincipalId", userPrincipalId);
		toStringMap.put("administrativeDescription", administrativeDescription);
		return toStringMap;
	}

    public String getDescription()
    {
    return description;
    }

    public void setDescription(String desc)
    {
    	this.description = desc;
    }

    public String getUserPrincipalId(){
    	return userPrincipalId;
    }

    public void setUserPrincipalId(String pid)
    {
    	this.userPrincipalId = pid;
    }

    public String getAdministrativeDescription(){
    	return administrativeDescription;
    }

    public void setAdministrativeDescription(String addesc)
    {
    	this.administrativeDescription = addesc;
    }

	public Long getTkTaskId() {
		return tkTaskId;
	}

	public void setTkTaskId(Long tkTaskId) {
		this.tkTaskId = tkTaskId;
	}

	public Long getTask() {
		return task;
	}

	public void setTask(Long task) {
		this.task = task;
	}

	public Long getWorkArea() {
		return workArea;
	}

	public void setWorkArea(Long workArea) {
		this.workArea = workArea;
	}

	public Long getTkWorkAreaId() {
		return tkWorkAreaId;
	}

	public void setTkWorkAreaId(Long tkWorkAreaId) {
		this.tkWorkAreaId = tkWorkAreaId;
	}


}
