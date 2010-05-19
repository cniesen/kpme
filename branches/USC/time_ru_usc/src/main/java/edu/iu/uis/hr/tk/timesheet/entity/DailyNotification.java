package edu.iu.uis.hr.tk.timesheet.entity;

import org.apache.log4j.Logger;

import edu.iu.uis.hr.entity.PersistentMaintainedEntity;

public class DailyNotification extends AbstractDailyNotification implements PersistentMaintainedEntity {
	private static final Logger LOG = Logger.getLogger(DailyNotification.class);
    
	public DailyNotification() {
		super();
	}
    
}

