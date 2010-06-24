package edu.iu.uis.hr.tk.timesheet.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.ApproveEvent;
import edu.iu.uis.hr.Event;
import edu.iu.uis.hr.OpenEvent;
import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.WorkflowDocumentElement;
import edu.iu.uis.hr.client.NonDatabasePropertyDescriptor;
import edu.iu.uis.hr.client.NonDatabaseStringPropertyDescriptor;
import edu.iu.uis.hr.entity.AbstractDocument;
import edu.iu.uis.hr.entity.AbstractPersistentDatabaseEntity;
import edu.iu.uis.hr.entity.Document;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.DepartmentElement;
import edu.iu.uis.hr.tk.DepartmentsElement;
import edu.iu.uis.hr.tk.DocumentElement;
import edu.iu.uis.hr.tk.EmployeeTypeElement;
import edu.iu.uis.hr.tk.PayEndDateElement;
import edu.iu.uis.hr.tk.SalaryPlanElement;
import edu.iu.uis.hr.tk.WorkAreaElement;
import edu.iu.uis.hr.tk.employee.entity.LeaveBalance;
import edu.iu.uis.hr.tk.job.entity.EarningsDistribution;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.timesheet.client.EditEvent;
import edu.iu.uis.hr.tk.timesheet.entity.logic.AssignmentAuthorizationSaveLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ClockModeLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.EarnCodePerTimeBlockLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ExceedLeaveBalanceLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.InvalidHoursLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.InvalidWorkingAssignmentLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.IsReadyToApproveLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.OverlappingTimeBlocksLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.SetTimeZoneLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.TimeBlockSpansMidnightLogic;
import edu.iu.uis.hr.tk.timesheet.entity.logic.ValidPtoForManualShiftExistLogic;

@SuppressWarnings({ "serial", "unchecked" })
public class TimesheetDocument extends AbstractDocument implements Document {

	public static final String TIMESHEET_DOCUMENT_TYPE = "Timesheet";
	public static final String TIMESHEET_DOCUMENT_TITLE = "Timesheet";
	public static final String ASSIGNMENT_EARN_CODES = "assignmentEarnCodes";
	public static final String ASSIGNMENT_TIME_OFFSETS = "assignmentTimeOffsets";
	public static final String SERVER_LOCATION_TIME_OFFSET = "serverLocationTimeOffset";
	public static final String SERVER_TIME_MILLIS_AT_LOAD = "serverTimeMillisAtLoad";
	public static final String USER_LOCATION_PREFERENCE_OFFSET = "userLocationPreferenceOffset";
	public static final String HOURS_ENTRY_EARN_CODES = "hoursEntryEarnCodes";
	private static final String NAME_PROPERTY_DESCRIPTOR_FIELD = "name";
	private static final int NAME_PROPERTY_DESCRIPTOR_LENGTH = 50;

	protected static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
	static {
		LOGIC_EXEMPT_PROPERTY_NAMES.add("jobs");
		LOGIC_EXEMPT_PROPERTY_NAMES.add("hoursSummary");
		LOGIC_EXEMPT_PROPERTY_NAMES.add("leaveBalances");
		LOGIC_EXEMPT_PROPERTY_NAMES.add(ASSIGNMENT_EARN_CODES);
		LOGIC_EXEMPT_PROPERTY_NAMES.add(ASSIGNMENT_TIME_OFFSETS);
		LOGIC_EXEMPT_PROPERTY_NAMES.add(HOURS_ENTRY_EARN_CODES);
		LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.BEGIN_TIME);
		LOGIC_EXEMPT_PROPERTY_NAMES.add(FieldNames.END_TIME);
		LOGIC_EXEMPT_PROPERTY_NAMES.addAll(AbstractDocument.LOGIC_EXEMPT_PROPERTY_NAMES);
		LOGIC_EXEMPT_PROPERTY_NAMES.add("timeBlockToDistribute");
		LOGIC_EXEMPT_PROPERTY_NAMES.add("timeBlockToDistributeLists");
	}

	private static final Map CUSTOM_PROPERTY_DESCRIPTORS = new HashMap();
	static {
		NonDatabaseStringPropertyDescriptor assignmentEarnCodePropertyDescriptor = new NonDatabaseStringPropertyDescriptor(ASSIGNMENT_EARN_CODES, 100000);
		assignmentEarnCodePropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(TimesheetDocument.ASSIGNMENT_EARN_CODES, assignmentEarnCodePropertyDescriptor);
		NonDatabaseStringPropertyDescriptor serverTimeMillisAtLoadPropertyDescriptor = new NonDatabaseStringPropertyDescriptor(SERVER_TIME_MILLIS_AT_LOAD, 100);
		serverTimeMillisAtLoadPropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(TimesheetDocument.SERVER_TIME_MILLIS_AT_LOAD, serverTimeMillisAtLoadPropertyDescriptor);
		NonDatabaseStringPropertyDescriptor assignmentTimeOffsetsPropertyDescriptor = new NonDatabaseStringPropertyDescriptor(ASSIGNMENT_TIME_OFFSETS, 100000);
		assignmentTimeOffsetsPropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(TimesheetDocument.ASSIGNMENT_TIME_OFFSETS, assignmentTimeOffsetsPropertyDescriptor);
		NonDatabaseStringPropertyDescriptor serverLocationTimeOffsetPropertyDescriptor = new NonDatabaseStringPropertyDescriptor(SERVER_LOCATION_TIME_OFFSET, 100);
		serverLocationTimeOffsetPropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(TimesheetDocument.SERVER_LOCATION_TIME_OFFSET, serverLocationTimeOffsetPropertyDescriptor);

		NonDatabaseStringPropertyDescriptor userLocationPreferenceOffsetPropertyDescriptor = new NonDatabaseStringPropertyDescriptor(USER_LOCATION_PREFERENCE_OFFSET, 100);
		userLocationPreferenceOffsetPropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(TimesheetDocument.USER_LOCATION_PREFERENCE_OFFSET, serverLocationTimeOffsetPropertyDescriptor);

		NonDatabaseStringPropertyDescriptor hoursEntryEarnCodePropertyDescriptor = new NonDatabaseStringPropertyDescriptor(HOURS_ENTRY_EARN_CODES, 1000);
		hoursEntryEarnCodePropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(TimesheetDocument.HOURS_ENTRY_EARN_CODES, hoursEntryEarnCodePropertyDescriptor);
		NonDatabasePropertyDescriptor documentIdPropertyDescriptor = AbstractPersistentDatabaseEntity.getNonDatabasePropertyDescriptor(FieldNames.DOCUMENT_ID, DocumentHeader.class);
		documentIdPropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(FieldNames.DOCUMENT_ID, documentIdPropertyDescriptor);
		NonDatabaseStringPropertyDescriptor namePropertyDescriptor = new NonDatabaseStringPropertyDescriptor(NAME_PROPERTY_DESCRIPTOR_FIELD, NAME_PROPERTY_DESCRIPTOR_LENGTH);
		namePropertyDescriptor.setDisplayOnly(true);
		CUSTOM_PROPERTY_DESCRIPTORS.put(NAME_PROPERTY_DESCRIPTOR_FIELD, namePropertyDescriptor);
	}

	private String name;
	private DocumentHeader documentHeader;
	private Clock clock;
	private Hours hours;
	private HoursSummary hoursSummary;
	private TypedPersistentMaintainedEntityList leaveBalances;
	private TypedPersistentMaintainedEntityList jobs;
	private String assignmentEarnCodes;
	private String hoursEntryEarnCodes;
	private String assignmentTimeOffsets;
	private String serverLocationTimeOffset;
	private String serverTimeMillisAtLoad;
	private String userLocationPreferenceOffset;
	private boolean runAsynchronously;
	
	public HoursDetailToDistribute timeBlockToDistribute;
	public TypedPersistentMaintainedEntityList timeBlockToDistributeLists = new TypedPersistentMaintainedEntityList(HoursDetailToDistribute.class);

	public TimesheetDocument() {
		super();
		setDocumentHeader(new DocumentHeader());
		setClock(new Clock());
		setJobs(new TypedPersistentMaintainedEntityList(Job.class));
		setLeaveBalances(new TypedPersistentMaintainedEntityList(LeaveBalance.class));

	}

	public TimesheetDocument(DocumentHeader documentHeader) {
		this();
		setDocumentHeader(documentHeader);
		setDocumentId(documentHeader.getDocumentId());
	}

	public TimesheetDocument(DocumentHeader documentHeader, Clock clock, Hours hoursDetail, TypedPersistentMaintainedEntityList jobs, TypedPersistentMaintainedEntityList leaveSummary) {
		this(documentHeader);
		setClock(clock);
		setHours(hoursDetail);
		setJobs(jobs);
		setLeaveBalances(leaveSummary);
	}

	public List getLogicExemptPropertyNames() {
		return LOGIC_EXEMPT_PROPERTY_NAMES;
	}

	public Map getPropertyDescriptorMap() {
		CUSTOM_PROPERTY_DESCRIPTORS.putAll(super.getPropertyDescriptorMap());
		return CUSTOM_PROPERTY_DESCRIPTORS;
	}

	protected List getOperationalLogics(OpenEvent event) {
		List operationalLogics = new ArrayList();
		operationalLogics.add(InvalidWorkingAssignmentLogic.class);
		operationalLogics.add(InvalidHoursLogic.class);
		operationalLogics.add(OverlappingTimeBlocksLogic.class);
		operationalLogics.add(EarnCodePerTimeBlockLogic.class);
		operationalLogics.add(ClockModeLogic.class);
		return operationalLogics;
	}

	protected List getOperationalLogics(SaveEvent event) {
		List operationalLogics = new ArrayList();
		operationalLogics.add(InvalidHoursLogic.class);
		operationalLogics.add(SetTimeZoneLogic.class);
		operationalLogics.add(OverlappingTimeBlocksLogic.class);
		operationalLogics.add(TimeBlockSpansMidnightLogic.class);
		operationalLogics.add(ValidPtoForManualShiftExistLogic.class);
		operationalLogics.add(ExceedLeaveBalanceLogic.class);
		operationalLogics.add(EarnCodePerTimeBlockLogic.class);
		operationalLogics.add(AssignmentAuthorizationSaveLogic.class);
		return operationalLogics;
	}

	protected List getOperationalLogics(ApproveEvent event) {
		List operationalLogics = new ArrayList();
		operationalLogics.add(OverlappingTimeBlocksLogic.class);
		operationalLogics.add(IsReadyToApproveLogic.class);
		return operationalLogics;
	}

	protected List getUncommonOperationalLogics(Event event) {
		List operationalLogics = new ArrayList();
		if (event instanceof EditEvent) {
			operationalLogics.add(InvalidWorkingAssignmentLogic.class);
			operationalLogics.add(InvalidHoursLogic.class);
			operationalLogics.add(OverlappingTimeBlocksLogic.class);
			operationalLogics.add(EarnCodePerTimeBlockLogic.class);
		}
		return operationalLogics;
	}

	public String getDocumentType() {
		return TimesheetDocument.TIMESHEET_DOCUMENT_TYPE;
	}
	
	private static String JOB_EMPL_RCD_THREAD_LOCAL_KEY = "TIMESHEET_JOB_EMPL_RCD_THREAD_LOCAL_KEY";

	private String buildKey(BigDecimal employeeRecord, Timestamp beginTime) {
		return JOB_EMPL_RCD_THREAD_LOCAL_KEY + employeeRecord + beginTime;
	}
	
	public Job getJob(BigDecimal employeeRecord, Timestamp beginTime) {
//	    The "Context.getStorageMap()" is causing a concurrent issue, commenting it out.		
//		String key = buildKey(employeeRecord, beginTime);
//		if (Context.getStorageMap().get(JOB_EMPL_RCD_THREAD_LOCAL_KEY) != null) {
//			return (Job)Context.getStorageMap().get(JOB_EMPL_RCD_THREAD_LOCAL_KEY);
//		}
			Iterator jobsItr = getJobs().iterator();
			Job compJob = null;
			while (jobsItr.hasNext()) {
				Job job = (Job) jobsItr.next();
				Date timeBlockBegin = beginTime.getDate();
	
				if (job.getEmployeeRecord().equals(employeeRecord)) {
					if ((timeBlockBegin.after(job.getEffectiveDate()) || timeBlockBegin.equals(job.getEffectiveDate()))) {
						if (compJob == null || job.getEffectiveDate().after(compJob.getEffectiveDate()))
							compJob = job;
					}
				}
	
			}
			if (edu.iu.uis.hr.entity.logic.Utility.hasValue(compJob)) {
				//Context.getStorageMap().put(JOB_EMPL_RCD_THREAD_LOCAL_KEY, compJob);
				return compJob;
			}
			else {
				//Context.getStorageMap().put(JOB_EMPL_RCD_THREAD_LOCAL_KEY, new Job());
				return new Job();
			}
	}

	public Assignment getAssignment(BigDecimal employeeRecord, BigDecimal workArea, BigDecimal task, Timestamp beginTime) {
		Job workingJob = getJob(employeeRecord, beginTime);
		if (Utility.hasValue(workingJob)) {
			return getJob(employeeRecord, beginTime).getAssignment(workArea, task);
		}
		return null;
	}

	public WorkflowDocumentElement getDocumentElement() {
		// set up maps
		Map departmentWorkAreaListMap = getAssignmentDepartmentWorkAreaListMap();
		Map departmentSalaryPlanMap = getDepartmentSalaryPlanMap();
		Map departmentEmployeeTypeMap = getDepartmentEmployeeTypeMap();
		// root
		DocumentElement documentElement = new DocumentElement();
		
        // departments
		DepartmentsElement departmentsElement = new DepartmentsElement();
		documentElement.getChildElements().add(departmentsElement);
		Iterator departments = departmentWorkAreaListMap.keySet().iterator();
		while (departments.hasNext()) {
			// department
			String department = (String) departments.next();
			DepartmentElement departmentElement = new DepartmentElement(department);
			
			// work areas
			Iterator workAreas = ((List) departmentWorkAreaListMap.get(department)).iterator();
			while (workAreas.hasNext()) {
				WorkAreaElement workAreaElement = new WorkAreaElement((String) workAreas.next());
				departmentElement.getChildElements().add(workAreaElement);
			}
			
			// sal plan
			String salPlan = (String)departmentSalaryPlanMap.get(department);
			SalaryPlanElement salaryPlanElement = new SalaryPlanElement(salPlan);
			departmentElement.getChildElements().add(salaryPlanElement);
			// emp type
			String employeeType = (String) departmentEmployeeTypeMap.get(department);
			EmployeeTypeElement employeeTypeElement = new EmployeeTypeElement(employeeType);
			departmentElement.getChildElements().add(employeeTypeElement);
			departmentsElement.getChildElements().add(departmentElement);
		}
		// pay end date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = dateFormatter.format(this.getDocumentHeader().getPayEndDate());
        PayEndDateElement payEndDateElement = new PayEndDateElement(dateString);
        documentElement.getChildElements().add(payEndDateElement);
        
		return documentElement;
	}

	
	private Map getDepartmentEmployeeTypeMap() {
		Map departmentEmployeeTypeMap = new HashMap();
		Iterator jobs = getJobs().iterator();
		while (jobs.hasNext()) {
			Job job = (Job) jobs.next();
			departmentEmployeeTypeMap.put(job.getDepartment(), job.getEmployeeType());
		}
		return departmentEmployeeTypeMap;
	}
	
	
	private Map getDepartmentSalaryPlanMap() {
		Map departmentWorkAreaListMap = new HashMap();
		Iterator jobs = getJobs().iterator();
		while (jobs.hasNext()) {
			Job job = (Job) jobs.next();
			departmentWorkAreaListMap.put(job.getDepartment(), job.getSalaryPlan());
		}
		return departmentWorkAreaListMap;
	}

	@SuppressWarnings("unchecked")
	private Map getAssignmentDepartmentWorkAreaListMap() {
		Map assignmentDepartmentWorkAreaListMap = new HashMap();
		Iterator jobs = getJobs().iterator();
		while (jobs.hasNext()) {
			Job job = (Job) jobs.next();
			Iterator assignments = job.getAssignments().iterator();
			while (assignments.hasNext()) {
				Assignment assignment = (Assignment) assignments.next();
				if (assignmentDepartmentWorkAreaListMap.containsKey(job.getDepartment())) {
					if (Utility.hasValue(assignment.getWorkArea()) && !((List) assignmentDepartmentWorkAreaListMap.get(job.getDepartment())).contains(assignment.getWorkArea().toString())) {
						((List) assignmentDepartmentWorkAreaListMap.get(job.getDepartment())).add(assignment.getWorkArea().toString());
					}
				} else {
					if (Utility.hasValue(assignment.getWorkArea())) {
						List workAreas = new ArrayList();
						workAreas.add(assignment.getWorkArea().toString());
						assignmentDepartmentWorkAreaListMap.put(job.getDepartment(), workAreas);
					}
				}
			}
		}
		return assignmentDepartmentWorkAreaListMap;
	}

	public DocumentHeader getDocumentHeader() {
		return documentHeader;
	}

	public void setDocumentHeader(DocumentHeader documentHeader) {
		if (documentHeader != null) {
			this.documentHeader = documentHeader;
		}
	}

	public TypedPersistentMaintainedEntityList getJobs() {
		return jobs;
	}

	public void setJobs(TypedPersistentMaintainedEntityList jobs) {
		if (jobs != null) {
			this.jobs = jobs;
		}
	}

	public Job getJob(int index) {
		return (Job) ((TypedPersistentMaintainedEntityList) getJobs()).get(index);
	}

	public void setJob(int index, Job job) {
		((TypedPersistentMaintainedEntityList) getJobs()).add(index, job);
	}

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		if (clock != null) {
			this.clock = clock;
		}
	}

	public Hours getHours() {
		return hours;
	}

	public void setHours(Hours hours) {
		if (hours != null) {
			this.hours = hours;
		}
	}

	public HoursSummary getHoursSummary() {
		return hoursSummary;
	}

	public void setHoursSummary(HoursSummary hoursSummary) {
		if (hoursSummary != null) {
			this.hoursSummary = hoursSummary;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public TypedPersistentMaintainedEntityList getLeaveBalances() {
		return leaveBalances;
	}

	public void setLeaveBalances(TypedPersistentMaintainedEntityList leaveAccruals) {
		this.leaveBalances = leaveAccruals;
	}

	public LeaveBalance getLeaveBalance(int index) {
		return (LeaveBalance) ((TypedPersistentMaintainedEntityList) getLeaveBalances()).get(index);
	}

	public void setLeaveBalance(int index, LeaveBalance leaveSummary) {
		((TypedPersistentMaintainedEntityList) getLeaveBalances()).add(index, leaveSummary);
	}

	public String getAssignmentEarnCodes() {
		return assignmentEarnCodes;
	}

	public void setAssignmentEarnCodes(String assignmentEarnCodes) {
		this.assignmentEarnCodes = assignmentEarnCodes;
	}

	public String getHoursEntryEarnCodes() {
		return hoursEntryEarnCodes;
	}

	public void setHoursEntryEarnCodes(String hoursEntryEarnCodes) {
		this.hoursEntryEarnCodes = hoursEntryEarnCodes;
	}

	public HoursDetailToDistribute getTimeBlockToDistribute() {
		return timeBlockToDistribute;
	}

	public void setTimeBlockToDistribute(HoursDetailToDistribute timeBlockToDistribute) {
		if (timeBlockToDistribute != null) {
			this.timeBlockToDistribute = timeBlockToDistribute;
		}
	}

	public TypedPersistentMaintainedEntityList getTimeBlockToDistributeLists() {
		return timeBlockToDistributeLists;
	}

	public void setTimeBlockToDistributeLists(TypedPersistentMaintainedEntityList timeBlockToDistribute) {
		if (timeBlockToDistribute != null) {
			this.timeBlockToDistributeLists = timeBlockToDistribute;
		}
	}

	public HourDetail getTimeBlockToDistributeList(int index) {
		return (HourDetail) getTimeBlockToDistributeLists().get(index);
	}

	public void setTimeBlockToDistributeList(int index, HourDetail timeBlockToDistribute) {
		if (timeBlockToDistribute != null) {
			getTimeBlockToDistributeLists().add(index, timeBlockToDistribute);
		}
	}

	public String getAssignmentTimeOffsets() {
		return assignmentTimeOffsets;
	}

	public void setAssignmentTimeOffsets(String assignmentTimeOffsets) {
		this.assignmentTimeOffsets = assignmentTimeOffsets;
	}

	public String getServerLocationTimeOffset() {
		return serverLocationTimeOffset;
	}

	public void setServerLocationTimeOffset(String serverLocationTimeOffset) {
		this.serverLocationTimeOffset = serverLocationTimeOffset;
	}

	public String getServerTimeMillisAtLoad() {
		return serverTimeMillisAtLoad;
	}

	public void setServerTimeMillisAtLoad(String serverTimeMillisAtLoad) {
		this.serverTimeMillisAtLoad = serverTimeMillisAtLoad;
	}

	public String getUserLocationPreferenceOffset() {
		return userLocationPreferenceOffset;
	}

	public void setUserLocationPreferenceOffset(String userLocationPreferenceOffset) {
		this.userLocationPreferenceOffset = userLocationPreferenceOffset;
	}

	public void checkLeaveStatus() {
		//this method sets a timesheet warning if the earnings distribution indicates a leave status (reduces expected RGN hours)
		//code was mostly lifted from isReadyToApproveLogic for a last minute fix
		try {
			for (Iterator iter = getJobs().iterator(); iter.hasNext();) {
				Job job = (Job) iter.next();
				double regularEarningsDistributionPercent = 100; // default value if no distribution defined
				if (Utility.hasValue(job.getEarningsDistributions())) { //avoiding NPE for null values
					for (Iterator iterator = job.getEarningsDistributions().iterator(); iterator.hasNext();) {
						regularEarningsDistributionPercent = 0.0; // if distribution defined and no reg earnings get found, assume 100% Leave and a dist pct of 0.
						EarningsDistribution earningsDistribution = (EarningsDistribution) iterator.next();
						if (earningsDistribution.getEarnCode().startsWith("R")) { //TODO: can we look for reg earnings with this criteria?
							regularEarningsDistributionPercent = earningsDistribution.getDistributionPercent().doubleValue();
							break; // found a value, so stop looking
						}
					}
				}
				if (regularEarningsDistributionPercent < 100) {
					double expectedHours = job.getStandardHours().doubleValue() * (regularEarningsDistributionPercent / 100);
					getEntityWarnings().add(new String[] { "timesheetDocument" }, "Expected hours have been reduced from " + job.getStandardHours().doubleValue() + " to " + expectedHours + " due to a " + (100 - regularEarningsDistributionPercent)	+ "% leave status.");
				}
			}
		} catch (RuntimeException e) {
			// code could bomb if job not saved to PS - new hire enroute - just abort
		}
		return;
	}

	public boolean isRunAsynchronously() {
		return runAsynchronously;
	}

	public void setRunAsynchronously(boolean runAsynchronously) {
		this.runAsynchronously = runAsynchronously;
	}

	
}