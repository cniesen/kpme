package edu.iu.uis.hr.tk;

import edu.iu.uis.hr.tk.timesheet.client.BreakInEvent;
import edu.iu.uis.hr.tk.timesheet.client.BreakOutEvent;
import edu.iu.uis.hr.tk.timesheet.client.ClockInEvent;
import edu.iu.uis.hr.tk.timesheet.client.ClockOutEvent;
import edu.iu.uis.hr.tk.timesheet.client.EditEvent;
import edu.iu.uis.hr.tk.timesheet.client.LunchInEvent;
import edu.iu.uis.hr.tk.timesheet.client.LunchOutEvent;

public interface Event extends edu.iu.uis.hr.Event {
    public static final ClockInEvent CLOCK_IN_EVENT = new ClockInEvent();
    public static final ClockOutEvent CLOCK_OUT_EVENT = new ClockOutEvent();
    public static final LunchInEvent LUNCH_IN_EVENT = new LunchInEvent();
    public static final LunchOutEvent LUNCH_OUT_EVENT = new LunchOutEvent();
    public static final BreakInEvent BREAK_IN_EVENT = new BreakInEvent();
    public static final BreakOutEvent BREAK_OUT_EVENT = new BreakOutEvent();
    public static final EditEvent EDIT_EVENT = new EditEvent();
}