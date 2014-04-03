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
package org.kuali.rice.krad.kpme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.MaintenanceDocumentController;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Properties;


/**
 * This abstract class is the root of the KPME maintenance document controller hierarchy.
 * 
 */
public abstract class KPMEMaintenanceDocumentController extends MaintenanceDocumentController {
	
	// this method will intercept all disapprove requests and show the dialog asking for user explanantion for the disapproval.
	@Override
	public ModelAndView disapprove(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// todo add dialog logic here
		return super.disapprove(form, result, request, response);		
	}

    /**
     * Goes back to the return url. If form is accessed from the action list or doc search return to application url.
     */
    @RequestMapping(params = "methodToCall=close")
    public ModelAndView cancel(@ModelAttribute("KualiForm") MaintenanceDocumentForm form, BindingResult result,
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