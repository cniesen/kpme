package edu.iu.uis.hr.tk.extract.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.Timestamp;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.tk.job.funding.entity.Assignment;

public class PayrollInterface extends AbstractPayrollInterface implements PersistentMaintainedEntity {
    private static final Logger LOG = Logger.getLogger(PayrollInterface.class);
    private static final String SPACE = " ";
    private static final String OPR_ID = "TIME";
    private static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal(0);

    public PayrollInterface() {
        super();
    }

    public PayrollInterface(String company, String paygroup, Date payEndDate, boolean offCycle, String universityId, BigDecimal employeeRecord, Date earnsBeginDate, Date earnsEndDate, String earnCode, BigDecimal pageNumber, BigDecimal lineNumber, Assignment assignment) {
        this();
        setCompany(company);
        setPaygroup(paygroup);
        setPayEndDate(payEndDate);
        setOffCycle(offCycle);
        setUniversityId("X"+universityId); // TODO : testing
        //setUniversityId(universityId);
        setEmployeeRecord(employeeRecord);
        setEarnsBeginDate(earnsBeginDate);
        setEarnsEndDate(earnsEndDate);
        setEarnCode(earnCode);
        setPageNumber(pageNumber);
        setLineNumber(lineNumber);
        setEmployeeRecordOther(employeeRecord);
        // TODO: accounting info 
        setAccount(assignment.getAccount());
        setChartOfAccounts(assignment.getChartOfAccounts());
        setSubAccount(assignment.getSubAccount());
        setSubObject(assignment.getSubObject());
        setObject(assignment.getObject());
        setOrganizationReferenceId(assignment.getOrganizationReferenceId());
        setProject(assignment.getProject());
        // TODO : Other columns
        setOrder(BIGDECIMAL_ZERO);
        setAdditionalPayLineNumber(BIGDECIMAL_ZERO);
        setAdditionalSequence(BIGDECIMAL_ZERO);
        setRate(BIGDECIMAL_ZERO);
        setAmount(BIGDECIMAL_ZERO);
        setPercentage(BIGDECIMAL_ZERO);

        setPayfundInterfaceId(SPACE); // doc id ?
        setPayfundStatus("U");
        setPayfundProcessMessage(SPACE);

        setCreatedOprId(OPR_ID);
        setLastUpdateOprId(OPR_ID);
        setDateTimeCreated(new Timestamp());
        setLastUpdateDateTime(new Timestamp());

        setBillDepartment("N");
        setDescription254(SPACE);
        setVoucherTableCode(SPACE);
        setAdjustmentVoucherType(SPACE);
        setGrossup(false); // space in old TK
    }

}
