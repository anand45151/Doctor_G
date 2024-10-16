<?php

// Include database connection file
include('cons.php');

// Set headers to allow cross-origin resource sharing (CORS)
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

// Initialize response array
$response = array();

// Check request method
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Check if required POST parameters are set
    if (isset($_POST['sname'], $_POST['email'], $_POST['city'])) {
        // Sanitize input data
        $sname = mysqli_real_escape_string($conn, $_POST['sname']);
        $email = mysqli_real_escape_string($conn, $_POST['email']);
        $city = mysqli_real_escape_string($conn, $_POST['city']);

        // Check if student with the given name already exists
        $query = "SELECT * FROM student WHERE sname = '$sname'";
        $result = mysqli_query($conn, $query);

        if (!$result) {
            // Query execution failed
            $response['status'] = 'error';
            $response['message'] = "Database query failed: " . mysqli_error($conn);
        } else {
            // Check if any rows were returned
            if (mysqli_num_rows($result) > 0) {
                $response['status'] = 'error';
                $response['message'] = 'Student already exists';
            } else {
                // Insert new student record
                $insertQuery = "INSERT INTO student (sname, email, city) VALUES ('$sname', '$email', '$city')";
                $insertResult = mysqli_query($conn, $insertQuery);

                if ($insertResult) {
                    $response['status'] = 'success';
                    $response['message'] = "Student inserted successfully";
                } else {
                    $response['status'] = 'error';
                    $response['message'] = "Failed to insert student: " . mysqli_error($conn);
                }
            }
        }
    } else {
        $response['status'] = 'error';
        $response['message'] = "Missing required POST parameters";
    }
} else {
    $response['status'] = 'error';
    $response['message'] = "Invalid request method. Only POST method is allowed.";
}

// Output JSON response
echo json_encode($response);

// Close database connection
mysqli_close($conn);

?>
