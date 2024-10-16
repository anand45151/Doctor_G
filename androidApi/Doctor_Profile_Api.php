<?php
$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

if (isset($_GET['doctor_name'])) {  
    $doctor_name = $_GET['doctor_name'];

    $query = "SELECT Doctor_Name, Doctor_Specialty, Doctor_Experiences, Doctor_Location, Doctor_Photo FROM doctors WHERE Doctor_Name = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $doctor_name);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $doctor = $result->fetch_assoc();
        echo json_encode($doctor);  
    } else {
        echo json_encode(["message" => "Doctor not found"]);  
    }

    $stmt->close();
    $conn->close();
} else {
    echo json_encode(["message" => "Doctor name not provided"]);  
}
?>
