package org.kuali.hr.leave.util;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class T2User implements Serializable {

	private static final long serialVersionUID = -3216089514481450316L;
	
	private String networkId;
	private String emplid;
	private String fullName;
	private T2User backdoorUser;
	private String lastName;
	private String positionNumber;
// TODO	private SecurityContext securityContext;

	public T2User() {

	}

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public T2User getBackdoorUser() {
		return backdoorUser;
	}

	public void setBackdoorUser(T2User backdoorUser) {
		this.backdoorUser = backdoorUser;
	}

// TODO	public String getPositionNumber() {
// TODO		if (StringUtils.isNotEmpty(getEmplid())) {
// TODO			return EptoServiceLocator.getPositionHierarchyService().translateEmplidToPosition(getEmplid());
// TODO		} else {
// TODO			return null;
// TODO		}
// TODO		
// TODO	}

	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isMatch(T2User emplUser){
		return StringUtils.equals(this.networkId, emplUser.getNetworkId());
	}

// TODO	public SecurityContext getSecurityContext() {
// TODO		return securityContext;
// TODO	}
// TODO
// TODO	public void setSecurityContext(SecurityContext securityContext) {
// TODO		this.securityContext = securityContext;
// TODO	}
	
	public boolean isEmpty(){
		if(StringUtils.isBlank(emplid)){
			return true;
		}
		return false;
	}

}
