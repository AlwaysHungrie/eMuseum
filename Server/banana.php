<?php
$servername = "localhost";
$username = "id808947_always_hungrie";
$password = "gocashless";
$database = "id808947_legendb";

$number = intval($_GET['id']);
$dbhandle = mysqli_connect($servername, $username, $password)  
or die("Unable to connect to MySQL");  
echo "";  
// connect with database  
$selected = mysqli_select_db($dbhandle, $database)  
or die("Could not select examples");  
//query fire  
$result = mysqli_query($dbhandle,"SELECT ID,Title,Short_Description,Filename,Long_Description,Type,Period,Religion,Dynasty from metadata where ID=$number;");  
//$result = mysqli_query($dbhandle,"SELECT ID,Title from metadata ORDER BY Title;"); 
$json_response = array();  
// fetch data in array format  
while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {  
// Fetch data of Fname Column and store in array of row_array
$row_array['ID'] = $row['ID'];  
$row_array['Title'] = $row['Title'];
$row_array['Period'] = $row['Period'];
$row_array['Religion'] = $row['Religion'];
$row_array['Dynasty'] = $row['Dynasty'];
$row_array['Type'] = $row['Type'];
$row_array['Short_Description'] = $row['Short_Description'];
$row_array['Long_Description'] = $row['Long_Description'];
$row_array['Filename'] = $row['Filename'];

//push the values in the array  
array_push($json_response,$row_array);  
}  
//  
echo json_encode($json_response); 
mysqli_free_result($result);
?>
