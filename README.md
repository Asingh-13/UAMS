# User Access Management System

A web application for managing software access requests, allowing users to request access, managers to approve or reject requests, and admins to manage users and software.

## Table of Contents
1. [Project Structure](#project-structure)
2. [Prerequisites](#prerequisites)
3. [Database Setup](#database-setup)
4. [Application Setup](#application-setup)
5. [Running the Application](#running-the-application)
6. [Accessing the Application](#accessing-the-application)
7. [Additional Information](#additional-information)

---

## Project Structure


UserAccessManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── controller/          # Servlets for handling requests
│   │   │   ├── dao/                 # Data Access Objects for database interaction
│   │   │   └── model/               # Java classes representing data models
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── views/           # JSP views (login, signup, adminDashboard, etc.)
│   │       │   └── web.xml          # Deployment descriptor
│   │       └── css/                 # CSS for styling
└── README.md
                      # Project setup and run instructions
## Prerequisites
Java Development Kit (JDK) - Version 8 or higher
Apache Tomcat - Version 9 or higher
PostgreSQL Database - Version 10 or higher
Maven - For building the project and managing dependencies


## Database Setup
Create Database:

Open PostgreSQL and create a new database (e.g., uams_db).
Connect to your PostgreSQL server and create the database:
sql
Copy code
CREATE DATABASE uams_db;
Run Database Scripts:

Open your database client (e.g., pgAdmin or psql).
Run the provided SQL script (database_script.sql) to create the users, software, and requests tables.
Optionally, populate the tables with sample data for testing.
Update Database Connection Details:

In src/main/java/util/DatabaseConnection.java, update the connection details:
java
Copy code
private static final String dbURL = "jdbc:postgresql://localhost:5432/uams_db";
private static final String dbUsername = "your_postgresql_username";
private static final String dbPassword = "your_postgresql_password";

## Application Setup
Clone the Repository:

bash
Copy code
git clone https://github.com/your-username/UserAccessManagementSystem.git
Build the Project:

Navigate to the project directory and build with Maven:
bash
Copy code
cd UserAccessManagementSystem
mvn clean install
Configure Tomcat in IDE (if using an IDE like Eclipse or IntelliJ):

Go to Server settings, add Apache Tomcat, and point to the directory where Tomcat is installed.

## Running the Application
Deploy the Application to Tomcat:

Place the generated .war file in the webapps folder of your Tomcat installation or run the application directly from your IDE.
Start Tomcat:

Run Tomcat by executing startup.bat (Windows) or startup.sh (Linux/macOS) in the bin folder of the Tomcat installation.

## Accessing the Application
Open a Web Browser:

Go to http://localhost:8080/UserAccessManagementSystem/
Default Pages:

Login: http://localhost:8080/UserAccessManagementSystem/login.jsp
Sign Up: http://localhost:8080/UserAccessManagementSystem/signup.jsp
User Roles:

Admin: Manages users and software applications.
Manager: Approves or rejects access requests from employees.
Employee: Requests access to various software applications.

## Additional Information
Session Management: Each user must log in to access their role-specific pages. Sessions will expire after 30 minutes of inactivity.
Logout: A user can log out by clicking the logout link, which ends their session.
Troubleshooting
Database Connection Issues:

Ensure PostgreSQL is running and that the database URL, username, and password are correctly set in DatabaseConnection.java.
Application Not Deploying:

Confirm the project builds successfully without errors. If deploying manually, check that the .war file is copied to Tomcat’s webapps directory.
403/404 Errors:

Check that your URLs are correct based on your Tomcat server context.
Ensure role-based access restrictions in JSP files align with your database roles.
License
This project is open source and available for personal and educational use.
