package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.entity.PayrollExtractReportSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshotSearchCriteria;

public interface DocumentHeaderSnapshotDataAccess extends DataAccessOjb {
    
    public DocumentHeaderSnapshot getDocumentHeaderSnapshot(String documentId);
    
    public DocumentHeaderSnapshot getDocumentHeaderSnapshot(String universityId, Date payEndDate);

    public List getDocumentHeaderSnapshots(Date payEndDate);
    
    public TypedPersistentMaintainedEntityList lookupDocumentHeaderSnapshots(DocumentHeaderSnapshotSearchCriteria searchCriteria);
    
    public TypedPersistentMaintainedEntityList lookupPayrollExtractReports(PayrollExtractReportSearchCriteria searchCriteria);

}
