<?php
$conn = new mysqli("localhost", "root", "", "db_medisystem");

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}else{
    echo "Connected";
}
//echo "Connected successfully";
?>