package org.kuali.hr.time.department.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.department.Department;
import org.kuali.hr.time.exceptions.TkException;

/**
 * @author bsoohoo
 * 
 */
@WebService(name = "departmentServiceSOAP", targetNamespace = "/tk")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface DepartmentService {

	/**
	 * @return
	 * @throws TkException
	 */
	@WebMethod
	public List<Department> getAllDepartments() throws TkException;

	/**
	 * @param deptCode
	 * @return
	 * @throws TkException
	 */
	@WebMethod
	public List<Department> getDepartmentByDeptCode(
			@WebParam(name = "deptCode") String deptCode) throws TkException;

	/**
	 * @param departments
	 * @return
	 * @throws TkException
	 */
	@WebMethod
	public boolean addDepartments(
			@WebParam(name = "department") List<Department> departments)
			throws TkException;

}
