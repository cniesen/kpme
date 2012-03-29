package edu.iu.hr.time.accrual.service;

import java.sql.Date;
import java.util.List;

import edu.iu.hr.time.accrual.AccrualRate;

public interface IUTimeOffAccrualRateService {
	public List<AccrualRate> getAccrualRate(String principalId, Date asOfDate);
}
