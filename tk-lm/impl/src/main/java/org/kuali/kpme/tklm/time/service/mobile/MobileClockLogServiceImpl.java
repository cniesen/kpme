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
package org.kuali.kpme.tklm.time.service.mobile;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.json.simple.JSONValue;
import org.kuali.kpme.core.api.assignment.Assignment;
import org.kuali.kpme.core.api.assignment.AssignmentDescriptionKey;
import org.kuali.kpme.core.api.assignment.service.AssignmentService;
import org.kuali.kpme.core.api.calendar.entry.CalendarEntry;
import org.kuali.kpme.core.api.job.Job;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.api.paytype.PayType;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.tklm.api.common.TkConstants;
import org.kuali.kpme.tklm.api.time.clocklog.ClockLog;
import org.kuali.kpme.tklm.api.time.mobile.CryptographicService;
import org.kuali.kpme.tklm.api.time.mobile.MobileClockLogService;
import org.kuali.kpme.tklm.time.clocklog.RemoteClockLog;
import org.kuali.kpme.tklm.time.clocklog.RemoteSwipeData;
import org.kuali.kpme.tklm.time.clocklog.RemoteSwipeDevice;
import org.kuali.kpme.tklm.time.service.TkServiceLocator;
import org.kuali.kpme.tklm.time.timesheet.TimesheetDocument;
import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.principal.EntityNamePrincipalName;
import org.kuali.rice.kim.api.role.RoleMember;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.krad.bo.AdHocRoutePerson;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class MobileClockLogServiceImpl implements MobileClockLogService {
    private static final Logger LOG = Logger.getLogger(MobileClockLogServiceImpl.class);

    public static final String POSITIONS = "positions";
    public static final String STATUS = "status";
    public static final String MESSAGES = "messages";
    public static final String STATUS_MULTIPLE_POSITIONS = "multiple positions";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILURE = "failure";

    // Following messages should go to ApplicationResources.properties
    public static final String MESSAGE_NO_POSITION = "You have no position to log your clock action.";
    public static final String MESSAGE_INVALID_CLOCK_LOG = "Your clockLog is not valid.";
    public static final String MESSAGE_INVALID_CARD_DATA = "Your cardData is not valid.";
    public static final String MESSAGE_INVALID_UID = "Your UID does not exist in the system.";
    public static final String MESSAGE_UPDATED = "Your clock action was successfully updated. BlahBlahBlah";
    public static final String MESSAGE_MULTIPLE_POSITIONS = "You have multiple Positions.";
    public static final String MESSAGE_INVALID_DEVICE = "This device is not registered or activated.";

    @Override
    public void heartbeat(@Context HttpServletRequest hsr) {
        LOG.info("heartbeat - Start");
        String ipAddress = hsr.getRemoteAddr();
        String fwdIp = hsr.getHeader("X-Forwarded-For");
        if (fwdIp != null) {
            ipAddress = fwdIp;
        }
        LOG.info("heartbeat - IP Address:"+ipAddress);
        RemoteSwipeDevice remoteSwipeDevice = getRemoteSwipeDeviceUsingIpAddress(ipAddress);

        if (checkValidRemoteDevice(remoteSwipeDevice)) {
            remoteSwipeDevice.setLastSeenOnline(new Timestamp(System.currentTimeMillis()).toString());
            KRADServiceLocator.getBusinessObjectService().save(remoteSwipeDevice);
            LOG.info("heartbeat - Valid remote device");
        }
        else
            LOG.info("heartbeat - Invalid remote device");
    }

    @Override
    public String addClockLog(String clockLogString, @Context HttpServletRequest hsr) {
        Map<String, Object> returnStrings = new HashMap<String, Object>();
        List<String> messages = new ArrayList<String>();
    	String ipAddress = hsr.getRemoteAddr();
    	String fwdIp = hsr.getHeader("X-Forwarded-For");
        if (fwdIp != null) {
        	ipAddress = fwdIp;
        }

        // check whether or not remote device is registered and active
        RemoteSwipeDevice remoteSwipeDevice = getRemoteSwipeDeviceUsingIpAddress(ipAddress);
        if (!checkValidRemoteDevice(remoteSwipeDevice)) {
        	returnStrings.put(STATUS, STATUS_FAILURE);
        	messages.add(MESSAGE_INVALID_DEVICE);
        	returnStrings.put(MESSAGES, messages);
        	return JSONValue.toJSONString(returnStrings);
        }

        Gson gson = new Gson();

        // set object from JSON
        RemoteClockLog remoteClockLog = gson.fromJson(clockLogString, RemoteClockLog.class);

        // decrypt cardData
        RemoteSwipeData remoteSwipeData = decryptCardData(remoteClockLog);

        // TODO: Validate cardData : existence of principalID, expiration date,
        // if there is no error from validation, then status should be empty.
        if (!validateInputData(remoteClockLog, remoteSwipeData, remoteSwipeDevice, messages)) {
            // if there is any error, then build and return.
            returnStrings.put(STATUS, STATUS_FAILURE);
            returnStrings.put(MESSAGES, messages);
            return JSONValue.toJSONString(returnStrings);
        }

        // get Assignment
        List<Assignment> assignments = new ArrayList<Assignment>();
        ClockLog lastClockLog = TkServiceLocator.getClockLogService().getLastClockLog(remoteClockLog.getPrincipalId());

        String currentClockAction = getCurrentClockAction(lastClockLog);
       	LOG.info("Current Clock Action="+currentClockAction);	
        assignments = getAssignments(remoteClockLog, currentClockAction, lastClockLog);
    	LOG.info("Assignment Size="+assignments.size());	

        // if there is no assignment for the user, then return error message.
        if (assignments.size() < 1) {
            returnStrings.put(STATUS, STATUS_FAILURE);
            messages.add(MESSAGE_NO_POSITION);
            returnStrings.put(MESSAGES, messages);

            // TODO: Should RemoteSwipeDevice.lastSeenOnline be updated??

            // send email notification to admin
            // TODO: Confirm the message.
            String subject = "Employee does not have ANY assignments.";
            EntityNamePrincipalName person = KimApiServiceLocator.getIdentityService().getDefaultNamesForPrincipalId(remoteSwipeData.getUid());
            String emailMessage = "";
            if (person == null) {
            	emailMessage = "Employee (" + remoteSwipeData.getUid() + ") does NOT have any assignments.";
            }
            else {
            	emailMessage = person.getDefaultName().getCompositeName()+" (" + remoteSwipeData.getUid() + ") does not have ANY assignments.";
            }
            emailMessage = emailMessage+"\n"+"Attempted clock in from "+remoteSwipeDevice.getDeviceName()+" ("+remoteSwipeDevice.getIpAddress()+").";
    		sendNotificationsToAdmin(subject, emailMessage);

            return JSONValue.toJSONString(returnStrings);
        }

        // in case of user has multiple positions.
        Map<String, String> positions = new HashMap<String, String>();
        if (assignments.size() > 1) {
            // input is NOT from Queue, then return the positions.
            if (!remoteClockLog.isFromQueue()) {
            	LOG.info("isFromQueue="+"false");	
                for (Assignment eachAssignment : assignments) {
                    if (eachAssignment.getJob() != null) {
                        String key = new AssignmentDescriptionKey(eachAssignment).toAssignmentKeyString();
                        String desc = eachAssignment.getAssignmentDescription();
                        positions.put(key, desc);
                    }
                }
                returnStrings.put(STATUS, STATUS_MULTIPLE_POSITIONS);
                returnStrings.put(POSITIONS, positions);
                messages.add(MESSAGE_MULTIPLE_POSITIONS);
                returnStrings.put(MESSAGES, messages);

                // TODO: RemoteSwipeDevice remoteSwipeDevice should be updated??
                return JSONValue.toJSONString(returnStrings);
            } else {
                // input is from Queue, then build a blank assignment. This will
                // be fixed by approver.
            	LOG.info("isFromQueue="+"true");	
                //Assignment assignment = buildBlankAssignment(remoteClockLog.getPrincipalId());
                //assignments.add(assignment);
            	//LOG.info("Assignment Size="+assignments.size());	
            }
        }

        // There is only one assignment
        Assignment assignment = assignments.get(0);
        storeClockLog(remoteClockLog.getPrincipalId(), assignment, ipAddress, remoteClockLog.getClockLog(), currentClockAction);

        returnStrings.put(STATUS, STATUS_SUCCESS);
        messages = buildSuccessfullyUpdatedMessage(remoteClockLog, assignment, currentClockAction);
        LOG.info("Messages="+messages);
        returnStrings.put(MESSAGES, messages);

        // TODO: RemoteSwipeDevice remoteSwipeDevice should be updated??
        return JSONValue.toJSONString(returnStrings);

    }

    private RemoteSwipeData decryptCardData(RemoteClockLog remoteClockLog) {
    	LOG.info("decryptCardData - Start");	

        CryptographicService cryptographicService = (CryptographicService) TkServiceLocator.getCryptographicService();

        String bdkFileName = ConfigContext.getCurrentContextConfig().getProperty("dukpt.key.file");

        String bdk = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(bdkFileName));
            bdk = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            LOG.info("No file found for " + bdkFileName);
        } catch (IOException e) {
            LOG.info("IO exception in bdk loading");
        }

        String decryptedCardData = cryptographicService.decryption(bdk, remoteClockLog.getCardData().get("ksn"), remoteClockLog.getCardData().get("track"));

        return new RemoteSwipeData(decryptedCardData);

    }

    protected boolean validateInputData(RemoteClockLog remoteClockLog, RemoteSwipeData remoteSwipeData,RemoteSwipeDevice remoteSwipeDevice , List<String> messages) {
    	LOG.info("validateInputData - Start");	
        boolean valid = true;;

        // Track 2 Location Contents A.B.A. Description
        // Col. 1 ; Start sentinel
        // Col. 2-7 600957 ISU ISO Number
        // Col. 8-16 999999999 ISU UID
        // Col. 17 9 Card Version No.**
        // Col. 18 Check Digit
        // Col. 19 = Field Separator
        // Col. 20-21 49 Expiration Yr.
        // Col. 22-23 12 Expiration Mo.
        // Col. 24-26 120 Card Type Code
        // Col. 27-34 00000000 Filler
        // Col. 35-38 0000 Pin Offset
        // Col. 39 ? End Sentinel
        // Col. 40 Longitudinal Redundancy Check

        Date clockLog = new Date(remoteClockLog.getClockLogTimestamp());

        // TODO: should I validate this clock log? (for example, is this clock
        // time for this pay period?)

        // check validation of clockLog
        if (ObjectUtils.isNull(clockLog)) {
            valid &= false;
            messages.add(MESSAGE_INVALID_CLOCK_LOG);
        } else {
            remoteClockLog.setClockLog(clockLog);
        }

        // check basic validation of cardData
        boolean startSentinel = remoteSwipeData.getStartSentinel().equals(RemoteSwipeData.START_SENTINEL);
        boolean endSentinel = remoteSwipeData.getEndSentinel().equals(RemoteSwipeData.END_SENTINEL);
        if (!startSentinel && endSentinel){
            valid &= false;
            messages.add(MESSAGE_INVALID_CARD_DATA);
        }

        // check uid
        // TODO: Make sure with ISU, is UID the same as employeeId in krim_prncpl_t??
        Person principal = null;
        principal = getPersonFromCardData(remoteSwipeData);
        if (ObjectUtils.isNull(principal)){
            // TODO: for just test`
            // remoteClockLog.setPrincipalId("10013");
            valid &= false;
            messages.add(MESSAGE_INVALID_UID);

            // send email to admin
            // TODO: Confirm the message.
            String subject = "Invalid employee Id from remote device.";

    		String emailMessage = "Employee Id " + remoteSwipeData.getUid() + " is invalid or does NOT exist.";
            emailMessage = emailMessage+"\n"+"Attempted clock in from "+remoteSwipeDevice.getDeviceName()+" ("+remoteSwipeDevice.getIpAddress()+").";
            sendNotificationsToAdmin(subject, emailMessage);

        } else {
            remoteClockLog.setPrincipalId(principal.getPrincipalId());
        }
    	LOG.info("validateInputData - End");	
        return valid;
    }

    protected Person getPersonFromCardData(RemoteSwipeData remoteSwipeData) {
        return KimApiServiceLocator.getPersonService().getPerson(remoteSwipeData.getUid());
    }

    private List<Assignment> getAssignments(RemoteClockLog remoteClockLog, String currentClockAction, ClockLog lastClockLog) {
        AssignmentService assignmentService = HrServiceLocator.getAssignmentService();
        List<Assignment> assignments = new ArrayList<Assignment>();

        // NEED To check the last clock log
        // if user already has clock in, then get assignment from last clock log
        // and don't return multiple position.
        if(TkConstants.CLOCK_OUT.equals(currentClockAction)){
            AssignmentDescriptionKey assignmentDescriptionKey = new AssignmentDescriptionKey(lastClockLog.getGroupKeyCode(), lastClockLog.getJobNumber(), lastClockLog.getWorkArea(), lastClockLog.getTask());
            Assignment assignment = assignmentService.getAssignment(remoteClockLog.getPrincipalId(), assignmentDescriptionKey, new LocalDate(remoteClockLog.getClockLogTimestamp()));
            if (ObjectUtils.isNotNull(assignment)){
                assignments.add(assignment);
            }
        } else {
            // get assignments

            // if position is specified
            if (ObjectUtils.isNotNull(remoteClockLog.getPosition()) && StringUtils.isNotEmpty(remoteClockLog.getPosition())) {
                AssignmentDescriptionKey assignmentDescriptionKey = assignmentService.getAssignmentDescriptionKey(remoteClockLog.getPosition());
                Assignment assignment = assignmentService.getAssignment(remoteClockLog.getPrincipalId(), assignmentDescriptionKey, new LocalDate(remoteClockLog.getClockLog()));
                if (ObjectUtils.isNotNull(assignment)){
                    assignments.add(assignment);
                }
            } else {
                assignments = assignmentService.getAssignments(remoteClockLog.getPrincipalId(), new LocalDate(remoteClockLog.getClockLog()));
            }
        }

        return assignments;
    }

    private void storeClockLog(String principalId, Assignment assignment, String ipAddress, Date clockLog, String currentClockAction) {
        DateTime clockDateTime = new DateTime(clockLog);
        CalendarEntry calendarEntry = HrServiceLocator.getCalendarEntryService().getCurrentCalendarDates(principalId, clockDateTime);
        TimesheetDocument td;
        try {
            td = TkServiceLocator.getTimesheetService().openTimesheetDocument(principalId, calendarEntry);
        } catch (WorkflowException e) {
            throw new RuntimeException("Could not open timesheet");
        }

        // processClockLog is the correct method to use. It creates and persists a clock log and a time block if necessary.
        // There should be an exception when clock logs are out of order
        try {
            TkServiceLocator.getClockLogService().processClockLog(principalId, td.getDocumentId(), clockDateTime, assignment, td.getCalendarEntry(), ipAddress, LocalDate.now(), currentClockAction, true, principalId);
        } catch (Exception e){
            // send FYI to approvers
            sendApproverFyi(td, assignment.getWorkArea());
        }
    }

    private Assignment buildBlankAssignment(String principalId) {


        PayType.Builder payTypeObj = PayType.Builder.create("XH");
        payTypeObj.setRegEarnCode("REG");
        Job.Builder job = Job.Builder.create(principalId, 0L);
        job.setPayTypeObj(payTypeObj);

        Assignment.Builder newAssignment = Assignment.Builder.create(principalId, "", 0L, 0L, 0L);
        newAssignment.setPrincipalId(principalId);
        newAssignment.setJob(job);

        return newAssignment.build();
    }

    private List<String> buildSuccessfullyUpdatedMessage(RemoteClockLog remoteClockLog, Assignment assignment, String currentClockAction){
        List<String> messages = new ArrayList<String>();
        //Person person = assignment.getPrincipal();
        String firstName = "";
        String lastName = "";
        EntityNamePrincipalName namePrincipalName = KimApiServiceLocator.getIdentityService().getDefaultNamesForPrincipalId(assignment.getPrincipalId());
        if (!ObjectUtils.isNull(namePrincipalName)){
            if (namePrincipalName.getDefaultName() != null) {
                firstName = namePrincipalName.getDefaultName().getFirstName();
                lastName = namePrincipalName.getDefaultName().getLastName();
            }
        }

        messages.add("Thank you, " + firstName + " " + lastName  + ".");
        String clockInOrOut = TkConstants.CLOCK_IN.equals(currentClockAction) ? "In" : "Out";
        messages.add("You have successfully");
        messages.add("Clocked " + clockInOrOut  + ".");

        return messages;
    }

    private String getCurrentClockAction(ClockLog lastClockLog){

        String currentClockAction = TkConstants.CLOCK_IN;
        if (lastClockLog != null) {
            if (StringUtils.equals(lastClockLog.getClockAction(), TkConstants.CLOCK_IN)) {
                currentClockAction = TkConstants.CLOCK_OUT;
            } else if (StringUtils.equals(lastClockLog.getClockAction(), TkConstants.CLOCK_OUT)) {
                currentClockAction = TkConstants.CLOCK_IN;
            } else if (StringUtils.equals(lastClockLog.getClockAction(), TkConstants.LUNCH_IN)) {
                currentClockAction = TkConstants.LUNCH_OUT;
            } else {
                currentClockAction = TkConstants.LUNCH_IN;
            }
        }

        return currentClockAction;
    }

    private RemoteSwipeDevice getRemoteSwipeDeviceUsingIpAddress(String ipAddress){
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("ipAddress", ipAddress);
        RemoteSwipeDevice remoteSwipeDevice = KRADServiceLocator.getBusinessObjectService().findByPrimaryKey(RemoteSwipeDevice.class, fields);

        return remoteSwipeDevice;
    }

    private void sendApproverFyi(TimesheetDocument timesheetDocument, Long workArea){

        List<AdHocRouteRecipient> recipients = buildAdHocRouteRecipients(workArea);
        String documentId = timesheetDocument.getDocumentId();
        WorkflowDocument wd = WorkflowDocumentFactory.loadDocument(timesheetDocument.getPrincipalId(), documentId);

        try {
            // TODO: Annotation - "Clock Logs are out of order" need to be changed ???
            KRADServiceLocatorWeb.getWorkflowDocumentService().sendWorkflowNotification(wd, "Clock Logs are out of order", recipients);
        } catch (WorkflowException e) {
            LOG.debug("sendApproverFyi was filed.");
        }
    }

    private List<AdHocRouteRecipient> buildAdHocRouteRecipients(Long workArea) {
        List<AdHocRouteRecipient> recipients = new ArrayList<AdHocRouteRecipient>();
        DateTime date = LocalDate.now().toDateTimeAtStartOfDay();
        List<RoleMember> roleMembers = HrServiceLocator.getKPMERoleService().getRoleMembersInWorkArea(KPMENamespace.KPME_HR.getNamespaceCode(), KPMERole.APPROVER.getRoleName(), workArea, date, true);

        for (RoleMember roleMember : roleMembers) {
            Person person = KimApiServiceLocator.getPersonService().getPerson(roleMember.getMemberId());
            if (person != null) {
                AdHocRouteRecipient adHocRouteRecipient = new AdHocRoutePerson();
                // into constant class?
                String ACTION_REQUEST_FYI_REQ = "F";
                adHocRouteRecipient.setActionRequested(ACTION_REQUEST_FYI_REQ);
                adHocRouteRecipient.setId(person.getPrincipalName());
                recipients.add(adHocRouteRecipient);
            }
        }
        return recipients;
    }


    private void sendNotificationsToAdmin(String subject, String emailMessage) {

        List<RoleMember> roleMembers = HrServiceLocator.getKPMERoleService().getRoleMembers(KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_SYSTEM_ADMINISTRATOR.getRoleName(), LocalDate.now().toDateTimeAtStartOfDay(), true);
        List<String> roleMemberIdList = new ArrayList<String>();
        for(RoleMember roleMember : roleMembers) {
            roleMemberIdList.add(roleMember.getMemberId());
        }
        String[] roleMemberIds = new String[roleMemberIdList.size()];
        HrServiceLocator.getKPMENotificationService().sendNotification(subject, emailMessage, roleMemberIdList.toArray(roleMemberIds));
    }
    
    private boolean checkValidRemoteDevice(RemoteSwipeDevice remoteSwipeDevice){
    	if (ObjectUtils.isNull(remoteSwipeDevice)){
    	return false;
    	} else if (!remoteSwipeDevice.isActive()){
    	return false;
    	}
    	return true;
    }

}