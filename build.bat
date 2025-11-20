@echo off
REM Build script for ovOOP project (Windows)

echo ====================================
echo   ovOOP Build Script
echo ====================================
echo.

REM Check if GSON library exists
if not exist "lib\gson-2.13.2.jar" (
    echo ERROR: GSON library not found at lib\gson-2.13.2.jar
    echo Please download GSON from https://github.com/google/gson/releases
    echo and place it in the lib\ directory.
    exit /b 1
)

echo [OK] GSON library detected: lib\gson-2.13.2.jar
echo.

REM Create bin directory if it doesn't exist
echo Creating bin directory...
if not exist bin mkdir bin

REM Compile the project
echo Compiling Java source files...
javac -cp "lib/*;src" src\ovOOP\*.java -d bin\

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ====================================
    echo   Build successful!
    echo ====================================
    echo.
    echo To run the application:
    echo   java -cp "bin;lib/*" ovOOP.Main
    echo.
) else (
    echo.
    echo ====================================
    echo   Build failed!
    echo ====================================
    exit /b 1
)
