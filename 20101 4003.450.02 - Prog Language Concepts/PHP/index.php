<html> 
<head></head> 
<body> 

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
 
	$upload_dir = './upload_cache/'; // directory to store the files
	if ( !is_dir("$upload_dir") ) { mkdir("$upload_dir", 0700); } // create directory
	$transList = array();
	$error = false;
	
	if ( empty($_FILES) ) {
	/***** show the upload form if no upload file given *****/
?>

		<h2>Gabriel Smith - PLC Project 3</h2><br>
		<form enctype="multipart/form-data" action="<?php echo $_SERVER['PHP_SELF'];?>" method="POST"> 
			<b>Upload your expense report:</b>
			<input name="uploaded" type="file" />
			<input type="submit" value="Upload" /> 
		</form>
		<br>
		<form action="report.php" method="POST">
			<b>Generate expense report</b><br>
			Name: <input type="text" name="name" />
			<input type="submit" value="Generate" /><br>
			Date Range: <input type="text" name="date1" />-<input type="text" name="date2" /><br>
			Dates must be formatted as <b>MM/DD/YYYY</b>.<br>
			Leave fields blank to collect data for all values.
		</form>
		<br>
		<b><a href="plc.log">View Log File</a></b>
<?php 
	} else {
		$fileType = $_FILES["uploaded"]["type"];
		$fileName = basename($_FILES['uploaded']['name']);
		plcLog( "Attempt to upload file ".$fileName." of type ".$fileType );
		if ( $fileType != "text/plain" ) {
			$error = true;
			echo "<b>ERROR: Only plain text files are supported.</b><br>";
		} else {
			/***** upload the file *****/
			$target = $upload_dir . $fileName ; 
			if ( move_uploaded_file($_FILES['uploaded']['tmp_name'], $target) ) {
				// a file was uploaded 
				echo "The file <b>", $fileName, "</b> has been uploaded";
				sleep(1); // ensure the system has closed the file before loading it
				
				/***** open and parse the file *****/
				echo "<br>Opening and parsing <b>", $fileName, "</b><br>"; 
				$dataFile = fopen($upload_dir . $fileName, "rb");  
				
				$id = "([\w\s]*)"; // Regex tokens
				$date = "([\d]{1,2}\/[\d]{1,2}\/[\d]{4})";
				$cost = "[\$]([\d]+\.[\d]{2})";
				
				$namePattern = "/Name: ".$id."$/"; // Regex Patterns
				$transPattern = "/".$id."\, ".$date."\: ".$cost."$/";
				
				$curId = null;
				while ( !feof($dataFile) ) {
					// Parse the file line-by-line
					$curLine = trim( fgets($dataFile) );
					if ( !$error && $curLine != "" ) {
						// see if line matches either the name or trans pattern
						preg_match($namePattern, $curLine, $nameMatches);
						preg_match($transPattern, $curLine, $transMatches);

						if ( !empty($nameMatches[1]) ){
							// found a Name line, set current person
							$curId = $nameMatches[1];
						} else if ( !empty($transMatches[1]) ){
							// found a Transaction line, save data found in it
							if ( curId == null ) {
								$error = true;
								echo "<b>ERROR: No ID linked with transaction: </b>", $curLine, "<br>";
							} else {
								$transId = $transMatches[1];
								$transDate = $transMatches[2];
								$transCost = $transMatches[3];
								array_push( $transList, array($curId, $transId, $transDate, $transCost) );
								plcLog( "Found valid expense for {$curId}: {$transId} on {$transDate} for {$transCost}" );
							}
						} else {
							$error = true;
							echo "<b>ERROR: Malformed input at line: </b>", $curLine, "<br>";
						}					
					}
				}
				fclose($dataFile); // close the file 
			} else {
				echo "Sorry, there was a problem uploading your file.<br>";
			}
			
		}
		if ( !$error ) {
			/***** file successfully parsed and stored in memory, insert it into database *****/
			$dbHost = "huckleberryroad.com";
			$dbUser = "hucklebe_plc";
			echo "File successfully parsed. Connecting to database <b>{$dbUser}@{$dbHost}</b><br>";
			$con = mysql_connect( $dbHost, $dbUser , "0WtMN@M}QABk");
			if ( !$con ) {
				$error = true;
				plcLog( "Failed to connect to database: hucklebe_plc@huckleberryroad.com" );
				echo "<b>ERROR: Could not connect to database.</b><br>";
			} else {
				mysql_select_db("hucklebe_plc", $con);
				plcLog( "Connected to database: hucklebe_plc@huckleberryroad.com/hucklebe_plc" );
				
				$datePattern = "/([\d]{1,2})\/([\d]{1,2})\/([\d]{4})/";

				echo "Inserting expenses into database.<br>";
				foreach ( $transList as $tran ) {
					// convert date to proper format
					preg_match($datePattern, $tran[2], $dateMatches);
					if ( !empty($dateMatches[1]) ){
						$day = $dateMatches[2]; $month = $dateMatches[1]; $year = $dateMatches[3];
					} else {
						plcLog( "Bad date found: {$tran[2]}" );
						$error = true;
						echo "<b>ERROR: Bad date found:{$tran[2]}</b><br>";
					}
					
					// define transaction details
					$expense_date = date(DATE_ATOM, mktime(0, 0, 0, $month, $day, $year));
					$person_name = $tran[0];
					$item = $tran[1];
					$cost = $tran[3];
					
					// check if person already exists and grab person_id
					$sql = "SELECT * FROM People WHERE name = '{$person_name}'";
					plcLog( " Executing SQL: {$sql}" );
					$result = mysql_query( $sql );
					$personExists = false;
					while( $row = mysql_fetch_array($result) ) {
						$personExists = true;
						$person_id = $row['person_id'];
					}
					
					// insert person into database if they're not already there
					if ( !$personExists ) {
						plcLog( $person_name." doesn't exist in database. Creating a new entry." );
						$sql = "INSERT INTO People(name) VALUES ('".$person_name."')";
						mysql_query($sql);
						plcLog( " Executing SQL: {$sql}" );
						$result = mysql_query( "SELECT * FROM People WHERE name = '".$person_name."'" );
						while( $row = mysql_fetch_array($result) ) {
							$person_id = $row['person_id'];
						}
					}
					
					// check if expense already exists in database
					$sql = "SELECT * FROM Expenses WHERE person_id = '{$person_id}'"
											." AND item = '{$item}'"
											." AND expense_date = '{$expense_date}'";
					plcLog( " Executing SQL: {$sql}" );
					$result = mysql_query( $sql, $con );
					
					$transExists = false;
					while( $row = mysql_fetch_array($result) ) {
						$transExists = true;
						$expense_id = $row['expense_id'];
					}
					if ( !$transExists ) { // insert transaction into database if not already there
						$sql = "INSERT INTO Expenses(person_id, item, cost, expense_date) VALUES ('{$person_id}', '{$item}', '{$cost}', '{$expense_date}')";
						plcLog( " Executing SQL: {$sql}" );
						mysql_query( $sql, $con );
					} else {
						// TODO: prompt user to either update or overwrite existing expenses
						plcLog("Entry already exists for: {$person_name}. Item: {$item}, Date: {$expense_date}");
					}
					
				}
				echo "Expense report <b>complete</b>. Closing database connections.";
				mysql_close($con); // close database connection
			}
		}
	}
?> 
</body></html> 

<?php 
// check for files already in the upload directory and delete them
$files = scandir( $upload_dir );
foreach ( $files as $num => $fname ) { 
    if ( $fname != "." && $fname != ".." && file_exists("{$upload_dir}{$fname}") ) {
        if ( unlink("{$upload_dir}{$fname}") ){ 
            echo "<br>Deleted <b>{$fname}</b>"; 
        }
    }
}
?> 