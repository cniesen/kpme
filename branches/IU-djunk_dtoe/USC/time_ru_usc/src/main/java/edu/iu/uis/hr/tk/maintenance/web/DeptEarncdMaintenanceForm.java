package edu.iu.uis.hr.tk.maintenance.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.tk.maintenance.DepartmentEarncdBean;

public class DeptEarncdMaintenanceForm extends AbstractStrutsActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8680919722716907623L;

	
	private String earnCode;
	private String method;
	private List<DepartmentEarncdBean> searchResults;
	private DepartmentEarncdBean addRow;
	private String sqlError;
	private List<String> messages;
	private Boolean runSearch;
	private String editRow;
	private DepartmentEarncdBean editRowBean;
	
	public DeptEarncdMaintenanceForm(){
		setAddRow(new DepartmentEarncdBean());
		setSearchResults(new ArrayList<DepartmentEarncdBean>());
		setMessages(new ArrayList<String>());
		setRunSearch(Boolean.FALSE);
		setEditRowBean(new DepartmentEarncdBean());
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		getAddRow().setEmployee(Boolean.FALSE);
		getAddRow().setSupervisor(Boolean.FALSE);
		getAddRow().setPayrollprocessor(Boolean.FALSE);
		getAddRow().setActive(Boolean.FALSE);	
		getEditRowBean().setEmployee(Boolean.FALSE);
		getEditRowBean().setSupervisor(Boolean.FALSE);
		getEditRowBean().setPayrollprocessor(Boolean.FALSE);
		getEditRowBean().setActive(Boolean.FALSE);		
	}
	
	public void prepare() {
		
	}

	public List getAccessAuthorizations() {
		return null;
	}

	public String getEarnCode() {
		return earnCode;
	}

	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<DepartmentEarncdBean> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<DepartmentEarncdBean> searchResults) {
		this.searchResults = searchResults;
	}

	public DepartmentEarncdBean getAddRow() {
		return addRow;
	}

	public void setAddRow(DepartmentEarncdBean addRow) {
		this.addRow = addRow;
	}

	public String getSqlError() {
		return sqlError;
	}

	public void setSqlError(String sqlError) {
		this.sqlError = sqlError;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public Boolean getRunSearch() {
		return runSearch;
	}

	public void setRunSearch(Boolean runSearch) {
		this.runSearch = runSearch;
	}

	public String getEditRow() {
		return editRow;
	}

	public void setEditRow(String editRow) {
		this.editRow = editRow;
	}

	public DepartmentEarncdBean getEditRowBean() {
		return editRowBean;
	}

	public void setEditRowBean(DepartmentEarncdBean editRowBean) {
		this.editRowBean = editRowBean;
	}



}
