import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/** The CommandCalendar class is the driver
 *  for the Calendar program. It will display 
 *  the current calendar as well as give the user 
 *  many options to choose from. The user will have
 *  options that include but are not limited to, seeing
 *  the next month, the previous month, switching views, 
 *  and making appointments.
 *  
 * @author Henryk Tunguz
 * @author Vaidotas Jasinevicius
 *
 */

public class CommandCalendar {
	
	//declare our global variables for future use
	//initialize our calendar
	
	static Scanner input;
	static GregorianCalendar cal = new GregorianCalendar();
	static int currentMonth = cal.get(Calendar.MONTH);
	
	/** This method computes the first day of every month
	 *  since every month starts on a different day.
	 *  
	 * @return standardFirst   the calculated first day of the current month
	 */
	
	public static int computeFirstOfMonth() {
		
		//mod our current day by 7 to roll the calendar up 
		//to the first week, then substract the day of the week
		//(Sunday = 1, Monday = 2 etc.) from the day we just calculated
		//then we ask if the result is negative, if it is then we know that
		//the month does not start on that day. We make it positive and then
		//subtract seven and add 1 because of index errors and then we arrive 
		//at the first day of the month
		//otherwise if the result is positive, then we add one and ask if it 
		//is over 7, it is we take away seven 
		
		int firstDay = (cal.get(Calendar.DAY_OF_MONTH)) % 7;
		int standardFirst = cal.get(Calendar.DAY_OF_WEEK);
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
	
	/** ComputeNumDays calculates how many days should be in 
	 * the current month, since some months have 30 days and
	 * others have 31. Leap years are also accounted for.
	 * 
	 * @return days   an array of days calculated to fit the current month
	 */
	
	public static int[] computeNumDays() {
		
		//Simply, we instantiate an array of days, then proceed
		//to get the current month. if the current month matches
		//0,2,4,6,7,9, or 11 we know there are 31 days.
		//If the current month is February, we ask if it is a leap 
		//year. If it is, then an array of 29 days is made. Otherwise, 
		//the array is set to 28.
		//In the case that the current month is it not any of those
		//predetermined numbers, the array is set to a size of 
		//30 days.
 		
		int[] days;
		int mon = cal.get(Calendar.MONTH);
		if(mon == 0 ||mon == 2 || mon == 4 || mon == 6 ||
			mon == 7 || mon == 9 || mon == 11){
			days = new int[31];
		} 
		else if(mon == 1) {
			if(cal.isLeapYear(cal.get(Calendar.YEAR))) {
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
	
	/** This method displays the current month
	 *  
	 */ 
	
	public static void displayMonth(){
		
		//We switch on whatever month it currently is
		//on based on the choice we print out a designated 
		//month
		
		System.out.println("----------------------------");
		
		switch(cal.get(Calendar.MONTH)){
			case 0: System.out.println("       January      ");
					break;
			case 1: System.out.println("       February     ");
					break;
			case 2: System.out.println("       March        ");
					break;
			case 3: System.out.println("       April        ");
					break;
			case 4: System.out.println("       May          ");
					break;
			case 5: System.out.println("       June         ");
					break;
			case 6: System.out.println("       July         ");
					break;
			case 7: System.out.println("       August       ");
					break;
			case 8: System.out.println("       September    ");
					break;
			case 9: System.out.println("       October      ");
					break;
			case 10: System.out.println("       November     ");
					break;
			case 11: System.out.println("       December     ");
		}
		System.out.println("----------------------------");
	}
	
	/** The displayCalendar is fairly large, it prints out
	 * and correctly formats the days of whichever month 
	 * it happens to be.  It prints out a normal, familiar calendar
	 * with the days of week at the top. Then days of the month below 
	 * it
	 */
	
	public static void displayCalendar() {
		
		//our booleans will tell us whether the first day has been
		//set, and whether the first line on the calendar has been 
		//returned.
		
		boolean firstDaySet = false;
		boolean firstLineReturn = false;
		int standardFirstDay  = computeFirstOfMonth();
		int[] days = computeNumDays();
		displayMonth();
		int dayCount = 0;
		System.out.println("S  M  T  W  R  F  S");
		
		//Daycount will keep track of how many days have
		//passed by, and if equal to seven will create a line return
		
		//This for loop traverses the days array given to us
		//by computeNumDays(). On each iteration it asks if the
		//iterator is less than 8, because the first week will consist of 7
		//days however the number of days on that line will vary.
		
		for(int a = 1; a <= days.length; a++) {
			if(a < 8){
				if(a == 1){
					if(!firstDaySet){
						switch(standardFirstDay){
							case 1: System.out.print(a + "  ");
									firstDaySet = true;
									break;
							case 2: System.out.print("   " + a + "  ");
									firstDaySet = true;
									break;
							case 3: System.out.print("      " + a + "  ");
									firstDaySet = true;
									break;
							case 4: System.out.print("         " + a + "  ");
									firstDaySet = true;
									break;
							case 5: System.out.print("            " + a + "  ");
									firstDaySet = true;
									break;
							case 6: System.out.print("               " + a + "  ");
									firstDaySet = true;
									break;
							case 7: System.out.print("                  " + a + "  ");
									firstDaySet = true;
									break;
						}
					}
				}else{
				
					//After the first day has been set, we need to determine
					//where the first line return shall be.
					//Therefore we ask !firstLineReturn returns true every time,
					//then we switch on standardFirstDay which was given to us by
					//computeFirstOfMonth().  Based on the selection from the switch
					//statement we ask if the iteration modded by whatever days would be 
					//left in the week equals zero.
					//if it does, print the day, make a new line, and set our boolean to 
					//true.  Otherwise, simply print the day with a space until we get to
					//our needed day.
					
					if(!firstLineReturn ){
						switch(standardFirstDay){
							case 1:  if(a % 7 == 0 && a != 0){
										System.out.println(a + "  ");
										firstLineReturn = true;
										a++;
									 }else {
									 	System.out.print(a + "  ");
									 }break;
									 
							case 2:  if(a % 6 == 0 && a != 0){
										System.out.println(a + "  ");
										firstLineReturn = true;
										a++;
									 }else {
									 	System.out.print(a + "  ");
									 }break;
							case 3:  if(a % 5 == 0 && a != 0){
										System.out.println(a + "  ");
										firstLineReturn = true;
										a++;
									 }else {
									 	System.out.print(a + "  ");
									 }break;
							case 4:  if(a % 4 == 0 && a != 0){
										System.out.println(a + "  ");
										firstLineReturn = true;
										a++;
									 }else {
									 	System.out.print(a + "  ");
									 }break;
							case 5: if(a % 3 == 0 && a != 0){
										System.out.println(a + "  ");
										firstLineReturn = true;
										a++;
									}else {
										System.out.print(a + "  ");
									}break;
							case 6:  if(a % 2 == 0 && a != 0){
										System.out.println(a + "  ");
										firstLineReturn = true;
										a++;
									}else {
										System.out.print(a + "  ");
									}break;
							case 7: 
								if(a % 1 == 0 && a != 0){
									System.out.println();
									System.out.print(a + "  ");
									dayCount++;
									firstLineReturn = true;
									a++;
								}else {
									System.out.print(a + "  ");
								}
						}
					}
					
					//Here we ask if we have already printed out a new line,
					//and if we have to track the number of days that have
					//been printed using dayCount
					if(firstLineReturn){
						dayCount++;
						System.out.print(a + "  ");
					}
				}
			//When the current day is 8 or 9 we perform similar functions
			//to before, track the days, print a new line.
				
			}else if(a < 10){
				System.out.print(a + "  ");
				dayCount++;
	
				if(dayCount == 7){
					System.out.println();
					dayCount = 0;
				}
			
			//When the current day is greater than 10, we track the 
			//number of days and then reset if we get to 7
				
			}else if(a > 10){
				System.out.print(a + " ");
				dayCount++;
		
				if(dayCount == 7){
					System.out.println();
					dayCount = 0;
				}
			
			//The same process is followed for day 10	
				
			}else if(a == 10) {
				System.out.print(a + " ");
				dayCount++;
			
				if(dayCount == 7){
					System.out.println();
					dayCount = 0;
				}
			}
		}
	}
	
	/** The convertString method ensures that a user can mistype
	 *  and the system will not falter because of that mistype.
	 *  Leading and trailing white space is eliminated as well if the user
	 *  inputs "4." instead of "4". this method also takes care of parsing the String
	 *  to a Integer 
	 * @param part    	the user defined String passed in      
	 * @return numPart  the user defined String passed in turned into a convenient Integer
	 */
	
	public static int convertString(String part) {
		
		//Trim the string, initiate our Integer, ask if it contains "."
		//then parse the String.
		
		part = part.trim();
		int numPart = 0;
		if(part.contains(".")){
			part = part.substring(0 , part.indexOf("."));
		}
		numPart = Integer.parseInt(part);
		return numPart;
	}
	
	/** This method serves as a convenient alternative to 
	 * clearing a screen of all previous text
	 */
	
	public static void clearScreen(){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	/** the main method
	 * 
	 * @param args  command line arguments
	 */
	
	public static void main(String[] args) {
		
		//Initialize Scanner object, initialize our options array
		//clear the screen of all text and display our calendar
		
		input = new Scanner(System.in);
		int[] options = new int[12];
		clearScreen();
		displayCalendar();
		SECalendar SEcal = new SECalendar(); 

		//Fill our options array
		
		for(int a = 1; a < options.length; a++) {
			options[a] = a;
		}
		
		//Print what today is
		
		System.out.println("\n\nToday is " + cal.getTime());
		String inp = "null";
			
			//Print to the user what options are available
		
			do{	System.out.println("\nEnter Option to continue \n or type 'quit' to exit \n");
				System.out.println("1. Next Month");
				System.out.println("2. Previous Month");
				System.out.println("3. New Appointment");
				System.out.println("4. Edit Appointment");
				System.out.println("5. View Appointment");
				System.out.println("6. Go To Today");
				System.out.println("7. Go To Date");
				System.out.println("8. Import Calendar");
				System.out.println("9. Export Calendar");
				System.out.println("10. Month View");
				System.out.println("11. Week View");
				System.out.println("12. Day View");
				System.out.print("\nWhat would you Like to do? \n");
			
				//Take the input given by the user and then analyze it.
				//if it doesn't equal "quit" or a space, then convert the input 
				//to a number and then switch on that number.
				//The following cases that it switches upon are respective
				//to the options listed above.
				
				inp = input.nextLine();
				System.out.println();
				if(!inp.equals("quit") && !inp.equals("")
				 )
				 {
				int in = 0;
				try{
					
					in = convertString(inp);
					 switch(in){
						case 1: //Next month
								cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+ 1));
								clearScreen();
								displayCalendar();
								System.out.println();
								break;
						case 2: //Previous month
								cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - 1));
								clearScreen();
								displayCalendar();
								System.out.println();
								break;
						case 3: //New Appointment
								System.out.println("What do you want to call the appointment?");
				//				String title = input.nextLine();
								System.out.println("What's the date of the appointment? ");
				//				String dateT = input.nextLine();
								System.out.println("How many times will this appointment happen?");
				//				String freqS = input.nextLine(); 
				//				int freq = convertString(freqS);
								System.out.println("Where is the appointment? ");
					//			String location = input.nextLine();
					//			long startTim = 0;
					//			long endTim = 0;
								System.out.println("Please enter description for the appointment? ");
					//			String desc = input.nextLine();
					//			boolean[] a = new boolean[7];
					//			ArrayList<String> ex = new ArrayList<String>();
					//			Appointment b = new Appointment(title, dateT, freq, location, 
					//				 3, startTim, endTim, desc, false, 0, a, ex, 0, 0, 0, false,false);
					//			SEcal.addAppointment(b);
								clearScreen();
								displayCalendar();
								System.out.println();
								break;
						case 4: //Edit Appointment
								System.out.println("Enter the date of the appointment: ");
								String d = input.nextLine();
								ArrayList<Appointment> currentDateApps = SEcal.viewApp(d);
								System.out.println("What's the title of the appointment? ");
								String t = input.nextLine();
								Appointment appToBeEdited = null;
								for (Appointment e: currentDateApps) {
									if (e.getTitle().equals(t)) {
										appToBeEdited = e;
									}
								}
								
								System.out.println("What part of the appointment do you want to edit ?\n");
								System.out.println("0.date \n1.freq \n2.location \n3.priorty \n" +
								"4.startTime \n5. endTime \n6.description ");
								System.out.print("\nEnter choice: ");
								String part = input.nextLine();
								int numPart = convertString(part);
								Appointment edited = new Appointment(appToBeEdited);
								
								//If the user wants to edit an appointment, the user
								//will be asked what part of the appointment they 
								//want to edit
								switch(numPart){
								case 0:	System.out.println("Previous date: " + appToBeEdited.getDate());
										System.out.print("Enter change for date: ");
										String newDate = input.nextLine();
										edited.setDate(newDate);
										break;
								case 1:	//System.out.println("Previous frequency: " + appToBeEdited.getFreq());
										System.out.println("Enter change for frequency: ");
										String newFre = input.nextLine();
										int newFreq = convertString(newFre);
									//	edited.setFreq(newFreq);
										break;
								case 2: System.out.println("Previous location: " + appToBeEdited.getLocation());
										System.out.println("Enter change for location: ");
										String newLocation = input.nextLine();
										edited.setLocation(newLocation);
										break;
								case 3: System.out.println("Previous priority: " + appToBeEdited.getPriority());
									 	System.out.println("Enter change for priorty: ");
										String newPriorty = input.nextLine();
										int newPriort = convertString(newPriorty);
										edited.setPriority(newPriort);	
										break;
								case 4: System.out.println("Previous start-time: " + appToBeEdited.getStartTime());
						        		System.out.println("Enter change for start-time: ");
										String startTime = input.nextLine();
										startTime = startTime.trim();
										long start = Long.parseLong(startTime);
										edited.setStartTime(start);
										break;
								case 5: System.out.println("Previous end-time: " + appToBeEdited.getEndTime());
										System.out.println("Enter change for end-time: ");
										String endTime = input.nextLine();
										endTime = endTime.trim();
										long end = Long.parseLong(endTime);
										edited.setEndTime(end);
										break;
								case 6: System.out.println("Previous description: " + appToBeEdited.getDesc());
										System.out.println("Enter change for description: ");
										String descr = input.nextLine();
										edited.setDesc(descr);
										break;
								}
								
								//save the newly made appointment
								
								SEcal.saveEditedApp(appToBeEdited, edited);
								clearScreen();
								displayCalendar();
								break;
						case 5: //view Appointment
								
								break;
						case 6: //Go To Today
								//Going to today will show the current month
								//and it will display whether or not there are 
								//any appointments for today
								cal.set(Calendar.MONTH, currentMonth);
								Integer mont = cal.get(Calendar.MONTH);
								Integer day = cal.get(Calendar.DAY_OF_MONTH);
								Integer year = cal.get(Calendar.YEAR);
								
								String todayD = "0" + (mont.toString()) + "/" + "0" +(day.toString()) + "/" + 
												((year.toString()).substring(2));	 
								clearScreen();
								displayCalendar();
								System.out.println("\n\n" + cal.getTime());
								System.out.println("\n" + SEcal.viewApp(todayD));
								if((SEcal.viewApp(todayD)).size() == 0){
									System.out.println("\nThis day contains no appointments.");
								}
								break;
						case 7: //Go To Date
								System.out.println("What date would you like to go to? ");
								String date = input.nextLine();		
								clearScreen();
								displayCalendar();
								System.out.println("\n\n" + date);
								System.out.println("\n" + SEcal.viewApp(date));
								if((SEcal.viewApp(date)).size() == 0){
									System.out.println("\nThis day contains no appointments.");
								}
								break;
						case 8: //Import Calendar 
								System.out.println("What calendar would you like to import?\n");
								String fileName = input.nextLine();
								clearScreen();
								displayCalendar();
								System.out.println();
								SEcal.importCalendar(fileName);  
								break;
						case 9://Export Calendar
								if(SEcal.isModified()){
									System.out.println("What would you like to save the calendar as?");	
									String file = input.nextLine();
									SEcal.exportCalendar(file);
									clearScreen();
									displayCalendar();
								} else {
									clearScreen();
									displayCalendar();
									System.out.println("\n\nCalendar does not contain any changes");
								}
								break;
						case 10: //Month View
								 clearScreen();
					 		   	 displayCalendar();
					 		     System.out.println();	
					 		  	 break;	
						case 11://Week View
								//Based on what week it is, it will show what week it 
								//is and then the days that ensue.
							
								clearScreen();
								int calcday = cal.get(Calendar.DAY_OF_MONTH) - (cal.get(Calendar.DAY_OF_WEEK) - 1);
								if(calcday <= 0){
									calcday = 1;
								}
								
								System.out.println("Week of  " + calcday + "\n");
								System.out.println("S  M  T  W  R  F  S");
								
								for(int f = calcday; f <= calcday + 6; f++){
									if(f < 10){
										if(f == 0){
											System.out.print("   ");
										}else{
										System.out.print(f + "  ");
										}
									}else if(f >= 10 && f != 0){
										System.out.print(f + " ");
									}
								} System.out.println("\n");
								  break;
					    case 12: //Day View
					    		 //Day view takes the current hour and displays 
					    		 //a few hours before and after the current hour.
					    		//As well as show you specifically what time it is.
					    		 clearScreen();
								 int hour = cal.get(Calendar.HOUR);
					    		 int decrement = 5;
					    		 boolean done = false;
					    		 boolean corrected = false;
					    		 
					    		 System.out.println(cal.getTime() + "\n");
					    		 while(decrement < 5 || !done){
					    		 	if(!done){
					    		 		int time = hour - decrement;
					    		 		if(time <= 0){
					    		 			time = 12 - (time * (-1));
					    		 			corrected = true;
					    		 		}
					    		 		decrement--;
					    		 		if(cal.get(Calendar.AM_PM) == 0 && corrected){
					    		 			if(time == hour){
					    		 				System.out.println(time + ":00" + " <----------" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
					    		 			}else{
					    		 				System.out.println(time + ":00");
					    		 			}	
					    		 		}else if(time == hour){
					    		 			System.out.println(time + ":00" + " <----------" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
					    		 		}else {
					    		 			System.out.println(time + ":00");
					    		 		}
					    		 		if(decrement - 1 < 0){
					    		 		decrement = 0;
					    		 		done = true;	
					    		 		}
					    		 		corrected = false;
					    		 	}else if(done){
					    		 		int time = hour + decrement;
					    		 		if(time > 12){
					    		 			time = time - 12;
					    		 			corrected = true;
					    		 		}
					    		 		decrement++;
					    		 		if(cal.get(Calendar.AM_PM) == 0 && corrected){
					    		 			if(time == hour){
					    		 				System.out.println(time + ":00" + " <----------" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
					    		 			}else {
					    		 				System.out.println(time + ":00");
					    		 			}
					    		 		}else if(time == hour) {
					    		 			System.out.println(time + ":00" + " <----------" + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
					    		 		}else {
					    		 			System.out.println(time + ":00");
					    		 		}
					    		 	}
					    		 }
					 }
				//Ensure that if the user mistypes anywhere that the program
				//will continue to run and issue a message telling the user to 
				//to input a valid option
					 
				}catch(NumberFormatException e){
					System.err.println("\n Not a valid option, pick an option listed above.\n");
				}catch(NullPointerException f){
					clearScreen();
					displayCalendar();
				}
					 if(in > 12){
					 	System.out.println("\n Not a valid option, pick an option listed above.\n");
					 } 		  
				 }
				
			//As long as the user doesn't input "quit" the program will continue to
			//function
				
			}while(!inp.equals("quit"));
			cal = new GregorianCalendar(); 			
	System.out.println("quitting the program...");
	}
}//CommandCalendar