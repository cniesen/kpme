package org.kuali.hr.time.earncode.service;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.earncode.EarnCode;
import org.kuali.hr.time.exceptions.TkException;

@WebService(name = "earnCodeServiceSOAP", targetNamespace = "/tk")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

public interface EarnCodeService {

	@WebMethod(exclude = true)
	public List<EarnCode> getEarnCodes(Assignment a);
	
	@WebMethod(exclude = true)
	public EarnCode getEarnCode(String earnCode, Date asOfDate);
	
	
	// WebService
	@WebMethod
	public boolean addEarnCodes(
			@WebParam(name = "earnCode") List<EarnCode> earnCodes) throws TkException;
	
	
}
