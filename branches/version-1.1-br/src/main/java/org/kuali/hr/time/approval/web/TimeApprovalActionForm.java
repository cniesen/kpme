package org.kuali.hr.time.approval.web;

import org.kuali.hr.time.base.web.TkForm;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;

import java.util.*;

public class TimeApprovalActionForm extends TkForm {

    public static final String ORDER_BY_PRINCIPAL = "principalName";
    public static final String ORDER_BY_DOCID = "documentId";
    public static final String ORDER_BY_STATUS = "Status";
    public static final String ORDER_BY_WORKAREA = "WorkArea";

    private static final long serialVersionUID = -173408280988754540L;

    private Long hrPyCalendarEntriesId;
    private Long hrPyCalendarId;
    private String name;
    private List<String> payCalendarGroups = new LinkedList<String>();
    private String selectedPayCalendarGroup;
    private String selectedDept;
    private String selectedWorkArea;
    private Date payBeginDate;
    private Date payEndDate;
    private String payBeginDateForSearch;
    private String payEndDateForSearch;

    private List<String> payCalendarLabels = new ArrayList<String>();
    private List<ApprovalTimeSummaryRow> approvalRows;
    private Long workArea = null;
    private Set<Long> deptWorkareas = new HashSet<Long>();
    private String documentId;
    private String employeeWorkArea;
    private List<String> assignmentPrincipalIds = new LinkedList<String>();
    
    private Set<String> principalIds = new HashSet<String>();



	/**
     * Used for ajax dynamic row updating
     */
    private String outputString;

    private String searchField;
    private String searchTerm;

    private int rowsToShow = 50;
    private int rowsInTotal;
    private String sortField;
    private boolean ascending = true;
    private boolean ajaxCall = false;

    private Boolean testSelected = Boolean.FALSE;

    private Long prevPayCalendarId = null;
    private Long nextPayCalendarId = null;

    private List<String> departments = new ArrayList<String>();
    private Integer resultSize = 0;
    private List<String> searchResultList = new LinkedList<String>();

    private String calNav = null;

    public String getCalNav() {
        return calNav;
    }

    public void setCalNav(String calNav) {
        this.calNav = calNav;
    }

    public Long getHrPyCalendarEntriesId() {
        return hrPyCalendarEntriesId;
    }

    public void setHrPyCalendarEntriesId(Long hrPyCalendarEntriesId) {
        this.hrPyCalendarEntriesId = hrPyCalendarEntriesId;
    }

    public Long getHrPyCalendarId() {
        return hrPyCalendarId;
    }

    public void setHrPyCalendarId(Long hrPyCalendarId) {
        this.hrPyCalendarId = hrPyCalendarId;
    }

    /**
     * Gets the name of the user that this row represents.
     *
     * @return String representing the users name.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPayBeginDate(Date payBeginDate) {
        this.payBeginDate = payBeginDate;
    }

    public Date getPayBeginDate() {
        return payBeginDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayCalendarLabels(List<String> payCalendarLabels) {
        this.payCalendarLabels = payCalendarLabels;
    }

    public List<String> getPayCalendarLabels() {
        return payCalendarLabels;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getRowsToShow() {
        return rowsToShow;
    }

    public void setRowsToShow(int rowsToShow) {
        this.rowsToShow = rowsToShow;
    }

    public List<ApprovalTimeSummaryRow> getApprovalRows() {
        return approvalRows;
    }

    public void setApprovalRows(List<ApprovalTimeSummaryRow> approvalRows) {
        this.approvalRows = approvalRows;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public boolean isAjaxCall() {
        return ajaxCall;
    }

    public void setAjaxCall(boolean ajaxCall) {
        this.ajaxCall = ajaxCall;
    }

    public List<String> getPayCalendarGroups() {
        return payCalendarGroups;
    }

    public void setPayCalendarGroups(List<String> payCalendarGroups) {
        this.payCalendarGroups = payCalendarGroups;
    }

    public int getRowsInTotal() {
        return rowsInTotal;
    }

    public void setRowsInTotal(int rowsInTotal) {
        this.rowsInTotal = rowsInTotal;
    }

    /**
     * Provides a set of WorkArea numbers that the current approver has
     * dominion over.
     *
     * @return A Set of Longs representing work areas.
     */
    public Set<Long> getApproverWorkAreas() {
        TKUser tkUser = TKContext.getUser();
        return tkUser.getCurrentRoles().getApproverWorkAreas();
    }

    public Long getWorkArea() {
        return workArea;
    }

    public void setWorkArea(Long workArea) {
        this.workArea = workArea;
    }

    public String getSelectedPayCalendarGroup() {
        return selectedPayCalendarGroup;
    }

    public void setSelectedPayCalendarGroup(String selectedPayCalendarGroup) {
        this.selectedPayCalendarGroup = selectedPayCalendarGroup;
    }

    public String getEmployeeWorkArea() {
        return employeeWorkArea;
    }

    public void setEmployeeWorkArea(String employeeWorkArea) {
        this.employeeWorkArea = employeeWorkArea;
    }

    public Boolean getTestSelected() {
        return testSelected;
    }

    public void setTestSelected(Boolean testSelected) {
        this.testSelected = testSelected;
    }

    public Long getPrevPayCalendarId() {
        return prevPayCalendarId;
    }

    public void setPrevPayCalendarId(Long prevPayCalendarId) {
        this.prevPayCalendarId = prevPayCalendarId;
    }

    public Long getNextPayCalendarId() {
        return nextPayCalendarId;
    }

    public void setNextPayCalendarId(Long nextPayCalendarId) {
        this.nextPayCalendarId = nextPayCalendarId;
    }

    public String getSelectedDept() {
        return selectedDept;
    }

    public void setSelectedDept(String selectedDept) {
        this.selectedDept = selectedDept;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public Set<Long> getDeptWorkareas() {
        return deptWorkareas;
    }

    public void setDeptWorkareas(Set<Long> deptWorkareas) {
        this.deptWorkareas = deptWorkareas;
    }

    public List<String> getAssignmentPrincipalIds() {
        return assignmentPrincipalIds;
    }

    public void setAssignmentPrincipalIds(List<String> assignmentPrincipalIds) {
        this.assignmentPrincipalIds = assignmentPrincipalIds;
    }

    public int getResultSize() {
        return resultSize;
    }

    public void setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
    }

    public String getSelectedWorkArea() {
        return selectedWorkArea;
    }

    public void setSelectedWorkArea(String selectedWorkArea) {
        this.selectedWorkArea = selectedWorkArea;
    }

    public List<String> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<String> searchResultList) {
        this.searchResultList = searchResultList;
    }

    public String getPayBeginDateForSearch() {
        return payBeginDateForSearch;
    }

    public void setPayBeginDateForSearch(String payBeginDateForSearch) {
        this.payBeginDateForSearch = payBeginDateForSearch;
    }

    public String getPayEndDateForSearch() {
        return payEndDateForSearch;
    }

    public void setPayEndDateForSearch(String payEndDateForSearch) {
        this.payEndDateForSearch = payEndDateForSearch;
    }

    public Set<String> getPrincipalIds() {
		return principalIds;
	}

	public void setPrincipalIds(Set<String> principalIds) {
		this.principalIds = principalIds;
	}
}
