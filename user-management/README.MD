# Simple User Management and Authentication System

## Overview

This project is a simple user management and authentication system implemented using Java and Spring Boot.

### Requirements

- Create, Update, Delete, List web service endpoints for a User object.
- User object should contain a name, email address, password, and the date of their last login.
- Provide a login endpoint that validates the email address and password provided by the user matches the one stored in the database.

## Implementation

### Framework and Tools

- Framework: Spring Boot
- Database: MySQL
- Dependencies: Spring Web, Spring Data JDBC, MySQL Driver, HIKARICP, mysql-connector-java

### Notes

- Framework: I chose to use Spring Boot for this project due to its ease of setup and extensive documentation.
- Database: I used MySQL as the database for this project. However, any relational database could be used.
- Security: While I implemented basic authentication, in a real-world scenario, I would use Spring Security for more advanced security features.
- Error Handling: In this code, I've kept error handling minimally, but in a real-world scenario, I would implement more robust error handling.

### Usage

To run this application, you need to have Java and Maven installed on your machine.

1. Clone this repository:

   ```bash
   git clone <repository_url>
   
### Navigate to the project directory

cd <project_directory>

### update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/db_name
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.datasource.driver.class.name=com.mysql.cj.jdbc.Driver

### Build the project

mvn clean install

### Run the application

mvn spring-boot:run

### API endpoints

- Create User: POST /users/create
- Update User: PUT /users/update/{id}
- Delete User: DELETE /users/delete/{id}
- List Users: GET /users/list
- Login: POST /users/login

### Sample Output

[
{
"id": 1,
"name": "xyz",
"email": "xyz@gmail.com",
"password": "password123",
"lastLogin": "2024-04-27T10:30:00.000+00:00"
},
{
"id": 2,
"name": "abc",
"email": "abc@gmail.com",
"password": "password1234",
"lastLogin": "2024-04-27T10:30:00.000+00:00"
}
]

### Assumptions

- Database Schema: I assumed a basic database schema for the User object with the following fields: `id`, `name`, `email`, `password`, and `lastLogin`.
- Password Storage: For simplicity, I stored passwords in plain text in the database. In a production environment, I would hash passwords for security.
- Date Format: Dates are stored in (`YYYY-MM-DDTHH:MM:SS.000Z`) format in the database.

### Enhancements

- Sever Port Configuration: Making the application service port configurable instead of using default http port (`8080`).
- Adding More Validation: Client validation and Server validation can be added for API requests.
- Password Encryption: Enhance security by encrypting passwords before storing them in the database.
- Role-based Access Control: Implement role-based access control to restrict certain endpoints to specific user roles.
- Email Verification: Implement email verification during user registration to ensure that the provided email addresses are valid.
- Forgot Password Functionality: Implement a "Forgot Password" functionality that allows users to reset their passwords via email.
- Logging: Implement logging to record important events and errors for easier debugging and monitoring of the application.
- Session Management: Implement session management to track user sessions and provide features like session expiration and invalidation.
- HTTPS Support: Enable HTTPS to encrypt data transmitted between the client and server for improved security.