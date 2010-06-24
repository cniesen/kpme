package edu.iu.uis.hr.tk.report.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.iu.uis.hr.client.AbstractStrutsActionForm;
import edu.iu.uis.hr.tk.report.Report;


public class TKRunReportForm extends AbstractStrutsActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4049275549693940758L;

	private static final String FINAL_CHECK_TIMESHEET_CALC_SQL = "SELECT DISTINCT H.*, T.BEGIN_TS FROM tk.TK_DOCUMENT_HEADER_T H, tk.TK_TIME_BLOCK_T T WHERE H.PAY_END_DT = TO_DATE(?, 'MM/DD/YYYY') AND h.DOCUMENT_ID = t.DOCUMENT_ID AND H.EMPLID IN (SELECT /*+ORDERED */ a.EMPLID FROM sysadm.PS_PAY_EARNINGS A, sysadm.PS_JOB B WHERE A.COMPANY = B.COMPANY   AND A.PAYGROUP = B.PAYGROUP   AND B.EMPLID = A.EMPLID   AND B.EMPL_RCD = A.EMPL_RCD   AND B.EFFDT = (SELECT MAX(B_ED.EFFDT) FROM sysadm.PS_JOB B_ED   WHERE B.EMPLID = B_ED.EMPLID   AND B.EMPL_RCD = B_ED.EMPL_RCD)   AND B.EFFSEQ = (SELECT MAX(B_ES.EFFSEQ) FROM sysadm.PS_JOB B_ES   WHERE B.EMPLID = B_ES.EMPLID   AND B.EMPL_RCD = B_ES.EMPL_RCD   AND B.EFFDT = B_ES.EFFDT)   AND A.PAY_SHEET_SRC = 'K'   AND A.PAY_END_DT = H.PAY_END_DT   AND B.EMPL_STATUS IN ('T')) AND t.BEGIN_TS = (SELECT MAX(t1.begin_ts) FROM tk.tk_time_block_t t1 WHERE t1.DOCUMENT_ID = h.document_id) ORDER BY 1";
	private static final String BAD_EARNINGS_REPORT_SQL = "SELECT * FROM tk.TK_TIME_BLOCK_T WHERE begin_ts > to_date( ?, 'MM/DD/YYYY') AND ERNCD NOT IN ( SELECT e.erncd FROM sysadm.PS_ERN_PROGRAM_def e WHERE e.ern_program = 'T26' ) AND erncd <> 'RGH' AND erncd <> 'RGW' AND erncd <> 'RGP' AND erncd <> 'OOR' AND WORK_AREA_ID IN ( SELECT DISTINCT a.work_area_id FROM tk.TK_WORK_AREA_T a WHERE a.EMPL_TYPE = 'H' AND a.EFFDT = ( SELECT MAX(b.effdt) FROM tk.TK_WORK_AREA_T b WHERE a.work_area_id = b.WORK_AREA_ID ) AND a.effseq = (SELECT MAX(C.effseq) FROM tk.TK_WORK_AREA_T C WHERE a.work_area_id = C.work_area_id AND a.effdt = C.effdt ) ) ORDER BY 1 DESC";
	private static final String EMPLOYEE_APPROVAL_SQL = "SELECT w.deptid, d.emplid, t.* FROM tk.TK_TIME_BLOCK_T T, tk.TK_WORK_AREA_T W, tk.TK_DOCUMENT_HEADER_T D WHERE d.document_id = t.document_id AND T.DOCUMENT_ID IN ( SELECT H.DOCUMENT_ID FROM tk.TK_DOCUMENT_HEADER_T H WHERE H.PAY_END_DT = to_date( ?, 'MM/DD/YYYY') AND DOCUMENT_STATUS = 'E' ) AND T.work_area_id = W.WORK_AREA_ID AND w.EFFDT = ( SELECT MAX(w1.effdt) FROM tk.tk_work_area_t W1 WHERE w1.WORK_AREA_ID = w.work_area_id ) AND w.effseq = (SELECT MAX(w2.effseq) FROM tk.tk_work_area_t W2 WHERE w2.WORK_AREA_ID = w.work_area_id AND w2.effdt = w.effdt )";
	
	private String method;
	private String reportToRun;
	private String reportError;
	private Boolean reportRunComplete;
	private String[] paramsForReport;
	private final static Map<String, Report> REPORTS = new LinkedHashMap<String, Report>();
	static{
		List<String> eaParams = new ArrayList<String>();
		eaParams.add("Earnings End Date");
		List<String> eaParamValidations = new ArrayList<String>();
		eaParamValidations.add("Date");
		REPORTS.put("Employee Approval", new Report("Employee Approval", EMPLOYEE_APPROVAL_SQL, eaParams, eaParamValidations));
		List<String> beParams = new ArrayList<String>();
		beParams.add("Pay Period Begin Date");
		List<String> beParamValidations = new ArrayList<String>();
		beParamValidations.add("Date");
		REPORTS.put("Bad Earnings Report", new Report("Bad Earnings Report", BAD_EARNINGS_REPORT_SQL, beParams, beParamValidations));
		List<String> fcParams = new ArrayList<String>();
		fcParams.add("Earnings End Date");
		List<String> fcParamValidations = new ArrayList<String>();
		fcParamValidations.add("Date");
		REPORTS.put("Final Check Timesheet Calculations", new Report("Final Check Timesheet Calculations", FINAL_CHECK_TIMESHEET_CALC_SQL, fcParams, fcParamValidations));
	}
	private List<Map> results;
	
	public TKRunReportForm(){
		this.reportRunComplete = false;
		this.results = new ArrayList<Map>();		
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getReportToRun() {
		return reportToRun;
	}

	public void setReportToRun(String reportToRun) {
		this.reportToRun = reportToRun;
	}
	
	public Boolean getReportRunComplete() {
		return reportRunComplete;
	}
	
	public void setReportRunComplete(Boolean reportRunComplete) {
		this.reportRunComplete = reportRunComplete;
	}
	
	public Map<String, Report> getReports() {
		return REPORTS;
	}
	
	public List<Map> getResults() {
		return results;
	}
	
	public void setResults(List<Map> results) {
		this.results = results;
	}
	
	public void prepare() {
		// TODO Auto-generated method stub
		
	}

	public List getAccessAuthorizations() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getParamsForReport() {
		return paramsForReport;
	}

	public void setParamsForReport(String[] paramsForReport) {
		this.paramsForReport = paramsForReport;
	}

	public String getReportError() {
		return reportError;
	}

	public void setReportError(String reportError) {
		this.reportError = reportError;
	}
}
