@echo off
echo Starting Ticket Management System...
echo.

where mvn >nul 2>&1
if %errorlevel% == 0 (
    echo Maven found. Building and running the application...
    echo.
    mvn spring-boot:run
) else (
    echo Maven not found. Please install Maven to run this application.
    echo.
    echo To run this Spring Boot application:
    echo 1. Install Maven 3.6 or higher
    echo 2. Make sure Maven is in your PATH
    echo 3. Run this script again
    echo.
    echo Alternatively, you can:
    echo 1. Install Maven
    echo 2. Navigate to this directory
    echo 3. Run: mvn spring-boot:run
    echo.
    pause
    exit /b
)