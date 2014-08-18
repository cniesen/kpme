package org.kuali.kpme.edo.workflow;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.sql.Timestamp;
import javax.persistence.*;

import org.joda.time.DateTime;
import org.kuali.kpme.edo.api.workflow.EdoWorkflowDefinition;
import org.kuali.kpme.edo.api.workflow.EdoWorkflowDefinitionContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * $HeadURL$
 * $Revision$
 * Created with IntelliJ IDEA.
 * User: bradleyt
 * Date: 4/23/14
 * Time: 11:24 AM
 */
@Entity
@Table(name = "EDO_WORKFLOW_DEF_T")
public class EdoWorkflowDefinitionBo extends PersistableBusinessObjectBase implements EdoWorkflowDefinitionContract {

    static class KeyFields {

        private static final String WORKFLOW_NAME = "workflowName";
    }

    @Id
    @Column(name = "EDO_WORKFLOW_ID", length = 60)
    private String edoWorkflowId;

    @Column(name = "WORKFLOW_NAME", length = 25)
    private String workflowName;

    @Column(name = "WORKFLOW_DESCRIPTION", length = 512)
    private String workflowDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIMESTAMP", nullable = false)
    private Timestamp actionTimestamp;

    @Column(name = "USER_PRINCIPAL_ID", nullable = false, length = 40)
    private String userPrincipalId;

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.WORKFLOW_NAME).build();

    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.WORKFLOW_NAME, this.getWorkflowName()).build();
    }

    public String getId() {
        return getEdoWorkflowId();
    }

    public void setId(String id) {
        setEdoWorkflowId(id);
    }

    public String getEdoWorkflowId() {
        return edoWorkflowId;
    }

    public void setEdoWorkflowId(String edoWorkflowId) {
        this.edoWorkflowId = edoWorkflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getWorkflowDescription() {
        return workflowDescription;
    }

    public void setWorkflowDescription(String workflowDescription) {
        this.workflowDescription = workflowDescription;
    }

    public String getUserPrincipalId() {
        return userPrincipalId;
    }

    public void setUserPrincipalId(String userPrincipalId) {
        this.userPrincipalId = userPrincipalId;
    }

    public Timestamp getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(Timestamp actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    public DateTime getActionFullDateTime() {
        return actionTimestamp != null ? new DateTime(actionTimestamp) : null;
    }

    public static EdoWorkflowDefinitionBo from(EdoWorkflowDefinition im) {
        if (im == null) {
            return null;
        }
        EdoWorkflowDefinitionBo ewfd = new EdoWorkflowDefinitionBo();
        ewfd.setEdoWorkflowId(im.getEdoWorkflowId());
        ewfd.setWorkflowName(im.getWorkflowName());
        ewfd.setWorkflowDescription(im.getWorkflowDescription());
        ewfd.setUserPrincipalId(im.getUserPrincipalId());
        ewfd.setActionTimestamp(im.getActionFullDateTime() == null ? null : new Timestamp(im.getActionFullDateTime().getMillis()));
        ewfd.setVersionNumber(im.getVersionNumber());
        ewfd.setObjectId(im.getObjectId());
        return ewfd;
    }

    public static EdoWorkflowDefinition to(EdoWorkflowDefinitionBo bo) {
        if (bo == null) {
            return null;
        }
        return EdoWorkflowDefinition.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<EdoWorkflowDefinitionBo, EdoWorkflowDefinition> toImmutable = new ModelObjectUtils.Transformer<EdoWorkflowDefinitionBo, EdoWorkflowDefinition>() {

        public EdoWorkflowDefinition transform(EdoWorkflowDefinitionBo input) {
            return EdoWorkflowDefinitionBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<EdoWorkflowDefinition, EdoWorkflowDefinitionBo> toBo = new ModelObjectUtils.Transformer<EdoWorkflowDefinition, EdoWorkflowDefinitionBo>() {

        public EdoWorkflowDefinitionBo transform(EdoWorkflowDefinition input) {
            return EdoWorkflowDefinitionBo.from(input);
        }

        ;
    };
}
