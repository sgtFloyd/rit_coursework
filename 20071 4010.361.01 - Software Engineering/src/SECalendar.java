/*
 * SECalendar
 *
 * Version:
 *     $Id: SECalendar.java,v 1.19 2007-11-06 20:39:51 lmh5282 Exp $
 *
 * Revisions:
 *     $Log: SECalendar.java,v $
 *     Revision 1.19  2007-11-06 20:39:51  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.18  2007-11-04 21:07:35  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.17  2007-11-03 22:38:32  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.16  2007-11-03 21:03:02  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.15  2007-11-03 18:46:18  vxj4346
 *     small change
 *
 *     Revision 1.14  2007-11-03 18:45:51  vxj4346
 *     second recurrence
 *
 *     Revision 1.12  2007-11-03 18:18:55  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.11  2007-11-03 18:17:59  vxj4346
 *     recurring
 *
 *     Revision 1.10  2007-10-22 14:54:55  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.9  2007-10-22 04:15:29  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.8  2007-10-22 02:57:51  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.7  2007-10-21 06:54:13  hgt9090
 *     Implemented utility method for computing the first of the month
 *
 *     Revision 1.6  2007-10-20 21:38:25  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.5  2007-10-20 17:53:29  vxj4346
 *     used split on /
 *
 *     Revision 1.4  2007-10-20 17:19:29  vxj4346
 *     accessors, mutators for currentDate and today
 *
 *     Revision 1.3  2007-10-20 16:13:30  vxj4346
 *     added computeNumDays
 *
 *     Revision 1.2  2007-10-13 18:22:13  vxj4346
 *     Implemented viewApp, updateConfig, some accessors
 *
 *     Revision 1.1  2007-10-09 22:30:12  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.10  2007-10-08 22:18:10  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.9  2007-10-08 06:44:43  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.8  2007-10-07 20:38:03  vxj4346
 *     added isModified method
 *
 *     Revision 1.7  2007-10-07 20:27:12  vxj4346
 *     edited importCalendar method
 *
 *     Revision 1.6  2007-10-07 19:14:24  vxj4346
 *     Edited Appointment SECalendar CommandCalendar
 *
 *     Revision 1.5  2007-10-07 18:27:38  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.4  2007-10-06 17:43:53  vxj4346
 *     Implemented functionality. Changed design of this class
 *
 *     Revision 1.3  2007-10-02 22:11:31  sch5387
 *     can read and write from calendarIO, but cannot yet return the data in week or month format. Can fill in appointments into specific days of the year.
 *
 *     Revision 1.2  2007-10-02 21:46:10  sch5387
 *     SEcalendar, fixing errors
 *
 *     Revision 1.1  2007-09-29 18:59:00  sch5387
 *     steve's work, 9-29-07
 *
 *
 */

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.io.*;

/**
 * @author vxj4346
 * @author Henryk Tunguz
 * 
 * Calendar Class, interfaces with the GUI interface and the IO class to present information 
 * from the data files to the GUI.
 *
 */
public class SECalendar {
    
    private final String calendar_config = "config.cfg";
    private ArrayList<String> config;
    private boolean modified;
    private ArrayList<Appointment> appointments;
    private CalendarIO IO;
    private GregorianCalendar cale;
    private String currentDate;
    private String today;
    
    /**
     * Instantiates CalendarIO and reads configuration file. Opens an existing calendar
     * if the configuration file contains calendar file name. Creates an empty calendar
     * otherwise. 
     */
    public SECalendar() { 
        cale = new GregorianCalendar();
        
        Integer mont = cale.get(Calendar.MONTH) + 1;
        Integer day = cale.get(Calendar.DAY_OF_MONTH);
        Integer year = cale.get(Calendar.YEAR);
        currentDate = year.toString() + "/" + mont.toString() 
                        + "/" + day.toString(); 
        today = currentDate;
        modified = false;
        IO = new CalendarIO();
        config = getConfig();
        if (config != null) {
            // if saved calendar exists
            File f2 = new File(config.get(0));
            if (f2.exists()) {
                appointments = importCalendar(config.get(0));
            } else {
                appointments = new ArrayList<Appointment>();
            }
        }
    }   
    
    /**
     * is Appointment list empty?
     * @return  true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return appointments.isEmpty();
    }
    
    /**
     * Adds an appointment to the current list of appointments
     * @param app new appointment to be added
     */
    public void addAppointment(Appointment app) {
        modified = true;
        appointments.add(app);
    }
    
    /**
     * Deletes the old appointment and adds a new appointment to the list
     * @param oldApp old appointment
     * @param newApp new appointment
     */
    public void saveEditedApp(Appointment oldApp, Appointment newApp) {
        modified = true;
        // save specific instance
        if (oldApp.getRecurring() && !newApp.getRecurring()) {
            System.out.println("Saving specific instance");
            oldApp.addExceptDate(newApp.getDate());
            appointments.add(newApp);
        } else {
            appointments.set(appointments.indexOf(oldApp), newApp);
        }
    }
    
    /**
     * Removes specific instance of the recurring appointment
     * 
     * @param oldApp  recurring appointment 
     * @param date    date that the appointment will not reocurr
     */
    public void removeSpecific(Appointment oldApp, String date) {
        if (oldApp.getRecurring()) {
            oldApp.addExceptDate(date);
        }
    }
    
    /**
     * Removes an appointment from the list
     * 
     * @param app appointment that will be deleted
     */
    public void removeApp(Appointment app) {
        modified = true;
        appointments.remove(app);       
    }
    
    
    /**
     * View Appointment of the specified date
     * @return appointment list of that day
     * @param date    
     */ 
    public ArrayList<Appointment> viewApp(String date) {
        ArrayList<Appointment> filteredApps = new ArrayList<Appointment>();
        String[] tokens1 = date.split("/");  
        // for every appointment in the list
        for (Appointment a: appointments) {
            boolean addIt = true;
            String[] tokens2 = a.getDate().split("/");
            if (a.getRecurring()) {
                switch(a.typeRecurring()) {     
                    case 0: {
                        setCaleDate(date);
                        boolean[] daysOfWeek = a.getBoolDaysOfWeek();
                        if (daysOfWeek[cale.get(Calendar.DAY_OF_WEEK)-1]) {
                            if (!a.getExceptDates().contains(date)) {
                                filteredApps.add(a);
                                addIt = false;
                            }
                        }
                    }       
                    break;
                    case 1: {
                        int n = a.getN();
                        Date d1 = new Date(Integer.parseInt(tokens1[0]), 
                                    Integer.parseInt(tokens1[1]),
                                    Integer.parseInt(tokens1[2]));
                        long d1Long = d1.getTime();
                        Date d2 = new Date(Integer.parseInt(tokens2[0]),
                                Integer.parseInt(tokens2[1]),
                                Integer.parseInt(tokens2[2]));
                        long d2Long = d2.getTime();
                        if ( ((d2Long - d1Long)/86400000L) % n == 0  ) {
                            if (!a.getExceptDates().contains(date)) {
                                filteredApps.add(a);
                                addIt = false;
                            }                       
                        }
                    }
                    break;
                    case 2: {
                        if (Integer.parseInt(tokens1[2]) == a.getN()) {
                            if (!a.getExceptDates().contains(date)) {
                                filteredApps.add(a);
                                addIt = false;
                            }
                        }
                    }
                    break;
                    case 3: {
                        boolean[] daysOfWeek = a.getBoolDaysOfWeek();
                        int n = a.getN();
                        int[] days = computeNumDays(Integer.parseInt(tokens1[1]),
                                Integer.parseInt(tokens1[2]));
                        int count = 0;
                        for (int i = 1; i <= days.length; i++ ) {
                            String dt = tokens1[0]+"/"+tokens1[1]+"/"+i;
                            setCaleDate(dt);
                            if (daysOfWeek[cale.get(Calendar.DAY_OF_WEEK)-1] ) {
                                count++;
                                if (count == n && dt.equals(date)) {
                                    if (!a.getExceptDates().contains(date)) {
                                        filteredApps.add(a);
                                        addIt = false;
                                    }
                                }                                   
                            }
                        }                       
                    }
                    break;
                }
            } else {
                // for every token in parameter
                for (int i = 0; i < tokens1.length; i++ ) {
                    if (!(tokens1[i].equals(tokens2[i]))) {
                        addIt = false;
                    }
                }
                if (addIt) {
                    filteredApps.add(a);
                }               
            }
        }
        return filteredApps;
    }
    
    /**
     * Imports calendar from specified location
     * @param filename the full path of filename
     */
    public ArrayList<Appointment> importCalendar(String filename) {
        appointments = (ArrayList<Appointment>)IO.getAppointments(filename);
        return appointments;
    }
    
    /**
     * Exports calendar to a specific file
     * @param filename a name of a file
     */
    public void exportCalendar(String filename) {
        IO.saveAppointments(appointments, filename);
        modified = false;
    }
    
    /**
     * Is calendar modified?
     * @return true if calendar list is modified, false otherwise
     */
    public boolean isModified() {
        return modified;
    }
    
    /**
     * Accessor for config
     * @return ArrayList of strings 
     */
    public ArrayList<String> getConfig() {
        File f = new File(calendar_config);
        // if config file exists
        if (f.exists()) {       
            return IO.getConfig(calendar_config);
        } else {
            return null;
        }
    }
    
    /**
     * Saves configuration to the calendar_config file
     * @param c   a list of Strings
     */
    public void updateConfig(ArrayList<String> c) {
        config = c;
        IO.saveConfig(config, calendar_config);
    }

    /** ComputeNumDays calculates how many days should be in 
     * the current month, since some months have 30 days and
     * others have 31. Leap years are also accounted for.
     * 
     * @return days   an array of days calculated to fit the current month
     */
    
    public int[] computeNumDays(int mont, int year) {
        
        //Simply, we instantiate an array of days, then proceed
        //to get the current month. if the current month matches
        //0,2,4,6,7,9, or 11 we know there are 31 days.
        //If the current month is February, we ask if it is a leap 
        //year. If it is, then an array of 29 days is made. Otherwise, 
        //the array is set to 28.
        //In the case that the current month is it not any of those
        //predetermined numbers, the array is set to a size of 
        //30 days.
        cale.set(Calendar.MONTH, mont - 1);
        int[] days;
        int mon = cale.get(Calendar.MONTH);
        if(mon == 0 ||mon == 2 || mon == 4 || mon == 6 ||
            mon == 7 || mon == 9 || mon == 11){
            days = new int[31];
        } 
        else if(mon == 1) {
            if(cale.isLeapYear(year)) {
                days = new int[29];
            }
            else {
                days = new int[28];
            }
        }else {
            days = new int[30];
        }
        
        for(int b = 0; b < days.length; b++) {
            
            days[b] = b + 1;
            
        }
        return days;
    }
    
    //access currentDate
    public String getCurrentDate() {
        return currentDate;
    }
    
    //access today
    public String getToday() { 
        return today;
    }
    
    //mutator currentDate
    public void setCurrentDate(String newCurrent) {
        currentDate = newCurrent;
    }
    /** This method computes the first day of every month
     *  since every month starts on a different day.
     *  
     * @return standardFirst   the calculated first day of the current month
     */
    
    private void setCaleDate( String date ) {
        int year;
        int month;
        int day;
        String[] tokens = date.split("/");
        year = Integer.parseInt(tokens[0]);
        month = Integer.parseInt(tokens[1]);
        day = Integer.parseInt(tokens[2]);
        cale.set(Calendar.YEAR, year);
        cale.set(Calendar.MONTH, month - 1);
        cale.set(Calendar.DAY_OF_MONTH, day);
    }
    
    public int computeFirstOfMonth() {
        
        //mod our current day by 7 to roll the calendar up 
        //to the first week, then substract the day of the week
        //(Sunday = 1, Monday = 2 etc.) from the day we just calculated
        //then we ask if the result is negative, if it is then we know that
        //the month does not start on that day. We make it positive and then
        //subtract seven and add 1 because of index errors and then we arrive 
        //at the first day of the month
        //otherwise if the result is positive, then we add one and ask if it 
        //is over 7, it is we take away seven 
        
        // make sure cale is the current date

        setCaleDate(currentDate);
        int firstDay = cale.get(Calendar.DAY_OF_MONTH) % 7;
        int standardFirst = cale.get(Calendar.DAY_OF_WEEK);
        standardFirst = (standardFirst - firstDay);
        if(standardFirst < 0){
            standardFirst = standardFirst * (-1);
            standardFirst = 7 - standardFirst;
            standardFirst = standardFirst + 1;
        }else {
            standardFirst = standardFirst + 1;
            if(standardFirst > 7) {
                standardFirst = standardFirst - 7;
            }
        }
        return standardFirst;
    }
    
    public boolean isLeapYear(int year) {
        return cale.isLeapYear(year);
    }
}
