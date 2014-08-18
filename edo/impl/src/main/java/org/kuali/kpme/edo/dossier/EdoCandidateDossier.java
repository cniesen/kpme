package org.kuali.kpme.edo.dossier;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EDO_CANDIDATEDOSSIER_V")
public class EdoCandidateDossier {

    @Id
    @Column(name = "DOSSIER_ID", nullable = false, precision = 4)
    private BigDecimal dossierId;

    @Column(name = "CANDIDATE_ID", nullable = false, precision = 4)
    private BigDecimal candidateId;

    @Column(name = "DOCUMENT_ID", nullable = false, length = 40)
    private String documentId;

    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 45)
    private String lastName;

    @Column(name = "USERNAME", nullable = false, length = 16)
    private String username;

    @Column(name = "CANDIDACY_CAMPUS", nullable = false, length = 2)
    private String campus;

    @Column(name = "CANDIDACY_SCHOOL", nullable = false, length = 12)
    private String school;

    @Column(name = "PRIMARY_DEPARTMENT_ID", nullable = false, length = 12)
    private String department;

    @Column(name = "CANDIDACY_YEAR", nullable = false)
    private BigDecimal candidacyYear;

    @Column(name = "CURRENT_RANK", nullable = false, length = 32)
    private String currentRank;

    @Column(name = "RANK_SOUGHT", nullable = false, length = 32)
    private String rankSought;

    @Column(name = "DOSSIER_STATUS", nullable = false, length = 12)
    private String dossierStatus;

    @Transient
    private Date dueDate;

    @Column(name = "SECONDARY_UNIT", length = 12)
    private String secondaryUnit;

    @Column(name = "WORKFLOW_ID", nullable = false, length = 25)
    private String workflowId;

    public BigDecimal getDossierId() {
        return dossierId;
    }

    public void setDossierId(BigDecimal dossierId) {
        this.dossierId = dossierId;
    }

    public BigDecimal getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(BigDecimal candidateId) {
        this.candidateId = candidateId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getCandidacyYear() {
        return candidacyYear;
    }

    public void setCandidacyYear(BigDecimal candidacyYear) {
        this.candidacyYear = candidacyYear;
    }

    public String getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(String currentRank) {
        this.currentRank = currentRank;
    }

    public String getRankSought() {
        return rankSought;
    }

    public void setRankSought(String rankSought) {
        this.rankSought = rankSought;
    }

    public String getDossierStatus() {
        return dossierStatus;
    }

    public void setDossierStatus(String dossierStatus) {
        this.dossierStatus = dossierStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getSecondaryUnit() {
        return secondaryUnit;
    }

    public void setSecondaryUnit(String secondaryUnit) {
        this.secondaryUnit = secondaryUnit;
    }

    public String getCandidateDossierJSONString() {
        ArrayList<String> tmp = new ArrayList<String>();
        Type tmpType = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();
        String dueDateString = new SimpleDateFormat("yyyy-MM-dd").format(this.getDueDate());
        tmp.add(this.getCandidateId().toString());
        tmp.add(this.getDossierId().toString());
        tmp.add(this.getLastName());
        tmp.add(this.getFirstName());
        tmp.add(this.getUsername());
        tmp.add(this.getCurrentRank());
        tmp.add(this.getRankSought());
        tmp.add(this.getDepartment());
        tmp.add(this.getCampus());
        tmp.add(this.getSchool());
        tmp.add(this.getCandidacyYear().toString());
        tmp.add(dueDateString);
        tmp.add(this.getDossierStatus());
        // dossier ID 
        return gson.toJson(tmp, tmpType).toString();
    }
}
