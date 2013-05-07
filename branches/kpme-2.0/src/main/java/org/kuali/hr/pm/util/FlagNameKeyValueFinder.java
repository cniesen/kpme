package org.kuali.hr.pm.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.hr.pm.classification.Classification;
import org.kuali.hr.pm.classification.ClassificationFlag;
import org.kuali.hr.pm.positionflag.PositionFlag;
import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.web.form.MaintenanceDocumentForm;

public class FlagNameKeyValueFinder extends UifKeyValuesFinderBase {
	private static final long serialVersionUID = 1L;

	@Override
	public List<KeyValue> getKeyValues(ViewModel model) {
		MaintenanceDocumentForm docForm = (MaintenanceDocumentForm) model;
		List<KeyValue> options = new ArrayList<KeyValue>();
		Classification cf = (Classification) docForm.getDocument().getNewMaintainableObject().getDataObject();
		LocalDate aDate = cf.getEffectiveLocalDate() != null ? cf.getEffectiveLocalDate() : null;
		
		if(docForm.getActionEvent().equals("addLine")) {
			for(Object anObj : docForm.getAddedCollectionItems()) {
				ClassificationFlag aFlag = (ClassificationFlag) anObj;
				if(aFlag != null && StringUtils.isNotEmpty(aFlag.getNames())) {
					options.add(new ConcreteKeyValue(aFlag.getNames(), aFlag.getNames()));
					return options;
				}
			}
		} else {
			ClassificationFlag aFlagObj = (ClassificationFlag) docForm.getNewCollectionLines().get("document.newMaintainableObject.dataObject.flagList");
			String category = null;
			if(aFlagObj != null && StringUtils.isNotEmpty(aFlagObj.getCategory())) {
				category = aFlagObj.getCategory();
			}
			List<PositionFlag> flagList = PmServiceLocator.getPositionFlagService().getAllActivePositionFlagsWithCategory(category, aDate);
			if(CollectionUtils.isNotEmpty(flagList)) {
				for(PositionFlag aFlag : flagList) {
					options.add(new ConcreteKeyValue((String) aFlag.getPositionFlagName(), (String) aFlag.getPositionFlagName()));
				}
			}
		}
        return options;
	}

}
