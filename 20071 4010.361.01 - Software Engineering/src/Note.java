/*
 * Note.java
 *
 * Version:
 *     $Id: Note.java,v 1.2 2007-11-06 04:42:42 hgt9090 Exp $
 *
 * Revisions:
 *     $Log: Note.java,v $
 *     Revision 1.2  2007-11-06 04:42:42  hgt9090
 *     documented code.
 *
 *     Revision 1.1  2007-10-20 20:38:49  ges7506
 *     Initial Revision
 *
 *
 */

import java.io.Serializable;

/**
 * This is the note class, it holds one or many notes per Appointment.
 * Notes hold a Title and description field, where the user may write a 
 * detailed description.
 *
 * @author Gabriel Smith
 */
public class Note implements Serializable{
	private String title;
	private String description;
	private Appointment host;
	static final long serialVersionUID = 123;
	
	//default constructor
	public Note(){
		this("", "", null);
	}
	
	//constructor
	public Note(String title, String description, Appointment host) {
		this.title = title;
		this.description = description;
		this.host = host;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * @return the title of the note
	 */
	public String getTitle(){
		return title;
	}
	
	/** 
	 * @param description the description for this note
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the description of the note
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param host 	the appointment that this note belongs to
	 */
	
	public void setHost(Appointment host) {
		this.host = host;
	}
	
	/**
	 * @return the host appointment of the note
	 */
	public Appointment getHost() {
		return host;
	}
}