package org.kuali.hr.pm.classification.service;

import org.kuali.hr.pm.classification.Classification;
import org.kuali.hr.pm.classification.ClassificationDuty;
import org.kuali.hr.pm.classification.ClassificationFlag;
import org.kuali.hr.pm.classification.ClassificationQualification;
import org.kuali.hr.pm.service.base.PmServiceLocator;
import org.kuali.hr.time.HrBusinessObject;
import org.kuali.hr.time.util.HrBusinessObjectMaintainableImpl;

public class ClassificationMaintainableImpl extends HrBusinessObjectMaintainableImpl {
	private static final long serialVersionUID = 1L;

	@Override
	public HrBusinessObject getObjectById(String id) {
		return PmServiceLocator.getClassificationService().getClassificationById(id);
	}
	@Override
	public void customSaveLogic(HrBusinessObject hrObj){
		Classification aClss = (Classification) hrObj;
		for(ClassificationQualification aQual : aClss.getQualificationList()) {
			aQual.setPmPositionClassId(aClss.getPmPositionClassId());
			aQual.setPmClassificationQualificationId(null);
		}
		for(ClassificationDuty aDuty : aClss.getDutyList()) {
			aDuty.setPmPositionClassId(aClss.getPmPositionClassId());
			aDuty.setPmDutyId(null);
		}
		for(ClassificationFlag aFlag : aClss.getFlagList()) {
			aFlag.setPmPositionClassId(aClss.getPmPositionClassId());
			aFlag.setPmFlagId(null);
		}
		
	}
	

}
