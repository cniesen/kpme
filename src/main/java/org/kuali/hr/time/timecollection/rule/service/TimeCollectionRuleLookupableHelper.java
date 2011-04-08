package org.kuali.hr.time.timecollection.rule.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.hr.time.authorization.DepartmentalRuleAuthorizer;
import org.kuali.hr.time.authorization.TkAuthorizedLookupableHelperBase;
import org.kuali.hr.time.collection.rule.TimeCollectionRule;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;

import java.util.List;
import java.util.Map;

public class TimeCollectionRuleLookupableHelper extends
        TkAuthorizedLookupableHelperBase {
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
        return DepartmentalRuleAuthorizer.hasAccessToRead(bo);
    }

	@Override
	public List<HtmlData> getCustomActionUrls(BusinessObject businessObject,
			List pkNames) {
		List<HtmlData> customActionUrls = super.getCustomActionUrls(
				businessObject, pkNames);
		TimeCollectionRule timeCollectionRule = (TimeCollectionRule) businessObject;
		final String className = this.getBusinessObjectClass().getName();
		final Long tkTimeCollectionRuleId = timeCollectionRule.getTkTimeCollectionRuleId();
		HtmlData htmlData = new HtmlData() {

			@Override
			public String constructCompleteHtmlTag() {
				return "<a target=\"_blank\" href=\"inquiry.do?businessObjectClassName="
						+ className + "&methodToCall=start&tkTimeCollectionRuleId=" + tkTimeCollectionRuleId
						+ "&dept=&workArea=\">view</a>";
			}
		};
		customActionUrls.add(htmlData);
		return customActionUrls;
	}

	@Override
	public List<? extends BusinessObject> getSearchResults(
			Map<String, String> fieldValues) {
		if (fieldValues.containsKey("workArea")
				&& StringUtils.equals(fieldValues.get("workArea"), "%")) {
			fieldValues.put("workArea", "");
		}
		if (fieldValues.containsKey("jobNumber")
				&& StringUtils.equals(fieldValues.get("jobNumber"), "%")) {
			fieldValues.put("jobNumber", "");
		}
		return super.getSearchResults(fieldValues);
	}

	@Override
	protected void validateSearchParameterWildcardAndOperators(
			String attributeName, String attributeValue) {
		if (!StringUtils.equals(attributeValue, "%")) {
			super.validateSearchParameterWildcardAndOperators(attributeName,
					attributeValue);
		}
	}
}
