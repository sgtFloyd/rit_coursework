using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

/**
 * 
 * Author: Gabriel Smith (ges7506@rit.edu)
 * PLC Project 1 09.26.10
 * 
 * This class opens the specified DFA definition file,
 * runs it through the scanner and feeds that into the
 * parser. If parsing finishes successfully, it then
 * generates an output file from the parser using the
 * specified output file name.
 */ 
class DFAGen {

    static void Main(string[] args) {
        String fileNameIn = "";
        String fileNameOut = "";
        Boolean debugMode = false;

        // Parse command line options
        if ( args.Length == 3 && args[0].Equals("-d")) {
            debugMode = true;
            fileNameIn = args[1];
            fileNameOut = args[2];
        } else if ( args.Length == 2 ) {
            fileNameIn = args[0];
            fileNameOut = args[1];
        } else {
            Console.WriteLine("Usage: DFAGen [-d] DFAProgramFile C#ClassName"); return;
        }

        // create file reader
        TextReader reader = null;
        try {
            reader = File.OpenText( fileNameIn );
        } catch (Exception e) {
            Console.WriteLine("File Error:  " + e.Message);
            return;
        }

        DFAScanner scanner = new DFAScanner(reader);
        DFAParser parser = new DFAParser(scanner);
        parser.setDebug(debugMode);
        parser.parse();

        // write out the generated file
        parser.outputToFile( fileNameOut );
    }
}
