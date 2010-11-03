package org.kuali.hr.time.webservices.workarea.model;

import javax.activation.DataHandler;

public class XMLDoc {
	private String fileName;
	private String fileType;
	private byte[] data;
	
	public XMLDoc(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	public XMLDoc() {
		super();
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	
}
