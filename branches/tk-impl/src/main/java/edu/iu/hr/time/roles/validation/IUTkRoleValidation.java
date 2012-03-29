package edu.iu.hr.time.roles.validation;


import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.roles.validation.TkRoleValidation;
import org.kuali.hr.time.util.ValidationUtils;

import edu.iu.hr.time.util.IUTkConstants;

public class IUTkRoleValidation extends TkRoleValidation {

	@Override
	protected boolean validateTkRole(TkRole role, String fieldPrefix) {
		boolean valid = false;

		valid = super.validateTkRole(role, fieldPrefix);

		if (StringUtils.equalsIgnoreCase(role.getRoleName(),
				IUTkConstants.ROLE_TK_PY_PROCESSOR)) {

			if (role.getDepartment() == null) {
				this.putFieldError(fieldPrefix + "department", "role.dept.and");
			} else {
				boolean validateDept = ValidationUtils.validateDepartment(
						role.getDepartment(), role.getEffectiveDate());
				
				if (!validateDept) {
					this.putFieldError(fieldPrefix + "department", "dept.notfound");
				}else {
					valid = true;
				}
				
			}

		}

		return valid;
	}
}
