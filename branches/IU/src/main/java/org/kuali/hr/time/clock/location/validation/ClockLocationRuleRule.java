package org.kuali.hr.time.clock.location.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.kuali.hr.time.clock.location.ClockLocationRule;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class ClockLocationRuleRule extends MaintenanceDocumentRuleBase {

    private static final String REGEX_IP_ADDRESS_STRING = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    private static final Pattern REGEX_IP_ADDRESS_PATTERN = Pattern.compile(REGEX_IP_ADDRESS_STRING);

    private static Logger LOG = Logger.getLogger(ClockLocationRuleRule.class);

    protected boolean validateIpAddress(String ip) {
	boolean valid = false;

	LOG.debug("Validating IP address: " + ip);
	Matcher matcher = REGEX_IP_ADDRESS_PATTERN.matcher(ip);
	valid = matcher.matches();
	if (!valid) {
	    this.putFieldError("ipAddress", "ipaddress.invalid.format", ip);
	}

	return valid;
    }

    protected boolean validateWorkArea(ClockLocationRule clr) {
	return true;
    }

    protected boolean validateDepartment(ClockLocationRule clr) {
	return true;
    }

    protected boolean validateJobNumber(ClockLocationRule clr) {
	return true;
    }

    protected boolean validatePrincipalId(ClockLocationRule clr) {
	return true;
    }

    /**
     * It looks like the method that calls this class doesn't actually care about the return type.
     */
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
	boolean valid = false;

	LOG.debug("entering custom validation for ClockLocationRule");
	PersistableBusinessObject pbo = this.getNewBo();
	if (pbo instanceof ClockLocationRule) {
	    ClockLocationRule clr = (ClockLocationRule) pbo;
	    if (clr != null) {
		valid = true;
		valid &= this.validateDepartment(clr);
		valid &= this.validateWorkArea(clr);
		valid &= this.validatePrincipalId(clr);
		valid &= this.validateJobNumber(clr);
		valid &= this.validateIpAddress(clr.getIpAddress());
	    }
	}

	return valid;
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
	return super.processCustomApproveDocumentBusinessRules(document);
    }

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
	return super.processCustomRouteDocumentBusinessRules(document);
    }

}
