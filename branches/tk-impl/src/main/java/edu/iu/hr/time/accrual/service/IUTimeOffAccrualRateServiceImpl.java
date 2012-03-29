package edu.iu.hr.time.accrual.service;

import java.sql.Date;
import java.util.List;

import edu.iu.hr.time.accrual.AccrualRate;
import edu.iu.hr.time.accrual.dao.IUTimeOffAccrualRateDao;

public class IUTimeOffAccrualRateServiceImpl implements IUTimeOffAccrualRateService {
	private IUTimeOffAccrualRateDao timeOffAccrualRateDao;
	
	
	@Override
	public List<AccrualRate> getAccrualRate(String principalId, Date asOfDate) {
		return timeOffAccrualRateDao.getAccrualRate(principalId, asOfDate);
	}


	public IUTimeOffAccrualRateDao getTimeOffAccrualRateDao() {
		return timeOffAccrualRateDao;
	}


	public void setTimeOffAccrualRateDao(IUTimeOffAccrualRateDao timeOffAccrualRateDao) {
		this.timeOffAccrualRateDao = timeOffAccrualRateDao;
	}

}
