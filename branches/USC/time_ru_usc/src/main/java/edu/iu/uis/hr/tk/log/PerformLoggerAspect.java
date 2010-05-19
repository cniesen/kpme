package edu.iu.uis.hr.tk.log;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.kuali.rice.core.config.ConfigContext;

import edu.iu.uis.hr.tk.util.TkConstants;

@Aspect
public class PerformLoggerAspect {
	public static Logger LOG = Logger.getLogger(LogPerformanceMethod.class);
	
	@Around("@annotation(edu.iu.uis.hr.tk.log.LogPerformanceMethod)")
	public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
		if (LOG.isDebugEnabled()) {
			if (StringUtils.isBlank(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORMANCE)) || (!Boolean.parseBoolean(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORMANCE)))) {
				return pjp.proceed(pjp.getArgs());
			}
			Object obj = null;
			long before = System.currentTimeMillis();
			obj = pjp.proceed(pjp.getArgs());
			long after = System.currentTimeMillis();
			long msIgnore = 0;
			if (StringUtils.isNotBlank(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORM_TIME_IGNORE))) {
				msIgnore = Long.parseLong(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORM_TIME_IGNORE));
			}
			if ((after - before) > msIgnore) {
				LOG.debug(getMessageString(pjp, (after - before)));
				PerformanceInfo perfInfo = new PerformanceInfo(after-before, new Date(System.currentTimeMillis()), 
						pjp.getTarget().getClass().getName(),pjp.getSignature().getName());
				PerformanceInfoManager.addToPerformanceList(perfInfo);
			}
			return obj;
		}
		return pjp.proceed(pjp.getArgs());
	}
	@Pointcut("execution(* edu.iu.uis.hr.*.*.*.*(..)) OR " +
			"execution(* edu.iu.uis.hr.*.*.*(..)) OR " +
			  "execution(* edu.iu.uis.hr.tk.timesheet.service.TimesheetServiceImpl*(..)) OR " +
			  "execution(* edu.iu.uis.hr.*.*.*.*.*(..))")
	public void service(){}
	
	@Around("edu.iu.uis.hr.tk.log.PerformLoggerAspect.service()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		if (LOG.isDebugEnabled()) {
			if (StringUtils.isBlank(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORMANCE)) || 
					pjp.getTarget().getClass() != null) {
				if (StringUtils.isBlank(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORM_SERVICE)) || !Boolean.parseBoolean(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORM_SERVICE))) {
					return pjp.proceed(pjp.getArgs());
				}
			}
			if (!Boolean.parseBoolean(ConfigContext.getCurrentContextConfig().getProperty(TkConstants.ConfigSettings.LOG_PERFORMANCE))) {
				return pjp.proceed(pjp.getArgs());
			}

			return logPerformance(pjp);
		}
		return pjp.proceed(pjp.getArgs());
	}
	
	private String getMessageString(ProceedingJoinPoint pjp, long timeMS){
		String msg = "Total Time in Class: " + pjp.getTarget().getClass().getName()+ " Method: " + pjp.getSignature().getName(); 
		if(pjp.getArgs().length > 0){
			msg = msg + " Parameters: ";
			for(Object ob : pjp.getArgs()){
				if(ob!=null){
					msg = msg + ob.toString() + " ";
				}
			}
		}
		msg = msg + "Time: "+ timeMS + " ms";
		return msg;
	}
	
}
