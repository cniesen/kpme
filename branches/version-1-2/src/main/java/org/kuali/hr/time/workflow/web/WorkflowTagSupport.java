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
package org.kuali.hr.time.workflow.web;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.roles.TkUserRoles;
import org.kuali.hr.time.roles.UserRoles;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.kew.doctype.SecuritySession;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;

public class WorkflowTagSupport {

    public String getDocumentId() {
        return TKContext.getCurrentTimesheetDocumentId();
    }

    public boolean isDisplayingRouteButton() {
        TkUserRoles roles = TkUserRoles.getUserRoles(GlobalVariables.getUserSession().getActualPerson().getPrincipalId());
        TimesheetDocument doc = TKContext.getCurrentTimesheetDocument();
        TimesheetDocumentHeader tdh = doc.getDocumentHeader();
        if(tdh.getDocumentStatus().equals("S") || tdh.getDocumentStatus().equals("I")){
            return roles.canSubmitTimesheet(doc);
        }
        return false;
    }

    /**
     * Disable the 'route' button if the document has already been routed.
     * @return true if the route button should render as enabled.
     */
    public boolean isRouteButtonEnabled() {
        TimesheetDocument doc = TKContext.getCurrentTimesheetDocument();
        TimesheetDocumentHeader tdh = doc.getDocumentHeader();
        return (tdh.getDocumentStatus().equals("I") || tdh.getDocumentStatus().equals("S"));
    }

    public boolean isDisplayingApprovalButtons() {
        UserRoles roles = TkUserRoles.getUserRoles(GlobalVariables.getUserSession().getPrincipalId());
        TimesheetDocument doc = TKContext.getCurrentTimesheetDocument();
        boolean tookActionAlready = KEWServiceLocator.getActionTakenService().hasUserTakenAction(TKContext.getPrincipalId(), doc.getDocumentId());
        return !tookActionAlready && roles.isApproverForTimesheet(doc) && !StringUtils.equals(doc.getDocumentHeader().getDocumentStatus(), "F");
    }

    public boolean isApprovalButtonsEnabled() {
        TimesheetDocument doc = TKContext.getCurrentTimesheetDocument();
        TimesheetDocumentHeader tdh = doc.getDocumentHeader();
        boolean isEnroute = tdh.getDocumentStatus().equals(TkConstants.ROUTE_STATUS.ENROUTE);
        if(isEnroute){
            DocumentRouteHeaderValue routeHeader = KEWServiceLocator.getRouteHeaderService().getRouteHeader(doc.getDocumentId());
            boolean authorized = KEWServiceLocator.getDocumentSecurityService().routeLogAuthorized(TKContext.getPrincipalId(), routeHeader, new SecuritySession(TKContext.getPrincipalId()));
            boolean tookActionAlready = KEWServiceLocator.getActionTakenService().hasUserTakenAction(TKContext.getPrincipalId(), doc.getDocumentId());
            return !tookActionAlready && authorized;
        }
        return false;
    }

    public String getRouteAction() { return TkConstants.DOCUMENT_ACTIONS.ROUTE; }
    public String getApproveAction() { return TkConstants.DOCUMENT_ACTIONS.APPROVE; }
    public String getDisapproveAction() { return TkConstants.DOCUMENT_ACTIONS.DISAPPROVE; }
}
