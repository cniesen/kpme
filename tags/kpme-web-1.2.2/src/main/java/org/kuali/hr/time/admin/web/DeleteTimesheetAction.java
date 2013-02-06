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
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.hr.time.base.web.TkAction;
import org.kuali.hr.time.service.base.TkServiceLocator;

public class DeleteTimesheetAction extends TkAction {
	
	private static final Logger LOG = Logger.getLogger(DeleteTimesheetAction.class);

    public ActionForward deleteTimesheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	DeleteTimesheetForm deleteTimesheetForm = (DeleteTimesheetForm) form;
    	String documentId = deleteTimesheetForm.getDeleteDocumentId();
    	
    	if (StringUtils.isNotBlank(documentId)) {
    		TkServiceLocator.getTimesheetService().deleteTimesheet(documentId);
    		
    		LOG.debug("Deleting timesheet: " + documentId);
    	}
    	
    	return mapping.findForward("basic");
    }

}