package org.kuali.hr.time.timeentry.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kuali.hr.time.assignment.Assignment;
import org.kuali.hr.time.base.web.TkForm;
import org.kuali.hr.time.paycalendar.PayCalendarEntries;
import org.kuali.rice.kim.bo.Person;

public class TimeEntryForm extends TkForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 883093629749776571L;

	private PayCalendarEntries payCalendarEntry;
	private List<TimeCaptureRow> timeCaptureRows = new ArrayList<TimeCaptureRow>();
	private List<Assignment> assignments = new ArrayList<Assignment>();
	private List<Note> notes = new ArrayList<Note>();

	private String newNote;
	
	public void setPayCalendarEntry(PayCalendarEntries payCalendarEntry) {
		this.payCalendarEntry = payCalendarEntry;
	}

	public PayCalendarEntries getPayCalendarEntry() {
		return payCalendarEntry;
	}

	public void setTimeCaptureRows(List<TimeCaptureRow> timeCaptureRows) {
		this.timeCaptureRows = timeCaptureRows;
	}

	public List<TimeCaptureRow> getTimeCaptureRows() {
		return timeCaptureRows;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNewNote(String newNote) {
		this.newNote = newNote;
	}

	public String getNewNote() {
		return newNote;
	}

	public class Note {
		
		private final SimpleDateFormat DF = new SimpleDateFormat("MM/dd/yyyy");
		
		private Date createDate;
		private Person creator;
		private String text;

		public void setText(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setCreator(Person creator) {
			this.creator = creator;
		}

		public Person getCreator() {
			return creator;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public Date getCreateDate() {
			return createDate;
		}
		
		public String getFormattedDate(){
			return DF.format(createDate);
		}
	}

	public Note Note() {
		return new Note();
	}

}
