/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.hr.time.scheduler;


import java.util.Calendar;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TimeBlockSerializerJobBean extends QuartzJobBean{
	
	private static TimeBlockSerializerService timeBlockSerializerService;
	
	

	public TimeBlockSerializerService getTimeBlockSerializerService() {
		return timeBlockSerializerService;
	}



	public void setTimeBlockSerializerService(
			TimeBlockSerializerService timeBlockSerializerService) {
		TimeBlockSerializerJobBean.timeBlockSerializerService = timeBlockSerializerService;
	}

		
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			//UnComment following 2 statements to apply the effect of Scheduled Job
			
			System.out.println("Executed at :"+Calendar.getInstance().getTime());
			timeBlockSerializerService.serializeToCSV();
			timeBlockSerializerService.serializeToXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	

}
