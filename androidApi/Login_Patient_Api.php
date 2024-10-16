<?php
$conn = mysqli_connect("localhost", "root", "1234", "corephpadmin");

if ($conn->connect_error) {
    echo json_encode(["error" => "Connection failed: " . $conn->connect_error]);
    exit;
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $f_name = isset($_POST['f_name']) ? $_POST['f_name'] : '';
    $email = isset($_POST['email']) ? $_POST['email'] : '';

    if (!empty($f_name) && !empty($email)) {
        $query = "SELECT id FROM customers WHERE f_name = ? AND email = ?";
        $stmt = $conn->prepare($query);

        if ($stmt) {
            $stmt->bind_param("ss", $f_name, $email);
            $stmt->execute();
            $result = $stmt->get_result();

            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();
                echo json_encode([
                    "success" => true,
                    "message" => "Login successful!",
                    "patient_id" => $row['id'] 
                ]);
            } else {
                echo json_encode(["success" => false, "message" => "Invalid first name or email!"]);
            }
            $stmt->close();
        } else {
            echo json_encode(["success" => false, "message" => "Query preparation failed."]);
        }
    } else {
        echo json_encode(["success" => false, "message" => "First name and email are required!"]);
    }
} else {
    echo json_encode(["success" => false, "message" => "Invalid request method."]);
}

$conn->close();
?>
