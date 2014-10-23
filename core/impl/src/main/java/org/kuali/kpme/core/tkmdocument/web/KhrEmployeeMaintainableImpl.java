/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.core.tkmdocument.web;


import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.assignment.AssignmentContract;
import org.kuali.kpme.core.api.job.Job;
import org.kuali.kpme.core.api.job.JobContract;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.api.principal.PrincipalHRAttributes;
import org.kuali.kpme.core.api.principal.PrincipalHRAttributesContract;
import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.assignment.account.AssignmentAccountBo;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.bo.HrKeyedBusinessObject;
import org.kuali.kpme.core.cache.CacheUtils;
import org.kuali.kpme.core.job.JobBo;
import org.kuali.kpme.core.principal.PrincipalHRAttributesBo;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.tkmdocument.KhrEmployeeDocument;
import org.kuali.kpme.core.tkmdocument.tkmjob.KhrEmployeeJob;
import org.kuali.kpme.core.tkmdocument.validation.KhrEmployeeDocumentValidation;
import org.kuali.kpme.core.util.HrConstants;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;

import de.danielbechler.diff.Configuration;
import de.danielbechler.diff.ObjectDiffer;
import de.danielbechler.diff.ObjectDifferFactory;
import de.danielbechler.diff.node.Node;
import de.danielbechler.diff.path.PropertyPath;

import java.sql.Timestamp;
import java.util.*;

public class KhrEmployeeMaintainableImpl extends MaintainableImpl {
    
	private static final long serialVersionUID = 1140415771858326498L;
	
	protected static Logger LOG = Logger.getLogger(KhrEmployeeMaintainableImpl.class);
	
	protected final Configuration jobDiffConfiguration = new Configuration()
													.withoutProperty(PropertyPath.buildWith("businessKeyValuesMap"))
			     									.withoutProperty(PropertyPath.buildWith("relativeEffectiveDate"))
			     									.withoutProperty(PropertyPath.buildWith("effectiveLocalDate"))
			     									.withoutProperty(PropertyPath.buildWith("userPrincipalId"))
			    
			     									.withoutProperty(PropertyPath.buildWith("groupKey"))
			     									.withoutProperty(PropertyPath.buildWith("locationObj"))
			     									.withoutProperty(PropertyPath.buildWith("location"))      
			     									.withoutProperty(PropertyPath.buildWith("institutionObj"))
			     									.withoutProperty(PropertyPath.buildWith("institution"))

	 												.withoutProperty(PropertyPath.buildWith("assignments")) 
	 												.withoutProperty(PropertyPath.buildWith("endDate")) 
    
	 												.withoutProperty(PropertyPath.buildWith("fte")) 
	 												.withoutProperty(PropertyPath.buildWith("deptObj")) 
	 												.withoutProperty(PropertyPath.buildWith("payTypeObj")) 
	 												.withoutProperty(PropertyPath.buildWith("payGradeObj")) 
	 												.withoutProperty(PropertyPath.buildWith("payTypeObj")) 
	 												.withoutProperty(PropertyPath.buildWith("salaryGroupObj")) 
	 												.withoutProperty(PropertyPath.buildWith("positionObj")) 
	 												.withoutProperty(PropertyPath.buildWith("principalName")) 
	 												.withoutProperty(PropertyPath.buildWith("name")); 
	
	protected final Configuration assignmentDiffConfiguration = new Configuration()
														.withoutProperty(PropertyPath.buildWith("businessKeyValuesMap"))
														.withoutProperty(PropertyPath.buildWith("relativeEffectiveDate"))
														.withoutProperty(PropertyPath.buildWith("effectiveLocalDate"))
														.withoutProperty(PropertyPath.buildWith("userPrincipalId"))
												
														.withoutProperty(PropertyPath.buildWith("groupKey"))
														.withoutProperty(PropertyPath.buildWith("locationObj"))
														.withoutProperty(PropertyPath.buildWith("location"))      
														.withoutProperty(PropertyPath.buildWith("institutionObj"))
														.withoutProperty(PropertyPath.buildWith("institution"))
														
														.withoutProperty(PropertyPath.buildWith("job"))	
            											.withoutProperty(PropertyPath.buildWith("workAreaObj"))
            											.withoutProperty(PropertyPath.buildWith("taskObj"))
            											.withoutProperty(PropertyPath.buildWith("payTypeObj"))
            
            											.withoutProperty(PropertyPath.buildWith("dept"))
            											.withoutProperty(PropertyPath.buildWith("assignmentDescription"))
            
            											.withoutProperty(PropertyPath.buildWith("assignmentAccounts"));
	
	protected final Configuration assignmentAccountDiffConfiguration = new Configuration()
																		.withoutProperty(PropertyPath.buildWith("finCoaCd"))
																		.withoutProperty(PropertyPath.buildWith("accountObj"))
																		.withoutProperty(PropertyPath.buildWith("subAccountObj"))
																		.withoutProperty(PropertyPath.buildWith("objectCodeObj"))
																		.withoutProperty(PropertyPath.buildWith("subObjectCodeObj"))
																		.withoutProperty(PropertyPath.buildWith("projectCodeObj"))
																		.withoutProperty(PropertyPath.buildWith("earnCodeObj"))
																		.withoutProperty(PropertyPath.buildWith("assignmentObj"));
	
	
	
    @Override
    public Object retrieveObjectForEditOrCopy(MaintenanceDocument document, Map<String, String> dataObjectKeys) {
        KhrEmployeeDocument tkmDocument = new KhrEmployeeDocument();
        String principalId = dataObjectKeys.get("principalId");

        LOG.info("Data Object Keys: " + dataObjectKeys.toString());
        //set person section
        Person person = KimApiServiceLocator.getPersonService().getPerson(principalId);
        tkmDocument.setPrincipalId(principalId);
        tkmDocument.setName(person.getName());
        //TODO: figure out Kim Override start and endDates
        tkmDocument.setStartDate(TKUtils.convertDateStringToDateTime("01/01/2010", "00:00:00").toDate());
        tkmDocument.setEndDate(null);

        //set editable department list
        tkmDocument.setEditableDepartmentList(HrServiceLocator.getKPMERoleService().getDepartmentsForPrincipalInRole(HrContext.getTargetPrincipalId(), KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_DEPARTMENT_ADMINISTRATOR.getRoleName(), LocalDate.now().toDateTimeAtStartOfDay(), true));

        //set job section
        //CacheUtils.flushCaches(JobBo.CACHE_FLUSH);
        List<Job> jobList = HrServiceLocator.getJobService().getJobs(principalId, LocalDate.now());

        List<KhrEmployeeJob> tkmJobs = new ArrayList<KhrEmployeeJob>();

        Map<String, Boolean> canEditJob = new HashMap<String, Boolean>();
        for (Job job : jobList) {
            KhrEmployeeJob tkmJob = createTKMJob(job);
            //TODO set end date

//            Job nextInactiveJob = HrServiceLocator.getJobService().getNextInactiveJob(job);
//            if (nextInactiveJob != null) {
//                tkmJob.setEndDate(nextInactiveJob.getEffectiveLocalDate().toDate());
//            }

            //add assignment list to job
            //CacheUtils.flushCaches(AssignmentBo.CACHE_FLUSH);
            tkmJob.setAssignments(ModelObjectUtils.transform(HrServiceLocator.getAssignmentService().getActiveAssignmentsForJob(principalId, tkmJob.getJobNumber(), LocalDate.now()), AssignmentBo.toAssignmentBo));
            tkmJobs.add(tkmJob);

            canEditJob.put(tkmJob.getId(), tkmJob.getCanEditJob());
        }

        //tkmDocument.setCanEditJob(canEditJob);
        tkmDocument.setJobList(tkmJobs);
        return tkmDocument;
    }

    protected boolean isUnchanged(HrBusinessObject oldHrBo, HrBusinessObject currentHrBo) {
    	boolean retVal = true;    	
    	Configuration configuration = new Configuration();
        
        if(oldHrBo instanceof JobContract) {
    		oldHrBo = createTKMJob((JobContract) oldHrBo);
    		configuration = jobDiffConfiguration;
    	}
        
        if(oldHrBo instanceof AssignmentBo) {
        	configuration = assignmentDiffConfiguration;
        }
        
    	ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance(configuration);
        final Node root = objectDiffer.compare(currentHrBo, oldHrBo);
        if(root.hasChanges()) {
        	retVal = false;
        }
        
        // check the assignment accounts collections for changes, if no changes detected in parent assignment
        if( (retVal && (oldHrBo instanceof AssignmentBo)) ) {
        	Collection<AssignmentAccountBo>  oldAccounts = ((AssignmentBo) oldHrBo).getAssignmentAccounts();
        	Collection<AssignmentAccountBo>  currentAccounts = ((AssignmentBo) currentHrBo).getAssignmentAccounts();
        	retVal = !accountsChanged(oldAccounts, currentAccounts);
        }
        return retVal;
	}
    
    
    protected boolean accountsChanged(Collection<AssignmentAccountBo> oldAccounts, Collection<AssignmentAccountBo> currentAccounts) {
    	if( (oldAccounts == null) && (currentAccounts == null) ){
    		return false;
    	}
    	if( ((oldAccounts == null) && (currentAccounts != null)) || ((oldAccounts != null) && (currentAccounts == null)) ) { 
    		return true;
    	}
    	// at this point both Accounts are non-null, check sizes are not same -- optimization
    	if(oldAccounts.size() != currentAccounts.size()) {
    		return true;
    	}
    	    	
    	// now we have two non-null collections of the same size, lets check using diff
    	for(AssignmentAccountBo oldAccount: oldAccounts) {
    		boolean foundItemMatch = false;
    		for(AssignmentAccountBo currentAccount: currentAccounts) {
    	    	
    	    	ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance(assignmentAccountDiffConfiguration);
    	        final Node root = objectDiffer.compare(currentAccount, oldAccount);
    	        if(!root.hasChanges()) {
    	        	foundItemMatch = true;
    	        	break;
    	        }
    		}
    		if(!foundItemMatch) {
    			return true;
    		}
    	}
    	return false;
	}
    

	public void saveAndVersionBusinessObject(HrBusinessObject currentHrBo) {
    	boolean saveCurrentInNewRow = true; // this flag will determine if we actually save this HR bo or not
    	// check if this is an existing BO
		if(currentHrBo.getId() != null) {
			// get the old HR BO version from DB to compare
			HrBusinessObject oldHrBO = this.getBusinessObjectService().findBySinglePrimaryKey(currentHrBo.getClass(), currentHrBo.getId());
			if(oldHrBO != null) {
				if( !(isUnchanged(oldHrBO, currentHrBo)) ) {
					//if the effective dates are the same do not create a new row just inactivate the old one
					if(currentHrBo.getEffectiveDate().equals(oldHrBO.getEffectiveDate())){
						oldHrBO.setActive(false);
						oldHrBO.setTimestamp(TKUtils.subtractOneSecondFromTimestamp(new Timestamp(DateTime.now().getMillis())));
					} 
					else {
    					//if effective dates not the same, add a new row that inactivates the old entry based on the new effective date
    					oldHrBO.setTimestamp(TKUtils.subtractOneSecondFromTimestamp(new Timestamp(DateTime.now().getMillis())));
    					oldHrBO.setEffectiveDate(currentHrBo.getEffectiveDate());
    					oldHrBO.setActive(false);
    					oldHrBO.setId(null);
    					customInactiveSaveLogicNewEffective(currentHrBo);
					}

					// save the older version - will trigger either an UPDATE or an INSERT depending on effective date remaining same or not.
					KRADServiceLocator.getBusinessObjectService().save(oldHrBO);
				}
				else {
					// the old and new are the same, so no need to save as new row
					saveCurrentInNewRow = false;
				}
			}
		}
		// finally save the current BO in a new row, if so indicated
		if(saveCurrentInNewRow) {
			currentHrBo.setTimestamp(new Timestamp(System.currentTimeMillis()));
			currentHrBo.setId(null);
			currentHrBo.setUserPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
			customSaveLogic(currentHrBo);
			
			getBusinessObjectService().save(currentHrBo);			
		}
        CacheUtils.flushCache(PrincipalHRAttributesBo.CACHE_NAME);
        //JobBo list contains Assignment, and everything in the Assignment list, so we should be covered.
        CacheUtils.flushCaches(JobBo.CACHE_FLUSH);
    }
    
	protected void customInactiveSaveLogicNewEffective(HrBusinessObject currentHrBo) {
		if(currentHrBo instanceof AssignmentBo) {
			AssignmentBo bo = (AssignmentBo)currentHrBo;
	        bo.setAssignmentAccounts(new ArrayList<AssignmentAccountBo>());
		}
	}

	protected void customSaveLogic(HrBusinessObject currentHrBo) {
		// set the job number if saving a freshly added job
		if (currentHrBo instanceof KhrEmployeeJob) {
			KhrEmployeeJob currentTkmJob = (KhrEmployeeJob) currentHrBo;
			// set default value for some of the fields
			currentTkmJob.setPositionNumber("1");
			currentTkmJob.setHrPayType("XH");
			currentTkmJob.setHrSalGroup("XH");
			currentTkmJob.setPrimaryIndicator(false);
			currentTkmJob.setFlsaStatus("NE");
			
			if (currentTkmJob.getJobNumber() < 0L) {
				Long newJobNumber = getNextJobNumber();
				currentTkmJob.setJobNumber(newJobNumber);
				for (AssignmentBo assignment : currentTkmJob.getAssignments()) {
					assignment.setJobNumber(newJobNumber);
				}
			}
		}
		
		// process the accounts collection elements to be versioned as well
		if (currentHrBo instanceof AssignmentBo) {
			AssignmentBo assignment = (AssignmentBo) currentHrBo;
			for (AssignmentAccountBo assignAcct : assignment.getAssignmentAccounts()) {
				assignAcct.setTkAssignAcctId(null);
				assignAcct.setTkAssignmentId(assignment.getTkAssignmentId());
				assignAcct.setUserPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
			}
		}

	}

	public void processEndDate(KhrEmployeeJob currentTkmJob, String processString) {
		if(currentTkmJob.getEndDate() != null) {
			Date currentDate = LocalDate.now().toDate();
			if(!currentTkmJob.getEndDate().after(currentDate) && processString.equals(HrConstants.KhrEmployeeDocProcess.MAINTAIN_JOB)) {
				Date effDateToUse = currentTkmJob.getEndDateTime().plusDays(1).toDate();
				// create inactive job records
				KhrEmployeeJob inactiveJob = (KhrEmployeeJob) ObjectUtils.deepCopy(currentTkmJob);
				inactiveJob.setHrJobId(null);
				inactiveJob.setActive(false);
				inactiveJob.setEffectiveDate(effDateToUse);
				getBusinessObjectService().save(inactiveJob);
				
				// create inactive assignment records
				for(AssignmentBo assignment : currentTkmJob.getAssignments()) {
					AssignmentBo inactiveAssignment = (AssignmentBo) ObjectUtils.deepCopy(assignment);
					inactiveAssignment.setTkAssignmentId(null);
					inactiveAssignment.setActive(false);
					inactiveAssignment.setDept(assignment.getDept());
					inactiveAssignment.setEffectiveDate(effDateToUse);
					KRADServiceLocator.getBusinessObjectService().save(inactiveAssignment);
				}				
			}
		}
		CacheUtils.flushCaches(JobBo.CACHE_FLUSH);		

    }
    
    
    public void processHrPrincipalAttributes(String principalId) {
    	 List<PrincipalHRAttributesBo> activeAttributes = ModelObjectUtils.transform(HrServiceLocator.getPrincipalHRAttributeService().getAllActivePrincipalHrAttributesForPrincipalId(principalId, LocalDate.now()), PrincipalHRAttributesBo.toBo);

         if (CollectionUtils.isEmpty(activeAttributes)) {
             PrincipalHRAttributesBo newAttributes = new PrincipalHRAttributesBo();
             newAttributes.setEffectiveDate(LocalDate.now().toDate());
             newAttributes.setPrincipalId(principalId);
             newAttributes.setUserPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
             // TODO: create parameter for default calendar
             newAttributes.setPayCalendar("ISU-SEMI-MNTHLY");
             newAttributes.setActive(true);

             getBusinessObjectService().linkAndSave(newAttributes);
             CacheUtils.flushCache(PrincipalHRAttributesContract.CACHE_NAME);
         }
    }

    
    
    
    
    @Override
    public void saveDataObject() {
    	
        KhrEmployeeDocument tkmDocument = (KhrEmployeeDocument) this.getDataObject();
        List<KhrEmployeeJob> tkmJobList = tkmDocument.getJobList();
        
        // iterate thru the job list and apply the persistence logic to the jobs/end-date and assignments
        for (KhrEmployeeJob currentTkmJob : tkmJobList) {	
        	// persist the job Bo if needed
        	saveAndVersionBusinessObject(currentTkmJob);        	
        	
            processEndDate(currentTkmJob, tkmDocument.getProcess());
            
        	// persist the assignments for this job if needed
            for(AssignmentBo assignment: currentTkmJob.getAssignments()) {
            	saveAndVersionBusinessObject(assignment);
            }
            
        }

        //edit hr principal attributes
        processHrPrincipalAttributes(tkmDocument.getPrincipalId());

    }
    
    

    protected Long getNextJobNumber() {
    	KhrEmployeeDocument tkmDocument  = (KhrEmployeeDocument) this.getDataObject();
        Job maxJob = HrServiceLocator.getJobService().getMaxJob(tkmDocument.getPrincipalId());

        if(maxJob != null) {
            return (maxJob.getJobNumber() + 1);
        } 
        else {
            return 0L;
        }
    }

	@Override
    protected void processAfterAddLine(View view, CollectionGroup collectionGroup, Object model, Object addLine, boolean isValidLine) {
        super.processAfterAddLine(view, collectionGroup, model, addLine, isValidLine);

        MaintenanceDocumentForm maintenanceDocument = (MaintenanceDocumentForm) model;
        KhrEmployeeDocument tkmDocument = (KhrEmployeeDocument) maintenanceDocument.getDocument().getNewMaintainableObject().getDataObject();

        //add additional fields to job
        if (addLine instanceof KhrEmployeeJob) {
            KhrEmployeeJob addJob = (KhrEmployeeJob) addLine;
            addJob.setPrincipalId(tkmDocument.getPrincipalId());
            addJob.setActive(true);

            //set job number to negative one (represents not yet finalized)
            addJob.setJobNumber(-1L);
        }

        //add additional assignment fields
        if (addLine instanceof AssignmentBo) {
            AssignmentBo addAssignment = (AssignmentBo) addLine;

            //determine which job the assignment is being added to
            long jobNumber = 0L;
            for (KhrEmployeeJob job : tkmDocument.getJobList()) {
                if (job.getAssignments().contains(addLine)) {
                    jobNumber = job.getJobNumber();
                }
            }

            addAssignment.setPrincipalId(tkmDocument.getPrincipalId());
            addAssignment.setJobNumber(jobNumber);
            addAssignment.setActive(true);
        }

    }
    
    // set the khr employee job's field values that are not set via the "add job" user interface, but are needed for authorization.
    protected void processBeforeAddLine(View view, CollectionGroup collectionGroup, Object model, Object addLine) {
    	 if (addLine instanceof HrKeyedBusinessObject) {
    		 // TODO: create parameter for default group key code
    		 ((HrKeyedBusinessObject) addLine).setGroupKeyCode("ISU-IA");
    		 ((HrKeyedBusinessObject) addLine).setUserPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
    	 }
    	 else if(addLine instanceof AssignmentAccountBo) {
    		 ((AssignmentAccountBo) addLine).setUserPrincipalId(GlobalVariables.getUserSession().getPrincipalId());
    	 }
    }

    @Override
    protected void processAfterDeleteLine(View view, CollectionGroup collectionGroup, Object model, int lineIndex) {
        //since the delete action is only available for new lines, we don't need to delete the line from the old data object
        //which was causing errors in sub collections
    }

    @Override
    protected boolean performAddLineValidation(View view, CollectionGroup collectionGroup, Object model, Object addLine) {
        boolean isValid = true;

        if (addLine instanceof KhrEmployeeJob) {
            KhrEmployeeJob tkmJob = (KhrEmployeeJob) addLine;
            //check permission reusing the rules class
            if ( !(((new KhrEmployeeDocumentValidation())).canMaintainJob(tkmJob)) ) {
                String[] params = new String[1];
                params[0] = tkmJob.getDept();
                GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.jobList'].dept","tkmdocument.job.permissions",params);
                isValid = false;
            }
        }

        return isValid;
    }
    private KhrEmployeeJob createTKMJob(JobContract job) {
        KhrEmployeeJob tkmJob = new KhrEmployeeJob();

        //copy job to tkmjob
        tkmJob.setGroupKeyCode(job.getGroupKeyCode());
        tkmJob.setHrPayType(job.getHrPayType());
        tkmJob.setPayGrade(job.getPayGrade());
        tkmJob.setStandardHours(job.getStandardHours());
        tkmJob.setHrJobId(job.getHrJobId());
        tkmJob.setPrincipalId(job.getPrincipalId());
        tkmJob.setFirstName(job.getFirstName());
        tkmJob.setLastName(job.getLastName());
        tkmJob.setPrincipalName(job.getPrincipalName());
        tkmJob.setJobNumber(job.getJobNumber());
        tkmJob.setDept(job.getDept());
        tkmJob.setHrSalGroup(job.getHrSalGroup());
        tkmJob.setPrimaryIndicator(job.isPrimaryJob());
        tkmJob.setCompRate(job.getCompRate());
        tkmJob.setPositionNumber(job.getPositionNumber());
        tkmJob.setEligibleForLeave(job.isEligibleForLeave());
        tkmJob.setFlsaStatus(job.getFlsaStatus());
        tkmJob.setEffectiveDate(job.getEffectiveLocalDate().toDate());
        tkmJob.setVersionNumber(job.getVersionNumber());
        tkmJob.setObjectId(job.getObjectId());
        tkmJob.setActive(job.isActive());
        tkmJob.setUserPrincipalId(job.getUserPrincipalId());
        tkmJob.setEndDate(job.getEndDateTime() == null ? null: job.getEndDateTime().toDate());

        return tkmJob;
    }
}