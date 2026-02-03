# Job Application Tracking System

A Spring Boot API for tracking job applications. Users can register, manage applications, and query them by status.

## Features
- User registration
- Create job applications with company, position, and notes
- Track application status (APPLIED, INTERVIEW, REJECTED, OFFERED, etc.)
- Retrieve all applications or user-scoped applications

## Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL (or any supported JPA database)

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- A database supported by Spring Data JPA

### Configure
Update `src/main/resources/application.yaml` with your database connection details.

### Run locally
```bash
./mvnw spring-boot:run
```

### Run tests
```bash
./mvnw test
```

## Docker

### Build the application jar
```bash
./mvnw clean package
```

### Run with Docker Compose
```bash
docker compose up --build
```

The API will be available at `http://localhost:8080`.

## API Overview

### User endpoints
- `GET /api/user/all` — list users
- `GET /api/user/email/{email}` — find user by email
- `GET /api/user/id/{id}` — find user by id
- `POST /api/user` — create a user
- `DELETE /api/user/{id}` — delete a user

### Job application endpoints
- `GET /api/job/all` — list all job applications
- `GET /api/job/all-user/{userId}` — list job applications for a user
- `POST /api/job/{id}` — create a job application for a user id
- `GET /api/job/user/{userId}/job/{id}` — fetch a job application for a user
- `GET /api/job/count/{userId}` — count job applications for a user

## Contributing
See [CONTRIBUTING.md](CONTRIBUTING.md).

## Code of Conduct
See [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md).

## Security
See [SECURITY.md](SECURITY.md).

## License
This project is licensed under the terms of the [LICENSE](LICENSE) file.
