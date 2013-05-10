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
package org.kuali.hr.time.timezone.service;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.kuali.hr.job.Job;
import org.kuali.hr.location.Location;
import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timeblock.TimeBlock;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.krad.util.GlobalVariables;

public class TimezoneServiceImpl implements TimezoneService {

    @Override
    public String getUserTimezone(String principalId) {
        PrincipalHRAttributes principalCalendar = TkServiceLocator.getPrincipalHRAttributeService().getPrincipalCalendar(principalId, LocalDate.now());
        if(principalCalendar != null && principalCalendar.getTimezone() != null){
            return principalCalendar.getTimezone();
        }
        List<Job> jobs = TkServiceLocator.getJobService().getJobs(principalId, LocalDate.now());
        if (jobs.size() > 0) {
            // Grab the location off the first job in the list
            Location location = TkServiceLocator.getLocationService().getLocation(jobs.get(0).getLocation(), LocalDate.now());
            if (location!=null){
                if(StringUtils.isNotBlank(location.getTimezone())){
                    return location.getTimezone();
                }
            }
        }
        return TKUtils.getSystemTimeZone();
    }

    /**
	 * Used to determine if an override condition exists for a user timezone
	 */
	@Override
	public String getUserTimezone() {
		String timezone = "";
		if (GlobalVariables.getUserSession() != null) {
			timezone = getUserTimezone(GlobalVariables.getUserSession().getPrincipalId());
		}
        return timezone;
	}

    @Override
    public DateTimeZone getUserTimezoneWithFallback() {
        String tzid = getUserTimezone();
        if (StringUtils.isEmpty(tzid)) {
            return TKUtils.getSystemDateTimeZone();
        } else {
            return DateTimeZone.forID(tzid);
        }
    }

    private DateTimeZone getUserTimezoneWithFallback(String principalId) {
        String tzid = getUserTimezone(principalId);
        if (StringUtils.isEmpty(tzid)) {
            return TKUtils.getSystemDateTimeZone();
        } else {
            return DateTimeZone.forID(tzid);
        }
    }

	/**
	 * Translation needed for UI Display
	 * @param timeBlocks
	 * @param timezone
	 * @return timeblock list modified with times offset for timezone
	 */
	public List<TimeBlock> translateForTimezone(List<TimeBlock> timeBlocks, DateTimeZone timezone){
		for(TimeBlock tb : timeBlocks){
			//No need for translation if it matches the current timezone
			if(ObjectUtils.equals(timezone, TKUtils.getSystemDateTimeZone())){
				tb.setBeginTimeDisplay(tb.getBeginDateTime());
				tb.setEndTimeDisplay(tb.getEndDateTime());
			}
			else {
				tb.setBeginTimeDisplay(tb.getBeginDateTime().withZone(timezone));
				tb.setEndTimeDisplay(tb.getEndDateTime().withZone(timezone));
			}
		}
		return timeBlocks;
	}

    public void translateForTimezone(List<TimeBlock> timeBlocks) {
        if (timeBlocks.isEmpty()) {
            translateForTimezone(timeBlocks, getUserTimezoneWithFallback());
        }
        else {
            translateForTimezone(timeBlocks, getUserTimezoneWithFallback(timeBlocks.get(0).getPrincipalId()));
        }
    }

	@Override
	public boolean isSameTimezone() {
		String userTimezone = getUserTimezone();
		if(StringUtils.isNotBlank(userTimezone)) {
			return StringUtils.equals(TKUtils.getSystemTimeZone(), userTimezone);
		}
		return true;
	}
	
	
	public long getTimezoneOffsetFromServerTime(DateTimeZone dtz){
		long systemOffsetUTC = TKUtils.getSystemDateTimeZone().getOffset(null);
		long tzOffsetUTC = dtz.getOffset(null);
		return tzOffsetUTC - systemOffsetUTC;
	}



}
