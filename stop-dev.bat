@echo off
setlocal enabledelayedexpansion
title NETWORK-SYSTEM - STOP

echo ==========================================
echo   Network System - Stop (Windows)
echo ==========================================
echo.

call :KillPort 8080
call :KillPort 5173

echo.
echo [OK] Stop concluido.
echo.
pause
exit /b 0

:KillPort
set "PORT=%~1"
echo Parando processos na porta %PORT%...

for /f "tokens=5" %%P in ('netstat -aon ^| findstr /r /c:":%PORT% .*LISTENING"') do (
    echo  - Matando PID %%P (porta %PORT%)
    taskkill /PID %%P /F >nul 2>nul
)

REM Também mata conexões ESTABLISHED que seguram porta (raramente necessário)
for /f "tokens=5" %%P in ('netstat -aon ^| findstr /r /c:":%PORT% .*ESTABLISHED"') do (
    echo  - Matando PID %%P (conexao ativa na porta %PORT%)
    taskkill /PID %%P /F >nul 2>nul
)

exit /b 0
