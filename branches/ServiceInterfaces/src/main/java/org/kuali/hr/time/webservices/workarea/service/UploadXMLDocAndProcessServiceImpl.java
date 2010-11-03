package org.kuali.hr.time.webservices.workarea.service;

import java.io.File;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.DataHandler;
import javax.servlet.ServletContext;

import org.kuali.hr.sys.util.constants.SpringBeans;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.util.TkConstants;
import org.kuali.hr.time.webservices.util.WebServiceUtil;
import org.kuali.hr.time.webservices.workarea.model.WorkAreaWrapper;
import org.kuali.hr.time.webservices.workarea.model.XMLDoc;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.hr.time.workarea.service.WorkAreaService;
import org.kuali.rice.kns.service.KNSServiceLocator;

import com.thoughtworks.xstream.XStream;

public class UploadXMLDocAndProcessServiceImpl implements
		UploadXMLDocAndProcessService {
	//set it to WorkArea.xsd location
	private final static String workAreaXSD = "d:\\WorkArea.xsd";

	@Override
	public String uploadXMLDocAndProcess(XMLDoc xmlDoc) {
		System.out.println("Inside uploadXMLDocAndProcess");
		String result = TkConstants.SUCCESSFUL_XML_DOC_UPLOAD;
		FileOutputStream os = null;
		InputStream is = null;
		FileOutputStream fos = null;
		File tempFile = null;
		System.out.println("Length =" + xmlDoc.getData().length);
		try {
			tempFile = File.createTempFile("workarea", ".xml");
			fos = new FileOutputStream(tempFile);
			// writting request data to File
			fos.write(xmlDoc.getData());
			// validate the incoming file against schema
			WebServiceUtil.validateXMLwithXSD(tempFile.getAbsolutePath(),
					workAreaXSD);
			// it works ! now lets parse it.
			is = new ByteArrayInputStream(xmlDoc.getData());

			WorkAreaWrapper pListNew = (WorkAreaWrapper) WebServiceUtil
					.parseXml(is, WorkAreaWrapper.class, WorkArea.class);
			for (WorkArea p1 : pListNew.getWorkAreas()) {
				//temporarily set to null ,update case will be taken care later
				p1.setTkWorkAreaId(null); 
				KNSServiceLocator.getBusinessObjectService().save(p1);				
				
				//System.out.println("" + p1.getTkWorkAreaId());
			}

		} catch (Exception ex) {
			System.out.println("IOException : " + ex);
			ex.printStackTrace();
			result = TkConstants.FAILURE_XML_DOC_UPLOAD + ex.getMessage();
		} finally {
			close(fos);
			close(is);
		}
		return result;
	}

	public void flush(Flushable flushable) {
		if (flushable == null)
			return;
		try {
			flushable.flush();
		} catch (IOException e) {
			e.printStackTrace();
			// log the exception
		}
	}

	public void close(Closeable closable) {
		if (closable == null)
			return;
		try {
			closable.close();
		} catch (IOException e) {
			e.printStackTrace();
			// log the exception
		}
	}
}