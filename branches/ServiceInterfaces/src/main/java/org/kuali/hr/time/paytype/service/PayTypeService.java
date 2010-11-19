package org.kuali.hr.time.paytype.service;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.paytype.PayType;

@WebService(name = "payTypeServiceSOAP", targetNamespace = "/tk") 
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface PayTypeService {

	@WebMethod(exclude = true)
	public void saveOrUpdate(PayType payType);
	
	@WebMethod(exclude = true)
	public void saveOrUpdate(List<PayType> payTypeList);
	
	/**
	 * Provides access to the PayType.   The PayCalendar will be loaded as well.
	 * @param payTypeId
	 * @return A fully populated PayType.
	 */
	@WebMethod(exclude = true)
	public PayType getPayType(String payType, Date effectiveDate);
	
	// WebService
	@WebMethod
	public boolean addPayTypes(
			@WebParam(name = "payType") List<PayType> payTypes)
			throws Exception;
}
