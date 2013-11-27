package org.kuali.kpme.core.salarygroup.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.core.lookup.KPMELookupableHelper;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.util.TKUtils;
import org.kuali.rice.krad.bo.BusinessObject;

public class SalaryGroupLookupableHelper extends KPMELookupableHelper {

	@Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        String hrSalGroup = fieldValues.get("hrSalGroup");
        String fromEffdt = TKUtils.getFromDateString(fieldValues.get("effectiveDate"));
        String toEffdt = TKUtils.getToDateString(fieldValues.get("effectiveDate"));
        String active = fieldValues.get("active");
        String showHist = fieldValues.get("history");
        String institution = fieldValues.get("institution");
        String location = fieldValues.get("location");
        String leavePlan = fieldValues.get("leavePlan");

        if (StringUtils.equals(hrSalGroup, "%")) {
            hrSalGroup = "";
        }
        
        return HrServiceLocator.getSalaryGroupService().getSalaryGroups(hrSalGroup, institution, location, leavePlan, TKUtils.formatDateString(fromEffdt),
                TKUtils.formatDateString(toEffdt), active, showHist);
	}
}
