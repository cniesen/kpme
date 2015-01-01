/**
 * Copyright 2004-2015 The Kuali Foundation
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
package org.kuali.kpme.pm.position.web;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.core.api.department.Department;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.bo.HrDataObjectMaintainableImpl;
import org.kuali.kpme.core.departmentaffiliation.DepartmentAffiliationBo;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.ValidationUtils;
import org.kuali.kpme.pm.position.PositionBo;
import org.kuali.kpme.pm.position.PositionDutyBo;
import org.kuali.kpme.pm.position.PositionQualificationBo;
import org.kuali.kpme.pm.position.PstnFlagBo;
import org.kuali.kpme.pm.position.funding.PositionFundingBo;
import org.kuali.kpme.pm.positiondepartment.PositionDepartmentBo;
import org.kuali.kpme.pm.positionresponsibility.PositionResponsibilityBo;
import org.kuali.kpme.pm.service.base.PmServiceLocator;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.principal.EntityNamePrincipalName;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.container.CollectionGroup;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;

import de.danielbechler.diff.Configuration;
import de.danielbechler.diff.ObjectDiffer;
import de.danielbechler.diff.ObjectDifferFactory;
import de.danielbechler.diff.node.CollectionNode;
import de.danielbechler.diff.node.Node;
import de.danielbechler.diff.path.PropertyPath;
import de.danielbechler.diff.visitor.Visit;

public class PositionMaintainableServiceImpl extends HrDataObjectMaintainableImpl {

	private static final long serialVersionUID = 1L;

	@Override
	public HrBusinessObject getObjectById(String id) {
		return PositionBo.from(PmServiceLocator.getPositionService().getPosition(id));
	}

    @Override
    public void customInactiveSaveLogicNewEffective(HrBusinessObject oldHrObj) {
        PositionBo bo = (PositionBo)oldHrObj;
        bo.setDepartmentList(null);
        bo.setDutyList(null);
        bo.setPositionResponsibilityList(null);
        bo.setQualificationList(null);
        bo.setFlagList(null);
        bo.setFundingList(null);
        bo.setRequiredQualList(null);
    }

    @Override
	public void customSaveLogic(HrBusinessObject hrObj){
		PositionBo aPosition = (PositionBo) hrObj;
		for(PositionQualificationBo aQual : aPosition.getQualificationList()) {
			aQual.setHrPositionId(aPosition.getHrPositionId());
			aQual.setPmQualificationId(null);
		}
		for(PositionDutyBo aDuty : aPosition.getDutyList()) {
			aDuty.setHrPositionId(aPosition.getHrPositionId());
			aDuty.setPmDutyId(null);
		}
		for(PstnFlagBo aFlag : aPosition.getFlagList()) {
			aFlag.setHrPositionId(aPosition.getHrPositionId());
			aFlag.setPmFlagId(null);
		}
		for(PositionFundingBo aFunding : aPosition.getFundingList()) {
			aFunding.setHrPositionId(aPosition.getHrPositionId());
			aFunding.setPmPositionFunctionId(null);
		}
        for(PositionDepartmentBo aDepartment : aPosition.getDepartmentList()) {
            aDepartment.setHrPositionId(aPosition.getHrPositionId());
            aDepartment.setPmPositionDeptId(null);
        }
        for(PositionResponsibilityBo aResponsibility : aPosition.getPositionResponsibilityList()) {
        	aResponsibility.setHrPositionId(aPosition.getHrPositionId());
        	aResponsibility.setPositionResponsibilityId(null);
        }
        
        // KPME-3016 populate institution and location here
        // We should be able to do this in addNewLineToCollection, but all the components are in "pages" now with the layout change, 
        // not on the form, and addNewLineToCollection doesn't get called. 
        if (aPosition.getDepartmentList() != null) {
        	for(PositionDepartmentBo aPositionDepartment : aPosition.getDepartmentList()) {
        		if(aPositionDepartment != null && aPositionDepartment.getDeptAffl() != null) {
        			DepartmentAffiliationBo pda = (DepartmentAffiliationBo)aPositionDepartment.getDeptAfflObj();
        			if (pda.isPrimaryIndicator()) {
        				aPosition.setGroupKeyCode(aPositionDepartment.getGroupKeyCode());
        				break;
        			}
        		}
        	}
        }

	}
	
	@Override
    protected boolean performAddLineValidation(View view, CollectionGroup collectionGroup, Object model,
            Object addLine) {
        boolean isValid = super.performAddLineValidation(view, collectionGroup, model, addLine);
        if (model instanceof MaintenanceDocumentForm) {
	        MaintenanceDocumentForm maintenanceForm = (MaintenanceDocumentForm) model;
	        MaintenanceDocument document = maintenanceForm.getDocument();
	        if (document.getNewMaintainableObject().getDataObject() instanceof PositionBo) {
	        	PositionBo aPosition = (PositionBo) document.getNewMaintainableObject().getDataObject();
	        	// Duty line validation
		        if (addLine instanceof PositionDutyBo) {
		        	PositionDutyBo pd = (PositionDutyBo) addLine;
		        	boolean results = this.validateDutyListPercentage(pd, aPosition);
		        	if(!results) {
		        		return false;
		        	}
		        }
	        	// Funding line validation
		        if (addLine instanceof PositionFundingBo) {
		        	PositionFundingBo pf = (PositionFundingBo) addLine;
		        	boolean results = this.validateAddFundingLine(pf, aPosition);
		        	if(!results) {
		        		return false;
		        	}
		        }
		        
		        // Responsibility validation
		        if(addLine instanceof PositionResponsibilityBo) {
		        	PositionResponsibilityBo pr = (PositionResponsibilityBo) addLine;
		        	boolean results = this.validatePositionResponsibilityListPercentage(pr, aPosition);
		        	if(!results) {
		        		return false;
		        	}
		        }
		        
		        //Department validation
		        if(addLine instanceof PositionDepartmentBo) {
		        	PositionDepartmentBo pd = (PositionDepartmentBo) addLine;
		        	boolean results = this.validateAdditionalDepartmentList(pd,aPosition);
		        	if(!results){
		        		return false;
		        	}
		        }
	        }
        }

        return isValid;
    }
	
	private boolean validateAdditionalDepartmentList(PositionDepartmentBo pd,
			PositionBo aPosition) {
		
		//Will only be validated if effective local date in position is not null
		if(aPosition.getEffectiveLocalDate()!=null && pd != null){
			Department department = HrServiceLocator.getDepartmentService().getDepartment(pd.getDepartment(), pd.getGroupKeyCode(), aPosition.getEffectiveLocalDate());
			if(department == null){
				GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.departmentList'].department", "error.existence", "Position Department '" + pd.getDepartment() + "'");
				return false;
			}
		}

		return true;
	}

	private boolean validateDutyListPercentage(PositionDutyBo pd, PositionBo aPosition) {
		if(CollectionUtils.isNotEmpty(aPosition.getDutyList()) && pd.getPercentage() != null) {
			BigDecimal sum = pd.getPercentage();
			for(PositionDutyBo aDuty : aPosition.getDutyList()) {
				if(aDuty != null && aDuty.getPercentage() != null) {
					sum = sum.add(aDuty.getPercentage());
				}
			}
			if(sum.compareTo(new BigDecimal(100)) > 0) {
				GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.dutyList'].percentage", "duty.percentage.exceedsMaximum", sum.toString());
				return false;
			}
		}		
		return true;
	}
	
	private boolean validatePositionResponsibilityListPercentage(PositionResponsibilityBo pd, PositionBo aPosition) {
		if(CollectionUtils.isNotEmpty(aPosition.getPositionResponsibilityList()) && pd.getPercentTime() != null) {
			BigDecimal sum = pd.getPercentTime();
			for(PositionResponsibilityBo aResponsibility : aPosition.getPositionResponsibilityList()) {
				if(aResponsibility != null && aResponsibility.getPercentTime() != null) {
					sum = sum.add(aResponsibility.getPercentTime());
				}
			}
			if(sum.compareTo(new BigDecimal(100)) > 0) {
				GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.positionResponsibilityList'].percentTime", "responsibility.percenttime.exceedsMaximum", sum.toString());
				return false;
			}
		}		
		return true;
	}

	protected boolean validateAddFundingLine(PositionFundingBo pf, PositionBo aPosition) {
    	if(StringUtils.isNotEmpty(pf.getAccount())) {
    		boolean results = ValidationUtils.validateAccount(pf.getChart(), pf.getAccount());
    		if(!results) {
    			GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.fundingList'].account","error.existence", "Account '" + pf.getAccount() + "'");
    			return results;
    		}
    	}
    	if(StringUtils.isNotEmpty(pf.getSubAccount())) {
    		boolean results = ValidationUtils.validateSubAccount(pf.getSubAccount(), pf.getAccount(), pf.getChart());
    		if(!results) {
	   			 GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.fundingList'].subAccount","error.existence", "Sub Account '" + pf.getSubAccount() + "'");
	   			 return results;
    		}
    	}
    	if(StringUtils.isNotEmpty(pf.getObjectCode())) {
    		boolean results = ValidationUtils.validateObjectCode(pf.getObjectCode(), pf.getChart(), null);
    		if(!results) {
    			 GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.fundingList'].objectCode","error.existence", "Object Code '" + pf.getObjectCode() + "'");
      			 return results;
    		}
    	}
    	if(StringUtils.isNotEmpty(pf.getSubObjectCode())) {
    		boolean results = ValidationUtils.validateSubObjectCode(null,
    				pf.getChart(),
    				pf.getAccount(),
    				pf.getObjectCode(),
    				pf.getSubObjectCode());
    		if(!results) {
    			 GlobalVariables.getMessageMap().putError("newCollectionLines['document.newMaintainableObject.dataObject.fundingList'].subObjectCode","error.existence", "Sub Object Code '" + pf.getSubObjectCode() + "'");
      			 return results;
    		}
    	}
    	return true;
    
	}

	
	// KPME-3016
	//set document description here so it passes validation.  It will get overriden in doRouteStatusChange method
	@Override
	public void processAfterEdit(MaintenanceDocument document, Map<String, String[]> requestParameters) {
        document.getDocumentHeader().setDocumentDescription("Edit Position");
        super.processAfterEdit(document, requestParameters);
    }
	
	
	protected void setupNewPositionRecord(MaintenanceDocument document) {
		PositionBo aPosition = (PositionBo) document.getNewMaintainableObject().getDataObject();
        aPosition.setProcess("New");
        String positionNumber = KRADServiceLocator.getSequenceAccessorService().getNextAvailableSequenceNumber("hr_position_s", PositionBo.class).toString();
        aPosition.setPositionNumber(positionNumber);
        
        document.getDocumentHeader().setDocumentDescription("New Position");
	}
	
	
	@Override 
	public void processAfterNew(MaintenanceDocument document, Map<String, String[]> requestParameters) {
		setupNewPositionRecord(document);
		super.processAfterNew(document, requestParameters);
	}
	
	@Override
	public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parameters) {
		setupNewPositionRecord(document);
		super.processAfterCopy(document, parameters);
	}

    @Override
    public String getDocumentTitle(MaintenanceDocument document) {
        String docTitle = document.getDocumentHeader().getDocumentDescription();
        return docTitle;
    }

	@Override
    public void doRouteStatusChange(DocumentHeader documentHeader) {

		String docDescription = null;
		PositionBo position = (PositionBo)this.getDataObject();
		DocumentStatus documentStatus = documentHeader.getWorkflowDocument().getStatus();
	
		//Set document description for real here
		if (StringUtils.isEmpty(position.getPositionNumber())) {
			docDescription = "Process: " + position.getProcess() + " Position Status: " + position.getPositionStatus();
		} else {
			docDescription = "Process: " + position.getProcess() + " Position Number: " + position.getPositionNumber() + " Position Status: " + position.getPositionStatus();
		}

		if (DocumentStatus.ENROUTE.equals(documentStatus)) {
			try {
				MaintenanceDocument md = (MaintenanceDocument)KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(documentHeader.getDocumentNumber());
		        md.getDocumentHeader().setDocumentDescription(docDescription);
		        md.getNewMaintainableObject().setDataObject(position);
		        KRADServiceLocatorWeb.getDocumentService().saveDocument(md);
			} catch (WorkflowException e) {
	            LOG.error("caught exception while handling doRouteStatusChange -> documentService.getByDocumentHeaderId(" + documentHeader.getDocumentNumber() + "). ", e);
	            throw new RuntimeException("caught exception while handling doRouteStatusChange -> documentService.getByDocumentHeaderId(" + documentHeader.getDocumentNumber() + "). ", e);
	        }
		}
    }

    //KPME-2624 added logic to save current logged in user to UserPrincipal id for collections
    @Override
    public void prepareForSave() {
    	
    	super.prepareForSave();
    	
    	PositionBo position = (PositionBo)this.getDataObject();
        boolean hasPrimaryDepartment = false;
        for (PositionDepartmentBo positionDepartment : position.getDepartmentList()) {
            if (positionDepartment.getDeptAfflObj().isPrimaryIndicator()) {
                hasPrimaryDepartment=true;
                positionDepartment.setDepartment(position.getPrimaryDepartment());
                positionDepartment.setGroupKeyCode(position.getGroupKeyCode());
                positionDepartment.setDeptAffl(HrServiceLocator.getDepartmentAffiliationService().getPrimaryAffiliation().getDeptAfflType());
            }
        }

        //create primary department
        if (!hasPrimaryDepartment && StringUtils.isNotEmpty(position.getPrimaryDepartment())) {
            PositionDepartmentBo primaryDepartment = new PositionDepartmentBo();
            primaryDepartment.setDepartment(position.getPrimaryDepartment());
            primaryDepartment.setGroupKeyCode(position.getGroupKeyCode());
            primaryDepartment.setDeptAffl(HrServiceLocator.getDepartmentAffiliationService().getPrimaryAffiliation().getDeptAfflType());
            position.getDepartmentList().add(primaryDepartment);
        }

        //add note if enroute change occurs
        try {
        	
            MaintenanceDocument maintenanceDocument = (MaintenanceDocument) KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(this.getDocumentNumber());
            if (maintenanceDocument != null && maintenanceDocument.getNewMaintainableObject().getDataObject() instanceof PositionBo) {
            	PositionBo previousPosition = (PositionBo) maintenanceDocument.getNewMaintainableObject().getDataObject();
            	recordEnrouteChanges(previousPosition, maintenanceDocument.getNoteTarget().getObjectId());
            }
        }
        catch (Exception e) {
                e.printStackTrace();
        }

       


    }

    
    class ChangedBusinessObjectVisitor implements Node.Visitor {
    	
    	private final Object current;
    	private final Object previous;
    	
    	private final String newline = "\n";    	
    	private String terminalElementMessages = "";  	
		private String collectionMessages = "";

		private DataDictionaryService dataDictionaryService;
    	
		
    	public ChangedBusinessObjectVisitor(Object current, Object previous, DataDictionaryService dataDictionaryService) {
    		this.current = current;
    		this.previous = previous;
    		this.dataDictionaryService = dataDictionaryService;
    	}
    	
    	private String getDisplayLabel(Node node) {
    		String displayLabel = "";
        	if(node.getParentNode() != null && node.getPathElement() != null) {
        		if(!node.isCollectionNode()) {
        			displayLabel = dataDictionaryService.getAttributeLabel(node.getParentNode().getType(), node.getPathElement().toString());
        		}
        		else {
        			displayLabel = dataDictionaryService.getCollectionLabel(node.getParentNode().getType(), node.getPathElement().toString());
        		}
        		
        		if(displayLabel != null) {
        			displayLabel = displayLabel.toUpperCase();
        		}
        		else {
        			//this method will never return null
        			displayLabel = "";
        		}
        	}
        	return displayLabel;
    	}
    	
		public void accept(final Node node, final Visit visit) {
			if(node.hasChanges()) {
				// if its a changed collection node, don't go deeper, instead check if the collection is really changed
				// and if so create an appropriate message
				if(node.isCollectionNode()) {
					visit.dontGoDeeper();
					if(hasCollectionBeenActuallyChanged(node.toCollectionNode(), this.current, this.previous)) { 
	        			String displayLabel = getDisplayLabel(node);
	    	        	if(StringUtils.isEmpty(displayLabel)) {
	    	        		displayLabel = node.getPathElement().toString();
	    	        	}
	    	        	// create collection message with the complete path display label
						displayLabel = getPrefixPathLabel(node) + displayLabel; 
	    	        	addToCollectionMessages(displayLabel);
	        		}
				}
				else {
					// we only create messages for changed terminal nodes that have display labels throughout their path 
					String displayLabel = getDisplayLabel(node);
					if(StringUtils.isEmpty(displayLabel) && !(node.isRootNode())) {
						visit.dontGoDeeper();
					}					
					else {
						if(node.getChildren().isEmpty()) {
							// create terminal element message with the complete path display label
							displayLabel = getPrefixPathLabel(node) + displayLabel;
							addToTerminalElementMessages(displayLabel, node);
						}
					}
				}
			}
			else {
				visit.dontGoDeeper();
			}
		}    

		protected void addToTerminalElementMessages(String displayLabel, Node changedNode) {
			terminalElementMessages += " " + displayLabel;
        	if(changedNode.isRemoved()) {
        		terminalElementMessages += " was DELETED it had value:<< " + changedNode.canonicalGet(previous) + " >>"; 
        	}
        	else if(changedNode.isAdded()) {
        		terminalElementMessages += " was ADDED with value:<< " + changedNode.canonicalGet(current) + " >>"; 
        	}
        	else if(changedNode.isChanged()) {
        		terminalElementMessages += " was CHANGED from value:<< " + changedNode.canonicalGet(previous) + " >> to value:<< " + changedNode.canonicalGet(current) + " >>"; 
        	}
        	terminalElementMessages += newline;
		}
		
		protected void addToCollectionMessages(String displayLabel) {
			collectionMessages += " " + displayLabel + " (collection) was CHANGED " + newline;
		}
		
		public boolean hasMessages() {
			return StringUtils.isNotBlank(terminalElementMessages) || StringUtils.isNotBlank(collectionMessages); 
		}		

		public String getPrefixPathLabel(Node node) {
			String retVal = "";
			Node parentNode = node.getParentNode();
			if(parentNode != null) {
				while(parentNode.getParentNode() != null) {
					retVal = getDisplayLabel(parentNode) + "->" + retVal;
					parentNode = parentNode.getParentNode();
				}
			}
			return retVal;
		}
		
		private boolean hasCollectionBeenActuallyChanged(CollectionNode collectionNode, Object current,	Object previous) {
			// get both collection objects
	    	Collection<?> currentCollection = null;
	    	Collection<?> previousCollection = null;
	    	
	    	if(collectionNode.canonicalGet(current) instanceof Collection) {
	    		currentCollection = (Collection<?>) collectionNode.canonicalGet(current);
	    	}
	    	if(collectionNode.canonicalGet(previous) instanceof Collection) {
	    		previousCollection = (Collection<?>) collectionNode.canonicalGet(previous);
	    	}
	    	
	    	if( (previousCollection == null) && (currentCollection == null) ){
	    		return false;
	    	}
	    	if( ((previousCollection == null) && (currentCollection != null)) || ((previousCollection != null) && (currentCollection == null)) ) { 
	    		return true;
	    	}
	    	// at this point both collections are non-null
	    	// check sizes are not same -- optimization
	    	if(previousCollection.size() != currentCollection.size()) {
	    		return true;
	    	}
	    	for(Object previousCollectionItem: previousCollection) {
	    		boolean foundItemMatch = false;
	    		for(Object currentCollectionItem: currentCollection) {
	    			ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance();
	    	        final Node root = objectDiffer.compare(currentCollectionItem, previousCollectionItem);
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

		public String getCollectionMessages() {
			return collectionMessages;
		}
    	
    	public String getTerminalElementMessages() {
			return terminalElementMessages;
		}
    }
    
    
    
    
    
    
    private void recordEnrouteChanges(PositionBo previousPosition, String noteTarget) {
        PositionBo currentPosition = (PositionBo) this.getDataObject();

        //ignore certain fields on position
        final Configuration configuration = new Configuration();
        configuration.withoutProperty(PropertyPath.buildWith("businessKeyValuesMap"));
        configuration.withoutProperty(PropertyPath.buildWith("relativeEffectiveDate"));
        configuration.withoutProperty(PropertyPath.buildWith("effectiveLocalDate"));
        configuration.withoutProperty(PropertyPath.buildWith("userPrincipalId"));
        
        configuration.withoutProperty(PropertyPath.buildWith("groupKey"));
        configuration.withoutProperty(PropertyPath.buildWith("locationObj"));
        configuration.withoutProperty(PropertyPath.buildWith("location"));        
        configuration.withoutProperty(PropertyPath.buildWith("institutionObj"));
        configuration.withoutProperty(PropertyPath.buildWith("institution"));
        
        
        ObjectDiffer objectDiffer = ObjectDifferFactory.getInstance(configuration);
        final Node root = objectDiffer.compare(currentPosition, previousPosition);
        final ChangedBusinessObjectVisitor visitor = new ChangedBusinessObjectVisitor(currentPosition, previousPosition, KRADServiceLocatorWeb.getDataDictionaryService());
        root.visit(visitor);
        
        // finally save the note text associated with the approver if the visitor has any messages
        if(visitor.hasMessages()) {
        	EntityNamePrincipalName approver = KimApiServiceLocator.getIdentityService().getDefaultNamesForPrincipalId(currentPosition.getUserPrincipalId());
	        String newline = "\n";
	        String noteText = approver.getPrincipalName() + " modified the following fields:" + newline;
	        noteText += visitor.getTerminalElementMessages();
	        noteText += visitor.getCollectionMessages();
	        // finally create and save the note containing the above note text
	        KRADServiceLocator.getNoteService().save(createNote(noteText, noteTarget, currentPosition.getUserPrincipalId()));
        }
        
    }

    private Note createNote(String noteText, String noteTarget, String principalId) {
        Note note = new Note();
        note.setRemoteObjectIdentifier(noteTarget);
        note.setNoteText(StringUtils.abbreviate(noteText,800));
        note.setAuthorUniversalIdentifier(principalId);
        note.setNotePostedTimestampToCurrent();
        return note;
    }

}
