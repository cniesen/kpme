/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.hr.time.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.joda.time.LocalDate;
import org.kuali.hr.job.Job;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.base.web.TkAction;
import org.kuali.hr.time.base.web.TkForm;
import org.kuali.hr.time.collection.rule.TimeCollectionRule;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.util.GlobalVariables;

public class TimeAction extends TkAction {

    @Override
    protected void checkTKAuthorization(ActionForm form, String methodToCall) throws AuthorizationException {
        TkForm tkForm = (TkForm) form;

        if (StringUtils.equals(methodToCall, "targetEmployee") || StringUtils.equals(methodToCall, "changeEmployee") || StringUtils.equals(methodToCall, "clearBackdoor") || StringUtils.equals(methodToCall, "clearChangeUser")) {
            // Handle security validation in targetEmployee action, we may need
            // to check the document for validity, since the user may not
            // necessarily be a system administrator.
        } else {
        	String principalId = GlobalVariables.getUserSession().getPrincipalId();
            if (!TKContext.isSystemAdmin()
        			&& !TKContext.isLocationAdmin()
        			&& !TKContext.isDepartmentAdmin()
        			&& !TKContext.isGlobalViewOnly()
        			&& !TKContext.isDepartmentViewOnly()
        			&& (tkForm.getDocumentId() != null && !TkServiceLocator.getTKPermissionService().canApproveTimesheet(principalId, tkForm.getDocumentId()))
        			&& (tkForm.getDocumentId() != null && !TkServiceLocator.getTKPermissionService().canViewTimesheet(principalId, tkForm.getDocumentId())))  {
                throw new AuthorizationException("", "TimeAction", "");
            }
        }
    }

    
    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        //boolean synch = TKUser.isSynchronous();
        String principalId = TKContext.getTargetPrincipalId();
        if (TKContext.isSystemAdmin()) {
            return new ActionRedirect("/portal.do");
        }
        PrincipalHRAttributes phra = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar(principalId, LocalDate.now());
        if (phra == null) {
            return new ActionRedirect("/PersonInfo.do");
        }
        Job job = TkServiceLocator.getJobService().getPrimaryJob(principalId, LocalDate.now());
        boolean activeAssignments = false;
        if (job != null) {
            String flsa = job.getFlsaStatus();
            List<Assignment> assignments = TkServiceLocator.getAssignmentService().getActiveAssignmentsForJob(principalId, job.getJobNumber(), LocalDate.now());
            for (Assignment asmnt : assignments) {
                if (asmnt.isActive()) {
                    if (job.getFlsaStatus().equals(TkConstants.FLSA_STATUS_NON_EXEMPT)) {
                        TimeCollectionRule tcr = asmnt.getTimeCollectionRule();
                        if (tcr.isClockUserFl()) {
                            return new ActionRedirect("/Clock.do");
                        } else {
                            return new ActionRedirect("/TimeDetail.do");
                        }
                    } else {
                        if (job.isEligibleForLeave()) {
                            return new ActionRedirect("/LeaveCalendar.do");
                        }
                    }
                }
            }
        }

        return new ActionRedirect("/PersonInfo.do");

            //if (assignment != null) {
            //    assignment.get
            //}
        /*if (principalId != null) {
            if (TKUser.isSystemAdmin()) {
                return new ActionRedirect("/portal.do");
            } else if (TKUser.isDepartmentAdmin()
                    && !synch) {
                return new ActionRedirect("/portal.do");
            } else if (TKUser.isApprover()
                    && !synch) {
                return new ActionRedirect("/TimeApproval.do");
            } else if (TKUser.isReviewer()
                    && !synch) {
                return new ActionRedirect("/TimeApproval.do");
            } else if (TKUser.isActiveEmployee()
                    && !synch) {
                return new ActionRedirect("/TimeDetail.do");
            } else if (synch) {
                return new ActionRedirect("/Clock.do");
            } else {
                return new ActionRedirect("/PersonInfo.do");
            }
        }
    }
	return super.execute(mapping, form, request, response);*/
}
    
}
