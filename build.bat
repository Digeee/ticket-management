@echo off
echo Building Ticket Management System...
echo.

where mvn >nul 2>&1
if %errorlevel% == 0 (
    echo Maven found. Building the application...
    echo.
    mvn clean install
    echo.
    echo Build completed. To run the application, use:
    echo java -jar target/ticket-management-0.0.1-SNAPSHOT.jar
) else (
    echo Maven not found. Please install Maven to build this application.
    echo.
    echo To build this Spring Boot application:
    echo 1. Install Maven 3.6 or higher
    echo 2. Make sure Maven is in your PATH
    echo 3. Run this script again
    echo.
    echo Alternatively, you can:
    echo 1. Install Maven
    echo 2. Navigate to this directory
    echo 3. Run: mvn clean install
    echo.
)
pause