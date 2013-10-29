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
package org.kuali.hr.time.batch;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.assignment.AssignmentDescriptionKey;
import org.kuali.hr.time.calendar.Calendar;
import org.kuali.hr.time.calendar.CalendarEntries;
import org.kuali.hr.time.clocklog.ClockLog;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timesheet.TimesheetDocument;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.workflow.TimesheetDocumentHeader;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class EndPayPeriodJob implements Job {

    private static final Logger LOG = Logger.getLogger(EndPayPeriodJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("Starting of EndPayPeriod Job!!!");
        String batchUserPrincipalId = getBatchUserPrincipalId();

        if (batchUserPrincipalId != null) {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

            String hrCalendarEntryId = jobDataMap.getString("hrCalendarEntryId");
            String tkClockLogId = jobDataMap.getString("tkClockLogId");
            CalendarEntries calendarEntry = TkServiceLocator.getCalendarEntriesService().getCalendarEntries(hrCalendarEntryId);
            Calendar calendar = TkServiceLocator.getCalendarService().getCalendar(calendarEntry.getHrCalendarId());
            calendarEntry.setCalendarObj(calendar);

            Date endPeriodDateTime = calendarEntry.getEndPeriodDateTime();
            CalendarEntries nextCalendarEntry = TkServiceLocator.getCalendarEntriesService().getNextCalendarEntriesByCalendarId(calendarEntry.getHrCalendarId(), calendarEntry);
            Date beginNextPeriodDateTime = nextCalendarEntry.getBeginPeriodDateTime();

            ClockLog openClockLog = TkServiceLocator.getClockLogService().getClockLog(tkClockLogId);
            String ipAddress = openClockLog.getIpAddress();
            String principalId = openClockLog.getPrincipalId();

            TimesheetDocumentHeader timesheetDocumentHeader = TkServiceLocator.getTimesheetDocumentHeaderService().getDocumentHeader(principalId, calendarEntry.getBeginPeriodDateTime(), endPeriodDateTime);
            if (timesheetDocumentHeader != null) {
                LOG.info("Current timesheet document id is " + timesheetDocumentHeader.getDocumentId());
                TimesheetDocument timesheetDocument = TkServiceLocator.getTimesheetService().getTimesheetDocument(timesheetDocumentHeader.getDocumentId());
                String assignmentKey = new AssignmentDescriptionKey(openClockLog.getJobNumber(), openClockLog.getWorkArea(), openClockLog.getTask()).toAssignmentKeyString();
                Assignment assignment = TkServiceLocator.getAssignmentService().getAssignment(timesheetDocument, assignmentKey);

                LOG.info("Before creating clock OUT log!");
                ClockLog clockOutLog = TkServiceLocator.getClockLogService().processClockLog(new java.sql.Timestamp(endPeriodDateTime.getTime()), assignment, calendarEntry, ipAddress,
                        new java.sql.Date(endPeriodDateTime.getTime()), timesheetDocument, TkConstants.CLOCK_OUT, false, principalId, batchUserPrincipalId);
                LOG.info("Clock OUT log created, the id is " + clockOutLog.getTkClockLogId() + ", timestamp is " + clockOutLog.getTimestamp().toString());

                TimesheetDocumentHeader nextTdh = TkServiceLocator.getTimesheetDocumentHeaderService()
                        .getDocumentHeader(principalId, nextCalendarEntry.getBeginPeriodDateTime(), nextCalendarEntry.getEndPeriodDateTime());
                TimesheetDocument nextTimeDoc = null;
                if(nextTdh != null) {
                    nextTimeDoc = TkServiceLocator.getTimesheetService().getTimesheetDocument(nextTdh.getDocumentId());
                    LOG.info("Next Time document is not null, the document id is " + nextTdh.getDocumentId());
                }
                LOG.info("Before creating clock IN log!");
                ClockLog clockInLog = TkServiceLocator.getClockLogService().processClockLog(new java.sql.Timestamp(beginNextPeriodDateTime.getTime()), assignment, nextCalendarEntry, ipAddress,
                        new java.sql.Date(beginNextPeriodDateTime.getTime()), nextTimeDoc, TkConstants.CLOCK_IN, false, principalId, batchUserPrincipalId);
                LOG.info("Clock IN log created, the id is " + clockInLog.getTkClockLogId() + ", timestamp is " + clockInLog.getTimestamp().toString());

                // add 5 seconds to clock in log's timestamp so it will be found as the latest clock action
                Timestamp ts= clockInLog.getTimestamp();
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.setTimeInMillis(ts.getTime());
                cal.add(java.util.Calendar.SECOND, 5);
                Timestamp later = new Timestamp(cal.getTime().getTime());
                clockInLog.setTimestamp(later);
                TkServiceLocator.getClockLogService().saveClockLog(clockInLog);
                LOG.info("After adding 5 seconds to ClockInLog, the timestamp is " + clockInLog.getTimestamp().toString());
            }
        } else {
            String principalName = ConfigContext.getCurrentContextConfig().getProperty(TkConstants.BATCH_USER_PRINCIPAL_NAME);
            LOG.error("Could not run batch jobs due to missing batch user " + principalName);
        }
    }

    private String getBatchUserPrincipalId() {
        String principalName = ConfigContext.getCurrentContextConfig().getProperty(TkConstants.BATCH_USER_PRINCIPAL_NAME);
        Principal principal = KimApiServiceLocator.getIdentityService().getPrincipalByPrincipalName(principalName);
        return principal == null ? null : principal.getPrincipalId();
    }

}
