package edu.iu.uis.hr.tk.timesheet.entity;

import java.util.Date;

import edu.iu.uis.hr.Time;

public class ActualTime extends DocumentHeader {

    private Date date;
    String assignment;
    private String actualBeginTime;
    private String actualEndTime;
    private Time beginTime;
    private Time endTime;

public ActualTime(Date date,String assignment, String actualBeginTime, String actualEndTime, Time roundedBeginTime, Time roundedEndTime) {
        super();
        this.date = date;
        this.assignment = assignment;
        this.actualBeginTime = actualBeginTime;
        this.actualEndTime = actualEndTime;
        this.beginTime = roundedBeginTime;
        this.endTime = roundedEndTime;
    }

/**
     * @return the actualBeginTime
     */
    public String getActualBeginTime() {
        return actualBeginTime;
    }

    /**
     * @param the actualBeginTime to set
     */
    public void setActualBeginTime(String actualBeginTime) {
        this.actualBeginTime = actualBeginTime;
    }

    /**
     * @return the actualEndTime
     */
    public String getActualEndTime() {
        return actualEndTime;
    }

    /**
     * @param the actualEndTime to set
     */
    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    /**
     * @return the beginTime
     */
    public Time getBeginTime() {
        return beginTime;
    }

    /**
     * @param the beginTime to set
     */
    public void setBeginTime(Time roundedBeginTime) {
        this.beginTime = roundedBeginTime;
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param the endTime to set
     */
    public void setEndTime(Time roundedEndTime) {
        this.endTime = roundedEndTime;
    }

public ActualTime() {
    super();
    // TODO implement this constructor
}

/**
 * @return the date
 */
public Date getDate() {
    return date;
}

/**
 * @param the date to set
 */
public void setDate(Date date) {
    this.date = date;
}

/**
 * @return the assignment
 */
public String getAssignment() {
    return assignment;
}

/**
 * @param the assignment to set
 */
public void setAssignment(String assignment) {
    this.assignment = assignment;
}
  
  
    
    
}
