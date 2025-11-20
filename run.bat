@echo off
REM Run script for ovOOP project (Windows)

REM Check if project is compiled
if not exist "bin\ovOOP\Main.class" (
    echo Project not compiled. Running build script...
    echo.
    call build.bat
    echo.
)

REM Check if GSON library exists
if not exist "lib\gson-2.13.2.jar" (
    echo ERROR: GSON library not found at lib\gson-2.13.2.jar
    exit /b 1
)

REM Run the application
java -cp "bin;lib/*" ovOOP.Main
