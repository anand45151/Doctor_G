<?php

$conn = mysqli_connect("localhost", "root", "1234");
mysqli_select_db($conn, "corephpadmin");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $doctorName = $_POST['Doctor_Name'];
    $doctorSpecialty = $_POST['Doctor_Specialty'];
    $doctorExperiences = $_POST['Doctor_Experiences'];
    $doctorLocation = $_POST['Doctor_Location'];
    $doctorPhoto = $_POST['Doctor_Photo'];  

    $sql = "INSERT INTO doctors (Doctor_Name, Doctor_Specialty, Doctor_Experiences, Doctor_Location, Doctor_Photo) 
            VALUES ('$doctorName', '$doctorSpecialty', '$doctorExperiences', '$doctorLocation', '$doctorPhoto')";

    if ($conn->query($sql) === TRUE) {
        echo json_encode(array("status" => "success", "message" => "Doctor registered successfully."));
    } else {
        echo json_encode(array("status" => "error", "message" => "Error: " . $conn->error));
    }
} else {
    echo json_encode(array("status" => "error", "message" => "Invalid request."));
}

$conn->close();
?>
