<?php

// Database connection
$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// SQL query to fetch all doctor records
$query = "SELECT Doctor_id, Doctor_Name, Doctor_Specialty, Doctor_Experiences, Doctor_Location, Doctor_Photo FROM doctors";
$result = mysqli_query($conn, $query);

// Initialize an array to hold the data
$data = array();

// Base URL for images
$baseUrl = "http://loaclhost/uploads/";

// Fetch data and add to the array
while ($row = mysqli_fetch_assoc($result)) {
    // Append base URL to the image path
    $row['Doctor_Photo'] = $baseUrl . $row['Doctor_Photo'];
    $data[] = $row;
}

// Set the content type to JSON
header('Content-Type: application/json');

// Output the data in JSON format
echo json_encode($data);

// Close the database connection
mysqli_close($conn);

?>
