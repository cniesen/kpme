package edu.iu.hr.time.accrual.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import uk.ltd.getahead.dwr.util.Logger;
import edu.iu.hr.time.accrual.AccrualRate;
import edu.iu.hr.time.service.base.TkServiceLocator;

public class IUTimeOffAccrualRateDaoImpl implements IUTimeOffAccrualRateDao {
	
	private static final Logger LOG = Logger.getLogger(IUTimeOffAccrualRateDaoImpl.class);

	private static final String GET_ACCRUAL_RATE_SQL = "select A0.plan_type, A0.benefit_plan, A0.hours_earned from sysadm.ps_leave_rate_tbl A0, "+
														"sysadm.ps_leave_plan B0, sysadm.PS_EG_FLX_SVC_DT C0 " +
														"where B0.emplid = ? and A0.plan_type = B0.plan_type" +
														"and C0.emplid = B0.emplid and a0.benefit_plan = b0.benefit_plan" +
														"and B0.effdt = (select max(effdt) from sysadm.ps_leave_plan where emplid = B0.emplid and effdt <= ?)" +
														"and a0.service_intervals = " +
														"(select max(D0.service_intervals) from sysadm.ps_leave_rate_tbl D0 " +
														"where D0.service_intervals <= months_between(SYSDATE,C0.EG_PROJ_DT) and "+
														"D0.plan_type = A0.plan_type and d0.benefit_plan = b0.benefit_plan) " +
														"and B0.coverage_elect='E'";
	
	@Override
	public List<AccrualRate> getAccrualRate(String principalId, Date asOfDate) {
		List<AccrualRate> accrualRates = new ArrayList<AccrualRate>();
		SqlRowSet rs = TkServiceLocator.getSudsJdbcTemplate().queryForRowSet(GET_ACCRUAL_RATE_SQL, new Object[]{principalId, asOfDate});
		while(rs.next()){
			AccrualRate accrualRate = new AccrualRate();
			accrualRate.setBenefitPlan(rs.getString("A0.benefit_plan"));
			accrualRate.setPlanType(rs.getString("A0.plan_type"));
			accrualRate.setHoursEarned(rs.getBigDecimal("A0.hours_earned"));
			accrualRates.add(accrualRate);
		}
		return accrualRates;
	}

}
