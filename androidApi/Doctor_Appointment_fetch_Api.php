<?php

$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$doctor_id = isset($_GET['doctor_id']) ? intval($_GET['doctor_id']) : null;

if ($doctor_id) {
    $query = "
        SELECT 
            c.f_name AS patient_first_name,
            c.l_name AS patient_last_name,
            c.date_of_birth AS patient_dob,
            a.disease_description,
            a.Appointment_date
        FROM 
            appointments a
        JOIN 
            customers c ON a.Patient_id = c.id
        WHERE 
            a.Doctor_id = ?"; 

    $stmt = mysqli_prepare($conn, $query);
    if (!$stmt) {
        die("SQL Prepare Error: " . mysqli_error($conn));
    }

    mysqli_stmt_bind_param($stmt, "i", $doctor_id);
    mysqli_stmt_execute($stmt);

    $result = mysqli_stmt_get_result($stmt);

    if (!$result) {
        die("SQL Query Error: " . mysqli_error($conn)); // Log any SQL error
    }

    $data = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row; // Add each row to the data array
    }

    if (empty($data)) {
        echo json_encode(array("message" => "No appointments found."));
    } else {
        header('Content-Type: application/json');
        echo json_encode($data);
    }

    mysqli_stmt_close($stmt);

} else {
    header('Content-Type: application/json');
    echo json_encode(array("error" => "Doctor ID is required."));
    echo "Checkpoint 6: Doctor ID is missing<br>";
}

mysqli_close($conn);
?>
