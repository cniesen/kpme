package org.kuali.kpme.edo.role;

public enum EDORole {

    EDO_CANDIDATE ("EDO_CANDIDATE"),
    EDO_GUEST ("EDO_GUEST"),
    EDO_CANDIDATE_DELEGATE ("EDO_CANDIDATE_DELEGATE"),
    EDO_ADMINISTRATOR ("EDO_ADMINISTRATOR"),
    EDO_DOCUMENT_MANAGER ("EDO_DOCUMENT_MANAGER"),
    EDO_SUPERUSER ("EDO_SUPERUSER"),
    EDO_CHAIR_DELEGATE ("EDO_CHAIR_DELEGATE"),
    EDO_COMMITTEE_CHAIR ("EDO_COMMITTEE_CHAIR"),
    EDO_FYI_TENURE_REVIEWER_LEVEL1 ("EDO_FYI_REVIEWER_TENURE_LEVEL1"),
    EDO_FYI_TENURE_REVIEWER_LEVEL2 ("EDO_FYI_REVIEWER_TENURE_LEVEL2"),
    EDO_FYI_TENURE_REVIEWER_LEVEL3 ("EDO_FYI_REVIEWER_TENURE_LEVEL3"),
    EDO_FYI_TENURE_REVIEWER_LEVEL4 ("EDO_FYI_REVIEWER_TENURE_LEVEL4"),
    EDO_FYI_TENURE_REVIEWER_LEVEL5 ("EDO_FYI_REVIEWER_TENURE_LEVEL5"),
    EDO_TENURE_COMMITTEE_CHAIR_LEVEL1 ("EDO_COMMITTEE_CHAIR_TENURE_LEVEL1"),
    EDO_TENURE_COMMITTEE_CHAIR_LEVEL2 ("EDO_COMMITTEE_CHAIR_TENURE_LEVEL2"),
    EDO_TENURE_COMMITTEE_CHAIR_LEVEL3 ("EDO_COMMITTEE_CHAIR_TENURE_LEVEL3"),
    EDO_TENURE_COMMITTEE_CHAIR_LEVEL4 ("EDO_COMMITTEE_CHAIR_TENURE_LEVEL4"),
    EDO_TENURE_COMMITTEE_CHAIR_LEVEL5 ("EDO_COMMITTEE_CHAIR_TENURE_LEVEL5"),
    EDO_TENURE_COMMITTEE_CHAIR_LEVEL6 ("EDO_COMMITTEE_CHAIR_TENURE_LEVEL6"),
    EDO_FYI_PROMOTION_REVIEWER_LEVEL1 ("EDO_FYI_REVIEWER_PROMOTION_LEVEL1"),
    EDO_FYI_PROMOTION_REVIEWER_LEVEL2 ("EDO_FYI_REVIEWER_PROMOTION_LEVEL2"),
    EDO_FYI_PROMOTION_REVIEWER_LEVEL3 ("EDO_FYI_REVIEWER_PROMOTION_LEVEL3"),
    EDO_FYI_PROMOTION_REVIEWER_LEVEL4 ("EDO_FYI_REVIEWER_PROMOTION_LEVEL4"),
    EDO_FYI_PROMOTION_REVIEWER_LEVEL5 ("EDO_FYI_REVIEWER_PROMOTION_LEVEL5"),
    EDO_PROMOTION_COMMITTEE_CHAIR_LEVEL1 ("EDO_COMMITTEE_CHAIR_PROMOTION_LEVEL1"),
    EDO_PROMOTION_COMMITTEE_CHAIR_LEVEL2 ("EDO_COMMITTEE_CHAIR_PROMOTION_LEVEL2"),
    EDO_PROMOTION_COMMITTEE_CHAIR_LEVEL3 ("EDO_COMMITTEE_CHAIR_PROMOTION_LEVEL3"),
    EDO_PROMOTION_COMMITTEE_CHAIR_LEVEL4 ("EDO_COMMITTEE_CHAIR_PROMOTION_LEVEL4"),
    EDO_PROMOTION_COMMITTEE_CHAIR_LEVEL5 ("EDO_COMMITTEE_CHAIR_PROMOTION_LEVEL5"),
    EDO_PROMOTION_COMMITTEE_CHAIR_LEVEL6 ("EDO_COMMITTEE_CHAIR_PROMOTION_LEVEL6"),
    EDO_COMMITTEE_CHAIR_LEVEL7 ("EDO_COMMITTEE_CHAIR_LEVEL7"),
    EDO_COMMITTEE_CHAIR_LEVEL8 ("EDO_COMMITTEE_CHAIR_LEVEL8");

    private String edoRole;

    private EDORole(String edoRole) {
        this.edoRole = edoRole;
    }

    public String getEdoRole() {
        return edoRole;
    }

    public void setEdoRole(String edoRole) {
        this.edoRole = edoRole;
    }

}
