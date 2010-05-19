package edu.iu.uis.hr.tk.workflow;

import java.util.List;

import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.AbstractRoleAttribute;
import org.kuali.rice.kew.rule.ResolvedQualifiedRole;

public class SupervisorPayrollProcessorRoleAttribute extends AbstractRoleAttribute {   
        
	/**
	 * 
	 */
	private static final long serialVersionUID = -7219461085659772481L;
	
	private SupervisorPayrollProcessorRoleAttributeImpl attribute = new SupervisorPayrollProcessorRoleAttributeImpl();
	

	@SuppressWarnings("unchecked")
	public List getRoleNames() {
		return attribute.getRoleNames();
	}
	
    @SuppressWarnings("unchecked")
	public List getQualifiedRoleNames(String roleName, DocumentContent documentContent){
    	return attribute.getQualifiedRoleNames(roleName, documentContent);
    }
    
    public ResolvedQualifiedRole resolveQualifiedRole(RouteContext context, String roleName, String qualifiedRole){
    	return attribute.resolveQualifiedRole(context, roleName, qualifiedRole);
    }
    
    
}
