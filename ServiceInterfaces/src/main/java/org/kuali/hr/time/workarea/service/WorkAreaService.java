package org.kuali.hr.time.workarea.service;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.kuali.hr.time.util.exceptions.ServiceException;
import org.kuali.hr.time.workarea.WorkArea;

@WebService(name = "workAreaServiceSOAP", targetNamespace = "/tk")
@javax.jws.soap.SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface WorkAreaService {
	@WebMethod(exclude = true)
    public WorkArea getWorkArea(Long workAreaId, Date asOfDate);
	@WebMethod(exclude = true)
    public void saveOrUpdate(WorkArea workArea);
    @WebMethod
	public boolean addWorkAreas(
			@WebParam(name = "workAreas") List<WorkArea> workAreas) throws ServiceException;
    

}
