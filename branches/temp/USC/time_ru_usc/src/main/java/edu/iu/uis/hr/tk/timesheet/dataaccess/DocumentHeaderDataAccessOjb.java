package edu.iu.uis.hr.tk.timesheet.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;

import edu.iu.uis.hr.dataaccess.AbstractDataAccessOjb;
import edu.iu.uis.hr.entity.DocumentConstants;
import edu.iu.uis.hr.entity.FieldNames;
import edu.iu.uis.hr.entity.TypedPersistentMaintainedEntityList;
import edu.iu.uis.hr.entity.logic.Utility;
import edu.iu.uis.hr.tk.TKServiceLocator;
import edu.iu.uis.hr.tk.entity.report.ExtractDocument;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderLight;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSearchCriteria;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeaderSnapshot;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class DocumentHeaderDataAccessOjb extends AbstractDataAccessOjb implements DocumentHeaderDataAccess {
    private static final Logger LOG = Logger.getLogger(DocumentHeaderDataAccessOjb.class);

    public DocumentHeader getDocumentHeader(String documentId) {
        DocumentHeader documentHeader = (DocumentHeader)getCurrentActiveRecord(new DocumentHeader(documentId));
        return documentHeader;
    }

    public DocumentHeader getDocumentHeader(String universityId, Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        List documentHeaders = new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeader.class, criteria)));
        if (Utility.hasValue(documentHeaders)) {
            return (DocumentHeader)documentHeaders.get(0);
        }
        return null;
    }

    //This method avoids the auto-retrieval of all timeblocks associated to pay period
    public DocumentHeader getDocumentHeaderLight(String universityId, Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.UNIVERSITY_ID, universityId);
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        List documentHeaders = new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeaderLight.class, criteria)));
        if (Utility.hasValue(documentHeaders)) {
            return (DocumentHeader)documentHeaders.get(0);
        }
        return null;
    }

    public List getDocumentHeaders(Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        return new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeader.class, criteria)));
    }

    public TypedPersistentMaintainedEntityList lookupDocumentHeaders(DocumentHeaderSearchCriteria searchCriteria) {
        Criteria lookupCriteria = new Criteria();
        getStandardLookupLogic(lookupCriteria, FieldNames.UNIVERSITY_ID, searchCriteria.getUniversityId());
        if (Utility.hasValue(searchCriteria.getDocumentId())) {
            lookupCriteria.addEqualTo(FieldNames.DOCUMENT_ID, searchCriteria.getDocumentId());
        }
        if (Utility.hasValue(searchCriteria.getPayEndDate())) {
            lookupCriteria.addEqualTo(FieldNames.PAY_END_DATE, searchCriteria.getPayEndDate());
        }
        return getAllMaintainableValuesListByQuery(QueryFactory.newQuery(DocumentHeader.class, lookupCriteria));
    }

    public List getDocumentHeadersEnroute(Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        criteria.addEqualTo(FieldNames.DOCUMENT_STATUS, DocumentConstants.ROUTED);
        return new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeader.class, criteria)));
    }

    public List getDocumentHeadersRoutedToCanceled(Date payEndDate) {
        //we want a list of docs that were canceled by the approve process.
        //said another way, we want docs with a status of canceled right now, and a status of not-canceled when we took a snapshot.
        List returnList = new ArrayList();
        List allCanceledDocs = getDocumentHeadersCanceled(payEndDate);
        Iterator documentHeaderIterator = allCanceledDocs.iterator();
        while (documentHeaderIterator.hasNext()) {
            try {
                DocumentHeader documentHeader = (DocumentHeader)documentHeaderIterator.next();
                DocumentHeaderSnapshot documentHeaderSnapshot = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getDocumentHeaderSnapshot(documentHeader.getDocumentId());
                if (!documentHeaderSnapshot.getDocumentStatus().equalsIgnoreCase(documentHeader.getDocumentStatus())) {
                    returnList.add(documentHeader);
                }
            } catch (Exception e) {
                //ignore errors
            }
        }
        return returnList;
    }

    public List getDocumentHeadersAutoApproved(Date payEndDate) {
        //we want a list of docs that were approved by the approve process.
        //said another way, we want docs with a status of approved right now, and a status of not-approved when we took a snapshot.
        List returnList = new ArrayList();
        List allApprovedDocs = getDocumentHeadersApproved(payEndDate);
        Iterator documentHeaderIterator = allApprovedDocs.iterator();
        while (documentHeaderIterator.hasNext()) {
            try {
                DocumentHeader documentHeader = (DocumentHeader)documentHeaderIterator.next();
                DocumentHeaderSnapshot documentHeaderSnapshot = ((TimesheetService)edu.iu.uis.hr.Context.getService(TimesheetService.class)).getDocumentHeaderSnapshot(documentHeader.getDocumentId());
                if (!documentHeaderSnapshot.getDocumentStatus().equalsIgnoreCase(documentHeader.getDocumentStatus())) {
                    returnList.add(documentHeader);
                }
            } catch (Exception e) {
                // ignore errors
            }
        }
        return returnList;
    }

    private List getDocumentHeadersCanceled(Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        criteria.addEqualTo(FieldNames.DOCUMENT_STATUS, DocumentConstants.CANCELLED);
        return new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeader.class, criteria)));
    }

    // could just make one method that takes a status as well as a date...
    private List getDocumentHeadersApproved(Date payEndDate) {
        Criteria criteria = new Criteria();
        criteria.addEqualTo(FieldNames.PAY_END_DATE, payEndDate);
        criteria.addEqualTo(FieldNames.DOCUMENT_STATUS, DocumentConstants.FINAL);
        return new ArrayList(getCollectionByQuery(QueryFactory.newQuery(DocumentHeader.class, criteria)));
    }

    public void getExtractReportInfo(List documentHeaders, Collection col) {
    	//TODO: is there a max length for a sql statement? if we have lots of documentHeaders, we may find out.
		StringBuffer sqlString = new StringBuffer("SELECT B.DOCUMENT_ID, B.EMPLID, A.EMPL_RCD, N.NAME, J1.DEPTID, J1.EMPL_STATUS, (SELECT DECODE(COUNT(*),0,'N','Y')  FROM TK.TK_TIME_BLOCK_T TB WHERE TB.DOCUMENT_ID=B.DOCUMENT_ID) AS HASHOURS ");
		sqlString.append("FROM HRE.HRE_TK_ASSIGNMENT_T A, ");
		sqlString.append("TK.TK_DOCUMENT_HEADER_T B, ");
		sqlString.append("SYSADM.PS_NAMES N, ");
		sqlString.append("SYSADM.PS_JOB J1 ");
		sqlString.append("WHERE B.EMPLID = N.EMPLID ");
		sqlString.append("AND N.NAME_TYPE = 'PRI' ");
		sqlString.append("AND N.EFFDT = ( SELECT MAX(EFFDT) FROM SYSADM.PS_NAMES WHERE EMPLID = N.EMPLID AND NAME_TYPE=N.NAME_TYPE AND EFFDT <= SYSDATE) ");
		sqlString.append("AND ( J1.EMPL_STATUS <> 'T' OR J1.EFFDT > SYSDATE - 30)");
		sqlString.append("AND B.EMPLID = A.EMPLID ");
		sqlString.append("AND A.EFFDT = (SELECT MAX(EFFDT) FROM HRE.HRE_TK_ASSIGNMENT_T A1 ");
		sqlString.append("WHERE A.EMPLID = A1.EMPLID ");
		sqlString.append("AND A.EMPL_RCD = A1.EMPL_RCD) ");
		sqlString.append("AND A.EFFSEQ = (SELECT MAX(EFFSEQ) FROM HRE.HRE_TK_ASSIGNMENT_T A2 ");
		sqlString.append("WHERE A.EMPLID = A2.EMPLID ");
		sqlString.append("AND A.EMPL_RCD = A2.EMPL_RCD ");
		sqlString.append("AND A.EFFDT = A2.EFFDT) ");
		sqlString.append("AND B.EMPLID = J1.EMPLID ");
		sqlString.append("AND A.EMPL_RCD = J1.EMPL_RCD ");
		sqlString.append("AND J1.EFFDT = (SELECT MAX(EFFDT) FROM SYSADM.PS_JOB J2 WHERE J1.EMPLID = J2.EMPLID AND J1.EMPL_RCD = J2.EMPL_RCD AND J2.EFFDT <= SYSDATE) ");
		sqlString.append("AND J1.EFFSEQ = (SELECT MAX(EFFSEQ) FROM SYSADM.PS_JOB J3 WHERE J1.EMPLID = J3.EMPLID AND J1.EMPL_RCD = J3.EMPL_RCD AND J1.EFFDT = J3.EFFDT) ");
		sqlString.append("AND B.DOCUMENT_ID IN (");
		Iterator docHdrItr = documentHeaders.iterator();
		while (docHdrItr.hasNext()) {
			DocumentHeader docHdr = (DocumentHeader)docHdrItr.next();
			sqlString.append("'");
			sqlString.append(docHdr.getDocumentId());
			sqlString.append("',");
		}
		sqlString.append("'') ");

    	try {
    		Statement stmt = getDataSource().getConnection().createStatement();
    		ResultSet rs = stmt.executeQuery(sqlString.toString());

    		while (rs.next()) {
    			col.add(new ExtractDocument(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
    		}
    		stmt.close();

    	} catch (Exception e) {
    		LOG.warn("getExtractReportInfo error: " + e.getMessage());
    	}
    }

    public void takeSnapshot(Date payEndDate) throws SQLException {
        String sqlDelete = "DELETE FROM TK_DOCUMENT_HEADER_SNAP_T WHERE PAY_END_DT = ?";
        String sqlInsert = "INSERT INTO TK_DOCUMENT_HEADER_SNAP_T " + 
        " (SELECT DOCUMENT_ID, EMPLID, PAY_END_DT, DOCUMENT_STATUS, SYSDATE " + 
        "  FROM TK_DOCUMENT_HEADER_T WHERE " + 
        "  PAY_END_DT = ?)";
        
        TKServiceLocator.getTkJdbcTemplate().update(sqlDelete, new Object[]{payEndDate});
        TKServiceLocator.getTkJdbcTemplate().update(sqlInsert, new Object[]{payEndDate});
    }

//	public void removeAllDocumentHeaders() throws SQLException {
//		Statement stmt = getDataSource().getConnection().createStatement();
//
//        String sqlDelete = "DELETE FROM TK.TK_DOCUMENT_HEADER_T ";
//        stmt.executeUpdate(sqlDelete);
//
//        stmt.close();
//	}
}
