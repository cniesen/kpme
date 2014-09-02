package org.kuali.kpme.core.tkmdocument.validation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.kuali.kpme.core.api.namespace.KPMENamespace;
import org.kuali.kpme.core.api.paygrade.PayGrade;
import org.kuali.kpme.core.api.workarea.WorkArea;
import org.kuali.kpme.core.assignment.AssignmentBo;
import org.kuali.kpme.core.assignment.account.AssignmentAccountBo;
import org.kuali.kpme.core.role.KPMERole;
import org.kuali.kpme.core.service.HrServiceLocator;
import org.kuali.kpme.core.tkmdocument.KhrEmployeeDocument;
import org.kuali.kpme.core.tkmdocument.tkmjob.KhrEmployeeJob;
import org.kuali.kpme.core.util.HrContext;
import org.kuali.kpme.core.util.ValidationUtils;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.rules.MaintenanceDocumentRuleBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class KhrEmployeeDocumentValidation extends MaintenanceDocumentRuleBase {
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        boolean valid = false;
        LOG.debug("entering custom validation for TKMDocument");
        KhrEmployeeDocument tkmDocument = (KhrEmployeeDocument) this.getNewDataObject();

        if (tkmDocument != null) {
            valid = true;
            valid &= this.validateJobLines(tkmDocument);
        }
        return valid;
    }

    protected boolean validateJobLines(KhrEmployeeDocument tkmDocument) {
        boolean valid = true;
        if (CollectionUtils.isNotEmpty(tkmDocument.getJobList())) {
            for(ListIterator<KhrEmployeeJob> jobIterator = tkmDocument.getJobList().listIterator(); jobIterator.hasNext();)  {
                int index = jobIterator.nextIndex();
                KhrEmployeeJob tkmJob = jobIterator.next();

                //validate job effective date
                if (tkmJob.getEffectiveDate().before(tkmDocument.getStartDate())) {
                    this.putFieldError("dataObject.jobList["+ index +"].effectiveDate","tkmjob.effective.before.start");
                    valid = false;
                }

                //validate comp rate
                if (tkmJob.getCompRate().bigDecimalValue().compareTo(new BigDecimal(7.25)) < 0) {
                    this.putFieldError("dataObject.jobList["+ index +"].compRate","tkmjob.minimum.wage");
                    valid = false;
                }

                //validate end date
                if (tkmJob.getEndDate() != null && tkmJob.getEndDate().after(LocalDate.now().plusMonths(18).toDate())) {
                    this.putFieldError("dataObject.jobList["+ index +"].endDate","tkmjob.invalid.end.date");
                    valid = false;
                }

                //validate department
                if (!HrServiceLocator.getKPMERoleService().principalHasRoleInDepartment(HrContext.getTargetPrincipalId(), KPMENamespace.KPME_TK.getNamespaceCode(), KPMERole.TIME_DEPARTMENT_ADMINISTRATOR.getRoleName(), tkmJob.getDept(), tkmJob.getGroupKeyCode(), LocalDate.now().toDateTimeAtStartOfDay())) {
                    String[] params = new String[1];
                    params[0] = tkmJob.getDept();
                    this.putFieldError("newCollectionLines['document.newMaintainableObject.dataObject.jobList'].dept", "tkmdocument.job.permissions", params);
                    valid = false;
                }

                //validate pay grade
                String validSalGroup = "XH"; //todo create parameter for valid salary groups
                List<String> validPayGrades  = new ArrayList<String>();

                for (PayGrade payGrade : HrServiceLocator.getPayGradeService().getPayGrades("", "", validSalGroup, "Y", "N")) {
                    validPayGrades.add(payGrade.getPayGrade());
                }

                if(!validPayGrades.contains(tkmJob.getPayGrade())){
                    String[] params = new String[1];
                    params[0] = validSalGroup;
                    this.putFieldError("dataObject.jobList["+ index +"].payGrade","tkmjob.invalid.paygrade",params);
                    valid = false;
                }

                //validate Assignments
                if (CollectionUtils.isNotEmpty(tkmJob.getAssignments())) {
                    for(ListIterator<AssignmentBo> assignmentIterator = tkmJob.getAssignments().listIterator(); assignmentIterator.hasNext();) {
                        int assignmentIndex = assignmentIterator.nextIndex();
                        AssignmentBo assignment = assignmentIterator.next();

                        //validate assignment effective date
                        if(assignment.getEffectiveDate().before(tkmJob.getEffectiveDate())) {
                            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].effectiveDate","tkmdocument.assignment.effective.before.job");
                            valid = false;
                        }

                        //validate workarea department
                        List<Long> validWorkAreasforDepartment = HrServiceLocator.getWorkAreaService().getWorkAreasForDepartment(tkmJob.getDept(),tkmJob.getEffectiveLocalDate());
                        if(!validWorkAreasforDepartment.contains(assignment.getWorkArea())){
                            String[] params = new String[2];
                            params[0] = assignment.getWorkArea().toString();
                            params[1] = tkmJob.getDept();
                            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","tkmdocument.assignment.workarea.wrong.dept",params);
                            valid = false;
                        }

                        //validate workarea
                        WorkArea workArea = HrServiceLocator.getWorkAreaService().getWorkArea(assignment.getWorkArea(),assignment.getEffectiveLocalDate());
                        if (workArea == null) {
                            String[] params = new String[1];
                            params[0] = assignment.getWorkArea().toString();
                            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","tkmdocument.assignment.workarea.not.active",params);
                            valid = false;
                        }

                        if (CollectionUtils.isNotEmpty(assignment.getAssignmentAccounts())) {
                            BigDecimal totalPercent = new BigDecimal(0);
                            for(ListIterator<AssignmentAccountBo> assignmentAccountIterator = assignment.getAssignmentAccounts().listIterator(); assignmentAccountIterator.hasNext();) {
                                int assignmentAccountIndex = assignmentAccountIterator.nextIndex();
                                AssignmentAccountBo assignmentAccount = assignmentAccountIterator.next();

                                totalPercent = totalPercent.add(assignmentAccount.getPercent());

                                //validate account
                                //todo figure out how to pass chart
                                if (!ValidationUtils.validateAccount("IS", assignmentAccount.getAccountNbr())) {
                                    String[] params = new String[1];
                                    params[0] = assignmentAccount.getFinCoaCd();
                                    this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].assignmentAccounts[" + assignmentAccountIndex + "].accountNbr","exists.account");
                                    valid = false;
                                }
                                //validate sub account
                                if (StringUtils.isNotEmpty(assignmentAccount.getSubAcctNbr())) {
                                    if (!ValidationUtils.validateSubAccount(assignmentAccount.getSubAcctNbr(), assignmentAccount.getAccountNbr(), "IS")) {
                                        String[] params = new String[2];
                                        params[0] = assignmentAccount.getSubAcctNbr();
                                        params[1] = assignmentAccount.getAccountNbr();
                                        this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].assignmentAccounts[" + assignmentAccountIndex + "].subAcctNbr","tkmdocument.assignment.subacct.exists",params);
                                    }
                                }
                            }

                            if (totalPercent.compareTo(new BigDecimal(100)) != 0) {
                                this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","error.active.account.percentage");
                                valid = false;
                            }
                        } else {
                            this.putFieldError("dataObject.jobList["+ index +"].assignmentList[" + assignmentIndex + "].workArea","earncode.regular.pay.required");
                            valid = false;
                        }
                    }

                } else {
                    this.putFieldError("dataObject.jobList["+ index +"].jobNumber","tkmjob.no.assignment");
                    valid = false;
                }

            }
        }

        return valid;

    }



}
