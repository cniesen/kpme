
package org.kuali.hr.time.earncode.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;

import org.kuali.hr.job.Job;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.dept.earncode.DepartmentEarnCode;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.earncode.dao.EarnCodeDao;
import org.kuali.hr.time.exceptions.TkException;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.rice.kns.service.KNSServiceLocator;


@WebService(endpointInterface = "org.kuali.hr.time.earncode.service.EarnCodeService")
public class EarnCodeServiceImpl implements EarnCodeService  {

	@Override
	public List<EarnCode> getEarnCodes(Assignment a) {
		List<EarnCode> earnCodes = new LinkedList<EarnCode>();
		
		if (a == null) 
			throw new RuntimeException("Can not get earn codes for null assignment");
		Job job = a.getJob();
		if (job == null || job.getPayTypeObj() == null)
			throw new RuntimeException("Null job/job paytype on assignment!");
		
		EarnCode regularEc = getEarnCode(job.getPayTypeObj().getRegEarnCode(), job.getEffectiveDate());
		if (regularEc == null) 
			throw new RuntimeException("No regular earn code defined.");
		earnCodes.add(regularEc);
		List<DepartmentEarnCode> decs = TkServiceLocator.getDepartmentEarnCodeService().getDepartmentEarnCodes(job.getDept(), job.getTkSalGroup(), job.getEffectiveDate());
		for (DepartmentEarnCode dec : decs) {
			// Iterating over these one by one, running a query because each earn code has effective dating/time stamp/active
			EarnCode ec = getEarnCode(dec.getEarnCode(), dec.getEffectiveDate());
			earnCodes.add(ec);
		}
		
		return earnCodes;
	}
	
	public EarnCode getEarnCode(String earnCode, Date asOfDate) {
		EarnCode ec = null;
		
		ec = SpringContext.getBean(EarnCodeDao.class).getEarnCode(earnCode, asOfDate);
		
		return ec;
	}
	
	// webService
	public boolean addEarnCodes(List<EarnCode> earnCodes) throws TkException{
		// go through each earnCodes
		// add earnCode if it is NOT there.
		for (EarnCode earnCode : earnCodes){
			try {
				
				// has to change to SpringContext.getBean instead of earnCodeDao
				EarnCode oldEarnCode = SpringContext.getBean(EarnCodeDao.class).getEarnCode(earnCode.getEarnCode()); 
				
				if (oldEarnCode == null){   // Create new record
					// SET some new fields for new record and default to true
					earnCode.setRecordAmount(true);
					earnCode.setRecordTime(true);
										
					KNSServiceLocator.getBusinessObjectDao().save(earnCode);
				}else{
					oldEarnCode.setDescription (earnCode.getDescription());
//	default			oldEarnCode.setRecordTime (earnCode.getRecordTime());
//	default			oldEarnCode.setRecordAmount (earnCode.getRecordAmount());
					oldEarnCode.setRecordHours (earnCode.getRecordHours());
					oldEarnCode.setRecordTime (true);
					oldEarnCode.setRecordAmount (true);
					
					oldEarnCode.setTimestamp   (earnCode.getTimestamp());
					oldEarnCode.setEffectiveDate (earnCode.getEffectiveDate());
					
					KNSServiceLocator.getBusinessObjectDao().save(oldEarnCode); // update existing record
				}

			}
			catch (Exception e){
				//
				throw new TkException(e.getMessage());
			}

		}
		return true;
		
	}

	
	
	
}
