package org.kuali.hr.time.workarea.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.authorization.DepartmentalRule;
import org.kuali.hr.time.authorization.DepartmentalRuleAuthorizer;
import org.kuali.hr.time.authorization.TkAuthorizedLookupableHelperBase;
import org.kuali.hr.time.workarea.WorkArea;
import org.kuali.rice.kns.authorization.BusinessObjectRestrictions;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.web.struts.form.LookupForm;

import java.util.List;
import java.util.Map;

public class WorkAreaLookupableHelper extends TkAuthorizedLookupableHelperBase {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    @Override
    /**
     * Implemented method to reduce the set of Business Objects that are shown
     * to the user based on their current roles.
     */
    public boolean shouldShowBusinessObject(BusinessObject bo) {
        return (bo instanceof DepartmentalRule) && DepartmentalRuleAuthorizer.hasAccessToRead((DepartmentalRule)bo);
    }

	@Override
	public List<HtmlData> getCustomActionUrls(BusinessObject businessObject,
			List pkNames) {
		List<HtmlData> customActionUrls = super.getCustomActionUrls(
				businessObject, pkNames);
		WorkArea workArea = (WorkArea) businessObject;
		final String className = this.getBusinessObjectClass().getName();
		final Long tkWorkAreaId = workArea.getTkWorkAreaId();
		HtmlData htmlData = new HtmlData() {

			@Override
			public String constructCompleteHtmlTag() {
				return "<a target=\"_blank\" href=\"inquiry.do?businessObjectClassName="
						+ className + "&methodToCall=start&tkWorkAreaId=" + tkWorkAreaId
						+ "\">view</a>";
			}
		};
		customActionUrls.add(htmlData);
		return customActionUrls;
	}

	@Override
	public HtmlData getReturnUrl(BusinessObject businessObject,
			LookupForm lookupForm, List returnKeys,
			BusinessObjectRestrictions businessObjectRestrictions) {
		if (lookupForm.getFieldConversions().containsKey("effectiveDate")) {
			lookupForm.getFieldConversions().remove("effectiveDate");
		}
		if (returnKeys.contains("effectiveDate")) {
			returnKeys.remove("effectiveDate");
		}
		return super.getReturnUrl(businessObject, lookupForm, returnKeys,
				businessObjectRestrictions);
	}
	
	@Override
	public List<? extends BusinessObject> getSearchResults(
			Map<String, String> fieldValues) {
		if (fieldValues.containsKey("workArea")
				&& StringUtils.equals(fieldValues.get("workArea"), "%")) {
			fieldValues.put("workArea", "");
		}
		if (fieldValues.containsKey("dept")
				&& StringUtils.equals(fieldValues.get("dept"), "%")) {
			fieldValues.put("dept", "");
		}
		return super.getSearchResults(fieldValues);
	}


}
