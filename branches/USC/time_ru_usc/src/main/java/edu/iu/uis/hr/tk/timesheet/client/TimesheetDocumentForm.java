package edu.iu.uis.hr.tk.timesheet.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.TimelessDate;
import edu.iu.uis.hr.client.AbstractDocumentActionForm;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.client.OpenAccessAuthorization;
import edu.iu.uis.hr.client.StrutsDispatchParamConstants;
import edu.iu.uis.hr.client.UserSession;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.Document;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.PayCalendar;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.directory.entity.User;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.funding.service.AssignmentService;
import edu.iu.uis.hr.tk.job.funding.service.EarningService;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.log.LogPerformanceMethod;
import edu.iu.uis.hr.tk.timesheet.TimesheetInitiateKey;
import edu.iu.uis.hr.tk.timesheet.entity.CheckedPayCalendarDate;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.DatedHourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentLock;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.Hours;
import edu.iu.uis.hr.tk.timesheet.entity.HoursDetail;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class TimesheetDocumentForm extends AbstractDocumentActionForm {
    private static final Logger LOG = Logger.getLogger(TimesheetDocumentForm.class);
    private static final Logger LOG_PERFORM = Logger.getLogger(LogPerformanceMethod.class);

    private static final long serialVersionUID = -3454704041203825476L;
    private TimesheetDocument timesheetDocument;
    private boolean editAllowed;
    private Date payEndDate;
    private String payEndDateStr;
    private User user;
    private String cardId;
    private boolean kioskUser;
    private boolean allowTimesheetLite;
    private String scrollx;
    private String scrolly;
    private DatedHourDetail datedHourDetail;
    
    private boolean clockOnlyTimesheet;
    private boolean ignoreClockOnlyStatus;

    private final String OVERTIME_EARN_CODE_SHORT_NAME = "OT Earn Code";
    private final String OVERTIME_HOURS_SHORT_NAME = "OT Hours";
    private final String ORGANIZATION_REFERENCE_ID_SHORT_NAME = "Org Ref Id";
    private final String USER_DOING_HOUR_DISTRIBUTION_SHORT_NAME = "Distribute Hours?";
    private final String HOUR_DISTRIBUTION_PERCENT_SHORT_NAME = "Percent";
    private final String BOTH_OVERTIME_PREFERENCE = "BOT";
    

    //Remove following variables and use the ones from AbstractStrutsActionForm
//    public static final String BEGIN_JAVASCRIPT_CALL = "javascript:";
//    public static final String BEGIN_JAVASCRIPT_PARAMETERS = "('";
//    public static final String END_JAVASCRIPT_CALL = "')";
    

    public TimesheetDocumentForm() {
        super();
        setTimesheetDocument(new TimesheetDocument());
        setDatedHourDetail(new DatedHourDetail());
        getLogicExemptPropertyNames().add("datedHourDetail");
    }

    public List getAccessAuthorizations() {
        // TODO : not sure about this yet.   need to let employee access this page.
        List accessAuthorizations = new ArrayList();
        accessAuthorizations.add(OpenAccessAuthorization.class);
        return accessAuthorizations;
    }

    public void initialize() {
    }

    public void initiate() {
        setDocument(((TimesheetService)getService(getDocumentServiceClass())).initiate(new TimesheetInitiateKey(getTimesheetDocument().getDocumentHeader().getUniversityId(), getTimesheetDocument().getDocumentHeader().getPayEndDate())));
    }

    public void open() {
        if (!Utility.hasValue(getDocId()) || !Utility.hasValue(getCommand())) {
            if (!Utility.hasValue(getPayEndDate())) {
                setDocument(((TimesheetService)getService(getDocumentServiceClass())).openUserTimesheet());
            } else {
                setDocument(((TimesheetService)getService(getDocumentServiceClass())).openUserTimesheet(getPayEndDate()));
            }
        } else {
            super.open();
        }
    }

    @SuppressWarnings("unchecked")
	public void prepare() {
    	//this line was not getting hit at the right time
    	//this.timesheetDocument.setRunAsynchronously(this.isClockOnlyTimesheet() && this.isAllowTimesheetLite() && !this.isIgnoreClockOnlyStatus());
    	
        AssignmentService assignmentService = (AssignmentService)getService(AssignmentService.class);
        TimesheetService timesheetService = (TimesheetService)getService(getDocumentServiceClass());
        
        String universityId = getTimesheetDocument().getDocumentHeader().getUniversityId();
       
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.OVERTIME_EARN_CODE, OVERTIME_EARN_CODE_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.OVERTIME_HOURS, OVERTIME_HOURS_SHORT_NAME);
        getLabels().put(edu.iu.uis.hr.entity.FieldNames.ORGANIZATION_REFERENCE_ID, ORGANIZATION_REFERENCE_ID_SHORT_NAME);
        getLabels().put(Clock.USER_DOING_HOUR_DISTRIBUTION, USER_DOING_HOUR_DISTRIBUTION_SHORT_NAME);
        getLabels().put(HourDetail.HOUR_DISTRIBUTION_PERCENT_FIELD_NAME, HOUR_DISTRIBUTION_PERCENT_SHORT_NAME);

        //begin async processing clock in stuff
        if (this.isClockOnlyTimesheet()) { 
            //need to re-grab jobs (jobs not passed through the jsp any more for clock actions)
              getTimesheetDocument().setJobs(((JobService)getService(JobService.class)).getJobsWithAssignments(universityId, getTimesheetDocument().getDocumentHeader().getPayEndDate()));
        }
        //end async processing clock in stuff

        setFieldOptions(Clock.CLOCK_ASSIGNMENT, timesheetService.getDropdownClockAssignments(getTimesheetDocument()));
        NonDatabasePropertyDescriptor clockAssignmentPropertyDescriptor = (NonDatabaseStringPropertyDescriptor) getTimesheetDocument().getClock().getPropertyDescriptorMap().get(Clock.CLOCK_ASSIGNMENT);
        clockAssignmentPropertyDescriptor.setDisplayOnly(getTimesheetDocument().getClock().isOnRestrictiveClock() || ((Utility.hasValue(assignmentService.getSynchronousAssignments(universityId))) && assignmentService.getSynchronousAssignments(universityId).size() == 1));
        getTimesheetDocument().getPropertyDescriptorMap().put(Clock.CLOCK_ASSIGNMENT, clockAssignmentPropertyDescriptor);
        setFieldOptions(Assignment.ASSIGNMENT, assignmentService.getDropdownAssignments(universityId, getTimesheetDocument().getDocumentHeader().getPayEndDate(), false));
        setFieldOptions(edu.iu.uis.hr.entity.FieldNames.EARN_CODE, ((EarningService)getService(EarningService.class)).getDropdownEarnCodes(getTimesheetDocument().getDocumentHeader().getPayEndDate()));
        HourDetail hourDetail = new HourDetail();
        NonDatabasePropertyDescriptor overtimeEarnCodePropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.OVERTIME_EARN_CODE, TimeBlock.class);
        setFieldOptions(edu.iu.uis.hr.entity.FieldNames.OVERTIME_EARN_CODE, ((EarningService)getService(EarningService.class)).getDropdownWeeklyOvertimeEarnCodes(getTimesheetDocument().getDocumentHeader().getPayEndDate()));
        if (getUser().hasSupervisoryRole() || ( Utility.hasValue(getTimesheetDocument().getDocumentHeader().getDocumentLock()) && mayChooseOvertimePreference(getTimesheetDocument().getHours(), getTimesheetDocument().getDocumentHeader().getPayEndDate()))) {
            overtimeEarnCodePropertyDescriptor.setDisplayOnly(false);
        } else {
            overtimeEarnCodePropertyDescriptor.setDisplayOnly(true);
        }
        hourDetail.getPropertyDescriptorMap().put(FieldNames.OVERTIME_EARN_CODE, overtimeEarnCodePropertyDescriptor);
        if (Utility.hasValue(assignmentService.getSynchronousAssignments(universityId)) && !getTimesheetDocument().getClock().isOnRestrictiveClock()) {
            if (!Utility.hasValue(getTimesheetDocument().getClock().getClockAssignment()) || !Utility.hasValue(assignmentService.getAssignment(getTimesheetDocument().getClock().getClockAssignment(), getTimesheetDocument().getDocumentHeader().getPayEndDate())) || (Utility.hasValue(assignmentService.getAssignment(getTimesheetDocument().getClock().getClockAssignment(), getTimesheetDocument().getDocumentHeader().getPayEndDate())) && Utility.hasValue(getTimesheetDocument().getClock().getClockAssignment()) && !assignmentService.isSynchronousAssignment(assignmentService.getAssignment(getTimesheetDocument().getClock().getClockAssignment(), getTimesheetDocument().getDocumentHeader().getPayEndDate())))) {
                getTimesheetDocument().getClock().setClockAssignment(((Assignment)((assignmentService.getSynchronousAssignments(universityId))).iterator().next()).getAssignmentKeyAsOptionKey(true));
            }
        }
        
        //Hour Distribution conditions
        if (getTimesheetDocument().getClock().isUserDoingHourDistribution()) {
            List hourDistributionAssignmentOptions = assignmentService.getDropdownHourDistributionAssignments(universityId);
            if (isEditedByAdministrator()) { //keeps just those assignments that administrator has jurisdiction over
                for (Iterator iter = hourDistributionAssignmentOptions.iterator(); iter.hasNext();) {
                    if (!timesheetService.hasJurisdictionOverAssignment(getTimesheetDocument(), ((Option)iter.next()).getValue())) {
                        iter.remove();
                    }
                }
            }
            setFieldOptions(Assignment.ASSIGNMENT, hourDistributionAssignmentOptions);
            return;
        }
        if (getTimesheetDocument().getClock().isOnTheClock()) { //if not in the clock, does not display checkbox to distribute time
            getTimesheetDocument().getClock().setHourDistributionAllowed(timesheetService.isHourDistributionAllowed(getTimesheetDocument()));
        } else {
        	getTimesheetDocument().getClock().setHourDistributionAllowed(false);
        }

        if (getTimesheetDocument().getUserLocationPreferenceOffset() == null){ //No need to grab it again if it's already passed from timesheet
           getTimesheetDocument().setUserLocationPreferenceOffset((timesheetService.getOffsetFromLocation(timesheetService.getLocationFromTimesheet(getTimesheetDocument()))).toString());
        }

        if (!this.timesheetDocument.isRunAsynchronously() || timesheetDocument.getName()==null) { 
          timesheetService.finishPreparingTimesheetDocument(getTimesheetDocument());
        } else{
             timesheetDocument.setServerLocationTimeOffset(timesheetService.populateServerLocationTimeOffset());
             timesheetDocument.setServerTimeMillisAtLoad(new Long(new Date().getTime()).toString());
        }
    }


    //loops through all timeblocks checking if an assigment's work area has overtimePreference as "Both", indicating that the user should be able to get the OTCode dropdown menu
    private boolean mayChooseOvertimePreference(Hours hours, Date payEndDate){
    	Set<String> assignments = new HashSet();
    	for (Iterator hoursDetailIt=hours.getHoursDetails().iterator(); hoursDetailIt.hasNext();){
    		for (Iterator hourDetailsIt = ((HoursDetail)hoursDetailIt.next()).getHourDetails().iterator(); hourDetailsIt.hasNext();){
    			HourDetail hourDetail = (HourDetail)hourDetailsIt.next();
    			assignments.add(hourDetail.getAssignment());
    		}
    	}
    	for (String assignment: assignments){
    		if (Utility.hasValue(assignment)){
	        	Map assignmentKeyMap = ((AssignmentService)(getService(AssignmentService.class))).getAssignmentKeyMapFromCommaDelimitedList(assignment);
	        	WorkArea workArea = ((AssignmentService)(getService(AssignmentService.class))).getCurrentWorkArea((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), payEndDate);
	    		if (BOTH_OVERTIME_PREFERENCE.equals(workArea.getOvertimePreference())){
	    			return true;
	    		}
    		}
    	}
    	return false;
    }
    
    public boolean isEditedByAdministrator() {
        return ((TimesheetService)getService(getDocumentServiceClass())).isEditedByAdministrator(getTimesheetDocument());
    }

    public void clockIn() {
        ((TimesheetService)getService(getDocumentServiceClass())).clockIn(getTimesheetDocument());
    }

    public void clockOut() {
    	timesheetDocument.setRunAsynchronously(this.isClockOnlyTimesheet() && this.isAllowTimesheetLite() && !this.isIgnoreClockOnlyStatus());
        ((TimesheetService)getService(getDocumentServiceClass())).clockOut(getTimesheetDocument());
    }

    public void lunchIn() {
        ((TimesheetService)getService(getDocumentServiceClass())).lunchIn(getTimesheetDocument());
    }

    public void lunchOut() {
    	 timesheetDocument.setRunAsynchronously(this.isClockOnlyTimesheet() && this.isAllowTimesheetLite() && !this.isIgnoreClockOnlyStatus());
        ((TimesheetService)getService(getDocumentServiceClass())).lunchOut(getTimesheetDocument());
    }

    public void breakIn() {
        ((TimesheetService)getService(getDocumentServiceClass())).breakIn(getTimesheetDocument());
    }

    public void breakOut() {
        ((TimesheetService)getService(getDocumentServiceClass())).breakOut(getTimesheetDocument());
    }

    public void unlockDocument() {
        ((TimesheetService)getService(getDocumentServiceClass())).unlockDocument(getTimesheetDocument());
    }

    public void edit() {
        getTimesheetDocument().getDocumentHeader().setDocumentLock(((TimesheetService)getService(getDocumentServiceClass())).lock(getTimesheetDocument().getDocumentId()));
    }

    public void delete() {
        TimesheetService timesheetService = (TimesheetService)getService(getDocumentServiceClass()); 
        HourDetail hourDetail = (HourDetail) getTypedPersistentMaintainedEntityList().get(getIndex());

        // check to make sure they have jurisdiction to delete
        boolean hasJurisdiction = true;
        if (timesheetService.isEditedByAdministrator(getTimesheetDocument())) {
            hasJurisdiction = timesheetService.isAuthorized(getUser(), hourDetail);
        }
        
        if(hasJurisdiction && !hourDetail.isErroneous()) {
            super.delete();
        }
    }

    public boolean hourDistribution() {
        setTimesheetDocument(((TimesheetService)getService(getDocumentServiceClass())).distributeHours(getTimesheetDocument()));
        if(getTimesheetDocument().isErroneous()) {
            return false;
        }
        return true;
    }

    public void hourDistributionCancel() {
        setTimesheetDocument(((TimesheetService)getService(getDocumentServiceClass())).getTimesheetDocument(getTimesheetDocument().getDocumentId()));
        getTimesheetDocument().getClock().setUserDoingHourDistribution(false);
    }

    public boolean prepareHourDetailCopy() {
        TimesheetService timesheetService = (TimesheetService)getService(getDocumentServiceClass()); 
        HourDetail hourDetail = (HourDetail) getTypedPersistentMaintainedEntityList().get(getIndex());
        hourDetail.getMode().setEditable(false);
        getDatedHourDetail().setHourDetail(hourDetail);
        // create the list of dates
        prepareCheckedPayCalendarDates(hourDetail);
        // check authorization
        boolean isAuthorized = true;
        if (timesheetService.isEditedByAdministrator(getTimesheetDocument())) {
            isAuthorized = timesheetService.isAuthorized(getUser(), hourDetail); 
        } else {
            isAuthorized = timesheetService.isAuthorized(hourDetail);
        }
        return isAuthorized;
    }

    public void copyHourDetails() {
        TimesheetService timesheetService = (TimesheetService)getService(getDocumentServiceClass());
        timesheetService.createHourDetails(getDatedHourDetail().getCheckedPayCalendarDates(), getTimesheetDocument(), getDatedHourDetail().getHourDetail());
        setTimesheetDocument(getTimesheetDocument().getHours(), getTimesheetDocument().getDocumentHeader().getDocumentLock());
    }
    
    public void returnToTimesheet() {
        setTimesheetDocument(getTimesheetDocument().getHours(), getTimesheetDocument().getDocumentHeader().getDocumentLock());
    }

    public String getClockInUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.CLOCK_IN_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getClockOutUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.CLOCK_OUT_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getLunchInUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.LUNCH_IN_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getLunchOutUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.LUNCH_OUT_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getBreakInUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.BREAK_IN_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getBreakOutUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.BREAK_OUT_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getEditUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.EDIT_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getCopyUrl() {
        return new StringBuffer(BEGIN_POST_TO_OPERATION).append(getPageBaseUrl(TimesheetDocumentForm.class)).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.COPY_DISPATCH_PARAM_VALUE)).toString();
    }

    public String getEditLink() {
        return (String)getLabels().get(TimesheetDocumentAction.EDIT_DISPATCH_PARAM_VALUE);
    }

    public String getunlockDocumentUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.UNLOCK_DOCUMENT_DISPATCH_PARAM_VALUE)).toString());
    }

    public String getunlockDocumentLink() {
        return (String)getLabels().get(TimesheetDocumentAction.UNLOCK_DOCUMENT_DISPATCH_PARAM_VALUE);
    }

    public String getHourDistributionUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.HOUR_DISTRIBUTION_DISPATCH_PARAM_VALUE)).toString());
        //return new StringBuffer(BEGIN_JAVASCRIPT_CALL).append(TimesheetDocumentAction.HOUR_DISTRIBUTION_DISPATCH_PARAM_VALUE).append(BEGIN_JAVASCRIPT_PARAMETERS).append(getPageBaseUrl(TimesheetDocumentForm.class)).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.HOUR_DISTRIBUTION_DISPATCH_PARAM_VALUE)).append(END_JAVASCRIPT_CALL).toString();
    }

    public String getHourDistributionCancelUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, TimesheetDocumentAction.HOUR_DISTRIBUTION_CANCEL_DISPATCH_PARAM_VALUE)).toString());
    }

    protected Class getDocumentServiceClass() {
        return TimesheetService.class;
    }

    protected TypedPersistentMaintainedEntityList getDefaultMaintainableList() {
        throw new UnsupportedOperationException("TimesheetDocumentForm does not implement getDefualtMaintainableList()");
    }

    public void setLookupResults(List lookupResults) {
        throw new UnsupportedOperationException("TimesheetDocumentForm does not implement setLookupResults(List lookupResults)");
    }

    public void setDocument(Document document) {
        if (document != null && document instanceof TimesheetDocument) {
            setTimesheetDocument((TimesheetDocument)document);
        }
    }

    public Document getDocument() {
        return getTimesheetDocument();
    }

    public TimesheetDocument getTimesheetDocument() {
        return timesheetDocument;
    }

    public void setTimesheetDocument(TimesheetDocument timesheetDocument) {
        if (timesheetDocument != null) {
            this.timesheetDocument = timesheetDocument;
        }
    }

    public Class getModeAuthorization() {
        return TimesheetDocumentFormModeAuthorization.class;
    }

    public boolean isEditAllowed() {
        return editAllowed;
    }

    public void setEditAllowed(boolean editAllowed) {
        this.editAllowed = editAllowed;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        if (payEndDate != null) {
            this.payEndDate = payEndDate;
            payEndDateStr = payEndDate.toString();
        }
    }

    public String getPayEndDateStr() {
        return payEndDateStr;
    }

    public void setPayEndDateStr(String payEndDateStr) {
        if (payEndDateStr != null) {
            this.payEndDateStr = payEndDateStr;
            setPayEndDate(new TimelessDate(payEndDateStr).getDate());
        }
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getScrollx() {
        return scrollx;
    }

    public void setScrollx(String scrollx) {
        this.scrollx = scrollx;
    }

    public String getScrolly() {
        return scrolly;
    }

    public void setScrolly(String scrolly) {
        this.scrolly = scrolly;
    }

    public User getUser() {
        if (user == null) {
            WebUserService webUserService = (WebUserService)getService(WebUserService.class);
            if (Utility.hasValue(getCardId())) {
                UserSession userSession = webUserService.getUserSession();
                setUser((User)userSession.getUser());
            } else {
                setUser((User)webUserService.getUser());
            }
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getCopyHourDetailsToFormUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, "copyHourDetails")).toString());
    }
    
    public String getReturnToTimesheetUrl() {
        return getPostUrl(new StringBuffer(getPageBaseUrl()).append(edu.iu.uis.hr.client.Utility.QUESTION_MARK).append(edu.iu.uis.hr.client.Utility.getParameterQueryString(StrutsDispatchParamConstants.DISPATCH_PARAMETER, "returnToTimesheet")).toString());
    }

    public DatedHourDetail getDatedHourDetail() {
        return datedHourDetail;
    }

    public void setDatedHourDetail(DatedHourDetail datedHourDetail) {
        if (datedHourDetail != null) {
            this.datedHourDetail = datedHourDetail;
        }
    }
    
    private void prepareCheckedPayCalendarDates(HourDetail hourDetail) {
        getLabels().put("displayDate", "Date");
        getLabels().put("checked", "Select");
        PayCalendar payCalendar = getTimesheetDocument().getHours().getPayCalendar();
        for(Iterator payDatesIterator = payCalendar.getDatesInPayPeriod().iterator(); payDatesIterator.hasNext();){
            Date date = (Date)payDatesIterator.next();
            CheckedPayCalendarDate checkedPayCalendarDate = new CheckedPayCalendarDate(date);
            // disable checking of the copy from checkedDate
            if(date.compareTo(hourDetail.getHoursDetail().getDate()) == 0) {
                checkedPayCalendarDate.getMode().setEditable(false);
            } else {
                checkedPayCalendarDate.getMode().setEditable(true);
            }
            getDatedHourDetail().getCheckedPayCalendarDates().add(checkedPayCalendarDate);
        }
    }
    
    private void setTimesheetDocument(Hours hours, DocumentLock lock) {
        TimesheetService timesheetService = (TimesheetService)getService(getDocumentServiceClass()); 
        TimesheetDocument savedTimesheetDocument = timesheetService.getTimesheetDocument(timesheetDocument.getDocumentId());
        savedTimesheetDocument.setHours(hours);
        setTimesheetDocument(savedTimesheetDocument);
        getTimesheetDocument().getDocumentHeader().setDocumentLock(lock);
    }
    
    

	public boolean isClockOnlyTimesheet() {
		return  TKServiceLocator.getAssignmentService().hasSynchronousAssignment(getUser().getUniversityId());
	}

//	public void setClockOnlyTimesheet(boolean clockOnlyTimesheet) {
//		this.clockOnlyTimesheet = clockOnlyTimesheet;
//	}

	public boolean isIgnoreClockOnlyStatus() {
		return ignoreClockOnlyStatus;
	}

	public void setIgnoreClockOnlyStatus(boolean ignoreClockOnlyStatus) {
		this.ignoreClockOnlyStatus = ignoreClockOnlyStatus;
	}

	public boolean isKioskUser() {
		return kioskUser;
	}

	public void setKioskUser(boolean kioskUser) {
		this.kioskUser = kioskUser;
	}

	public boolean isAllowTimesheetLite() {
		return allowTimesheetLite;
	}

	public void setAllowTimesheetLite(boolean allowTimesheetLite) {
		this.allowTimesheetLite = allowTimesheetLite;
	}
	
	
}