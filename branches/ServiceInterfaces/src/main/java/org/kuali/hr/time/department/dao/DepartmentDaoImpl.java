package org.kuali.hr.time.department.dao;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.exceptions.TkException;
import org.springmodules.orm.ojb.support.PersistenceBrokerDaoSupport;

/**
 * 
 * @author bsoohoo
 * 
 */
public class DepartmentDaoImpl extends PersistenceBrokerDaoSupport implements
		DepartmentDao {

	/*
	 * @see
	 * org.kuali.hr.time.department.dao.DepartmentDao#departmemtExists(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Department departmentExists(String dept) throws TkException {

		Criteria criteria = new Criteria();
		criteria.addEqualTo("dept", dept);
		Object o = getPersistenceBrokerTemplate().getObjectByQuery(
				new QueryByCriteria(Department.class, criteria));

		return (Department) o;
	}

	/*
	 * @see
	 * org.kuali.hr.time.department.dao.DepartmentDao#getDepartmentById(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Department getDepartmentById(String tkDeptId) throws TkException {

		Criteria criteria = new Criteria();
		criteria.addEqualTo("id", tkDeptId);

		Object o = getPersistenceBrokerTemplate().getObjectByQuery(
				new QueryByCriteria(Department.class, criteria));

		return (Department) o;
	}

	/*
	 * @see
	 * org.kuali.hr.time.department.dao.DepartmentDao#getDepartmentByDeptCode
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Department getDepartmentByDeptCode(String dept) throws TkException {
		Criteria criteria = new Criteria();
		criteria.addEqualTo("dept", dept);

		Object o = getPersistenceBrokerTemplate().getObjectByQuery(
				new QueryByCriteria(Department.class, criteria));

		return (Department) o;
	}

	/*
	 * @see
	 * org.kuali.hr.time.department.dao.DepartmentDao#saveDepartment(org.kuali
	 * .hr.time.department.Department)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void saveDepartment(Department department) throws TkException {

		getPersistenceBrokerTemplate().store(department);

	}

}
