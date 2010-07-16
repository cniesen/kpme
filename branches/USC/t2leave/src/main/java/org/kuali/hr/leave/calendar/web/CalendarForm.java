package org.kuali.hr.leave.calendar.web;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import org.kuali.hr.leave.calendar.Month;
import org.kuali.hr.leave.calendar.document.CalendarDocument;
import org.kuali.hr.leave.leavecode.LeaveCode;

public class CalendarForm extends T2Form {

	private static final long serialVersionUID = 1L;

	private Month month;
	private int targetMonth;
	private int year;

	private Date fromDate;
	private Date toDate;

	private String description;
	private Boolean includeWeekends;
	private Boolean applyToYTD;
	private String leaveCodeKey;
	private BigDecimal hours;
	private int inactiveLedgerId;
	private boolean showSubmit;
	private boolean showApprove;

// TODO	private PtoBalanceContainer ptoBalanceContainer;
	private Date addAccrualDate;
	private CalendarDocument calendarDocument;
	private List<LeaveCode> basicLeaveCodes = new ArrayList<LeaveCode>();
	private List<LeaveCode> advancedLeaveCodes = new ArrayList<LeaveCode>();
	private List<LeaveCode> allLeaveCodes = new ArrayList<LeaveCode>();
	private String errorLocation;
	private String disapprovedMessage;
	private List<String> disapprovedDocumentIds = new ArrayList<String>();
	private Boolean flexSchedule;
	
	public CalendarForm (){
		this.setCalendarDocument(new CalendarDocument());
// TODO		this.setPtoBalanceContainer(new PtoBalanceContainer());
		this.setShowSubmit(false);
		this.setShowApprove(false);
		this.setFlexSchedule(false);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		return actionErrors;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		resetFields();
		super.reset(mapping, request);

	}

	protected void resetFields() {
		this.setIncludeWeekends(Boolean.FALSE);
		this.setApplyToYTD(Boolean.FALSE);
		this.setHours(null);
	}


	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getTargetMonth() {
		return targetMonth;
	}

	public void setTargetMonth(int targetMonth) {
		this.targetMonth = targetMonth;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Boolean getIncludeWeekends() {
		return includeWeekends;
	}

	public void setIncludeWeekends(Boolean includeWeekends) {
		this.includeWeekends = includeWeekends;
	}

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public String getLeaveCodeKey() {
		return leaveCodeKey;
	}

	public void setLeaveCodeKey(Long ptoCode) {
		this.leaveCodeKey = Long.toString(ptoCode);
	}
	
	public void setLeaveCodeKey(String ptoCode){
		this.leaveCodeKey = ptoCode;
	}

	public int getInactiveLedgerId() {
		return inactiveLedgerId;
	}

	public void setInactiveLedgerId(int inactiveLedgerId) {
		this.inactiveLedgerId = inactiveLedgerId;
	}

	public Date getAddAccrualDate() {
		return addAccrualDate;
	}

	public void setAddAccrualDate(Date addAccrualDate) {
		this.addAccrualDate = addAccrualDate;
	}
// TODO	public PtoBalanceContainer getPtoBalanceContainer() {
// TODO		return ptoBalanceContainer;
// TODO	}
// TODO
// TODO	public void setPtoBalanceContainer(PtoBalanceContainer ptoBalanceContainer) {
// TODO		this.ptoBalanceContainer = ptoBalanceContainer;
// TODO	}
	public Date getToday(){
		return (new Date(System.currentTimeMillis()));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CalendarDocument getCalendarDocument() {
		return calendarDocument;
	}

	public void setCalendarDocument(CalendarDocument calendarDocument) {
		this.calendarDocument = calendarDocument;
	}

	public boolean isShowSubmit() {
		return showSubmit;
	}

	public void setShowSubmit(boolean showSubmit) {
		this.showSubmit = showSubmit;
	}

	public boolean isShowApprove() {
		return showApprove;
	}

	public void setShowApprove(boolean showApprove) {
		this.showApprove = showApprove;
	}

	public List<LeaveCode> getBasicLeaveCodes() {
		return this.basicLeaveCodes;
	}

	public void setBasicLeaveCodes(List<LeaveCode> basicLeaveCodes) {
		this.basicLeaveCodes = basicLeaveCodes;
	}

	public List<LeaveCode> getAdvancedLeaveCodes() {
		return this.advancedLeaveCodes;
	}

	public void setAdvancedLeaveCodes(List<LeaveCode> advancedLeaveCodes) {
		this.advancedLeaveCodes = advancedLeaveCodes;
	}

	public String getErrorLocation() {
		return errorLocation;
	}

	public void setErrorLocation(String errorLocation) {
		this.errorLocation = errorLocation;
	}

	public List<LeaveCode> getAllLeaveCodes() {
		return allLeaveCodes;
	}

	public void setAllLeaveCodes(List<LeaveCode> allLeaveCodes) {
		this.allLeaveCodes = allLeaveCodes;
	}

	public Boolean getApplyToYTD() {
		return applyToYTD;
	}

	public void setApplyToYTD(Boolean applyToYTD) {
		//This is an absolute horrible hack in order to make the checkbox work.  If you want to try to do it correctly, good luck.  Default above applyToYTD in reset() to TRUE if you change the ! here
		this.applyToYTD = applyToYTD;
	}

	public String getDisapprovedMessaage() {
		return disapprovedMessage;
	}

	public void setDisapprovedMessage(String disapprovedMessaage) {
		this.disapprovedMessage = disapprovedMessaage;
	}

	public List<String> getDisapprovedDocumentIds() {
		return disapprovedDocumentIds;
	}

	public void setDisapprovedDocumentIds(List<String> disapprovedDocumentIds) {
		this.disapprovedDocumentIds = disapprovedDocumentIds;
	}
	
// TODO	public Boolean getBasicWithHours(){
// TODO		return flexSchedule || EptoUtils.isPastJan1st(getMonth());
// TODO	}

	public Boolean getFlexSchedule() {
		return flexSchedule;
	}

	public void setFlexSchedule(Boolean flexSchedule) {
		this.flexSchedule = flexSchedule;
	}

	@Override
	public void addRequiredNonEditableProperties() {
	    // TODO Auto-generated method stub
	    super.addRequiredNonEditableProperties();
	}

}
