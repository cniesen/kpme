package org.kuali.hr.time.department.validation;

import java.sql.Date;
import java.sql.Timestamp;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * @author bsoohoo This class provides validation for incoming Department data
 */
public class DepartmentServiceRule {

	/**
	 * Check if department exists
	 * 
	 * @param department
	 *            - the Department to be validated.
	 * @return boolean - whether or not Department has a valid id.
	 */
	public boolean validateDepartment(Department department) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Department.class,
						department.getTkDeptId()) != null) {
			valid = true;
		}
		return valid;
	}

	/**
	 * Check by Department Code if department exists
	 * 
	 * @param department
	 *            - the Department to be validated.
	 * @return boolean - whether or not Department has a valid dept code
	 */
	public boolean validateDepartmentByCode(Department department) {
		boolean valid = false;
		if (KNSServiceLocator.getBusinessObjectService()
				.findBySinglePrimaryKey(Department.class, department.getDept()) != null) {
			valid = true;
		}
		return valid;
	}

	/**
	 * Checks if dept code (String datatype) is a 10 digit number.
	 * 
	 * @param department
	 *            - the Department to be validated.
	 * @return boolean value whether dept code is valid 10 digit number.
	 * @throws ServiceException
	 */
	public boolean validateDepartmentCode(Department department)
			throws ServiceException {
		boolean valid = false;
		String dept = department.getDept();

		if (dept.length() == 10) {

			for (int i = 0; i < dept.length(); i++) {
				if (!Character.isDigit(dept.charAt(i))) {
					throw new IllegalArgumentException(
							"Department Code can contain numberic digits only: "
									+ dept);
				} else
					valid = true;
			}
		} else {
			throw new IllegalArgumentException("Invalid department code: "
					+ dept);
		}
		return valid;
	}

	/**
	 * Validates Department for missing required properties.
	 * 
	 * @param department
	 *            - the Department object to be validated
	 * @return department - the validated Department
	 * @throws Exception
	 */
	public Department validateDepartmentObject(Department department)
			throws Exception {

		try {
			validateDepartmentCode(department);

			if ((department.getDescription() == null)
					|| (department.getDescription().equals("")))
				department.setDescription("Dept: " + department.getDept());

			if ((department.getOrg() == null)
					|| (department.getOrg().equals("")))
				department.setOrg("USC");

			if ((department.getChart() == null)
					|| (department.getChart().equals("")))
				department.setChart("UPC");

			if ((department.getActive() == null)
					|| (department.getActive().equals("")))
				department.setActive(true);

			checkDateAndTime(department);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}

		return department;
	}

	/**
	 * If new Department exists in database but is missing Effective Date, get
	 * Effective Date from existing Department. Then
	 * {@link #validateDepartmentObject(Department)} is called to validate the
	 * rest of the new Department properties. This method assumes the database
	 * has already been checked for an existing Department record and the
	 * existing Department has been retrieved.
	 * 
	 * @param newDept
	 *            - new incoming Department to be saved.
	 * @param oldDept
	 *            - existing Department retrieved from the database.
	 * @return newDept - the new incoming Department that has been validated.
	 * @throws Exception
	 */
	public Department compareDepartments(Department newDept, Department oldDept)
			throws Exception {

		try {
			if ((newDept.getDescription() == null)
					|| (newDept.getDescription().equals(""))) {
				newDept.setDescription(oldDept.getDescription());
			}

			if ((newDept.getEffectiveDate() == null)
					|| (newDept.getEffectiveDate().equals(""))) {
				Date oldEffDate = new Date(oldDept.getEffectiveDate().getTime());
				newDept.setEffectiveDate(oldEffDate);
			}

			validateDepartmentObject(newDept);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}

		return newDept;
	}

	/**
	 * Adds current date and/or Timestamp if Department is missing Effective
	 * Date and/or Timestamp.
	 * 
	 * @param department
	 * @return department
	 */
	public Department checkDateAndTime(Department department) {

		Date today = new Date(System.currentTimeMillis());
		Timestamp todayTS = new Timestamp(System.currentTimeMillis());

		if (department.getEffectiveDate() == null) {
			department.setEffectiveDate(today);
		}

		if (department.getTimestamp() == null) {
			department.setTimestamp(todayTS);
		}
		return department;
	}

}
