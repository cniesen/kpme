package org.kuali.kpme.edo.item;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.sql.Timestamp;
import javax.persistence.*;

import org.joda.time.DateTime;
import org.kuali.kpme.edo.api.item.EdoItem;
import org.kuali.kpme.edo.api.item.EdoItemContract;
import org.kuali.rice.core.api.mo.ModelObjectUtils;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

/**
 * $HeadURL$
 * $Revision$
 * Created with IntelliJ IdEA.
 * User: bradleyt
 * Date: 11/16/12
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "EDO_ITEM_T")
public class EdoItemBo extends PersistableBusinessObjectBase implements EdoItemContract {

    private static final long serialVersionUID = -6465675990155553098L;

    static class KeyFields {

        private static final String EDO_ITEM_TYPE_ID = "edoItemTypeId";

        private static final String EDO_DOSSIER_ID = "edoDossierId";

        private static final String EDO_CHECKLIST_ITEM_ID = "edoChecklistItemId";

        private static final String FILE_NAME = "fileName";
    }

    @PortableSequenceGenerator(name = "EDO_ITEM_S")
    @GeneratedValue(generator = "EDO_ITEM_S")
    @Id
    @Column(name = "EDO_ITEM_ID", nullable = false)
    private String edoItemId;

    @Column(name = "EDO_ITEM_TYPE_ID", nullable = false)
    private String edoItemTypeId;

    @Column(name = "EDO_CHECKLIST_ITEM_ID", nullable = false)
    private String edoChecklistItemId;

    @Column(name = "EDO_DOSSIER_ID", nullable = false)
    private String edoDossierId;

    @Column(name = "FILE_NAME", nullable = false, length = 1024)
    private String fileName;

    @Column(name = "FILE_LOCATION", nullable = false, length = 1024)
    private String fileLocation;

    @Column(name = "NOTES", length = 1000)
    private String notes;

    @Column(name = "ROUTED", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean routed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIMESTAMP", nullable = false)
    private Timestamp actionTimestamp;

    @Column(name = "USER_PRINCIPAL_ID", nullable = false, length = 40)
    private String userPrincipalId;

    @Column(name = "CONTENT_TYPE", length = 128)
    private String contentType;

    @Column(name = "ROW_INDEX", nullable = false)
    private int rowIndex;

    @Column(name = "EDO_REVIEW_LAYER_DEF_ID")
    private String edoReviewLayerDefId;

    @Column(name = "FILE_DESCRIPTION", nullable = false, length = 1000)
    private String fileDescription;

    @Column(name = "ACTION", nullable = false, length = 20)
    private String action;

    @Column(name = "ACTIVE", nullable = false, length = 1)
    @Convert(converter = BooleanYNConverter.class)
    private boolean active;

    public static final ImmutableList<String> BUSINESS_KEYS = new ImmutableList.Builder<String>().add(KeyFields.EDO_ITEM_TYPE_ID).add(KeyFields.EDO_DOSSIER_ID).add(KeyFields.EDO_CHECKLIST_ITEM_ID).add(KeyFields.FILE_NAME).build();

    public EdoItemBo() {
    }

    public String getId() {
        return getEdoItemId();
    }

    public void setId(String id) {
        setEdoItemId(id);
    }

    public ImmutableMap<String, Object> getBusinessKeyValuesMap() {
        return new ImmutableMap.Builder<String, Object>().put(KeyFields.EDO_ITEM_TYPE_ID, this.getEdoItemTypeId()).put(KeyFields.EDO_DOSSIER_ID, this.getEdoDossierId()).put(KeyFields.EDO_CHECKLIST_ITEM_ID, this.getEdoChecklistItemId()).put(KeyFields.FILE_NAME, this.getFileName()).build();
    }

    public String getEdoItemId() {
        return edoItemId;
    }

    public void setEdoItemId(String edoItemId) {
        this.edoItemId = edoItemId;
    }

    public String getEdoItemTypeId() {
        return edoItemTypeId;
    }

    public void setEdoItemTypeId(String edoItemTypeId) {
        this.edoItemTypeId = edoItemTypeId;
    }

    public String getEdoChecklistItemId() {
        return edoChecklistItemId;
    }

    public void setEdoChecklistItemId(String edoChecklistItemId) {
        this.edoChecklistItemId = edoChecklistItemId;
    }

    public String getEdoDossierId() {
        return edoDossierId;
    }

    public void setEdoDossierId(String edoDossierId) {
        this.edoDossierId = edoDossierId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isRouted() {
        return routed;
    }

    public void setRouted(boolean routed) {
        this.routed = routed;
    }

    public String getUserPrincipalId() {
        return userPrincipalId;
    }

    public void setUserPrincipalId(String userPrincipalId) {
        this.userPrincipalId = userPrincipalId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getEdoReviewLayerDefId() {
        return edoReviewLayerDefId;
    }

    public void setEdoReviewLayerDefId(String edoReviewLayerDefId) {
        this.edoReviewLayerDefId = edoReviewLayerDefId;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public int compareTo(EdoItemContract eic) {
        return (this.getRowIndex() - eic.getRowIndex());
    }

    public static EdoItemBo from(EdoItem im) {
        if (im == null) {
            return null;
        }
        EdoItemBo it = new EdoItemBo();
        it.setEdoItemId(im.getEdoItemId());
        it.setEdoItemTypeId(im.getEdoItemTypeId());
        it.setEdoChecklistItemId(im.getEdoChecklistItemId());
        it.setEdoDossierId(im.getEdoDossierId());
        it.setFileName(im.getFileName());
        it.setFileLocation(im.getFileLocation());
        it.setNotes(im.getNotes());
        it.setRouted(im.isRouted());
        it.setContentType(im.getContentType());
        it.setRowIndex(im.getRowIndex());
        it.setEdoReviewLayerDefId(im.getEdoReviewLayerDefId());
        it.setFileDescription(im.getFileDescription());
        it.setActive(im.isActive());
        it.setUserPrincipalId(im.getUserPrincipalId());
        it.setAction(im.getAction());
        it.setActionTimestamp(im.getActionFullDateTime() == null ? null : new Timestamp(im.getActionFullDateTime().getMillis()));
        it.setVersionNumber(im.getVersionNumber());
        it.setObjectId(im.getObjectId());
        return it;
    }

    public static EdoItem to(EdoItemBo bo) {
        if (bo == null) {
            return null;
        }
        return EdoItem.Builder.create(bo).build();
    }

    public static final ModelObjectUtils.Transformer<EdoItemBo, EdoItem> toImmutable = new ModelObjectUtils.Transformer<EdoItemBo, EdoItem>() {

        public EdoItem transform(EdoItemBo input) {
            return EdoItemBo.to(input);
        }

        ;
    };

    public static final ModelObjectUtils.Transformer<EdoItem, EdoItemBo> toBo = new ModelObjectUtils.Transformer<EdoItem, EdoItemBo>() {

        public EdoItemBo transform(EdoItem input) {
            return EdoItemBo.from(input);
        }

        ;
    };
}
