package org.kuali.hr.lm.leaveblock.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.kuali.hr.lm.LMConstants;
import org.kuali.hr.lm.accrual.AccrualCategory;
import org.kuali.hr.lm.leaveblock.LeaveBlock;
import org.kuali.hr.lm.leaveblock.LeaveBlockHistory;
import org.kuali.hr.lm.leaveblock.dao.LeaveBlockDao;
import org.kuali.hr.lm.workflow.LeaveCalendarDocumentHeader;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.calendar.CalendarEntries;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TKContext;
import org.kuali.hr.time.util.TKUtils;
import org.kuali.rice.krad.service.KRADServiceLocator;

public class LeaveBlockServiceImpl implements LeaveBlockService {

    private static final Logger LOG = Logger.getLogger(LeaveBlockServiceImpl.class);

    private LeaveBlockDao leaveBlockDao;

    @Override
    public LeaveBlock getLeaveBlock(Long leaveBlockId) {
        return leaveBlockDao.getLeaveBlock(leaveBlockId);
    }

    public LeaveBlockDao getLeaveBlockDao() {
        return leaveBlockDao;
    }

    public void setLeaveBlockDao(LeaveBlockDao leaveBlockDao) {
        this.leaveBlockDao = leaveBlockDao;
    }

    public List<LeaveBlock> getLeaveBlocksForDocumentId(String documentId) {
        return leaveBlockDao.getLeaveBlocksForDocumentId(documentId);
    }

    @Override
    public List<LeaveBlock> getLeaveBlocks(String principalId, Date beginDate,
                                   Date endDate) {
        return leaveBlockDao.getLeaveBlocks(principalId, beginDate, endDate);
    }

    @Override
    public void saveLeaveBlocks(List<LeaveBlock> leaveBlocks) {
        for (LeaveBlock leaveBlock : leaveBlocks) {
        	leaveBlockDao.saveOrUpdate(leaveBlock);
        	// create leaveblock history
        	LeaveBlockHistory lbh = new LeaveBlockHistory(leaveBlock);
        	lbh.setAction(LMConstants.ACTION.ADD);
        	TkServiceLocator.getLeaveBlockHistoryService().saveLeaveBlockHistory(lbh);
        }
    }

    @Override
    public void deleteLeaveBlock(long leaveBlockId) {
        LeaveBlock leaveBlock = TkServiceLocator.getLeaveBlockService().getLeaveBlock(leaveBlockId);
        
//        leaveBlock.setPrincipalIdModified(TKContext.getTargetPrincipalId());
//        leaveBlock.setTimestamp(TKUtils.getCurrentTimestamp());
        
        // Make entry into LeaveBlockHistory table
        LeaveBlockHistory leaveBlockHistory = new LeaveBlockHistory(leaveBlock);
        leaveBlockHistory.setPrincipalIdDeleted(TKContext.getPrincipalId());
        leaveBlockHistory.setTimestampDeleted(new Timestamp(System.currentTimeMillis()));
        leaveBlockHistory.setAction(LMConstants.ACTION.DELETE);

        // deleting leaveblock
        KRADServiceLocator.getBusinessObjectService().delete(leaveBlock);
        
        // creating history
        KRADServiceLocator.getBusinessObjectService().save(leaveBlockHistory); 
        
        
    }

    @Override
    public void saveLeaveBlock(LeaveBlock leaveBlock) {

    	// first delete and create new entry in the database
    	KRADServiceLocator.getBusinessObjectService().delete(leaveBlock);
    	
    	// create new 
        leaveBlock.setLmLeaveBlockId(null);
    	leaveBlock.setTimestamp(new Timestamp(System.currentTimeMillis()));
    	leaveBlock.setPrincipalIdModified(TKContext.getPrincipalId());
    	KRADServiceLocator.getBusinessObjectService().save(leaveBlock);

        // save history
        LeaveBlockHistory lbh = new LeaveBlockHistory(leaveBlock);
        lbh.setAction(LMConstants.ACTION.MODIFIED);
        TkServiceLocator.getLeaveBlockHistoryService().saveLeaveBlockHistory(lbh);
        
    }

    @Override
    public void addLeaveBlocks(DateTime beginDate, DateTime endDate, CalendarEntries ce, String selectedEarnCode, 
    		BigDecimal hours, String description, Assignment selectedAssignment, String spanningWeeks, String leaveBlockType) {
        String princpalId = TKContext.getTargetPrincipalId();
        DateTimeZone zone = TkServiceLocator.getTimezoneService().getUserTimezoneWithFallback();
        DateTime calBeginDateTime = beginDate;
    	DateTime calEndDateTime = endDate;
        if(ce != null) {
        	calBeginDateTime = ce.getBeginLocalDateTime().toDateTime(zone);
        	calEndDateTime = ce.getEndLocalDateTime().toDateTime(zone);
        }
        Interval calendarInterval = new Interval(calBeginDateTime, calEndDateTime);
       
        // To create the correct interval by the given begin and end dates,
        // we need to plus one day on the end date to include that date
        List<Interval> leaveBlockIntervals = TKUtils.createDaySpan(beginDate, endDate.plusDays(1), zone);
        // need to use beginDate and endDate of the calendar to find all leaveBlocks since LeaveCalendarDocument Id is not always available
        List<LeaveBlock> currentLeaveBlocks =TkServiceLocator.getLeaveBlockService().getLeaveBlocks(princpalId, calBeginDateTime.toDate(), calEndDateTime.toDate());
    
        // use the current calendar's begin and end date to figure out if this pay period has a leaveDocument
        LeaveCalendarDocumentHeader lcdh = TkServiceLocator.getLeaveCalendarDocumentHeaderService()
        		.getDocumentHeader(princpalId, ce.getBeginLocalDateTime().toDateTime().toDate(), ce.getEndLocalDateTime().toDateTime().toDate());
        String docId = lcdh == null ? null : lcdh.getDocumentId();
        
        // TODO: need to integrate with the scheduled timeoff.
        for (Interval leaveBlockInt : leaveBlockIntervals) {
            if (calendarInterval.contains(leaveBlockInt)) {
            	// KPME-1446 if "Include weekends" check box is checked, don't add Sat and Sun to the leaveblock list
            	if (StringUtils.isEmpty(spanningWeeks) && 
                	(leaveBlockInt.getStart().getDayOfWeek() == DateTimeConstants.SATURDAY ||leaveBlockInt.getStart().getDayOfWeek() == DateTimeConstants.SUNDAY)) {
            		
            		// do nothing
            	} else {
            		 // Currently, we store the accrual category value in the leave code table, but store accrual category id in the leaveBlock.
                    // That's why there is a two step server call to get the id. This might be changed in the future.
             
                    EarnCode earnCodeObj = TkServiceLocator.getEarnCodeService().getEarnCodeById(selectedEarnCode);
                    AccrualCategory accrualCategory = TkServiceLocator.getAccrualCategoryService().getAccrualCategory(earnCodeObj.getAccrualCategory(), TKUtils.getCurrentDate());
                    String acId = accrualCategory == null ? null : accrualCategory.getLmAccrualCategoryId();
	                LeaveBlock leaveBlock = new LeaveBlock.Builder(new DateTime(leaveBlockInt.getStartMillis()), docId, princpalId, earnCodeObj.getEarnCode(), hours)
	                        .description(description)
	                        .principalIdModified(princpalId)
	                        .timestamp(TKUtils.getCurrentTimestamp())
	                        .earnCodeId(earnCodeObj.getHrEarnCodeId())
	                        .scheduleTimeOffId("0")
	                        .accrualCategoryId(acId)
	                        .workArea(selectedAssignment.getWorkArea())
	                        .jobNumber(selectedAssignment.getJobNumber())
	                        .task(selectedAssignment.getTask())
	                        .leaveBlockType(leaveBlockType)
	                        .build();
	                currentLeaveBlocks.add(leaveBlock);
            	}
            }
        }

        TkServiceLocator.getLeaveBlockService().saveLeaveBlocks(currentLeaveBlocks);
    }
    
    // KPME-1447
    @Override
    public void updateLeaveBlock(LeaveBlock leaveBlock) {
    	
        // Make entry into LeaveBlockHistory table
        LeaveBlockHistory leaveBlockHistory = new LeaveBlockHistory(leaveBlock);
        leaveBlockHistory.setPrincipalIdDeleted(TKContext.getPrincipalId());
        leaveBlockHistory.setTimestampDeleted(new Timestamp(System.currentTimeMillis()));
        leaveBlockHistory.setAction(LMConstants.ACTION.MODIFIED);

        leaveBlockDao.saveOrUpdate(leaveBlock);
        
        // creating history
        KRADServiceLocator.getBusinessObjectService().save(leaveBlockHistory); 
    }    

    public static List<Interval> createDaySpan(DateTime beginDateTime, DateTime endDateTime, DateTimeZone zone) {
        beginDateTime = beginDateTime.toDateTime(zone);
        endDateTime = endDateTime.toDateTime(zone);
        List<Interval> dayIntervals = new ArrayList<Interval>();

        DateTime currDateTime = beginDateTime;
        while (currDateTime.isBefore(endDateTime)) {
            DateTime prevDateTime = currDateTime;
            currDateTime = currDateTime.plusDays(1);
            Interval daySpan = new Interval(prevDateTime, currDateTime);
            dayIntervals.add(daySpan);
        }

        return dayIntervals;
    }

	@Override
	public List<LeaveBlock> getLeaveBlocks(String principalId, String requestStatus, Date currentDate) {
		return leaveBlockDao.getLeaveBlocks(principalId, requestStatus, currentDate);
	}
	
	@Override
	public List<LeaveBlock> getLeaveBlocksForDate(String principalId, Date leaveDate) {
		return leaveBlockDao.getLeaveBlocksForDate(principalId, leaveDate);
	}
	
	@Override
	public Double calculateAccrualbalance(Date leaveDate, String accrualCategoryId, String principalId){
		Double totalAccrualBal = 0.0;
		List<LeaveBlock> leaveBlocks = leaveBlockDao.getLeaveBlocks(leaveDate, accrualCategoryId, principalId);
		if(leaveBlocks != null && !leaveBlocks.isEmpty()) {
			for(LeaveBlock lb : leaveBlocks) {
				totalAccrualBal += lb.getLeaveAmount().doubleValue();
			}
		}
		return totalAccrualBal;
				
	}
	@Override
	public List<LeaveBlock> getNotAccrualGeneratedLeaveBlocksForDate(String principalId, Date leaveDate) {
		return leaveBlockDao.getNotAccrualGeneratedLeaveBlocksForDate(principalId, leaveDate);
	}

	@Override
	public List<LeaveBlock> getLeaveBlocksForTimesheet(String principalId, Date beginDate, Date endDate) {
		return leaveBlockDao.getLeaveBlocksForTimesheet(principalId, beginDate, endDate);
	}

    @Override
    public void deleteLeaveBlocksForDocumentId(String documentId){
        leaveBlockDao.deleteLeaveBlocksForDocumentId(documentId);
    }
}
