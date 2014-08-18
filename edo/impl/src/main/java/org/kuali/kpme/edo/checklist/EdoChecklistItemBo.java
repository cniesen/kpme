package org.kuali.kpme.edo.checklist;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.kuali.kpme.edo.api.checklist.EdoChecklistItem;
import org.kuali.kpme.edo.api.checklist.EdoChecklistItemContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

/**
 * $HeadURL$
 * $Revision$
 * Created with IntelliJ IDEA.
 * User: bradleyt
 * Date: 5/22/14
 * Time: 9:54 AM
 */
@Entity
@Table(name = "EDO_CHECKLIST_ITEM_T")
public class EdoChecklistItemBo extends PersistableBusinessObjectBase implements EdoChecklistItemContract {

    private static final long serialVersionUID = -3737008004782110416L;

    static class KeyFields {

        private static final String EDO_CHECKLIST_SECTION_ID = "edoChecklistSectionId";

        private static final String EDO_CHECKLIST_ITEM_NAME = "checklistItemName";
    }

    @PortableSequenceGenerator(name = "EDO_CHECKLIST_ITEM_S")
    @GeneratedValue(generator = "EDO_CHECKLIST_ITEM_S")
    @Id
    @Column(name = "EDO_CHECKLIST_ITEM_ID", nullable = false)
    private String edoChecklistItemId;

    @Column(name = "EDO_CHECKLIST_SECTION_ID", nullable = false)
    private String edoChecklistSectionId;

    @Column(name = "CHECKLIST_ITEM_NAME", nullable = false, length = 200)
    private String checklistItemName;

    @Column(name = "DESCRIPTION")
    private String itemDescription;

    @Column(name = "REQUIRED", nullable = false)
    @Convert(converter = BooleanYNConverter.class)
    private boolean required;

    @Column(name = "CHECKLIST_ITEM_ORDINAL", nullable = false)
    private int checklistItemOrdinal;

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.EDO_CHECKLIST_SECTION_ID).add(KeyFields.EDO_CHECKLIST_ITEM_NAME).build();

    public String getId() {
        return getEdoChecklistItemId();
    }

    public void setId(String edoChecklistItemId) {
        setEdoChecklistItemId(edoChecklistItemId);
    }

    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.EDO_CHECKLIST_SECTION_ID, this.getEdoChecklistSectionId()).put(KeyFields.EDO_CHECKLIST_ITEM_NAME, this.getChecklistItemName()).build();
    }

    public String getEdoChecklistItemId() {
        return edoChecklistItemId;
    }

    public void setEdoChecklistItemId(String edoChecklistItemId) {
        this.edoChecklistItemId = edoChecklistItemId;
    }

    public String getEdoChecklistSectionId() {
        return edoChecklistSectionId;
    }

    public void setEdoChecklistSectionId(String edoChecklistSectionId) {
        this.edoChecklistSectionId = edoChecklistSectionId;
    }

    public String getChecklistItemName() {
        return checklistItemName;
    }

    public void setChecklistItemName(String checklistItemName) {
        this.checklistItemName = checklistItemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getChecklistItemOrdinal() {
        return checklistItemOrdinal;
    }

    public void setChecklistItemOrdinal(int checklistItemOrdinal) {
        this.checklistItemOrdinal = checklistItemOrdinal;
    }

    public static EdoChecklistItemBo from(EdoChecklistItem im) {
        if (im == null) {
            return null;
        }
        EdoChecklistItemBo ecli = new EdoChecklistItemBo();
        ecli.setEdoChecklistItemId(im.getEdoChecklistItemId());
        ecli.setEdoChecklistSectionId(im.getEdoChecklistSectionId());
        ecli.setChecklistItemName(im.getChecklistItemName());
        ecli.setItemDescription(im.getItemDescription());
        ecli.setChecklistItemOrdinal(im.getChecklistItemOrdinal());
        ecli.setVersionNumber(im.getVersionNumber());
        ecli.setObjectId(im.getObjectId());
        return ecli;
    }

    public static EdoChecklistItem to(EdoChecklistItemBo bo) {
        if (bo == null) {
            return null;
        }
        return EdoChecklistItem.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<EdoChecklistItemBo, EdoChecklistItem> toImmutable = new ModelObjectUtils.Transformer<EdoChecklistItemBo, EdoChecklistItem>() {

        public EdoChecklistItem transform(EdoChecklistItemBo input) {
            return EdoChecklistItemBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<EdoChecklistItem, EdoChecklistItemBo> toBo = new ModelObjectUtils.Transformer<EdoChecklistItem, EdoChecklistItemBo>() {

        public EdoChecklistItemBo transform(EdoChecklistItem input) {
            return EdoChecklistItemBo.from(input);
        }

        ;
    };
}
