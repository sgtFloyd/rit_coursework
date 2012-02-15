<?php
	/////
	// PLC Project #3, Question 2
	// Author: Gabriel Smith ( ges7506@rit.edu )
	// 10-26-2010
	/////
	
	// Collect text from user via stdin and store lines in an array.
	$lines = array();
	echo "Enter text, line by line. Finish with an empty line.\n";
	$count = 0;
	while( ($lines[$count] = trim(fgets(fopen("php://stdin","r")))) != "" ) {
		$count++;
	}

	// For each line in the input
	foreach( $lines as $line ) {
		$lineLen = mb_strlen( $line );
		
		// For each character in the string, transform it individually
		for ( $i = 0; $i<$lineLen; $i++ ) {
			$char = ord( $line[$i] );
			
			// lower case letters (97-122)
			if ( $char >= 97 && $char <= 122 ) {
				$line[$i] = chr( (($char - 84) % 26) + 97 );
				
			// upper case letters (65-90)
			} else if ( $char >= 65 && $char <= 90 ) {
				$line[$i] = chr( (($char - 52) % 26) + 65 );
			}
			
			echo $line[$i]; // output the transformed character
		}
		echo "\n";
	}
	
?>                                                            