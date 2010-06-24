package edu.iu.uis.hr.tk.report.web.rule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import edu.iu.uis.hr.tk.report.web.TKRunReportForm;


public class TKRunReportRule {
	
	public String validateInputs(TKRunReportForm form){
		String errorMessage = "";
		Integer i = 0;
		for (String param : form.getParamsForReport()){
			if (StringUtils.equals(form.getReports().get(form.getReportToRun()).getParamValidations().get(i), "Date")){
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				try{
					Date testDate = df.parse(param);
					if (!df.format(testDate).equals(param))  {
						errorMessage += "Invalid Date ("+form.getReports().get(form.getReportToRun()).getParams().get(i)+")<br/>";
					}
				} catch (ParseException e){
					errorMessage += "Invalid date format ("+form.getReports().get(form.getReportToRun()).getParams().get(i)+")<br/>";
				}							
			} else if(StringUtils.equals(form.getReports().get(form.getReportToRun()).getParamValidations().get(i), "String")){
				errorMessage += (StringUtils.isEmpty(param)) ? "You must enter a value ("+form.getReports().get(form.getReportToRun()).getParams().get(i)+")<br/>" : "";
			}
			i++;
		}
		return errorMessage;
	}


}
