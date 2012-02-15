<?php
	/////
	// PLC Project #3, Question 1
	// Author: Gabriel Smith ( ges7506@rit.edu )
	// 10-26-2010
	/////

	// Collect 5 numbers from the user through stdin
	$list = array();
	for ( $i = 0; $i < 5; $i++ ) {
		echo "Enter number: ";
		$list[$i] = trim(fgets(fopen("php://stdin","r")));
	}
	
	// Sort the numbers numerically
	sort($list);
	
	// Print the numbers in ascending and descending order
	echo "Ascending Order:";
	for ( $i = 0; $i < 5; $i++ ) {
		echo " ", $list[$i];
	}
	echo "\nDescending Order:";
	for ( $i = 4; $i >=0; $i-- ) {
		echo " ", $list[$i];
	}
	
?>                                                            