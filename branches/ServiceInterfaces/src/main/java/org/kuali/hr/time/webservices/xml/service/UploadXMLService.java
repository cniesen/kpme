package org.kuali.hr.time.webservices.xml.service;

import org.kuali.hr.time.webservices.xml.model.XMLDoc;


public interface UploadXMLService {
   
  public String uploadWorkArea(XMLDoc xmlDoc);
  public String uploadAssignmentAccount(XMLDoc xmlDoc);
  public String uploadAssignment(XMLDoc xmlDoc);
  public String uploadJob(XMLDoc xmlDoc);
  public String uploadTimeOffAccrual(XMLDoc xmlDoc);
  public String uploadAccrualCategory(XMLDoc xmlDoc);
  
}

