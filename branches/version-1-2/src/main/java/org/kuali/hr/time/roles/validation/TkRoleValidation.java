package org.kuali.hr.time.roles.validation;


import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.roles.TkRoleGroup;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class TkRoleValidation extends MaintenanceDocumentRuleBase{

    private static final String ADD_LINE_LOCATION = "add.roles.";

   

    protected boolean validateTkRole(TkRole role, String fieldPrefix) {
        boolean valid = true;

        if (fieldPrefix == null)
            fieldPrefix = "";

       String rname = role.getRoleName();
       if (StringUtils.equalsIgnoreCase(rname, TkConstants.ROLE_TK_LOCATION_ADMIN) || StringUtils.equalsIgnoreCase(rname, TkConstants.ROLE_TK_LOCATION_VO)) {
    	   this.putFieldError(fieldPrefix + "chart", "error.role.chart.required");
           valid = false;
        } 
       
        return valid;
    }

    /**
     * Currently, the prefix will not work when the attributes of the collection
     * items are set to readOnlyAfterAdd. This seems to be a built in function
     * of rice. Errors will still appear but no marker will be placed next to
     * the field.
     */
    boolean validateTkRoleGroup(TkRoleGroup roleGroup) {
        boolean valid = true;

        int pos = 0;
        for (TkRole role: roleGroup.getRoles()) {
            StringBuffer prefix = new StringBuffer("roles[");
            prefix.append(pos).append("].");
            validateTkRole(role, prefix.toString());
            pos++;
        }

        return valid;
    }

	@Override
	protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
		boolean valid = true;

		PersistableBusinessObject pbo = (PersistableBusinessObject)this.getNewBo();
		if (pbo instanceof TkRole) {
			TkRole role = (TkRole)pbo;
            valid = validateTkRole(role, null);
		} else if (pbo instanceof TkRoleGroup) {
            TkRoleGroup roleGroup = (TkRoleGroup)pbo;
            valid = validateTkRoleGroup(roleGroup);
        }

		return valid;
	}

    @Override
    /**
     * Validation for the 'add' role in the collection manipulator.
     */
    public boolean processCustomAddCollectionLineBusinessRules(MaintenanceDocument document, String collectionName, PersistableBusinessObject line) {
        boolean valid = true;

        if (line instanceof TkRole) {
            TkRole role = (TkRole)line;
            valid = validateTkRole(role, ADD_LINE_LOCATION);
        }

        return valid;
    }
}
