package org.kuali.kpme.edo.reviewlayerdef;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.edo.api.reviewlayerdef.EdoSuppReviewLayerDefinition;
import org.kuali.kpme.edo.api.reviewlayerdef.EdoSuppReviewLayerDefinitionContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "EDO_SUPP_REVIEW_LAYER_DEF_T")
public class EdoSuppReviewLayerDefinitionBo extends PersistableBusinessObjectBase implements EdoSuppReviewLayerDefinitionContract {

    private static final long serialVersionUID = -9121537077110752118L;

    static class KeyFields {

        private static final String EDO_REVIEW_LAYER_DEFINITION_ID = "edoReviewLayerDefinitionId";

        private static final String SUPP_NODE_NAME = "suppNodeName";
    }

    @PortableSequenceGenerator(name = "EDO_SUPP_REVIEW_LAYER_DEF_S")
    @GeneratedValue(generator = "EDO_SUPP_REVIEW_LAYER_DEF_S")
    @Id
    @Column(name = "EDO_SUPP_REVIEW_LAYER_DEF_ID", nullable = false, length = 60)
    private String edoSuppReviewLayerDefinitionId;

    @Column(name = "EDO_REVIEW_LAYER_DEF_ID", nullable = false, length = 60)
    private String edoReviewLayerDefinitionId;

    @Column(name = "SUPPLEMENTAL_NODE_NAME", nullable = false, length = 100)
    private String suppNodeName;

    @Column(name = "ACKNOWLEDGE_FLAG", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean acknowledgeFlag;

    //private EdoReviewLayerDefinitionBo reviewLayerDefinition;  
    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.EDO_REVIEW_LAYER_DEFINITION_ID).add(KeyFields.SUPP_NODE_NAME).build();

    public String getEdoSuppReviewLayerDefinitionId() {
        return edoSuppReviewLayerDefinitionId;
    }

    public void setEdoSuppReviewLayerDefinitionId(String edoSuppReviewLayerDefinitionId) {
        this.edoSuppReviewLayerDefinitionId = edoSuppReviewLayerDefinitionId;
    }

    public String getEdoReviewLayerDefinitionId() {
        return edoReviewLayerDefinitionId;
    }

    public void setEdoReviewLayerDefinitionId(String edoReviewLayerDefinitionId) {
        this.edoReviewLayerDefinitionId = edoReviewLayerDefinitionId;
    }

    public String getSuppNodeName() {
        return suppNodeName;
    }

    public void setSuppNodeName(String suppNodeName) {
        this.suppNodeName = suppNodeName;
    }

    public boolean isAcknowledgeFlag() {
        return acknowledgeFlag;
    }

    public void setAcknowledgeFlag(boolean acknowledgeFlag) {
        this.acknowledgeFlag = acknowledgeFlag;
    }

    public String getId() {
        return getEdoSuppReviewLayerDefinitionId();
    }

    public void setId(String id) {
        setEdoSuppReviewLayerDefinitionId(id);
    }

    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.EDO_REVIEW_LAYER_DEFINITION_ID, this.getEdoReviewLayerDefinitionId()).put(KeyFields.SUPP_NODE_NAME, this.getSuppNodeName()).build();
    }

    /*As a general piece of information.  The BusinessObjectService should never be called from a Business Object.
    public EdoReviewLayerDefinitionBo getReviewLayerDefinition() {
        if (ObjectUtils.isNull(reviewLayerDefinition) && reviewLayerDefinitionId != null) {
            this.reviewLayerDefinition = EdoServiceLocator.getEdoReviewLayerDefinitionService().getReviewLayerDefinition(workflowId, reviewLayerDefinitionId);
        }
        return reviewLayerDefinition;
    }*/
    public static EdoSuppReviewLayerDefinitionBo from(EdoSuppReviewLayerDefinition edoSuppReviewLayerDefinition) {
        if (edoSuppReviewLayerDefinition == null) {
            return null;
        }
        EdoSuppReviewLayerDefinitionBo edoSuppReviewLayerDefinitionBo = new EdoSuppReviewLayerDefinitionBo();
        edoSuppReviewLayerDefinitionBo.setEdoSuppReviewLayerDefinitionId(edoSuppReviewLayerDefinition.getEdoSuppReviewLayerDefinitionId());
        edoSuppReviewLayerDefinitionBo.setEdoReviewLayerDefinitionId(edoSuppReviewLayerDefinition.getEdoReviewLayerDefinitionId());
        edoSuppReviewLayerDefinitionBo.setSuppNodeName(edoSuppReviewLayerDefinition.getSuppNodeName());
        edoSuppReviewLayerDefinitionBo.setAcknowledgeFlag(edoSuppReviewLayerDefinition.isAcknowledgeFlag());
        edoSuppReviewLayerDefinitionBo.setVersionNumber(edoSuppReviewLayerDefinition.getVersionNumber());
        edoSuppReviewLayerDefinitionBo.setObjectId(edoSuppReviewLayerDefinition.getObjectId());
        return edoSuppReviewLayerDefinitionBo;
    }

    public static EdoSuppReviewLayerDefinition to(EdoSuppReviewLayerDefinitionBo bo) {
        if (bo == null) {
            return null;
        }
        return EdoSuppReviewLayerDefinition.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<EdoSuppReviewLayerDefinitionBo, EdoSuppReviewLayerDefinition> toImmutable = new ModelObjectUtils.Transformer<EdoSuppReviewLayerDefinitionBo, EdoSuppReviewLayerDefinition>() {

        public EdoSuppReviewLayerDefinition transform(EdoSuppReviewLayerDefinitionBo input) {
            return EdoSuppReviewLayerDefinitionBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<EdoSuppReviewLayerDefinition, EdoSuppReviewLayerDefinitionBo> toBo = new ModelObjectUtils.Transformer<EdoSuppReviewLayerDefinition, EdoSuppReviewLayerDefinitionBo>() {

        public EdoSuppReviewLayerDefinitionBo transform(EdoSuppReviewLayerDefinition input) {
            return EdoSuppReviewLayerDefinitionBo.from(input);
        }

        ;
    };
}
