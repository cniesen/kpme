package org.kuali.hr.leave.accrualcategory.rule;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

public class AccrualCategoryRule extends PersistableBusinessObjectBase implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long accrualCategoryPseudoKey;
    private String documentNumber;
    private BigDecimal startMonths;
    private BigDecimal endMonths;
    private BigDecimal maxTaken;
    private BigDecimal maxCarryover;
    private BigDecimal transferToAmount;
    private BigDecimal transferFromAmount;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getStartMonths() {
        return startMonths;
    }

    public void setStartMonths(BigDecimal startMonths) {
        this.startMonths = startMonths;
    }

    public BigDecimal getEndMonths() {
        return endMonths;
    }

    public void setEndMonths(BigDecimal endMonths) {
        this.endMonths = endMonths;
    }

    public BigDecimal getMaxTaken() {
        return maxTaken;
    }

    public void setMaxTaken(BigDecimal maxTaken) {
        this.maxTaken = maxTaken;
    }

    public BigDecimal getMaxCarryover() {
        return maxCarryover;
    }

    public void setMaxCarryover(BigDecimal maxCarryover) {
        this.maxCarryover = maxCarryover;
    }

    public BigDecimal getTransferToAmount() {
        return transferToAmount;
    }

    public void setTransferToAmount(BigDecimal transferToAmount) {
        this.transferToAmount = transferToAmount;
    }

    public BigDecimal getTransferFromAmount() {
        return transferFromAmount;
    }

    public void setTransferFromAmount(BigDecimal transferFromAmount) {
        this.transferFromAmount = transferFromAmount;
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
