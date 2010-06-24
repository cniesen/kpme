package edu.iu.uis.hr.tk.log;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import edu.iu.uis.hr.Context;

@Aspect
public class LogAspect {

	public static final String USER_NAME_STORAGE_MAP_KEY = "_userNameKey";
	
	public static final Logger LOG = Logger.getLogger(LogAspect.class);
		
	@SuppressWarnings("unchecked")
	@Around("execution(* edu.iu.uis.hr..*.*(..))")
	public Object doBasicLogging(ProceedingJoinPoint pjp) throws Throwable {
		Logger targetLog = null;
		Date startTime = null;
		String loggingPrepend = null;
		targetLog = Logger.getLogger(pjp.getTarget().getClass());
		try {
			if (targetLog.isDebugEnabled()) {
				startTime = new Date();
				String userName = (String)Context.getStorageMap().get(USER_NAME_STORAGE_MAP_KEY);
				loggingPrepend = "{" + userName + "} " + pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + getArgString(pjp.getArgs()) ;
				targetLog.debug("Entering method: " + loggingPrepend );
			}
			return pjp.proceed(pjp.getArgs());
		} finally {
			if (targetLog.isDebugEnabled()) {
				long totalTime = System.currentTimeMillis() - startTime.getTime();
				targetLog.debug("Exiting method: " + loggingPrepend + " :: PERFORMANCE :: Total Time: " + totalTime + "ms");
			}
		}
	}

	private String getArgString(Object[] args) {
		String argString = "(";
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				argString += "null";
				continue;
			}
			argString += args[i].getClass().getSimpleName();
			if (i < args.length - 1) {
				argString += ",";
			}
		}
		return argString + ")";
	}
}