<?php

header('Content-Type: application/json; charset=utf-8');

// Remove qualquer buffer acidental
if (ob_get_length()) {
    ob_clean();
}

$action = $_POST['action'] ?? '';
$target = $_POST['target'] ?? '';

$response = [
    'success' => false,
    'action'  => $action,
    'target'  => $target,
    'output'  => null,
    'error'   => null
];

if (!$action || !$target) {
    $response['error'] = 'Missing parameters';
    echo json_encode($response);
    exit;
}

// Caminho do runner ajustado para Windows e Linux
if (strtoupper(substr(PHP_OS, 0, 3)) === 'WIN') {
    $runner = '"C:\\Users\\dolit\\Projects\\multiplataforma\\network-system\\cpp\\build\\Debug\\runner.exe"';
} else {
    $runner = __DIR__ . "/../cpp/runner";
}

// Comando com escape e redirecionamento de erros para stdout
$cmd = "$runner $action " . escapeshellarg($target) . " 2>&1";
// file_put_contents('debug.txt', "CMD=$cmd\n", FILE_APPEND);

// Executa e captura a saída
$output = shell_exec($cmd);
// file_put_contents('debug.txt', "OUTPUT=$output\n", FILE_APPEND);

if ($output === null) {
    $response['error'] = 'Execution failed';
} else {
    $response['success'] = true;
    $response['output']  = $output;
}

echo json_encode($response, JSON_UNESCAPED_UNICODE);
exit;