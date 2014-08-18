package org.kuali.kpme.edo.group;

import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.core.bo.HrBusinessObject;
import org.kuali.kpme.core.groupkey.HrGroupKeyBo;
import org.kuali.kpme.edo.api.checklist.EdoChecklist;
import org.kuali.kpme.edo.api.group.EdoGroupDefinition;
import org.kuali.kpme.edo.api.group.EdoGroupDefinitionContract;
import org.kuali.kpme.edo.checklist.EdoChecklistBo;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

/**
 * $HeadURL$
 * $Revision$
 * Created with IntelliJ IDEA.
 * User: bradleyt
 * Date: 12/18/13
 * Time: 10:27 AM
 */
@Entity
@Table(name = "EDO_GROUP_DEF_T")
public class EdoGroupDefinitionBo extends HrBusinessObject implements EdoGroupDefinitionContract {

    private static final long serialVersionUID = 7089330958890991138L;

    static class KeyFields {

        private static final String EDO_WORKFLOW_ID = "edoWorkflowId";

        private static final String WORKFLOW_LEVEL = "workflowLevel";

        private static final String DOSSIER_TYPE = "dossierType";
    }

    @PortableSequenceGenerator(name = "EDO_GROUP_DEF_S")
    @GeneratedValue(generator = "EDO_GROUP_DEF_S")
    @Id
    @Column(name = "EDO_GROUP_ID", length = 60)
    private String edoGroupId;

    @Column(name = "EDO_WORKFLOW_ID", nullable = false, length = 60)
    private String edoWorkflowId;

    @Column(name = "WORKFLOW_LEVEL", nullable = false, length = 25)
    private String workflowLevel;

    @Column(name = "DOSSIER_TYPE", nullable = false, length = 25)
    private String dossierType;

    @Column(name = "WORKFLOW_TYPE", nullable = false, length = 25)
    private String workflowType;

    @Column(name = "KIM_TYPE_NAME", nullable = false, length = 25)
    private String kimTypeName;

    @Column(name = "KIM_ROLE_NAME", nullable = false, length = 64)
    private String kimRoleName;

    @Override
    public String getId() {
        return getEdoGroupId();
    }

    @Override
    public void setId(String edoGroupId) {
        setEdoGroupId(edoGroupId);
    }

    @Override
    protected String getUniqueKey() {
        return getEdoGroupId();
    }

    @Override
    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.EDO_WORKFLOW_ID, this.getEdoWorkflowId()).put(KeyFields.WORKFLOW_LEVEL, this.getWorkflowLevel()).put(KeyFields.DOSSIER_TYPE, this.getDossierType()).build();
    }

    public String getEdoGroupId() {
        return edoGroupId;
    }

    public void setEdoGroupId(String edoGroupId) {
        this.edoGroupId = edoGroupId;
    }

    public String getEdoWorkflowId() {
        return edoWorkflowId;
    }

    public void setEdoWorkflowId(String edoWorkflowId) {
        this.edoWorkflowId = edoWorkflowId;
    }

    public String getWorkflowLevel() {
        return workflowLevel;
    }

    public void setWorkflowLevel(String workflowLevel) {
        this.workflowLevel = workflowLevel;
    }

    public String getDossierType() {
        return dossierType;
    }

    public void setDossierType(String dossierType) {
        this.dossierType = dossierType;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }

    public String getKimTypeName() {
        return kimTypeName;
    }

    public void setKimTypeName(String kimTypeName) {
        this.kimTypeName = kimTypeName;
    }

    public String getKimRoleName() {
        return kimRoleName;
    }

    public void setKimRoleName(String kimRoleName) {
        this.kimRoleName = kimRoleName;
    }

    public static EdoGroupDefinitionBo from(EdoGroupDefinition im) {
        if (im == null) {
            return null;
        }
        EdoGroupDefinitionBo eclt = new EdoGroupDefinitionBo();
        eclt.setEdoGroupId(im.getEdoGroupId());
        eclt.setEdoWorkflowId(im.getEdoWorkflowId());
        eclt.setWorkflowLevel(im.getWorkflowLevel());
        eclt.setDossierType(im.getDossierType());
        eclt.setWorkflowType(im.getWorkflowType());
        eclt.setKimTypeName(im.getKimTypeName());
        eclt.setKimRoleName(im.getKimRoleName());
        // finally copy over the common fields into phra from im 
        copyCommonFields(eclt, im);
        return eclt;
    }

    public static EdoGroupDefinition to(EdoGroupDefinitionBo bo) {
        if (bo == null) {
            return null;
        }
        return EdoGroupDefinition.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<EdoGroupDefinitionBo, EdoGroupDefinition> toImmutable = new ModelObjectUtils.Transformer<EdoGroupDefinitionBo, EdoGroupDefinition>() {

        public EdoGroupDefinition transform(EdoGroupDefinitionBo input) {
            return EdoGroupDefinitionBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<EdoGroupDefinition, EdoGroupDefinitionBo> toBo = new ModelObjectUtils.Transformer<EdoGroupDefinition, EdoGroupDefinitionBo>() {

        public EdoGroupDefinitionBo transform(EdoGroupDefinition input) {
            return EdoGroupDefinitionBo.from(input);
        }

        ;
    };
}
