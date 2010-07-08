package org.kuali.hr.leave.accrualcategory;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class AccrualCategory extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long accrualCategoryPseudoKey;
    private Long leavePlanPseudoKey;
    private String accrualCategory;
    private boolean prorationInd;
    private boolean showOnGridInd;
    private boolean defaultAccrualCategoryInd;
    private String documentNumber;
    private String previousDocumentId;
    private Date effectiveDate;
    private boolean activeInd;
    
    @Override
    protected LinkedHashMap toStringMapper() {
	// TODO Auto-generated method stub
	return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccrualCategoryPseudoKey() {
        return accrualCategoryPseudoKey;
    }

    public void setAccrualCategoryPseudoKey(Long accrualCategoryPseudoKey) {
        this.accrualCategoryPseudoKey = accrualCategoryPseudoKey;
    }

    public Long getLeavePlanPseudoKey() {
        return leavePlanPseudoKey;
    }

    public void setLeavePlanPseudoKey(Long leavePlanPseudoKey) {
        this.leavePlanPseudoKey = leavePlanPseudoKey;
    }

    public String getAccrualCategory() {
        return accrualCategory;
    }

    public void setAccrualCategory(String accrualCategory) {
        this.accrualCategory = accrualCategory;
    }

    public boolean isProrationInd() {
        return prorationInd;
    }

    public void setProrationInd(boolean prorationInd) {
        this.prorationInd = prorationInd;
    }

    public boolean isShowOnGridInd() {
        return showOnGridInd;
    }

    public void setShowOnGridInd(boolean showOnGridInd) {
        this.showOnGridInd = showOnGridInd;
    }

    public boolean isDefaultAccrualCategoryInd() {
        return defaultAccrualCategoryInd;
    }

    public void setDefaultAccrualCategoryInd(boolean defaultAccrualCategoryInd) {
        this.defaultAccrualCategoryInd = defaultAccrualCategoryInd;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPreviousDocumentId() {
        return previousDocumentId;
    }

    public void setPreviousDocumentId(String previousDocumentId) {
        this.previousDocumentId = previousDocumentId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public boolean isActiveInd() {
        return activeInd;
    }

    public void setActiveInd(boolean activeInd) {
        this.activeInd = activeInd;
    }

}
