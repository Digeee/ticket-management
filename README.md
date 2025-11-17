# Ticket Management System

A complete ticket management system built with Spring Boot that includes full CRUD functionality for managing support tickets.

## Features

- Create, Read, Update, and Delete tickets
- Modern, responsive UI with Bootstrap 5
- Ticket status tracking (Open, In Progress, Resolved, Closed)
- Priority levels (Low, Medium, High, Urgent)
- Assignment capabilities
- Search functionality
- Sample data initialization

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## How to Run

### Option 1: Using the provided batch files
1. Double-click on `run.bat` to run the application directly
2. Double-click on `build.bat` to build the application

### Option 2: Using Maven commands
1. Open a terminal in the project root directory
2. Run the following command:
   ```bash
   mvn spring-boot:run
   ```
   
   Or to build and run:
   ```bash
   mvn clean install
   java -jar target/ticket-management-0.0.1-SNAPSHOT.jar
   ```

3. Access the application at: http://localhost:8080

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/ticket/management/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── TicketManagementApplication.java
│   └── resources/
│       ├── templates/
│       ├── static/
│       └── application.properties
```

## Technology Stack

- Spring Boot 3.1.0
- Spring Data JPA
- Thymeleaf
- H2 Database
- Bootstrap 5
- Font Awesome

## Functionality

### Ticket Management
- Create new tickets with title, description, priority, status, and assignee
- View all tickets in a responsive table
- Edit existing tickets
- Delete tickets
- Search tickets by title

### UI Features
- Modern gradient design
- Responsive layout for all devices
- Interactive cards with hover effects
- Color-coded priority and status indicators
- Smooth animations and transitions
- Confirmation dialogs for delete actions

## Database

The application uses an in-memory H2 database for simplicity. Data is initialized with sample tickets on startup.

You can access the H2 console at: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: password

## API Endpoints

- `GET /` - Display all tickets
- `GET /showNewTicketForm` - Show form to create a new ticket
- `POST /saveTicket` - Save a ticket
- `GET /showFormForUpdate/{id}` - Show form to update a ticket
- `GET /deleteTicket/{id}` - Delete a ticket
- `GET /search?keyword={keyword}` - Search tickets by title

## Customization

You can customize the application by modifying:
- `application.properties` for database configuration
- Templates in `src/main/resources/templates/` for UI changes
- CSS styles in `layout.html`