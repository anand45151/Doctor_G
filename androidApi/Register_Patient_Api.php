<?php
$conn = mysqli_connect("localhost", "root", "1234");
mysqli_select_db($conn, "corephpadmin");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$firstName = $_POST['f_name'];
$lastName = $_POST['l_name'];
$gender = $_POST['gender'];
$address = $_POST['address'];
$city = $_POST['city'];
$state = $_POST['state'];
$phone = $_POST['phone'];
$email = $_POST['email'];
$dob = $_POST['dob'];

error_log("Date of Birth: " . $dob);

if (empty($dob) || !validateDate($dob)) {
    $response['message'] = 'Invalid date of birth format or empty value. Please use YYYY-MM-DD.';
    echo json_encode($response);
    exit;
}

function validateDate($date, $format = 'Y-m-d') {
    $d = DateTime::createFromFormat($format, $date);
    return $d && $d->format($format) === $date;
}

$dob = date('Y-m-d', strtotime($dob));

// Check if the user already exists based on email
$query = "SELECT * FROM patients WHERE email = '$email'";
$raw = mysqli_query($conn, $query);

$count = mysqli_num_rows($raw);
if ($count > 0) {
    // User already exists
    $response['message'] = 'User already exists';
} else {
    // Insert new user
    $query2 = "INSERT INTO patients (f_name, l_name, gender, address, city, state, phone, email, date_of_birth) 
               VALUES ('$firstName', '$lastName', '$gender', '$address', '$city', '$state', '$phone', '$email', '$dob')";
    
    $res = mysqli_query($conn, $query2);
    
    if ($res) {
        $response['message'] = 'Registration successful';
    } else {
        $response['message'] = 'Error: ' . mysqli_error($conn) . ' Query: ' . $query2;
    }
}

// Return response as JSON
header('Content-Type: application/json');
echo json_encode($response);

// Close connection
mysqli_close($conn);
?>
