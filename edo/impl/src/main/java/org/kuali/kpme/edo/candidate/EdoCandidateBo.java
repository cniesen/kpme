package org.kuali.kpme.edo.candidate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.kuali.kpme.core.bo.HrKeyedBusinessObject;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.edo.api.candidate.EdoCandidate;
import org.kuali.kpme.edo.api.candidate.EdoCandidateContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

/**
 * Date: 06/07/14
 */
@Entity
@Table(name = "EDO_CANDIDATE_T")
public class EdoCandidateBo extends HrKeyedBusinessObject implements EdoCandidateContract {

    static class KeyFields {

        private static final String PRINCIPAL_NAME = "principalName";
    }

    private static final long serialVersionUID = 6843318899816055301L;

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.PRINCIPAL_NAME).build();

    //public static final String CACHE_NAME = HrConstants.CacheNamespace.NAMESPACE_PREFIX + "PrincipalHRAttributes"; 
    public static final ModelObjectUtils.Transformer<EdoCandidateBo, EdoCandidate> toImmutable = new ModelObjectUtils.Transformer<EdoCandidateBo, EdoCandidate>() {

        public EdoCandidate transform(EdoCandidateBo input) {
            return EdoCandidateBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<EdoCandidate, EdoCandidateBo> toBo = new ModelObjectUtils.Transformer<EdoCandidate, EdoCandidateBo>() {

        public EdoCandidateBo transform(EdoCandidate input) {
            return EdoCandidateBo.from(input);
        }

        ;
    };

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.PRINCIPAL_NAME, this.getPrincipalName()).build();
    }

    @Override
    public String getUniqueKey() {
        return getPrincipalName();
    }

    @PortableSequenceGenerator(name = "EDO_CANDIDATE_S")
    @GeneratedValue(generator = "EDO_CANDIDATE_S")
    @Id
    @Column(name = "EDO_CANDIDATE_ID", nullable = false, length = 60)
    private String edoCandidateId;

    @Column(name = "LAST_NAME", nullable = false, length = 45)
    private String lastName;

    @Column(name = "FIRST_NAME", nullable = false, length = 45)
    private String firstName;

    @Column(name = "PRNCPL_NM", nullable = false, length = 100)
    private String principalName;

    @Column(name = "PRIMARY_DEPARTMENT_ID", nullable = false, length = 12)
    private String primaryDeptId;

    @Column(name = "TNP_DEPARTMENT_ID", nullable = false, length = 12)
    private String tnpDeptId;

    @Column(name = "CANDIDACY_SCHOOL", nullable = false, length = 12)
    private String candidacySchool;

    @Transient
    private transient Person principal;

    @Override
    public String getId() {
        return this.getEdoCandidateId();
    }

    @Override
    public void setId(String id) {
        setEdoCandidateId(id);
    }

    public Person getPrincipal() {
        return principal;
    }

    public void setPrincipal(Person principal) {
        this.principal = principal;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getEdoCandidateId() {
        return edoCandidateId;
    }

    public void setEdoCandidateId(String edoCandidateId) {
        this.edoCandidateId = edoCandidateId;
    }

    public String getPrimaryDeptId() {
        return primaryDeptId;
    }

    public void setPrimaryDeptId(String primaryDeptId) {
        this.primaryDeptId = primaryDeptId;
    }

    public String getTnpDeptId() {
        return tnpDeptId;
    }

    public void setTnpDeptId(String tnpDeptId) {
        this.tnpDeptId = tnpDeptId;
    }

    public String getCandidacySchool() {
        return candidacySchool;
    }

    public void setCandidacySchool(String candidacySchool) {
        this.candidacySchool = candidacySchool;
    }

    public String getCandidateJSONString() {
        ArrayList<String> tmp = new ArrayList<String>();
        Type tmpType = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();
        tmp.add(this.getEdoCandidateId().toString());
        tmp.add(this.getFirstName() + " " + this.getLastName());
        tmp.add(this.getLastName());
        tmp.add(this.getFirstName());
        tmp.add(this.getPrincipalName());
        tmp.add(this.getPrimaryDeptId());
        tmp.add(this.getCandidacySchool());
        return gson.toJson(tmp, tmpType).toString();
    }

    public static EdoCandidateBo from(EdoCandidate edoCandidate) {
        if (edoCandidate == null) {
            return null;
        }
        EdoCandidateBo edoCandidateBo = new EdoCandidateBo();
        edoCandidateBo.setEdoCandidateId(edoCandidate.getEdoCandidateId());
        edoCandidateBo.setPrincipalName(edoCandidate.getPrincipalName());
        edoCandidateBo.setLastName(edoCandidate.getLastName());
        edoCandidateBo.setFirstName(edoCandidate.getFirstName());
        edoCandidateBo.setPrimaryDeptId(edoCandidate.getPrimaryDeptId());
        edoCandidateBo.setTnpDeptId(edoCandidate.getTnpDeptId());
        edoCandidateBo.setCandidacySchool(edoCandidate.getCandidacySchool());
        edoCandidateBo.setGroupKeyCode(edoCandidate.getGroupKeyCode());
        edoCandidateBo.setGroupKey(HrGroupKeyBo.from(edoCandidate.getGroupKey()));
        // finally copy over the common fields into edoCandidateBo from im 
        copyCommonFields(edoCandidateBo, edoCandidate);
        return edoCandidateBo;
    }

    public static EdoCandidate to(EdoCandidateBo bo) {
        if (bo == null) {
            return null;
        }
        return EdoCandidate.Builder.create(bo).build();
    }
}
