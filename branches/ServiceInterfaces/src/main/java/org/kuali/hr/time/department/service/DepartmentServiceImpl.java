package org.kuali.hr.time.department.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kuali.hr.sys.context.SpringContext;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.department.dao.DepartmentDao;
import org.kuali.hr.time.department.validation.DepartmentServiceRule;
import org.kuali.hr.time.exceptions.TkException;
import org.kuali.rice.kns.service.KNSServiceLocator;

/**
 * 
 * @author bsoohoo
 * 
 */
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger LOG = Logger.getLogger(DepartmentService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kuali.hr.time.department.service.DepartmentService#getAllDepartments
	 * ()
	 */
	@Override
	public List<Department> getAllDepartments() throws TkException {
		Collection<Department> department = KNSServiceLocator
				.getBusinessObjectService().findAll(Department.class);
		return new ArrayList<Department>(department);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kuali.hr.time.department.service.DepartmentService#getDepartmentsByCode
	 * (java.lang.String)
	 */
	@Override
	public List<Department> getDepartmentByDeptCode(String deptCode)
			throws TkException {

		Map<String, Object> deptMap = new HashMap<String, Object>();
		deptMap.put("dept", deptCode);

		Collection<Department> department = KNSServiceLocator
				.getBusinessObjectService().findMatching(Department.class,
						deptMap);
		return new ArrayList<Department>(department);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kuali.hr.time.department.service.DepartmentService#addDepartment(
	 * java.util.List)
	 */
	@Override
	public boolean addDepartments(List<Department> departments)
			throws TkException {
		// go through each department coming in
		for (Department department : departments) {
			try {
				// Validate department
				DepartmentServiceRule departmentServiceRule = new DepartmentServiceRule();
				/*
				 * if (!departmentServiceRule.validateDepartment(department)) {
				 * throw new IllegalArgumentException("invalid data for job"); }
				 */
				// departmentServiceRule.validateDepartmentObject(department);
				// Get bean
				DepartmentDao departmentDao = SpringContext
						.getBean(DepartmentDao.class);
				// check if department with the same code exists and try to get
				// the Department object
				Department oldDepartment = departmentDao
						.departmentExists(department.getDept());
				// if dept doesn't exist, save (add) it
				if (oldDepartment == null) {
					System.out.println(" * * * NEW DEPT * * * ");
					departmentServiceRule.validateDepartmentObject(department);
					KNSServiceLocator.getBusinessObjectDao().save(department);
				} else {
					System.out.println(" * * * Old Dept * * * ");
					// id dept exists, update with new dept data
					departmentServiceRule.compareDepartments(department,
							oldDepartment);

					oldDepartment.setDept(department.getDept());
					oldDepartment.setDescription(department.getDescription());
					oldDepartment.setOrg(department.getOrg());
					oldDepartment.setChart(department.getChart());
					oldDepartment.setEffectiveDate(department
							.getEffectiveDate());
					oldDepartment.setTimestamp(department.getTimestamp());
					oldDepartment.setActive(department.getActive());
					KNSServiceLocator.getBusinessObjectDao()
							.save(oldDepartment);
				}
				// any exceptions will be transformed into service exceptions
			} catch (Exception e) {
				LOG.error(e);
				throw new TkException(e.toString());
			}
		}
		return true;
	}

}
