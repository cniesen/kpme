package org.kuali.hr.time.assignment.service;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.kuali.hr.time.assignment.AssignmentAccount;
import org.kuali.hr.time.util.exceptions.ServiceException;


@WebService(name = "assignmentAccountServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AssignmentAccountService {

	 @WebMethod
		public boolean addAssignmentAccounts(
				@WebParam(name = "assignmentAccounts") List<AssignmentAccount> assignmentAccounts) throws ServiceException;
	    
}
