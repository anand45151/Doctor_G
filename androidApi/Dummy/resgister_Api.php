<?php

// ..
$conn = mysqli_connect("localhost", "root", "1234");
mysqli_select_db($conn, "demo3");


$id =     $_POST["id"];
$uname =     $_POST["uname"];
$uaddress =     $_POST["uaddress"];
$uphone =     $_POST["uphone"];
$ubod =     $_POST["ubod"];
$query = "select * from user_tbl where  uname = '$uname'";

$raw = mysqli_query($conn, $query);

$count = mysqli_num_rows($raw);
if ($count > 0) {

    $response['message'] = 'exist';
} else {

    $query2 = "INSERT INTO user_tbl VALUES ('$id','$uname','$uaddress','$uphone','$ubod')";

    $res  = mysqli_query($conn, $query2);
    if ($res == true) {
        $response['message'] = 'success';
    } else {
        $response['message'] = 'Not Done Yet ';
    }
}
echo json_encode($response);
