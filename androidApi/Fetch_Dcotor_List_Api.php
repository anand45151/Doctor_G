<?php
$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$query = "SELECT Doctor_id, Doctor_Name, Doctor_Specialty, Doctor_Experiences, Doctor_Location, Doctor_Photo FROM doctors";
$result = mysqli_query($conn, $query);

$data = array();

while ($row = mysqli_fetch_assoc($result)) {

    $row['Doctor_Photo'] = $row['Doctor_Photo'];
    $data[] = $row;
}

header('Content-Type: application/json');

echo json_encode($data);

mysqli_close($conn);

?>
