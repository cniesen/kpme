package org.kuali.hr.leave.calendar.web;

import org.kuali.hr.leave.util.T2User;
import org.kuali.rice.kns.web.struts.form.KualiForm;

public class T2Form extends KualiForm {

	private static final long serialVersionUID = 1L;
	
	private String mappingName;
	private String mappingPath;
	
 	private T2User employee;
	private String methodToCall;
// TODO	private SecurityContext userSecurityContext;
// TODO	private SecurityContext employeeSecurityContext;
	private String employeeForAction;
	private String changeEmployeeNetworkId;
	private boolean showNonUserTabs;
	

	public String getMethodToCall() {
		return methodToCall;
	}
	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
	}
// TODO	public EptoUser getUser() {
// TODO		return EptoContext.getUser();
// TODO	}
 	public T2User getEmployee() {
 		return employee;
 	}
 	public void setEmployee(T2User employee){
 		this.employee = employee;
 	}
// TODO	public boolean isCorrectionMode(){
// TODO		return (!StringUtils.equals(this.employee.getEmplid(), this.getUser().getEmplid()));
// TODO	}
// TODO	public SecurityContext getUserSecurityContext() {
// TODO		return userSecurityContext;
// TODO	}
// TODO	public void setUserSecurityContext(SecurityContext userSecurityContext) {
// TODO		this.userSecurityContext = userSecurityContext;
// TODO	}
// TODO	public boolean isBackdoorUser() {
// TODO		return EptoContext.getBackdoorUser() != null;
// TODO	}
	public String getEmployeeForAction() {
		return employeeForAction;
	}
	public void setEmployeeForAction(String employeeForAction) {
		this.employeeForAction = employeeForAction;
	}
	public String getMappingName() {
		return mappingName;
	}
	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}
	public String getMappingPath() {
		return mappingPath;
	}
	public void setMappingPath(String mappingPath) {
		this.mappingPath = mappingPath;
	}
	public String getChangeEmployeeNetworkId() {
		return changeEmployeeNetworkId;
	}
	public void setChangeEmployeeNetworkId(String changeEmployeeNetworkId) {
		this.changeEmployeeNetworkId = changeEmployeeNetworkId;
	}
	public boolean isShowNonUserTabs() {
		return showNonUserTabs;
	}
	public void setShowNonUserTabs(boolean showNonUserTabs) {
		this.showNonUserTabs = showNonUserTabs;
	}
// TODO	public SecurityContext getEmployeeSecurityContext() {
// TODO		return employeeSecurityContext;
// TODO	}
// TODO	public void setEmployeeSecurityContext(SecurityContext employeeSecurityContext) {
// TODO		this.employeeSecurityContext = employeeSecurityContext;
// TODO	}
	@Override
	public boolean isPropertyNonEditableButRequired(String propertyName) {
		return true;
	}
	@Override
	public boolean isPropertyEditable(String propertyName) {
		return true;
	}
	

}
