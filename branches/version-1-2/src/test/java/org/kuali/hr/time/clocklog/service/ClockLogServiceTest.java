package org.kuali.hr.time.clocklog.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.hr.test.KPMETestCase;
import org.kuali.hr.time.service.base.TkServiceLocator;
import org.kuali.hr.time.timeblock.TimeBlock;

public class ClockLogServiceTest extends KPMETestCase {
	
	@Test
	public void testGetUnapprovedIPWarning() throws Exception {
		List<TimeBlock> tbLists = new ArrayList<TimeBlock>();
		TimeBlock timeBlock = new TimeBlock();
        timeBlock.setUserPrincipalId("testUser");
        timeBlock.setClockLogCreated(true);
        timeBlock.setClockLogEndId("5000");
        tbLists.add(timeBlock);		
		
		List<String> warningList = TkServiceLocator.getClockLogService().getUnapprovedIPWarning(tbLists);
		Assert.assertTrue("There should be 1 warning message ", warningList.size()== 1);
		String warning = warningList.get(0);
		Assert.assertTrue("Warning message should be 'Warning: Action 'Clock Out' taken at 2012-03-01 08:08:08.0 was from an unapproved IP address - TEST', not " + warning, 
				warning.equals("Warning: Action 'Clock Out' taken at 2012-03-01 08:08:08.0 was from an unapproved IP address - TEST"));
		
	}
}
