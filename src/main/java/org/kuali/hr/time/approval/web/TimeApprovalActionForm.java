package org.kuali.hr.time.approval.web;

import org.kuali.hr.time.timesheet.web.TimesheetActionForm;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUser;

import java.util.*;

public class TimeApprovalActionForm extends TimesheetActionForm {

    public static final String ORDER_BY_PRINCIPAL = "PrincipalName";
    public static final String ORDER_BY_DOCID = "DocumentId";
    public static final String ORDER_BY_STATUS = "Status";

    private static final long serialVersionUID = -173408280988754540L;

    private String name;
    private SortedSet<String> payCalendarGroups = new TreeSet<String>();
    private String selectedPayCalendarGroup;
    private Date payBeginDate;
    private Date payEndDate;
    private List<String> payCalendarLabels = new ArrayList<String>();
    private List<ApprovalTimeSummaryRow> approvalRows = new ArrayList<ApprovalTimeSummaryRow>();
    private Long workArea = null;
    private String lastDocumentId;

    /** Used for ajax dynamic row updating */
    private String outputString;

    private String searchField;
    private String searchTerm;

    private int rowsToShow = 5;
    private int rowsInTotal;
    private String sortField;
    private boolean ascending = true;
    private boolean ajaxCall = false;

    /**
     * Gets the name of the user that this row represents.
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

    public String getLastDocumentId() {
        return lastDocumentId;
    }

    public void setLastDocumentId(String lastDocumentId) {
        this.lastDocumentId = lastDocumentId;
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

    public SortedSet<String> getPayCalendarGroups() {
        return payCalendarGroups;
    }

    public void setPayCalendarGroups(SortedSet<String> payCalendarGroups) {
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
}
