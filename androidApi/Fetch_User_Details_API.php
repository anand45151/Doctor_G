<?php

// Database connection using correct credentials
$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");

// Check the connection
if ($conn->connect_error) {
    echo json_encode(["error" => "Connection failed: " . $conn->connect_error]);
    exit;
}

// Fetch user details by email
if (isset($_GET['email'])) {
    $email = $_GET['email'];

    // Prepare SQL statement using '?' placeholder for email
    $stmt = $conn->prepare("SELECT id, f_name, l_name, address FROM customers WHERE email = ?");
    $stmt->bind_param("s", $email);  // 's' means the parameter is a string
    $stmt->execute();
    $result = $stmt->get_result();
    
    if ($result->num_rows > 0) {
        $user = $result->fetch_assoc();
        echo json_encode([
            "success" => true,
            "patientId" => $user['id'],  // Changed 'patient_id' to 'id'
            "firstName" => $user['f_name'],  // Changed 'first_name' to 'f_name'
            "lastName" => $user['l_name'],  // Changed 'last_name' to 'l_name'
            "address" => $user['address']
        ]);
    } else {
        echo json_encode(["success" => false, "message" => "User not found"]);
    }

    $stmt->close();
} else {
    echo json_encode(["success" => false, "message" => "Email is required"]);
}

$conn->close();
?>
