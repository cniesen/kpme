package edu.iu.uis.hr.tk.timesheet.entity.logic;

import edu.iu.uis.hr.Context;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.entity.logic.AbstractLogic;
import edu.iu.uis.hr.entity.logic.OperationalLogic;
import edu.iu.uis.hr.tk.directory.service.WebUserService;
import edu.iu.uis.hr.tk.timesheet.entity.ClockMode;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;
import edu.iu.uis.hr.tk.timesheet.service.TimesheetService;

public class ClockModeLogic extends AbstractLogic implements OperationalLogic {

//	private TimesheetService timesheetService;
//	private WebUserService webUserService;

	
	public void execute(Entity entity) {
		if (getTimesheetService().isDocumentLockedByUser(((TimesheetDocument)entity).getDocumentId(), getWebUserService().getUser().getUniversityId())) {
	        ((TimesheetDocument)entity).getClock().setMode(new ClockMode(false, ((TimesheetDocument)entity).getClock()));
		}
	}

	
	public boolean isMatch() {
		return false;
	}


    public TimesheetService getTimesheetService() {
        return (TimesheetService) Context.getService(TimesheetService.class);
}


	public WebUserService getWebUserService() {
        return (WebUserService) Context.getService(WebUserService.class);
}

}
