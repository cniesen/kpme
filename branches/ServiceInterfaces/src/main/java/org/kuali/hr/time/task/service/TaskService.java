package org.kuali.hr.time.task.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.task.Task;
import org.kuali.hr.time.util.exceptions.ServiceException;
/**
 * 
 * @author Jigar
 *
 */
@WebService(name = "taskServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface TaskService {

	@WebMethod
	public boolean addTasks(
			@WebParam(name = "tasks") List<Task> tasks)
			throws ServiceException;
}
