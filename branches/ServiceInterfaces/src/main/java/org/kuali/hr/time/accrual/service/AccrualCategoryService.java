package org.kuali.hr.time.accrual.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import org.kuali.hr.time.accrual.AccrualCategory;
import org.kuali.hr.time.util.exceptions.ServiceException;


@WebService(name = "accrualCategoryServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface AccrualCategoryService {
	@WebMethod
	public boolean addAccrualCategories(
			@WebParam(name = "accrualCategories") List<AccrualCategory> accrualCategories) throws ServiceException;
 
}
