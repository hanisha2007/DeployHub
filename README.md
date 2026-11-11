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
- Java 25
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
```

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/hanisha2007/DeployHub.git
cd DeployHub
```

### 2. Create a Docker Network

```bash
docker network create deployhub-network
```

### 3. Start the PostgreSQL Container

```bash
docker run -d --name deployhub-db --network deployhub-network -e POSTGRES_DB=deployhub -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=your_password postgres:17
```

Replace `your_password` with your own PostgreSQL password.

### 4. Build the Docker Image

```bash
docker build -t deployhub .
```

### 5. Run the Application

```bash
docker run -d --name deployhub-container --network deployhub-network -p 8080:8080 -e PORT=8080 -e DB_URL=jdbc:postgresql://deployhub-db:5432/deployhub -e DB_USERNAME=postgres -e DB_PASSWORD=your_password deployhub
```

Use the same PostgreSQL password configured in Step 3.

### 6. Access API Documentation

Open the following URL in your browser:

http://localhost:8080/swagger-ui/index.html

## Environment Variables

DeployHub uses PostgreSQL as its database.

Before running the application, create a PostgreSQL database named `deployhub` and configure the following environment variables using your own database credentials:

- `DB_URL` – PostgreSQL database connection URL.
- `DB_USERNAME` – PostgreSQL username.
- `DB_PASSWORD` – PostgreSQL password.
- `PORT` – Application port (optional; defaults to 10000).

## Architecture

```text
    Client (Postman)
          |
          v
     Controllers
          |
          v
       Services
          |
          v
     Repositories
          |
          v
      PostgreSQL
```

## Deployment

DeployHub is deployed on Render using a PostgreSQL database.

The application is containerized using Docker, making it easier to build and run consistently across different environments.
