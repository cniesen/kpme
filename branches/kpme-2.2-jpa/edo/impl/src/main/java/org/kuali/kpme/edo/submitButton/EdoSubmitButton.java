package org.kuali.kpme.edo.submitButton;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

@Entity
@Table(name = "EDO_SUBMIT_BUTTON_DISPLAY_T")
public class EdoSubmitButton {

    @Id
    @Column(name = "EDO_SUBMIT_BUTTON_DISPLAY_ID ")
    private BigDecimal edoSubmitButtonDisplayId;

    @Column(name = "CAMPUS_CODE ", length = 2)
    private String campusCode;

    @Column(name = "ACTIVE_FLAG", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean activeFlag;

    public BigDecimal getEdoSubmitButtonDisplayId() {
        return edoSubmitButtonDisplayId;
    }

    public void setEdoSubmitButtonDisplayId(BigDecimal edoSubmitButtonDisplayId) {
        this.edoSubmitButtonDisplayId = edoSubmitButtonDisplayId;
    }

    public String getCampusCode() {
        return campusCode;
    }

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }
}
