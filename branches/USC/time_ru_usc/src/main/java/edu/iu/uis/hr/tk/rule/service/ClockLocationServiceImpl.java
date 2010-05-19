package edu.iu.uis.hr.tk.rule.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.iu.hrms.hredoc.cache.CacheResult;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRule;
import edu.iu.uis.hr.tk.rule.entity.ClockLocationRuleSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.dataaccess.ClockLogDataAccess;
import edu.iu.uis.hr.tk.timesheet.entity.ClockLog;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;

public class ClockLocationServiceImpl implements ClockLocationService {
	
    private static final String PATTERN = "([0-9]{1,3})";
	private static final String WILDCARD = "@";
    private static final String SPLIT_STRING = "\\.";
    
    private ClockLogDataAccess clockLogDataAccess;
    
	@SuppressWarnings("unchecked")
	@CacheResult
	public List<ClockLocationRule> getClockLocation(String emplid, BigDecimal emplRcd, String dept, BigDecimal workArea) {
        ClockLocationRuleSearchCriteria cLocSearchCriteria = new ClockLocationRuleSearchCriteria();
        cLocSearchCriteria.setDepartment(dept);
        cLocSearchCriteria.setWorkArea(workArea);
        cLocSearchCriteria.setUniversityId(emplid);
        cLocSearchCriteria.setEmployeeRecord(emplRcd);

        List<ClockLocationRule> locationsRules = TKServiceLocator.getRuleService().lookupMaintainableClockLocationRules(cLocSearchCriteria);
        if(!locationsRules.isEmpty()){
        	return locationsRules;
        } else {
        	cLocSearchCriteria.setEmployeeRecord(new BigDecimal(-1));
        	locationsRules = TKServiceLocator.getRuleService().lookupMaintainableClockLocationRules(cLocSearchCriteria);
        	if(!locationsRules.isEmpty()){
        		return locationsRules;
        	} else{
        		cLocSearchCriteria.setUniversityId("@");
        		locationsRules = TKServiceLocator.getRuleService().lookupMaintainableClockLocationRules(cLocSearchCriteria);
        		if(!locationsRules.isEmpty()){
        			return locationsRules;
        		}
        	}
        }
        return new ArrayList<ClockLocationRule>();
	}
	
	@SuppressWarnings("unused")
	private List<String> transformToIpAddress(List<ClockLocationRule> clockRules) {
		List<String> iNetAddresses = new ArrayList<String>();
		for(ClockLocationRule clockRule : clockRules){
			if (clockRule.isActive()) {
				iNetAddresses.add(clockRule.getIpAddress());
			}
		}
		return iNetAddresses;
	}

	private String transformToIpAddress(ClockLocationRule clockRule) {
		if (clockRule.isActive()) {
			return clockRule.getIpAddress();
		}
		return Utility.getDefaultStringValue();
	}
	
	public boolean isAuthorizedInetAddress(DocumentHeader documentHeader, TimeBlock timeBlock, Assignment assignment, ClockLog clockLog) {
		boolean isAuthorized = false;
		boolean isSuppressWarning = false;
		List<ClockLocationRule> rules = getClockLocation(documentHeader.getUniversityId(), timeBlock.getEmployeeRecord(), assignment.getJob().getDepartment(), assignment.getWorkArea());
		if (rules.isEmpty()) {
			return true;
		}
		int activeCount = 0;
		for (ClockLocationRule clockLocationRule : rules) {
			if(clockLocationRule.isActive()){
				activeCount++;
			}
		}
		
		if(activeCount == 0){
			return true;
		}
		
		for (ClockLocationRule clockLocationRule : rules) {
			if(clockLocationRule.isActive()){
				String iNetAddress = transformToIpAddress(clockLocationRule);
        		String[] iNetAddressParts = iNetAddress.split(SPLIT_STRING);
        		StringBuffer generatedPatternBuffer = new StringBuffer();
            	Pattern partialPattern = Pattern.compile(PATTERN);
        		for (int j = 0; j < iNetAddressParts.length; j++) {
            		Matcher partialMatcher = partialPattern.matcher(iNetAddressParts[j]);
            		if (partialMatcher.matches()) {
            			// not a wilcard
            			buildPattern(generatedPatternBuffer, iNetAddressParts[j], j, iNetAddressParts.length);
            		} else {
            			buildPattern(generatedPatternBuffer, PATTERN, j, iNetAddressParts.length);
            		}
        		}
        		String generatedPattern = generatedPatternBuffer.toString();
        		Pattern completePattern = Pattern.compile(generatedPattern);
            	Matcher completeMatcher = completePattern.matcher(clockLog.getIpAddress());
            	if (completeMatcher.matches()) {
            		return true;
            	}
			}
		}
//		if (!isAuthorized) {
//			for (ClockLocationRule clockLocationRule : rules) {
//				if (clockLocationRule.isSuppressWarning()) {
//					isSuppressWarning = true;
//				}
//			}
//		}
		return false;
	}
	
	private void buildPattern(StringBuffer generatedPattern, String patternStr, int currentIndex, int compareIndex) {
		if (currentIndex == (compareIndex - 1)) {
    		generatedPattern.append(patternStr);
		} else {
    		generatedPattern.append(patternStr);
    		generatedPattern.append(SPLIT_STRING);
		}
	}

	public ClockLogDataAccess getClockLogDataAccess() {
		return clockLogDataAccess;
	}

	public void setClockLogDataAccess(ClockLogDataAccess clockLogDataAccess) {
		this.clockLogDataAccess = clockLogDataAccess;
	}

	public TypedPersistentMaintainedEntityList getClockLogs(String universityId) {
		return getClockLogDataAccess().getClockLogs(universityId);
	}
}
