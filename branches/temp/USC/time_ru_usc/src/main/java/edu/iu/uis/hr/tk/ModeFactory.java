package edu.iu.uis.hr.tk;

import edu.iu.uis.hr.Mode;
import edu.iu.uis.hr.client.StrutsActionForm;
import edu.iu.uis.hr.entity.Entity;
import edu.iu.uis.hr.tk.client.NotifySupervisorSearchCriteriaMode;
import edu.iu.uis.hr.tk.client.OtherTimesheetsSearchCriteriaMode;
import edu.iu.uis.hr.tk.client.UserPreferenceForm;
import edu.iu.uis.hr.tk.client.UserPreferenceFormMode;
import edu.iu.uis.hr.tk.department.entity.Department;
import edu.iu.uis.hr.tk.department.entity.Kiosk;
import edu.iu.uis.hr.tk.department.entity.KioskMode;
import edu.iu.uis.hr.tk.directory.entity.UserRoles;
import edu.iu.uis.hr.tk.entity.NotifySupervisorSearchCriteria;
import edu.iu.uis.hr.tk.entity.OtherTimesheetsSearchCriteria;
import edu.iu.uis.hr.tk.entity.TopLevelEntityMode;
import edu.iu.uis.hr.tk.extract.client.PayrollExtractRunForm;
import edu.iu.uis.hr.tk.extract.client.PayrollExtractRunFormMode;
import edu.iu.uis.hr.tk.rule.entity.Rule;
import edu.iu.uis.hr.tk.rule.entity.RuleMode;
import edu.iu.uis.hr.tk.timesheet.client.DocumentHeaderMaintenanceMode;
import edu.iu.uis.hr.tk.timesheet.client.TimeBlockMaintenanceForm;
import edu.iu.uis.hr.tk.timesheet.client.TimeBlockMaintenanceFormMode;
import edu.iu.uis.hr.tk.timesheet.client.TimesheetDocumentForm;
import edu.iu.uis.hr.tk.timesheet.client.TimesheetDocumentFormMode;
import edu.iu.uis.hr.tk.timesheet.entity.CheckedPayCalendarDate;
import edu.iu.uis.hr.tk.timesheet.entity.CheckedPayCalendarDateMode;
import edu.iu.uis.hr.tk.timesheet.entity.Clock;
import edu.iu.uis.hr.tk.timesheet.entity.ClockMode;
import edu.iu.uis.hr.tk.timesheet.entity.DocumentHeader;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetail;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetailDistribution;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetailDistributionMode;
import edu.iu.uis.hr.tk.timesheet.entity.HourDetailMode;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlock;
import edu.iu.uis.hr.tk.timesheet.entity.TimeBlockMaintenanceMode;
import edu.iu.uis.hr.tk.timesheet.entity.TimesheetDocument;

public class ModeFactory extends edu.iu.uis.hr.ModeFactory {

    protected Mode getStrutsActionFormMode(StrutsActionForm form, boolean editable) {
        if (form instanceof TimesheetDocumentForm) {
            return new TimesheetDocumentFormMode(editable, form);
        } else if (form instanceof PayrollExtractRunForm) {
            return new PayrollExtractRunFormMode(editable, form);
        } else if (form instanceof UserPreferenceForm) {
            return new UserPreferenceFormMode(editable, form);
        } else if (form instanceof TimeBlockMaintenanceForm) {
            return new TimeBlockMaintenanceFormMode(editable, form);
        }
        return super.getStrutsActionFormMode(form, editable);
    }

    protected Mode getEntityMode(Entity entity, boolean editable) {
        if ((entity instanceof UserRoles) || (entity instanceof Department) || (entity instanceof TimesheetDocument)) {
            return new TopLevelEntityMode(editable, entity);
        } else if (entity instanceof Clock) {
            return new ClockMode(editable, entity);
        } else if (entity instanceof Kiosk) {
            return new KioskMode(editable, entity);
        } else if (entity instanceof HourDetailDistribution) {
            return new HourDetailDistributionMode(editable, entity);
        } else if (entity instanceof Rule) {
            return new RuleMode(editable, entity);           
        } else if (entity instanceof HourDetail) {
            return new HourDetailMode(editable, entity);
        } else if (entity instanceof OtherTimesheetsSearchCriteria) {
            return new OtherTimesheetsSearchCriteriaMode(editable, entity);
        } else if (entity instanceof NotifySupervisorSearchCriteria) {
            return new NotifySupervisorSearchCriteriaMode(editable, entity);
        } else if (entity instanceof CheckedPayCalendarDate) {
            return new CheckedPayCalendarDateMode(editable, entity);
        } else if (entity instanceof DocumentHeader) {
            return new DocumentHeaderMaintenanceMode(editable, entity);
        } else if (entity instanceof TimeBlock) {
            return new TimeBlockMaintenanceMode(editable, entity);
        }  
        return super.getEntityMode(entity, editable);
    }

}
