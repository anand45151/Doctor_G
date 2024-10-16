<?php
$conn = mysqli_connect("localhost", "root", "1234");
mysqli_select_db($conn, "corephpadmin");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);

}


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $patient_id = $_POST['patient_id'];
    $doctor_id = $_POST['doctor_id'];
    $disease_description = $_POST['disease_description'];
    $appointment_date = $_POST['appointment_date'];
    $status = 'Pending'; // Default status

    $appointment_date = date('Y-m-d', strtotime($appointment_date));

    if (!empty($patient_id) && !empty($doctor_id) && !empty($appointment_date)) {
        $checkQuery = "SELECT * FROM appointments WHERE patient_id = ? AND doctor_id = ? AND (status = 'Pending' OR status = 'Confirmed')";
        $checkStmt = $conn->prepare($checkQuery);
        $checkStmt->bind_param("ii", $patient_id, $doctor_id);
        $checkStmt->execute();
        $result = $checkStmt->get_result();

        if ($result->num_rows > 0) {
            echo json_encode(["error" => "You already have a pending or confirmed appointment with this doctor."]);
        } else {
            $query = "INSERT INTO appointments (patient_id, doctor_id, disease_description, appointment_date, status)
                      VALUES (?, ?, ?, ?, ?)";
            $stmt = $conn->prepare($query);
            $stmt->bind_param("iisss", $patient_id, $doctor_id, $disease_description, $appointment_date, $status);

            if ($stmt->execute()) {
                echo json_encode(["message" => "Appointment booked successfully!"]);
            } else {
                echo json_encode(["error" => "Failed to book appointment"]);
            }
            $stmt->close();
        }
        $checkStmt->close();
    } else {
        echo json_encode(["error" => "Required fields are missing!"]);
    }
}
$conn->close();
?>
