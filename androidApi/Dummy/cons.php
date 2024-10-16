<?php

$host = "localhost";
$username = "root";
$password = "1234";
$db = "quiz";
$conn = mysqli_connect($host, $username, $password, $db);

$smtm = $conn->prepare(
    "SELECT  `question`, `opt_1`, `opt_2`, `opt_3`, `opt_4`, `correct_opt` FROM `math_table`"
);

$smtm->execute();

$smtm->bind_result($question , $opt1,$opt2,$opt3,$opt4,$correct_option);

$question_array = array();
while ($smtm->fetch()) 
{
        $temp = array();
        $temp['question'] = $question;
        $temp['opt_1'] = $opt1;
        $temp['opt_2'] = $opt2;
        $temp['opt_3'] = $opt3;
        $temp['opt_4'] = $opt4;
        $temp['correct_opt'] = $correct_option;
        array_push($question_array,$temp);

}

echo json_encode($question_array);

?>