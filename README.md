# BridgeCare Auth Service

## Overview
The **BridgeCare Auth Service** is responsible for handling authentication and authorization within the BridgeCare platform. It is built using **Spring Boot** and **Spring Security**, implementing JWT-based authentication to ensure secure access to system resources.

## Features
- User authentication using **JWT tokens**.
- Role-based access control.
- Secure storage of user credentials.
- Database integration with **PostgreSQL**.
- Environment-based configuration using **dotenv**.

## Tech Stack
- **Spring Boot 3.4.3**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**

## Getting Started

### Prerequisites
- Java **17+**
- Maven **3.8+**
- Docker & Docker Compose
- PostgreSQL Database

### Installation

#### Clone the repository
```bash
git clone https://github.com/Proyecto-de-grado-BRIDGECARE/bridgecare-auth-service
cd bridgecare-auth-service
```

#### Setup environment variables
Copy `.env.example` to `.env` and set the required environment variables:
`cp .env.example .env`

#### Build and run
To build and run the service locally, use:
```bash
mvn clean package
java -jar target/auth-service-0.0.1-SNAPSHOT.jar
```

Or use Maven to run the application:
```bash
./mvnw spring-boot:run
```

**Or use Docker:**
```bash
docker-compose up --build
```

## API Endpoints

| Method | Endpoint           | Description              | Auth Required | Disabled |
|:------:|--------------------|--------------------------|:-------------:|:--------:|
| POST   | /api/auth/login    | User login               | ✅            | ❌       |
| POST   | /api/auth/register | User registration        | ✅            | ✅       |
| GET    | /api/auth/me       | Get current user details | ✅            | ❌       |
| POST   | /api/auth/logout   | Invalidate JWT token     | ✅            | ❌       |

## Security
This service uses **Spring Security** with **JWT-based authentication**.
Tokens must be included in the `Authorization` header as:
`Authorization: Bearer <your_token>`

## Running Tests
To run tests:
`mvn test`

## Contributing
1. Fork the repository.
2. Create a new branch.
3. Commit your changes.
4. Push to your fork.
5. Create a Pull Request.

## License
This project is licensed under the MIT License.
