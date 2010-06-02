package org.kuali.hr.time.assignment.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.kuali.hr.time.assignment.dao.AssignmentDao;
import org.kuali.hr.time.domain.base.Assignment;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.test.TestHarnessWebBase;

public class AssignmentServiceImplTest extends TestHarnessWebBase {
    
    private static final Logger LOG = Logger.getLogger(AssignmentServiceImplTest.class);
    AssignmentDao assignmentDao = null;
    
    @Before
    public void setUp() throws Exception {
	super.setUp();
	assignmentDao = TkServiceLocator.getAssignmentDao();
    }
    
    @Test
    public void testGetAssignmentsByDate() throws Exception {
	Calendar cal = Calendar.getInstance();
	assignmentDao.deleteAllAssignments();
	createAssignmentsForAssignmentsByDateTest();

	cal.set(Calendar.YEAR, 2009);
	cal.set(Calendar.MONTH, Calendar.JANUARY);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	
	AssignmentService as = TkServiceLocator.getAssignmentService();
	assertNotNull("Bootstrap configuration error.", as);
	List<Assignment> list = as.getAssignmentsOnOrAfter(new Date(cal.getTimeInMillis()));
	assertNotNull("Null list of assignments.", list);
	
	assertEquals("Incorrect number of assignments returned.", 4, list.size());
	
	cal.set(Calendar.MONTH, Calendar.MARCH);
	list = as.getAssignmentsOnOrAfter(new Date(cal.getTimeInMillis()));
	assertEquals("Incorrect number of assignments returned.", 2, list.size());
    }
    
    private void createAssignmentsForAssignmentsByDateTest() throws Exception {
	Calendar cal = Calendar.getInstance();
	List<Assignment> assignmentList = new ArrayList<Assignment>(4);
	LOG.info("Creating test assignments.");
	
	cal.set(Calendar.YEAR, 2009);
	cal.set(Calendar.MONTH, Calendar.JANUARY);
	cal.set(Calendar.DAY_OF_MONTH, 1);

	Assignment a = new Assignment("12345", 1, new Date(cal.getTimeInMillis()), "ERN", 0, 1, 1, new BigDecimal("100") );
	assignmentList.add(a);

	cal.set(Calendar.MONTH, Calendar.FEBRUARY);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	
	Assignment b = new Assignment("12345", 1, new Date(cal.getTimeInMillis()), "ERN", 0, 1, 1, new BigDecimal("100") );
	assignmentList.add(b);
	
	cal.set(Calendar.MONTH, Calendar.MARCH);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	
	Assignment c = new Assignment("12345", 1, new Date(cal.getTimeInMillis()), "ERN", 0, 1, 1, new BigDecimal("100") );
	assignmentList.add(c);
	
	cal.set(Calendar.MONTH, Calendar.MARCH);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	
	Assignment d = new Assignment("12345", 1, new Date(cal.getTimeInMillis()), "ERN", 1, 1, 1, new BigDecimal("100") );
	assignmentList.add(d);
	
	assignmentDao.saveOrUpdate(assignmentList);
	assertEquals("Assignments not loaded.", 4, assignmentDao.findAllAssignments().size());
	LOG.info("Test assignments loaded.");
    }

}
