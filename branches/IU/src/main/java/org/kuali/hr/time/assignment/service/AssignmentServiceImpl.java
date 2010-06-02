package org.kuali.hr.time.assignment.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.hr.time.assignment.dao.AssignmentDao;
import org.kuali.hr.time.domain.base.Assignment;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;

public class AssignmentServiceImpl implements AssignmentService {
    private static final Logger LOG = Logger.getLogger(AssignmentServiceImpl.class);
    private AssignmentDao assignmentDao;

    @Override
    public List<Assignment> getAssignmentsOnOrAfter(Date effectiveDate) {	
	return assignmentDao.findAssignmentsOnOrAfter(effectiveDate);
    }

    public AssignmentDao getAssignmentDao() {
	return assignmentDao;
    }

    public void setAssignmentDao(AssignmentDao assignmentDao) {
	this.assignmentDao = assignmentDao;
    }
}
