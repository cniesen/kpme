package org.kuali.kpme.edo.dossier.web;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.kpme.edo.base.web.EdoAction;
import org.kuali.kpme.edo.base.web.EdoForm;
import org.kuali.kpme.edo.candidate.EdoCandidate;
import org.kuali.kpme.edo.dossier.EdoDossier;
import org.kuali.kpme.edo.item.EdoItem;
import org.kuali.kpme.edo.service.EdoServiceLocator;
import org.kuali.kpme.edo.util.EdoConstants;
import org.kuali.kpme.edo.util.EdoContext;
import org.kuali.kpme.edo.util.EdoRule;
import org.kuali.kpme.edo.util.EdoUtils;
import org.kuali.kpme.edo.util.TagSupport;
import org.kuali.kpme.edo.workflow.DossierProcessDocumentHeader;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class EdoDossierRouteAction extends EdoAction {

    public ActionForward routeDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        MessageMap msgmap = GlobalVariables.getMessageMap();
        boolean isRouted = false;

        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());

        if ( principal != null ) {
            if (EdoRule.isDossierReadyForRoute(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()) ) ) {
                isRouted = EdoServiceLocator.getEdoDossierService().routeDocument(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), edoDossierRouteForm.getDossierType());
            } else {
                msgmap.putError(EdoConstants.ErrorKeys.ERROR_KEYS, "error.route.ready");
            }
        }
        // update the selectedCandidate instance with the new dossier status
        if (isRouted) {
        	EdoDossier edoDossier = EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()));
        	/*if(StringUtils.isNotBlank(edoDossier.getSecondaryUnit())){
            	//also send a notification to the secondary unit reviwers if the candidate has the secondary unit
            	// get the second unit reviewer associated with the logged in candidate
                List<String> secUnitReviewers = EdoServiceLocator.getEdoMaintenanceService().getCandidateSecondaryUnitReviewers(edoDossier.getDossierID());
                for(String secUnitReviewer : secUnitReviewers) {
                    Person reviewer = KimApiServiceLocator.getPersonService().getPerson(secUnitReviewer);
                    String subject = "Workflow Notification";
                    String content = "This is an FYI email notification : "
                    		+ "Candidate" + edoDossier.getCandidateUsername()
							+ "has submitted their dossier for review at http://edossier.iu.edu.\n\n"
							+ "For additional help or feedback, email <edossier@indiana.edu>.";        				
                    String testEmailAddresses = new String();
					if (!TagSupport.isNonProductionEnvironment()) {
							EdoServiceLocator.getEdoNotificationService()
									.sendMail(reviewer.getEmailAddress(),
											"edossier@indiana.edu",
											subject, content);
						
					} else {
						testEmailAddresses = ConfigContext
								.getCurrentContextConfig()
								.getProperty(
										EdoConstants.TEST_EMAIL_NOTIFICATION);
						EdoServiceLocator.getEdoNotificationService()
								.sendMail(testEmailAddresses,
										"edossier@indiana.edu", subject,
										content);
					}
                	
                }

        	}*/
        EdoContext.getSelectedCandidate().setDossierStatus(edoDossier.getDossierStatus());
        } else {
            msgmap.putError(EdoConstants.ErrorKeys.ERROR_KEYS, "error.route.general");
        }

        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }

    public ActionForward submitForSignOff(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        //update the dossier status to pending
        EdoDossier edoDossier = EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()));
        EdoContext.getSelectedCandidate().setDossierStatus(EdoConstants.DOSSIER_STATUS.PENDING);
        edoDossier.setDossierStatus(EdoConstants.DOSSIER_STATUS.PENDING);
        edoDossier.setLastUpdated(EdoUtils.getNow());
        edoDossier.setUpdatedBy(EdoContext.getPrincipalName());
        EdoServiceLocator.getEdoDossierService().saveOrUpdate(edoDossier);
        //send an adhoc email to the sign off person
        //get the sign off person by passing in the qualifier resolver
	     List<String> checkListOffPersonList = EdoServiceLocator.getEdoMaintenanceService().getCheckListSignOffPerson(edoDossier.getDepartmentID(), edoDossier.getSchoolID(), edoDossier.getDossierID());
	      String subject = "Workflow Notification";
	      String content = "This is an FYI email notification : " + edoDossier.getCandidateUsername()
      		+ " has submitted their dossier for review at http://edossier.iu.edu. "
      		+ "In your role, following the guidelines for tenure and promotion reviews, "
      		+ "please review the e-dossier to ensure that all pertinent materials listed on the checklist are included in the proper categories. If you approve, "
      		+ "the dossier will be formally entered into the review process and eligible departmental reviewers will be notified. If you disapprove, "
      		+ "please return the dossier to the candidate and meet with the candidate to discuss what is missing or misclassified."
			+ "For additional help or feedback, email <edossier@indiana.edu>.";   
	      String testEmailAddresses = new String();
			if (!TagSupport.isNonProductionEnvironment()) {
	      for(String signOffPerson : checkListOffPersonList) {
        	//send an email to the check list sign off
        	Person person = KimApiServiceLocator
					.getPersonService().getPerson(signOffPerson);
			EdoServiceLocator.getEdoNotificationService()
					.sendMail(person.getEmailAddress(),
							"edossier@indiana.edu",
							subject, content);
        }
			}
			else {
				testEmailAddresses = ConfigContext
						.getCurrentContextConfig()
						.getProperty(
								EdoConstants.TEST_EMAIL_NOTIFICATION);
				EdoServiceLocator.getEdoNotificationService()
						.sendMail(testEmailAddresses,
								"edossier@indiana.edu", subject,
								content);
			}
        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }

    public ActionForward returnToCandidate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
      /*  MessageMap msgmap = GlobalVariables.getMessageMap();
        boolean isRouted = false;
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());

        if (principal != null) {
            isRouted = EdoServiceLocator.getEdoDossierService().returnToCandidate(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), edoDossierRouteForm.getDossierType());
        }

        // update the selectedCandidate instance with the new dossier status
        if (isRouted) {
            EdoContext.getSelectedCandidate().setDossierStatus(EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId() )).getDossierStatus());
        } else {
            msgmap.putError(EdoConstants.ErrorKeys.ERROR_KEYS, "error.route.general");
        }*/
        //this has to be changed too
        //reset the dossier status to "	open" in the edo_dossier_t table
        // update the selectedCandidate instance with the "open" dossier status
        //send an adhoc email to the candidate saying that his dossier has not met the requirements.
        EdoDossier edoDossier = EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()));
        if(ObjectUtils.isNotNull(edoDossier)) {
        EdoContext.getSelectedCandidate().setDossierStatus(EdoConstants.DOSSIER_STATUS.OPEN);
        edoDossier.setDossierStatus(EdoConstants.DOSSIER_STATUS.OPEN);
        edoDossier.setLastUpdated(EdoUtils.getNow());
        edoDossier.setUpdatedBy(EdoContext.getPrincipalName());
        EdoServiceLocator.getEdoDossierService().saveOrUpdate(edoDossier);
        //send an adhoc email to the sign off person
        String subject = "Workflow Notification";
        String content = "This is an FYI email notification : "
        		+ " Your Department Chair has returned your dossier to you for updates/modifications. "
				+ " Please login at at http://edossier.iu.edu.\n\n"
				+ "For additional help or feedback, email <edossier@indiana.edu>.";        
        
        String testEmailAddresses = new String();
		if (!TagSupport.isNonProductionEnvironment()) {
				Person person = KimApiServiceLocator
						.getPersonService().getPersonByPrincipalName(edoDossier.getCandidateUsername());
				EdoServiceLocator.getEdoNotificationService()
						.sendMail(person.getEmailAddress(),
								"edossier@indiana.edu",
								subject, content);
				} else {
			testEmailAddresses = ConfigContext
					.getCurrentContextConfig()
					.getProperty(
							EdoConstants.TEST_EMAIL_NOTIFICATION);
			EdoServiceLocator.getEdoNotificationService()
					.sendMail(testEmailAddresses,
							"edossier@indiana.edu", subject,
							content);
		}
    }
       
        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }

    public ActionForward superUserApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        MessageMap msgmap = GlobalVariables.getMessageMap();
        boolean isRouted = false;
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());

        if (principal != null) {
            EdoServiceLocator.getEdoDossierService().superUserAction(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), edoDossierRouteForm.getDossierType(), ActionType.SU_APPROVE, edoDossierRouteForm.getNode());
        }

        // update the selectedCandidate instance with the new dossier status
        if (isRouted) {
            EdoContext.getSelectedCandidate().setDossierStatus(EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId())).getDossierStatus());
        } else {
            msgmap.putError(EdoConstants.ErrorKeys.ERROR_KEYS, "error.route.general");
        }
        

        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }

   public ActionForward superUserReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        MessageMap msgmap = GlobalVariables.getMessageMap();
        boolean isRouted = false;
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());

        if (principal != null) {
            EdoServiceLocator.getEdoDossierService().superUserAction(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), edoDossierRouteForm.getDossierType(), ActionType.SU_RETURN_TO_PREVIOUS, edoDossierRouteForm.getNode());
        }

        // update the selectedCandidate instance with the new dossier status
        if (isRouted) {
            EdoContext.getSelectedCandidate().setDossierStatus(EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId())).getDossierStatus());
        } else {
            msgmap.putError(EdoConstants.ErrorKeys.ERROR_KEYS, "error.route.general");
        }

        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }
    

    /*public ActionForward superUserReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        EdoDossier edoDossier = EdoServiceLocator.getEdoDossierService().getDossierById(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()));
        if(ObjectUtils.isNotNull(edoDossier)) {
        EdoContext.getSelectedCandidate().setDossierStatus(EdoConstants.DOSSIER_STATUS.OPEN);
        edoDossier.setDossierStatus(EdoConstants.DOSSIER_STATUS.OPEN);
        edoDossier.setLastUpdated(EdoUtils.getNow());
        edoDossier.setUpdatedBy(EdoContext.getPrincipalName());
        EdoServiceLocator.getEdoDossierService().saveOrUpdate(edoDossier);
        //send an adhoc email to the sign off person
        String subject = "Workflow Notification";
        String content = "This is an FYI email notification : "
        		+ " Your Department Chair has returned your dossier to you for updates/modifications. "
				+ " Please login at at http://edossier.iu.edu.\n\n"
				+ "For additional help or feedback, email <edossier@indiana.edu>.";        
        
        String testEmailAddresses = new String();
		if (!TagSupport.isNonProductionEnvironment()) {
				Person person = KimApiServiceLocator
						.getPersonService().getPersonByPrincipalName(edoDossier.getCandidateUsername());
				EdoServiceLocator.getEdoNotificationService()
						.sendMail(person.getEmailAddress(),
								"edossier@indiana.edu",
								subject, content);
				} else {
			testEmailAddresses = ConfigContext
					.getCurrentContextConfig()
					.getProperty(
							EdoConstants.TEST_EMAIL_NOTIFICATION);
			EdoServiceLocator.getEdoNotificationService()
					.sendMail(testEmailAddresses,
							"edossier@indiana.edu", subject,
							content);
		}
    }


    	String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }*/
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoForm edoForm = (EdoForm)form;
        if(StringUtils.equals(request.getParameter(KewApiConstants.COMMAND_PARAMETER), KewApiConstants.DOCSEARCH_COMMAND)
                || StringUtils.equals(request.getParameter(KewApiConstants.COMMAND_PARAMETER), KewApiConstants.SUPERUSER_COMMAND)
                || StringUtils.equals(request.getParameter(KewApiConstants.COMMAND_PARAMETER), KewApiConstants.ACTIONLIST_COMMAND)) {
            return displayDocSearchView(mapping, edoForm, request, response);
        }

        return super.execute(mapping, form, request, response);
    }

    public ActionForward displayDocSearchView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String returnAction = "EdoIndex.do";

        String documentId = request.getParameter(KewApiConstants.DOCUMENT_ID_PARAMETER);
        WorkflowDocument workflowDocument = WorkflowDocumentFactory.loadDocument(EdoContext.getPrincipalId(), documentId);

        if (workflowDocument != null) {
            Principal principal = KimApiServiceLocator.getIdentityService().getPrincipal(workflowDocument.getInitiatorPrincipalId());
            if (principal != null) {
                EdoCandidate candidate = EdoServiceLocator.getCandidateService().getCandidateByUsername(principal.getPrincipalName());
                if (candidate != null) {
                    ActionRedirect rd = new ActionRedirect(mapping.findForward("candidateSelectRedirect"));
                    rd.addParameter("nid", "Dcklst_0_0");
                    rd.addParameter("cid", candidate.getCandidateID().toString());
                    rd.addParameter("dossier", workflowDocument.getApplicationDocumentId());
                    return rd;
                }
            }
        }

        System.out.println("FOUND EDO " + workflowDocument.getApplicationDocumentId());
        return new ActionRedirect(returnAction);
    }
    public ActionForward routeSupplementalDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());
          if (principal != null) {
        	 
           Boolean routed =  EdoServiceLocator.getEdoDossierService().routeSupplementalDocument(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), edoDossierRouteForm.getDossierType());
          
           if(routed) {
        	   //update edo_item_t table - set addendum_routed to 0 for supplemental category
               BigDecimal checklistItemID = EdoServiceLocator.getChecklistViewService().getChecklistItemByName(EdoConstants.EDO_SUPPLEMENTAL_ITEM_CATEGORY_NAME).getChecklistItemID();
               List<EdoItem> edoItems = EdoServiceLocator.getEdoItemService().getPendingItemsByDossierId(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()), checklistItemID);
              if(!edoItems.isEmpty()) {
               for(EdoItem edoItem : edoItems) {
            	   //update
            	   edoItem.setAddendumRouted(BigDecimal.ZERO);
            	   EdoServiceLocator.getEdoItemService().saveOrUpdate(edoItem);
               	}
              }
        	 
           }
            
          }

        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }
    public ActionForward approveSupplemental(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//approve and stay in the same page
    	  EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
          Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());
          String suppType = null;
          if(StringUtils.equals(edoDossierRouteForm.getDossierType(), "Tenure") || StringUtils.equals(edoDossierRouteForm.getDossierType(), "Tenure+Promotion"))   {
        	  suppType = "Tenure Supplemental";
          }
          if(StringUtils.equals(edoDossierRouteForm.getDossierType(), "Promotion"))   {
        	  suppType = "Promotion Supplemental";
          }
          if (principal != null) {
            	
            	EdoServiceLocator.getEdoDossierService().approveSupplemental(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), suppType);
            }
    	String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    	
    }
    public ActionForward approveSupplementalWithAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//approve and stay in the same page
  	  EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());
        String suppType = null;
        if(StringUtils.equals(edoDossierRouteForm.getDossierType(), "Tenure") || StringUtils.equals(edoDossierRouteForm.getDossierType(), "Tenure+Promotion"))   {
      	  suppType = "Tenure Supplemental";
        }
        if(StringUtils.equals(edoDossierRouteForm.getDossierType(), "Promotion"))   {
      	  suppType = "Promotion Supplemental";
        }
        if (principal != null) {
          	
          	EdoServiceLocator.getEdoDossierService().approveSupplemental(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), suppType);
          }
        //notify the current level of newly uploaded review letter
        DossierProcessDocumentHeader dossierDocumentHeader = EdoServiceLocator.getDossierProcessDocumentHeaderService().getDossierProcessDocumentHeader(edoDossierRouteForm.getDossierId());
        if (ObjectUtils.isNotNull(dossierDocumentHeader)) {
            //Get the parent workflow document (dossier).
        	WorkflowDocument dossierWorkflowDocument = WorkflowDocumentFactory.loadDocument(dossierDocumentHeader.getPrincipalId(), dossierDocumentHeader.getDocumentId());
            //Get the parent workflow document route level name.
            DocumentRouteHeaderValue dossierRouteHeader = KEWServiceLocator.getRouteHeaderService().getRouteHeader(dossierWorkflowDocument.getDocumentId());
            //make a service call to notify the current level where the parent edoc is 
            String contentSingle = "This is an FYI email notification:" 
            		+ EdoContext.getSelectedCandidate().getCandidateUsername() 
            		+ "e-dossier is currently under review at your level. In response to the addition of new materials by" + EdoContext.getSelectedCandidate().getCandidateUsername() + ", a formal response has been offered by an earlier level of review. Please consider these responses. No other action is required.";
					
			String contentMultiple = "This is an FYI email notification:"
					+ "In response to the addition of new materials by "+ EdoContext.getSelectedCandidate().getCandidateUsername() 
					+ "a formal response has been offered by an earlier level of review. Please consider these responses and notify others at your level (i.e., committee members) to do so as well. No other action is required.";
			
        	EdoServiceLocator.getEdoSupplementalPendingStatusService().notifyCurrentEdossierLevelForSuppDoc(dossierRouteHeader.getDocumentId(), dossierRouteHeader.getCurrentRouteLevelName(), contentSingle, contentMultiple );
        }
        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
  	
  }
    public ActionForward routeReconsiderDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EdoDossierRouteForm edoDossierRouteForm = (EdoDossierRouteForm)form;
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(edoDossierRouteForm.getCandidateUsername());

       
        if (principal != null) {
        	 Boolean routed =  EdoServiceLocator.getEdoDossierService().routeReconsiderDocument(principal.getPrincipalId(), edoDossierRouteForm.getDossierId(), edoDossierRouteForm.getDossierType());
       
        if(routed) {
     	   //update edo_item_t table - set addendum_routed to 0 for Reconsider category
            BigDecimal checklistItemID = EdoServiceLocator.getChecklistViewService().getChecklistItemByName(EdoConstants.EDO_RECONSIDERATION_ITEM_CATEGORY_NAME).getChecklistItemID();
            List<EdoItem> edoItems = EdoServiceLocator.getEdoItemService().getPendingItemsByDossierId(BigDecimal.valueOf(edoDossierRouteForm.getDossierId()), checklistItemID);
           if(!edoItems.isEmpty()) {
            for(EdoItem edoItem : edoItems) {
         	   //update
         	   edoItem.setAddendumRouted(BigDecimal.ZERO);
         	   EdoServiceLocator.getEdoItemService().saveOrUpdate(edoItem);
            	}
             }
     	 
           }
        }

        String prevPage = request.getHeader("REFERER");
        return new ActionRedirect(prevPage);
    }
}
