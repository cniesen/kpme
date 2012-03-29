package edu.iu.hr.time.logic.dao;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import edu.iu.hr.time.earns.dist.EarningsDistribution;
import edu.iu.hr.time.service.base.TkServiceLocator;


public class ReadyToApproveDaoImpl implements ReadyToApproveDao{
	private static final String EARNS_DIST_SQL = "select A.ERNCD, A.DIST_PCT from sysadm.ps_job_earns_dist a where emplid = ? and EMPL_RCD = ? and " + 
											 "effdt = (select max(b.effdt) from sysadm.ps_job_earns_dist B where B.emplid = A.emplid and " +
											 "B.EMPL_RCD = A.EMPL_RCD and B.effdt <= ?) and EFFSEQ = (select max(c.effseq) from sysadm.ps_job_earns_dist "+
											 "C where C.emplid = A.emplid and C.EMPL_RCD = A.EMPL_RCD)";
	
	
	public EarningsDistribution getEarningsDistribution(String principalId, Integer emplRecord,Date effectiveDate ){
		EarningsDistribution earnDist = null;
		SqlRowSet rs = TkServiceLocator.getSudsJdbcTemplate().queryForRowSet(EARNS_DIST_SQL, new Object[]{principalId,emplRecord,effectiveDate});
		if(rs.next()){
			earnDist = new EarningsDistribution();
			earnDist.setEarnCode(rs.getString("A.ERNCD"));
			earnDist.setDistPct(rs.getBigDecimal("A.DIST_PCT"));
			if(rs.next()){
				earnDist.setEarnCode2(rs.getString("A.ERNCD"));
				earnDist.setDistPct2(rs.getBigDecimal("A.DIST_PCT"));
			}
		}
		
		return earnDist;
	}
}
