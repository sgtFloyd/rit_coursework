/*
 * Appointment.java
 *
 * Version:
 *     $Id: Appointment.java,v 1.19 2007-11-06 01:04:49 ges7506 Exp $
 *
 * Revisions:
 *     $Log: Appointment.java,v $
 *     Revision 1.19  2007-11-06 01:04:49  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.18  2007-11-05 19:54:13  hgt9090
 *     Began commenting the routine functions of the Appointment class
 *
 *     Revision 1.17  2007-11-04 21:25:57  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.16  2007-11-04 18:20:30  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.15  2007-11-04 17:35:54  vxj4346
 *     getCurrentTime
 *
 *     Revision 1.14  2007-11-03 21:45:20  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.13  2007-11-03 18:44:29  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.12  2007-11-01 21:56:28  lmh5282
 *     for real this time
 *
 *     Revision 1.11  2007-11-01 21:55:09  lmh5282
 *     deleted frequency value
 *
 *     Revision 1.10  2007-10-24 14:56:50  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.9  2007-10-23 21:50:50  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.8  2007-10-23 21:43:11  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.7  2007-10-22 16:34:42  hgt9090
 *     Added Confirmed/Tentative functionality. This will allow
 *     the user to set whether an appointment is confirmed or tentative.
 *
 *     Revision 1.6  2007-10-21 23:39:47  hgt9090
 *     Implemented Multiple Notes for each Appointment
 *
 *     Revision 1.5  2007-10-20 22:52:20  vxj4346
 *     another mess
 *
 *     Revision 1.4  2007-10-20 22:37:08  vxj4346
 *     constructo change
 *
 *     Revision 1.3  2007-10-20 22:34:25  vxj4346
 *     Added more variables dealing with Recurring Appointments
 *
 *     Revision 1.2  2007-10-13 18:22:41  vxj4346
 *     removed Steve
 *
 *     Revision 1.1  2007-10-09 22:30:12  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.6  2007-10-08 06:45:09  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.5  2007-10-07 19:14:24  vxj4346
 *     Edited Appointment SECalendar CommandCalendar
 *
 *     Revision 1.4  2007-10-07 18:36:22  hgt9090
 *     Added title attribute
 *
 *     Revision 1.3  2007-10-02 21:43:31  vxj4346
 *     Added appointment start time and end time
 *
 *     Revision 1.2  2007-09-29 19:01:22  vxj4346
 *     Implemented read, save methods
 *
 *     Revision 1.1  2007-09-27 18:40:30  vxj4346
 *     Fixed some type-Os
 *
 *     Revision 1.1  2007-09-25 19:05:59  vxj4346
 *     Initial version of Appointment class
 *
 */

import java.io.Serializable; 
import java.util.ArrayList;

/**
 * This is the appointment class.
 *
 * @author Vaidotas J
 * @author Henryk Tunguz
 */
public class Appointment implements Serializable, Comparable<Appointment> {
	// instance variables
	private boolean confirmed = false;;
	ArrayList<Note> notes;
	private String title;
	private String date;
	private String location;
	private int priority;
	private long startTime;
	private long endTime;
	private String description;
	private boolean recurring;
	private int typeOfRecurring;
	private boolean[] daysOfWeek;
	private ArrayList<String> exceptDates;
	private int N;
	private boolean fullDay;
	private boolean emailAlarm;
	private boolean visibleAlarm;
	static final long serialVersionUID = 123;
	private boolean alarmed;
	
	// constructor
	public Appointment(String title, String date, String location, 
			int priority, long startTime, long endTime, String description,
			boolean recurring, int typeOfRecurring, boolean[] daysOfWeek, 
			ArrayList<String> exceptDates, int N,
			boolean fullDay, boolean confirmed, boolean emailAlarm,
			boolean visibleAlarm) {
		
		this.title = title;
		this.date = date;
		this.location = location;
		this.priority = priority;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
		this.recurring = recurring;
		this.typeOfRecurring = typeOfRecurring;
		this.daysOfWeek = daysOfWeek;
		this.exceptDates = exceptDates;
		this.N = N;
		this.fullDay = fullDay;
		this.confirmed = confirmed;
		this.emailAlarm = emailAlarm;
		this.visibleAlarm = visibleAlarm;
		notes = new ArrayList<Note> ();
		alarmed = false;
	}
	
	public Appointment(Appointment a) {
		this.title = a.getTitle();
		this.date = a.getDate();
		this.location = a.getLocation();
		this.priority = a.getPriority();
		this.startTime = a.getStartTime();
		this.endTime = a.getEndTime();
		this.description = a.getDesc();
		this.recurring = a.getRecurring();
		this.daysOfWeek = a.getBoolDaysOfWeek();
		this.exceptDates = a.getExceptDates();
		this.N = a.getN();
		this.fullDay = a.getFullDay();
		this.confirmed = a.getConfirmed();
	}
	// accessors
	
	/** getTitle returns the title of the Appointment
	 *
	 * @return the title of the appointment
	 */
	public String getTitle() {
		return title;
	}
	
	/** getDate returns the date for the Appointment
	 *
	 * @return date represented as a String
	 */
	public String getDate() {
		return date;
	}
	
	/**  getLocation returns the location of which the appointment
	 * is occurring at
	 *  
	 * @return  a location of this appointment
	 */
	public String getLocation() {
		return location;
	}

	/** getPriority returns the priority of the appointment,
	 * be it 0 (Low), 1(Medium), or 2(High)
	 * 
	 * @return priority of this appointment
	 */
	public int getPriority() {
		return priority;
	}

	/** getStartTime returns the start time for the appointment
	 * 
	 * @return starting time of this appointment 
	 */
	public long getStartTime() {
		return startTime;
	}
	
	/** getEndTime returns the end time for the appointment
	 * 
	 * @return ending time of this appointment
	 */	
	public long getEndTime() {
		return endTime;
	}
	
	/** getDesc returns the description of the appointment
	 * 
	 * @return description of the appointment
	 */
	public String getDesc() {
		return description;
	}
	
	/** the toString returns the appointment as a String,
	 * showing all of it's data fields. (title, date, start time,
	 * end time, priority, description, recurring, type of recurring,
	 * 
	 * Represents this appointment as a String
	 *
	 * @return  this appointment as a String   
	 */
	public String toString() {
		return "title: "  + title + "\ndate: " + date + "\nStart time: " + startTime +
		"\nEnd time: " + endTime +
		"\npriority: " + priority +
		"\ndescription: " + description + 
		"\nisRecurring: " + recurring +
		"\ntype of recurring: " + typeOfRecurring +
		"\nEnd time: " + endTime + "\npriority: " + priority +
		"\ndescription: " + description + "\n"; 
	}

	
	// mutators
	
	/** the setTitle sets a new title
	 * 
	 * @param newTitle the new title to be set
	 */
	public void setTitle(String newTitle){
		title = newTitle;
	}
	
	/** the setDate sets a new date
	 * 
	 * @param newDate the new date to be set
	 */
	public void setDate(String newDate) {
		date = newDate;
	}

	/** the setLocation sets a new location
	 * 
	 * @param newLoc the new location to be set
	 */
	public void setLocation(String newLoc) {
		location = newLoc;
	}

	/** the setPriority sets a new priority
	 * 
	 * @param newTitle the new title to be set
	 */
	public void setPriority(int newPriority) {
		priority = newPriority;
	}

	/** the setStartTime sets a new start time
	 * 
	 * @param newTime the new start time to be set
	 */
	public void setStartTime(long newTime) {
		startTime = newTime;
	}
	
	/** the setEndTime sets a new end time
	 * 
	 * @param newTime the new end time to be set
	 */
	public void setEndTime(long newTime) {
		endTime = newTime;
	}

	/** the setDesc sets a new description
	 * 
	 * @param newDesc the new description to be set
	 */
	public void setDesc(String newDesc) {
		description = newDesc;
	}
	
	/** the getRecurring returns whether or not the
	 * appointment is recurring
	 *
	 *@return recurring  	is appointment recurring? 
	 */
	public boolean getRecurring(){
		return recurring;
	}
	
	/**the typeRecuring returns what type of recurring the 
	 * appointment is, if it is recurring every day, weekly,
	 * nth day of month or week etc.
	 * 
	 * @return typeOfRecurring 	the type of recurring the appointment is
	 */
	public int typeRecurring() {
		return typeOfRecurring;
	}
	
	/** getBoolDaysOfWeek returns a boolean array 
	 * describing whether or not each day is true for recurring
	 * 
	 * @return daysOfWeek 	array of booleans describing recurring
	 */
	public boolean[] getBoolDaysOfWeek(){
		return daysOfWeek;
	}
	
	/**getExceptDates returns an ArrayList of exception dates
	 * that represent which dates from a normally recurring series
	 * are not recurring and for a specific date
	 * 
	 * @return exceptDates 	the ArrayList of exception dates
	 */
	public ArrayList<String> getExceptDates(){
		return exceptDates;
	}
	
	
	/**
	 * @param date date to be added to except dates
	 */
	public void addExceptDate(String date) {
		exceptDates.add(date);
	}
	/**
	 * @return N, the number used for recurring appointments
	 */
	public int getN() { 
		return N;
	}
	/**
	 * @return true if appointment is confirmed
	 */
	public boolean getConfirmed(){
		return confirmed;
	}
	/**
	 * @param bool boolean value to set recurring status
	 */
	public void setRecurring(boolean bool){
		recurring = bool;
	}
	/**
	 * @param x type of recurring
	 * 				0 for Days of week
	 * 				1 for Every N Days
	 * 				2 for Nth day of month
	 * 				3 for Nth Mon/Tues of month
	 */
	public void setTypeRecurring(int x){
		typeOfRecurring = x;
	}
	/**
	 * @param x	 sets the boolean array representing the recurring values
	 * on the days of the week
	 */
	public void setBoolDaysOfWeek(boolean[] x){
		daysOfWeek = x;
	}
	/**
	 * @param x sets the except dates for this appointment
	 */
	public void setExceptDates(ArrayList<String> x){
		exceptDates = x;
	}
	/**
	 * @return true if this is a full day appointment
	 */
	public boolean getFullDay() {
		return fullDay;
	}
	/**
	 * @param f boolean value to set to full day
	 */
	public void setFullDay(boolean f) {
		fullDay = f;
	}
	/**
	 * @return an ArrayList of the notes for this appointment
	 */
	public ArrayList<Note> getNotes(){
		return notes;
	}
	/**
	 * @param note the note to be deleted from this appointment
	 */
	public void deleteNote(Note note){
		notes.remove(note);
	}
	/**
	 * @param note the note to be added to this appointment
	 */
	public void addNote(Note note){
		notes.add(note);
	}
	/**
	 * @param b boolean value to set for confirmed
	 */
	public void setConfirmed(boolean b){
		confirmed = b;
	}
	/**
	 * @param b boolean value to be set for email alarm
	 */
	public void setEmailAlarm(boolean b) {
		emailAlarm = b;
	}
	/**
	 * @param b boolean value to be set for visible alarm
	 */
	public void setVisibleAlarm(boolean b){
		visibleAlarm = b;
	}
	/**
	 * @return true is this appointment has an email alarm
	 */
	public boolean getEmailAlarm() {
		return emailAlarm;
	}
	/**
	 * @return true if this appointment hasa visible alarm
	 */
	public boolean getVisibleAlarm() {
		return visibleAlarm;
	}
	/**
	 * @return true if this appointment has any alarm
	 */
	public boolean getAlarmed() {
		return alarmed;
	}
	/**
	 * @param b value to set alarmed
	 */
	public void setAlarmed(boolean b) {
		alarmed = b; 
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	//@Override
	public int compareTo(Appointment that) {
		//if this is full day and that isn't
		if (this.getFullDay() && !that.getFullDay()){
			return -1;
		//if that is full day and this isn't
		} else if (!this.getFullDay() && that.getFullDay()){
			return 1;
		//if this and that are both full days
		} else if (this.getFullDay() && that.getFullDay()){
			return this.getTitle().compareTo(that.getTitle());
		//if neither this nor that are full days
		} else {
			//if this starts before that
			if(this.getStartTime() < that.getStartTime()){
				return -1;
			//if this starts after that
			} else if (this.getStartTime() > that.getStartTime()){
				return 1;
			//if start times are equal
			} else {
				//if this ends after that
				if (this.getEndTime() > that.getEndTime()){
					return -1;
				//if this ends before that
				} else if (this.getEndTime() < that.getEndTime()){
					return 1;
				//if end times are equal
				} else {
					return this.getTitle().compareTo(that.getTitle());
				}
			}
		}
	}
}