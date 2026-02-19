@echo off
setlocal enabledelayedexpansion
title NETWORK-SYSTEM - DEV START

REM ==========================================
REM  Config
REM ==========================================
set "SPRING_PORT=8080"
set "VUE_PORT=5173"
set "CPP_CONFIG=Debug"   REM troque pra Release se quiser: Release

echo ==========================================
echo   Network System - Dev Start (Windows)
echo ==========================================
echo.

REM Vai pra raiz do projeto
cd /d "%~dp0"

REM ==========================================
REM  0) Checar portas e parar se ocupadas
REM ==========================================
call :CheckPort %SPRING_PORT%
if errorlevel 1 (
    echo.
    echo [AVISO] Porta %SPRING_PORT% esta ocupada (Spring).
    echo        Rode stop-dev.bat ou feche o processo.
    echo.
    pause
    exit /b 1
)

call :CheckPort %VUE_PORT%
if errorlevel 1 (
    echo.
    echo [AVISO] Porta %VUE_PORT% esta ocupada (Vue).
    echo        Rode stop-dev.bat ou feche o processo.
    echo.
    pause
    exit /b 1
)

REM ==========================================
REM  1) Localizar Visual Studio via vswhere
REM ==========================================
set "VSWHERE=%ProgramFiles(x86)%\Microsoft Visual Studio\Installer\vswhere.exe"

if not exist "%VSWHERE%" (
    echo [ERRO] vswhere nao encontrado:
    echo        %VSWHERE%
    echo Instale VS Build Tools (C++ Build Tools).
    pause
    exit /b 1
)

for /f "usebackq tokens=* delims=" %%I in (`"%VSWHERE%" -latest -products * -requires Microsoft.VisualStudio.Component.VC.Tools.x86.x64 -property installationPath`) do (
    set "VSINSTALL=%%I"
)

if "%VSINSTALL%"=="" (
    echo [ERRO] Nao encontrei VS com C++ Build Tools.
    pause
    exit /b 1
)

set "VSDEVCMD=%VSINSTALL%\Common7\Tools\VsDevCmd.bat"
if not exist "%VSDEVCMD%" (
    echo [ERRO] VsDevCmd.bat nao encontrado:
    echo        %VSDEVCMD%
    pause
    exit /b 1
)

REM Carrega ambiente do MSVC/CMake
call "%VSDEVCMD%" -no_logo >nul

where cmake >nul 2>nul
if errorlevel 1 (
    echo [ERRO] cmake nao encontrado no PATH apos VsDevCmd.
    echo        Marque "CMake tools for Windows" no VS Installer ou instale CMake.
    pause
    exit /b 1
)

echo [OK] Ambiente MSVC/CMake carregado.
echo.

REM ==========================================
REM
