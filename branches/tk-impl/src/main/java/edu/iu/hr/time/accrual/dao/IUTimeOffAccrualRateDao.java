package edu.iu.hr.time.accrual.dao;

import java.sql.Date;
import java.util.List;

import edu.iu.hr.time.accrual.AccrualRate;

public interface IUTimeOffAccrualRateDao {
	public List<AccrualRate> getAccrualRate(String principalId, Date asOfDate);
}
