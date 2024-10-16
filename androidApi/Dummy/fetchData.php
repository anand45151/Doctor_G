<?php

$conn = mysqli_connect("localhost", "root", "1234");
mysqli_select_db($conn, "demo3");
$query = "SELECT * FROM user_tbl";
$raw = mysqli_query($conn, $query);

$data = array();

while ($row = mysqli_fetch_assoc($raw)) {
    $data[] = $row;
}
header('Content-Type: application/json');
echo json_encode($data);
?>
