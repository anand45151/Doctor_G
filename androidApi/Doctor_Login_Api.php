<?php
$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
$doctor_id = $_POST['doctor_id'];
$doctor_name = $_POST['doctor_name'];

if (!empty($doctor_id) && !empty($doctor_name)) {
    
    $doctor_id = $conn->real_escape_string($doctor_id);
    $doctor_name = $conn->real_escape_string($doctor_name);

    $query = "SELECT * FROM doctors WHERE Doctor_id = '$doctor_id' AND Doctor_Name = '$doctor_name' LIMIT 1";
    $result = $conn->query($query);

    if ($result->num_rows > 0) {
        $doctor = $result->fetch_assoc();
        $response = [
            'status' => 'success',
            'message' => 'Login successful',
            'doctor' => [
                'id' => $doctor['Doctor_id'],
                'name' => $doctor['Doctor_Name'],
                'specialty' => $doctor['Doctor_Specialty'],
                'experience' => $doctor['Doctor_Experiences'],
                'location' => $doctor['Doctor_Location'],
                'photo' => $doctor['Doctor_Photo']
            ]
        ];
    } else {
        $response = [
            'status' => 'error',
            'message' => 'Invalid Doctor ID or Doctor Name'
        ];
    }
} else {
    // Missing fields
    $response = [
        'status' => 'error',
        'message' => 'Please provide both Doctor ID and Doctor Name'
    ];
}

$conn->close();

header('Content-Type: application/json');
echo json_encode($response);

?>
