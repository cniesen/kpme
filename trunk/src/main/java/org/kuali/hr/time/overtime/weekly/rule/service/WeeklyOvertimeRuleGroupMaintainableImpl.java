package org.kuali.hr.time.overtime.weekly.rule.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.kuali.hr.time.overtime.weekly.rule.WeeklyOvertimeRule;
import org.kuali.hr.time.overtime.weekly.rule.WeeklyOvertimeRuleGroup;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class WeeklyOvertimeRuleGroupMaintainableImpl extends KualiMaintainableImpl{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
    public void addNewLineToCollection(String collectionName) {
        if (collectionName.equals("lstWeeklyOvertimeRules")) {
        	WeeklyOvertimeRule aRule = (WeeklyOvertimeRule)newCollectionLines.get(collectionName );
            if ( aRule != null ) {
            	WeeklyOvertimeRuleGroup aGroup = (WeeklyOvertimeRuleGroup)this.getBusinessObject();
            	Set<BigDecimal> steps = new HashSet<BigDecimal>();
            	for(WeeklyOvertimeRule wotr : aGroup.getLstWeeklyOvertimeRules()){
            		steps.add(wotr.getStep());
            	}
            	if(steps.contains(aRule.getStep())){
            		GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE +"lstWeeklyOvertimeRules", 
            				"weeklyOvertimeRule.duplicate.step",aRule.getStep().toString());
            		return;
    			} 
            }
        }
       super.addNewLineToCollection(collectionName);
    }

}
