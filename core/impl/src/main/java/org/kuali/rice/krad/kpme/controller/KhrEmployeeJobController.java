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
package org.kuali.rice.krad.kpme.controller;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.krad.bo.PersistableAttachment;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.maintenance.Maintainable;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.DocumentControllerBase;
import org.kuali.rice.krad.web.controller.MaintenanceDocumentController;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.kuali.kpme.core.tkmdocument.KhrEmployeeDocument;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Created by mlemons on 9/9/14.
*/

@Controller
@RequestMapping(value = "/kpme/KhrEmployeeJobMaintenance")
public class KhrEmployeeJobController extends MaintenanceDocumentController {
/*
    @Override
    @RequestMapping(params = "methodToCall=cancel")
    public ModelAndView cancel(@ModelAttribute("KualiForm") UifFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {

        //return returnToPrevious(form);
        DocumentFormBase documentForm = (DocumentFormBase) form;
        DocumentStatus status = documentForm.getDocument().getDocumentHeader().getWorkflowDocument().getStatus();

        return super.cancel(form, result, request, response);
    }
*/

    @RequestMapping(params = "methodToCall=close")
    public ModelAndView close(@ModelAttribute("KualiForm") MaintenanceDocumentForm form, BindingResult result,
                               HttpServletRequest request, HttpServletResponse response) {
        Properties props = new Properties();
        props.put(UifParameters.METHOD_TO_CALL, UifConstants.MethodToCallNames.REFRESH);

        String command = form.getCommand();
        String returnUrl = form.getReturnLocation();

        //if form is accessed from the action list or doc search return to application url
        if (StringUtils.equals(command, "displayDocSearchView")
                || StringUtils.equals(command, "displayActionListView")
                || StringUtils.equals(command, "displaySuperUserView")) {
            returnUrl = ConfigContext.getCurrentContextConfig().getProperty(KRADConstants.APPLICATION_URL_KEY);
        }
        return performRedirect(form, returnUrl, props);
    }


}

