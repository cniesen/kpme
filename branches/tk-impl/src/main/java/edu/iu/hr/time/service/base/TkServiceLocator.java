package edu.iu.hr.time.service.base;

import org.kuali.hr.time.SudsDaoImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.iu.hr.time.accrual.service.IUTimeOffAccrualRateService;

public class TkServiceLocator extends
		org.kuali.hr.time.service.base.TkServiceLocator {
	
	public static final String SUDS_JDBC_TEMPLATE = "sudsJdbcTemplate";
	public static final String ACCRUAL_RATE_SERVICE = "iuTimeOffAccrualRateService";
	
	public static IUTimeOffAccrualRateService getAccrualRateService(){
		return (IUTimeOffAccrualRateService) CONTEXT.getBean(ACCRUAL_RATE_SERVICE);
	}
	
	public static JdbcTemplate getSudsJdbcTemplate(){
		return (JdbcTemplate)CONTEXT.getBean(SUDS_JDBC_TEMPLATE);
	}
	
	public static SudsDaoImpl getSudsDao(){
		return (SudsDaoImpl) CONTEXT.getBean("sudDao");
	}
	
	
}
