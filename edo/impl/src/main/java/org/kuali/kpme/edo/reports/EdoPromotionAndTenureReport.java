package org.kuali.kpme.edo.reports;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.kuali.kpme.edo.api.reviewlayerdef.EdoReviewLayerDefinition;
import org.kuali.kpme.edo.reviewlayerdef.EdoReviewLayerDefinitionBo;
import org.kuali.kpme.edo.util.EdoConstants.VoteType;
import org.kuali.kpme.edo.util.EdoConstants;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.BusinessObjectBase;
import org.kuali.rice.krad.service.KRADServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.PersistenceService;
import org.kuali.rice.krad.util.ObjectUtils;

@Entity
@Table(name = "EDO_PT_REPORT_V")
public class EdoPromotionAndTenureReport extends BusinessObjectBase {

    private static final long serialVersionUID = -317961785047441240L;

    private static transient PersistenceService persistenceService;

    public static final List<String> VOTE_RECORD_VALUES = EdoConstants.VOTE_RECORD_VALUES;

    @Id
    @Column(name = "VOTE_RECORD_ID", nullable = false)
    private Integer voteRecordId;

    @Column(name = "CANDIDATE_USERNAME", length = 16)
    private String candidateUsername;

    @Column(name = "IU_LAST_NAME_PRF", length = 30)
    private String iuLastNamePref;

    @Column(name = "IU_FIRST_NAME_PRF", length = 30)
    private String iuFirstNamePref;

    @Column(name = "OPTION_VALUE", length = 50)
    private String optionValue;

    @Column(name = "CURRENT_RANK", length = 32)
    private String currentRank;

    @Column(name = "DOSSIER_ID", precision = 4)
    private BigDecimal dossierId;

    @Column(name = "DOSSIER_STATUS", length = 12)
    private String dossierStatus;

    @Column(name = "RANK_SOUGHT", length = 32)
    private String rankSought;

    @Column(name = "DEPARTMENT_ID", nullable = false, length = 12)
    private String departmentId;

    @Column(name = "SCHOOL_ID", length = 12)
    private String schoolId;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "DOSSIER_TYPE_NAME", length = 45)
    private String dossierTypeName;

    @Column(name = "DOSSIER_TYPE_ID", precision = 4)
    private BigDecimal dossierTypeId;

    @Column(name = "VOTE_ROUND")
    private Integer voteRound;

    @Column(name = "ROUTE_LEVEL", precision = 3)
    private BigDecimal routeLevel;

    @Column(name = "REVIEW_LAYER", length = 4000)
    private String reviewLayer;

    @Column(name = "VOTE_TYPE", length = 16)
    private String voteType;

    @Column(name = "WORKFLOW_ID", length = 25)
    private String workflowId;

    @Column(name = "ABSENT_COUNT_PROMOTION")
    private Integer absentCountPromotion;

    @Column(name = "ABSENT_COUNT_TENURE")
    private Integer absentCountTenure;

    @Column(name = "ABSTAIN_COUNT_PROMOTION")
    private Integer abstainCountPromotion;

    @Column(name = "ABSTAIN_COUNT_TENURE")
    private Integer abstainCountTenure;

    @Column(name = "NO_COUNT_PROMOTION")
    private Integer noCountPromotion;

    @Column(name = "NO_COUNT_TENURE")
    private Integer noCountTenure;

    @Column(name = "YES_COUNT_PROMOTION")
    private Integer yesCountPromotion;

    @Column(name = "YES_COUNT_TENURE")
    private Integer yesCountTenure;

    @Column(name = "AOE_CODE", length = 1)
    private String aoeCode;

    @Column(name = "VISA_PERMIT_TYPE", length = 100)
    private String visaPermitType;

    @Column(name = "ETHNICITY", length = 100)
    private String ethnicity;

    @Transient
    private boolean early;

    @Transient
    private Map<BigDecimal, EdoPromotionAndTenureReport> voteRecordMap;

    @Transient
    private List<EdoReviewLayerDefinition> reviewRouteLevels;

    public Integer getVoteRecordId() {
        return voteRecordId;
    }

    public void setVoteRecordId(Integer voteRecordId) {
        this.voteRecordId = voteRecordId;
    }

    public String getCandidateUsername() {
        return candidateUsername;
    }

    public void setCandidateUsername(String candidateUsername) {
        this.candidateUsername = candidateUsername;
    }

    public String getIuLastNamePref() {
        return iuLastNamePref;
    }

    public void setIuLastNamePref(String iuLastNamePref) {
        this.iuLastNamePref = iuLastNamePref;
    }

    public String getIuFirstNamePref() {
        return iuFirstNamePref;
    }

    public void setIuFirstNamePref(String iuFirstNamePref) {
        this.iuFirstNamePref = iuFirstNamePref;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(String currentRank) {
        this.currentRank = currentRank;
    }

    public BigDecimal getDossierId() {
        return dossierId;
    }

    public void setDossierId(BigDecimal dossierId) {
        this.dossierId = dossierId;
    }

    public String getDossierStatus() {
        return dossierStatus;
    }

    public void setDossierStatus(String dossierStatus) {
        this.dossierStatus = dossierStatus;
    }

    public String getRankSought() {
        return rankSought;
    }

    public void setRankSought(String rankSought) {
        this.rankSought = rankSought;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDossierTypeName() {
        return dossierTypeName;
    }

    public void setDossierTypeName(String dossierTypeName) {
        this.dossierTypeName = dossierTypeName;
    }

    public BigDecimal getDossierTypeId() {
        return dossierTypeId;
    }

    public void setDossierTypeId(BigDecimal dossierTypeId) {
        this.dossierTypeId = dossierTypeId;
    }

    public Integer getVoteRound() {
        return voteRound;
    }

    public void setVoteRound(Integer voteRound) {
        this.voteRound = voteRound;
    }

    public String getVoteRoundName() {
        if (ObjectUtils.isNotNull(getVoteRound())) {
            String voteRoundName = EdoConstants.VOTE_ROUNDS.get(getVoteRound());
            if (ObjectUtils.isNotNull(voteRoundName) && StringUtils.isNotEmpty(voteRoundName)) {
                return voteRoundName;
            } else {
                return "Invalid Vote Round";
            }
        } else {
            return "Unknown Vote Round";
        }
    }

    public BigDecimal getRouteLevel() {
        return routeLevel;
    }

    public void setRouteLevel(BigDecimal routeLevel) {
        this.routeLevel = routeLevel;
    }

    public String getReviewLayer() {
        return reviewLayer;
    }

    public void setReviewLayer(String reviewLayer) {
        this.reviewLayer = reviewLayer;
    }

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getAbsentCountPromotion() {
        return (ObjectUtils.isNotNull(absentCountPromotion) ? absentCountPromotion : new Integer(0));
    }

    public void setAbsentCountPromotion(Integer absentCountPromotion) {
        this.absentCountPromotion = (ObjectUtils.isNotNull(absentCountPromotion) ? absentCountPromotion : new Integer(0));
    }

    public Integer getAbsentCountTenure() {
        return (ObjectUtils.isNotNull(absentCountTenure) ? absentCountTenure : new Integer(0));
    }

    public void setAbsentCountTenure(Integer absentCountTenure) {
        this.absentCountTenure = (ObjectUtils.isNotNull(absentCountTenure) ? absentCountTenure : new Integer(0));
    }

    public Integer getAbstainCountPromotion() {
        return (ObjectUtils.isNotNull(abstainCountPromotion) ? abstainCountPromotion : new Integer(0));
    }

    public void setAbstainCountPromotion(Integer abstainCountPromotion) {
        this.abstainCountPromotion = (ObjectUtils.isNotNull(abstainCountPromotion) ? abstainCountPromotion : new Integer(0));
    }

    public Integer getAbstainCountTenure() {
        return (ObjectUtils.isNotNull(abstainCountTenure) ? abstainCountTenure : new Integer(0));
    }

    public void setAbstainCountTenure(Integer abstainCountTenure) {
        this.abstainCountTenure = (ObjectUtils.isNotNull(abstainCountTenure) ? abstainCountTenure : new Integer(0));
    }

    public Integer getNoCountPromotion() {
        return (ObjectUtils.isNotNull(noCountPromotion) ? noCountPromotion : new Integer(0));
    }

    public void setNoCountPromotion(Integer noCountPromotion) {
        this.noCountPromotion = (ObjectUtils.isNotNull(noCountPromotion) ? noCountPromotion : new Integer(0));
    }

    public Integer getNoCountTenure() {
        return (ObjectUtils.isNotNull(noCountTenure) ? noCountTenure : new Integer(0));
    }

    public void setNoCountTenure(Integer noCountTenure) {
        this.noCountTenure = (ObjectUtils.isNotNull(noCountTenure) ? noCountTenure : new Integer(0));
    }

    public Integer getYesCountPromotion() {
        return (ObjectUtils.isNotNull(yesCountPromotion) ? yesCountPromotion : new Integer(0));
    }

    public void setYesCountPromotion(Integer yesCountPromotion) {
        this.yesCountPromotion = (ObjectUtils.isNotNull(yesCountPromotion) ? yesCountPromotion : new Integer(0));
    }

    public Integer getYesCountTenure() {
        return (ObjectUtils.isNotNull(yesCountTenure) ? yesCountTenure : new Integer(0));
    }

    public void setYesCountTenure(Integer yesCountTenure) {
        this.yesCountTenure = (ObjectUtils.isNotNull(yesCountTenure) ? yesCountTenure : new Integer(0));
    }

    public String getAoeCode() {
        return aoeCode;
    }

    public void setAoeCode(String aoeCode) {
        this.aoeCode = aoeCode;
    }

    public String getVisaPermitType() {
        return visaPermitType;
    }

    public void setVisaPermitType(String visaPermitType) {
        this.visaPermitType = visaPermitType;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public boolean isEarly() {
        return early;
    }

    public void setEarly(boolean early) {
        this.early = early;
    }

    public Map<BigDecimal, EdoPromotionAndTenureReport> getVoteRecordMap() {
        return voteRecordMap;
    }

    public void setVoteRecordMap(Map<BigDecimal, EdoPromotionAndTenureReport> voteRecordMap) {
        this.voteRecordMap = voteRecordMap;
    }

    protected List<EdoReviewLayerDefinition> getReviewRouteLevels() {
        return reviewRouteLevels;
    }

    public void setReviewRouteLevels(List<EdoReviewLayerDefinition> reviewRouteLevels) {
        this.reviewRouteLevels = reviewRouteLevels;
    }

    public List<String> getVoteForReport() {
        if (dossierTypeName.equals(VoteType.VOTE_TYPE_TENURE)) {
            return getTenureVote();
        }
        if (dossierTypeName.equals(VoteType.VOTE_TYPE_PROMOTION)) {
            return getPromotionVote();
        }
        //else - dossierTypeName = VoteType.VOTE_TYPE_TENURE_PROMOTION  
        //TODO What to do about Tenure+Promotion dossiers? How to display both vote records?  
        return getTenureVote();
    }

    public String getVoteForReportDisplay() {
        List<String> voteForReport = getVoteForReport();
        if (voteForReport.isEmpty()) {
            return "";
        } else if (voteForReport.size() == 1) {
            return voteForReport.get(0);
        } else {
            String result = "";
            for (Iterator<String> iter = voteForReport.iterator(); iter.hasNext(); ) {
                result += iter.next();
                if (iter.hasNext()) {
                    result += "-";
                }
            }
            return result;
        }
    }

    protected List<String> getPromotionVote() {
        List<String> result = new ArrayList<String>();
        if (voteType.equals(EdoConstants.VOTE_TYPE_SINGLE)) {
            result.addAll(evaluateForSingleVoteTypePromotion());
            return result;
        } else if (voteType.equals(EdoConstants.VOTE_TYPE_MULTIPLE)) {
            result.addAll(evaluateForMultipleVoteTypePromotion());
            return result;
        }
        result.add("error");
        return result;
    }

    protected List<String> getTenureVote() {
        List<String> result = new ArrayList<String>();
        if (voteType.equals(EdoConstants.VOTE_TYPE_SINGLE)) {
            result.addAll(evaluateForSingleVoteTypeTenure());
            return result;
        } else if (voteType.equals(EdoConstants.VOTE_TYPE_MULTIPLE)) {
            result.addAll(evaluateForMultipleVoteTypeTenure());
            return result;
        }
        result.add("error");
        return result;
    }

    /**
     * Some entities, such as chair, dean, and president will have a single vote that can be summarized instead
     * of tallying each possible outcome.
     *
     * @return a List that summarizes this individual entity's single vote
     */
    protected List<String> evaluateForSingleVoteTypePromotion() {
        List<String> result = new ArrayList<String>();
        if (getAbsentCountPromotion() + getAbstainCountPromotion() + getNoCountPromotion() + getYesCountPromotion() > 1) {
            result.add("ERROR");
        } else if (getYesCountPromotion() == 1) {
            result.add(VOTE_RECORD_VALUES.get(0));
        } else if (getNoCountPromotion() == 1) {
            result.add(VOTE_RECORD_VALUES.get(1));
        } else if (getAbstainCountPromotion() == 1) {
            result.add(VOTE_RECORD_VALUES.get(2));
        } else if (getAbsentCountPromotion() == 1) {
            result.add(VOTE_RECORD_VALUES.get(3));
        } else
            result.add("--");
        return result;
    }

    /**
     * Some entities, such as chair, dean, and president will have a single vote that can be summarized instead
     * of tallying each possible outcome.
     *
     * @return a List that summarizes this individual entity's single vote
     */
    protected List<String> evaluateForSingleVoteTypeTenure() {
        List<String> result = new ArrayList<String>();
        if (getAbsentCountTenure() + getAbstainCountTenure() + getNoCountTenure() + getYesCountTenure() > 1) {
            result.add("ERROR");
        } else if (getYesCountTenure() == 1) {
            result.add(VOTE_RECORD_VALUES.get(0));
        } else if (getNoCountTenure() == 1) {
            result.add(VOTE_RECORD_VALUES.get(1));
        } else if (getAbstainCountTenure() == 1) {
            result.add(VOTE_RECORD_VALUES.get(2));
        } else if (getAbsentCountTenure() == 1) {
            result.add(VOTE_RECORD_VALUES.get(3));
        } else
            result.add("--");
        return result;
    }

    /**
     *
     * @return a string that summarizes all the votes for this review level
     */
    protected List<String> evaluateForMultipleVoteTypePromotion() {
        List<String> result = new ArrayList<String>();
        result.add("" + getYesCountPromotion());
        result.add("" + getNoCountPromotion());
        result.add("" + getAbstainCountPromotion());
        result.add("" + getAbsentCountPromotion());
        return result;
    }

    /**
    *
    * @return a string that summarizes all the votes for this review level
    */
    protected List<String> evaluateForMultipleVoteTypeTenure() {
        List<String> result = new ArrayList<String>();
        result.add("" + getYesCountTenure());
        result.add("" + getNoCountTenure());
        result.add("" + getAbstainCountTenure());
        result.add("" + getAbsentCountTenure());
        return result;
    }

    public String getPromotionAndTenureReportJSONString() {
        ArrayList<String> tmp = new ArrayList<String>();
        Type tmpType = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();
        tmp.add(getIuLastNamePref());
        tmp.add(getIuFirstNamePref());
        //Early   
        //TODO once the Early flag has been added to the Dossier table, and added to the PT Report View replace the empty string below with isEarly()  
        //and uncomment the last field descriptor in edo-ojb-repository.xml for EDO_PT_REPORT_V  
        tmp.add("");
        tmp.add(getOptionValue());
        tmp.add(getCurrentRank());
        tmp.add(getRankSought());
        tmp.add(getDepartmentId().toString());
        tmp.add(getSchoolId().toString());
        tmp.add(getGender());
        tmp.add(getEthnicity());
        tmp.add(getVisaPermitType());
        //Vote Record for each Route Level  
        for (EdoReviewLayerDefinition reviewLayer : getReviewRouteLevels()) {
            if (ObjectUtils.isNotNull(getVoteRecordMap())) {
                EdoPromotionAndTenureReport voteRecord = getVoteRecordMap().get(reviewLayer.getRouteLevel());
                if (ObjectUtils.isNotNull(voteRecord)) {
                    for (String vote : voteRecord.getVoteForReport()) {
                        tmp.add(vote);
                    }
                } else {
                    addEmptyVoteValues(tmp, reviewLayer);
                }
            } else {
                addEmptyVoteValues(tmp, reviewLayer);
            }
        }
        tmp.add(getDossierTypeName().toString());
        tmp.add(getVoteRoundName());
        return gson.toJson(tmp, tmpType).toString();
    }

    protected void addEmptyVoteValues(List<String> tmp, EdoReviewLayerDefinition reviewLayer) {
        if (reviewLayer.getVoteType().equals(EdoConstants.VOTE_TYPE_SINGLE)) {
            tmp.add("--");
        } else if (reviewLayer.getVoteType().equals(EdoConstants.VOTE_TYPE_MULTIPLE)) {
            tmp.add("--");
            tmp.add("--");
            tmp.add("--");
            tmp.add("--");
        }
    }

    @Override
    public void refresh() {
        getPersistenceService().retrieveNonKeyFields(this);
    }

    /**
	 * @return the persistenceService
	 */
    protected static PersistenceService getPersistenceService() {
        //		if ( persistenceService == null ) {  
        //			persistenceService = KNSServiceLocator.getPersistenceService();  
        //		}  
        return persistenceService;
    }
}
