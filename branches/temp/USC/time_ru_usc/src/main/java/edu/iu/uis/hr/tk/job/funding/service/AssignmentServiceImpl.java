package edu.iu.uis.hr.tk.job.funding.service;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.Option;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.job.funding.entity.Task;
import edu.iu.uis.hr.job.funding.entity.WorkArea;
import edu.iu.uis.hr.tk.job.entity.Job;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.job.service.JobService;
import edu.iu.uis.hr.tk.log.LogPerformanceMethod;
import edu.iu.uis.hr.tk.rule.entity.TimeCollectionRule;
import edu.iu.uis.hr.tk.rule.service.RuleService;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public class AssignmentServiceImpl extends edu.iu.uis.hr.job.funding.service.AssignmentServiceImpl implements AssignmentService {
	
	private static final Logger LOG = Logger.getLogger(AssignmentServiceImpl.class);
    private JobService jobService;
    private RuleService ruleService;

    
    @SuppressWarnings("unchecked")
	public Map getAssignmentKeyMapFromCommaDelimitedList(String assignmentKeyCommaDelimitedList) {
        Map assignmentKeys = new HashMap();
        if (Utility.hasValue(assignmentKeyCommaDelimitedList)) {
            String[] keys = assignmentKeyCommaDelimitedList.split(edu.iu.uis.hr.Utility.COMMA);
            assignmentKeys.put(FieldNames.UNIVERSITY_ID, keys[0]);
            assignmentKeys.put(FieldNames.EMPLOYEE_RECORD, new BigDecimal(keys[1]));
            assignmentKeys.put(FieldNames.WORK_AREA, new BigDecimal(keys[2]));
            assignmentKeys.put(FieldNames.TASK, new BigDecimal(keys[3]));
        }
        return assignmentKeys;
    }

    @SuppressWarnings("unchecked")
	public boolean hasAssignment(String universityId) {
        Iterator jobsItr = getJobService().getJobs(universityId, new Date()).iterator();
        while (jobsItr.hasNext()) {
            if (Utility.hasValue(((Job)jobsItr.next()).getAssignments())) {
                return true;
            }
        }
        return false;
    }

    @CacheResult
    public boolean hasSynchronousAssignment(String universityId) {
        if (Utility.hasValue(getSynchronousAssignments(universityId))) {
            return true;
        }
        return false;
    }

    @CacheResult
    public boolean hasAsynchronousAssignment(String universityId) {
        List asynchronousAssignments = getAsynchronousAssignments(universityId);
        if (Utility.hasValue(asynchronousAssignments)) {
            return true;
        }
        return false;
    }

    public boolean containsAsynchronousAssignment(List jobs) {
        Iterator jobsItr = jobs.iterator();
        while (jobsItr.hasNext()) {
            Job job = (Job)jobsItr.next();

            if (Utility.hasValue(job.getAssignments())) {
                if (Utility.hasValue(getAsynchronousAssignments(job.getAssignments()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsSynchronousAssignment(List jobs) {
        Iterator jobsItr = jobs.iterator();
        while (jobsItr.hasNext()) {
            Job job = (Job)jobsItr.next();
            if (Utility.hasValue(job.getAssignments())) {
                if (Utility.hasValue(getSynchronousAssignments(job.getAssignments()))) {
                    return true;
                }
            }
        }
        return false;
    }

   @SuppressWarnings("unchecked")
   public List getSynchronousAssignments(String universityId) {
        List synchronousAssignments = new ArrayList();
        Iterator jobsItr = getJobService().getJobs(universityId, new Date()).iterator();
        while (jobsItr.hasNext()) {
            Job job = (Job)jobsItr.next();
            if (Utility.hasValue(job.getAssignments())) {
                synchronousAssignments.addAll(getSynchronousAssignments(job.getAssignments()));
            }
        }
        return synchronousAssignments;
    }
   

    public List getAsynchronousAssignments(String universityId) {
        List asynchronousAssignments = new ArrayList();
        Iterator jobsItr = getJobService().getJobs(universityId, new Date()).iterator();
        while (jobsItr.hasNext()) {
            Job job = (Job)jobsItr.next();
            if (Utility.hasValue(job.getAssignments())) {
                Iterator assignmentsItr = job.getAssignments().iterator();
                while (assignmentsItr.hasNext()) {
                    Assignment assignment = (Assignment)assignmentsItr.next();
                    TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(job.getDepartment(), assignment.getWorkArea(), new Date());
                    if (Utility.hasValue(timeCollectionRule) && !timeCollectionRule.isClockUseRequired()) {
                        asynchronousAssignments.add(assignment);
                    }
                }
            }
        }
        return asynchronousAssignments;
    }

    public Assignment getAssignment(String assignmentString, Date transactionRecordEffectiveDate) {
        Assignment assignment = null;
        Map assignmentKeyMap = getAssignmentKeyMapFromCommaDelimitedList(assignmentString);
        Job job = getJobService().getJob((String)assignmentKeyMap.get(FieldNames.UNIVERSITY_ID), (BigDecimal)assignmentKeyMap.get(FieldNames.EMPLOYEE_RECORD), transactionRecordEffectiveDate);
        if (Utility.hasValue(job)) {
            assignment = job.getAssignment((BigDecimal)assignmentKeyMap.get(FieldNames.WORK_AREA), (BigDecimal)assignmentKeyMap.get(FieldNames.TASK));
        }
        return assignment;
    }

    public List getAssignments(String universityId) {
        return getAssignments(universityId, new Date());
    }
    
    private static final Logger LOG_PERFORM = Logger.getLogger(LogPerformanceMethod.class);
    
    @SuppressWarnings("unchecked")
	public List getAssignments(String universityId, Date date) {
        List assignments = new ArrayList();
        Iterator jobsItr = getJobService().getJobs(universityId, date).iterator();
        while (jobsItr.hasNext()) {
            Job job = (Job)jobsItr.next();
            if (Utility.hasValue(job.getAssignments())) {
                assignments.addAll(job.getAssignments());
            }
        }
        return assignments;
    }
    
    //returns sync assignments as of current date    
    public List getDropdownAssignments(String universityId) {
        return getDropdownAssignments(getAssignments(universityId).iterator(), true);
    }
    
    //returns syn assignments as of timesheet's pay end date
    public List getDropdownAssignments(String universityId, Date date, boolean synchronous) {
        return getDropdownAssignments(getAssignments(universityId, date).iterator(), synchronous);
    }
    
    public List getDropdownSynchronousAssignments(String universityId) {
        return getDropdownAssignments(getSynchronousAssignments(universityId).iterator(), true);
    }

    public List getDropdownHourDistributionAssignments(String universityId) {
        return getDropdownAssignments(getHourDistributionAssignments(getSynchronousAssignments(universityId)).iterator(), false);
    }

    public String getAssignmentDescription(Assignment assignment) {

        if (Utility.hasValue(assignment.getAssignmentDescription())) {
            return assignment.getAssignmentDescription();
        }
        WorkArea workArea = getWorkArea(assignment.getWorkArea(), new Date());

        if (!Utility.hasValue(workArea)) {
            return ("Workarea " + assignment.getWorkArea() + " does not exist or is inactive");
        }

        Task task = workArea.getTask(assignment.getTask());
        String result = new String();
        String workAreaDescription = new String();
        String taskDescription = new String();

        if (Utility.hasValue(workArea.getDescription())) {
            workAreaDescription = workArea.getDescription();
        }

        if (task != null && Utility.hasValue(task.getDescription()) && task.getTask().compareTo(new BigDecimal(0)) > 0) {
            taskDescription = task.getDescription();
        }

        result = workAreaDescription.trim() + edu.iu.uis.hr.Utility.SPACE + taskDescription.trim();

        if (result.length() > 31) {

            if (workAreaDescription.length() > 15) {
                if (taskDescription.length() < 15) {
                    workAreaDescription = workAreaDescription.substring(0, 30 - taskDescription.length());
                } else {
                    workAreaDescription = workAreaDescription.substring(0, 15);
                }
            }

            result = workAreaDescription.trim() + edu.iu.uis.hr.Utility.SPACE + taskDescription.trim();

            if (result.length() > 31) {
                result = result.substring(0, 31);
            }
        }
        result = result.trim() + edu.iu.uis.hr.Utility.COLON + edu.iu.uis.hr.Utility.SPACE + new DecimalFormatSymbols().getCurrencySymbol() + assignment.getJob().getCompensationRate() + PER_HOUR + edu.iu.uis.hr.Utility.SPACE + RECORD_NUMBER_LABEL + assignment.getJob().getEmployeeRecord() + edu.iu.uis.hr.Utility.SPACE + assignment.getJob().getDepartment();
        assignment.setAssignmentDescription(result);
        return result;
    }

    @SuppressWarnings("unchecked")
	private List getDropdownAssignments(Iterator assignmentsItr, boolean synchronous) {
        List options = new ArrayList();
        Map optionsMap = new TreeMap();

        while (assignmentsItr.hasNext()) {
            Assignment assignment = (Assignment)assignmentsItr.next();
            optionsMap.put(assignment.getAssignmentKeyAsOptionKey(synchronous), new Option(assignment.getAssignmentKeyAsOptionKey(synchronous), getAssignmentDescription(assignment)));
        }

        Iterator optionsMapItr = optionsMap.values().iterator();
        while (optionsMapItr.hasNext()) {
            options.add((Option)optionsMapItr.next());
        }
        return options;
    }

    @SuppressWarnings("unchecked")
	private List getSynchronousAssignments(Collection assignments) {
        List synchronousAssignments = new ArrayList();
        Iterator assignmentsItr = assignments.iterator();
        while (assignmentsItr.hasNext()) {
            Assignment assignment = (Assignment)assignmentsItr.next();
            TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(assignment.getJob().getDepartment(), assignment.getWorkArea(), new Date());
            if (!Utility.hasValue(timeCollectionRule) || timeCollectionRule.isClockUseRequired()) {
                synchronousAssignments.add(assignment);
            }
        }
        return synchronousAssignments;
    }

    @SuppressWarnings("unchecked")
	private List getHourDistributionAssignments(Collection assignments) {
        List hourDistributionAssignments = new ArrayList();
        Iterator assignmentsItr = assignments.iterator();
        while (assignmentsItr.hasNext()) {
            Assignment assignment = (Assignment)assignmentsItr.next();
            TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(assignment.getJob().getDepartment(), assignment.getWorkArea(), new Date());
            if (Utility.hasValue(timeCollectionRule) && timeCollectionRule.isClockUseRequired() && timeCollectionRule.isHoursDistributionAllowed()) {
                hourDistributionAssignments.add(assignment);
            }
        }
        return hourDistributionAssignments;
    }

    public boolean isSynchronousAssignment(Assignment assignment) {
        if (Utility.hasValue(assignment.getJob())) {
            TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(assignment.getJob().getDepartment(), assignment.getWorkArea(), new Date());
            if (Utility.hasValue(timeCollectionRule)) {
                return timeCollectionRule.isClockUseRequired();
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
	private List getAsynchronousAssignments(Collection assignments) {
        List asynchronousAssignments = new ArrayList();
        Iterator assignmentsItr = assignments.iterator();
        while (assignmentsItr.hasNext()) {
            Assignment assignment = (Assignment)assignmentsItr.next();
            TimeCollectionRule timeCollectionRule = getRuleService().getTimeCollectionRule(assignment.getJob().getDepartment(), assignment.getWorkArea(), new Date());
            if (Utility.hasValue(timeCollectionRule) && !timeCollectionRule.isClockUseRequired()) {
                asynchronousAssignments.add(assignment);
            }
        }
        return asynchronousAssignments;
    }
 
    //
    /**
     * gets the effective date of the relevant assignment in order to match appropriately in the drop down 
	 * list on the timesheet if there are two assignments differentiated by effdt but no other key fields.
     */
    public String appendMatchingAssignEffectiveDate(TimeBlock timeBlock, String universityId, String timeBlockAssignKey){
    	List assignments = getDropdownAssignments(universityId, timeBlock.getBeginTime().getDate(), false);
    	Date compareDate = null;
    	String key = null;
    	int keyLength = timeBlockAssignKey.length();
    	for (Iterator iter = assignments.iterator(); iter.hasNext();) {
			Option option = (Option) iter.next();
			key = option.getValue();
			if(timeBlockAssignKey.equals(key.substring(0, keyLength))){
				try{
					SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
					if(timeBlock.getBeginTime().getDate().compareTo((Date)formatter.parse(key.substring(keyLength+1))) >= 0){
						if(Utility.hasValue(compareDate)){
							if(compareDate.before((Date)formatter.parse(key.substring(keyLength+1)))){
								compareDate = (Date)formatter.parse(key.substring(keyLength+1));
							}
						}else{
							compareDate = (Date)formatter.parse(key.substring(keyLength+1));
						}
					}
				}catch(Exception e){
					LOG.error("Error occured while parsing date - assignmentService line 300");
				}
			}
		}
    	
    	return timeBlockAssignKey + edu.iu.uis.hr.Utility.COMMA + compareDate;
    }
    
    private JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        if (jobService != null) {
            this.jobService = jobService;
        }
    }

    public RuleService getRuleService() {
        return ruleService;
    }

    public void setRuleService(RuleService ruleService) {
        if (ruleService != null) {
            this.ruleService = ruleService;
        }
    }

}