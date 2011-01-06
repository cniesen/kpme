package org.kuali.hr.time.util;

import java.sql.Date;

import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.salgroup.SalGroup;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.workarea.WorkArea;

/**
 * A few methods to assist with various validation tasks.
 */
public class ValidationUtils {

	/** 
	 * Most basic validation: Only checks for presence in the database.
	 */
	public static boolean validateWorkArea(Long workArea) {
		return validateWorkArea(workArea, null);
	}

	/** 
	 * Most basic validation: Only checks for presence in the database.
	 */
	public static boolean validateDepartment(String department) {
		return validateDepartment(department, null);
	}
	
	public static boolean validateSalGroup(String salGroup, Date asOfDate) {
		boolean valid = false;

		if (StringUtils.equals(salGroup, TkConstants.WILDCARD_CHARACTER)) {
			valid = true;
		} else if (asOfDate != null) {
			SalGroup sg = TkServiceLocator.getSalGroupService().getSalGroup(salGroup, asOfDate);
			valid = (sg != null);
		} else {
			Criteria crit = new Criteria();
			crit.addEqualTo("dept", salGroup);		
			Query query = QueryFactory.newQuery(SalGroup.class, crit);
			int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);	
			valid = (count > 0);
		}
		
		return valid;
	}
	
	public static boolean validateEarnCode(String earnCode, Date asOfDate) {
		boolean valid = false;
		
		if (asOfDate != null) {
			EarnCode ec = TkServiceLocator.getEarnCodeService().getEarnCode(earnCode, asOfDate);
			valid = (ec != null);
		} else {
			Criteria crit = new Criteria();
			crit.addEqualTo("earnCode", earnCode);		
			Query query = QueryFactory.newQuery(EarnCode.class, crit);
			int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);
			valid = (count > 0);
		}
		
		return valid;
	}
	
	/**
	 * Checks for row presence of a department, and optionally whether or not 
	 * it is active as of the specified date.
	 */
	public static boolean validateDepartment(String department, Date asOfDate) {
		boolean valid = false;
		
		if (StringUtils.equals(department, TkConstants.WILDCARD_CHARACTER)) {
			valid = true;
		} else if (asOfDate != null) {
			Department d = TkServiceLocator.getDepartmentService().getDepartment(department, asOfDate);
			valid = (d != null);
		} else {
			Criteria crit = new Criteria();
			crit.addEqualTo("dept", department);		
			Query query = QueryFactory.newQuery(Department.class, crit);
			int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);	
			valid = (count > 0);
		}
		
		return valid;
	}

	/**
	 * Checks for row presence of a work area, and optionally whether or not 
	 * it is active as of the specified date.
	 */
	public static boolean validateWorkArea(Long workArea, Date asOfDate) {
		boolean valid = false;
		
		if (workArea == null) {
			valid = false;
		} else if (workArea.equals(TkConstants.WILDCARD_LONG)) {
			valid = true;
		} else if (asOfDate != null) {
			WorkArea wa = TkServiceLocator.getWorkAreaService().getWorkArea(workArea, asOfDate);
			valid = (wa != null);
		} else {
			Criteria crit = new Criteria();
			crit.addEqualTo("workArea", workArea);
			Query query = QueryFactory.newQuery(WorkArea.class, crit);
			int count = PersistenceBrokerFactory.defaultPersistenceBroker().getCount(query);	
			valid = (count > 0);
		}
		
		return valid;
	}
}
