# DeployHub

DeployHub is a backend application that allows users to manage projects, collaborate with team members, and track deployment activities.
It provides secure authentication, role-based access control, project management, and deployment tracking through REST APIs.

## Features

- User Registration and Login
- Secure Password Hashing using BCrypt
- JWT-based Authentication
- Role-Based Access Control (Owner, Editor, Viewer)
- Project Creation and Management (CRUD)
- Team Member Management
- Deployment Tracking
- Pagination and Sorting
- REST API Documentation using Swagger
- Centralized Exception Handling
- Application Logging
- Docker-based Containerization
- Cloud Deployment

## Technology Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security

### Database
- PostgreSQL

### Authentication
- JWT
- BCrypt

### Tools & Deployment
- Maven
- IntelliJ IDEA
- Postman
- Swagger / OpenAPI
- Docker
- Render
- Git & GitHub

## Prerequisites

- Docker Desktop
- Git

## API Documentation

DeployHub provides interactive API documentation using Swagger UI.

After starting the application, access:

http://localhost:8080/swagger-ui/index.html

Users can explore available endpoints, view request/response formats, and test APIs directly through Swagger UI.

## Project Structure

```text
DeployHub/
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/com/deployhub/
│   │   │   ├── controller/    # Handles incoming API requests
│   │   │   ├── dto/           # Data Transfer Objects
│   │   │   ├── exception/     # Global exception handling
│   │   │   ├── model/         # Entity classes
│   │   │   ├── repository/    # Database operations
│   │   │   ├── security/      # JWT and security configuration
│   │   │   ├── service/       # Business logic
│   │   │   └── DeployHubApplication.java
│   │   │
│   │   └── resources/         # Application configuration
│   │
│   └── test/                  # Test files
│
├── .gitattributes
├── .gitignore
├── Dockerfile
└── README.md
