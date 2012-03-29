package edu.iu.hr.time.department;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.department.DepartmentRule;
import org.kuali.hr.time.roles.TkRole;
import org.kuali.hr.time.util.TkConstants;

import edu.iu.hr.time.util.IUTkConstants;

public class IUDepartmentRule extends DepartmentRule {

	@Override
	protected boolean validateRolePresent(List<TkRole> roles, Date effectiveDate) {
		boolean valid = false;

		if (roles != null && roles.size() > 0) {
			for (TkRole role : roles) {
				valid |= role.isActive()
						&& (StringUtils.equals(role.getRoleName(),
								TkConstants.ROLE_TK_DEPT_ADMIN) || StringUtils
								.equals(role.getRoleName(),
										IUTkConstants.ROLE_TK_PY_PROCESSOR));
			}
		}

		if (!valid) {
			this.putGlobalError("role.required");
		}

		return valid;
	}
}
