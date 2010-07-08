package org.kuali.hr.leave.test;

import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.kuali.hr.leave.TkConfiguration;
import org.kuali.hr.leave.service.base.TkServiceLocator;

public class TestHarnessNonWebBase extends TestHarnessBase {
    
    private static final Logger LOG = Logger.getLogger(TestHarnessNonWebBase.class);
    
	
    @Override
    @Before
    public void setUp() throws Exception {
        try{
            TkServiceLocator.start();
            // TODO - Start up Minimal set of services here, perhaps depending on test case need.
        } catch(Exception e){
		LOG.error("Failed to start TK app lifecycle", e);
		throw new RuntimeException("Failed to start TK app lifecycle", e);
        }
        TkConfiguration.baseApplicationSetup();
        super.setUp();
    }
    
    @Override
    @After
    public void tearDown() throws Exception {
	super.tearDown();
    }

}
