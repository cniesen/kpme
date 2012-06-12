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
 
package org.kuali.mobility.kpme.service;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.mobility.kpme.dao.KPMEDao;
import org.kuali.mobility.kpme.entity.ClockEntryInfo;
import org.kuali.mobility.kpme.entity.KPME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tkmobileservice.TkMobileService;


@Service
public class KPMEServiceImpl implements KPMEService {

    @Autowired
    private KPMEDao dao;
    
    private org.kuali.hr.time.mobile.service.TkMobileService tkMobileService;
    
    public void setKPMEDao(KPMEDao dao) {
        this.dao = dao;
    }
    public KPMEDao getKPMEDao() {
        return dao;
    }
    
    public org.kuali.hr.time.mobile.service.TkMobileService getTkMobileService() {
    	if(tkMobileService == null){
    		TkMobileService factory = new TkMobileService();
        	this.tkMobileService = factory.getTkMobileServicePort();
    	}
		return tkMobileService;
	}
	public void setTkMobileService(
		org.kuali.hr.time.mobile.service.TkMobileService tkMobileService) {
		this.tkMobileService = tkMobileService;
	}
	
	@Override
    public ClockEntryInfo getClockinfo(String principalId){
    	org.kuali.hr.time.mobile.service.TkMobileService service = getTkMobileService();
    	String re = service.getClockEntryInfo(principalId);
    	ClockEntryInfo info = (new Gson()).fromJson(re, ClockEntryInfo.class);
    	return info;
    }
    
    @Override
    public Map<String, List<String>> addClockAction(String principalId, String assignmentKey, String clockAction){
    	org.kuali.hr.time.mobile.service.TkMobileService service = getTkMobileService();
    	service.addClockAction(principalId, assignmentKey, clockAction);
    	return null;
    }
}
