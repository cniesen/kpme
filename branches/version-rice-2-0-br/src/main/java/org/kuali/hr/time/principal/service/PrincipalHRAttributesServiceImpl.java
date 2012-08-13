package org.kuali.hr.time.principal.service;

import org.kuali.hr.time.principal.PrincipalHRAttributes;
import org.kuali.hr.time.principal.dao.PrincipalHRAttributesDao;
import org.kuali.hr.time.service.base.TkServiceLocator;

import java.util.Date;
import java.util.List;

public class PrincipalHRAttributesServiceImpl implements PrincipalHRAttributesService {
	private PrincipalHRAttributesDao principalHRAttributesDao;

	public void setPrincipalHRAttributesDao(PrincipalHRAttributesDao principalHRAttributesDao) {
		this.principalHRAttributesDao = principalHRAttributesDao;
	}
	
	public PrincipalHRAttributes getPrincipalCalendar(String principalId, Date asOfDate){
		PrincipalHRAttributes pc =  this.principalHRAttributesDao.getPrincipalCalendar(principalId, asOfDate);
		if(pc != null) {
			pc.setCalendar(TkServiceLocator.getCalendarService().getCalendarByGroup(pc.getPayCalendar()));
			pc.setLeaveCalObj(TkServiceLocator.getCalendarService().getCalendarByGroup(pc.getLeaveCalendar()));
		}
		return pc;
	}
	
	/**
     * KPME-1250 Kagata
     * Get a list of active employees based on leave plan and as of a particular date
     */
    @Override
    public List<PrincipalHRAttributes> getActiveEmployeesForLeavePlan(String leavePlan, Date asOfDate) {
        List<PrincipalHRAttributes> principals = principalHRAttributesDao.getActiveEmployeesForLeavePlan(leavePlan, asOfDate);

        return principals;
    }
    
//    @Override
//	public PrincipalHRAttributes getPrincipalHRAttributes(String principalId) {
//		return this.principalHRAttributesDao.getPrincipalHRAttributes(principalId);
//	}
    
    @Override
    public PrincipalHRAttributes getInactivePrincipalHRAttributes(String principalId, Date asOfDate) {
    	return this.principalHRAttributesDao.getInactivePrincipalHRAttributes(principalId, asOfDate);
    }
    
    @Override
    public PrincipalHRAttributes getPrincipalHRAttributes(String hrPrincipalAttributeId) {
    	return this.principalHRAttributesDao.getPrincipalHRAttributes(hrPrincipalAttributeId);
    }
    
    @Override
    public List<PrincipalHRAttributes> getAllActivePrincipalHrAttributesForPrincipalId(String principalId, Date asOfDate) {
    	return this.principalHRAttributesDao.getAllActivePrincipalHrAttributesForPrincipalId(principalId, asOfDate);
    }
    @Override
    public PrincipalHRAttributes getMaxTimeStampPrincipalHRAttributes(String principalId) {
    	return principalHRAttributesDao.getMaxTimeStampPrincipalHRAttributes(principalId);
    }
    
    @Override
    public List<PrincipalHRAttributes> getAllInActivePrincipalHrAttributesForPrincipalId(String principalId, Date asOfDate) {
    	return this.principalHRAttributesDao.getAllInActivePrincipalHrAttributesForPrincipalId(principalId, asOfDate);
    }
    @Override
    public List<PrincipalHRAttributes> getActivePrincipalHrAttributesForRange(String principalId, Date startDate, Date endDate) {
    	return this.principalHRAttributesDao.getActivePrincipalHrAttributesForRange(principalId, startDate, endDate);
    }
    @Override
    public List<PrincipalHRAttributes> getInactivePrincipalHRAttributesForRange(String principalId, Date startDate, Date endDate) {
    	return this.principalHRAttributesDao.getInactivePrincipalHRAttributesForRange(principalId, startDate, endDate);
    }
}
