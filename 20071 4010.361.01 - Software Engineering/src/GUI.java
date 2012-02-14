/*
 * GUI.java
 *
 * Version:
 *     $Id: GUI.java,v 1.127 2007-11-06 20:34:38 lmh5282 Exp $
 *
 * Revisions:
 *     $Log: GUI.java,v $
 *     Revision 1.127  2007-11-06 20:34:38  lmh5282
 *     *** empty log message ***
 *
 *     Revision 1.126  2007-11-06 05:21:23  lmh5282
 *     fixed refreshing
 *
 *     Revision 1.125  2007-11-06 00:57:46  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.124  2007-11-06 00:50:14  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.123  2007-11-06 00:40:26  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.122  2007-11-06 00:37:05  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.121  2007-11-05 23:58:38  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.120  2007-11-05 22:28:31  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.119  2007-11-05 22:18:31  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.118  2007-11-05 22:09:49  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.117  2007-11-05 21:42:47  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.116  2007-11-05 21:07:52  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.115  2007-11-05 20:47:35  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.114  2007-11-05 20:07:23  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.113  2007-11-05 18:38:43  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.112  2007-11-05 18:33:22  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.111  2007-11-05 18:33:07  hgt9090
 *     Day View is fully functional with overlapping appointments, multiple appointments, priorites, and full day appointments. :)
 *
 *     Revision 1.110  2007-11-05 18:23:28  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.109  2007-11-05 18:18:18  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.108  2007-11-05 16:24:44  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.107  2007-11-05 07:12:43  hgt9090
 *     Fixed and re-implemented Day View, time slices, full day appointments, mutliple and multiple appointments work correctly now.
 *
 *     Revision 1.106  2007-11-05 00:55:53  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.105  2007-11-05 00:54:46  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.104  2007-11-05 00:38:48  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.103  2007-11-04 23:48:55  hgt9090
 *     This is the version of GUI.java with working Day View! :D
 *
 *     Revision 1.102  2007-11-04 23:47:58  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.101  2007-11-04 23:46:08  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.100  2007-11-04 23:34:49  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.99  2007-11-04 23:23:08  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.98  2007-11-04 23:21:18  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.97  2007-11-04 23:17:55  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.96  2007-11-04 23:12:47  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.95  2007-11-04 23:08:40  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.94  2007-11-04 21:32:27  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.93  2007-11-04 21:31:08  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.92  2007-11-04 20:44:56  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.91  2007-11-04 20:44:31  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.90  2007-11-04 19:50:46  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.89  2007-11-04 18:33:14  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.88  2007-11-04 18:15:51  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.87  2007-11-04 18:15:27  vxj4346
 *     hgh
 *
 *     Revision 1.86  2007-11-04 18:02:52  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.85  2007-11-04 17:55:25  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.84  2007-11-04 17:19:21  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.83  2007-11-04 17:18:00  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.82  2007-11-04 17:12:22  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.81  2007-11-03 22:50:37  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.80  2007-11-03 22:38:22  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.79  2007-11-03 21:33:59  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.78  2007-11-03 21:28:51  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.77  2007-11-03 21:02:51  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.76  2007-11-03 21:00:41  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.75  2007-11-03 20:57:56  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.74  2007-11-03 20:08:37  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.73  2007-11-03 18:41:08  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.72  2007-11-03 18:36:11  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.71  2007-11-03 18:17:48  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.70  2007-11-01 22:05:53  hgt9090
 *     DayView is independent of what day is picked
 *
 *     Revision 1.69  2007-11-01 21:55:58  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.68  2007-10-29 02:52:51  hgt9090
 *     added the Day View, next day and previous functions for day view, appointment viewing, look at Event Listener for more information.
 *
 *     Revision 1.67  2007-10-24 17:08:35  hgt9090
 *     Implemented Day View and worked on Priorites
 *
 *     Revision 1.66  2007-10-24 16:51:56  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.65  2007-10-24 15:25:00  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.64  2007-10-24 15:17:18  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.63  2007-10-24 14:22:23  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.62  2007-10-23 23:57:52  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.61  2007-10-23 23:45:41  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.60  2007-10-23 23:12:11  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.59  2007-10-23 22:18:48  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.58  2007-10-23 05:16:32  hgt9090
 *     Began implementing Day View. Day View will allow the users to view their appointments on a given day in an organized fashion.
 *
 *     Revision 1.57  2007-10-22 21:26:25  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.56  2007-10-22 21:09:53  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.55  2007-10-22 21:06:18  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.54  2007-10-22 20:56:24  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.53  2007-10-22 20:52:26  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.52  2007-10-22 20:45:42  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.51  2007-10-22 20:19:41  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.50  2007-10-22 20:13:08  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.49  2007-10-22 19:27:42  hgt9090
 *     This is the GUI edit for Week View
 *
 *     Revision 1.48  2007-10-22 17:34:53  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.47  2007-10-22 17:30:24  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.46  2007-10-22 16:57:37  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.45  2007-10-22 16:49:14  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.44  2007-10-22 16:40:25  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.43  2007-10-22 16:22:05  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.42  2007-10-22 16:09:55  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.41  2007-10-22 16:04:12  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.40  2007-10-22 15:04:57  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.39  2007-10-22 01:47:31  vxj4346
 *     gui commit
 *
 *     Revision 1.38  2007-10-21 23:44:48  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.37  2007-10-21 23:33:51  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.36  2007-10-21 23:21:57  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.35  2007-10-21 23:15:26  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.34  2007-10-21 22:47:06  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.33  2007-10-21 21:49:54  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.32  2007-10-21 21:41:53  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.31  2007-10-21 21:03:20  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.30  2007-10-21 20:33:40  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.28  2007-10-21 17:24:43  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.27  2007-10-21 17:18:33  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.26  2007-10-21 06:53:36  hgt9090
 *     Worked on displaying the first day of the month correctly.
 *
 *     Revision 1.25  2007-10-20 23:27:40  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.24  2007-10-20 23:16:24  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.23  2007-10-20 22:49:23  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.22  2007-10-20 22:47:59  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.21  2007-10-20 22:45:31  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.20  2007-10-20 22:42:35  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.19  2007-10-20 21:54:14  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.18  2007-10-20 21:34:28  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.17  2007-10-20 20:43:33  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.16  2007-10-20 18:50:13  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.15  2007-10-20 18:43:22  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.14  2007-10-20 18:29:38  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.13  2007-10-20 18:17:41  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.12  2007-10-20 17:39:37  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.11  2007-10-20 17:24:56  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.10  2007-10-20 17:23:41  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.9  2007-10-20 17:19:47  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.8  2007-10-20 16:20:26  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.7  2007-10-20 16:16:52  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.6  2007-10-20 16:13:41  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.5  2007-10-20 16:11:55  ges7506
 *     *** empty log message ***
 *
 *     Revision 1.4  2007-10-20 15:11:40  ges7506
 *     *** empty log message ***
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * This is the GUI.
 *
 * @author Gabriel Smith
 * @author Vaidotas Jasinevicius
 * @author Henryk Tunguz
 * 
 */
public class GUI extends SingleFrameApplication {
	private JPanel contentPanel, topPanel, toolBarPanel, bottomPanel,
		sidePanel, mainPanel, bottomAptPanel3, bottomDeletePanel,
		bottomAptPanel2, bottomSavePanel, newAptPanel2, newAptPanel1,
		deleteAptPanel, saveAptPanel, newAptPanel, deleteNotePanel,
		viewNotePanel, newNotePanel, titleMonthPanel, titleWeekPanel, satPanel,
		friPanel, thursPanel, wedPanel, tuesPanel, monPanel, sunPanel,
		weekLabelPanel, mainTitlePanel, sideTitlePanel, noteListButtons,
		bottomList, bottomText, bottomButtons, sundayPanel, mondayPanel,
		tuesdayPanel, wednesdayPanel, thursdayPanel, fridayPanel, saturdayPanel,
		weekDays, weekGridNumbers;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem jMenuNew, jMenuOpen, jMenuSave, jMenuCopy, jMenuCut, 
    	jMenuPaste, jMenuPref, jMenuExit, jMenuDelete;
    private JButton dayButton, weekButton, monthButton, dateButton, todayButton,
    	noteListNew, noteListView, noteListDelete, bottomDelete, bottomSave,
    	bottomNew, newButton, openButton, saveButton;
    private JCheckBox singleInstanceBox, confirmCheckBox, satBox, friBox,
    	thursBox, wedBox, tuesBox, monBox, sunBox, recurringBox, emailAlarmBox,
    	visAlarmBox,wholeDayBox;
    private JLabel editInstanceLabel, confirmedLabel, bottomAptLabel3,
    	bottomDeleteLabel, bottomAptLabel2, bottomSaveLabel, bottomAptLabel1,
    	bottomNewLabel, deleteNoteLabel, viewNoteLabel, newNoteLabel, nEquals,
    	typeLabel, titleMonth, titleWeek, satLabel, friLabel, thursLabel,
    	wedLabel, tuesLabel, monLabel, sunLabel, aptRecurring, emailAlarm,
    	aptDescription, aptPriority, aptLocation, aptWholeDay, aptVisAlarm,
    	aptEndTime, aptStartTime, aptDate, aptTitle, sideTitle, sideDate;
    private JTextField nEqualsField, locationField, endTimeField,
    	startTimeField, dateField, titleField;
    private JComboBox recurringChoices, priorityBox;
    private JEditorPane descriptionField;
    private JList sideList, noteList;
    private Border border;
    private JToolBar toolBar;
    
    private String weekStartDate, weekEndDate;
    
    private boolean fullBoxChecked = false;
    private boolean visBoxChecked = false;
    private boolean emailBoxChecked = false;
    private boolean recurringBoxChecked = false;
    private boolean sunBoxChecked = false;
    private boolean monBoxChecked = false;
    private boolean tuesBoxChecked = false;
    private boolean wedBoxChecked = false;
    private boolean thursBoxChecked = false;
    private boolean friBoxChecked = false;
    private boolean satBoxChecked = false;
    private boolean confirmBoxChecked = false;
    private boolean instanceBoxChecked = true;
    
    public String[] noteListString = {};
    public String[] appointmenttListString = {};
    ArrayList<Appointment> appIndex = new ArrayList<Appointment>();
    ArrayList<Note> noteIndex = new ArrayList<Note>();
    
	EventListener el = new EventListener(this);
	ActionListener buttonListener = el.new buttonListener();
	ActionListener aptButtonListener = el.new appointmentButtonListener();
	ActionListener noteButtonListener = el.new noteButtonListener();
	
	@Action
	public void open() {}
	@Action
	public void save() {}
	@Action
	public void newFile() {}
    private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }

    /* (non-Javadoc)
     * @see org.jdesktop.application.Application#startup()
     */
    @Override
    protected void startup() {
        
    	Thread alarm = new CheckAlarm(this, el.getNumMinutes(), el.getFrom(), el.getPassword(), el.getSmtp(), el.getPort(), el.getRecpt());
        alarm.start();
        getMainFrame().setResizable(true);
        //Build toolbar
        {
            topPanel = new JPanel();
            BorderLayout panelLayout = new BorderLayout();
            topPanel.setLayout(panelLayout);
            topPanel.setPreferredSize(new java.awt.Dimension(1024, 768));
            {
                toolBarPanel = new JPanel();
                topPanel.add(toolBarPanel, BorderLayout.NORTH);
                BorderLayout jPanel1Layout = new BorderLayout();
                toolBarPanel.setLayout(jPanel1Layout);
                {
                    JSeparator jSeparator = new JSeparator();
                    toolBarPanel.add(jSeparator, BorderLayout.SOUTH);
                }
                {
                	toolBar = new JToolBar();
                	toolBarPanel.add(toolBar, BorderLayout.CENTER);
                	toolBar.setPreferredSize(new java.awt.Dimension(723, 25));
                	{
                		newButton = new JButton();
                		toolBar.add(newButton);
                		newButton.setAction(getAppActionMap().get("newFile"));
                		newButton.setName("newButton");
                		newButton.setFocusable(false);
                		newButton.addActionListener(buttonListener);
                	}
                	{
                		openButton = new JButton();
                		toolBar.add(openButton);
                		openButton.setAction(getAppActionMap().get("open"));
                		openButton.setName("openButton");
                		openButton.setFocusable(false);
                		openButton.addActionListener(buttonListener);
                	}
                	{
                		saveButton = new JButton();
                		toolBar.add(saveButton);
                		saveButton.setAction(getAppActionMap().get("save"));
                		saveButton.setName("saveButton");
                		saveButton.setFocusable(false);
                		saveButton.addActionListener(buttonListener);
                	}
            		{
            			toolBar.addSeparator();
            		}
                	{
                		todayButton = new JButton();
                		toolBar.add(todayButton);
                		todayButton.setName("todayButton");
                		todayButton.addActionListener(buttonListener);
                	}
                	{
                		dateButton = new JButton();
                		toolBar.add(dateButton);
                		dateButton.setName("dateButton");
                		dateButton.addActionListener(buttonListener);
                	}
            		{
            			toolBar.addSeparator();
            		}
                	{
                		monthButton = new JButton();
                		toolBar.add(monthButton);
                		monthButton.setName("monthButton");
                		monthButton.addActionListener(buttonListener);
                	}
                	{
                		weekButton = new JButton();
                		toolBar.add(weekButton);
                		weekButton.setName("weekButton");
                		weekButton.addActionListener(buttonListener);
                	}
                	{
                		dayButton = new JButton();
                		toolBar.add(dayButton);
                		dayButton.setName("dayButton");
                		dayButton.addActionListener(buttonListener);
                	}
            		{
            			toolBar.addSeparator();
            		}
                }
            }
            {
            	contentPanel = new JPanel();
            	BorderLayout contentPanelLayout = new BorderLayout();
            	contentPanel.setLayout(contentPanelLayout);
            	topPanel.add(contentPanel, BorderLayout.CENTER);
            	{
            		//Draw the rest of the GUI
            		drawBottomPanel();
            		drawSidePanel();
            		drawMainPanel();
            	}
            }
        }
        //Build File and Edit menus
        menuBar = new JMenuBar();
        {
            fileMenu = new JMenu();
            menuBar.add(fileMenu);
            fileMenu.setName("fileMenu");
            {
                jMenuNew = new JMenuItem();
                fileMenu.add(jMenuNew);
                jMenuNew.setAction(getAppActionMap().get("newFile"));
        		jMenuNew.addActionListener(buttonListener);
            }
            {
                jMenuOpen = new JMenuItem();
                fileMenu.add(jMenuOpen);
                jMenuOpen.setAction(getAppActionMap().get("open"));
        		jMenuOpen.addActionListener(buttonListener);
            }
            {
                jMenuSave = new JMenuItem();
                fileMenu.add(jMenuSave);
                jMenuSave.setAction(getAppActionMap().get("save"));
        		jMenuSave.addActionListener(buttonListener);
            }
            {
            	JSeparator jSeparator2 = new JSeparator();
            	fileMenu.add(jSeparator2);
            }
            {
            	jMenuPref = new JMenuItem();
            	fileMenu.add(jMenuPref);
            	jMenuPref.setName("jMenuPref");
        		jMenuPref.addActionListener(buttonListener);
            }
            {
            	jMenuExit = new JMenuItem();
            	fileMenu.add(jMenuExit);
            	jMenuExit.setName("jMenuExit");
        		jMenuExit.addActionListener(buttonListener);
            }
        }
        {
            editMenu = new JMenu();
            menuBar.add(editMenu);
            editMenu.setName("editMenu");
            {
                jMenuCopy = new JMenuItem();
                editMenu.add(jMenuCopy);
                jMenuCopy.setAction(getAppActionMap().get("copy"));
            }
            {
                jMenuCut = new JMenuItem();
                editMenu.add(jMenuCut);
                jMenuCut.setAction(getAppActionMap().get("cut"));
            }
            {
                jMenuPaste = new JMenuItem();
                editMenu.add(jMenuPaste);
                jMenuPaste.setAction(getAppActionMap().get("paste"));
            }
            {
                jMenuDelete = new JMenuItem();
                editMenu.add(jMenuDelete);
                jMenuDelete.setAction(getAppActionMap().get("delete"));
            }
        }
        getMainFrame().addWindowListener(el.new myWindowListener());
        getMainFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getMainFrame().setJMenuBar(menuBar);
        show(topPanel);
    }
    /**
     * Refresh the GUI after changes made
     */
    public void refreshAll() {
    	contentPanel.remove(bottomPanel);
    	drawBottomPanel();
    	contentPanel.remove(sidePanel);
    	drawSidePanel();
    	contentPanel.remove(mainPanel);
    	drawMainPanel();
    }

    /**
     * Draws the main panel, either as day, week or month view
     */
    private void drawMainPanel() {
		mainPanel = new JPanel();
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		BorderLayout mainPanelLayout = new BorderLayout();
		mainPanel.setLayout(mainPanelLayout);
    	if (el.getCurrentView() == 1) { //Month view
    		{
    			mainTitlePanel = new JPanel();
    			BorderLayout mainTitlePanelLayout = new BorderLayout();
    			mainTitlePanel.setLayout(mainTitlePanelLayout);
    			mainPanel.add(mainTitlePanel, BorderLayout.NORTH);
    			mainTitlePanel.setPreferredSize(new java.awt.Dimension(774, 70));
    			{
    				weekLabelPanel = new JPanel();
    				GridLayout weekLabelPanelLayout = new GridLayout(1, 7);
    				mainTitlePanel.add(weekLabelPanel, BorderLayout.SOUTH);
    				weekLabelPanel.setLayout(weekLabelPanelLayout);
    				{
    					sunPanel = new JPanel();
    					weekLabelPanel.add(sunPanel);
    					{
    						sunLabel = new JLabel();
    						sunPanel.add(sunLabel);
    						sunLabel.setName("sunLabel");
    						sunLabel.setText("Sunday");
    					}
    				}
    				{
    					monPanel = new JPanel();
    					weekLabelPanel.add(monPanel);
    					{
    						monLabel = new JLabel();
    						monPanel.add(monLabel);
    						monLabel.setName("monLabel");
    						monLabel.setText("Monday");
    					}
    				}
    				{
    					tuesPanel = new JPanel();
    					weekLabelPanel.add(tuesPanel);
    					{
    						tuesLabel = new JLabel();
    						tuesPanel.add(tuesLabel);
    						tuesLabel.setName("tuesLabel");
    						tuesLabel.setText("Tuesday");
    					}
    				}
    				{
    					wedPanel = new JPanel();
    					weekLabelPanel.add(wedPanel);
    					{
    						wedLabel = new JLabel();
    						wedPanel.add(wedLabel);
    						wedLabel.setName("wedLabel");
    						wedLabel.setText("Wednesday");
    					}
    				}
    				{
    					thursPanel = new JPanel();
    					weekLabelPanel.add(thursPanel);
    					{
    						thursLabel = new JLabel();
    						thursPanel.add(thursLabel);
    						thursLabel.setName("thursLabel");
    						thursLabel.setText("Thursday");
    					}
    				}
    				{
    					friPanel = new JPanel();
    					weekLabelPanel.add(friPanel);
    					{
    						friLabel = new JLabel();
    						friPanel.add(friLabel);
    						friLabel.setName("friLabel");
    						friLabel.setText("Friday");
    					}
    				}
    				{
    					satPanel = new JPanel();
    					weekLabelPanel.add(satPanel);
    					{
    						satLabel = new JLabel();
    						satPanel.add(satLabel);
    						satLabel.setName("satLabel");
    						satLabel.setText("Saturday");
    					}
    				}
    			}
    			{
    				titleMonthPanel = new JPanel();
    				mainTitlePanel.add(titleMonthPanel, BorderLayout.CENTER);
    				{
    					JPanel prevMonthPanel = new JPanel();
    					BorderLayout prevMonthPanelLayout = new BorderLayout();
    					prevMonthPanel.setLayout(prevMonthPanelLayout);
    					JLabel prevMonth = new JLabel("<  ");
    					prevMonth.setFont(new Font("prevFont", Font.PLAIN, 24));
    					prevMonthPanel.add(prevMonth, BorderLayout.EAST);
    					prevMonthPanel.setName("prevMonth");
    					prevMonthPanel.addMouseListener(el.new dayListener(prevMonthPanel));
    					titleMonthPanel.add(prevMonthPanel);
    				}
    				{
    					String selectedMonth = el.getSelectedMonth();
    					String rawSelectedDate = el.getSelectedDate();
    			    	String[] date = rawSelectedDate.split("/");
    					titleMonth = new JLabel(selectedMonth + ", " + date[0]);
    					titleMonth.setFont(new Font("sideFont", Font.PLAIN, 30));
    					titleMonthPanel.add(titleMonth);
    				}
    				{
    					JPanel nextMonthPanel = new JPanel();
    					BorderLayout nextMonthPanelLayout = new BorderLayout();
    					nextMonthPanel.setLayout(nextMonthPanelLayout);
    					JLabel nextMonth = new JLabel("  >");
    					nextMonth.setFont(new Font("nextFont", Font.PLAIN, 24));
    					nextMonthPanel.add(nextMonth, BorderLayout.WEST);
    					nextMonthPanel.setName("nextMonth");
    					nextMonthPanel.addMouseListener(el.new dayListener(nextMonthPanel));
    					titleMonthPanel.add(nextMonthPanel);
    				}
    			}
    		}
    		JPanel dayPanel = new JPanel();
    		dayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    		dayPanel.setLayout(new GridLayout(0,7));
    		for (int i=1; i< el.firstOfMonth(); i++) {
    			dayPanel.add(new JPanel());
    		}
    		ArrayList<JPanel> dayButtons = monthView();
    		for (int i = 0; i < dayButtons.size(); i++) {
    			dayPanel.add(dayButtons.get(i));
    		}
    		mainPanel.add(dayPanel, BorderLayout.CENTER);
    	} else if (el.getCurrentView() == 2) {//Week view
    	
    		{
    			mainTitlePanel = new JPanel();
    			BorderLayout mainTitlePanelLayout = new BorderLayout();
    			mainTitlePanel.setLayout(mainTitlePanelLayout);
    			mainPanel.add(mainTitlePanel, BorderLayout.NORTH);
    			mainTitlePanel.setPreferredSize(new java.awt.Dimension(774, 70));
    			{
    				titleWeekPanel = new JPanel();
    				mainTitlePanel.add(titleWeekPanel, BorderLayout.CENTER);
    				titleWeekPanel.setPreferredSize(new java.awt.Dimension(41, 46));
    				{
    					JPanel prevWeekPanel = new JPanel();
    					BorderLayout prevWeekPanelLayout = new BorderLayout();
    					prevWeekPanel.setLayout(prevWeekPanelLayout);
    					JLabel prevWeek = new JLabel("<  ");
    					prevWeek.setFont(new Font("prevFont", Font.PLAIN, 24));
    					prevWeekPanel.add(prevWeek, BorderLayout.EAST);
    					prevWeekPanel.setName("prevWeek");
    					prevWeekPanel.addMouseListener(el.new dayListener(prevWeekPanel));
    					titleWeekPanel.add(prevWeekPanel);
    				}
    				{
    					String selectedWeek = el.getSelectedWeek();

    					String[] week = selectedWeek.split("/");
    					String weekMonth=monthToString(Integer.parseInt(week[1]));
    					int weekStartMo = Integer.parseInt(week[1]);
    					int weekStartYr = Integer.parseInt(week[0]);
    					int weekEndDay=Integer.parseInt(week[2]);
    					int weekEndMonth=Integer.parseInt(week[1]);
    					int weekEndYear=Integer.parseInt(week[0]);
    					if ((weekEndDay+6)>el.daysInMonth(weekEndMonth, weekEndYear)){
    						if ((weekEndMonth+1)!=13){
    							weekEndMonth++;
    						} else {
    							weekEndMonth=1;
    							weekEndYear++;
    						}
    						weekEndDay = weekEndDay+6-el.daysInMonth(weekStartMo, weekStartYr);
    					} else {
    						weekEndDay += 6;
    					}
    					weekStartDate = selectedWeek;
    					weekEndDate = weekEndYear +"/"+ weekEndMonth +"/"+ weekEndDay;
    					String weekEndMonthS=monthToString(weekEndMonth);
    					String weekTitleString = weekMonth + " " + week[2] + " - " + weekEndMonthS + " " + weekEndDay;
    					titleWeek = new JLabel(weekTitleString);
    					titleWeek.setFont(new Font("sideFont", Font.PLAIN, 30));
    					titleWeekPanel.add(titleWeek);
    				}
    				{
    					JPanel nextWeekPanel = new JPanel();
    					BorderLayout nextWeekPanelLayout = new BorderLayout();
    					nextWeekPanel.setLayout(nextWeekPanelLayout);
    					JLabel nextWeek = new JLabel("  >");
    					nextWeek.setFont(new Font("nextFont", Font.PLAIN, 24));
    					nextWeekPanel.add(nextWeek, BorderLayout.WEST);
    					nextWeekPanel.setName("nextWeek");
    					nextWeekPanel.addMouseListener(el.new dayListener(nextWeekPanel));
    					titleWeekPanel.add(nextWeekPanel);
    				}
    			}
    		}
    		weekView(weekStartDate, weekEndDate);
    	} else if (el.getCurrentView() == 3) {//Day view
    		dayView();
    	}
    }
    /**
     * Draw side panel with no appointment list
     */
    private void drawSidePanel() {
    	drawSidePanel(null);
    }
    /**
     * Draw side panel with an appointment list
     * 
     * @param appointments appointment list to use
     */
    private void drawSidePanel(ArrayList<Appointment> appointments) {
		sidePanel = new JPanel();
		BorderLayout sidePanelLayout = new BorderLayout();
		contentPanel.add(sidePanel, BorderLayout.WEST);
		sidePanel.setLayout(sidePanelLayout);
		sidePanel.setPreferredSize(new java.awt.Dimension(250, 570));
		{
			sideTitlePanel = new JPanel();
			BorderLayout sideTitlePanelLayout = new BorderLayout();
			sidePanel.add(sideTitlePanel, BorderLayout.NORTH);
			sideTitlePanel.setLayout(sideTitlePanelLayout);
		}
		resetSideList();
		{
			ListModel sideListModel = 
				new DefaultComboBoxModel(appointmenttListString);
			sideList = new JList();
			sideList.setName("sideList");
			sideList.addMouseListener(el.new listListener(sideList));
			JScrollPane scrollPane = new JScrollPane(sideList);
			sidePanel.add(scrollPane, BorderLayout.CENTER);
			sideList.setModel(sideListModel);
			int selectedIndex = el.getSelectedIndex();
			if (selectedIndex > -1) {
				sideList.setSelectedIndex(selectedIndex);
			}
		}
    	String selectedDate = el.getSelectedMonth();
    	String rawSelectedDate = el.getSelectedDate();
    	String[] date = rawSelectedDate.split("/");
    	selectedDate += " " + date[2];
		sideTitle = new JLabel("Appointments for", SwingConstants.CENTER);
		sideTitle.setFont(new Font("sideFont", Font.PLAIN, 24));
		sideTitlePanel.add(sideTitle, BorderLayout.NORTH);
		sideDate = new JLabel(selectedDate, SwingConstants.CENTER);
		sideDate.setFont(new Font("sideDateFont", Font.PLAIN, 24));
		sideTitlePanel.add(sideDate, BorderLayout.SOUTH);
    }
    /**
     * Draws the bottom, appointment input panel
     */
    private void drawBottomPanel() {
		bottomPanel = new JPanel();
		BorderLayout bottomPanelLayout = new BorderLayout();
		bottomPanel.setLayout(bottomPanelLayout);
		contentPanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setPreferredSize(new java.awt.Dimension(1024, 150));
		{
			JSeparator jSeparator = new JSeparator();
			bottomPanel.add(jSeparator, BorderLayout.NORTH);
		}
		resetNoteList(el.getSelectedIndex());
		{
			bottomList = new JPanel();
			BorderLayout bottomListLayout = new BorderLayout();
			bottomList.setLayout(bottomListLayout);
			bottomPanel.add(bottomList, BorderLayout.CENTER);
			border = BorderFactory.createEmptyBorder(5,5,0,5);
			bottomList.setBorder(border);
			{
				ListModel noteListModel = 
					new DefaultComboBoxModel(noteListString);
				noteList = new JList();
				noteList.setName("noteList");
				noteList.addMouseListener(el.new listListener(noteList));
				JScrollPane noteScroll = new JScrollPane(noteList);
				bottomList.add(noteScroll);
				noteList.setModel(noteListModel);
			}
			{
				noteListButtons = new JPanel();
				GridLayout noteListButtonsLayout = new GridLayout(1,3);
				noteListButtons.setLayout(noteListButtonsLayout);
				bottomList.add(noteListButtons, BorderLayout.SOUTH);
    			border = BorderFactory.createEmptyBorder(5,0,0,0);
    			noteListButtons.setBorder(border);
				{
					noteListNew = new JButton();
					noteListNew.addActionListener(noteButtonListener);
					noteListNew.setActionCommand("New");
					noteListButtons.add(noteListNew);
					noteListNew.setName("noteListNew");
					{
						newNotePanel = new JPanel();
						noteListNew.add(newNotePanel);
						newNotePanel.setBounds(17, 4, 40, 13);
						{
							newNoteLabel = new JLabel();
							newNotePanel.add(newNoteLabel);
							newNoteLabel.setName("newNoteLabel");
							newNoteLabel.setText("New Note");
						}
					}
				}
				{
					noteListView = new JButton();
					noteListView.addActionListener(noteButtonListener);
					noteListView.setActionCommand("View");
					noteListButtons.add(noteListView);
					noteListView.setName("noteListView");
					{
						viewNotePanel = new JPanel();
						noteListView.add(viewNotePanel);
						{
							viewNoteLabel = new JLabel();
							viewNotePanel.add(viewNoteLabel);
							viewNoteLabel.setName("viewNoteLabel");
							viewNoteLabel.setText("View Note");
						}
					}
				}
				{
					noteListDelete = new JButton();
					noteListDelete.addActionListener(noteButtonListener);
					noteListDelete.setActionCommand("Delete");
					noteListButtons.add(noteListDelete);
					noteListDelete.setName("noteListDelete");
					{
						deleteNotePanel = new JPanel();
						noteListDelete.add(deleteNotePanel);
						{
							deleteNoteLabel = new JLabel();
							deleteNotePanel.add(deleteNoteLabel);
							deleteNoteLabel.setName("deleteNoteLabel");
							deleteNoteLabel.setText("Delete Note");
						}
					}
				}
			}
		}
		{
			bottomText = new JPanel();
			bottomPanel.add(bottomText, BorderLayout.WEST);
			bottomText.setPreferredSize(new java.awt.Dimension(700, 150));
			bottomText.setLayout(null);
			{
				aptTitle = new JLabel();
				bottomText.add(aptTitle);
				aptTitle.setName("aptTitle");
				aptTitle.setText("Title:");
				aptTitle.setBounds(45, 12, 24, 14);
			}
			{
				titleField = new JTextField(el.getSelectedAppointment().getTitle());
				bottomText.add(titleField);
				titleField.setBounds(73, 9, 281, 20);
				titleField.setName("titleField");
			}
			{
				aptDate = new JLabel();
				bottomText.add(aptDate);
				aptDate.setBounds(42, 40, 27, 14);
				aptDate.setName("aptDate");
				aptDate.setText("Date");
			}
			{
				dateField = new JTextField(el.getSelectedDate());
				bottomText.add(dateField);
				dateField.setBounds(73, 37, 66, 20);
				dateField.setName("dateField");
			}
			{
				aptStartTime = new JLabel();
				bottomText.add(aptStartTime);
				aptStartTime.setBounds(16, 68, 53, 14);
				aptStartTime.setName("aptStartTime");
				aptStartTime.setText("Start Time:");
			}
			{
				String defaultStringStart = "" + el.getSelectedAppointment().getStartTime();
				String startMins = defaultStringStart.substring(defaultStringStart.length()-2);
				String startHours = defaultStringStart.substring(0, defaultStringStart.length()-2);
				defaultStringStart = startHours + ":" + startMins; 
				startTimeField = new JTextField(defaultStringStart);
				bottomText.add(startTimeField);
				startTimeField.setBounds(73, 65, 66, 20);
				startTimeField.setName("startTimeField");
				startTimeField.setEditable(!el.getSelectedAppointment().getFullDay());
			}
			{
				aptEndTime = new JLabel();
				bottomText.add(aptEndTime);
				aptEndTime.setBounds(22, 95, 47, 14);
				aptEndTime.setName("aptEndTime");
				aptEndTime.setText("End Time:");
			}
			{
				String defaultStringEnd = "" + el.getSelectedAppointment().getEndTime();
				String endMins = defaultStringEnd.substring(defaultStringEnd.length()-2);
				String endHours = defaultStringEnd.substring(0, defaultStringEnd.length()-2);
				defaultStringEnd = endHours + ":" + endMins; 
				endTimeField = new JTextField(defaultStringEnd);
				bottomText.add(endTimeField);
				endTimeField.setBounds(73, 92, 66, 20);
				endTimeField.setName("endTimeField");
				endTimeField.setEditable(!el.getSelectedAppointment().getFullDay());
				fullBoxChecked = el.getSelectedAppointment().getFullDay();
			}
			{
				aptWholeDay = new JLabel();
				bottomText.add(aptWholeDay);
				aptWholeDay.setBounds(17, 120, 107, 14);
				aptWholeDay.setName("aptWholeDay");
				aptWholeDay.setText("Full day appointment?");
			}
			{
				wholeDayBox = new JCheckBox("", el.getSelectedAppointment().getFullDay());
				wholeDayBox.setActionCommand("wholedayBox");
				wholeDayBox.addActionListener(buttonListener);
				bottomText.add(wholeDayBox);
				wholeDayBox.setBounds(122, 117, 21, 21);
			}
			{
				aptLocation = new JLabel();
				bottomText.add(aptLocation);
				aptLocation.setBounds(162, 40, 44, 14);
				aptLocation.setName("aptLocation");
				aptLocation.setText("Location:");
			}
			{
				aptPriority = new JLabel();
				bottomText.add(aptPriority);
				aptPriority.setBounds(168, 68, 38, 14);
				aptPriority.setName("aptPriority");
				aptPriority.setText("Priority:");
			}
			{
				aptDescription = new JLabel();
				bottomText.add(aptDescription);
				aptDescription.setBounds(149, 95, 57, 14);
				aptDescription.setName("aptDescription");
				aptDescription.setText("Description:");
			}
			{
				locationField = new JTextField(el.getSelectedAppointment().getLocation());
				bottomText.add(locationField);
				locationField.setBounds(210, 37, 144, 20);
				locationField.setName("locationField");
			}
			{
				ComboBoxModel priorityBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "Low", "Medium", "High" });
				priorityBox = new JComboBox();
				priorityBox.setActionCommand("priorityBox");
				priorityBox.addActionListener(buttonListener);
				bottomText.add(priorityBox);
				priorityBox.setModel(priorityBoxModel);
				priorityBox.setBounds(210, 64, 107, 22);
				priorityBox.setSelectedIndex(el.getSelectedAppointment().getPriority());
			}
			{
				descriptionField = new JEditorPane();
				JScrollPane descScroll = new JScrollPane(descriptionField);
				bottomText.add(descScroll);
				descScroll.setBounds(210, 92, 250, 45);
				descriptionField.setName("descriptionField");
				descriptionField.setText(el.getSelectedAppointment().getDesc());
			}
			{
				aptVisAlarm = new JLabel();
				bottomText.add(aptVisAlarm);
				aptVisAlarm.setBounds(372, 12, 64, 14);
				aptVisAlarm.setName("aptVisAlarm");
				aptVisAlarm.setText("Visible Alarm?");
			}
			{
				visAlarmBox = new JCheckBox("", el.getSelectedAppointment().getVisibleAlarm());
				visBoxChecked = el.getSelectedAppointment().getVisibleAlarm();
				visAlarmBox.setActionCommand("visAlarmBox");
				visAlarmBox.addActionListener(buttonListener);
				bottomText.add(visAlarmBox);
				visAlarmBox.setBounds(436, 9, 21, 21);
			}
			{
				emailAlarm = new JLabel();
				bottomText.add(emailAlarm);
				emailAlarm.setBounds(377, 40, 59, 14);
				emailAlarm.setName("emailAlarm");
				emailAlarm.setText("Email Alarm?");
			}
			{
				emailAlarmBox = new JCheckBox("", el.getSelectedAppointment().getEmailAlarm());
				emailBoxChecked = el.getSelectedAppointment().getEmailAlarm();
				emailAlarmBox.setActionCommand("emailAlarmBox");
				emailAlarmBox.addActionListener(buttonListener);
				bottomText.add(emailAlarmBox);
				emailAlarmBox.setBounds(436, 36, 21, 21);
			}
			{
				aptRecurring = new JLabel();
				bottomText.add(aptRecurring);
				aptRecurring.setBounds(483, 11, 51, 14);
				aptRecurring.setName("aptRecurring");
				aptRecurring.setText("Recurring");
			}
			{
				recurringBox = new JCheckBox("", el.getSelectedAppointment().getRecurring());
				recurringBoxChecked=el.getSelectedAppointment().getRecurring();
				recurringBox.setActionCommand("recurringBox");
				recurringBox.addActionListener(buttonListener);
				bottomText.add(recurringBox);
				recurringBox.setBounds(530, 8, 21, 21);
			}
			{
				typeLabel = new JLabel();
				bottomText.add(typeLabel);
				typeLabel.setBounds(471, 40, 28, 14);
				typeLabel.setName("typeLabel");
				typeLabel.setText("Type:");
			}
			{
				ComboBoxModel recurringChoicesModel = 
					new DefaultComboBoxModel(
							new String[] { "Days of week", "Every N days", "Nth day of month", "Nth Mon/Tue/… of each month" });
				recurringChoices = new JComboBox();
				bottomText.add(recurringChoices);
				recurringChoices.setModel(recurringChoicesModel);
				recurringChoices.setBounds(503, 36, 180, 22);
				recurringChoices.addActionListener(buttonListener);
			}
			{
				sunBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[0]);
				bottomText.add(sunBox);
				sunBox.setBounds(503, 60, 61, 23);
				sunBox.setName("sunBox");
				sunBox.setText("Sunday");
				sunBox.addActionListener(buttonListener);
				sunBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				monBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[1]);
				bottomText.add(monBox);
				monBox.setBounds(503, 79, 63, 23);
				monBox.setName("monBox");
				monBox.setText("Monday");
				monBox.addActionListener(buttonListener);
				monBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				tuesBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[2]);
				bottomText.add(tuesBox);
				tuesBox.setBounds(503, 98, 67, 23);
				tuesBox.setName("tuesBox");
				tuesBox.setText("Tuesday");
				tuesBox.addActionListener(buttonListener);
				tuesBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				wedBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[3]);
				bottomText.add(wedBox);
				wedBox.setBounds(503, 117, 83, 23);
				wedBox.setName("wedBox");
				wedBox.setText("Wednesday");
				wedBox.addActionListener(buttonListener);
				wedBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				thursBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[4]);
				bottomText.add(thursBox);
				thursBox.setBounds(578, 60, 71, 23);
				thursBox.setName("thursBox");
				thursBox.setText("Thursday");
				thursBox.addActionListener(buttonListener);
				thursBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				friBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[5]);
				bottomText.add(friBox);
				friBox.setBounds(578, 79, 55, 23);
				friBox.setName("friBox");
				friBox.setText("Friday");
				friBox.addActionListener(buttonListener);
				friBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				satBox = new JCheckBox("", el.getSelectedAppointment().getBoolDaysOfWeek()[6]);
				bottomText.add(satBox);
				satBox.setBounds(578, 98, 69, 23);
				satBox.setName("satBox");
				satBox.setText("Saturday");
				satBox.addActionListener(buttonListener);
				satBox.setEnabled(el.getSelectedAppointment().getRecurring());
			}
			{
				nEquals = new JLabel();
				bottomText.add(nEquals);
				nEquals.setBounds(620, 126, 18, 14);
				nEquals.setName("nEquals");
				nEquals.setText("N=");
			}
			{
				nEqualsField = new JTextField("" + el.getSelectedAppointment().getN());
				bottomText.add(nEqualsField);
				nEqualsField.setBounds(642, 123, 28, 20);
				nEqualsField.setName("nEqualsField");
			}
			{
				confirmedLabel = new JLabel();
				bottomText.add(confirmedLabel);
				confirmedLabel.setBounds(382, 67, 54, 14);
				confirmedLabel.setName("confirmedLabel");
				confirmedLabel.setText("Confirmed?");
			}
			{
				confirmCheckBox = new JCheckBox("", el.getSelectedAppointment().getConfirmed());
				confirmBoxChecked = el.getSelectedAppointment().getConfirmed();
				confirmCheckBox.addActionListener(buttonListener);
				confirmCheckBox.setActionCommand("confirmCheckBox");
				bottomText.add(confirmCheckBox);
				confirmCheckBox.setBounds(436, 64, 21, 21);
			}
			{
				editInstanceLabel = new JLabel();
				bottomText.add(editInstanceLabel);
				editInstanceLabel.setBounds(564, 11, 76, 14);
				editInstanceLabel.setName("editInstanceLabel");
				editInstanceLabel.setText("Single Instance");
			}
			{
				singleInstanceBox = new JCheckBox("", !el.getSelectedAppointment().getRecurring());
				bottomText.add(singleInstanceBox);
				singleInstanceBox.setBounds(638, 8, 21, 21);
				singleInstanceBox.setActionCommand("singleInstanceBox");
				singleInstanceBox.addActionListener(buttonListener);
			}
		}
		{
			if(el.getSelectedAppointment().getRecurring()){
				enableRecurringOptions();
			} else {
				disableRecurringOptions();
			}
		}
		{
			bottomButtons = new JPanel();
			bottomPanel.add(bottomButtons, BorderLayout.EAST);
			GridLayout bottomButtonsLayout = new GridLayout(3, 1);
			bottomButtonsLayout.setColumns(1);
			bottomButtonsLayout.setVgap(5);
			bottomButtons.setLayout(bottomButtonsLayout);
			bottomButtons.setPreferredSize(new java.awt.Dimension(95, 150));
			{
				bottomNew = new JButton();
				bottomNew.addActionListener(aptButtonListener);
				bottomNew.setActionCommand("New");
				bottomButtons.add(bottomNew);
				bottomNew.setName("bottomNew");
				{
					newAptPanel = new JPanel();
					GridLayout newAptPanelLayout = new GridLayout(2, 1);
					newAptPanel.setLayout(newAptPanelLayout);
					bottomNew.add(newAptPanel);
					{
						newAptPanel1 = new JPanel();
						newAptPanel.add(newAptPanel1);
						newAptPanel1.setPreferredSize(new java.awt.Dimension(66, 12));
						{
							bottomNewLabel = new JLabel();
							newAptPanel1.add(bottomNewLabel);
							bottomNewLabel.setName("bottomNewLabel");
							bottomNewLabel.setText("New");
						}
					}
					{
						newAptPanel2 = new JPanel();
						newAptPanel.add(newAptPanel2);
						{
							bottomAptLabel1 = new JLabel();
							newAptPanel2.add(bottomAptLabel1);
							bottomAptLabel1.setName("bottomAptLabel1");
							bottomAptLabel1.setText("Appointment");
						}
					}
				}
			}
			{
				bottomSave = new JButton();
				bottomSave.addActionListener(aptButtonListener);
				bottomSave.setActionCommand("Save");
				bottomButtons.add(bottomSave);
				bottomSave.setName("bottomSave");
				{
					saveAptPanel = new JPanel();
					bottomSave.add(saveAptPanel);
					GridLayout saveAptPanelLayout = new GridLayout(2, 1);
					saveAptPanel.setLayout(saveAptPanelLayout);
					{
						bottomSavePanel = new JPanel();
						saveAptPanel.add(bottomSavePanel);
						{
							bottomSaveLabel = new JLabel();
							bottomSavePanel.add(bottomSaveLabel);
							bottomSaveLabel.setName("bottomSaveLabel");
							bottomSaveLabel.setText("Save");
						}
					}
					{
						bottomAptPanel2 = new JPanel();
						saveAptPanel.add(bottomAptPanel2);
						{
							bottomAptLabel2 = new JLabel();
							bottomAptPanel2.add(bottomAptLabel2);
							bottomAptLabel2.setName("bottomAptLabel2");
							bottomAptLabel2.setText("Appointment");
						}
					}
				}
			}
			{
				bottomDelete = new JButton();
				bottomDelete.addActionListener(aptButtonListener);
				bottomDelete.setActionCommand("Delete");
				bottomButtons.add(bottomDelete);
				bottomDelete.setName("bottomDelete");
				{
					deleteAptPanel = new JPanel();
					GridLayout deleteAptPanelLayout = new GridLayout(2, 1);
					deleteAptPanel.setLayout(deleteAptPanelLayout);
					bottomDelete.add(deleteAptPanel);
					{
						bottomDeletePanel = new JPanel();
						deleteAptPanel.add(bottomDeletePanel);
						{
							bottomDeleteLabel = new JLabel();
							bottomDeletePanel.add(bottomDeleteLabel);
							bottomDeleteLabel.setName("bottomDeleteLabel");
							bottomDeleteLabel.setText("Delete");
						}
					}
					{
						bottomAptPanel3 = new JPanel();
						deleteAptPanel.add(bottomAptPanel3);
						{
							bottomAptLabel3 = new JLabel();
							bottomAptPanel3.add(bottomAptLabel3);
							bottomAptLabel3.setName("bottomAptLabel3");
							bottomAptLabel3.setText("Appointment");
						}
					}
				}
			}
		}
    }
    /**
     * Collects the appointments for each day in a month
     * 
     * @return an ArrayList of JPanels to add to the month view
     */
    private ArrayList<JPanel> monthView() {
    	int[] days = el.getNumDays();
    	ArrayList<JPanel> arrayOfDays = new ArrayList<JPanel>();
    	String cd = el.getSelectedDate();
    	String rawSelectedDate = el.getSelectedDate();
    	String[] date = rawSelectedDate.split("/");
    	if (Integer.parseInt(date[2]) < 10) {
    		cd = cd.substring(0, cd.length() - 2 );
    	} else {
    		cd = cd.substring(0, cd.length() - 3 );
    	}
    	
    	for (int i = 0; i < days.length; i++) {
    		String dt = cd + "/" + days[i];
    		ArrayList<Appointment> apps = el.viewApp(dt);
    		apps = sortList(apps);
    		JPanel pan = new JPanel();
    		pan.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
    		JLabel d = new JLabel(" " + days[i]);
    		d.setFont((d.getFont()).deriveFont((d.getFont()).getStyle() ^ Font.BOLD));
    		pan.add(d);
    		for (int k = 0; k < apps.size(); k++) {
    			JLabel label = new JLabel("  " + apps.get(k).getTitle());
    			if (apps.get(k).getPriority() == 2) {
    				label.setForeground(Color.RED);
    			}
    			if (apps.get(k).getConfirmed()){
    				label.setFont((label.getFont()).deriveFont((label.getFont()).getStyle() ^ Font.BOLD));
    			}
    			pan.add(label);
    		}
    		pan.setName( dt );
    		if (pan.getName().equals(el.getToday())){
    			pan.setBackground(Color.GREEN);
    		}
    		if (pan.getName().equals(rawSelectedDate)) {
				pan.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    		}
    		pan.addMouseListener( el.new dayListener(pan) );
    		arrayOfDays.add(pan);
    	}
    	return arrayOfDays;
    }
    /**
     * Draws week view in the main panel
     * 
     * @param sDate starting sunday of the current week
     * @param eDate ending saturday for the current week
     */
    private void weekView(String sDate, String eDate){
    	String selectedDate = el.getSelectedDate();
    	JPanel weekMainPanel = new JPanel();
    	weekMainPanel.setLayout(new BorderLayout());
    	mainPanel.add(weekMainPanel, BorderLayout.CENTER);
    	{
    		weekGridNumbers = new JPanel();
    		GridLayout gridNumbersLayout = new GridLayout(26, 1);
    		gridNumbersLayout.setColumns(1);
    		weekMainPanel.add(weekGridNumbers, BorderLayout.WEST);
    		weekGridNumbers.setLayout(gridNumbersLayout);
    		weekGridNumbers.setPreferredSize(new java.awt.Dimension(41, 501));
    		{
    			JPanel fdLabelPanel = new JPanel();
    			fdLabelPanel.setLayout(new BorderLayout());
    			fdLabelPanel.add(new JLabel("Full Day"), BorderLayout.EAST);
    			weekGridNumbers.add(new JLabel(""));
    			weekGridNumbers.add(fdLabelPanel);
    			for (int i=0; i<24; i++){
					JPanel newNum = new JPanel();
					newNum.setLayout(new BorderLayout());
					newNum.add(new JLabel(i + ":00 "), BorderLayout.EAST);
					weekGridNumbers.add(newNum);
    			}
    		}
    	}
    	{
    		weekDays = new JPanel();
    		GridLayout weekDaysLayout = new GridLayout(1, 7);
    		weekMainPanel.add(weekDays, BorderLayout.CENTER);
    		weekDays.setLayout(weekDaysLayout);
    		{
    			sundayPanel = new JPanel();
    			sundayPanel.setName(getDateName(sDate,eDate,0));
    			if (sundayPanel.getName().equals(selectedDate)) {
    				sundayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				sundayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			GridLayout sundayPanelLayout = new GridLayout(26, 1);
    			weekDays.add(sundayPanel);
    			sundayPanel.setLayout(sundayPanelLayout);
    			sundayPanel.add(new JLabel("     Sunday"));
    			sundayPanel.addMouseListener(el.new dayListener(sundayPanel));
    		}
    		{
    			mondayPanel = new JPanel();
    			mondayPanel.setName(getDateName(sDate,eDate,1));
    			if (mondayPanel.getName().equals(selectedDate)) {
    				mondayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				mondayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			GridLayout mondayPanelLayout = new GridLayout(26, 1);
    			weekDays.add(mondayPanel);
    			mondayPanel.setLayout(mondayPanelLayout);
    			mondayPanel.add(new JLabel("     Monday"));
    			mondayPanel.addMouseListener(el.new dayListener(mondayPanel));
    		}
    		{
    			tuesdayPanel = new JPanel();
    			tuesdayPanel.setName(getDateName(sDate,eDate,2));
    			if (tuesdayPanel.getName().equals(selectedDate)) {
    				tuesdayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				tuesdayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			GridLayout tuesdayPanelLayout = new GridLayout(26, 1);
    			weekDays.add(tuesdayPanel);
    			tuesdayPanel.setLayout(tuesdayPanelLayout);
    			tuesdayPanel.add(new JLabel("     Tuesday"));
    			tuesdayPanel.addMouseListener(el.new dayListener(tuesdayPanel));
    		}
    		{
    			wednesdayPanel = new JPanel();
    			wednesdayPanel.setName(getDateName(sDate,eDate,3));
    			if (wednesdayPanel.getName().equals(selectedDate)) {
    				wednesdayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				wednesdayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			GridLayout wednesdayPanelLayout = new GridLayout(26, 1);
    			weekDays.add(wednesdayPanel);
    			wednesdayPanel.setLayout(wednesdayPanelLayout);
    			wednesdayPanel.add(new JLabel("     Wednesday"));
    			wednesdayPanel.addMouseListener(el.new dayListener(wednesdayPanel));
    		}
    		{
    			thursdayPanel = new JPanel();
    			thursdayPanel.setName(getDateName(sDate,eDate,4));
    			if (thursdayPanel.getName().equals(selectedDate)) {
    				thursdayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				thursdayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			GridLayout thursdayPanelLayout = new GridLayout(26, 1);
    			weekDays.add(thursdayPanel);
    			thursdayPanel.setLayout(thursdayPanelLayout);
    			thursdayPanel.add(new JLabel("     Thursday"));
    			thursdayPanel.addMouseListener(el.new dayListener(thursdayPanel));
    		}
    		{
    			fridayPanel = new JPanel();
    			fridayPanel.setName(getDateName(sDate,eDate,5));
    			if (fridayPanel.getName().equals(selectedDate)) {
    				fridayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				fridayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			weekDays.add(fridayPanel);
    			GridLayout fridayPanelLayout = new GridLayout(26, 1);
    			fridayPanel.setLayout(fridayPanelLayout);
    			fridayPanel.add(new JLabel("     Friday"));
    			fridayPanel.addMouseListener(el.new dayListener(fridayPanel));
    		}
    		{
    			saturdayPanel = new JPanel();
    			saturdayPanel = new JPanel();
    			saturdayPanel.setName(getDateName(sDate,eDate,6));
    			if (saturdayPanel.getName().equals(selectedDate)) {
    				saturdayPanel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
    			} else {
    				saturdayPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
    			}
    			GridLayout saturdayPanelLayout = new GridLayout(26, 1);
    			weekDays.add(saturdayPanel);
    			saturdayPanel.setLayout(saturdayPanelLayout);
    			saturdayPanel.add(new JLabel("     Saturday"));
    			saturdayPanel.addMouseListener(el.new dayListener(saturdayPanel));
    		}
    	}
    	ArrayList<Appointment> oneDayList = new ArrayList<Appointment>();
    	for (int i=0; i<7; i++){
    		oneDayList = el.viewApp(getDateName(sDate, eDate, i));
    		oneDayList = sortList(oneDayList);
    		switch (i) {
    			case 0: //Sunday
    				drawDay(sundayPanel, oneDayList);
    				break;
    			case 1: //Monday
    				drawDay(mondayPanel, oneDayList);
    				break;
    			case 2: //Tuesday
    				drawDay(tuesdayPanel, oneDayList);
    				break;
    			case 3: //Wednesday
    				drawDay(wednesdayPanel, oneDayList);
    				break;
    			case 4: //Thursday
    				drawDay(thursdayPanel, oneDayList);
    				break;
    			case 5: //Friday
    				drawDay(fridayPanel, oneDayList);
    				break;
    			case 6: //Saturday
    				drawDay(saturdayPanel, oneDayList);
    				break;
    			default: break;
    		}
    	}
    	
    }
    /**
     * Draws day view in the main panel
     */
    private void dayView(){
    	ArrayList<Appointment> app = null;
		String selectedMonth = el.getSelectedMonth();
		String rawSelectedDate = el.getSelectedDate();
		String[] date = rawSelectedDate.split("/");
		JPanel displayMon = new JPanel();
		JPanel prevBut = new JPanel();
		JPanel nextBut = new JPanel();
		JPanel monDay = new JPanel();
		JPanel dayMainPanel = new JPanel();
		JPanel fullDayPanel = new JPanel();
		JPanel times = new JPanel();
		JPanel hourApp = new JPanel();
		JPanel secondApp = new JPanel();
		JLabel prevDay = new JLabel("<");
    	JLabel mont = new JLabel(selectedMonth + " ");
    	JLabel day = new JLabel();
    	JLabel nextDay = new JLabel(">");
		
		if(date.length == 3){
			day = new JLabel(date[2]);
		}else {
			day.setText("null");
		}
		JLabel yearLabel = new JLabel(", " + date[0]);
		prevBut.add(prevDay);
		nextBut.add(nextDay);
    	monDay.add(mont);  
    	monDay.add(day);
    	monDay.add(yearLabel);
    	prevBut.setName("Previous Day");
    	nextBut.setName("Next Day");
    	prevBut.addMouseListener(el.new dayListener(prevBut));
    	nextBut.addMouseListener(el.new dayListener(nextBut));
    	displayMon.add(prevBut);
		displayMon.add(monDay);
		displayMon.add(nextBut);
		displayMon.setVisible(true);
		sidePanel.setVisible(false);
		
		prevDay.setFont(new Font("titleFont", Font.PLAIN, 24));
		mont.setFont(new Font("titleFont", Font.PLAIN, 30));
		day.setFont(new Font("titleFont", Font.PLAIN, 30));
		yearLabel.setFont(new Font("titleFont", Font.PLAIN, 30));
		nextDay.setFont(new Font("sideFont", Font.PLAIN, 24));
  		mainPanel.setLayout(new BorderLayout());
  		mainPanel.add(displayMon, BorderLayout.NORTH);
  		
		try{
			app = el.viewApp(date[0] + "/" + date[1] + "/" + date[2]);
		}catch(NullPointerException ex){
			
		}
		app = sortList(app);
		GridLayout gridTimes = new GridLayout(26,1);
		gridTimes.setColumns(1);
				
		dayMainPanel.setLayout(new BorderLayout());
		mainPanel.add(dayMainPanel, BorderLayout.CENTER);
				
		dayMainPanel.add(times, BorderLayout.WEST);
		times.setLayout(gridTimes);
		times.setPreferredSize(new java.awt.Dimension(41,501));
				
		fullDayPanel.setLayout(new BorderLayout());
		fullDayPanel.add(new JLabel("Full Day"), BorderLayout.EAST);
		times.add(new JLabel(""));
		times.add(fullDayPanel);
		
		for (int i=0; i<24; i++){
			JPanel hour = new JPanel();
			hour.setLayout(new BorderLayout());
			hour.add(new JLabel(i + ":00 "), BorderLayout.EAST);
			times.add(hour);
    	} 
    			
		hourApp.setName(day.getText());
		GridLayout hourAppGrid = new GridLayout(26,1);
		dayMainPanel.add(hourApp, BorderLayout.CENTER);
		dayMainPanel.add(secondApp, BorderLayout.EAST);
		hourApp.setLayout(hourAppGrid);
		secondApp.setLayout(hourAppGrid);
		secondApp.add(new JLabel(""));
		hourApp.add(new JLabel(" "));
		drawDay(hourApp, app);
    }
    /**
     * Takes a panel with a grid layout and a sorted ArrayList of appointments
     * and places the appointments into the grid using
     * 
     * @param panel	grid panel to add appointments to
     * @param oneDayList sorted list of appointments to be added to panel
     */
    private void drawDay(JPanel panel, ArrayList<Appointment> oneDayList){
		long startTime=0;
		long endTime=0;
		int appLength=0;
		boolean fullDayAdded=false;
		for (int i=0; i<oneDayList.size(); i++){
			boolean lastApp = false;
			if (i==(oneDayList.size()-1)){
				lastApp = true;
			}
			Appointment a = oneDayList.get(i);
			if (!fullDayAdded){
				if (a.getFullDay()){
					JPanel appPanel = new JPanel();
					Color color = new Color(0,170,190);
					appPanel.setBackground(color);
					appPanel.setBorder(new LineBorder(new java.awt.Color(128,128,128), 1, false));
					JLabel appTitle = new JLabel(a.getTitle());
					if (a.getConfirmed()){
						appTitle.setFont((appTitle.getFont()).deriveFont((appTitle.getFont()).getStyle() ^ Font.BOLD));
					}
					appPanel.add(appTitle);
					appPanel.setName(a.getDate()+"/"+appIndex.indexOf(a));
					appPanel.addMouseListener(el.new appListener(appPanel));
					panel.add(appPanel);
				} else {
					panel.add(new JLabel(" "));
				}
				fullDayAdded=true;
			}
			if (!a.getFullDay()){
				appLength = (int)((a.getEndTime()+70)/100) - (int)(a.getStartTime()/100);
				boolean overLapping = false;
				if (!lastApp){
					overLapping = isOverlapping(a, oneDayList.get(i+1));
				}
				startTime = (a.getStartTime()/100)-endTime;
				for (int j=0; j<startTime; j++) {
					panel.add(new JLabel(" "));
				}
				if (!overLapping){
					endTime = ((a.getEndTime()+70)/100);
					boolean titleAdded = false;
					for (int j=0; j<appLength; j++){
						JPanel appPanel = new JPanel();
						Color color = Color.ORANGE;
						if (a.getPriority()==0){
							color = Color.YELLOW;
						} else if (a.getPriority()==2){
							color = Color.RED;
						}
						appPanel.setBackground(color);
						if (!titleAdded){
							JLabel appTitle = new JLabel(a.getTitle());
							if (a.getConfirmed()){
								appTitle.setFont((appTitle.getFont()).deriveFont((appTitle.getFont()).getStyle() ^ Font.BOLD));
							}
							appPanel.add(appTitle);
							appPanel.setName(a.getDate()+"/"+appIndex.indexOf(a));
							appPanel.addMouseListener(el.new appListener(appPanel));
							titleAdded=true;
						}
						panel.add(appPanel);
					}
				} else {
					i++;
					Appointment b = oneDayList.get(i);
					long aStart = (a.getStartTime()/100);
					long aEnd = ((a.getEndTime()+70)/100);
					long bStart = (b.getStartTime()/100);
					long bEnd = ((b.getEndTime()+70)/100);
					//relative starts and ends for a pair of overlapping apps
					int relStart=0;
					int relEnd=(int)(Math.max(aEnd, bEnd)-aStart);
					int relAStart=0;
					int relBStart=(int)(bStart-aStart);
					int relAEnd=(int)(aEnd-aStart);
					int relBEnd=(int)(bEnd-aStart);
					boolean aTitleAdded=false;
					boolean bTitleAdded=false;
					for (int pos=relStart; pos<=relEnd; pos++){
						JPanel splitPanel = new JPanel();
						splitPanel.setLayout(new GridLayout(1,2));
						panel.add(splitPanel);
						if(pos>=relAStart && pos<relAEnd){
							JPanel appPanel = new JPanel();
							Color color = Color.ORANGE;
							if (a.getPriority()==0){
								color = Color.YELLOW;
							} else if (a.getPriority()==2){
								color = Color.RED;
							}
							appPanel.setBackground(color);
							if (!aTitleAdded){
								JLabel appTitle = new JLabel(a.getTitle());
								if (a.getConfirmed()){
									appTitle.setFont((appTitle.getFont()).deriveFont((appTitle.getFont()).getStyle() ^ Font.BOLD));
								}
								appPanel.add(appTitle);
								appPanel.setName(a.getDate()+"/"+appIndex.indexOf(a));
								appPanel.addMouseListener(el.new appListener(appPanel));
								aTitleAdded=true;
							}
							splitPanel.add(appPanel);
						} else {
							splitPanel.add(new JLabel(""));
						}
						if(pos>=relBStart && pos<relBEnd){
							JPanel appPanel = new JPanel();
							Color color = Color.ORANGE;
							if (b.getPriority()==0){
								color = Color.YELLOW;
							} else if (b.getPriority()==2){
								color = Color.RED;
							}
							appPanel.setBackground(color);
							if (!bTitleAdded){
								JLabel appTitle = new JLabel(b.getTitle());
								if (b.getConfirmed()){
									appTitle.setFont((appTitle.getFont()).deriveFont((appTitle.getFont()).getStyle() ^ Font.BOLD));
								}
								appPanel.add(appTitle);
								appPanel.setName(b.getDate()+"/"+appIndex.indexOf(b));
								appPanel.addMouseListener(el.new appListener(appPanel));
								bTitleAdded=true;
							}
							splitPanel.add(appPanel);
						} else {
							splitPanel.add(new JLabel(""));
						}
					}
					endTime = (Math.max(aEnd, bEnd))+1;
					boolean oLap=true;
					while(oLap){
						if (i==(oneDayList.size()-1)){
							oLap=false;
						} else {
							Appointment c = oneDayList.get(i+1);
							if (isOverlapping(a,c) || isOverlapping(b,c)){
								i++;
								oLap = true;
							} else {
								oLap = false;
							}
						}
					}
				}
			}
		}
    }

    /**
     * takes an integer, and returns the month associated with it
     * 
     * @param month month to convert to string
     * @return String equivalent of month
     */
    private String monthToString(int month){
    	String weekMonth="";
		switch (month){
			case 1: weekMonth = "January"; break;
			case 2: weekMonth = "February"; break;
			case 3: weekMonth = "March"; break;
			case 4: weekMonth = "April"; break;
			case 5: weekMonth = "May"; break;
			case 6: weekMonth = "June"; break;
			case 7: weekMonth = "July"; break;
			case 8: weekMonth = "August"; break;
			case 9: weekMonth = "September"; break;
			case 10: weekMonth = "October"; break;
			case 11: weekMonth = "November"; break;
			case 12: weekMonth = "December"; break;
			default: break;
		}
		return weekMonth;
    }
    /**
     * Takes a span of 7 days and returns the date of the requested day
     * 
     * @param sDate sunday of a given week
     * @param eDate saturday of the same week
     * @param day requested day of week (0=sunday, 1=monday, etc)
     * @return string representation of requested day
     */
    private String getDateName(String sDate, String eDate, int day){
    	String retVal = "";
    	if (day==0){
    		retVal=sDate;
    	} else if (day==6){
    		retVal=eDate;
    	} else {
    		String[] splitDate = sDate.split("/");
    		int sYr = Integer.parseInt(splitDate[0]);//start year
    		int sMn = Integer.parseInt(splitDate[1]);//start month
    		int sDy = Integer.parseInt(splitDate[2]);//start day
    		if (sDy+day > el.daysInMonth(sMn, sYr)){
    			if(sMn+1!=13){
    				retVal= splitDate[0] +"/"+ (sMn+1) +"/"+ (sDy+day-el.daysInMonth(sMn,sYr));
    			} else {
    				retVal = (sYr+1) +"/1/"+ (sDy+day-el.daysInMonth(sMn,sYr));
    			}
    		} else {
    			retVal = splitDate[0] +"/"+ splitDate[1] +"/"+ (sDy+day);
    		}
    	}
    	return retVal;
    }
    /**
     * sorts a list of appointments using a bubble sort
     * 
     * @param appList list to sort
     * @return sorted list
     */
    private ArrayList<Appointment> sortList(ArrayList<Appointment> appList){
    	ArrayList<Appointment> retVal = appList;
    	Appointment data[] = new Appointment[retVal.size()];
    	for(int i=0;i<retVal.size(); i++){
    		data[i] = retVal.get(i);
    	}
    	boolean isSorted;
    	int numberOfTimesLooped = 0;
    	Appointment tempVariable;
    	do {
    		isSorted = true;
    		for (int i = 1; i < data.length - numberOfTimesLooped; i++) {
    			if (data[i].compareTo(data[i - 1])==-1) {
    	            tempVariable = data[i];
    	            data[i] = data[i - 1];
    	            data[i - 1] = tempVariable;
    	            isSorted = false;
    			}
    		}
    		numberOfTimesLooped++;
    	} while (!isSorted);
    	retVal = new ArrayList<Appointment>(Arrays.asList(data));
    	return retVal;
    }
    /**
     * Checks of two appointments are overlapping
     * 
     * @param a first appointment
     * @param b seconf appointment
     * @return true if appointments overlap
     */
    private boolean isOverlapping(Appointment a, Appointment b){
    	boolean overlap=false;
    	if ((b.getStartTime() >= a.getStartTime()) && (b.getStartTime() < a.getEndTime())){
    		overlap = true;
    	}
    	return overlap;
    }
    //RECURRING OPTION HANDLERS
    /**
     * called when the recurring options combo box is changed
     */
    public void rBoxChanged(){
    	enableRecurringOptions();
    }
    /**
     * disables all options related to recurring appointments
     */
    private void disableRecurringOptions(){
    	recurringBoxChecked = false;
    	recurringBox.setSelected(false);
    	instanceBoxChecked = true;
    	singleInstanceBox.setSelected(true);
    	recurringChoices.setEnabled(false);
    	disableRecurringDays();
    	nEqualsField.setEnabled(false);
    }
    /**
     * enables all options related to recurring appointments
     */
    private void enableRecurringOptions(){
    	int rType = recurringChoices.getSelectedIndex();
    	recurringBoxChecked = true;
    	recurringBox.setSelected(true);
    	instanceBoxChecked = false;
    	singleInstanceBox.setSelected(false);
    	recurringChoices.setEnabled(true);
    	recurringChoices.setSelectedIndex(rType);
    	updateRecurringDays(rType);
    	//Days of Week
    	if (rType == 0) {
        	sunBox.setEnabled(true);
        	monBox.setEnabled(true);
        	tuesBox.setEnabled(true);
        	wedBox.setEnabled(true);
        	thursBox.setEnabled(true);
        	friBox.setEnabled(true);
        	satBox.setEnabled(true);
        	nEqualsField.setEnabled(false);
    	}
    	//Every N Days
    	else if (rType == 1) {
        	sunBox.setEnabled(false);
        	monBox.setEnabled(false);
        	tuesBox.setEnabled(false);
        	wedBox.setEnabled(false);
        	thursBox.setEnabled(false);
        	friBox.setEnabled(false);
        	satBox.setEnabled(false);
        	nEqualsField.setEnabled(true);
    	}
    	//Nth day of month
    	else if (rType == 2) {
        	sunBox.setEnabled(false);
        	monBox.setEnabled(false);
        	tuesBox.setEnabled(false);
        	wedBox.setEnabled(false);
        	thursBox.setEnabled(false);
        	friBox.setEnabled(false);
        	satBox.setEnabled(false);
        	nEqualsField.setEnabled(true);
    	}
    	//Nth Mon/Tues/.. of each month
    	else {
        	sunBox.setEnabled(true);
        	monBox.setEnabled(true);
        	tuesBox.setEnabled(true);
        	wedBox.setEnabled(true);
        	thursBox.setEnabled(true);
        	friBox.setEnabled(true);
        	satBox.setEnabled(true);
        	nEqualsField.setEnabled(true);
    	}
    }
    /**
     * resets the day boxes (sunday/monday/tuesday/...) when needed
     */
    private void resetRecurringDays(){
    	sunBoxChecked = false;
    	sunBox.setSelected(false);
    	monBoxChecked = false;
    	monBox.setSelected(false);
    	tuesBoxChecked = false;
    	tuesBox.setSelected(false);
    	wedBoxChecked = false;
    	wedBox.setSelected(false);
    	thursBoxChecked = false;
    	thursBox.setSelected(false);
    	friBoxChecked = false;
    	friBox.setSelected(false);
    	satBoxChecked = false;
    	satBox.setSelected(false);
    }
    /**
     * disables the day check boxes when needed
     */
    private void disableRecurringDays(){
    	sunBoxChecked = false;
    	sunBox.setSelected(false);
    	sunBox.setEnabled(false);
    	
    	monBoxChecked = false;
    	monBox.setSelected(false);
    	monBox.setEnabled(false);
    	
    	tuesBoxChecked = false;
    	tuesBox.setSelected(false);
    	tuesBox.setEnabled(false);
    	
    	wedBoxChecked = false;
    	wedBox.setSelected(false);
    	wedBox.setEnabled(false);
    	
    	thursBoxChecked = false;
    	thursBox.setSelected(false);
    	thursBox.setEnabled(false);
    	
    	friBoxChecked = false;
    	friBox.setSelected(false);
    	friBox.setEnabled(false);
    	
    	satBoxChecked = false;
    	satBox.setSelected(false);
    	satBox.setEnabled(false);
    }
    /**
     * Sets the recurring days enabled and checked when required
     * 
     * @param rType type of recurring
     */
    private void updateRecurringDays(int rType){
    	recurringChoices.setSelectedIndex(rType);
    	sunBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[0];
    	sunBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[0]);
    	monBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[1];
    	monBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[1]);
    	tuesBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[2];
    	tuesBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[2]);
    	wedBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[3];
    	wedBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[3]);
    	thursBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[4];
    	thursBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[4]);
    	friBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[5];
    	friBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[5]);
    	satBoxChecked = el.getSelectedAppointment().getBoolDaysOfWeek()[6];
    	satBox.setSelected(el.getSelectedAppointment().getBoolDaysOfWeek()[6]);
    }
    //CHECK BOX TOGGLERS
    /**
     * toggles the full day option
     */
    public void toggleFullDay() {
    	if (fullBoxChecked) {
    		fullBoxChecked = false;
    	  	startTimeField.setEditable(true);
        	endTimeField.setEditable(true);
    	} else {
    		fullBoxChecked = true;
    	  	startTimeField.setEditable(false);
        	endTimeField.setEditable(false);
    	}
    }
    /**
     * toggles the visible alarm option
     */
    public void toggleVisibleAlarm() {
    	if (visBoxChecked) {
    		visBoxChecked = false;
    		visAlarmBox.setSelected(false);
    	} else {
    		visBoxChecked = true;
    		visAlarmBox.setSelected(true);
    	}
    }
    /**
     * toggles the email alarms
     */
    public void toggleEmailAlarm() {
    	if (emailBoxChecked) {
    		emailBoxChecked = false;
    		emailAlarmBox.setSelected(false);
    	} else {
    		emailBoxChecked = true;
    		emailAlarmBox.setSelected(true);
    	}
    }
    /**
     * toggles the confirmed/tentative option
     */
    public void toggleConfirm() {
    	if (confirmBoxChecked) {
    		confirmBoxChecked = false;
    	} else {
    		confirmBoxChecked = true;
    	}
    }
    /**
     * toggles the single instance option
     * also toggles the recurring option
     */
    public void toggleSingleInstance(){
    	resetRecurringDays();
    	if (instanceBoxChecked){
    		instanceBoxChecked = false;
    		recurringBoxChecked = true;
    		enableRecurringOptions();
    	} else {
    		instanceBoxChecked = true;
    		recurringBoxChecked = false;
    		disableRecurringOptions();
    	}
    }
    /**
     * toggles the recurring option
     * also toggles the single instance option
     */
    public void toggleRecurring() {
    	resetRecurringDays();
    	if (recurringBoxChecked){
    		recurringBoxChecked = false;
    		instanceBoxChecked = true;
    		disableRecurringOptions();
    	} else {
    		recurringBoxChecked = true;
    		instanceBoxChecked = false;
    		enableRecurringOptions();
    	}
    }
    /**
     * toggles the saturday check box
     */
    public void toggleSaturday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (satBoxChecked) {
    		satBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		satBoxChecked = true;
    		satBox.setSelected(true);
    	}
    }
    /**
     * toggles the friday check box
     */
    public void toggleFriday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (friBoxChecked) {
    		friBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		friBoxChecked = true;
    		friBox.setSelected(true);
    	}
    }
    /**
     * toggles the thursday check box
     */
    public void toggleThursday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (thursBoxChecked) {
    		thursBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		thursBoxChecked = true;
    		thursBox.setSelected(true);
    	}
    }
    /**
     * toggles the wednesday check box
     */
    public void toggleWednesday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (wedBoxChecked) {
    		wedBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		wedBoxChecked = true;
    		wedBox.setSelected(true);
    	}
    }
    /**
     * toggles the tuesday check box
     */
    public void toggleTuesday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (tuesBoxChecked) {
    		tuesBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		tuesBoxChecked = true;
    		tuesBox.setSelected(true);
    	}
    }
    /**
     * toggles the monday check box
     */
    public void toggleMonday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (monBoxChecked) {
    		monBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		monBoxChecked = true;
    		monBox.setSelected(true);
    	}
    }
    /**
     * toggles the sunday check box
     */
    public void toggleSunday(){
    	int rType = recurringChoices.getSelectedIndex();
    	if (sunBoxChecked) {
    		sunBoxChecked = false;
    	} else {
    		if (rType==3){
    			resetRecurringDays();
    		}
    		sunBoxChecked = true;
    		sunBox.setSelected(true);
    	}
    }
    //GETTERS
    /**
     * called to filter appointments to those with an alarm
     * 
     * @return arrayList of appointments with an alarm
     */
    public ArrayList<Appointment> getAlarmAppointments(){
    	ArrayList<Appointment> retVal = new ArrayList<Appointment>();
    	ArrayList<Appointment> allApps = el.viewApp(el.getToday());
    	for (Appointment a:allApps){
    		if (a.getEmailAlarm() || a.getVisibleAlarm()){
    			retVal.add(a);
    		}
    	}
    	return retVal;
    }
    /**
     * @return the main frame of the program
     */
    public JFrame getFrame(){
    	return getMainFrame();
    }
    /**
     * @return the string found in the title text field
     */
    public String getTitleField() {
    	return titleField.getText();
    }
    /**
     * @return the string found in the date text field
     */
    public String getDateField() {
    	return dateField.getText();
    }
    /**
     * return the string found in the start time text field
     */
    public long getStartTimeField() {
    	long retVal;
    	String[] parsed = startTimeField.getText().split(":");
    	if (parsed[1].length()==1){
    		parsed[1] = "0"+parsed[1];
    	}
    	String joined = parsed[0] + parsed[1];
    	retVal = (long) Integer.parseInt(joined);
    	return retVal;
    }
    /**
     * @return the string found in the end time text field
     */
    public long getEndTimeField() {
    	long retVal;
    	String[] parsed = endTimeField.getText().split(":");
    	String joined = parsed[0] + parsed[1];
    	retVal = (long) Integer.parseInt(joined);
    	return retVal;
    }
    /**
     * @return true if full day is enabled
     */
    public boolean getFullDay() {
    	return fullBoxChecked;
    }
    /**
     * @return the string found in the location field
     */
    public String getLocationField() {
    	return locationField.getText();
    }
    /**
     * @return the selected index of the priority combo box
     */
    public int getPriority() {
    	return priorityBox.getSelectedIndex();
    }
    /**
     * @return the string found in the description field
     */
    public String getDescriptionField() {
    	return descriptionField.getText();
    }
    /**
     * @return true if visible alarm is enabled
     */
    public boolean getVisibleAlarm() {
    	return visBoxChecked;
    }
    /**
     * @return true if email alarm is enabled
     */
    public boolean getEmailAlarm() {
    	return emailBoxChecked;
    }
    /**
     * @return true if confirm box is enabled
     */
    public boolean getConfirm() {
    	return confirmBoxChecked;
    }
    /**
     * @return true if recurring is checked
     */
    public boolean isRecurring() {
    	return recurringBoxChecked;
 	}
    /**
     * @return the value of the recurring options combo box
     */
    public int getRecurBox(){
    	return recurringChoices.getSelectedIndex();
    }
    /**
     * @return an array of boolean representing the values of the day boxes
     */
    public boolean[] gatherDays(){
        boolean[] daysOfWeek = new boolean[7];
    	daysOfWeek[0]=sunBoxChecked;
    	daysOfWeek[1]=monBoxChecked;
    	daysOfWeek[2]=tuesBoxChecked;
    	daysOfWeek[3]=wedBoxChecked;
    	daysOfWeek[4]=thursBoxChecked;
    	daysOfWeek[5]=friBoxChecked;
    	daysOfWeek[6]=satBoxChecked;
    	return daysOfWeek;
    }
    /**
     * @return true if at least one day is checked
     */
    public boolean daysChecked(){
    	boolean retVal=false;
    	boolean[] days = gatherDays();
    	for (int i=0; i<days.length; i++){
    		if (days[i]){
    			retVal=true;
    		}
    	}
    	return retVal;
    }
    /**
     * @return the string found in the N= text field
     */
    public String getNEqualsField() {
    	return nEqualsField.getText();
    }
    //LIST HANDLERS
    /**
     * @return the side appointment list
     */
    public JList getSideList() {
    	return sideList;
    }
    /**
     * @param index the index requested
     * @return the appointment associated with the index
     */
    public Appointment getFromIndex(int index) {
    	Appointment retVal;
    	if (index > -1) {
    		if (index < appIndex.size()) {
    			retVal =  appIndex.get(index);
    		} else {
    			retVal = null;
    		}
    	} else {
    		retVal = null;
    	}
    	return retVal;
    }
    /**
     * @return the bottom note list
     */
    public JList getNoteList() {
    	return noteList;
    }
    /**
     * @param index the requested index
     * @return the note associated with the requested index
     */
    public Note noteIndex(int index) {
    	Note retVal;
    	if (index!=-1) {
    		retVal = noteIndex.get(index);
    	} else {
    		retVal=null;
    	}
    	return retVal;
    }
    /**
     * resets the side appointment list
     */
    public void resetSideList() {
    	appointmenttListString = new String[0];
    	String rawSelectedDate = el.getSelectedDate();
    	appIndex = el.viewApp(rawSelectedDate);
    	appIndex = sortList(appIndex);
		if (appIndex.size() != 0) {
			String[] appList = new String[appIndex.size()];
			for (int i=0; i<appIndex.size(); i++) {
				if (!appIndex.get(i).getFullDay()) {
					String startTime = "" + appIndex.get(i).getStartTime();
					if (startTime.equals("0")){
						startTime = "000";
					}
					String endTime = "" + appIndex.get(i).getEndTime();
					if (endTime.equals("0")){
						endTime = "000";
					}
					String startTimeEnd = startTime.substring(startTime.length()-2);
					String startTimeBeginning = startTime.substring(0,startTime.length()-2);
					startTime = startTimeBeginning + ":" + startTimeEnd;
					String endTimeEnd = endTime.substring(endTime.length()-2);
					String endTimeBeginning = endTime.substring(0,endTime.length()-2);
					endTime = endTimeBeginning + ":" + endTimeEnd;
					appList[i] = startTime + " - " + endTime + ": " + appIndex.get(i).getTitle();
				} else {
					appList[i] = "Full Day: " + appIndex.get(i).getTitle();
				}
			}
			appointmenttListString = appList;
		}
    }
    /**
     * resets the bottom note list with a list of notes
     * belonging to the selected appointment
     * @param index  index of selected appointment in side list
     */
    public void resetNoteList(int index){
    	noteListString = new String[0];
    	if (index != -1) {
    		if (!(index>=appIndex.size())){
	    		noteIndex = appIndex.get(index).getNotes();
	    		if (noteIndex.size() > 0) {
	    			String[] newNoteList = new String[noteIndex.size()];
	    			for (int i=0; i<noteIndex.size(); i++){
	    				newNoteList[i] = noteIndex.get(i).getTitle();
	    			}
	    			noteListString = newNoteList;
	    		}
    		}
    	}
    }
    
    /**
     * the main method
     * @param args unused
     */
    public static void main(String[] args) {
    	launch(GUI.class, args);
    }

}