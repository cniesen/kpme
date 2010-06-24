package edu.iu.uis.hr.tk.timesheet.entity;

import edu.iu.uis.hr.Time;

public class ShiftHourDetail extends HourDetail {
    private int shiftDayIndex;
    private int dayIndex;
    private static final int MINUS_ONE=-1;
    private Time blockBeginTime;
    private Time blockEndTime;

    public ShiftHourDetail() {
        super();
        setShiftDayIndex(MINUS_ONE);
        setDayIndex(MINUS_ONE);   
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
            this.dayIndex = dayIndex;
    }

    public int getShiftDayIndex() {
        return shiftDayIndex;
    }

    public void setShiftDayIndex(int shiftDayIndex) {
            this.shiftDayIndex = shiftDayIndex;
    }

    public Time getBlockEndTime() {
        return blockEndTime;
    }

    public void setBlockEndTime(Time blockEndTime) {
        if (blockEndTime != null) {
            this.blockEndTime = blockEndTime;
        }
    }

    public Time getBlockBeginTime() {
        return blockBeginTime;
    }

    public void setBlockBeginTime(Time blockBeginTime) {
        if (blockBeginTime != null) {
            this.blockBeginTime = blockBeginTime;
        }
    }

}
