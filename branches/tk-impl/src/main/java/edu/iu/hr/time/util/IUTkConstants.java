package edu.iu.hr.time.util;

import org.kuali.hr.time.util.TkConstants;

public class IUTkConstants extends TkConstants {
	
    public static final String ROLE_TK_PY_PROCESSOR = "TK_PY_PROCESSOR";
    public static final String ROLE_TK_PY_PROCESSOR_DELEGATE = "TK_PY_PROCESSOR_DELEGATE";
    
    static {
    	ROLE_ASSIGNMENT_FOR_USER_ROLES.add(IUTkConstants.ROLE_TK_PY_PROCESSOR);
    	ROLE_ASSIGNMENT_FOR_USER_ROLES.add(IUTkConstants.ROLE_TK_PY_PROCESSOR_DELEGATE);
    	
    	ALL_ROLES_MAP.put(IUTkConstants.ROLE_TK_PY_PROCESSOR, "Payroll Processor");
    	ALL_ROLES_MAP.put(IUTkConstants.ROLE_TK_PY_PROCESSOR, "Payroll Processor Delegate");
    }
    
}
