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
package org.kuali.hr.time.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.time.base.web.TkAction;
import org.kuali.hr.time.calendar.Calendar;
import org.kuali.hr.time.calendar.CalendarEntry;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;

public class InitiateDocumentAction extends TkAction {

    public ActionForward initiateDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	InitiateDocumentForm initiateDocumentForm = (InitiateDocumentForm) form;
    	String principalId = initiateDocumentForm.getPrincipalId();
    	String hrCalendarEntryId = initiateDocumentForm.getHrCalendarEntryId();
    	
    	if (StringUtils.isNotBlank(principalId) && StringUtils.isNotBlank(hrCalendarEntryId)) {
    		Principal principal = KimApiServiceLocator.getIdentityService().getPrincipal(principalId);
    		CalendarEntry calendarEntry = TkServiceLocator.getCalendarEntryService().getCalendarEntry(hrCalendarEntryId);
    		
    		if (principal != null && calendarEntry != null) {
    			Calendar calendar = TkServiceLocator.getCalendarService().getCalendar(calendarEntry.getHrCalendarId());
    			
    			if (calendar != null) {
    				if (StringUtils.equals(calendar.getCalendarTypes(), TkConstants.CALENDAR_TYPE_PAY)) {
    					TkServiceLocator.getTimesheetService().openTimesheetDocument(principalId, calendarEntry);
    				} else if (StringUtils.equals(calendar.getCalendarTypes(), TkConstants.CALENDAR_TYPE_LEAVE)) {
    					TkServiceLocator.getLeaveCalendarService().openLeaveCalendarDocument(principalId, calendarEntry);
    				}
    			}
    		}
    	}
    	
    	return mapping.findForward("basic");
    }
    
}