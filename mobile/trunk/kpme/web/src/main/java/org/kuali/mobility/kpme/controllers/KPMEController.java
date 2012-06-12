/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.mobility.kpme.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import org.kuali.mobility.kpme.entity.ClockEntryInfo;
import org.kuali.mobility.kpme.entity.ClockEntryInfoModel;
import org.kuali.mobility.kpme.entity.KPME;
import org.kuali.mobility.kpme.service.KPMEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/kpme")
public class KPMEController {

    @Autowired
    private KPMEService service;
    public void setKPMEService(KPMEService service) {
        this.service = service;
    }
	
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel){
    	uiModel.addAttribute("kpme", new KPME());
    	return "kpme/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model uiModel, @ModelAttribute("principalId") KPME kpme, HttpServletRequest request){
    	String principalId = kpme.getName();
    	request.getSession().setAttribute("KPME_USER", principalId);
    	ClockEntryInfoModel info =new ClockEntryInfoModel(service.getClockinfo(principalId));
    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	format.setTimeZone(TimeZone.getTimeZone("EST"));
    	try {
    		Date d = format.parse(info.getLastClockEntry());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	uiModel.addAttribute("clockEntry", info);
    	if(!(info !=null && info.getClockActions() !=null && info.getClockActions().size()>0)){
    		uiModel.addAttribute("message", "Clock entry not found");
    	}
    	uiModel.addAttribute("kpme", new KPME());
    	return "kpme/index";
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String addClockEntry(Model uiModel, @ModelAttribute("kpme") KPME kpme,  HttpServletRequest request) {
    	
    	String principalId = (String) request.getSession().getAttribute("KPME_USER");
    	
    	if(kpme.getValue() != null && !kpme.getValue().isEmpty()){
    		//service.addClockAction(principalId, kpme.getValue(), kpme.getName());
    		service.addClockAction(principalId, kpme.getValue(), "");
    		uiModel.addAttribute("message", "Action Performed successfully");
    	}else {
    		uiModel.addAttribute("message", "Invalid Clock Entry.");
    	}
    	
    	ClockEntryInfoModel info =new ClockEntryInfoModel(service.getClockinfo(principalId));
    	uiModel.addAttribute("clockEntry", info);
    	DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	format.setTimeZone(TimeZone.getTimeZone("EST"));
    	try {
    		Date d = format.parse(info.getLastClockEntry());			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!(info !=null && info.getClockActions() !=null && info.getClockActions().size()>0)){
    		uiModel.addAttribute("message", "Clock entry not found");
    	}
    	uiModel.addAttribute("kpme", new KPME());
    	return "kpme/index";
    }
}
