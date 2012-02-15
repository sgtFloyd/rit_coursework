import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.ArrayList;

/*
 * EventHandler.java
 *
 * Version:
 *     $Id: EventListener.java,v 1.121 2007-11-06 20:34:27 lmh5282 Exp $
 *
 * Revisions:
 *     $Log: EventListener.java,v $
 *     Revision 1.121  2007-11-06 20:34:27  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.120  2007-11-06 19:46:02  vxj4346
 *     no spaces popup
 *
 *     Revision 1.119  2007-11-06 19:33:23  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.118  2007-11-06 18:43:05  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.117  2007-11-06 06:33:10  lmh5282
 *     cleaned up code... deleted stuff
 *
 *     Revision 1.116  2007-11-06 04:09:06  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.115  2007-11-06 01:58:57  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.114  2007-11-06 00:16:25  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.113  2007-11-06 00:14:17  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.112  2007-11-05 23:52:27  lmh5282
 *     lots
 *
 *     Revision 1.111  2007-11-05 22:17:46  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.110  2007-11-05 22:10:06  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.109  2007-11-05 22:06:12  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.108  2007-11-05 21:52:55  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.107  2007-11-05 21:28:29  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.106  2007-11-05 21:20:55  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.105  2007-11-05 21:14:18  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.104  2007-11-05 21:13:06  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.103  2007-11-05 21:07:31  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.102  2007-11-05 19:46:10  lmh5282
 *     finished gotodate error checking
 *
 *     Revision 1.101  2007-11-05 19:17:18  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.100  2007-11-05 16:26:08  lmh5282
 *     fixed priority in prefs
 *
 *     Revision 1.99  2007-11-05 00:56:25  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.98  2007-11-05 00:12:37  lmh5282
 *     finished prefs
 *
 *     Revision 1.97  2007-11-04 23:40:25  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.96  2007-11-04 23:39:45  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.95  2007-11-04 23:23:32  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.94  2007-11-04 23:05:32  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.93  2007-11-04 22:31:18  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.92  2007-11-04 22:12:36  lmh5282
 *     more preferences
 *
 *     Revision 1.91  2007-11-04 19:33:00  lmh5282
 *     fixed viewing notes
 *
 *     Revision 1.90  2007-11-04 19:09:52  lmh5282
 *     added preferences
 *
 *     Revision 1.89  2007-11-03 22:47:22  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.88  2007-11-03 22:02:21  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.87  2007-11-03 22:01:18  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.86  2007-11-03 21:20:16  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.85  2007-11-03 21:12:11  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.84  2007-11-03 21:05:14  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.83  2007-11-03 21:02:52  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.82  2007-11-03 20:57:49  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.81  2007-11-03 20:34:57  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.80  2007-11-03 20:01:23  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.79  2007-11-03 19:59:51  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.78  2007-11-03 18:41:31  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.77  2007-11-03 18:36:34  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.76  2007-11-03 18:26:43  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.75  2007-11-03 18:25:17  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.74  2007-11-03 18:01:16  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.73  2007-11-01 22:11:48  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.72  2007-11-01 21:57:24  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.71  2007-11-01 19:55:58  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.70  2007-10-29 02:52:00  hgt9090
 *     added a isLeapYear function via our SECalendar instance
 *     Developed the Day View for the Calendar, it'll show appointments
 *     based on their time slice. Also made the next and previous day functions,
 *     and all ensuing repercussions are as a result taken care of.
 *
 *     Revision 1.69  2007-10-24 16:05:46  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.68  2007-10-24 15:58:30  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.67  2007-10-24 15:17:23  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.66  2007-10-24 14:47:53  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.65  2007-10-24 14:23:44  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.64  2007-10-24 14:10:32  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.63  2007-10-23 23:57:56  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.62  2007-10-23 23:45:40  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.61  2007-10-23 23:09:31  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.60  2007-10-23 22:15:03  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.59  2007-10-23 22:09:14  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.58  2007-10-23 21:58:13  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.57  2007-10-23 21:19:32  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.56  2007-10-23 21:17:40  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.55  2007-10-22 21:26:32  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.54  2007-10-22 21:05:04  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.53  2007-10-22 20:52:17  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.52  2007-10-22 20:37:39  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.51  2007-10-22 20:21:55  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.50  2007-10-22 20:13:14  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.49  2007-10-22 18:39:10  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.48  2007-10-22 17:53:23  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.47  2007-10-22 17:30:52  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.46  2007-10-22 17:27:59  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.45  2007-10-22 17:11:22  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.44  2007-10-22 16:57:45  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.43  2007-10-22 16:49:13  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.42  2007-10-22 16:40:04  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.41  2007-10-22 16:22:19  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.40  2007-10-22 16:03:56  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.39  2007-10-22 15:24:44  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.38  2007-10-22 14:52:44  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.37  2007-10-22 02:34:36  vxj4346
 *     *** empty log message ***
 *
 *     Revision 1.36  2007-10-22 01:47:41  vxj4346
 *     event listener
 *
 *     Revision 1.35  2007-10-21 23:49:26  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.34  2007-10-21 23:45:30  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.33  2007-10-21 23:45:12  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.32  2007-10-21 23:33:49  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.31  2007-10-21 23:07:14  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.30  2007-10-21 22:37:06  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.29  2007-10-21 21:53:08  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.28  2007-10-21 21:49:57  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.27  2007-10-21 20:34:04  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.26  2007-10-21 19:52:26  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.25  2007-10-21 19:50:56  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.24  2007-10-21 19:17:32  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.23  2007-10-21 18:36:40  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.22  2007-10-21 17:35:12  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.21  2007-10-21 17:25:58  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.20  2007-10-21 17:24:19  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.19  2007-10-21 06:56:52  hgt9090
 *     implemented firstOfMonth method to talk with
 *     SECalendar. Also, implemented ArrayList of JPanels for
 *     correct dayplacement.
 *
 *     Revision 1.18  2007-10-20 23:15:39  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.17  2007-10-20 21:30:54  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.16  2007-10-20 21:04:42  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.15  2007-10-20 18:52:27  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.14  2007-10-20 18:41:39  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.13  2007-10-20 18:39:06  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.12  2007-10-20 18:33:55  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.11  2007-10-20 18:15:17  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.10  2007-10-20 18:08:06  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.9  2007-10-20 17:23:04  lmh5282
 *     open popup, popup listener, isconfigempty(), selected date
 *
 *     Revision 1.8  2007-10-20 16:12:14  lmh5282
 *     started adding functionality
 *
 *     Revision 1.7  2007-10-18 05:54:42  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.6  2007-10-17 07:16:14  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.5  2007-10-17 06:41:52  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.4  2007-10-17 00:16:53  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.3  2007-10-16 17:26:47  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.2  2007-10-15 05:58:21  lmh5282
 *
 *     Revision 1.1  2007-10-13 19:16:02  lmh5282
 *     just startin out
 *
 */
public class EventListener {
    
// MAIN FIELDS
    private SECalendar cal;
    private GUI gui;
    private Appointment defaultAppointment;
    private int currentView;
    private int appIndex;
// DEFAULT APPOINTMENT VARIABLES
    private String defaultTitle = "Title";
    private String defaultLocation = "Location";
    private String defaultDescription = "Description";
    private long defaultStart = 1100;
    private long defaultEnd = 1300;
    private int defaultN = 3;
    private int defaultPriority = 0;
    private int defaultTypeOfRecurring = 0;
    private boolean defaultFullDay = false;
    private boolean defaultRecurring = false;
    private boolean defaultConfirmed = false;
    private boolean defaultEmailAlarm = false;
    private boolean defaultVisibleAlarm = false;
    private boolean[] defaultDaysOfWeek = {false, false, false, false, false, false, false};
    private ArrayList<String> defaultExceptList = new ArrayList<String>();
    private ArrayList<String> defaultConfig = new ArrayList<String>();
// EMAIL ALERT FIELDS
    private String from = "username@gmail.com";
    private String password = "password";
    private String smtp = "smtp.gmail.com";
    private String port = "587";
    private String recpt = "username@email.com";
    
/*
 *  the main class where everything takes place.
 *  constructor initializes main field values, sets up 
 *  a default config file if on is not present, and makes 
 *  a default appointment based on the config file.
 *  
 *  @param   gui    The gui to be attached to.
 */
    public EventListener(GUI gui) {
// MAIN CONSTRUCTION
        this.gui = gui;
        cal = new SECalendar();
        cal.setCurrentDate(cal.getToday());
        currentView = 1;
        appIndex = -1;
// BUILD CONFIG
        defaultConfig.add("calFile");
        defaultConfig.add(defaultTitle);
        defaultConfig.add(defaultStart + "");
        defaultConfig.add(defaultEnd + "");
        defaultConfig.add(defaultFullDay + "");
        defaultConfig.add(defaultLocation);
        defaultConfig.add(defaultPriority + "");
        defaultConfig.add(defaultDescription);
        defaultConfig.add(defaultVisibleAlarm + "");
        defaultConfig.add(defaultEmailAlarm + "");
        defaultConfig.add(defaultConfirmed + "");
        defaultConfig.add("30");
        defaultConfig.add(from);
        defaultConfig.add(password);
        defaultConfig.add(smtp);
        defaultConfig.add(port);
        defaultConfig.add(recpt);       
        if(cal.getConfig().size() < 17){
            cal.updateConfig(defaultConfig);
        }
        buildDefaultApp();
    } 
/*
 * method for gui to call computeFirstOfMonth()
 */
// CAL ACCESSORS
    public int firstOfMonth() {
        return cal.computeFirstOfMonth();
    }
/*
 * method for gui to call getCurrentDate()
 */
    public String getSelectedDate() {
        return cal.getCurrentDate();
    }
/*
 * method for gui to call getToday()
 */
    public String getToday() {
        return cal.getToday();
    }
/*
 * method for gui to call viewApp()
 */
    public ArrayList<Appointment> viewApp(String date) {
        return cal.viewApp(date);
    }
/*
 * simple method to return the number of days in a specified month of a specified year
 */
    public int daysInMonth(int month, int year){
        return cal.computeNumDays(month, year).length;
    }

// ACCESSORS
    public int getCurrentView() {
        return currentView;
    }
    public String getSelectedMonth(){
        String[] date = getSelectedDate().split("/");
        Integer monthInt = Integer.parseInt(date[1]);
        String month = "";
        switch (monthInt) {
            case 1: month = "January"; break;
            case 2: month = "February"; break;
            case 3: month = "March"; break;
            case 4: month = "April"; break;
            case 5: month = "May"; break;
            case 6: month = "June"; break;
            case 7: month = "July"; break;
            case 8: month = "August"; break;
            case 9: month = "September"; break;
            case 10: month = "October"; break;
            case 11: month = "November"; break;
            case 12: month = "December"; break;
            default: month = ""; break;
        }
        return month;
    }
/*
 *  returns the sunday of whichever week you are currently in
 */
    public String getSelectedWeek(){
        String[] temp = getSelectedDate().split("/");
        int[] date = new int[3];
        for(int i = 0; i < 3; i++){
            date[i] = Integer.parseInt(temp[i]);
        }
        int tempday = date[2];
        int start = cal.computeFirstOfMonth();
        start += (tempday % 7) + 5;
        tempday = date[2] - start;
        while(tempday <= date[2]-7){
            tempday += 7;
        }
        if(tempday <= 0){
            if(date[1] == 1){
                date[1] = 12;
                date[0]--;
            }else{
                date[1]--;
            }
            tempday += daysInMonth(date[1], date[0]);
        }
        return date[0] + "/" + date[1] + "/" + tempday;
    }
    public int getSelectedIndex(){
        return appIndex;
    }
    public Appointment getSelectedAppointment(){
        Appointment retVal;
        if (appIndex != -1 && gui.getFromIndex(appIndex) != null) {
            retVal = gui.getFromIndex(appIndex);
        } else {
            retVal = defaultAppointment;
        }
        return retVal;
    }
    public int[] getNumDays() {
        String[] date = cal.getCurrentDate().split("/");
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[0]);
        return cal.computeNumDays(month, year);
    }

// CONFIG ACCESSORS 
    public int getNumMinutes(){
        return Integer.parseInt(cal.getConfig().get(11));
    }
    public String getFrom(){
        return cal.getConfig().get(12);
    }
    public String getPassword(){
        return cal.getConfig().get(13);
    }
    public String getSmtp(){
        return cal.getConfig().get(14);
    }
    public String getPort(){
        return cal.getConfig().get(15);
    }
    public String getRecpt(){
        return cal.getConfig().get(16);
    }

// LISTENERS
    class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
// MAIN BUTTONS
            if (event.getActionCommand().equals("New")) {
                if(!cal.isModified() || savePopup()){
                    cal = new SECalendar();
                    gui.refreshAll();
                }
            } else if (event.getActionCommand().equals("Open...")) {
                if(!cal.isModified() || savePopup()){
                    JFrame frame = new JFrame("Open");
                    JFileChooser popup = new JFileChooser();
                    popup.showOpenDialog(frame);
                    if (popup.getSelectedFile() != null) {
                        cal.importCalendar((popup.getSelectedFile()).getPath());
                    }
                }
                gui.refreshAll();
            } else if (event.getActionCommand().equals("Save")) {
                JFrame frame = new JFrame("Save");
                JFileChooser popup = new JFileChooser();
                popup.showSaveDialog(frame);
                if (popup.getSelectedFile() != null) {
                    cal.exportCalendar((popup.getSelectedFile()).getPath());
                }
                gui.refreshAll();
            } else if (event.getActionCommand().equals("Goto Today")) {
                cal.setCurrentDate(cal.getToday());
                gui.refreshAll();
            } else if (event.getActionCommand().equals("Goto Date...")) {
                new goToDate();
            } else if (event.getActionCommand().equals("Month View")) {
                currentView = 1;
                gui.refreshAll();
            } else if (event.getActionCommand().equals("Week View")) {
                currentView = 2;
                gui.refreshAll();
            } else if (event.getActionCommand().equals("Day View")) {
                currentView = 3;
                gui.refreshAll();
            } else if (event.getActionCommand().equals("Preferences")) {
                new prefPopup();
            } else if (event.getActionCommand().equals("Exit")) {
                if(!cal.isModified() || savePopup()){
                    gui.exit();
                }
            }
            // APPOINTMENT FIELDS
            else if (event.getActionCommand().equals("comboBoxChanged")) {
                gui.rBoxChanged();
            }else if (event.getActionCommand().equals("wholedayBox")) {
                gui.toggleFullDay();
            }else if (event.getActionCommand().equals("visAlarmBox")) {
                gui.toggleVisibleAlarm();
            }else if (event.getActionCommand().equals("emailAlarmBox")) {
                gui.toggleEmailAlarm();
            }else if (event.getActionCommand().equals("recurringBox")) {
                gui.toggleRecurring();
            }else if(event.getActionCommand().equals("singleInstanceBox")){
                gui.toggleRecurring();
            }else if (event.getActionCommand().equals("confirmCheckBox")) {
                gui.toggleConfirm();
            }else if (event.getActionCommand().equals("Sunday")) {
                gui.toggleSunday();
            }else if (event.getActionCommand().equals("Monday")) {
                gui.toggleMonday();
            }else if (event.getActionCommand().equals("Tuesday")) {
                gui.toggleTuesday();
            }else if (event.getActionCommand().equals("Wednesday")) {
                gui.toggleWednesday();
            }else if (event.getActionCommand().equals("Thursday")) {
                gui.toggleThursday();
            }else if (event.getActionCommand().equals("Friday")) {
                gui.toggleFriday();
            }else if (event.getActionCommand().equals("Saturday")) {
                gui.toggleSaturday();
            }
        }
    }
// APPOINTMENT BUTTONS
    class appointmentButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            boolean error = false;
            if (event.getActionCommand().equals("New")) {
                appIndex = -1;
            } 
            else if (event.getActionCommand().equals("Delete")) {
                if(gui.getFromIndex(appIndex) != null){
                    appIndex = gui.getSideList().getSelectedIndex();
                    Appointment app = gui.getFromIndex(appIndex);
                    if (app.getRecurring() && !gui.isRecurring()) {
                        cal.removeSpecific(app, gui.getDateField());
                    } else {
                        cal.removeApp(app);
                    }
                    appIndex = -1;
                }
                else{
                    popup("Please select an appointment.");
                }
            } 
            else if (event.getActionCommand().equals("Save")) {
                //error checking
                if(gui.getTitleField().equals("")){
                    error = true;
                    popup("No Title.");
                }
                int n = -1;
                try{
                    n = Integer.parseInt(gui.getNEqualsField());
                }catch(Exception e){
                    n = -1;
                }
                if(gui.isRecurring() && (gui.getRecurBox() != 0 && n <= 0)){
                    error = true;
                    popup("N must be a number greater than 0.");
                }
                else if(gui.isRecurring() && (gui.getRecurBox() == 3 && n > 5)){
                    error = true;
                    popup("No month has more than 5 weeks");
                }
                else if(gui.isRecurring() && (gui.getRecurBox() == 2 && n > 31)){
                    error = true;
                    popup("No month has more than 31 days");
                }
                else if(gui.isRecurring() && (gui.getRecurBox() == 0 || gui.getRecurBox() == 3)){
                    if(!gui.daysChecked()){
                        error = true;
                        popup("No days checked for recurring.");
                    }
                }
                try{
                    String temp;
                    temp = gui.getDateField();
                    String[] date = temp.split("/");
                    int[] date2 = new int[3];
                    for(int i = 0; i < 3; i++){
                        date2[i] = Integer.parseInt(date[i]);
                    }
                    if(date2[1] < 1 || date2[1] > 12){
                        error = true;
                        popup("Month must be between 1 and 12.");
                    }
                    else if(date2[2] > daysInMonth(date2[1], date2[0])){
                        error = true;
                        popup("Selected month only has " + daysInMonth(date2[1], date2[0]) + " days.");
                    }
                }catch(Exception e){
                    error = true;
                    popup("Date not formatted correctly. \n Year/Month/Day \n" + 
                            "Month must be between 1 and 12. \n" + 
                            "Day must exist in selected month.");
                }
                if(!gui.getFullDay()){
                    try{
                        if(gui.getStartTimeField() >= gui.getEndTimeField()){
                            error = true;
                            popup("Start time cannot come before end time.");
                        }
                        else if(gui.getStartTimeField() < 0 || gui.getEndTimeField() < 0){
                            error = true;
                            popup("Cannot have negative times.");
                        }
                        else if(gui.getStartTimeField() > 2400 || gui.getEndTimeField() > 2400){
                            error = true;
                            popup("Cannot have times greater than 24:00");
                        }
                    }catch(Exception e){
                        error = true;
                        popup("The time is not properly formatted. \n HH:MM");
                    }
                }
                long stime = 0;
                long etime = 0;
                try{
                    stime = gui.getStartTimeField();
                    etime = gui.getEndTimeField();
                    
                }catch(Exception e){
                    stime = Integer.parseInt(cal.getConfig().get(2));
                    etime = Integer.parseInt(cal.getConfig().get(3));
                }
                if(!error){
                    Appointment app = new Appointment( gui.getTitleField(), gui.getDateField(), 
                            gui.getLocationField(), gui.getPriority(), stime, etime,
                            gui.getDescriptionField(), gui.isRecurring(), gui.getRecurBox(), gui.gatherDays(),
                            new ArrayList<String>(), Integer.parseInt(gui.getNEqualsField()), gui.getFullDay(),
                            gui.getConfirm(), gui.getEmailAlarm(), gui.getVisibleAlarm());
                    if(appIndex != -1){
                        Appointment selectedApp = gui.getFromIndex(appIndex);
                        cal.saveEditedApp(selectedApp, app);
                    }else{
                        cal.addAppointment(app);
                    }
                    appIndex = -1;
                    gui.resetSideList();
                }
            }
            if(!error){
                gui.refreshAll();
                gui.resetSideList();
                gui.resetNoteList(appIndex);
            }
        }
    }
// NOTE BUTTONS
    class noteButtonListener implements ActionListener {
        Appointment app;
        Note note;
        JFrame frame;
        JPanel notePanel;
        JLabel noteTitle, appHost, appointment, noteDesc;
        JTextField title;
        JTextArea desc;
        JScrollPane descPane;
        JButton save, cancel;
        
        public noteButtonListener() {
            frame = new JFrame("Note");
            notePanel = new JPanel();
            appointment = new JLabel("Host");
            note = new Note();
            noteTitle = new JLabel("Title: ");
            noteDesc = new JLabel("Description: ");
            appHost = new JLabel("Appointment: ");
            title = new JTextField ("Title");
            desc = new JTextArea("Description");
            desc.setLineWrap (true);
            descPane = new JScrollPane(desc);
            save = new JButton("Save");
            cancel = new JButton("Cancel");
            save.addActionListener(this);
            cancel.addActionListener (this);
            notePanel.setLayout(null);
            
            noteTitle.setBounds(10,10,50,50);
            title.setBounds(100,23,125,20);
            appHost.setBounds(10,50,200,20);
            appointment.setBounds(100,50,100,20);
            noteDesc.setBounds(10,60,100,50);
            descPane.setBounds(100,80,125,125);
            save.setBounds(10,150,75,25);
            cancel.setBounds(10,180,75,25);
            
            notePanel.add(noteTitle);
            notePanel.add(title);
            notePanel.add(appHost);
            notePanel.add(appointment);
            notePanel.add(noteDesc);
            notePanel.add(descPane);
            notePanel.add(save);
            notePanel.add(cancel);
            
            frame.add(notePanel);
            frame.setResizable(false);
            frame.setBounds(500, 250, 250, 250);
            frame.setVisible(false);
        }

        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("New")) {
                app = gui.getFromIndex(appIndex);
                if(app != null){
                    title.setText("Title");
                    appointment.setText(app.getTitle());
                    desc.setText("Description");
                    frame.setVisible(true);
                    gui.getNoteList().clearSelection();
                }
                else{
                    popup("Please select an appointment.");
                }
            }
            else if (event.getActionCommand().equals("View")) {
                app = gui.getFromIndex(appIndex);
                if(app != null){
                    note = gui.noteIndex(gui.getNoteList().getSelectedIndex());
                    if(note != null){
                        title.setText(note.getTitle());
                        appointment.setText(app.getTitle());
                        desc.setText(note.getDescription());
                        frame.setVisible(true);
                    }
                    else{
                        popup("Please select a note to view.");
                    }
                }
                else{
                    popup("Please select an appointment.");
                }
            }
            else if (event.getActionCommand().equals("Delete")) {
                app = gui.getFromIndex(appIndex);
                if(app != null){
                    note = gui.noteIndex(gui.getNoteList().getSelectedIndex());
                    if(note != null){
                        app.deleteNote(note);
                    }
                    else{
                        popup("Please select a note to delete.");
                    }
                }
                else{
                    popup("Please select an appointment.");
                }
                gui.refreshAll();
            }
            else if(event.getActionCommand().equals("Cancel")){
                frame.setVisible(false);
                gui.refreshAll();
            }
            else if(event.getActionCommand ().equals("Save")){
                app = gui.getFromIndex(appIndex);
                if(!title.getText().equals("")){
                    note = new Note(title.getText(), desc.getText(), app);
                    if(gui.getNoteList().getSelectedIndex() != -1){
                        app.deleteNote(gui.noteIndex(gui.getNoteList().getSelectedIndex()));
                    }
                    app.addNote(note);
                    frame.setVisible(false);
                }
                else{
                    popup("No Title.");
                }
                gui.refreshAll();
            }
        }
    }

// JLIST LISTENER
    class listListener implements MouseListener {
        private JList list;
        public listListener(JList list) {
            this.list = list;
        }

        public void mouseClicked(MouseEvent event) {
            if (list.getName().equals("noteList")) {
                if (event.getClickCount() == 2) {
                    JButton temp = new JButton("View");
                    temp.addActionListener(new noteButtonListener());
                    temp.doClick();
                }
            } else if (list.getName().equals("sideList")) {
                appIndex = gui.getSideList().getSelectedIndex();
                gui.refreshAll();
            }
        }
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        public void mousePressed(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
    }
    class dayListener implements MouseListener {
        JPanel day;
        String newCurrent;
        
        public dayListener(JPanel day) {
            this.day = day;
        }

        public void mouseClicked(MouseEvent event) {
            String[] temp = getSelectedDate().split("/");
            int[] date = new int[3];
            for(int i = 0; i < 3; i++){
                date[i] = Integer.parseInt(temp[i]);
            }
// MONTH VIEW
            if (day.getName().equals("nextMonth")) {
                date[1]++;
                if(date[1] == 13){
                    date[1] = 1;
                    date[0]++;
                }
                if(date[2] >= daysInMonth(date[1], date[0])){
                    date[2] = daysInMonth(date[1], date[0]);
                }
                if(date[1] == 2 && date[2] > 28){
                    if(cal.isLeapYear(date[0])){
                        date[2] = 29;
                    }
                    else{
                        date[2] = 28;
                    }
                }
                newCurrent = (date[0] + "/" + date[1] + "/" + date[2]);
            } 
            else if (day.getName().equals("prevMonth")) {
                date[1]--;
                if(date[1] == 0){
                    date[1] = 12;
                    date[0]--;
                }
                if(date[2] >= daysInMonth(date[1], date[0])){
                    date[2] = daysInMonth(date[1], date[0]);
                }
                if(date[1] == 2 && date[2] > 28){
                    if(cal.isLeapYear(date[0])){
                        date[2] = 29;
                    }
                    else{
                        date[2] = 28;
                    }
                }
                newCurrent = (date[0] + "/" + date[1] + "/" + date[2]);
            }
// WEEK VIEW
            else if (day.getName().equals("prevWeek")) {
                date[2] -= 7;
                if(date[2] < 1){
                    date[1]--;
                    date[2] += daysInMonth(date[1], date[0]);
                    if(date[1] < 1){
                        date[0]--;
                        date[1] = 12;
                    }
                }
                newCurrent = date[0] + "/" + date[1] + "/" + date[2];
            }
            else if (day.getName().equals("nextWeek")) {
                date[2] += 7;
                int month = date[1];
                if(date[2] > daysInMonth(month, date[0])){
                    date[1]++;
                    date[2] -= daysInMonth(month, date[0]);
                    if(date[1] > 12){
                        date[0]++;
                        date[1] = 1;
                    }
                }
                newCurrent = date[0] + "/" + date[1] + "/" + date[2];
            }
// DAY VIEW
            else if(day.getName().equals("Previous Day")){
                date[2]--;
                if(date[2] == 0){
                    date[1]--;
                    if(date[1] == 0){
                        date[1] = 12;
                        date[0]--;
                    }
                    date[2] = daysInMonth(date[1], date[0]);
                }
                newCurrent = date[0] + "/" + date[1] + "/" + date[2];
            }else if(day.getName().equals("Next Day")){
                
                date[2]++;
                if(date[2] > daysInMonth(date[1], date[0])){
                    date[2] = 1;
                    date[1]++;
                }
                if(date[1] == 13){
                    date[1] = 1;
                    date[0]++;
                }
                newCurrent = date[0] + "/" + date[1] + "/" + date[2];
            }
// DAY
            else{
                newCurrent = day.getName();
                if(event.getClickCount() == 2){
                    currentView = 3;
                }
            }
            cal.setCurrentDate(newCurrent);
            appIndex = -1;
            gui.refreshAll();
        }
  
        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }
    class appListener implements MouseListener {
        JPanel panel;
        public appListener(JPanel panel){
            this.panel = panel;
        }
        public void mouseClicked(MouseEvent arg0) {
            String[] date = panel.getName().split("/");
            appIndex = Integer.parseInt(date[3]);
            gui.refreshAll();
        }
        public void mouseEntered(MouseEvent arg0) {}
        public void mouseExited(MouseEvent arg0) {}
        public void mousePressed(MouseEvent arg0) {}
        public void mouseReleased(MouseEvent arg0) {}
    }
    class myWindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent event){
            if(cal.isModified() && savePopup()){
                gui.exit();
                
            }
        }
    }
//PRIVATE CLASSES AND METHODS
    private boolean savePopup(){
        int answer = JOptionPane.showConfirmDialog(null, "Changes were found. \n Would you like to save?", "Save?", JOptionPane.YES_NO_CANCEL_OPTION);
        if(answer == 2){
            return false;
        }
        if(answer == 0){
            JButton temp = new JButton("Save");
            temp.addActionListener(new buttonListener());
            temp.doClick();
        }
        return true;
    }
    private void buildDefaultApp(){
        ArrayList<String> config = cal.getConfig();
        defaultAppointment = new Appointment(config.get(1), getSelectedDate(),
                config.get(5), Integer.parseInt(config.get(6)), Integer.parseInt(config.get(2)), 
                Integer.parseInt(config.get(3)), config.get(7), defaultRecurring, 
                defaultTypeOfRecurring, defaultDaysOfWeek, defaultExceptList, 
                defaultN, isTrue(config.get(4)), isTrue(config.get(10)), isTrue(config.get(9)), 
                isTrue(config.get(8)));
    }
    private boolean isTrue(String str){
        if(str.equals("true")){
            return true;
        }
        return false;
    }
    private void popup(String str){
        JOptionPane.showMessageDialog(null, str, "Error",JOptionPane.INFORMATION_MESSAGE);
    }
    private class goToDate implements ActionListener{
        JFrame frame;
        Container container;
        JComboBox year;
        JComboBox month;
        JComboBox day;
        JLabel label;
        JPanel slash3;
        JPanel slash4;
        JLabel slash;
        JLabel slash2;
        JButton ok;
        String[]today;
        public goToDate(){
            frame = new JFrame("Enter Date...");
            container = frame.getContentPane();
            container.setLayout(new GridLayout(1,0,5,5));
            today = cal.getToday().split("/");
            year = new JComboBox();
            for(int i = -50; i < 50; i++){
                year.addItem(i + Integer.parseInt(today[0]));
            }
            year.setSelectedItem(Integer.parseInt(today[0]));
            year.addActionListener(this);
            year.setActionCommand("year");
            month = new JComboBox(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
            month.setSelectedItem(Integer.parseInt(today[1]));
            month.addActionListener(this);
            label = new JLabel("Enter Date:");
            slash3 = new JPanel();
            slash4 = new JPanel();
            slash = new JLabel("/");
            slash2 = new JLabel("/");
            slash3.add(slash);
            slash4.add(slash2);
            ok = new JButton("OK");
            ok.addActionListener(this);
            draw();
            day.setSelectedItem(Integer.parseInt(today[2]));
        }
        public void actionPerformed(ActionEvent event){
            if(event.getActionCommand().equals("year")){
                Object temp = year.getSelectedItem();
                year = new JComboBox();
                for(int i = -50; i < 50; i++){
                    year.addItem(i + Integer.parseInt(temp + ""));
                }
                year.setSelectedItem(Integer.parseInt(temp + ""));
                year.addActionListener(this);
                year.setActionCommand("year");
            }
            if(event.getActionCommand().equals("OK")){
                cal.setCurrentDate(Integer.parseInt(year.getSelectedItem() + "") + 
                        "/" + (Integer.parseInt(month.getSelectedItem() + "")) + 
                        "/" + Integer.parseInt(day.getSelectedItem() + ""));
                frame.dispose();
                gui.resetSideList();
                gui.refreshAll();
            }
            else{
                int temp = day.getSelectedIndex();
                container.removeAll();
                draw();
                day.setSelectedIndex(temp);
            }
        }
        private void draw(){
            day = new JComboBox();
            for(int i = 1; i <= daysInMonth(month.getSelectedIndex()+1, Integer.parseInt(year.getSelectedItem()+"")); i++){
                day.addItem(i);
            }
            container.add(label);
            container.add(month);
            container.add(slash3);
            container.add(day);
            container.add(slash4);
            container.add(year);
            container.add(ok);
            frame.pack();
            frame.setLocation(200,200);
            frame.setVisible(true);
        }
    }
    private class prefPopup implements ActionListener{
        JFrame frame;
        JLabel title, STime, ETime, fullDay, location, priority, description, 
            visibleAlarm, emailAlarm, alarmTime, confirmed, from1, password1, smtp1, port1, recpt1, cal1;
        JTextField title2, STime2, ETime2, location2, description2, alarmTime2, from2, smtp2, port2, recpt2;
        JTextField password2;
        JCheckBox fullDay2, visibleAlarm2, emailAlarm2, confirmed2;
        JComboBox priority2;
        String[] prior;
        JButton save, cancel, cal2;
        String file;
        public prefPopup(){
            prior = new String[]{"Low", "Medium", "High"};
            frame = new JFrame("Preferences");
            frame.setLayout(new GridLayout(0,2,5,5));
            
            title = new JLabel("Default Title: ");
            STime = new JLabel("Default Start Time: ");
            ETime = new JLabel("Default End Time: ");
            fullDay = new JLabel("Default Full Day: ");
            location = new JLabel("Default Location: ");
            priority = new JLabel("Default Priority: ");
            description = new JLabel("Default Description: ");
            visibleAlarm = new JLabel("Default Visible Alarm: ");
            emailAlarm = new JLabel("Default Email Alarm: ");
            alarmTime = new JLabel("Alarm Warning Time");
            confirmed = new JLabel("Default Confirmed: ");
            from1 = new JLabel("From: ");
            password1 = new JLabel("Password: ");
            smtp1 = new JLabel("SMTP: ");
            port1 = new JLabel("Port: ");
            recpt1 = new JLabel("Recpt: ");
            cal1 = new JLabel("Default Calendar: ");
            
            title2 = new JTextField(defaultAppointment.getTitle());
            STime2 = new JTextField("" + defaultAppointment.getStartTime());
            ETime2 = new JTextField("" + defaultAppointment.getEndTime());
            fullDay2 = new JCheckBox();
            fullDay2.setSelected(defaultAppointment.getFullDay());
            location2 = new JTextField(defaultAppointment.getLocation());
            priority2 = new JComboBox(prior);
            priority2.setSelectedIndex(Integer.parseInt(cal.getConfig().get(6)));
            description2 = new JTextField(defaultAppointment.getDesc());
            visibleAlarm2 = new JCheckBox();
            visibleAlarm2.setSelected(defaultAppointment.getVisibleAlarm());
            emailAlarm2 = new JCheckBox();
            emailAlarm2.setSelected(defaultAppointment.getEmailAlarm());
            confirmed2 = new JCheckBox();
            confirmed2.setSelected(defaultAppointment.getConfirmed());
            alarmTime2 = new JTextField(cal.getConfig().get(11));
            from2 = new JTextField(cal.getConfig().get(12));
            password2 = new JTextField(cal.getConfig().get(13));
            smtp2 = new JTextField(cal.getConfig().get(14));
            port2 = new JTextField(cal.getConfig().get(15));
            recpt2 = new JTextField(cal.getConfig().get(16));
            
            cal2 = new JButton("Browse...");
            save = new JButton("Save");
            cancel = new JButton("Cancel");
            cal2.addActionListener(this);
            save.addActionListener(this);
            cancel.addActionListener(this);
            
            frame.add(title);
            frame.add(title2);
            frame.add(STime);
            frame.add(STime2);
            frame.add(ETime);
            frame.add(ETime2);
            frame.add(fullDay);
            frame.add(fullDay2);
            frame.add(location);
            frame.add(location2);
            frame.add(priority);
            frame.add(priority2);
            frame.add(description);
            frame.add(description2);
            frame.add(visibleAlarm);
            frame.add(visibleAlarm2);
            frame.add(emailAlarm);
            frame.add(emailAlarm2);
            frame.add(confirmed);
            frame.add(confirmed2);
            frame.add(alarmTime);
            frame.add(alarmTime2);
            frame.add(from1);
            frame.add(from2);
            frame.add(password1);
            frame.add(password2);
            frame.add(smtp1);
            frame.add(smtp2);
            frame.add(port1);
            frame.add(port2);
            frame.add(recpt1);
            frame.add(recpt2);
            frame.add(cal1);
            frame.add(cal2);
            frame.add(save);
            frame.add(cancel);
            
            frame.pack();
            frame.setLocation(400,400);
            frame.setVisible(true);
        }
        public void actionPerformed(ActionEvent event) {
            if(event.getActionCommand().equals("Save")){
                boolean error = false;
                if(title2.getText().equals("")){
                    error = true;
                    popup("Must have title.");
                }
                else if(from2.getText().equals("")){
                    error = true;
                    popup("Must have email address to send from.");
                }
                else if(password2.getText().equals("")){
                    error = true;
                    popup("Must have password for email address.");
                }
                else if(smtp2.getText().equals("")){
                    error = true;
                    popup("Must have SMTP to send email.");
                }
                else if(password2.getText().equals("")){
                    error = true;
                    popup("Must have password for email address.");
                }
                else if(recpt2.getText().equals("")){
                    error = true;
                    popup("Must have recipient email address.");
                }
                try{
                    if(Integer.parseInt(STime2.getText()) > Integer.parseInt(ETime2.getText())){
                        error = true;
                        popup("Start time cannot come before end time.");
                    }
                    else if(Integer.parseInt(STime2.getText()) < 0 || Integer.parseInt(ETime2.getText()) < 0){
                        error = true;
                        popup("Cannot have negative times.");
                    }
                    else if(Integer.parseInt(STime2.getText()) > 2400 || Integer.parseInt(ETime2.getText()) > 2400){
                        error = true;
                        popup("Cannot have times greater than 24:00");
                    }
                }catch(Exception e){
                    error = true;
                    popup("The time is not properly formatted. \n HHMM   -   No Colon");
                }
                try{
                    if(Integer.parseInt(port2.getText()) < 0){
                        error = true;
                        popup("Port number cannot be negative.");
                    }
                    else if(Integer.parseInt(port2.getText()) > 65535){
                        error = true;
                        popup("Port number cannot be greater than 65535.");
                    }
                    else if(port2.getText().equals("")){
                        error = true;
                        popup("Must have a port number.");
                    }
                }catch(Exception e){
                    error = true;
                    popup("Port number must be a number between 0 and 65535");
                }
                try{
                    if(alarmTime2.getText().equals("")){
                        error = true;
                        popup("Must have alarm warning time.");
                    }
                    else if(Integer.parseInt(alarmTime2.getText()) < 0){
                        error = true;
                        popup("Alarm warning time must be greater than 0");
                    }
                }catch(Exception e){
                    error = true;
                    popup("Alarm warning time must be a number greater than 0.");
                }
                /*if(title2.getText().contains(" ") || location2.getText().contains(" ") || 
                        description2.getText().contains(" ") || from2.getText().contains(" ") ||
                        password2.getText().contains(" ") || smtp2.getText().contains(" ") || 
                        recpt2.getText().contains(" ")){
                    error = true;
                    popup("Please, No Spaces.");
                }*/
                if(!error){
                    ArrayList<String> newConfig = new ArrayList<String>();
                    newConfig.add(file);
                    newConfig.add(title2.getText());
                    newConfig.add(STime2.getText());
                    newConfig.add(ETime2.getText());
                    newConfig.add(fullDay2.isSelected() + "");
                    newConfig.add(location2.getText());
                    newConfig.add(priority2.getSelectedIndex() + "");
                    newConfig.add(description2.getText());
                    newConfig.add(visibleAlarm2.isSelected() + "");
                    newConfig.add(emailAlarm2.isSelected() + "");
                    newConfig.add(confirmed2.isSelected() + "");
                    newConfig.add(alarmTime2.getText());
                    newConfig.add(from2.getText());
                    newConfig.add(password2.getText());
                    newConfig.add(smtp2.getText());
                    newConfig.add(port2.getText());
                    newConfig.add(recpt2.getText());
                    cal.updateConfig(newConfig);
                    buildDefaultApp();
                    frame.dispose();
                }
            }
            else if(event.getActionCommand().equals("Cancel")){
                frame.dispose();
            }
            else if(event.getActionCommand().equals("Browse...")){
                JFrame frame = new JFrame("Open");
                JFileChooser popup = new JFileChooser();
                popup.showOpenDialog(frame);

                if (popup.getSelectedFile() != null) {
                    file = popup.getSelectedFile().getPath();
                }
            }
            gui.refreshAll();
        }
    }
}