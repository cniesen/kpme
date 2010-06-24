package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSearchCriteria;

public interface DocumentHeaderDataAccess extends DataAccessOjb {

    public DocumentHeader getDocumentHeader(String documentId);

    public DocumentHeader getDocumentHeader(String universityId, Date payEndDate);

    public DocumentHeader getDocumentHeaderLight(String universityId, Date payEndDate);

    public List getDocumentHeaders(Date payEndDate);

    public TypedPersistentMaintainedEntityList lookupDocumentHeaders(DocumentHeaderSearchCriteria searchCriteria);

    public void takeSnapshot(Date payEndDate) throws SQLException;

    public List getDocumentHeadersEnroute(Date payEndDate);

    public List getDocumentHeadersRoutedToCanceled(Date payEndDate);

    public List getDocumentHeadersAutoApproved(Date payEndDate);

    public void getExtractReportInfo(List documentHeaders, Collection col);

    //void removeAllDocumentHeaders() throws SQLException;
}
