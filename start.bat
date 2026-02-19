@echo off
title NETWORK SYSTEM - START

echo ================================
echo Iniciando Network System...
echo ================================

REM === C++ BUILD ===
echo.
echo [1/3] Compilando C++...
cd cpp

if not exist build (
    mkdir build
)

cd build
cmake ..
cmake --build .
cd ../..

echo C++ OK.
echo.

REM === SPRING BOOT ===
echo [2/3] Iniciando Spring Boot...
start "" powershell -NoExit -Command "cd java; .\mvnw spring-boot:run"


echo Spring Boot iniciando...
echo.

REM === VUE ===
echo [3/3] Iniciando Vue...
start cmd /k "cd vue3 && npm run dev"

echo.
echo =================================
echo Projeto iniciado.
echo =================================
pause
