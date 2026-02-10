<?php

$action = $_POST['action'];
$target = $_POST['target'];

$cmd = "../cpp/runner $action " . escapeshellarg($target);
echo shell_exec($cmd);