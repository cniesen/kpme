package org.kuali.hr.time.webservices.workarea.service;

import org.kuali.hr.time.webservices.workarea.model.XMLDoc;
import org.kuali.hr.time.workarea.service.WorkAreaService;
import org.kuali.hr.time.workarea.service.WorkAreaServiceImpl;

public interface UploadXMLDocAndProcessService {
   
  public String uploadXMLDocAndProcess(XMLDoc xmlDoc);
}
