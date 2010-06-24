package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.SaveEvent;
import edu.iu.uis.hr.Utility;
import edu.iu.uis.hr.entity.PersistentMaintainedEntity;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.logic.HasValidAssignmentLogic;

public class DocumentHeader extends AbstractDocumentHeader implements PersistentMaintainedEntity {
	private static final Logger LOG = Logger.getLogger(DocumentHeader.class);
    private static final String DOCUMENT_LOCK_FIELD_NAME = "documentLock";
    private static final String DOCUMENT_STATUS_FIELD_NAME = "documentStatus";
    private static final List LOGIC_EXEMPT_PROPERTY_NAMES = edu.iu.uis.hr.Utility.getDefaultListValue();
    static {
        LOGIC_EXEMPT_PROPERTY_NAMES.add(DOCUMENT_LOCK_FIELD_NAME);
        LOGIC_EXEMPT_PROPERTY_NAMES.add(DOCUMENT_STATUS_FIELD_NAME);
    }
    
	public DocumentHeader() {
		super();
        setDocumentId(Utility.getDefaultStringValue());
        setTimeBlocks(new TypedPersistentMaintainedEntityList(TimeBlock.class));          
	}
    
    public DocumentHeader(String documentId) {
        this();
        setDocumentId(documentId);    
    }

    public DocumentHeader(String documentId, String universityId, Date payEndDate) {
        this(documentId);
        setUniversityId(universityId);
        setPayEndDate(payEndDate);        
    }
    
    public Class getModeAuthorization() {
        return DocumentHeaderModeAuthorization.class;
    }
    
    public List getLogicExemptPropertyNames() {
        return LOGIC_EXEMPT_PROPERTY_NAMES;
    }

    protected List getOperationalLogics(SaveEvent event) {
        List operationalLogics = new ArrayList();
        operationalLogics.add(HasValidAssignmentLogic.class);
        return operationalLogics;
    }
    
    public Collection getTimeBlocks() {
        if (!(super.getTimeBlocks() instanceof TypedPersistentMaintainedEntityList)) {
            setTimeBlocks(new TypedPersistentMaintainedEntityList(TimeBlock.class, super.getTimeBlocks()));
        }
        return super.getTimeBlocks();
    }

    public void setTimeBlocks(Collection timeBlocks) {
        if (timeBlocks instanceof TypedPersistentMaintainedEntityList) {
            super.setTimeBlocks(timeBlocks);
        }
        super.setTimeBlocks(new TypedPersistentMaintainedEntityList(TimeBlock.class, timeBlocks));
    }

    public TimeBlock getTimeBlock(int index) {
        return (TimeBlock)((TypedPersistentMaintainedEntityList)getTimeBlocks()).get(index);
    }

    public void setTimeBlock(int index, TimeBlock timeBlock) {
        ((TypedPersistentMaintainedEntityList)getTimeBlocks()).add(index, timeBlock);
    }

    public boolean equals(Object o) {
    	DocumentHeader compObj = (DocumentHeader)o;
    	return getDocumentId().equals(compObj.getDocumentId());   
    }
    
    public int hashCode() {
    	return getDocumentId().hashCode();
    }

}

