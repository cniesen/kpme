package edu.iu.uis.hr.tk.report;

import java.util.ArrayList;
import java.util.List;

public class Report {
	
	private String reportName;
	private String sql;
	private List<String> params;
	private List<String> paramValidations;
	
	public Report(String reportName, String sql, List<String> params, List<String> paramValidations) {
		this.reportName = reportName;
		this.sql = sql;
		this.setParams(params);
		this.setParamValidations(paramValidations);
	}
	public Report(String reportName, String sql) {
		this.reportName = reportName;
		this.sql = sql;
		this.setParams(new ArrayList<String>());
		this.setParamValidations(new ArrayList<String>());
	}
	public void setParamValidations(List<String> paramValidations) {
		this.paramValidations = paramValidations;
	}
	public List<String> getParamValidations() {
		return paramValidations;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	

}
