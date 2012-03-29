package edu.iu.hr.time.logic.dao;

import java.sql.Date;

import edu.iu.hr.time.earns.dist.EarningsDistribution;

public interface ReadyToApproveDao {
	public EarningsDistribution getEarningsDistribution(String principalId, Integer emplRecord,Date effectiveDate );
}
