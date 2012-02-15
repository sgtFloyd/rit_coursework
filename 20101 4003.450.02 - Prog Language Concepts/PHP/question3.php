<?php
	/////
	// PLC Project #3, Question 3
	// Author: Gabriel Smith ( ges7506@rit.edu )
	// 10-26-2010
	/////
	
	// Collect the input matrix from the user
	$count = 0;
	do {
		echo "Enter a row: ";
		$row = trim(fgets(fopen("php://stdin","r")));
		if ( $row != "" ) {
			// Split each row around "," and store it in the matrix
			$matrix[$count] = explode( ",", $row );
			$count++;
		}
	} while ( $row != "" );
	
	// Transpose the original matrix into a new one
	for ( $i = 0; $i < count( $matrix ); $i++ ) {
		for ( $j = 0; $j < count( $matrix[$i] ); $j++ ) {
			$transposed[$j][$i] = $matrix[$i][$j];
		}
	}
	
	// Print original and transposed matrices
	echo "\nInput:\n";
	print_r( $matrix );
	echo "\nTransposed Matrix:\n";
	print_r( $transposed );
	
?>                                                            