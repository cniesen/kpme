package org.kuali.hr.time.department.dao;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.exceptions.TkException;

/**
 * 
 * @author bsoohoo
 * 
 */
public interface DepartmentDao {

	/**
	 * @param dept
	 * @return
	 * @throws TkException
	 */
	public Department departmentExists(String dept) throws TkException;

	/**
	 * @param deptId
	 * @return
	 * @throws TkException
	 */
	public Department getDepartmentById(String deptId) throws TkException;

	/**
	 * @param dept
	 * @return
	 * @throws TkException
	 */
	public Department getDepartmentByDeptCode(String dept) throws TkException;

	/**
	 * @param department
	 */
	public void saveDepartment(Department department) throws TkException;

}
