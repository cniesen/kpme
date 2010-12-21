package org.kuali.hr.time.accrual.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.accrual.TimeOffAccrual;
import org.kuali.hr.time.util.exceptions.ServiceException;

/**
 * 
 * @author Jigar
 * 
 */
@WebService(name = "timeOffAccrualServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface TimeOffAccrualService {

	@WebMethod
	public boolean addTimeOffAccruals(
			@WebParam(name = "timeOffAccruals") List<TimeOffAccrual> timeOffAccruals)
			throws ServiceException;

	@WebMethod(exclude = true)
	public List<TimeOffAccrual> getTimeOffAccruals(String principalId);

	@WebMethod(exclude = true)
	public List<Map<String, Object>> getTimeOffAccrualsCalc(String principalId);
}
