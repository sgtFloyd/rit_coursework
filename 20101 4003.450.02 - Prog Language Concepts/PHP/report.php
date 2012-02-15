<?php
	/////
	// PLC Project #3, Question 4
	// Author: Gabriel Smith ( ges7506@rit.edu )
	// 10-28-2010
	/////
	function plcLog ( $msg ) {
		$logFile = fopen("plc.log", "a");
		fwrite( $logFile, date("m/d/y : H:i:s", time()).": ".$msg."\n" );
	}
	
	// get input values
	$name = trim($_REQUEST["name"]);
	$date1 = trim($_REQUEST["date1"]);
	$date2 = trim($_REQUEST["date2"]);
	$realDate1 = null;
	$realDate2 = null;
	
	$error = false;
	
	// validate name input
	if ( $name != "" ) {
		$namePattern = "/([\w\s]*)$/";
		preg_match( $namePattern, $name, $nameMatch );
		if ( empty($nameMatch[1]) ){
			$error = true;
			echo "<b>Error:</b> Please enter a valid name.";
			plcLog( "Invalid name input: {$name}" );
		}
	}

	if ( !$error ) {	
		// cannot have value in just one date
		if ( (empty($date1) && !empty($date2)) || (!empty($date1) && empty($date2)) ) {
				$error = true;
				echo "<b>Error:</b> Please enter a complete date range.";
				plcLog ("Report Generator: Unmatched date range found.");
				
		// if both dates have values
		} else if ( $date1 != "" && $date2 != "" ) { 
			$datePattern = "/([\d]{1,2})\/([\d]{1,2})\/([\d]{4})$/";
			
			// Parse date1 into usable format
			preg_match($datePattern, $date1, $date1Match);
			if ( !empty($date1Match[1]) ){
				$day = $date1Match[2];
				$month = $date1Match[1];
				$year = $date1Match[3];
				$realDate1 = date(DATE_ATOM, mktime(0, 0, 0, $month, $day, $year));
			} else {
				plcLog( "Report Generator: Bad date found: {$date1}" );
				$error = true;
				echo "<b>ERROR: Bad date found:</b> {$date1}<br>";
			}

			// Parse date2 into usable format
			preg_match($datePattern, $date2, $date2Match);
			if ( !empty($date2Match[1]) ){
				$day = $date2Match[2];
				$month = $date2Match[1];
				$year = $date2Match[3];
				$realDate2 = date(DATE_ATOM, mktime(0, 0, 0, $month, $day, $year));
			} else {
				plcLog( "Report Generator: Bad date found: {$date2}" );
				$error = true;
				echo "<b>ERROR: Bad date found:</b> {$date2}<br>";
			}
		}
	}
	
	// generate report
	if ( !$error ) {
		if ( $name == "" ) {
			if ( $realDate1 ==  null && $realDate2 == null ) {
				plcLog( "Generating complete report." );
				// generate complete report
				$sql = "SELECT * FROM People, Expenses WHERE People.person_id = Expenses.person_id";
				echo "Generating complete report.<br>";
			} else {
				plcLog( "Generating report on just a date range: {$realDate1} - {$realDate2}" );
				// generate report on just date range
				$sql = "SELECT * FROM People, Expenses WHERE People.person_id = Expenses.person_id AND Expenses.expense_date >= '{$realDate1}' AND Expenses.expense_date <= '{$realDate2}'";
				echo "Generating report on just a date range: {$realDate1} - {$realDate2}<br>";
			}
		} else {
			if ( $realDate1 ==  null && $realDate2 == null ) {
				plcLog( "Generating report on just a name: {$name}" );
				// generate report on just name
				$sql = "SELECT * FROM People, Expenses WHERE People.person_id = Expenses.person_id AND People.name = '{$name}'";
				echo "Generating report on just a name: {$name}<br>";
			} else {
				plcLog( "Generating report on name: {$name} and date range: {$realDate1} - {$realDate2}" );
				// generate report on name and date range
				$sql = "SELECT * FROM People, Expenses WHERE People.person_id = Expenses.person_id AND People.name = '{$name}' AND Expenses.expense_date >= '{$realDate1}' AND Expenses.expense_date <= '{$realDate2}'";
				echo "Generating report on name: {$name} and date range: {$realDate1} - {$realDate2}<br>";
			}
		}
		
		//connect to database and execute SQL
		$con = mysql_connect( "huckleberryroad.com", "hucklebe_plc" , "0WtMN@M}QABk");
		if ( !$con ) {
			$error = true;
			plcLog( "Report generator failed to connect to database: hucklebe_plc@huckleberryroad.com" );
			echo "<b>ERROR: Could not connect to database.</b><br>";
		} else {
			mysql_select_db("hucklebe_plc", $con);
			plcLog( "Report generator connected to database: hucklebe_plc@huckleberryroad.com/hucklebe_plc" );
			
			plcLog( "Report generator executing SQL: {$sql}" );
			$result = mysql_query( $sql );
			$nameTotal = array();
			$itemTotal = array();
			$overallTotal = 0;
			while( $row = mysql_fetch_array($result) ) {
				// gather results
				$name = $row['name'];
				$item = $row['item'];
				$cost = $row['cost'];
				
				$nameTotal[$name] = $nameTotal[$name] + $cost;
				$itemTotal[$item] = $itemTotal[$item] + $cost;
				$overallTotal += $cost;
			}
			mysql_close($con); // close database connection
			
			echo "<br>";
			foreach( $nameTotal as $name => $total ) {
				echo "{$name}: \${$total}<br>";
			}
			echo "<br>";
			foreach( $itemTotal as $item => $total ) {
				echo "{$item}: \${$total}<br>";
			}
			echo "<br>";
			echo "Total Expenses: \${$overallTotal}<br>";
		}
	}
?>