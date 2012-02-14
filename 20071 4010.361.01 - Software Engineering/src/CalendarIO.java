/*
 * CalendarIO.java
 *
 * Version:
 *     $Id: CalendarIO.java,v 1.2 2007-11-06 19:45:49 vxj4346 Exp $
 *
 * Revisions:
 *     $Log: CalendarIO.java,v $
 *     Revision 1.2  2007-11-06 19:45:49  vxj4346
 *     fixed spaces
 *
 *     Revision 1.1  2007-10-09 22:30:12  hgt9090
 *     *** empty log message ***
 *
 *     Revision 1.2  2007-09-29 19:01:22  vxj4346
 *     Implemented read, save methods
 *
 *     Revision 1.1  2007-09-27 18:40:30  vxj4346
 *     Fixed some type-Os
 *
 *     Revision 1.2  2007-09-25 19:26:37  vxj4346
 *     Implemented constructor
 *
 *     Revision 1.1  2007-09-25 18:20:13  vxj4346
 *     Testing commit
 *
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads and writes appointment, and configuration files
 *
 * @author sch5387
 * @author vxj4347
 */
public class CalendarIO {
	/**
	 * Reads configuration file
	 * 
	 * 	@param  source  configuration file name 
	 *  @return         array list of Strings
	 */
	public ArrayList<String> getConfig(String source) {
		BufferedReader in = null;
		Scanner sc;
		try {
            in = new BufferedReader(new FileReader(source));
        } catch (FileNotFoundException e) {
        	System.err.println(e.getMessage());
        	System.err.println( source + " not found." );
        }
        sc = new Scanner( in );	
        ArrayList<String> config = new ArrayList<String>();
        while(sc.hasNext()) {
        	config.add( sc.nextLine() );
        }
        return config;
	}

	/**
	 * Saves calendar configuration to a file
	 *  
	 * @param  config   a list of Strings 
	 * @param  filename destination file name
	 */	
	public void saveConfig(List<String> config, String filename) {
		FileWriter out = null;
		try {
			File outFile = new File(filename);
			out = new FileWriter(outFile);
			for (int i = 0; i < config.size(); i++) {
				out.write(config.get(i) + "\n");
			}
		}
		catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		finally {
		  try {
		    if ( out != null ) {
			  //close "out" and its underlying streams
			  out.close();
			}
		  }
		  catch (IOException ex){
		    System.err.println("Cannot close output stream." + ex.getMessage());
		  }
		}
	}
	
	/**
	 * Saves a list of appointments to a specified file
	 *  
	 * @param  apps     a list of appointments
	 * @param  filename destination file name
	 */
	public void saveAppointments( List<Appointment> apps, String filename) {
		ObjectOutput output = null;
		try {
			OutputStream file = new FileOutputStream( filename );
			OutputStream buffer = new BufferedOutputStream( file );
			output = new ObjectOutputStream( buffer );
			output.writeObject( apps );
		}
		catch(IOException ex){
			System.err.println(ex.getMessage());
		}
		finally {
			try {
			  if (output != null) {
			    //flush and close "output" and its underlying streams
			    output.close();
			  }
			}
		    catch (IOException ex ){
		    	System.err.println(ex.getMessage());
		    }
		}
	}

	/*
	 * Read appointments from a source file.
	 * 
	 * @param  source  a source file 
	 * @return         a list of appointments
	 */
	public List<Appointment> getAppointments(String source) {
		List<Appointment> appointments = new ArrayList<Appointment>();
		ObjectInput input = null;
	    try{
	      //use buffering
	      InputStream file = new FileInputStream( source );
	      InputStream buffer = new BufferedInputStream( file );
	      input = new ObjectInputStream ( buffer );
	      appointments = (List<Appointment>)input.readObject();
	    }
	    catch(IOException ex){
	    	System.err.println("Cannot perform input." + ex.getMessage());
	    }
	    catch (ClassNotFoundException ex){
	    	System.err.println("Unexpected class found upon input." + ex.getMessage());
	    }
	    finally{
	      try {
	        if ( input != null ) {
	          //close "input" and its underlying streams
	          input.close();
	        }
	      }
	      catch (IOException ex){
	    	  System.err.println("Cannot close input stream." + ex.getMessage());
	      }
	    }
		return appointments;
	}
	
	// test this class
	/*
	public static void main(String[] args) {
		CalendarIO calIO = new CalendarIO();
		//calIO.getAppointments();
		List<Appointment> apps = new ArrayList<Appointment>();
		apps.add(new Appointment("09/27/2007", 5, 
			"414 Krieger Rd. Webster, NY 14580", 3, 555666555, "will have a party" ));
		apps.add(new Appointment("09/27/2007", 5,  
				"100 Krieger Rd. Webster, NY 14580", 3, 555666555, "will have a party" ));
		apps.add(new Appointment("09/27/2007", 5,  
				"200 River Rd. Webster, NY 14580", 3, 555666555, "will have a party" ));
		apps.add(new Appointment("09/27/2007", 5, 
				"300 Santa Barbara Rd. Webster, NY 14580", 3, 555666555, "will have a party" ));
		calIO.saveAppointments(apps, "Z:\\eclipse\\workspace\\SE1team\\src\\savedCal.txt");
		List<Appointment> recoveredApps = calIO.getAppointments("Z:\\eclipse\\workspace\\SE1team\\src\\savedCal.txt");
		System.out.println(recoveredApps);
		ArrayList<String> conf = new ArrayList<String>();
		conf.add("c:\\calendar\\Appointment");
		conf.add("open new calendar");
		System.out.println(conf);
		calIO.saveConfig(conf, "Z:\\eclipse\\workspace\\SE1team\\src\\config.txt" );
		ArrayList<String> recoveredConf = calIO.getConfig("Z:\\eclipse\\workspace\\SE1team\\src\\config.txt");
		System.out.println(recoveredConf);
	} */
}